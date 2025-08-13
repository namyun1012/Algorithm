package Programmers;
import java.io.*;
import java.util.*;

// 금방 풀 것 같았는데 의외로 오래 걸린 문제
public class Q12951 {


    class Solution {
        static char TEMP = 'a' - 'A';

        public String solution(String s){

            int n = s.length();
            boolean new_word = true;
            String answer = "";

            for (int i = 0; i < n; i++) {
                char cur = s.charAt(i);

                // 공백
                if (cur == ' ') {
                    answer += cur;
                    new_word = true;
                }

                // 대문자인 경우
                else if (cur >= 'A' && cur <= 'Z') {
                    if (new_word) {
                        answer += cur;
                    }
                    else {
                        answer += (char) (cur + TEMP);
                    }
                    new_word = false;
                }

                else if(cur >= 'a' && cur <= 'z') {
                    if (new_word) {
                        answer += (char) (cur - TEMP);
                    }
                    else {
                        answer += cur;
                    }
                    new_word = false;
                }

                else {
                    answer += cur;
                    new_word = false;
                }

            }


            return answer;
        }
    }
}
