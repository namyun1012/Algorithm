import java.io.*;
import java.util.*;

// P 들을 찾은 이후에 계산?
// 아래 코드는 정답을 기대하긴 어려울 듯
// 마지막 껏 더하는 것 추가 후 정답

public class Q5525 {

    static int N;
    static int M;
    static String S;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        S = br.readLine();

        List<Integer> list = new ArrayList<>();

        char prev_2 = 'X';
        char prev = 'X';
        int len = 0;


        for (int i = 0; i < M; i++) {
            char cur = S.charAt(i);
            
            // 초기화
            if (prev_2 == 'X') {
                prev_2 = cur;
                continue;
            }

            //
            if (prev == 'X') {
                prev = cur;
                continue;
            }
            
            // IOI
            if(prev_2 == 'I' && prev == 'O' && cur == 'I') {
                len++;
                prev_2 = prev;
                prev = cur;
            }


            else if(prev_2 == 'O' && prev == 'I' && cur == 'O' && len > 0) {
                prev_2 = prev;
                prev = cur;
            }

            else {
                prev_2 = prev;
                prev = cur;

                if (len > 0) {
                    list.add(len);
                }

                len = 0;
            }
        }

        if (len > 0) list.add(len);

        int result = 0;
        for (int ele : list) {
            if (ele - N  + 1> 0) result += (ele - N + 1);
        }

        System.out.println(result);

    }
}
