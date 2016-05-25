package org.hackerrank.java.interview.stardev;

public class String3 {

    public static int countYZ(String str) {
        return countYZ(0, str.toCharArray(), 0);
    }

    private static int countYZ(int start, char[] str, int counter) {
        if (start >= str.length) {
            return counter;
        }
        if (str[start] == 'y' || str[start] == 'z' || str[start] == 'Y' || str[start] == 'Z') {
            if ((start + 1 < str.length && (!Character.isLetter(str[start + 1]) || Character.isWhitespace(str[start + 1]))) || start + 1 == str.length) {
                return countYZ(start + 1, str, counter + 1);
            }
        }
        return countYZ(start + 1, str, counter);
    }


    public static String withoutString(String base, String remove) {
        return withoutString(new StringBuilder(), base, remove);
    }

    public static String withoutString(StringBuilder sb, String base, String remove) {
        if (base.length() == 0) {
            return sb.toString();
        }
        if (base.toLowerCase().startsWith(remove.toLowerCase())) {
            if (sb.toString().endsWith(" ") && base.substring(remove.length()).startsWith(" ")) {
                return withoutString(sb, base.substring(remove.length() + 1), remove);
            } else {
                return withoutString(sb, base.substring(remove.length()), remove);
            }
        }
        return withoutString(sb.append(base.charAt(0)), base.substring(1), remove);
    }

    public static boolean equalIsNot(String str) {
        return equalIsNot(str, 0, 0);
    }

    public static boolean equalIsNot(String str, int countIs, int countNot) {
        if (str.length() == 0) {
            return countIs == countNot;
        }
        if (str.startsWith("is")) {
            return equalIsNot(str.substring(1), countIs + 1, countNot);
        }
        if (str.startsWith("not")) {
            return equalIsNot(str.substring(1), countIs, countNot + 1);
        }
        return equalIsNot(str.substring(1), countIs, countNot);
    }

    public static boolean gHappy(String str) {
        return gHappy(str, true);
    }

    private static boolean gHappy(String str, boolean isHappy) {
        if (str.length() == 0) {
            return isHappy;
        }

        if (str.startsWith("g")) {
            int i = 1;
            while (i < str.length() && str.charAt(i) == 'g') {
                i++;
            }
            if (i > 1) {
                return gHappy(str.substring(i), true);
            } else {
                return gHappy(str.substring(i), false);
            }
        }
        return gHappy(str.substring(1), isHappy);
    }

    public static int countTriple(String str) {
        if (str.length() == 0) {
            return 0;
        }
        if (str.length() >= 3 && str.charAt(0) == str.charAt(1) && str.charAt(1) == str.charAt(2)) {
            return 1 + countTriple(str.substring(1));
        }
        return countTriple(str.substring(1));
    }

    public static String mirrorEnds(String string) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < string.length() && string.charAt(i) == string.charAt(string.length() - i - 1); i++) {
            sb.append(string.charAt(i));
        }
        return sb.toString();
    }


}
