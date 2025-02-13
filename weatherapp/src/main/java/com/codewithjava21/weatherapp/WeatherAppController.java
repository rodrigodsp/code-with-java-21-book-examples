package com.codewithjava21.weatherapp;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/weather")
@RestController
public class WeatherAppController {

    RestTemplate restTemplate;

    WeatherAppRepository weatherRepo;


    @Autowired
    public WeatherAppController(RestTemplate restTemplate, WeatherAppRepository weatherRepo) {
        this.restTemplate = restTemplate;
        this.weatherRepo = weatherRepo;
    }

    public WeatherAppController(WeatherAppRepository weatherRepo) {
        this.weatherRepo = weatherRepo;
    }

    @GetMapping("/helloworld")
    public ResponseEntity<String> getHello() {
        return ResponseEntity.ok("Hello world!\n");
    }

    @PutMapping("/latest/station/{stationid}")
    public ResponseEntity<WeatherReading> putLastestData(
        @PathVariable(value="stationid") String stationId) {

        LatestWeather response = restTemplate.getForObject(
            "https://api.weather.gov/stations/" + stationId + "/observations/latest", 
            LatestWeather.class);

        // map latest reading to a WeatherEntity
        WeatherEntity weatherEntity = mapLatestWeatherToWeatherEntity(response, stationId);

        // save weather reading
        weatherRepo.save(weatherEntity);

        WeatherReading currentReading = mapWeatherEntityToWeatherReading(weatherEntity);

        return ResponseEntity.ok(currentReading);

    }

    @GetMapping("/latest/station/{stationid}/month/{month}")
    public ResponseEntity<WeatherReading> getLatestData(
        @PathVariable(value="stationid") String stationId,
        @PathVariable(value="month") int monthBucket) {

        WeatherEntity recentWeather = weatherRepo.findByStationIdAndMonthBucket(stationId, monthBucket);

        WeatherReading currentReading = mapWeatherEntityToWeatherReading(recentWeather);

        if (currentReading != null) {
            return ResponseEntity.ok(currentReading);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    protected int getBucket(Instant timestamp) {

        ZonedDateTime date = ZonedDateTime.parse(timestamp.toString());
        // parse date into year and month to create the month bucket
        Integer year = date.getYear();
        Integer month = date.getMonthValue();
        StringBuilder bucket = new StringBuilder(year.toString());

        if (month < 10) {
            bucket.append("0");
        }
        bucket.append(month);

        return Integer.parseInt(bucket.toString());
    }

    private WeatherEntity mapLatestWeatherToWeatherEntity(LatestWeather weather, String stationId) {

        WeatherEntity returnVal = new WeatherEntity();

        // use timestamp from response to create date
        Instant timestamp = weather.getProperties().getTimestamp();
        int bucket = getBucket(timestamp);

        // gen PK
        WeatherPrimaryKey key = new WeatherPrimaryKey(stationId, bucket, timestamp);

        returnVal.setPrimaryKey(key);
        returnVal.setReadingIcon(weather.getProperties().getIcon());
        returnVal.setStationCoordinatesLatitude(weather.getGeometry().getCoordinates()[0]);
        returnVal.setStationCoordinatesLongitude(weather.getGeometry().getCoordinates()[1]);
        returnVal.setTemperatureCelsius(weather.getProperties().getTemperature().getValue());
        returnVal.setWindDirectionDegrees(weather.getProperties().getWindDirection().getValue());
        returnVal.setWindGustKMH(weather.getProperties().getWindGust().getValue());
        returnVal.setPrecipitationLastHour(weather.getProperties().getPrecipitationLastHour().getValue());

        // process cloud layers
        CloudLayer[] clouds = weather.getProperties().getCloudLayers();
        Map<Integer,String> cloudMap = new HashMap<>();

        for (CloudLayer layer : clouds) {
            // measurements come back as floats, but we need ints
            cloudMap.put(layer.getBase().getValue(), layer.getAmount());
        }

        returnVal.setCloudCover(cloudMap);

        return returnVal;
    }

    private WeatherReading mapWeatherEntityToWeatherReading(WeatherEntity entity) {

        if (entity != null) {
            WeatherReading returnVal = new WeatherReading();

            returnVal.setStationId(entity.getPrimaryKey().getStationId());
            returnVal.setMontBucket(entity.getPrimaryKey().getMonthBucket());
            returnVal.setStationCoordinatesLatitude(entity.getStationCoordinatesLatitude());
            returnVal.setStationCoordinatesLongitude(entity.getStationCoordinatesLongitude());
            returnVal.setTimestamp(entity.getPrimaryKey().getTimestamp());
            returnVal.setTemperatureCelsius(entity.getTemperatureCelsius());
            returnVal.setWindSpeedKMH(entity.getWindSpeedKMH());
            returnVal.setWindDirectionDegrees(entity.getWindDirectionDegrees());
            returnVal.setWindGustKMH(entity.getWindGustKMH());
            returnVal.setReadingIcon(entity.getReadingIcon());
            returnVal.setVisibilityM(entity.getVisibilityM());
            returnVal.setPrecipitationLastHour(entity.getPrecipitationLastHour());
            returnVal.setCloudCover(entity.getCloudCover());

            return returnVal;
        }

        return null;
    }
    
}
