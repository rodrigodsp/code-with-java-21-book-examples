package com.example.chapter4;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Vector;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Hashtable;

public class WorkingWithCollections {
    
    public static void main(String[] args) {

        String[] heroes = {"Byorki", "K'lar", "Tyrenni", "Athena", "Jarrod"};

        // Set<String> heroSet = new HashSet<>();
        // Set<String> heroSet = new LinkedHashSet<>();
        Set<String> heroSet = new TreeSet<>();
        Collections.addAll(heroSet, heroes);
        printCollection(heroSet);
        heroSet.add("Byorki");
        printCollection(heroSet);
        heroSet.add("Rik");
        printCollection(heroSet);
        heroSet.remove("Rik");
        printCollection(heroSet);

        List<String> monsterList = new ArrayList<>();
        // List<String> monsterList = new Vector<>(); // thread-safe

        monsterList.add("Kobald");
        monsterList.add("Skeleton");
        monsterList.add("Zombie");
        monsterList.add("Rats");
        monsterList.add("Skeleton");

        printCollection(monsterList);
        Collections.sort(monsterList);
        printCollection(monsterList);
        monsterList.remove("Skeleton");
        printCollection(monsterList);
        monsterList.remove(2);
        printCollection(monsterList);
        System.out.println(monsterList.get(1));

        LinkedList<String> cityList = new LinkedList<>();
        cityList.add("Elddim");
        cityList.add("Crystwind");
        cityList.add("Fallraen");
        cityList.add("Meren");
        cityList.add("Lang");

        printCollection(cityList);

        System.out.println(cityList.get(3));

        cityList.remove("Meren");
        printCollection(cityList);

        System.out.println(cityList.peek());
        printCollection(cityList);

        System.out.println(cityList.peekLast());
        printCollection(cityList);

        System.out.println(cityList.poll());
        printCollection(cityList);

        System.out.println(cityList.pollLast());
        printCollection(cityList);

        Map<String,String> spellbook = new TreeMap<>();
        // Map<String,String> spellbook = new Hashtable<>(); // thread-safe
        spellbook.put("Fireball",
            "A ball of fire that inflicts 8 damage per level of magic.");
        spellbook.put("Healing Touch",
            "Touching an injured player recovers 5 hit points per character level.");
        spellbook.put("Lightning Bolt",
            "A stream of lightning that inflicts 10 damage per level of magic.");
        spellbook.put("Create Water",
            "Creates 10 liters of water per level of magic.");
        spellbook.put("Transmutation",
            "Converts common items into gold.");

        // printMap(spellbook);
        System.out.println(spellbook.get("Lightning Bolt"));
        printKeys(spellbook);
        
    }

    private static void printCollection(Collection collection) {

        for (Object element : collection) {
            System.out.printf("%s, ", element.toString());
        }

        System.out.println();
    }

    private static void printMap(Map map) {

        System.out.println();
        Set<Object> keys = map.keySet();

        for (Object key : keys) {
            System.out.printf("%s: %s%n", key, map.get(key));
        }
    }

    private static void printKeys(Map map) {

        System.out.println();
        Set<Object> keys = map.keySet();

        for (Object key : keys) {
            System.out.printf("%s, ", key);
        }
        System.out.println();
    }
}
