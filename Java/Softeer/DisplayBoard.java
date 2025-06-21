package Softeer;

// O(T * L^2  )
// insert 가 비효율적

import java.util.*;
import java.io.*;
public class DisplayBoard {

    static int T;
    
    
    // 변경할 때 필요한 수를 세줌
    // from to 순으로 사용
    // 두 번 안 찾게 저장 하기
    static int[][] number = new int[11][7];
    static  char x_value = '9' + 1;
    public static int change(int from, int to) {

        int result = 0;

        for(int i = 0; i < 7; i++) {
            if (number[from][i] != number[to][i])
                result++;
        }

        return result;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        List<Integer> results = new ArrayList<Integer>();


        // 숫자 번호들 지정
        // 10은 완전히 비어 있는 칸
        number[0] = new int[]{1, 1, 1, 0, 1, 1, 1};
        number[1] = new int[]{0,0,1,0,0,1,0};
        number[2] = new int[]{1,0,1,1,1,0,1};
        number[3] = new int[]{1,0,1,1,0,1,1};
        number[4] = new int[]{0,1,1,1,0,1,0};
        number[5] = new int[]{1,1,0,1,0,1,1};
        number[6] = new int[]{1,1,0,1,1,1,1};
        number[7] = new int[]{1,1,1,0,0,1,0};
        number[8] = new int[]{1,1,1,1,1,1,1};
        number[9] = new int[]{1,1,1,1,0,1,1};
        number[10] = new int[]{0,0,0,0,0,0,0};

        
        // T 만큼 반복 진행
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String from_temp = st.nextToken();
            String to_temp = st.nextToken();

            int diff = from_temp.length() - to_temp.length();

            StringBuilder from = new StringBuilder(from_temp);
            StringBuilder to   = new StringBuilder(to_temp);

            // from 이 더 길시
            if (diff > 0) {
                for (int j = 0; j < diff; j++)
                    to.insert(0, x_value);
            }

            else {
                for(int j = 0; j < (-diff); j++)
                    from.insert(0, x_value);
            }

            int result = 0;
            int length = from.length();

            for(int j = 0; j < length; j++) {
                result += change(from.charAt(j) - '0',to.charAt(j) - '0');
            }
            results.add(result);
        }

        for(int i : results)
            System.out.println(i);
    }

}
