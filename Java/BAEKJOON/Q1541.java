import java.io.*;
import java.util.*;
/*
 - 나온 이후에는 전부다 빼는 방식으로 진행하면 해결
 */

public class Q1541 {

    static String str;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();

        int result = 0;
        int temp = -1;
        char signal = 'X';
        
        
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            
            // 숫자일시
            if(c >= '0' && c <= '9') {
                if (temp == -1) {
                    temp = c - '0';
                }

                else {
                    temp *= 10;
                    temp += (c - '0');
                }
            }

            // 기호 나옴
            else {
                // 초기 상태
                if (signal == 'X') {
                    result = temp;
                    temp = -1;
                    signal = c;
                }

                else {
                    if (signal == '+') {
                        result += temp;
                        temp = -1;
                    }

                    else if(signal == '-') {
                        result -= temp;
                        temp = -1;
                    }
                    
                    if (c == '-') {
                        signal = '-';
                    }

                }
            }
        }

        if (signal == '-') {
            result -= temp;
        }
        else {
            result += temp;
        }

        System.out.println(result);
        br.close();
    }

}
