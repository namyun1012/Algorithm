import java.io.*;
import java.util.*;

// BFS 형식으로 풀어 보기 -> 안됨
// 그냥 bit 수를 세는 형식으로 해야 풀리는 듯


public class Q22973 {

    static long K;

    static Map<Long, Long> map = new HashMap<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Long.parseLong(br.readLine());
        K = Math.abs(K);

        if (K == 0)
            System.out.println(0);
        else if(K % 2 == 0)
            System.out.println(-1);
        else {
            long count = 0;
            while (K > 0) {
                K = K >> 1;
                count += 1;
            }

            System.out.println(count);
        }


        br.close();
    }
}
