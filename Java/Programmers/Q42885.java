package Programmers;
import java.io.*;
import java.util.*;

// 간단한 투포인터 문제

public class Q42885 {

// 구명 보트의 최소값

    class Solution {

        static int N;


        public int solution(int[] people, int limit) {
            N = people.length;
            int answer = 0;

            Arrays.sort(people);

            int start = 0;
            int end = N - 1;

            while(start <= end) {
                if(people[start] + people[end] > limit) {
                    answer++;
                    end--;
                }

                else {
                    answer++;
                    start++;
                    end--;
                }
            }


            return answer;
        }
    }
}
