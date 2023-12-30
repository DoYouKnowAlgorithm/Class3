package Algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class _10423 {
	private static final int CONNECTED = -1;

	static class Edge {
		int from;
		int to;
		int cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}

	static List<Edge> edges = new ArrayList<>();
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		parents = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			parents[i] = i;
		}

		Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.forEach(index -> parents[index] = CONNECTED);

		for (int i = 0; i < M; i++) {
			int[] inputs = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();

			edges.add(new Edge(inputs[0], inputs[1], inputs[2]));
		}

		edges.sort(Comparator.comparingInt(a -> a.cost));

		int cost = 0;
		for (Edge edge : edges) {
			if (find(edge.from) != find(edge.to)) {
				union(edge.from, edge.to);
				cost += edge.cost;
			}
		}

		System.out.println(cost);
	}

	private static int find(int a) {
		if (parents[a] == CONNECTED) {
			return CONNECTED;
		}

		return a != parents[a] ? find(parents[a]) : a;
	}

	private static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y) {
			if (x == CONNECTED) {
				parents[y] = x;
			} else if (y == CONNECTED) {
				parents[x] = y;
			} else {
				if (x > y) {
					parents[x] = y;
				} else {
					parents[y] = x;
				}
			}
		}
	}
}
