package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_2805 {

    /*
    각각의 나무 높이가 주어질 때 필요한 나무를 가져가기 위한 최대 절단 높이를 구하는 문제.
    예를 들어 20, 15, 10, 17미터의 나무들이 있고 적어도 7미터를 가져가야 한다면
    최대 15미터 높이를 잘라야 5 + 0 + 0 + 2 = 7로 원하는 길이의 나무를 가져갈 수 있다.

    이분탐색을 사용한 문제
     */

    private static int countTree;
    private static long targetHeight;
    private static long maxHeight = 0;
    private static long[] trees;
    private static long answer;

    public static void main(String[] args) throws IOException {
        input();
        binarySearch();
        System.out.println(answer);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        countTree = Integer.parseInt(st.nextToken());
        targetHeight = Long.parseLong(st.nextToken());
        trees = new long[countTree];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < countTree; i++) {
            trees[i] = Long.parseLong(st.nextToken());
            if (trees[i] > maxHeight) maxHeight = trees[i];
        }
    }

    private static void binarySearch() {
        long left = 0;
        long right = maxHeight;
        long mid;

        while (left <= right) {
            mid = (left + right) / 2;
            long sum = 0;
            for (int i = 0; i < trees.length; i++) {
                long cutHeight = trees[i] - mid;
                sum = sum + Math.max(cutHeight, 0);
            }

            if (sum >= targetHeight) {
                left = mid + 1;
                answer = mid;
            }
            else right = mid - 1;
        }
    }
}
