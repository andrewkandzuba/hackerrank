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
        int maxLength = 0;
        for (int i = 1; i < nums.length; i++) {
            if (findMirrorOfLength(i, nums)) {
                maxLength = i;
            }
        }
        return maxLength;
    }

    private static boolean findMirrorOfLength(int groupLength, int num[]) {
         boolean isFound = false;
         for(int i = 0; i < num.length - groupLength + 1; i++){
              isFound |=  findMirrow(0, num, i, groupLength);
         }
        return isFound;
    }

    private static boolean findMirrow(int start, int num[], int groupStart, int groupLength){
        if(start + groupLength >= num.length || groupStart >= num.length){
            return true;
        }

        boolean isFound = true;
        for(int i = groupLength; i > 0; i--){
            if(num[start + groupLength - i] != num[groupStart + i - 1]){
                isFound = false;
            }
        }
        return isFound || findMirrow(start + 1, num, groupStart, groupLength);
    }
}
