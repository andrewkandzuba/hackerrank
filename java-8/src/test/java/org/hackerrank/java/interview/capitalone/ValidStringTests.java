package org.hackerrank.java.interview.capitalone;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ValidStringTests {

    private final Solution solution = new Solution();

    @Test
    public void testValidString() {
        Assert.assertEquals("NO", solution.isValid("aabbcd"));
        Assert.assertEquals("YES", solution.isValid("abcdefghhgfedecba"));
        Assert.assertEquals("YES", solution.isValid("ibfdgaeadiaefgbhbdghhhbgdfgeiccbiehhfcggchgghadhdhagfbahhddgghbdehidbibaeaagaeeigffcebfbaieggabcfbiiedcabfihchdfabifahcbhagccbdfifhghcadfiadeeaheeddddiecaicbgigccageicehfdhdgafaddhffadigfhhcaedcedecafeacbdacgfgfeeibgaiffdehigebhhehiaahfidibccdcdagifgaihacihadecgifihbebffebdfbchbgigeccahgihbcbcaggebaaafgfedbfgagfediddghdgbgehhhifhgcedechahidcbchebheihaadbbbiaiccededchdagfhccfdefigfibifabeiaccghcegfbcghaefifbachebaacbhbfgfddeceababbacgffbagidebeadfihaefefegbghgddbbgddeehgfbhafbccidebgehifafgbghafacgfdccgifdcbbbidfifhdaibgigebigaedeaaiadegfefbhacgddhchgcbgcaeaieiegiffchbgbebgbehbbfcebciiagacaiechdigbgbghefcahgbhfibhedaeeiffebdiabcifgccdefabccdghehfibfiifdaicfedagahhdcbhbicdgibgcedieihcichadgchgbdcdagaihebbabhibcihicadgadfcihdheefbhffiageddhgahaidfdhhdbgciiaciegchiiebfbcbhaeagccfhbfhaddagnfieihghfbaggiffbbfbecgaiiidccdceadbbdfgigibgcgchafccdchgifdeieicbaididhfcfdedbhaadedfageigfdehgcdaecaebebebfcieaecfagfdieaefdiedbcadchabhebgehiidfcgahcdhcdhgchhiiheffiifeegcfdgbdeffhgeghdfhbfbifgidcafbfcd"));
    }

    class Solution {
        public String isValid(String s) {
            // Write your code here
            int[] alphabet = new int[26];
            for (byte b : s.getBytes()) {
                alphabet[b - 'a']++;
            }
            Arrays.sort(alphabet);

            int i = 0;
            for(;i<alphabet.length; i++){
                if(alphabet[i] != 0) break;
            }

            if( i+1 == alphabet.length ) return "YES";

            int first = alphabet[i];
            int second = alphabet[i+1];
            int secondLast = alphabet[alphabet.length - 2];
            int last = alphabet[alphabet.length - 1];

            // If first and last are same, then all frequencies are same
            if (first == last) return "YES";

            // If first is 1, and all other characters have 1 frequency
            if (first == 1 && second == last) return "YES";

            // If all are same and last character has just 1 extra count
            if (first == second && second == secondLast && secondLast == (last - 1)) return "YES";


            return "NO";
        }
    }
}
