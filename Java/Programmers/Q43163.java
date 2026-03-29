package Programmers;
import java.io.*;
import java.util.*;
public class Q43163 {


    // begin -> words 변경을 통해서 진행
// 단어의 길이가 같으므로 1번 이동시 글자 1개만 다른 걸로 하면 될 듯 함
    class Solution {

        // words size
        static int N;

        public int solution(String begin, String target, String[] words) {

            N = words.length;

            Map<String, Integer> visited = new HashMap<String, Integer>();

            Queue<String> queue = new ArrayDeque<String>();
            visited.put(begin, 1);
            queue.add(begin);

            while(!queue.isEmpty()) {
                String cur = queue.remove();

                if(cur.equals(target)) return visited.get(cur) - 1;

                for (String nxt : words) {
                    if(visited.get(nxt) != null) continue;
                    if(!isChange(cur, nxt)) continue;

                    queue.add(nxt);
                    visited.put(nxt, visited.get(cur) + 1);
                }
            }






            return 0;
        }

        boolean isChange(String cur, String nxt) {

            int size = cur.length();
            int diff = 0;

            for (int i = 0; i < size; i++) {
                if (cur.charAt(i) != nxt.charAt(i)) diff++;
            }

            if (diff == 1) return true;
            return false;

        }
    }
}
