package Programmers;
import java.io.*;
import java.util.*;

// 살짝 특이한 문제?
public class Q42842 {


// 가로는 언제나 세로 보다 크거나 같음
// brown 은 8 이상 5 이하


    class Solution {
        public int[] solution(int brown, int yellow) {
            int total = brown + yellow;
            int[] answer = {};
            int max_length = total / 3;

            for (int i = 3; i <= max_length; i++) {
                if (total % i != 0) continue;

                int j = total / i;

                // check 진행
                if ((i - 2) * (j - 2) == yellow) answer = new int[] {i, j};
            }

            return answer;
        }
    }
}
