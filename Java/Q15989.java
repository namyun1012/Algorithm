import java.io.*;
import java.util.*;

public class Q15989 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[] arr = new long[10_001];
        
        // 1로
        Arrays.fill(arr, 1);

        // 1, 2
        for(int i = 2; i <= 10000; i++) {
            arr[i] += arr[i-2];
        }

        // 1, 2, 3
        for(int i = 3; i <= 10000; i++) {
            arr[i] += arr[i-3];
        }


        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            System.out.println(arr[N]);

        }
    }





}
