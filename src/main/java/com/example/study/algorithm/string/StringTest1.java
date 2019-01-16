package com.example.study.algorithm.string;

import static org.assertj.core.api.Java6Assertions.assertThat;

import org.junit.Assert;
import org.junit.Test;

public class StringTest1 {

    @Test
    public void reverse_test() {
        Assert.assertEquals(null, reverse(null));
        Assert.assertEquals("", reverse(""));
        Assert.assertEquals("ba", reverse("ab"));
        Assert.assertEquals("bab", reverse("bab"));
        Assert.assertEquals("aabb", reverse("bbaa"));
    }

    private String reverse(String word) {

        if (word == null || word.length() == 1) {
            return word;
        }

        StringBuilder reverse = new StringBuilder();

        for (int point = word.length() - 1; point > -1; point--) {
            String endChar = word.substring(point, point + 1);
            reverse.append(endChar);
        }

        return reverse.toString();
    }

    @Test
    public void replace_space() {
        assertThat(replaceSpace(null, 13)).isEqualTo(null);
        assertThat(replaceSpace("", 13)).isEqualTo("");
        assertThat(replaceSpace(" ", 13)).isEqualTo("%20");
        assertThat(replaceSpace("  ", 13)).isEqualTo("%20%20");
        assertThat(replaceSpace("   ", 13)).isEqualTo("%20%20%20");
        assertThat(replaceSpace("  a b", 13)).isEqualTo("%20%20a%20b");
        assertThat(replaceSpace("a  b c", 13)).isEqualTo("a%20%20b%20c");
        assertThat(replaceSpace("a  b c c", 5)).isEqualTo("a%20%20b%20");
    }

    private String replaceSpace(String word, int showLength) {

        if (word == null || word == "") {
            return word;
        }

        StringBuilder replace = new StringBuilder();
        int appendCount = 0;
        String replaceWord = "";

        for (int i = 0; i < word.length(); i++) {

            if (appendCount == showLength) {
                break;
            }

            if (word.charAt(i) == ' ') {
                replaceWord = "%20";
            } else {
                replaceWord = word.substring(i, i + 1);
            }

            replace.append(replaceWord);
            appendCount++;
        }

        return replace.toString();
    }

    @Test
    public void zip_string_test() {
        assertThat(zipString(null)).isEqualTo(null);
        assertThat(zipString("")).isEqualTo("");
        assertThat(zipString("a")).isEqualTo("a");
        assertThat(zipString("  ")).isEqualTo("  ");
        assertThat(zipString("aa")).isEqualTo("aa");
        assertThat(zipString("aa ")).isEqualTo("aa ");
        assertThat(zipString("aabbba")).isEqualTo("aabbba");
        assertThat(zipString("aabccccccccaaa")).isEqualTo("a2b1c8a3");
        assertThat(zipString("aabcccccddd")).isEqualTo("a2b1c5d3");
        assertThat(zipString("a-b-33333")).isEqualTo("a-b-33333");
    }

    private String zipString(String word) {

        if (word == null || word.length() == 0) {
            return word;
        }

        String before = null;
        int wordCount = 0;

        String wordKey;
        StringBuilder wordToZip = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {

            wordKey = word.substring(i, i + 1);

            if(!wordKey.equals(before)) {
                if(before != null)
                    wordToZip.append(wordCount);

                wordToZip.append(wordKey);
                wordCount = 1;

            } else {
                wordCount++;
            }

            before = wordKey;
        }

        if(wordCount > 0)
            wordToZip.append(wordCount);


        return (wordToZip.length() < word.length()) ? wordToZip.toString() : word;
    }
}
