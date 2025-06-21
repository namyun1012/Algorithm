package Softeer;
import java.io.*;
import java.util.*;


// O (N log N * L ) 사실 상 처음의 sort 만 필요
public class ReserveRoom {
    static int N, M;
    static Map<String, Integer> map = new HashMap<String, Integer>();
    static int[][] rooms;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        String[] temp = new String[N];
        rooms = new int[N][19];
        // 정렬해서 집어 Map 으로 집어 넣음
        for(int i = 0; i < N; i++) {
            temp[i] = br.readLine();
        }
        Arrays.sort(temp);
        
        for(int i = 0; i < N; i++) {
            map.put(temp[i], i);
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int start = Integer.parseInt(st.nextToken());
            int end   = Integer.parseInt(st.nextToken());
            int index = map.get(name);
            
            // rooms[index][9] 는 9 ~ 10 을 의미함
            for(int j = start; j < end; j++) {
                rooms[index][j] = 1;
            }
        }

        for(int i = 0; i < N; i++) {
            // 경계 사이에서만 들어가는 코드
            if (i != 0)
                System.out.println("-----");
            // Room 이름
            System.out.println("Room " + temp[i] + ":");

            // 각 room 마다 찾음
            List<String> result = new ArrayList<String>();

            for(int j = 9; j <= 17; j++) {
                // 0일 경우 간격이 어디까지 인지 찾음
                if(rooms[i][j] == 0) {
                    int k;

                    for(k = j; k <= 17; k++) {
                        if(rooms[i][k] == 1)
                            break;
                    }
                    
                    // j가 10 보다 작을 시 0을 붙여줘야 한다.
                    StringBuilder sb = new StringBuilder();
                    
                    if (j < 10)
                        sb.append(0);

                    sb.append(j + "-" + k);
                    result.add(sb.toString());
                    j = k;
                }
            }

            if(result.isEmpty()) {
                System.out.println("Not available");
            }

            else {
                System.out.println(result.size() + " available:");
                for(String str : result) {
                    System.out.println(str);
                }
            }

        }
        
        // 종료 후 br 닫기
        br.close();
    }


}
