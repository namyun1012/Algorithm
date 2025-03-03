// 암호 만들기
// String builder 활용에 주의해야 했던 문제

import java.io.*;
import java.util.*;

public class Q1759 {
    static int L, C;

    static char[] board;
    
    static List<String> result = new ArrayList<String>();

    
    // a 는 자음 b 는 모음
    public static void backtracking(int a, int b, String str, int index) {
        // 현재 String 이 L 인 경우 체크
        if(str.length() == L) {

            if (a  >= 2 && b >= 1) {
                result.add(str);
            }

            return;
        }


        
        // 자음, 모음, 변형한 str, 추가한 char 의 index 를 가져다 줌
        for (int i = index; i < C; i++) {
            char cur_char = board[i];

            StringBuilder next = new StringBuilder(str);
            next.append(cur_char);

            // 모음 일 경우
            if(cur_char == 'a' || cur_char == 'e' || cur_char == 'i' || cur_char == 'o' || cur_char == 'u') {
                backtracking(a, b+1, next.toString(), i + 1);
            }
            
            // 자음일 경우
            else {
                backtracking(a + 1, b, next.toString(), i + 1);
            }
        }

        return;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
           board[i] = st.nextToken().charAt(0);
        }

        // Sorting 을 진행해 준다.
        for(int i = 0; i < C; i++) {
            for(int j = i; j < C; j++) {
                if(board[i] > board[j]) {
                    char temp = board[i];
                    board[i] = board[j];
                    board[j] = temp;
                }
            }
        }

        backtracking(0,0,"",0);

        for (String s : result) {
            System.out.println(s);
        }
    }
}
