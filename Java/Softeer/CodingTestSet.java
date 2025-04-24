package Softeer;
import java.io.*;
import java.util.*;
// Use Parametric Search
// Long 쓰면 parseLong 기억 하자!
public class CodingTestSet {

    public static int N;
    public static int T;

    public static long[] c;
    public static long[] d;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        // Initialize
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            c = new long[N + 1];
            d = new long[N + 1];

            long max = 0;
            long min = Long.MAX_VALUE;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N - 1 ; i++) {
                c[i] = Long.parseLong(st.nextToken());
                d[i] = Long.parseLong(st.nextToken());

                max += c[i] + d[i];
                min = Math.min(c[i], min);
            }

            c[N-1] = Long.parseLong(st.nextToken());

            max += c[N-1];
            min = Math.min(c[N-1], min);

            // Parametric Search 를 사용한 이진 탐색
            max /= N;
            max += 1;

            long result = 0;

            while(min <= max) {
                long mid = (max + min) / 2;

                //testing 가능한 지 확인
                long[] c_test = c.clone();
                long[] d_test = d.clone();

                boolean test = true;

                for(int i = 0; i < N; i++) {
                    // 그냥 많을 시
                    if (c_test[i] >= mid) {
                        continue;
                    }
                    
                    // d_test[i-1] 을 우선으로 빼기
                    else if(i >= 1 && c_test[i] + d_test[i] + d_test[i-1] >= mid) {
                        
                        // d[i-1] 합친 것이가 mid 보다 큰 경우
                        if (c_test[i] + d_test[i-1] >= mid) {
                            d_test[i-1] -= (mid - c_test[i]);
                        }

                        // d_test[i-1] = 0
                        else {
                            d_test[i] -= (mid - c_test[i] - d_test[i-1]);
                            d_test[i-1] = 0;
                        }
                    }
                    
                    // 첫 번째에만 적용됨
                    else if(c_test[i] + d_test[i] >= mid) {
                        d_test[i] -= (mid - c_test[i]);
                    }

                    else {
                        test = false;
                        break;
                    }
                }



                // test 를 통과 했으니 더 높여 봄
                if (test) {
                    result = Math.max(result, mid);
                    min = mid + 1;
                }

                else {
                    max = mid - 1;
                }

            }

            System.out.println(result);
        }

        br.close();
    }
}
