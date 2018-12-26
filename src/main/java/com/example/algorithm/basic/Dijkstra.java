package com.example.algorithm.basic;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder.In;
import org.junit.Test;

/*
 * 다익스트라 알고리즘
 *
 * //http://bumbums.tistory.com/4
 */
public class Dijkstra {


    @Test
    public void test() {

        int n = 8;
        Graph graph = makeGraph(n);

        int shortest = searchShortest(graph);
    }

    private int searchShortest(Graph graph) {

        int n = graph.getN();                   //마지막으로 방문해야 할 노드 번호
        int[][] maps = graph.getMaps();

        int best = Integer.MAX_VALUE;           //최단거리

        //시작점 부터 현재까지의 총 거리
        int[][] from = new int[n + 1][n + 1];
        boolean[] check = new boolean[n + 1];   //방문했는지 체크

        //시작노드값 초기화
        int start = 1;
        check[start] = true;

        //전체 노드를 방문 했을때 까지 최단 기간 계산
        while (!check[n]) {

            for (int i = 1; i < n + 1; i++) {
                //모든 경로의 총 경로 저장
                if(!check[i] && maps[start][i] != 0) {
                    from[start][i] = maps[start][i];
                }
            }

            break;
        }

        return 0;
    }


    private Graph makeGraph(int n) {

        Graph graph = new Graph(n);

        graph.input(1, 2, 3);
        graph.input(1, 4, 4);
        graph.input(1, 5, 5);
        graph.input(2, 3, 2);
        graph.input(3, 4, 1);
        graph.input(3, 8, 3);
        graph.input(4, 5, 2);
        graph.input(4, 7, 6);
        graph.input(5, 6, 4);
        graph.input(6, 7, 3);
        graph.input(6, 8, 2);

        return graph;

    }

    class Graph {

        private int n;        //노드들의 수
        private int maps[][]; //노드들간의 가중치

        public Graph(int n) {
            this.n = n;
            maps = new int[n + 1][n + 1];
        }

        public void input(int i, int j, int w) {
            maps[i][j] = w;
            maps[j][i] = w;
        }

        public int getN() {
            return n;
        }

        public int[][] getMaps() {
            return maps;
        }
    }

}
