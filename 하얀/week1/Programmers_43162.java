package week1;

import java.util.LinkedList;
import java.util.Queue;

public class Programmers_43162 {
    private static int n; // 컴퓨터의 개수
    private static int[][] computers;
    private static boolean[] isVisited;
    private static int answer = 0;

    public static void main(String[] args) {
        isVisited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) {
                bfs(i);
                answer++;
            }
        }

        System.out.println(answer);
    }

    static void bfs(int start) {
        isVisited[start] = true;
        Queue<Integer> qu = new LinkedList<>();
        qu.add(start);

        while (!qu.isEmpty()) {
            start = qu.poll();
            for (int i = 0; i < n; i++) {
                if (!isVisited[i] && computers[start][i] == 1) {
                    isVisited[i] = true;
                    qu.add(i);
                }
            }
        }
    }
}
