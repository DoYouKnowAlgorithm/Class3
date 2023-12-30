package Algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class _14621 {
	static class Vertex implements Comparable<Vertex> {
		int from;
		int to;
		int cost;

		public Vertex(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.cost - o.cost;
		}
	}

	static Queue<Vertex> vertices;
	static String[] schools;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}

		schools = br.readLine().split(" ");

		vertices = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			int[] inputs = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();

			if (!Objects.equals(schools[inputs[0] - 1], schools[inputs[1] - 1])) {
				vertices.add(new Vertex(inputs[0] - 1, inputs[1] - 1, inputs[2]));
			}
		}

		int cost = 0;
		int count = 0;

		while (!vertices.isEmpty()) {
			Vertex vertex = vertices.poll();

			if (find(vertex.from) != find(vertex.to)) {
				union(vertex.from, vertex.to);
				cost += vertex.cost;
				count++;
			}
		}

		System.out.println(count != N - 1 ? -1 : cost);
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
