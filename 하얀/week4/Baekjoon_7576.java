package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_7576 {

    /*
     * 상자 안에 익은 토마토와 익지 않은 토마토가 섞여 있다.
     * 하루가 지나면 익은 토마토의 상, 하, 좌, 우 토마토는 익는다.
     * 전체 토마토가 다 익을 때까지 걸리는 최소 일수를 구한다.
     *
     * 1은 익은 토마토, 0은 익지 않은 토마토, -1은 비어있는 칸
     */

    private static int row, column;
    private static int[][] tomatoes;
    private static Queue<int[]> queue = new LinkedList<>();
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int answer;
    private static int countAlreadyRipened = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    private static void output() {
        System.out.println(answer);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        column = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        tomatoes = new int[row][column];

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < column; k++) {
                tomatoes[i][k] = Integer.parseInt(st.nextToken());

                if (tomatoes[i][k] == 1) {
                    countAlreadyRipened++;
                    queue.offer(new int[]{i, k});
                }
            }
        }
    }

    private static void solve() {
        bfs();
        findAnswer();
    }

    private static void findAnswer() {
        if (countAlreadyRipened == row * column) {
            answer = 0;
            return;
        }

        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int k = 0; k < column; k++) {

                if (tomatoes[i][k] == 0) {
                    answer = -1;
                    return;
                }

                if (tomatoes[i][k] > max) {
                    max = tomatoes[i][k];
                }
            }
        }

        answer = max - 1;
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if (validateRange(nextX, nextY) && tomatoes[nextX][nextY] == 0) {
                    tomatoes[nextX][nextY] = tomatoes[x][y] + 1;
                    queue.offer(new int[]{nextX, nextY});
                }
            }
        }
    }

    private static boolean validateRange(int x, int y) {
        return x >= 0 && y >= 0 && x < row && y < column;
    }
}
