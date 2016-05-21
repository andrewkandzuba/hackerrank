package org.hackerrank.java.interview.pl.logic;

import java.util.stream.Stream;

public final class PositionsAggregator {
    private static final int NAV = 375;

    public static boolean containEquity10(Stream<Position> stream) {
        double coefficient = stream
                .filter(p -> p.getAssetType().equals(Position.AssetType.EQUITY))
                .mapToDouble(p -> (p.getLmv() + p.getSmv()) / 2).sum();
        return coefficient / NAV > 0.01;
    }

    public static boolean containBond15to25(Stream<Position> stream){
        double coefficient = stream
                .filter(p -> p.getAssetType().equals(Position.AssetType.CORPORATE_BOND)
                        || p.getAssetType().equals(Position.AssetType.GOVERNMENT_BOND))
                .mapToDouble(p -> (p.getLmv())).sum();
        double measured = coefficient / NAV;
        return measured > 0.15 && measured < 0.25;
    }

    //b)	( SUM(LMV) where Asset Type In ("CORPORATE BOND","GOVERNMENT BOND") /NAV ) IS BETWEEN 15% AND 25%
}
