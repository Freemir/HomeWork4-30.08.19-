package com.company;

import java.util.Random;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_GREY = "\u001B[37m";


    public static int[] health = {800, 250, 250, 250, 250};
    public static int[] damage = {50, 20, 20, 20, 0};
    public static int heal = 25;
    public static String[] bossDebilityType = {"Random", "Physical", "Magical", "Mental", "Haven't"};
    public static String[] players = {"Boss", "Warrior", "Wizard", "Mental", "Healer"};

    public static void main(String[] args) {
        while (!isFinish()) {
            bossDebilityType[0] = generateBossDebilityType();
            round();
        }
    }

    public static void round() {

        System.out.println("__________________StartRound__________________");
        printStatistic();

        if (health[0] > 0) {
            System.out.println(ANSI_YELLOW + "__________________BossDamageStatus__________________");
            for (int i = 1; i < players.length; i++) {
                health[i] = bossHit(i);
            }
            System.out.println("____________________________________________________" + ANSI_RESET);
        }

        if (health[1]>0||health[2]>0||health[3]>0||health[4]>0) {
            System.out.println(ANSI_BLUE + "__________________PlayersDamageStatus__________________");
            for (int i = 1; i < players.length; i++) {
                health[0] = playersHit(i);
            }
            System.out.println("______________________________________________________" + ANSI_RESET);
        }
        if (health[4] > 0) {
            System.out.println(ANSI_WHITE + "__________________HillStatus__________________");
            healingHealer();
            System.out.println("______________________________________________" + ANSI_RESET);
        }

        printStatistic();
        System.out.println("__________________EndRound__________________");
        System.out.println();

    }

    public static int bossHit(int playerIndex) {
        System.out.println("Boss damage " + players[playerIndex] + " " + damage[0]);
        return health[playerIndex] - damage[0];
    }

    public static int healingHealer() {
        for (int i = 1; i <= 3; i++) {
            health[i] += heal;
            System.out.println("Healer hill " + players[i] + " " + heal);
        }
        int i = 0;
        return health[i];
    }

    public static int playersHit(int playerIndex) {

        if (health[0]<=damage[playerIndex]){
            return health[0]=0;
        }

        Random r = new Random();
        int randomNumber = r.nextInt(8) + 2;
        if (bossDebilityType[0].equals(bossDebilityType[playerIndex])) {
            System.out.println(ANSI_CYAN + bossDebilityType[playerIndex] + " CRITICAL DAMAGE " + damage[playerIndex] * randomNumber + ANSI_RESET);
            return health[0] - damage[playerIndex] * randomNumber;
        }
        System.out.println(ANSI_BLUE + players[playerIndex] + " damage Boss " + damage[playerIndex]);
        return health[0] - damage[playerIndex];
    }

    public static String generateBossDebilityType() {
        Random r = new Random();
        int randomNumber = r.nextInt(3) + 1;
        return bossDebilityType[randomNumber];
    }

    public static void printStatistic() {
        System.out.println();
        System.out.println();
        System.out.println(ANSI_RED + "__________________StatisticHealth__________________");
        if (health[0]<= 0){
            System.out.println("Boss die");
        }else {
            System.out.println("Boss health " + health[0]);
        }
        if (health[1]<=0) {
            System.out.println("Warrior die");
        }else {
            System.out.println("Warrior health " + health[1]);
        }
        if (health[2]<=0){
            System.out.println("Magic die");
        }else {
            System.out.println("Magic health " + health[2]);
        }
        if (health[3]<=0){
            System.out.println("Kinetic die");
        }else {
            System.out.println("Kinetic health " + health[3]);
        }
        if (health[4]<=0){
            System.out.println("Healer die");
        }else {
            System.out.println("Healer health " + health[4]);
        }
        System.out.println("___________________________________________________" + ANSI_RESET);
        System.out.println();
        System.out.println();
    }

    public static boolean isFinish() {
        if (health[0] <= 0) {
            System.out.println("__________________Heroes won!!!__________________");
            return true;
        }
        if (health[1] <= 0 && health[2] <= 0 && health[3] <= 0 && health[4] <= 0) {
            System.out.println("__________________Boss won!!!__________________");
            return true;
        }

        return false;
    }
}