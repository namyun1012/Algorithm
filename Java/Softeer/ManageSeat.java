package Softeer;
import java.util.*;
import java.io.*;
public class ManageSeat {

    static int N, M, Q;
    static int[][] seat;
    static int[][] visited;
    static int[][] distance;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static Map<Integer, Coord> map = new HashMap<Integer, Coord>();;
    static int cur_eat = 0;
    static List<String> result_list = new ArrayList<String>();


    static class Coord {
        int x;
        int y;

        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void inSeat(int id) {

        Coord old = map.get(id);
        // 이미 한 번 배정을 받은 경우
        if(old != null) {
            // 이미 나감
            if(old.x == -1) {
                result_list.add(id + " already ate lunch.");
            }

            else {
                result_list.add(id + " already seated.");
            }

            return;
        }

        Coord result_coord = new Coord(0, 0);
        // 모든 좌석이 텅 비어 있는 경우
        if(cur_eat == 0) {
            result_coord = new Coord(1, 1);
        }
        
        // 좌석 찾기
        // 전체 BFS는 아닌 듯 각 스타트 지점에서 distance 를 매기고 갱신한 후 에 최종 최대 값을 찾는 방법으로 진행
        else {
            
            // distance 를 우선 MAX_VALUE 로 설정
            for (int i = 1; i <= N; i++) {
                for(int j = 1; j <= M; j++) {
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }

            // 이미 있는 것들 넣어놓기
            for (Coord coord : map.values()) {
                // 이미 뺀 것
                if(coord.x == -1) continue;

                // 이미 먹고 있는 부분은 무조건 0
                distance[coord.x][coord.y] = 0;
                
                // 제일 최소 거리로 distance를 갱신해 놓음
                for (int i = 1; i <= N; i++) {
                    for(int j = 1; j <= M; j++) {
                        distance[i][j] = Math.min(distance[i][j], (coord.x - i) * (coord.x - i) + (coord.y - j) * (coord.y -j));
                    }
                }
            }

            int max_distance = 0;
            int result_x = 0;
            int result_y = 0;

            for (int i = 1; i <= N; i++) {
                for(int j = 1; j <= M; j++) {
                    int cur_distance = distance[i][j];
                    if(cur_distance > max_distance) {
                        max_distance = cur_distance;
                        result_x = i;
                        result_y = j;
                    }
                }
            }
            // MAX distance 가 1 보다 커야 함
            if(max_distance > 1) {
                result_coord = new Coord(result_x, result_y);
            }
            
            // 1 과 같거나 작을 시 0 을 넣어서 밑에서 실패 처리
            else {
                result_coord = new Coord(0,0);
            }
        }

        // 좌석 못 찾음
        if (result_coord.x == 0) {
            result_list.add("There are no more seats.");
        }
        
        // 좌석 찾음
        else {
            map.put(id, result_coord);
            seat[result_coord.x][result_coord.y] = id;
            cur_eat += 1;
            result_list.add(id + " gets the seat (" + result_coord.x + ", " + result_coord.y + ").");
        }
    }

    public static void outSeat(int id) {
        Coord old = map.get(id);
        
        // 먹은 적이 없음
        if (old == null) {
            result_list.add(id + " didn't eat lunch.");
            return;
        }
        
        // 먹고 떠남
        if (old.x == -1) {
            result_list.add(id + " already left seat.");
            return;
        }

        result_list.add(id + " leaves from the seat (" + old.x + ", " + old.y + ").");
        seat[old.x][old.y] = 0;
        map.replace(id, new Coord(-1, -1));
        cur_eat -= 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        seat        = new int[N + 1][M + 1];
        visited     = new int[N + 1][M + 1];
        distance    = new int[N + 1][M + 1];


        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            String query = st.nextToken();
            int id = Integer.parseInt(st.nextToken());

            if(query.equals("In")) {
                inSeat(id);
            }

            else if(query.equals("Out")) {
                outSeat(id);
            }
        }

        for (String result : result_list) {
            System.out.println(result);
        }


        br.close();
    }

}
