package com.example.algorithm.basic;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class Dijkstra {

  @Test
  public void test() {

    //경로 해시테이블 - 각 node의 이웃을 저장한다.
    Map<String, Map> graph = new HashMap<>();
    makeGraph(graph);

    //각 정점의 비용을 저장하는 해시테이블
    Map<String, Map> costs = new HashMap<>();
    costs.put();
  }

  private void makeGraph(Map<String, Map> graph) {

    //start의 이웃 - "A", "B"
    Map<String, Integer> start = new HashMap<>();
    start.put("alice", 6);
    start.put("bob", 2);

    graph.put("start", start);

    //A의 이웃 - "end"
    Map<String, Integer> A = new HashMap<>();
    A.put("end", 1);

    graph.put("A", A);

    //B의 이웃 - "A", "end"
    Map<String, Integer> B = new HashMap<>();
    B.put("A", 3);
    B.put("end", 5);

    graph.put("B", B);

    //도착점 - 이웃이 없다.
    graph.put("end", null);
  }


}
