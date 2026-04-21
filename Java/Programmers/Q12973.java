package Programmers;
import java.io.*;
import java.util.*;
public class Q12973 {


// 100만임. => 단순 While 반복으로는 시간 초과 발생함..
// 실제 자르지는 말고 진행..? 걍 Stack 으로 ㄱㄱ 그러면 O(N) 으로 될 듯.. 뭐 했는지;;

    class Solution
    {
        public int solution(String s)
        {
            int answer = -1;

            Stack<Character> stack = new Stack<Character>();

            for (int i = 0; i < s.length(); i++) {

                char cur = s.charAt(i);

                // 비어있을 시는 그냥 넣음
                if (stack.isEmpty()) {
                    stack.add(cur);
                    continue;
                }

                if (stack.peek() == cur) {
                    stack.pop();
                }

                else {
                    stack.add(cur);
                }
            }

            if (!stack.isEmpty()) return 0;

            return 1;
        }
    }
}
