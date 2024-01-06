package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_2579 {

    /*
    1. 계단은 한 번에 1개 혹은 2개씩 오를 수 있다.
    2. 연속된 세 개의 계단을 모두 밟아서는 안 된다.
    3. 마지막 도착 계단은 반드시 밟아야 한다.
    이 때, 얻을 수 있는 총 점수의 최댓값을 구해야 한다.

    경우의 수
    1. n-2번째 계단을 밟지 않고 n, n-1번째 계단을 밟는 경우
    2. n-1번째 계단을 밟지 않고 n번째 계단을 밟는 경우
     */

    private static int countStairs;
    private static int[] score;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    private static void output() {
        System.out.println(dp[countStairs - 1]);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        countStairs = Integer.parseInt(br.readLine());
        score = new int[countStairs];
        dp = new int[countStairs];

        for (int i = 0; i < countStairs; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }
    }

    private static void solve() {
        dp[0] = score[0];
        if (countStairs > 1) dp[1] = score[0] + score[1];
        if (countStairs > 2) dp[2] = Math.max(score[0], score[1]) + score[2];

        for (int i = 3; i < countStairs; i++) {
            dp[i] = Math.max(dp[i - 3] + score[i - 1], dp[i - 2]) + score[i];
        }
    }
}
