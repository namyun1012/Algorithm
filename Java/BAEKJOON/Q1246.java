import java.io.*;
import java.util.*;

// 쓸데 없이 이분탐색으로 풀려다가 망함
// 그냥 풀것

public class Q1246 {

    static int N, M;

    static int[] arr;


    static int price = 0;
    static int result = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];

        int min = 1;
        int max = 1_000_000;



        for(int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        // Cost 순으로 정렬
        Arrays.sort(arr);

        for (int i = min; i <= max; i++) {

            int count = 0;
            int j = 0;
            for (j = 0; j < M; j++) {
                if (arr[j] >= i) break;
            }

            count = Math.min(N, M - j);

            if (i * count >= result) {
                price = i;
                result = i * count;
            }
        }

        System.out.println(price + " " + result);
        br.close();
    }

}
