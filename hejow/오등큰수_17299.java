package Algorithm.BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class _17299 {
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static Stack<Integer> stack = new Stack<>();
	static Map<Integer, Integer> count = new HashMap<>();
	static int[] numbers;
	static int[] answer;

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(bf.readLine());
		numbers = new int[N];
		answer = new int[N];

		readInputAndInitializeCount(N);

		solution(N);

		fillEmptyAnswer();
		printAnswer();
	}

	private static void solution(int size) {
		for (int idx = 0; idx < size; idx++) {
			// 현재 확인하는 값과 스택 끝의 값과 비교해서 오등큰수를 채운다.
			while (!stack.empty() && count.get(numbers[stack.peek()]) < count.get(numbers[idx])) {
				answer[stack.pop()] = numbers[idx];
			}

			stack.push(idx);
		}
	}

	private static void readInputAndInitializeCount(int size) throws IOException {
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < size; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());

			if (count.containsKey(numbers[i])) {
				int numberCount = count.get(numbers[i]);
				count.replace(numbers[i], numberCount + 1);
			} else {
				count.put(numbers[i], 1);
			}
		}
	}

	private static void fillEmptyAnswer() {
		while (!stack.empty()) {
			answer[stack.pop()] = -1;
		}
	}

	private static void printAnswer() throws IOException {
		for (int i : answer) {
			bw.write(i + " ");
		}

		bw.flush();
	}
}
