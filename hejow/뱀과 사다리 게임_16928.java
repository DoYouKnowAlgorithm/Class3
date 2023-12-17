package Algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _16928 {
	private static final int DESTINATION = 100;

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static int[] board = new int[DESTINATION + 1];

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		for (int i = 1; i < board.length; i++) {
			board[i] = i;
		}

		for (int i = 0; i < n + m; i++) {
			st = new StringTokenizer(bf.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			board[start] = end;
		}

		int answer = bfs(1);
		System.out.println(answer);
	}

	private static int bfs(int start) {
		int[] visited = new int[DESTINATION + 1];
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visited[start] = 0;

		while (true) {
			int current = q.poll();
			for (int i = 1; i < 7; i++) {
				int toGo = current + i;

				if (DESTINATION < toGo) {
					continue;
				}

				if (visited[board[toGo]] == 0) {
					q.offer(board[toGo]);
					visited[board[toGo]] = visited[current] + 1;
				}

				if (board[toGo] == DESTINATION) {
					return visited[DESTINATION];
				}
			}
		}
	}
}
