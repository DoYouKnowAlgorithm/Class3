package Algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _16398 {
	static class Node {
		int from;
		int to;
		int cost;

		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}

	static List<Node> nodes = new ArrayList<>();
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		parents = new int[n + 1];
		for (int i = 0; i < n + 1; i++) {
			parents[i] = i;
		}

		for (int i = 0; i < n; i++) {
			int[] inputs = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();

			for (int j = i + 1; j < n; j++) {
				nodes.add(new Node(i + 1, j + 1, inputs[j]));
			}
		}

		nodes.sort(Comparator.comparingInt(a -> a.cost));

		long answer = 0;
		for (Node node : nodes) {
			if (find(node.from) != find(node.to)) {
				union(node.from, node.to);
				answer += node.cost;
			}
		}

		System.out.println(answer);
	}

	private static int find(int a) {
		return a != parents[a] ? find(parents[a]) : a;
	}

	private static void union(int x, int y) {
		int nx = find(y);
		int ny = find(x);
		parents[ny] = nx;
	}
}
