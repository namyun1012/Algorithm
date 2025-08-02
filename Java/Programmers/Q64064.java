package Programmers;
import java.io.*;
import java.util.*;

public class Q64064 {

// 위치를 Hash 함 Bitmasking 이면 더 좋긴 할 듯?

    class Solution {
        static int N;
        static int M;
        static int result;
        static String[] user_ids;
        static String[] banned_ids;
        static Map<Long, Integer> map;

        public int solution(String[] user_id, String[] banned_id) {
            int answer = 0;

            N = user_id.length;
            M = banned_id.length;

            user_ids = user_id;
            banned_ids = banned_id;
            map = new HashMap<Long, Integer>();

            backtracking(0, new int[N]);

            return result;
        }

        public boolean check(String user_id, String banned) {
            if (user_id.length() != banned.length()) return false;

            for (int i = 0; i < user_id.length(); i++) {
                char user_cur = user_id.charAt(i);
                char banned_cur = banned.charAt(i);

                if (banned_cur == '*') continue;
                if (user_cur != banned_cur) return false;

            }
            return true;
        }

        // index 는 banned id 의 index
        public void backtracking(int index, int[] banned) {
            if (index == M) {
                long temp = saved(banned);

                if (map.get(temp) == null) {
                    map.put(temp, 1);
                    result++;
                }

                return ;
            }

            String ban_id = banned_ids[index];

            for (int i = 0; i < N; i++) {
                String user_id = user_ids[i];

                if(banned[i] == 1) continue;
                if(!check(user_id, ban_id)) continue;

                banned[i] = 1;
                backtracking(index + 1, banned);
                banned[i] = 0;
            }
        }

        // 반쯤 Hash 용도 arr 구별 용도임
        public long saved(int[] arr) {
            long temp = 0;
            for (int i = 0; i < arr.length; i++) {
                temp += (arr[i] * (long) Math.pow(10, i));
            }
            return temp;
        }
    }
}
