package Softeer;
import java.util.*;
import java.io.*;

// 매 t 마다 하는 것은 무리일 수도
// 입력은 시간 순서대로 주어지는 것이 맞는 듯, 오랫만에 원트
// O(N)
public class Crossroad {

    public static int N;

    public static class Car {
        int t;
        int direct;
        int index;

        public Car(int t, int direct, int index) {
            this.t = t;
            this.direct = direct;
            this.index = index;
        }
    }


    public static Queue<Car>[] carQueue;
    public static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        result = new int[N];


        carQueue = new Queue[4];
        for (int i = 0; i < 4; i++) {
            carQueue[i] = new ArrayDeque<Car>();
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t= Integer.parseInt(st.nextToken());
            int direct= st.nextToken().charAt(0) - 'A';

            Car cur = new Car(t, direct, i);
            carQueue[direct].add(cur);
            result[i] = -1; // deadlock 용도
        }

        int time = 0;
        boolean deadlock = false;
        int passed_car = 0;
        // 시간 순으로 정렬되어서 들어왔다고 생각함
        // 처음게 일단 시간은 같거나 빠름
        
        // time 및 보낼 car 정하기
        // 시간 낮은 순 그 다음으로는 갈 수 있는 지
        while (passed_car < N) {
            Car cur;

            int min_time = Integer.MAX_VALUE;


            // min_time 정함
            for (int dir = 0; dir < 4; dir++) {
                if (!carQueue[dir].isEmpty()) {
                    int cur_time = carQueue[dir].peek().t;
                    
                    // time 보다 적은 경우에는 time 을 수정함
                    if(cur_time < time)
                        carQueue[dir].peek().t = time;

                    min_time = Math.min(carQueue[dir].peek().t, min_time);
                }
            }
            
            // 처리할 차들
            int[] sameList = new int[4];
            int same_number = 0;
            // min_time 과 같은 time 을 가진 car 들 선정
            for (int dir = 0; dir < 4; dir++) {
                if (!carQueue[dir].isEmpty()) {
                    int cur_time = carQueue[dir].peek().t;

                    if(cur_time == min_time) {
                        sameList[carQueue[dir].peek().direct] = 1;
                        same_number++;
                    }
                }
            }

            // 같은 list 에 4개가 있으면 deadlock 발생함
            if (same_number >= 4) {
                break;
            }

            time = min_time;

            for(int dir = 0; dir < 4; dir++) {
                
                // 현재 대기중 차량에서만
                if(sameList[dir] == 1) {

                    cur = carQueue[dir].peek();
                   if(sameList[(dir + 3) % 4] == 0) {
                       result[cur.index] = time;
                       carQueue[cur.direct].poll();
                       passed_car += 1;
                   }
                    
                   // 처리가 불가능 함
                   else {
                       cur.t += 1;
                   }
                }
            }
            
            // time 은 1 늘려주긴 해야 함
            time++;
        }

        for(int i = 0; i < N; i++) {
            System.out.println(result[i]);
        }


        br.close();
    }
}
