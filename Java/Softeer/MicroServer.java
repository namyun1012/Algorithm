package Softeer;
import java.util.*;
import java.io.*;

// MicroServer 에는 1 ~ 3개가 들어갈 수 있음.
// 시간 복잡도 고려 필요
// 투 포인터 접근 및 Input 에 맞는 알고리즘 설계 필요
// O(T * N * log(N))

public class MicroServer {

    static int T;
    static int N;
    static int[] arr;
    static int[] dp;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        result = new int[T];

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            
            // Array 를 Sort 한 후에 진행
            Arrays.sort(arr);


            int count = 0;
            int start = 0;
            int end = N-1;
            int count_300 = 0;
            // 단순 Greedy 는 안 되는 듯하다. 투 포인터 접근
            // 뒤에 것이 되면 그것을 우선 해서 넣는 방식

            for (;start <= end;) {
                int start_value = arr[start];
                int end_value = arr[end];

                // 601 이상 처리
                if(end_value > 600) {
                    count++;
                    end--;
                }

                // 300을 미리 처리 해 둔다.
                else if(start_value == 300) {
                    count_300 += 1;
                    start++;
                }

                else {
                    break;
                }
            }

            for (;start <= end;) {
                int start_value = arr[start];
                int end_value = arr[end];

                if (start_value + end_value <= 900 && start < end) {
                    count++;
                    start++;
                    end--;
                }

                // 300 사용 가능한 경우
                else if(count_300 > 0) {
                    count++;
                    count_300 -= 1;
                    end--;
                }

                // 뒤에 것이 통채로 써야 함
                else {
                    count++;
                    end--;
                }
            }

            count += (count_300 / 3);
            if (count_300 % 3 > 0)
                count++;

            result[t] = count;
        }

        for(int i = 0; i < T; i++) {
            System.out.println(result[i]);
        }

    }
}
