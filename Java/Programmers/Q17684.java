package Programmers;
import java.io.*;
import java.util.*;
public class Q17684 {

    class Solution {
        public int[] solution(String msg) {
            Map<String, Integer> map = new HashMap<String, Integer>();
            List<Integer> list = new ArrayList<Integer>();

            int len = msg.length();

            for (int i = 0; i < 26; i++) {
                Character temp = (char)(65 + i);
                map.put(temp.toString(), i + 1);
            }

            String cur = "";
            int index = 27;

            // 가장 긴 거 찾아야 함
            // i 가 start, j 가 end
            for (int i = 0; i <= len - 1;) {

                for (int j = len - 1; i <= j; j--) {

                    // i ~ j 사이의 SubString
                    cur = msg.substring(i, j + 1);

                    // 사전에 있는 경우
                    if (map.get(cur) != null) {
                        list.add(map.get(cur));

                        // 이상한 것은 잡음
                        if (j < len - 1)
                            map.put(msg.substring(i, j + 2), index++);
                        i = j + 1;
                        break;
                    }
                }
            }

            // list.add(map.get(msg.substring(len - 1, len)));

            int array_len = list.size();

            int[] answer = new int[array_len];
            for (int i = 0; i < array_len; i++)
                answer[i] = list.get(i);


            return answer;
        }
    }
}
