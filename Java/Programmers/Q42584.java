package Programmers;

import java.io.*;
import java.util.*;
public class Q42584 {


// prices -> 가격이 떨어지지 않은 구간
// 각 시점 별로 떨어지지 않은 구간 인 듯.
// t 점차 늘림.. front 가 새로 들어온 것보다 낮으면 빼기 진행


    // Queue 가 아니라 Stack 이 맞는 듯
    class Solution {
        public int[] solution(int[] prices) {

            int N = prices.length;

            Stack<int[]> stack = new Stack<int[]>();
            int[] results = new int[N];
            int idx = 0; // results 에 쓰기 위한 index

            for (int t = 0; t < N; t++) {

                // 새로 들어오는 것 확인
                int[] cur = new int[] {prices[t], t};

                // cur < old 일 시 빼면서 결과에 집어넣기 반복
                while (!stack.isEmpty()) {

                    int[] old = stack.peek();

                    if (cur[0] >= old[0]) break;

                    stack.pop();
                    results[old[1]] = t - old[1];
                }

                // 새로 들어오는 건 집어넣음
                stack.push(cur);
            }

            while (!stack.isEmpty()) {
                int[] old = stack.pop();

                results[old[1]] = N - old[1] - 1;
            }


            return results;
        }
    }
}
