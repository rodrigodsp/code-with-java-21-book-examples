package com.example.chapter7;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import com.example.chapter7.AstronautCassandraDAL.AstronautMission;

public class GeminiAstronautsNoSQL {

    public static void main(String[] args) {
        
        String host = System.getenv("CASSANDRA_DB_HOST");
        String port = System.getenv("CASSANDRA_DB_PORT");
        String keyspace = System.getenv("CASSANDRA_DB_KEYSPACE");
        String localDatacenter = System.getenv("CASSANDRA_DB_LOCAL_DATACENTER");

        AstronautCassandraDAL astronautDAL = new AstronautCassandraDAL(host, port, keyspace, localDatacenter);

        System.out.println("Project Gemini Astronauts:");

        List<String> geminiAstronauts = astronautDAL.getGeminiRoster();

        for (String astronaut : geminiAstronauts) {
            System.out.println(astronaut);
        }

        System.out.println();

        Set<Integer> randomMissions = new HashSet<>();
        Random random = new Random();
        // generate 3 random numbers
        while (randomMissions.size() < 3) {
            int missionNumber = random.nextInt(10) + 3;
            randomMissions.add(missionNumber);
        }

        for (Integer missionNum : randomMissions) {

            StringBuilder mission = new StringBuilder("Gemini ");
            mission.append(missionNum.toString());
            List<AstronautMission> missionAstronauts = astronautDAL.getMissionAstronauts(mission.toString());
            
            for (AstronautMission astronautMission : missionAstronauts) {
                System.out.print(astronautMission.missionName() + " ");
                System.out.print(astronautMission.startDate() + " -> ");
                System.out.print(astronautMission.endDate() + " - ");
                System.out.println(astronautMission.astronautName());
            }

            System.out.println();
        }
    }
    
}
