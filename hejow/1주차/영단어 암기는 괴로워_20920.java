package Algorithm.BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.function.Consumer;

public class _20920 {
static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		int n = java.lang.Integer.parseInt(tokenizer.nextToken());
		int size = java.lang.Integer.parseInt(tokenizer.nextToken());

		Map<String, Integer> words = readWords(n, size);

		words.entrySet().stream()
			.sorted(customSort())
			.forEach(writeKey());

		bw.flush();
	}

	private static Consumer<Map.Entry<String, Integer>> writeKey() {
		return entry -> {
			try {
				bw.write(entry.getKey() + "\n");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		};
	}

	private static Map<String, Integer> readWords(int n, int size) throws IOException {
		Map<String, Integer> words = new HashMap<>();

		for (int i = 0; i < n; i++) {
			String word = br.readLine();

			if (word.length() >= size) {
				if (words.containsKey(word)) {
					Integer count = words.get(word);
					words.replace(word, count + 1);
				} else {
					words.put(word, 1);
				}
			}
		}

		return words;
	}

	private static Comparator<Map.Entry<String, Integer>> customSort() {
		return (c1, c2) -> {
			String c1Key = c1.getKey();
			Integer c1Count = c1.getValue();

			String c2Key = c2.getKey();
			Integer c2Count = c2.getValue();

			return Objects.equals(c1Count, c2Count) ? compareKeyLengthAndDictionaryOrder(c1Key, c2Key) : c2Count.compareTo(c1Count);
		};
	}

	private static int compareKeyLengthAndDictionaryOrder(String key1, String key2) {
		return key1.length() == key2.length() ? key1.compareTo(key2) : compareKeyLength(key1, key2);
	}

	private static int compareKeyLength(String key1, String key2) {
		return key1.length() > key2.length() ? -1 : 1;
	}
}
