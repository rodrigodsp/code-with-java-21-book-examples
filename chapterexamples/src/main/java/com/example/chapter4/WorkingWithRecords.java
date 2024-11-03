package com.example.chapter4;

import java.util.List;
import java.util.ArrayList;

public class WorkingWithRecords {

    public static void main(String[] args) {
        
        List<String> cabinExits = new ArrayList();
        cabinExits.add("South");
        cabinExits.add("West");

        Room lakeCabin = new Room("Lake cabin",
            "You are standing outside of a cabin on a lake, with water visible to the South and East."
            + " There is a red dock to the South", cabinExits);

        System.out.println(lakeCabin.description());
        System.out.println(lakeCabin.getExits());
    }
    
}
