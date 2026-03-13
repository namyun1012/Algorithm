package Programmers;

import java.io.*;
import java.util.*;
public class Q43165 {
    class Solution {

        static int result = 0;
        static int size = 0;
        static int obj = 0;

        public int solution(int[] numbers, int target) {
            size = numbers.length;
            obj = target;

            backtracking(0, 0, numbers);

            return result;
        }


        public static void backtracking(int val, int idx, int[] numbers) {

            if (idx == size) {
                if (val == obj) result++;
                return;
            }

            int cur = numbers[idx];

            backtracking(val + cur , idx + 1, numbers);
            backtracking(val - cur , idx + 1, numbers);
        }
    }

}
