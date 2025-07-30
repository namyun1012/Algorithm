package Programmers;

import java.io.*;
import java.util.*;
public class Q178870 {
    // 현재 시간 초과 발생중
    // 연속된 부분 수열의 합
    // Sequence 는 오름차순으로 정렬 되어 있음
    // 끝에 것 계산해 본다음 안 될 것 같으면 건너뜀

    class Solution {
        public int[] solution(int[] sequence, int k) {
            int length = sequence.length;

            int[] sum = new int[length + 1];
            int[] answer = new int[2];

            sum[0] = 0;

            for (int i = 1; i <= length; i++) {
                sum[i] = sum[i - 1] + sequence[i - 1];
            }

            for (int j = 0; j < length; j++) {
                for (int i = 0; i < length && i + j < length; i++) {
                    if(sum[length] - sum[length - j - 1] < k) continue;

                    int value = sum[i + j + 1] - sum[i];

                    if (value == k) {
                        answer[0] = i;
                        answer[1] = i + j;
                        return answer;
                    }

                }
            }

            return answer;
        }
    }
}
