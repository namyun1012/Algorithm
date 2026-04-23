package Programmers;

import java.io.*;
import java.util.*;

public class Q152996 {

    // 우선 정렬 부터..?
// 2, 3, 4 시소 짝꿍이 몇 쌍 존재하는 지 확인.
// 1 : 2, 2 : 3, 3: 4 의 비율 까지 가능함. + 1 : 1 동일한 경우..
// N 이 10만이므로 O(N^2) 는 힘들고, O (LOG N ) 까지 가능할 듯
// 그냥 Map 에다 전부 넣고 재 확인 하는 식으로.? ㄱㄱ..?
// 실패 존재..
    class Solution {
        public long solution(int[] weights) {

            Map<Long, Long> map = new TreeMap<>();
            long answer = 0;



            for (int i = 0; i < weights.length; i++) {
                long weight = weights[i];

                Long cur = map.get(weight);

                if (cur == null) map.put(weight, 1L);
                else map.put(weight, cur + 1);
            }

            // Map Key 별 연산 시작
            for (long val : map.keySet()) {

                long cur_count = (Long) map.get(val);

                // 자기 자신 nC2 => n * (n - 1) / 2;
                answer += cur_count * (cur_count - 1) / 2;

                // 2배 큰 것
                Long other_count = map.get(val * 2);
                if (other_count != null) answer += (other_count * cur_count);

                // 3/2 배 인 것 정확히..
                if (val % 2 == 0) {
                    other_count = map.get(val * 3 / 2);
                    if (other_count != null) answer += (other_count * cur_count);
                }

                // 4/3 배 인 것 정확히..
                if (val % 3 == 0) {
                    other_count = map.get(val * 4 / 3);
                    if (other_count != null) answer += (other_count * cur_count);
                }
            }



            return answer;
        }
    }
}
