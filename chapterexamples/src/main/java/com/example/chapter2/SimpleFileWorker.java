package com.example.chapter2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SimpleFileWorker {

    public static void main(String[] args) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("gamesCatalog.txt"));

            // header
            writer.write("name, company, year\r\n");

            // data
            writer.write("Pitfall!, Activision, 1982\r\n");
            writer.write("Crackpots, Activision, 1982\r\n");
            writer.write("Yars' Revenge, Atari, 1981\r\n");
            writer.write("Warlords, Atari, 1981\r\n");
            writer.write("Defender, Atari, 1981\r\n");
            writer.write("Adventure, Atari, 1980\r\n");

            writer.close();

        } catch (IOException writerEx) {
            System.out.println("Error occurred while writing:");
            writerEx.printStackTrace();
        }

        System.out.println("Writing completed!");
        System.out.println();

        try {

            BufferedReader reader = new BufferedReader(new FileReader("gamesCatalog.txt"));

            // read the first line
            String gameLine = reader.readLine();
            boolean headerRead = false;

            while (gameLine != null) {
                if (headerRead) {
                    String[] gameColumns = gameLine.split(",");
                    int year = Integer.parseInt(gameColumns[2].trim());
    
                    if (year == 1981) {
                        System.out.println(gameLine);
                    }
                } else {
                    headerRead = true;
                }

                // read the next line
                gameLine = reader.readLine();
            }
            
        } catch (IOException readerEx) {
            System.out.println("Error occurred while writing:");
            readerEx.printStackTrace();
        }
        
    }
    
}
