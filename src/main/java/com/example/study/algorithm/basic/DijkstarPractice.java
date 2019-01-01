package com.example.study.algorithm.basic;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

public class DijkstarPractice {


    @Test
    public void test() {

        Map<String, Map<String, Integer>> graph = getGraph();

        assertThat(bestDistance(graph)).isEqualTo(6);

        Map<String, Map<String, Integer>> graphA = getGraphA();
        assertThat(bestDistance(graphA)).isEqualTo(8);

        Map<String, Map<String, Integer>> graphB = getGraphB();
        assertThat(bestDistance(graphB)).isEqualTo(60);
    }

    private Map<String, Map<String, Integer>> getGraphB() {

        HashMap<String, Map<String, Integer>> graph = new HashMap<>();

        //출발점
        HashMap<String, Integer> start = new HashMap<>();
        start.put("a", 10);
        graph.put("start", start);

        HashMap<String, Integer> a = new HashMap<>();
        a.put("c", 20);
        graph.put("a", a);

        HashMap<String, Integer> b = new HashMap<>();
        b.put("a", 1);
        graph.put("b", b);

        HashMap<String, Integer> c = new HashMap<>();
        c.put("b", 1);
        c.put("end", 30);
        graph.put("c", c);

        return graph;
    }

    private int bestDistance(Map<String, Map<String, Integer>> graph) {

        Map<String, Integer> distance = new HashMap<>();        //모든 경로의 가중치를 누적하는 변수
        Map<String, String> from = new HashMap<>();             //어떤 정점의 from node가 무엇인지 저장하는 변수

        //초기값 셋팅
        for(String key : graph.get("start").keySet()) {
            distance.put(key, graph.get("start").get(key));
            from.put(key, "start");
        }
        distance.put("end", Integer.MAX_VALUE);

        //방문한 노드
        Set<String> checked = new HashSet<>();

        //아직 방문하지 않은 가장 싼 정점을 찾음
        String node = findBestNode(distance, checked);
        while (node != null) {

            //현재까지 찾은 가장 싼 정점의 거리
            int curDistance = distance.get(node);

            //가장 싼 정점의 이웃을 가져온다.
            Map<String, Integer> neighbors = graph.get(node);

            if (neighbors != null) {
                //모든 이웃들에 대해서 가장 싼값을 가져온다.
                for (String key : neighbors.keySet()) {
                    int newDistance = curDistance + neighbors.get(key); //현재 node까지 이동하는데 걸렸던 비용 + 이웃과의 거리

                    if (distance.get(key) == null || distance.get(key) > newDistance) {
                        distance.put(key, newDistance);
                        from.put(key, node);
                    }
                }
            }

            checked.add(node);
            node = findBestNode(distance, checked);
        }

        return distance.get("end");
    }

    private String findBestNode(Map<String, Integer> distance, Set<String> checked) {

        int best = Integer.MAX_VALUE;
        String bestWay = null;

        for (String key : distance.keySet()) {

            if (checked.contains(key)) {
                continue;
            }

            if (best > distance.get(key)) {
                best = distance.get(key);
                bestWay = key;
            }
        }

        return bestWay;
    }

    private Map<String, Map<String, Integer>> getGraph() {

        HashMap<String, Map<String, Integer>> graph = new HashMap<>();

        //출발점
        HashMap<String, Integer> start = new HashMap<>();
        start.put("a", 6);
        start.put("b", 2);
        graph.put("start", start);

        HashMap<String, Integer> a = new HashMap<>();
        a.put("end", 1);
        graph.put("a", a);

        HashMap<String, Integer> b = new HashMap<>();
        b.put("a", 3);
        b.put("end", 5);
        graph.put("b", b);

        return graph;
    }

    private Map<String, Map<String, Integer>> getGraphA() {

        HashMap<String, Map<String, Integer>> graph = new HashMap<>();

        //출발점
        HashMap<String, Integer> start = new HashMap<>();
        start.put("a", 5);
        start.put("b", 2);
        graph.put("start", start);

        HashMap<String, Integer> a = new HashMap<>();
        a.put("c", 2);
        a.put("d", 4);
        graph.put("a", a);

        HashMap<String, Integer> b = new HashMap<>();
        b.put("a", 8);
        b.put("c", 7);
        graph.put("b", b);

        HashMap<String, Integer> c = new HashMap<>();
        c.put("end", 1);
        graph.put("c", c);

        HashMap<String, Integer> d = new HashMap<>();
        d.put("c", 6);
        d.put("end", 3);
        graph.put("d", d);

        return graph;
    }


}
