package com.example.study.algorithm.string;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import org.junit.Test;

public class StringTest {

    @Test
    public void check_unique_best() {
        assertEquals(true, isUnique(null));
        assertEquals(true, isUnique("0"));
        assertEquals(true, isUnique("a"));

        assertEquals(false, isUnique("aa"));
        assertEquals(true, isUnique("ab"));

        assertEquals(true, isUnique("abc"));
        assertEquals(false, isUnique("acc"));
        assertEquals(false, isUnique("aca"));
        assertEquals(false, isUnique("aac"));

        assertEquals(true, isUnique("abcd"));
    }

    public boolean isUnique(String word) {

        if (word == null || word.length() < 2) {
            return true;
        }

        boolean[] charExist = new boolean[256];

        for (int i = 0; i < word.length(); i++) {

            char uniWord = word.charAt(i);
            if (charExist[uniWord]) {
                return false;
            }
            charExist[uniWord] = true;
        }

        return true;
    }

    @Test
    public void check_unique_normal() {
        assertEquals(true, isUnique_normal(null));
        assertEquals(true, isUnique_normal("0"));
        assertEquals(true, isUnique_normal("a"));

        assertEquals(false, isUnique_normal("aa"));
        assertEquals(true, isUnique_normal("ab"));

        assertEquals(true, isUnique_normal("abc"));
        assertEquals(false, isUnique_normal("acc"));
        assertEquals(false, isUnique_normal("aca"));
        assertEquals(false, isUnique_normal("aac"));

        assertEquals(true, isUnique_normal("abcd"));
    }

    private boolean isUnique_normal(String word) {

        if (word == null || word.length() < 2) {
            return true;
        }

        char[] charsOfWord = word.toCharArray();
        Arrays.sort(charsOfWord);

        for (int i = 0; i < charsOfWord.length; i++) {

            char firstChar = charsOfWord[i];
            for (int j = i + 1; j < charsOfWord.length; j++) {
                if (firstChar == charsOfWord[j]) {
                    return false;
                }
            }
        }

        return true;
    }

    @Test
    public void check_unique_normal_better() {
//        assertEquals(true, isUnique_normal_better(null));
//        assertEquals(true, isUnique_normal_better("0"));
//        assertEquals(true, isUnique_normal_better("a"));

        //assertEquals(false, isUnique_normal_better("aa"));
//        assertEquals(true, isUnique_normal_better("ab"));
//
//        assertEquals(true, isUnique_normal_better("abc"));
        assertEquals(false, isUnique_normal_better("acc"));
        assertEquals(false, isUnique_normal_better("aca"));
        assertEquals(false, isUnique_normal_better("aac"));

        assertEquals(true, isUnique_normal_better("abcd"));
    }

    private boolean isUnique_normal_better(String word) {

        if (word == null || word.length() < 2) {
            return true;
        }

        while (word.length() > 0) {

            String checkWord = word.substring(0, 1);
            word = word.substring(1, word.length());

            if (word.indexOf(checkWord) > -1) {
                return false;
            }
        }

        return true;
    }

}
