package com.example.chapter4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class RPGSimulation {
    
    record Monster (String name, int attack, int maxDamage, int defense) {
        static Random random = new Random();
        static int hitPoints = 2;
        static boolean alive = true;

        public int rollAttack() {
            return random.nextInt(attack) + 1;            
        }

        public int rollDamage() {
            return random.nextInt(maxDamage) + 1;
        }

        public boolean isAlive() {
            return alive;
        }

        public void decrementHitPoints(int damage) {
            hitPoints -= damage;
            if (hitPoints <= 0) {
                alive = false;
            }
        }
    }

    public static void main(String[] args) {
        Random randomNumber = new Random();
        int monsterCount = randomNumber.nextInt(4) + 1;

        List<Monster> monters = new ArrayList<>();

        for (int monsterIdx = 0; monsterIdx < monsterCount; monsterIdx++) {

            int typeIdx = randomNumber.nextInt(4);

            switch (typeIdx) {
                case 0:
                    monters.add(new Monster("Kobald", 2, 8, 1));
                    break;
                case 1:
                    monters.add(new Monster("Skeleton", 2, 8, 2));
                    break;
                case 2:
                    monters.add(new Monster("Zombie", 1, 6, 2));
                    break;
                default:
                    monters.add(new Monster("Rats", 1, 4, 1));
            }
        }

        Map<String,String> spellbook = new TreeMap<>();
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

        Hero byorki = new Hero("Byorki", 8, 5, 5);
        Hero klar = new Hero("K'lar", 10, 12, 3);
        Hero tyrenni = new Hero("Tyrenni", 6, 2, 6, spellbook);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(byorki);
        heroes.add(klar);
        heroes.add(tyrenni);

        List<Object> playerOrder = generatePlayOrder(heroes, monters);

        for (Object player : playerOrder) {
            System.out.println();

            if (player instanceof Hero hero) {
                if (hero.isAlive()) {
                    String name = hero.getName();

                    int monsterIndex = randomNumber.nextInt(monters.size());
                    Monster targetMonster = monters.get(monsterIndex);

                    System.out.println(name + " attacks " + targetMonster.name());

                    int attack = hero.rollAttack();
                    if (attack >= targetMonster.defense) {
                        int damage = hero.rollDamage();
                        System.out.println(name + " rolls a " + attack + " and hits " + targetMonster.name + " for " + damage + " points.");
                        targetMonster.decrementHitPoints(damage);

                        if (!targetMonster.isAlive()) {
                            System.out.println(targetMonster.name + " is down!");
                        }
                    } else {
                        System.out.println(name + " rolls a " + attack + " and misses " + targetMonster.name);
                    }
                }
            } else if (player instanceof Monster monster) {
                if (monster.isAlive()) {
                    String name = monster.name;

                    int heroIndex = randomNumber.nextInt(heroes.size());
                    Hero targetHero = heroes.get(heroIndex);
                    String heroName = targetHero.getName();

                    System.out.println(name + " attacks " + heroName);

                    int attack = monster.rollAttack();
                    if (attack >= targetHero.getDefense()) {
                        int damage = monster.rollDamage();
                        System.out.println(name + " rolls a " + attack + " and hits " + heroName + " for " + damage + " points.");
                        targetHero.decrementHitPoints(damage);

                        if (!targetHero.isAlive()) {
                            System.out.println(heroName + " is down!");
                        }
                    } else {
                        System.out.println(name + " rolls a " + attack + " and misses " + heroName);
                    }
                }
            }
        }
    }

    private static List<Object> generatePlayOrder(List<Hero> heroList, List<Monster> monsterList) {

        List<Hero> tempHeroList = new ArrayList<>(List.copyOf(heroList));
        List<Monster> tempMonsterList = new ArrayList<>(List.copyOf(monsterList));

        List<Object> returnValue = new ArrayList<>();
        Random random = new Random();
        int playerCount = heroList.size() + monsterList.size();

        while (returnValue.size() < playerCount) {
            if (random.nextBoolean()) {
                if (!tempHeroList.isEmpty()) {
                    int heroIndex = random.nextInt(tempHeroList.size());
                    returnValue.add(tempHeroList.get(heroIndex));
                    tempHeroList.remove(heroIndex);
                }
            } else {
                if (!tempMonsterList.isEmpty()) {
                    int monsterIndex = random.nextInt(tempMonsterList.size());
                    returnValue.add(tempMonsterList.get(monsterIndex));
                    tempMonsterList.remove(monsterIndex);
                }
            }
        }

        return returnValue;
    }
}
