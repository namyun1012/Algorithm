import java.io.*;
import java.util.*;

// backtracking 문제

public class Q15666 {
    static int N;
    static int M;
    static List<Integer> arr;
    static int length = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new ArrayList<Integer>();
        
        // 중복 숫자 제외 용도
        boolean[] check = new boolean[10_001];

        st = new StringTokenizer(br.readLine());


        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            
            // 있는 경우
            if(check[temp]) {
                continue;
            }

            else {
                check[temp] = true;
                arr.add(temp);
                length++;
            }
        }

        Collections.sort(arr);

        backtracking(0, new ArrayList<>(), 0);

        br.close();
    }


    static void backtracking(int index, List<Integer> list, int prev) {

        if (index == M) {
            for (int ele : list) {
                System.out.print(ele + " ");
            }
            System.out.println();
            return;
        }

        for (int i = prev; i < arr.size(); i++) {
            list.add(arr.get(i));
            backtracking(index + 1, list, i);
            list.remove(index);
        }
    }
}
