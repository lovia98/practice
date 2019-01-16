package com.example.study.algorithm.string;

import static org.assertj.core.api.Java6Assertions.assertThat;

import org.junit.Test;

public class StringTest2 {

    @Test
    public void test() {
        assertThat(isSubstring(null, "")).isEqualTo(false);
        assertThat(isSubstring("", "")).isEqualTo(false);
        assertThat(isSubstring("water", "erwat")).isEqualTo(true);
        assertThat(isSubstring("waterbottle", "erbottlewat")).isEqualTo(true);
    }

    private boolean isSubstring(String word1, String word2) {

        if (word1 == null || word2 == null || word2.length() == 0) {
            return false;
        }

        if (word1.equals(word2)) {
            return true;
        }

        String lotation = word2;
        for (int i = 0; i < lotation.length(); i++) {
            lotation = lotation.substring(1, lotation.length()) + lotation.substring(0, 1);

            if(lotation.equals(word1))
                return true;
        }

        return false;
    }
}
