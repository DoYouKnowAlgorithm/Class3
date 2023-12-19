package Algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _1967 {
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static List<Algorithm.BOJ._1967.Node>[] graph;
	static boolean[] visited;
	static int maxWeight;

	static class Node {
		int from;
		int weight;

		Node(int from, int weight) {
			this.from = from;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(bf.readLine());

		graph = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < n - 1; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			graph[from].add(new Algorithm.BOJ._1967.Node(to, weight));
			graph[to].add(new Algorithm.BOJ._1967.Node(from, weight));
		}

		for (int i = 1; i <= n; i++) {
			visited = new boolean[n + 1];
			visited[i] = true;
			dfs(i, 0);
		}

		System.out.println(maxWeight);
	}

	private static void dfs(int start, int totalWeight) {
		for (Algorithm.BOJ._1967.Node node : graph[start]) {
			if (!visited[node.from]) {
				visited[node.from] = true;
				dfs(node.from, totalWeight + node.weight);
			}
		}

		maxWeight = Math.max(maxWeight, totalWeight);
	}
}
