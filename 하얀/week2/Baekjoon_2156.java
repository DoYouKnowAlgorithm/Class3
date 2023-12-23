package week2;

import java.io.*;

public class Baekjoon_2156 {
    private static int n;
    private static int[] quantity;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        quantity = new int[n];
        dp = new int[n];

        for (int i = 0; i < n; i++) {
            quantity[i] = Integer.parseInt(br.readLine());
        }
    }

    private static void solve() {
        dp[0] = quantity[0];

        if (n > 1) dp[1] = dp[0] + quantity[1];
        if (n > 2) dp[2] = Math.max(Math.max(dp[1], dp[0] + quantity[2]), quantity[1] + quantity[2]);

        for (int i = 3; i < n; i++) {
            dp[i] = Math.max(Math.max(dp[i - 1], dp[i - 2] + quantity[i]), dp[i - 3] + quantity[i - 1] + quantity[i]);
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(dp[n - 1]));
        bw.flush();
    }
}
