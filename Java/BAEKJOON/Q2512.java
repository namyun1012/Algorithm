import java.io.*;
import java.util.*;

// Parametric Search 에서 약간의 변형

public class Q2512 {

    static int N;
    static int M;
    static int[] arr;
    static int result = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        int max_value = 0;

        for (int i = 0; i < N; i++) {

            arr[i] = Integer.parseInt(st.nextToken());
            max_value = Math.max(max_value, arr[i]);
        }

        M = Integer.parseInt(br.readLine());

        int start = 0;
        int end = M;
        
        
        // mid = 상한액
        while(start <= end) {
            int mid = (start + end) / 2;

            long sum = check(mid);

            if(sum <= M ) {
                result = Math.max(result, mid);
                start = mid + 1;
            }

            else {
                end = mid - 1;
            }
        }
        
        // 상한치가 아무도 없음
        if(result == M) result = max_value;

        System.out.println(result);
        br.close();
    }

    public static long check(int mid) {
        long sum = 0;

        for (int i = 0; i < N; i++) {
            if(arr[i] >= mid) sum += mid;
            else sum += arr[i];
        }

        return sum;
    }

}
