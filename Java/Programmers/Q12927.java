package Programmers;
import java.io.*;
import java.util.*;
public class Q12927 {
    class Solution {
        public long solution(int n, int[] works) {
            long answer = 0;

            int sum = 0;
            int len = works.length;

            for (int i = 0; i < len; i++)
                sum += works[i];

            if (sum <= n) return 0;

            PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Collections.reverseOrder());

            for (int i = 0; i < len; i++)
                queue.add(works[i]);

            for (int i = 0; i < n; i++) {
                int temp = queue.remove();
                queue.add(temp - 1);
            }

            for (int i = 0; i < len; i++) {
                answer += Math.pow(queue.remove(), 2);
            }


            return answer;


        }
    }
}
