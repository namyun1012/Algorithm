package Softeer;
import java.io.*;
import java.util.*;


// Overflow 처리해도 오류 발생 중
// min, max 처리 필요
// 결과적으로 O(N)? Integer.MAX , 0 으로 고정되어 있어서 이진 탐색 부분은 O(1)

public class SuperComputerClustering {

    public static int N;
    public static int[] arr;
    public static long B;
    public static long result = 0;

    public static long cost(long min_p) {
        // 우선 Overflow 처리함, 정답인 항목 늘어남
        long cur_result = 0;

        try {
            for (int i = 0; i < N; i++) {
                // 오버플로우 감지시, 바로 MAX 값 내보냄
                if (arr[i] < min_p) {
                    cur_result = Math.addExact((min_p - arr[i]) * (min_p - arr[i]), cur_result);
                }
            }
        }

        catch (Exception e) {
            cur_result = B + 1;
        }

        finally {
            return cur_result;
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 최저 성능을 mid 로 삼고 Parametric Search 진행
        // 최대 성능은 10^9 승이 아니라 Integer 의 최고 값
        long min = 0;
        long max = Integer.MAX_VALUE;

        long min_p;

        while (min <= max) {
            min_p = (max + min) / 2;

            long cur_cost = cost(min_p);
            
            // 가용 가능한 Cost 보다 클 경우, Cost 줄임
            if (cur_cost > B) {
                max = min_p - 1;
            }
            
            // 가용 가능한 Cost 보다 작거나 같은 경우, 더 늘려서 해봄
            else {
                result = Math.max(result, min_p);
                min = min_p + 1;
            }
        }

        System.out.println(result);
    }
}
