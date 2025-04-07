package com.codewithjava21.movieapp.batchloader;

import com.codewithjava21.movieapp.cassandraconnect.LocalConnection;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.data.CqlVector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MovieDataLoader {

    private static CqlSession session;
    private static PreparedStatement INSERTStatement;
    private static PreparedStatement INSERTByTitleStatement;

    private final static String strCQLINSERT = 
            "INSERT INTO movies "
            + "(movie_id, imdb_id, original_language, genres, "
            + "website, title, description, release_date, year, "
            + "budget, revenue, runtime, movie_vector) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ";

    private final static String strCQLINSERTByTitle = 
            "INSERT INTO movies_by_title "
            + "(title, movie_id) VALUES (?,?)";

    private static Map<String,Integer> genreIDMainMap = new HashMap<>();
    private static Map<Integer,Integer> collectionIDMainMap = new HashMap<>();

    public static void main(String[] args) {
        LocalConnection conn = new LocalConnection();
        session = conn.getCqlSession();

        INSERTStatement = session.prepare(strCQLINSERT);
        INSERTByTitleStatement = session.prepare(strCQLINSERTByTitle);

        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader("movieappdataloader/data/movies_metadata.csv"));
            String movieLine = reader.readLine();
            boolean headerRead = false;
            int movieCount = 0;
            int movieFail = 0;

            while (movieLine != null) {
                try {
                    if (headerRead) {
                        movieCount++;
                        String[] movieColumns = movieLine
                            .split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
    
                        Movie movie = new Movie();
    
                        String collections = movieColumns[1];
                        movie.setBudget(Long.parseLong(movieColumns[2]));
                        String genres = movieColumns[3];
                        movie.setWebsite(movieColumns[4]);
                        movie.setMovieId(Integer.parseInt(movieColumns[5]));
                        movie.setImdbId(movieColumns[6]);
                        movie.setOriginalLanguage(movieColumns[7]);
                        movie.setDescription(movieColumns[9]);
                        float popularity = Float.parseFloat(movieColumns[10]);
    
                        if (!movieColumns[14].isEmpty()) {
                            movie.setReleaseDate(LocalDate.parse(movieColumns[14]));
                            movie.setYear(movie.getReleaseDate().getYear());
                        }
    
                        movie.setRevenue(Long.parseLong(movieColumns[15]));
    
                        if (!movieColumns[16].isEmpty()) {
                            movie.setRuntime(Float.parseFloat(movieColumns[16]));
                        } else {
                            movie.setRuntime(0F);
                        }
    
                        movie.setTitle(movieColumns[20]);
                        float voteAverage = Float.parseFloat(movieColumns[22]);
                        int voteCount = Integer.parseInt(movieColumns[23]);
                        int collectionId = getCollectionId(collections);
    
                        // process genres
                        Map<Integer,String> genreMap = buildGenreMap(genres);
                        movie.setGenres(genreMap);
                        Integer[] genre = getGenreIds(movie.getGenres().keySet());
    
                        CqlVector<Float> vector = CqlVector.newInstance(
                            generateVector(collectionId, genre, popularity, voteAverage, voteCount));
    
                        movie.setVector(vector);
                        System.out.println(movie.getTitle());
                        writeToCassandra(movie);
                        
                    } else {
                        headerRead = true;
                    }
                } catch (Exception e) {
                    movieFail++;
                    System.out.printf("Error ocurred while reading the line %d and writing to Cassandra:", (movieFail + 1));
                    e.printStackTrace();
                }
                // read the next line
                movieLine = reader.readLine();
            }

            reader.close();
            System.out.printf("%d movies in the file\n", movieCount);
            System.out.printf("%d movies written\n", (movieCount - movieFail));
            System.out.printf("%d movies with error (not written)\n", movieFail);

        } catch (IOException readerEx) {
            System.out.println("Error ocurred while reading:");
            readerEx.printStackTrace();
        }
    }

    private static void writeToCassandra(Movie movie) {

        // write movie data
        BoundStatement movieInsert = INSERTStatement.bind(
            movie.getMovieId(), movie.getImdbId(), movie.getOriginalLanguage(), movie.getGenres(),
            movie.getWebsite(), movie.getTitle(), movie.getDescription(), movie.getReleaseDate(),
            movie.getYear(), movie.getBudget(), movie.getRevenue(), movie.getRuntime(), movie.getVector());
        session.execute(movieInsert);

        // write movies_by_title
        BoundStatement movieByTitleInsert = INSERTByTitleStatement.bind(
            movie.getTitle().toLowerCase(), movie.getMovieId());
        session.execute(movieByTitleInsert);
    }

    private static int getCollectionId(String collections) {
        int collectionId = 0;
        boolean idFound = false;
        String[] collArray = collections.split(",");

        for (String collection : collArray) {
            String[] kv = collection.split(":");

            if (kv[0].contains("'id'")) {
                idFound = true;
                int originalCollectionId = Integer.parseInt(kv[1].trim());

                if (collectionIDMainMap.containsKey(originalCollectionId)) {
                    collectionId = collectionIDMainMap.get(originalCollectionId);
                } else {
                    collectionId = collectionIDMainMap.size() + 1;
                    collectionIDMainMap.put(originalCollectionId, collectionId);
                }
                break;
            }
        }

        if (!idFound) {
            collectionId = 999;
        }

        return collectionId;
    }

    private static Map<Integer,String> buildGenreMap(String genres) {

        Map<Integer,String> returnVal = new HashMap<>();
        String[] genreArray = genres.split(",");

        Integer id = 0;
        String name = "";

        for (String genre : genreArray) {
            String[] genreKV = genre.split(":");

            if (genreKV[0].contains("'name'")) {
                name = genreKV[1]
                    .replaceAll("'", "")
                    .replaceAll("\"", "")
                    .replaceAll("}", "")
                    .replaceAll("]", "");

                // is name a genreIDMainMap?
                if (genreIDMainMap.containsKey(name)) {
                    id = genreIDMainMap.get(name);
                } else {
                    id = genreIDMainMap.size() + 1;
                    genreIDMainMap.put(name, id);
                }
                returnVal.put(id, name.trim());
            }
        }

        return returnVal;
    }

    private static Integer[] getGenreIds(Set<Integer> genreIds) {

        Integer[] genre = {0, 0, 0};
        int counter = 0;

        for (Integer id : genreIds) {
            if (counter >= genre.length) {
                break;
            }

            genre[counter] = id;
            counter++;
        }

        return genre;
    }

    private static List<Float> generateVector(Integer collectionId,
            Integer[] genre, float popularity, float voteAverage,
            Integer voteCount) {
        
        List<Float> returnVal = new ArrayList<>();

        returnVal.add(Float.parseFloat(collectionId.toString()));
        returnVal.add(Float.parseFloat(genre[0].toString()));
        returnVal.add(Float.parseFloat(genre[1].toString()));
        returnVal.add(Float.parseFloat(genre[2].toString()));
        returnVal.add(popularity);
        returnVal.add(voteAverage);
        returnVal.add(Float.parseFloat(voteCount.toString()));

        return returnVal;
    }
    
}
