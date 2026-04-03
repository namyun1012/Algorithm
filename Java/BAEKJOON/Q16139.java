import java.io.*;
import java.util.*;

// System.out -> BufferedWriter 로 하니까 맞음
// 20만줄의 출력 고려

public class Q16139 {

    static String s;
    static int Q;
    static int[][] sum;
    static int[] check;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        s = br.readLine();
        Q = Integer.parseInt(br.readLine());

        sum = new int[28][s.length() + 1];
        check = new int[28];

        for (int i = 1; i <= s.length(); i++) {

            sum[s.charAt(i-1) - 'a'][i] += 1;

            for (int j = 0; j < 28; j++) {
                sum[j][i] += sum[j][i - 1];
            }
        }

        for (int q = 0; q < Q; q++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char alphabet = st.nextToken().charAt(0);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken()) + 1;

            int alpha_index = (int) (alphabet - 'a');

            bw.write(sum[alpha_index][end] - sum[alpha_index][start] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
