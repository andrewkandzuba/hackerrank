package org.hackerrank.java.interview.stardev;

public class Interview {

    public static String stringSplosion(String str) {
        StringBuilder sb = new StringBuilder();
        String last = null;
        for (int i = 0; i < str.length(); i++) {
            if (last != null) {
                last += str.charAt(i);
            } else {
                last = "" + str.charAt(i);
            }
            sb.append(last);
        }
        return sb.toString();
    }

    public static int maxMirror(int[] nums) {
        return findMirrorOfLength(nums.length, nums);
    }

    private static int findMirrorOfLength(int groupLength, int nums[]) {
        if (groupLength == 0) {
            return 0;
        }
        if (findMirrow(nums, groupLength)) {
            return groupLength;
        }
        return findMirrorOfLength(groupLength - 1, nums);
    }

    private static boolean findMirrow(int nums[], int gl) {
        for(int gs = 0; gs <= nums.length - gl; gs++){
            for(int cp = 0; cp <= nums.length - gl; cp++){
                if (isMirrorOf(cp, nums, gs, gl)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isMirrorOf(int cp, int nums[], int gs, int gl) {
        for (int i = 0; i < gl; i++) {
            if (nums[cp + i] != nums[gs + gl - i - 1]) {
                return false;
            }
        }
        return true;
    }
}
