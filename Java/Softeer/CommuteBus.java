package Softeer;
import java.io.*;
import java.util.*;

// 단순 계산 125_000_000_000 => 불가능 할 듯

// 결과는 몇 개나 존재하는지
// 번호는 무조건 순차적으로,
// i < j < k
// ak < ai < aj 일시
// 누적합 사용 필요
// 결과값 long 으로 지정 필요

public class CommuteBus {

    public static int N;
    public static int[] arr;
    public static long result = 0;




    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N; i++) {
            int count = 0;
            // arr[i] < arr[j] 일시 count 증가
            for(int j = i + 1; j < N; j++) {
                if(arr[i] < arr[j]) {
                    count++;
                }

                // arr[i] > arr[j] 인 경우
                // current j 를 k 로 봄
                // i < j(count) < k
                // arr[k] < arr[i] < count 에 들어간 것들 (j) 가 성립
                else {
                    result += count;
                }
            }
        }

        System.out.println(result);
    }
}
