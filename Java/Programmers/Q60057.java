package Programmers;
import java.io.*;
import java.util.*;
public class Q60057 {


// 문자열은 제일 앞 부터 정해진 길이 만큼만 짤라야 함
// 잘라보면 서 제일 짧은 거 반환하면 될 듯
// 합칠 수 있는 지 없는 지 확인은

    class Solution {
        public int solution(String s) {

            int answer = 0;
            int size = s.length();
            int result = size; // 우선 길이로..


            // i 만큼 자름.
            for (int i = 1; i <= size; i++) {

                int idx = i;
                int count = 1;
                int length = 0;

                String prev = s.substring(0, i);

                while(idx + i <= size) {
                    String cur = s.substring(idx, idx + i);

                    if (cur.equals(prev)) {
                        count++;
                    } else {
                        length += solve_cur_len(count, i);
                        prev = cur;
                        count = 1;
                    }
                    idx += i;
                }

                // 1. 마지막으로 비교했던 뭉치(prev) 처리
                length += solve_cur_len(count, i);

                // 2. i 단위로 쪼개고 남은 진짜 '자투리'만 더함
                length += (size - idx);

                result = Math.min(result, length);
            }

            return result;
        }

        int solve_cur_len(int count, int i) {
            // prev 에 대한 처리 진행.
            int cur_len = 0;

            // prev 또한 한번만 나옴.
            if (count == 1) {
                cur_len += i;
            }

            // 2번 이상 나옴
            else {
                int digit = 0;

                while (count > 0) {
                    count /= 10;
                    digit++;
                }

                cur_len += (i + digit);
            }

            return cur_len;
        }
    }
}
