package week2;

import java.io.*;

public class Baekjoon_1463 {
    private static int number;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    private static void output() {
        System.out.println(dp[number]);
    }

    private static void solve() {
        dp = new int[number + 1];
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i-1] + 1;
            if (i % 2 == 0) dp[i] = Math.min(dp[i / 2] + 1, dp[i]);
            if (i % 3 == 0) dp[i] = Math.min(dp[i / 3] + 1, dp[i]);
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        number = Integer.parseInt(br.readLine());
    }
}
