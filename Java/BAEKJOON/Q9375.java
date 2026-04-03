import java.io.*;
import java.util.*;

// 아래의 backtracking 식을 계산할 필요가 있음

public class Q9375 {

    static int T;
    static int N;
    static HashMap<String, Integer> clothes;
    static int[] arr;
    static int lenght;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            clothes = new HashMap<>();
            result = 0;

            N = Integer.parseInt(br.readLine());


            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String clothe = st.nextToken();
                String type = st.nextToken();

                if(clothes.containsKey(type)) {
                    clothes.put(type, clothes.get(type) + 1);
                }

                else {
                    clothes.put(type, 1);
                }
            }
            
            // HashMap 생성됨
            arr = new int[clothes.keySet().size()];

            result = 1;
            for (String type : clothes.keySet()) {
                result *= (clothes.get(type) + 1);
            }


            System.out.println(result - 1);
        }

        br.close();
    }
}
