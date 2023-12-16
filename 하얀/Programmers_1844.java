import java.util.LinkedList;
import java.util.Queue;

public class Programmers_1844 {
    private static int[][] maps;
    private static int answer = 0;
    private static int[][] dist;

    public static void main(String[] args) {
        dist = new int[maps.length][maps[0].length];
        bfs(new int[]{0, 0});

        System.out.println(answer);
    }

    static void bfs(int[] start) {
        Queue<int[]> qu = new LinkedList<>();
        qu.add(start);
        int[] dx = {0, -1, 0, 1};
        int[] dy = {1, 0, -1, 0};

        while(!qu.isEmpty()) {
            int[] current = qu.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];
                if (nextX >= 0 && nextY >= 0 && nextX < maps.length && nextY < maps[0].length) {
                    if (maps[nextX][nextY] == 1 && dist[nextX][nextY] == 0) {
                        dist[nextX][nextY] = dist[x][y] + 1;
                        qu.add(new int[]{nextX, nextY});
                    }
                }
            }
        }

        if (dist[maps.length - 1][maps[0].length - 1] == 0) {
            answer = -1;
        } else {
            answer = dist[maps.length - 1][maps[0].length - 1] + 1;
        }
    }
}