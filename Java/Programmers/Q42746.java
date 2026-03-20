package Programmers;

import java.io.*;
import java.util.*;
public class Q42746 {


    class Solution {
        public String solution(int[] numbers) {
            int size = numbers.length;

            String[] arr = new String[size];

            for (int i = 0; i < size; i++) {
                arr[i] = String.valueOf(numbers[i]);
            }

            Arrays.sort(arr, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));

            if (arr[size - 1 ].equals("0")) {
                return "0";
            }

            StringBuilder answer = new StringBuilder();

            for (int i = size - 1; i >= 0; i--) {
                answer.append(arr[i]);
            }

            return answer.toString();
        }
    }
}
