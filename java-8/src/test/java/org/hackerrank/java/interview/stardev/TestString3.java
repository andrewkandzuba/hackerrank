package org.hackerrank.java.interview.stardev;

import org.junit.Assert;
import org.junit.Test;

import static org.hackerrank.java.interview.stardev.String3.*;

public class TestString3 {

    @Test
    public void testCountYZ() throws Exception {
        Assert.assertTrue(countYZ("fez day") == 2);
        Assert.assertTrue(countYZ("day fez") == 2);
        Assert.assertTrue(countYZ("day fyyyz") == 2);
        Assert.assertTrue(countYZ("aaa bbb") == 0);
        Assert.assertTrue(countYZ("zaaa ybbb") == 0);
        Assert.assertTrue(countYZ("zaaa ybbby") == 1);
        Assert.assertTrue(countYZ("zaaa ybbby**") == 1);
        Assert.assertTrue(countYZ("-z   ybbby**") == 2);
        Assert.assertTrue(countYZ("-z   y_y   ]bbby") == 4);

        Assert.assertTrue(countYZ("day:yak") == 1);
        Assert.assertTrue(countYZ("!!day--yaz!!") == 2);
        Assert.assertTrue(countYZ("DAY abc XYZ") == 2);
        Assert.assertTrue(countYZ("y2bz") == 2);
        Assert.assertTrue(countYZ("zxyx") == 0);

        Assert.assertTrue(countYZ("fez day") == 2);
        Assert.assertTrue(countYZ("day fez") == 2);
        Assert.assertTrue(countYZ("day fyyyz") == 2);
    }

    @Test
    public void testWithoutString() throws Exception {
        Assert.assertEquals(withoutString("Hello there", "llo"), "He there");
        Assert.assertEquals(withoutString("Hello there", "e"), "Hllo thr");
        Assert.assertEquals(withoutString("Hello there", "x"), "Hello there");
        Assert.assertEquals(withoutString("THIS is a FISH", "is"), "TH a FH");
        Assert.assertEquals(withoutString("THIS is a FISH", "iS"), "TH a FH");
        Assert.assertEquals(withoutString("xyzzy", "Y"), "xzz");
    }

    @Test
    public void testGHappy() throws Exception {
        Assert.assertTrue(gHappy("xxggxx"));
        Assert.assertFalse(gHappy("xxgxx"));
        Assert.assertFalse(gHappy("xxggyygxx"));
        Assert.assertTrue(gHappy(""));
        Assert.assertFalse(gHappy("g"));
        Assert.assertTrue(gHappy("gg"));
        Assert.assertFalse(gHappy("xg "));
        Assert.assertFalse(gHappy("xg g"));
    }


    @Test
    public void testCountTriple() throws Exception {
        Assert.assertEquals(countTriple("abcXXXabc"), 1);
        Assert.assertEquals(countTriple("xxxabyyyycd"), 3);
        Assert.assertEquals(countTriple("a"), 0);
    }

    @Test
    public void testMirrorEnds() throws Exception {
        Assert.assertEquals(mirrorEnds("abXYZba"), "ab");
        Assert.assertEquals(mirrorEnds("abca"), "a");
        Assert.assertEquals(mirrorEnds("aba"), "aba");
        Assert.assertEquals(mirrorEnds("a"), "a");
        Assert.assertEquals(mirrorEnds("ag"), "");
        Assert.assertEquals(mirrorEnds(""), "");
    }
}
