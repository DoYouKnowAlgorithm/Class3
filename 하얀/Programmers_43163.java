public class Programmers_43163 {
    private static String[] words;
    private static String target;
    private static String begin;
    private static int answer;
    private static boolean[] isVisited;
    private static final int IMPOSSIBILITY = 100;

    public static void main(String[] args) {
        answer = 100;
        isVisited = new boolean[words.length];
        dfs(begin, 0);

        if (answer == IMPOSSIBILITY) answer = 0;
        System.out.println(answer);
    }

    static void dfs(String begin, int count) {
        if (begin.equals(target)) {
            answer = Math.min(answer, count);

            return;
        }

        for (int i = 0; i < words.length; i++) {
            int len = 0;
            if (!isVisited[i]) {
                for (int k = 0; k < words[i].length(); k++) {
                    if (begin.charAt(k) == words[i].charAt(k)) len++;
                }

                if (len == begin.length() - 1) {
                    isVisited[i] = true;
                    dfs(words[i], count + 1);
                    isVisited[i] = false;
                }
            }
        }
    }
}
