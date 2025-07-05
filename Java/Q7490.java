import java.io.*;
import java.util.*;

// 바로 정답
// 백트래킹 보다는 구현이 어려웠던 듯?

public class Q7490 {

    static int T;
    static int N;
    static int[] arr;
    static List<String> results;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            results = new ArrayList<String>();


            for (int i = 0; i < N; i++) {
                arr[i] = i + 1;
            }

            backtracking(0, "1");

            for (String s : results) {
                bw.write(s);
                bw.write("\n");
            }
            bw.write("\n");
        }


        bw.flush();
        bw.close();
        br.close();
    }

    public static void backtracking(int index, String str) {
        
        // 0이 되는 지 검사 필요
        if(index == N - 1) {
            if(check(str)) results.add(str);
            return;
        }

        int new_ele = index + 2;
        for (int i = 0; i < 3; i++) {
            StringBuilder new_str = new StringBuilder(str);

            switch (i) {
                // +
                case 1:
                    new_str.append("+");
                    new_str.append(new_ele);
                    break;
                case 2:
                    new_str.append("-");
                    new_str.append(new_ele);
                    break;
                case 0:
                    new_str.append(" ");
                    new_str.append(new_ele);
                    break;
            }

            backtracking(index + 1, new_str.toString());

        }
    }

    public static boolean check(String str) {
        int value = -1;

        int temp = -1;
        char sign = 0;

        for (int i = 0; i < str.length(); i++) {
            char cur = str.charAt(i);
            
            // 숫자인 경우, 기호가 올 때 까지 찾음
            if(cur >= '0' && cur <= '9') {
                int num = 0;
                int j = i;

                while (j < str.length()) {
                    char nxt = str.charAt(j);

                    if (nxt >= '0' && nxt <= '9') {
                        num += nxt - '0';
                    }

                    else if (nxt == ' ') {
                        num *= 10;
                    }
                    
                    // 부호 만남
                    else {
                        j -= 1;
                        break;
                    }
                    j++;
                }

                if (sign == 0) {
                    value = num;
                }

                else {
                    temp = num;

                    if (sign == '+') {
                        value += temp;
                        temp = 0;
                    } else if (sign == '-') {
                        value -= temp;
                        temp = 0;
                    }
                }
                i = j;
            }

            else if(cur == '+') {
                sign = '+';
            }

            else if(cur == '-') {
                sign = '-';
            }
        }

        if (value == 0) return true;
        return false;

    }
}
