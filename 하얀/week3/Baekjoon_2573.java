package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_2573 {
    private static int row, column;
    private static int[][] map;
    private static final int[] dx = {0, 1, 0, -1};
    public static final int[] dy = {1, 0, -1, 0};
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        column = Integer.parseInt(st.nextToken());

        map = new int[row][column];
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < column; k++) {
                map[i][k] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void output() {
        System.out.println(answer);
    }

    private static void solve() {
        while (true) {
            int bundle = countBundle();

            if (bundle >= 2) {
                break;
            }

            if (bundle == 0) {
                answer = 0;
                break;
            }

            meltIce();
            answer++;
        }
    }

    private static int countBundle() {
        boolean[][] isVisited = new boolean[row][column];

        int bundle = 0;
        for (int i = 0; i < row; i++) {
            for (int k = 0; k < column; k++) {
                if (map[i][k] < 0) map[i][k] = 0;
                if (map[i][k] > 0 && !isVisited[i][k]) {
                    dfs(i, k, isVisited);
                    bundle++;
                }
            }
        }

        return bundle;
    }

    private static void dfs(int x, int y, boolean[][] isVisited) {
        isVisited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (checkRange(nextX, nextY)) {
                if (!isVisited[nextX][nextY] && map[nextX][nextY] > 0) {
                    dfs(nextX, nextY, isVisited);
                }
            }
        }
    }

    private static void meltIce() {
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < row; i++) {
            for (int k = 0; k < column; k++) {
                if (map[i][k] > 0) {
                    queue.offer(new int[]{i, k});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] start = queue.poll();
            int x = start[0];
            int y = start[1];
            int countZero = 0;

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if (checkRange(nextX, nextY)) {
                    if (map[nextX][nextY] == 0) countZero++;
                }
            }

            if (map[x][y] - countZero <= 0) {
                map[x][y] = -1;
            } else {
                map[x][y] -= countZero;
            }
        }
    }

    private static boolean checkRange(int x, int y) {
        return x >= 0 && y >= 0 && x < row && y < column;
    }
}
