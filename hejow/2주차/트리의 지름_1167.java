package Algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _1167 {
	private static final int START = 1;

	static class Node {
		int to;
		int cost;

		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	static boolean[] visited;
	static List<Algorithm.BOJ._1167.Node>[] graph;
	static int diameter = 0;
	static int farAway;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int vertex = Integer.parseInt(br.readLine());

		graph = new ArrayList[vertex + 1];
		for (int i = 0; i < vertex + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < vertex; i++) {
			int[] inputs = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();

			int from = inputs[0];
			for (int j = START; j < inputs.length - 1; j += 2) {
				int to = inputs[j];
				int cost = inputs[j + 1];

				graph[from].add(new Algorithm.BOJ._1167.Node(to, cost));
				graph[to].add(new Algorithm.BOJ._1167.Node(from, cost));
			}
		}

		visited = new boolean[vertex + 1];
		dfs(START, 0);

		visited = new boolean[vertex + 1];
		dfs(farAway, 0);

		System.out.println(diameter);
	}

	private static void dfs(int index, int totalCost) {
		if (diameter < totalCost) {
			diameter = totalCost;
			farAway = index;
		}

		for (Algorithm.BOJ._1167.Node node : graph[index]) {
			if (visited[node.to]) {
				continue;
			}

			visited[index] = true;
			dfs(node.to, totalCost + node.cost);
		}
	}
}
