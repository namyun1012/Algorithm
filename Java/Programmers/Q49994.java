package Programmers;

import java.io.*;
import java.util.*;

public class Q49994 {

    class Solution {

        static int[] dx = {0,0,1,-1};
        static int[] dy = {-1,1,0,0};

        public class Road {
            int start_x, start_y, end_x, end_y;

            public Road(int start_x, int start_y, int end_x, int end_y) {
                this.start_x = start_x;
                this.start_y = start_y;
                this.end_x = end_x;
                this.end_y = end_y;
            }
        }

        public int solution(String dirs) {
            int answer = 0;

            int max = 5;
            int min = -5;

            int cur_y = 0;
            int cur_x = 0;

            int nxt_y = 0;
            int nxt_x = 0;

            Map<Integer, Integer> map = new HashMap<>();

            // dir U D R L
            for (int i = 0; i < dirs.length(); i++) {
                char cur = dirs.charAt(i);
                int dir = -1;

                switch (cur) {
                    case 'U': dir = 0; break;
                    case 'D': dir = 1; break;
                    case 'R': dir = 2; break;
                    default : dir = 3;
                }

                nxt_y = cur_y + dy[dir];
                nxt_x = cur_x + dx[dir];

                if (nxt_y > max || nxt_x > max || nxt_y < min || nxt_x < min) continue;


                int check1 = getBits(cur_x, cur_y, nxt_x, nxt_y);
                int check2 = getBits(nxt_x, nxt_y, cur_x, cur_y);

                if (map.get(check1) == null) {
                    map.put(check1, 1);
                    map.put(check2, 1);
                    answer++;
                }

                cur_x = nxt_x;
                cur_y = nxt_y;
            }
            return answer;
        }


        public int getBits(int start_x, int start_y, int end_x, int end_y) {
            int sum = 0;

            sum += (start_x + 5) * 11;
            sum += (start_y + 5) * (int) Math.pow(11, 2);
            sum += (end_x + 5) * (int) Math.pow(11, 3);
            sum += (end_y + 5) * (int) Math.pow(11, 4);

            return sum;
        }
    }
}
