package com.battle;

import java.util.*;

public class BattleStrategy {

    //  method to find a winning strategy
    public static String findStrategy(List<Platoon> ownPlatoons, List<Platoon> opponentPlatoons) {
        List<Platoon> bestArrangement = null;

        // Generate all permutations of your platoons
        List<List<Platoon>> permutations = generatePermutations(ownPlatoons);
        for (List<Platoon> arrangement : permutations) {
            int wins = 0;

            // Simulate all 5 battles
            for (int i = 0; i < 5; i++) {
                Platoon own = arrangement.get(i);
                Platoon opponent = opponentPlatoons.get(i);
                int result = UnitAdvantages.determineBattle(own, opponent);
                if (result == 1) wins++; // Count wins
            }

            // If at least 3 wins, return this arrangement
            if (wins >= 3) {
                bestArrangement = arrangement;
                break;
            }
        }

        // No winning arrangement found
        if (bestArrangement == null) {
            return "There is no chance of winning";
        }

        // Format the winning arrangement as output
        StringBuilder sb = new StringBuilder();
        for (Platoon p : bestArrangement) {
            sb.append(p.toString()).append(";");
        }
        return sb.toString();
    }

    // Helper function to generate all permutations of a list
    private static List<List<Platoon>> generatePermutations(List<Platoon> list) {
        if (list.isEmpty()) {
            List<List<Platoon>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        List<Platoon> listCopy = new ArrayList<>(list);
        Platoon first = listCopy.remove(0);
        List<List<Platoon>> result = new ArrayList<>();
        List<List<Platoon>> permutations = generatePermutations(listCopy);

        for (List<Platoon> smallerPermutated : permutations) {
            for (int index = 0; index <= smallerPermutated.size(); index++) {
                List<Platoon> temp = new ArrayList<>(smallerPermutated);
                temp.add(index, first);
                result.add(temp);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        // Input your platoons
        List<Platoon> ownPlatoons = Arrays.asList(
                new Platoon("Spearmen", 10),
                new Platoon("Militia", 30),
                new Platoon("FootArcher", 20),
                new Platoon("LightCavalry", 1000),
                new Platoon("HeavyCavalry", 120)
        );

        // Input opponent's platoons
        List<Platoon> opponentPlatoons = Arrays.asList(
                new Platoon("Militia", 10),
                new Platoon("Spearmen", 10),
                new Platoon("FootArcher", 1000),
                new Platoon("LightCavalry", 120),
                new Platoon("CavalryArcher", 100)
        );

        // Find and print the winning strategy
        System.out.println(findStrategy(ownPlatoons, opponentPlatoons));
    }
}
