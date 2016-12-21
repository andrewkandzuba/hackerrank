package org.hackerrank.java.interview.pl.logic;


public class Position {
    public enum AssetType {
         EQUITY,
         CORPORATE_BOND,
         GOVERNMENT_BOND
    }

    private final int accountId,positionId;
    private final double lmv, smv;
    private final AssetType assetType;

    public Position(int accountId, int positionId, double lmv, double smv, AssetType assetType) {
        this.accountId = accountId;
        this.positionId = positionId;
        this.lmv = lmv;
        this.smv = smv;
        this.assetType = assetType;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getPositionId() {
        return positionId;
    }

    public double getLmv() {
        return lmv;
    }

    public double getSmv() {
        return smv;
    }

    public AssetType getAssetType() {
        return assetType;
    }
}
