package Programmers;
import java.io.*;
import java.util.*;

// Java Stack 연습용

public class Q12909 {
    class Solution {
        boolean solution(String s) {
            boolean answer = true;

            int n = s.length();


            Stack<Character> stack = new Stack();

            for (int i = 0; i < n; i++) {
                char cur = s.charAt(i);

                if (cur == '(') {
                    stack.add(cur);
                }

                // cur == ')'
                else {

                    if (stack.isEmpty()) return false;


                    char temp = stack.pop();

                    if (temp != '(') return false;

                }
            }

            if (!stack.isEmpty()) return false;

            return answer;
        }
    }
}
