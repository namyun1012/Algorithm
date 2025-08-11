package Programmers;
import java.io.*;
import java.util.*;

// 일종의 유사 투 포인터?
public class Q131127 {


    class Solution {

        static int N;
        static int M;

        public int solution(String[] want, int[] number, String[] discount) {
            N = want.length;
            M = discount.length;

            int answer = 0;
            Map<String, Integer> map = new HashMap<String, Integer>();

            for (int i = 0; i < N; i++) {
                map.put(want[i], i);
            }

            int[] temp = new int[N];
            for (int i = 0; i < 10; i++) {
                String cur = discount[i];

                if (map.get(cur) != null) {
                    temp[map.get(cur)] += 1;
                }
            }


            for (int i = 0; i <= M - 10; i++) {

                // 검증 진행
                boolean check = true;
                for (int j = 0; j < N; j++) {
                    if(temp[j] != number[j]) {
                        check = false;
                        break;
                    }
                }

                if (check) {
                    answer += 1;
                    System.out.println(i);
                }

                int start = i;
                int end = i + 10;

                if (i == M - 10) break;

                // 시작 빼고 끝을 더해줌
                String cur = discount[start];
                if (map.get(cur) != null) {
                    temp[map.get(cur)] -= 1;
                }

                cur = discount[end];
                if (map.get(cur) != null) {
                    temp[map.get(cur)] += 1;
                }

            }




            return answer;
        }
    }
}
