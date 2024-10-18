package com.battle;

public class Platoon {
    private String className;
    private int soldiers;

    public Platoon(String className, int soldiers) {
        this.className = className;
        this.soldiers = soldiers;
    }

    public String getClassName() {
        return className;
    }

    public int getSoldiers() {
        return soldiers;
    }

    @Override
    public String toString() {
        return className + "#" + soldiers;
    }
}
