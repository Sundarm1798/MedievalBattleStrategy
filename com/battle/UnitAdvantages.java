package com.battle;

import java.util.*;

public class UnitAdvantages {

    // Static map of unit advantages
    private static final Map<String, List<String>> advantages = new HashMap<>();

    static {
        advantages.put("Militia", Arrays.asList("Spearmen", "LightCavalry"));
        advantages.put("Spearmen", Arrays.asList("LightCavalry", "HeavyCavalry"));
        advantages.put("LightCavalry", Arrays.asList("FootArcher", "CavalryArcher"));
        advantages.put("HeavyCavalry", Arrays.asList("Militia", "FootArcher", "LightCavalry"));
        advantages.put("CavalryArcher", Arrays.asList("Spearmen", "HeavyCavalry"));
        advantages.put("FootArcher", Arrays.asList("Militia", "CavalryArcher"));
    }

    // Method to get the list of advantages for a given unit
    public static List<String> getAdvantages(String unitClass) {
        return advantages.getOrDefault(unitClass, Collections.emptyList());
    }

    // Determine the result of a battle between two platoons
    public static int determineBattle(Platoon own, Platoon opponent) {
        String ownClass = own.getClassName();
        String opponentClass = opponent.getClassName();
        int ownSoldiers = own.getSoldiers();
        int opponentSoldiers = opponent.getSoldiers();

        if (getAdvantages(ownClass).contains(opponentClass)) {
            // Own unit has advantage: 2-to-1 ratio
            if (ownSoldiers * 2 > opponentSoldiers) return 1; // Win
            if (ownSoldiers * 2 == opponentSoldiers) return 0; // Draw
        } else {
            // Standard 1-to-1 comparison
            if (ownSoldiers > opponentSoldiers) return 1; // Win
            if (ownSoldiers == opponentSoldiers) return 0; // Draw
        }
        return -1; // Loss
    }
}
