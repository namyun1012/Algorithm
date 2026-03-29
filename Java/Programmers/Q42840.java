package Programmers;
import java.io.*;
import java.util.*;

public class Q42840 {

    class Solution {
        public int[] solution(int[] answers) {
            int size = answers.length;

            int score_1 = 0;
            int score_2 = 0;
            int score_3 = 0;

            int[] answer_1 = new int[] {1, 2, 3, 4, 5};
            int[] answer_2 = new int[] {2, 1, 2, 3, 2, 4, 2, 5};
            int[] answer_3 = new int[] {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

            int size_1 = answer_1.length;
            int size_2 = answer_2.length;
            int size_3 = answer_3.length;

            for (int i = 0; i < size; i++) {
                int answer_cur = answers[i];

                if (answer_cur == answer_1[i % size_1]) score_1++;
                if (answer_cur == answer_2[i % size_2]) score_2++;
                if (answer_cur == answer_3[i % size_3]) score_3++;
            }

            int max = Math.max(score_1, score_2);
            max = Math.max(max, score_3);
            max = Math.max(score_2, max);

            List<Integer> result = new ArrayList<Integer>();

            if (score_1 == max) result.add(1);
            if (score_2 == max) result.add(2);
            if (score_3 == max) result.add(3);

            int[] arr = new int[result.size()];

            for (int i = 0; i < result.size(); i++) arr[i] = result.get(i);
            return arr;
        }
    }
}
