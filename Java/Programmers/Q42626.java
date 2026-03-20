package Programmers;
import java.io.*;
import java.util.*;

public class Q42626 {


    class Solution {
        public int solution(int[] scoville, int K) {

            int size = scoville.length;

            PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

            for (int i = 0; i < size; i++) {
                pq.add(scoville[i]);
            }

            int count = 0;

            while(pq.size() >= 2) {
                int first = pq.remove();

                if (first >= K) {
                    pq.add(first);
                    break;
                }

                int second = pq.remove();

                int mix = first + (second * 2);
                count++;
                pq.add(mix);
            }

            // 완전히 비었거나 첫 번째 가 K 미만인 경우
            if (pq.isEmpty() || pq.remove() < K) {
                return -1;
            }


            return count;
        }
    }
}
