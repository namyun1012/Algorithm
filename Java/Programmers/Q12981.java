package Programmers;

import java.io.*;
import java.util.*;

public class Q12981 {


// 가장 먼저 탈락하는 사람 + 몇 번쨰 차례의 탈락하는 지..
// 시간 복잡도의 고려는 필요 없을 듯.
// 이전에 있던 단어 사용 여부 기록, 가장 짧은 시간 내 탈락 하는 그런 거 측정은 없는 듯..
// 50% 가량 실패..?
// words 는 사용한 단어들이 순서대로 들어있는 배열

    class Solution {

        static int N;

        public int[] solution(int n, String[] words) {

            N = words.length;

            Map<String, Integer> map = new HashMap<>();

            // 첫번 째는 미리 사용
            String start = words[0];

            map.put(start, 1);

            String prev = start;

            // 단어 별로 계속 진행.
            for (int i = 1; i < N; i++) {

                int round = i / n;
                int player = i % n;

                char prev_char = prev.charAt(prev.length() - 1);

                if (words[i].charAt(0) == prev_char && map.get(words[i]) == null) {
                    map.put(words[i], 1);
                    prev = words[i];
                }

                else return new int[] {player + 1, round + 1};
            }

            return new int[] {0, 0};
        }
    }
}
