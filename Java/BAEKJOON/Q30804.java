// 그리디 방식으로는 해결 안 됨
// 누적합 방식 시간 초과
// O(N^2) 의 문제?
// 구현을 떠올리기 어려웠던 문제
// 일종의 slicing window? 투 포인터이나 시작, 끝 둘다 0에서 부터 시작함

import java.io.*;
import java.util.*;

public class Q30804 {

    static int N;
    static int cur = 0;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        int[] fruits = new int[10];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int fruit = Integer.parseInt(st.nextToken());
            arr[i] = fruit;
        }

        int start = 0;
        int types = 0;
        // N = 20만 이므로 O(N^2) 에서 끝내 보기
        // i, j 는 start, end 지점
        // 계속 end 를 늘리는 방식 type 이 2가 넘는 경우 start 를 1씩 늘림, 각 end 마다 비교 
        for (int end = 0; end < N; end++) {

            if(fruits[arr[end]] == 0) {
                types++;
            }

            fruits[arr[end]]++;

            if (types > 2) {
                fruits[arr[start]]--;

                if (fruits[arr[start]] == 0) {
                    types -= 1;
                }

                start++;
            }
            result = Math.max(result, end - start + 1);
        }


        System.out.println(result);
        br.close();
    }

}
