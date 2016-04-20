package org.hackerrank.java.interview;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution1 {

    static int maxProfit(int cost_per_cut, int metal_price, int[] lengths) {
        Arrays.sort(lengths);
        int longestPodLength = lengths[lengths.length - 1];
        int maxProfitFromRods = 0;
        for(int cutRodLength = 1; cutRodLength < longestPodLength; cutRodLength++){
            int totalRods = 0;
            int totalCuts = 0;
            for(int length : lengths){
                totalRods += (length / cutRodLength);
                totalCuts += ((length - 1) / cutRodLength);
            }
            int totalProfit = totalRods * cutRodLength * metal_price - totalCuts * cost_per_cut;
            maxProfitFromRods = Math.max(maxProfitFromRods, totalProfit);
        }
        return maxProfitFromRods;
    }


    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        int res;
        int _cost_per_cut;
        _cost_per_cut = Integer.parseInt(in.nextLine());

        int _metal_price;
        _metal_price = Integer.parseInt(in.nextLine());


        int _lengths_size = Integer.parseInt(in.nextLine());
        int[] _lengths = new int[_lengths_size];
        int _lengths_item;
        for(int _lengths_i = 0; _lengths_i < _lengths_size; _lengths_i++) {
            _lengths_item = Integer.parseInt(in.nextLine());
            _lengths[_lengths_i] = _lengths_item;
        }

        res = maxProfit(_cost_per_cut, _metal_price, _lengths);
        System.out.println(res);
    }
}
