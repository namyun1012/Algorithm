import java.io.*;
import java.util.*;

public class Q1138 {

    static int N;
    static int[] arr;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 제일 작은 사람들 부터 주어짐
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // solution
        int[] result = new int[N + 1];

        result[arr[1] + 1] = 1;

        for (int i = 2; i <= N; i++) {
            int count = 0;
            
            // 자신 보다 왼쪽에 있는 더 큰 것들
            int left = arr[i];

            for(int j = 1; j <= N; j++) {
                
                // count 충족시 들어감
                if(count == left && result[j] == 0) {
                    result[j] = i;
                    break;
                }
                
                // 무조건 자신 보다 작은 것
                if(result[j] != 0) continue;
                
                // 자신 보다 큰 것이 들어갈 위치
                count++;
            }
        }

        for (int i = 1; i <= N; i++) {
            System.out.print(result[i] + " ");
        }

        br.close();
    }
}
