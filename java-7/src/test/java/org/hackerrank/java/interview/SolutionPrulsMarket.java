package org.hackerrank.java.interview;

import java.io.IOException;
import java.util.*;

class Profit {
    private int income;
    private int amount;

    public Profit(int income, int amount) {
        this.income = income;
        this.amount = amount;
    }

    public int getIncome() {
        return income;
    }

    public int getAmount() {
        return amount;
    }
}

public class SolutionPrulsMarket {

    static final int priceToSell = 10;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        // set the pricing


        // fill the market with plur
        int z = Integer.parseInt(in.nextLine());
        while (z > 0){

            int maxProfit = 0;
            int workyards = z;
            List<List<Profit>> piles = new ArrayList<>(z);

            while (z-- > 0) {
                // add piles
                int e = in.nextInt();
                int currProfit = 0;
                int maxProfitPerPile = 0;
                List<Profit> profits = new ArrayList<>();
                int currPluls = 1;
                while (e-- > 0) {
                    // calculate profit and amount per pile
                    int priceToBuy = in.nextInt();
                    currProfit += (priceToSell - priceToBuy);
                    if (currProfit > maxProfitPerPile) {
                        profits.clear();
                        profits.add(new Profit(currProfit, currPluls));
                        maxProfitPerPile = currProfit;
                    } else if (currProfit == maxProfitPerPile) {
                        profits.add(new Profit(currProfit, currPluls));
                        maxProfitPerPile = currProfit;
                    }
                    currPluls++;
                }
                if (profits.size() > 0) {
                    maxProfit += profits.get(0).getIncome();
                }
                piles.add(profits);
            }

            // market is opened: now find best buy options
            report(piles, maxProfit, workyards);
            z = in.nextInt();
        }

    }

    private static void report(List<List<Profit>> piles, int maxProfit, int workyards) {
        List<Profit> profits = piles.get(0);
        Set<Integer> boxes = new TreeSet<>();
        for(Profit p : profits){
            boxes.add(p.getAmount());
        }
        boxes = shippingOptions(piles, 1, boxes);
        System.out.printf("Workyards %d%n", workyards);
        System.out.printf("Maximum profit is %d%n", maxProfit);
        StringBuilder sb = new StringBuilder("Number of pruls to buy: ");
        for(Integer box : boxes){
            sb.append(box).append(" ");
        }
        System.out.println(sb.toString());
    }

    private static Set<Integer> shippingOptions(List<List<Profit>> piles, int pileIndex, Set<Integer> boxes) {
        if (piles.size() == pileIndex) {
            return boxes;
        }
        Set<Integer> newBoxes = new TreeSet<>();
        for (Profit p : piles.get(pileIndex)) {
            for(Integer box : boxes){
                newBoxes.add(box + p.getAmount());
            }
        }
        return shippingOptions(piles, ++pileIndex, newBoxes);
    }
}
