import java.io.*;
import java.util.*;

// 바로 정답, 자료 구조를 생각해 봐야 하는 문제

public class Q1966 {

    public static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());


        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] important = new int[10];

            st = new StringTokenizer(br.readLine());

            Queue<Integer> queue = new ArrayDeque<Integer>();

            int count = 0;
            int chase = M; // M인 것의 위치를 찾음
            int result = -1;
            //
            for(int j = 0; j < N; j++) {
                int value = Integer.parseInt(st.nextToken());
                queue.add(value);
                important[value]++;
            }

            while (!queue.isEmpty()) {
                int cur = queue.poll();

                boolean ishigher = false;
                // 자신보다 높은 것 있는 지 확인
                for(int i = cur + 1; i <= 9 ; i++) {
                    if(important[i] > 0) {
                        ishigher = true;
                        break;
                    }
                }
                
                // 자신 보다 높은 것 존재함
                // 다시 넣어야 함
                if(ishigher) {
                    queue.add(cur);

                    if(chase == 0) {
                        chase = queue.size() - 1;
                    }

                    else {
                        chase -= 1;
                    }
                }
                
                // 높은 것이 없으니 출력
                else {
                    count++;
                    important[cur] -= 1;

                    if (chase == 0) {
                        result = count;
                        break;
                    }

                    else {
                        chase -= 1;
                    }
                }
            }

            System.out.println(result);
        }
    }

}
