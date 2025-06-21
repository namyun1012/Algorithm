package Softeer;
// M, N 등이 작아서 그냥 해도 될 듯?
// O(N * M)

import java.io.*;
import java.util.*;
public class SecretMenu {

    static int M, N, K;
    static int[] secret;
    static int[] control;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        secret = new int[M];
        control = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            secret[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            control[i] = Integer.parseInt(st.nextToken());
        }

        int front = secret[0];
        boolean check = false;

        for(int i = 0; i <= (N - M); i++) {
            // 같을 시 검사 진행
            if(control[i] == front) {
                for(int j = 0; j < M; j++) {
                    if(control[j + i] != secret[j])
                        break;
                    if(j == M-1)
                        check = true;
                }
            }

            if(check) break;
        }

        if(check)
            System.out.println("secret");
        else
            System.out.println("normal");

        br.close();
    }
}
