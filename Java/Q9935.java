import java.util.*;
import java.io.*;

// 일일이 비교하는 방식은 힘들고, Stack 에 넣어서 확인을 해야 할 듯?

public class Q9935 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String string = br.readLine();
        String explosion = br.readLine();

        Stack<Character> stack = new Stack<Character>();
        Stack<Character> temp  = new Stack<Character>();

        int string_len = string.length();
        int explosion_len = explosion.length();

        char explosion_last = explosion.charAt(explosion_len - 1);

        for (int i = 0; i < string_len; i++) {
            temp.clear();

            char cur = string.charAt(i);
            stack.push(cur);

            // explosion 의 마지막 글자일시 검사 진행
            if (cur == explosion_last) {
                // 사이즈 작을 시 확인 불 필요
                if(stack.size() < explosion_len)
                    continue;
                
                for (int j = 0; j < explosion_len; j++) {
                    char cur_j = stack.pop();
                    temp.push(cur_j);
                    
                    // 하나라도 explosion 아니면 다시 stack 에 집어넣음
                    if (cur_j != explosion.charAt(explosion_len - 1 - j)) {

                        // 실수 주의하기
                        while (!temp.isEmpty()) {
                            stack.push(temp.pop());
                        }

                        break;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (Character ch : stack) {
            sb.append(ch);
        }
        String result = sb.toString();

        if(result.isEmpty()) {
            bw.write("FRULA");
        }
        else  {
            bw.write(result);
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
