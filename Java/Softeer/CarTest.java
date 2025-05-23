package Softeer;
import java.io.*;
import java.util.*;

// 우선 탐색을 이진 탐색으로 진행함
// 의외로 바로 정답이 나옴? 이게 DNA 와 같은 3단계?

public class CarTest {
    public static int n;
    public static int q;

    public static long[] arr;
    public static long[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        arr = new long[n];
        result = new long[q];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        // 각 q 마다 진행
        for (int t = 0; t < q; t++) {
            int m = Integer.parseInt(br.readLine());
            
            // index 찾기
            int start = 0;
            int end =  n - 1;
            int mid = (start + end) / 2;

            while(start <= end) {
                mid = (start + end) / 2;

                if(arr[mid] < m) {
                    start = mid + 1;
                }

                else if(arr[mid] > m) {
                    end = mid - 1;
                }

                else {
                    break;
                }
            }

            // 못 찾을 시 0
            if(arr[mid] != m) {
                result[t] = 0;
            }

            else {
                result[t] = (long) mid * (n - mid - 1);
            }

        }

        for (int i = 0; i < q; i++) {
            System.out.println(result[i]);
        }

    }

}
