package Softeer;
import java.util.*;
import java.io.*;

// 무난한 원트

public class PlayFair {
    public static String message;
    public static String key;

    public static char[][] board = new char[5][5];
    public static int[] alphabet = new int[29];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        message = br.readLine();
        key = br.readLine();
        
        // 표 생성
        
        int board_index = 0;
        for(int i = 0; i < key.length(); i++) {
            char cur = key.charAt(i);

            // 이미 들어간 경우 continue
            if(alphabet[cur - 'A'] == 1) continue;

            board[board_index / 5][board_index % 5] = cur;
            alphabet[cur - 'A'] = 1;
            board_index++;
        }

        char alphabet_index = 'A';

        for(; board_index < 25; board_index++) {
            while(alphabet[alphabet_index - 'A'] != 0 || alphabet_index == 'J')
                alphabet_index++;



            board[board_index / 5][board_index % 5] = alphabet_index;
            alphabet[alphabet_index - 'A'] = 1;
        }

        // Message Split
        ArrayList<String> splited_message = new ArrayList<String>();

        StringBuilder cur = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            cur.append(message.charAt(i));

            // divide
            if (cur.length() >= 2) {

                // 둘이 XX 인 경우
                if(cur.toString().equals("XX")) {
                    splited_message.add("XQ");
                    cur = new StringBuilder("X");
                }
                
                // 둘이 같은 경우 X 삽입
                else if(cur.charAt(0) == cur.charAt(1)) {
                    String cur_char = cur.substring(0, 1);

                    String first = cur_char + "X";
                    splited_message.add(first);

                    cur = new StringBuilder(cur_char);
                }

                else {
                    splited_message.add(cur.toString());
                    cur = new StringBuilder();
                }
            }
        }

        if (cur.length() > 0) {
            splited_message.add(cur.append("X").toString());
        }

        // 암호화
        StringBuilder result = new StringBuilder();

//        for (int i = 0; i < 5; i++) {
//            for(int j = 0; j < 5; j++) {
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }


        for (String splited : splited_message) {
            // 같은 행 존재 확인
            boolean success = false;

            char first = splited.charAt(0);
            char second = splited.charAt(1);

            for(int i = 0; i < 5; i++) {

                int check1 = -1;
                int check2 = -1;

                for(int j = 0; j < 5; j++) {
                    if (board[i][j] == first) {
                        check1 = j;
                    }

                    if(board[i][j] == second) {
                        check2 = j;
                    }
                }
                
                // 둘 다 같은 행 내부에 존재함
                if(check1 != -1 && check2 != -1) {
                    StringBuilder temp = new StringBuilder();
                    temp.append(board[i][(check1 + 1) % 5]);
                    temp.append(board[i][(check2 + 1) % 5]);
                    result.append(temp);
                    success = true;
                    break;
                }
            }
            
            // 같은 열 존재 확인
            if (success) {
                continue;
            }
            // 다른 행, 다른 열 존재
            for(int j = 0; j < 5; j++) {

                int check1 = -1;
                int check2 = -1;

                for(int i = 0; i < 5; i++) {
                    if (board[i][j] == first) {
                        check1 = i;
                    }

                    if(board[i][j] == second) {
                        check2 = i;
                    }
                }

                // 둘 다 같은 열 내부에 존재함
                if(check1 != -1 && check2 != -1) {
                    StringBuilder temp = new StringBuilder();
                    temp.append(board[(check1 + 1) % 5][j]);
                    temp.append(board[(check2 + 1) % 5][j]);
                    result.append(temp);
                    success = true;
                    break;
                }
            }

            if (success) continue;

            int check1_row = -1;
            int check1_column = -1;

            int check2_row = -1;
            int check2_column = -1;

            // 같은 열, 같은 행에 없음
            for(int i = 0; i < 5; i++) {
                for(int j = 0; j < 5; j++) {
                    if (board[i][j] == first) {
                        check1_row =i;
                        check1_column = j;
                    }

                    if(board[i][j] == second) {
                        check2_row = i;
                        check2_column = j;
                    }
                }

                // 둘 다 찾음
                if(check1_row != -1 && check2_row != -1) {
                    StringBuilder temp = new StringBuilder();
                    temp.append(board[check1_row][check2_column]);
                    temp.append(board[check2_row][check1_column]);
                    result.append(temp);
                    success = true;
                    break;
                }
            }
        }

        System.out.println(result.toString());


        br.close();
    }
}
