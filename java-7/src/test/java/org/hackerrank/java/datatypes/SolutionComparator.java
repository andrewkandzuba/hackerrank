package org.hackerrank.java.datatypes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Player {
    String name;
    int score;

    Player(String name, int score) {
        this.name = name;
        this.score = score;
    }
}

class Checker implements Comparator<Player> {

    @Override
    public int compare(Player p1, Player p2) {
        int compare = (p2.score - p1.score);
        if(compare == 0){
            compare = p1.name.compareTo(p2.name);
        }
        return compare;
    }
}

public class SolutionComparator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        Player player[] = new Player[n];
        Checker checker = new Checker();

        for (int i = 0; i < n; i++) {
            player[i] = new Player(scan.next(), scan.nextInt());
        }
        scan.close();

        Arrays.sort(player, checker);
        for (Player aPlayer : player) {
            System.out.printf("%s %s\n", aPlayer.name, aPlayer.score);
        }
    }
}
