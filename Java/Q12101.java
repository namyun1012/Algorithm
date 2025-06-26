import java.io.*;
import java.util.*;

// n 은 양수이고 11 보다 작음
// 수를 1개 이상 사용해야 함
// 정답, 3 이하 일때 index 고려


public class Q12101 {

    static int N;
    static int K;

    static List<String>[] arr;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new List[12];
        for (int i = 0; i <= 11; i++) {
            arr[i] = new ArrayList<String>();
        }


        arr[1].add("1");
        arr[2].add("1+1");
        arr[2].add("2");
        arr[3].add("1+1+1");
        arr[3].add("1+2");
        arr[3].add("2+1");
        arr[3].add("3");


        for (int i = 4; i <= N; i++) {

            for(int j = 0; j < arr[i - 3].size(); j++) {
                arr[i].add(arr[i-3].get(j) + "+3");
            }

            for(int j = 0; j < arr[i - 2].size(); j++) {
                arr[i].add(arr[i-2].get(j) + "+2");
            }

            for(int j = 0; j < arr[i - 1].size(); j++) {
                arr[i].add(arr[i-1].get(j) + "+1");
            }
        }

        Collections.sort(arr[N]);

        if(K > arr[N].size()) {
            System.out.println(-1);
        }

        else {
            System.out.println(arr[N].get(K - 1));
        }

        br.close();
    }
}
