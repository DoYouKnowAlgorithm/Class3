package Algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _2206 {
	static class Point {
		int x;
		int y;
		int distance;
		boolean neverBroke;

		private Point(int x, int y, int distance, boolean neverBroke) {
			this.x = x;
			this.y = y;
			this.distance = distance;
			this.neverBroke = neverBroke;
		}

		static Algorithm.BOJ._2206.Point never(int x, int y, int distance) {
			return new Algorithm.BOJ._2206.Point(x, y, distance, true);
		}

		static Algorithm.BOJ._2206.Point broke(int x, int y, int distance) {
			return new Algorithm.BOJ._2206.Point(x, y, distance, false);
		}
	}

	private static final int START = Integer.MAX_VALUE;
	private static final int WALL = 1;

	static int[][] graph;
	static boolean[][][] visited;

	static int answer = START;
	static int n;
	static int m;

	static int[] dx = new int[] {1, 0, -1, 0};
	static int[] dy = new int[] {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new int[n][m];
		visited = new boolean[n][m][2]; // 0 : never, 1 : broke;

		for (int i = 0; i < n; i++) {
			graph[i] = br.readLine().chars()
				.map(Character::getNumericValue)
				.toArray();
		}

		bfs();
		System.out.println(answer != START ? answer : -1);
	}

	private static void bfs() {
		Queue<Algorithm.BOJ._2206.Point> queue = new LinkedList<>();
		queue.add(Algorithm.BOJ._2206.Point.never(0, 0, 1));

		while (!queue.isEmpty()) {
			Algorithm.BOJ._2206.Point point = queue.poll();

			if (isDestination(point)) {
				answer = Math.min(point.distance, answer);
			}

			for (int i = 0; i < 4; i++) {
				int nx = point.x + dx[i];
				int ny = point.y + dy[i];
				int nDistance = point.distance + 1;

				if (isOutOfRange(nx, ny)) {
					continue;
				}

				if (graph[nx][ny] == WALL) {
					if (point.neverBroke) {
						visited[nx][ny][1] = true;
						queue.add(Algorithm.BOJ._2206.Point.broke(nx, ny, nDistance));
					}
				} else {
					if (point.neverBroke) {
						if (!visited[nx][ny][0]) {
							visited[nx][ny][0] = true;
							queue.add(Algorithm.BOJ._2206.Point.never(nx, ny, nDistance));
						}
					} else {
						if (!visited[nx][ny][1]) {
							visited[nx][ny][1] = true;
							queue.add(Algorithm.BOJ._2206.Point.broke(nx, ny, nDistance));
						}
					}
				}
			}
		}
	}

	private static boolean isDestination(Algorithm.BOJ._2206.Point point) {
		return point.x == n - 1 && point.y == m - 1;
	}

	private static boolean isOutOfRange(int nx, int ny) {
		return 0 > nx || n <= nx || 0 > ny || m <= ny;
	}
}
