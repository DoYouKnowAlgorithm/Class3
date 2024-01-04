package Algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class _21924 {
	private static class Edge {
		int to;
		int cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	private static final int NOT_CONNECTED = -1;

	static boolean[] visited;
	static Set<Edge>[] graph;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		visited = new boolean[N + 1];
		graph = new Set[N + 1];
		for (int i = 1; i < N + 1; i++) {
			graph[i] = new HashSet<>();
		}

		long totalCost = 0;
		for (int i = 0; i < M; i++) {
			int[] inputs = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();

			graph[inputs[0]].add(new Edge(inputs[1], inputs[2]));
			graph[inputs[1]].add(new Edge(inputs[0], inputs[2]));
			totalCost += inputs[2];
		}

		long saved = prim();
		System.out.println(saved != NOT_CONNECTED ? totalCost - saved : NOT_CONNECTED);
	}

	private static long prim() {
		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
		pq.add(new Edge(1, 0));

		long cost = 0;
		int count = 0;
		while (!pq.isEmpty()) {
			Edge current = pq.poll();

			if (visited[current.to]) {
				continue;
			}

			visited[current.to] = true;
			cost += current.cost;
			count++;

			for (Edge edge : graph[current.to]) {
				if (!visited[edge.to]) {
					pq.add(edge);
				}
			}
		}

		return count == N ? cost : NOT_CONNECTED;
	}
}
