package com.example.study.programers.level1;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class Test1 {

    @Test
    public void test() {

//        assertArrayEquals(new int[]{}, solution(new int[]{5, 4, 4}));
        assertArrayEquals(new int[]{1}, solution(new int[]{1, 2, 3, 4, 5}));
        assertArrayEquals(new int[]{1, 2, 3}, solution(new int[]{1, 3, 2, 4, 2}));
        assertArrayEquals(new int[]{2}, solution(new int[]{2, 3, 2, 4, 2}));
    }

    public int[] solution(int[] answers) {

        StopWatch sw = new StopWatch();
        sw.start();

        int[] pattern1 = new int[]{1, 2, 3, 4, 5};
        int[] pattern2 = new int[]{2, 1, 2, 3, 2, 4, 2, 5};
        int[] pattern3 = new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int[] playersScore = new int[3];

        for (int i = 0; i < answers.length; i++) {
            if (pattern1[i % 5] == answers[i]) playersScore[0]++;
            if (pattern2[i % 8] == answers[i]) playersScore[1]++;
            if (pattern3[i % 10] == answers[i]) playersScore[2]++;
        }

        int max = Math.max(playersScore[0], Math.max(playersScore[1], playersScore[2]));

        List<Integer> list = new ArrayList<>();
        if(playersScore[0] == max) list.add(1);
        if(playersScore[1] == max) list.add(2);
        if(playersScore[2] == max) list.add(3);

        int[] answer = (list.size() == 0)? new int[]{} : new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        sw.stop();
        System.out.println(sw.getNanoTime());

        return answer;
    }

}
