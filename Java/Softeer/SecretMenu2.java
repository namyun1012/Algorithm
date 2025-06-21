package Softeer;
import java.util.*;
import java.io.*;

// 단순 비교는 시간 탐색 발생함
// 살짝 비효율적인 코드, LCS 알고리즘 적용
// O(MN)
public class SecretMenu2 {

    static int N, M, K;
    static int result = 0;
    static int[] first;
    static int[] second;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        first = new int[N];
        second = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            first[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            second[i] = Integer.parseInt(st.nextToken());
        }

        // i - first, j - second
        // LCS 알고리즘

        for (int i = 0; i < N; i++) {
            int count = 0;

            for(int j = 0; j < M && i + j < N ; j++) {
                if (first[i + j] == second[j]) {
                    count++;
                }

                else {
                    result = Math.max(result, count);
                    count = 0;
                }
            }

            result = Math.max(result, count);
        }

        for (int j = 0; j < M; j++) {
            int count = 0;

            for(int i = 0; i < N && i + j < M ; i++) {
                if (first[i] == second[i + j]) {
                    count++;
                }

                else {
                    result = Math.max(result, count);
                    count = 0;
                }
            }

            result = Math.max(result, count);
        }

        System.out.println(result);
        br.close();
    }
}
