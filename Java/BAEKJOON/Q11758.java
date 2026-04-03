// INF 처리 해줄 것
// 기울기, + 기울기 어느 쪽으로 가는 지 고려 하기
import java.nio.Buffer;
import java.util.*;
import java.io.*;
import java.math.*;


public class Q11758 {
    public static class Coord {
        int x;
        int y;

        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Coord[] coords = new Coord[3];

        int x, y;

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            coords[i] = new Coord(x, y);
        }

        int p2_p1_x, p2_p1_y, p3_p2_x, p3_p2_y;
        p2_p1_x = coords[1].x - coords[0].x;
        p2_p1_y = coords[1].y - coords[0].y;
        p3_p2_x = coords[2].x - coords[0].x;
        p3_p2_y = coords[2].y - coords[0].y;

        float p2_p1_m = (float) p2_p1_y / p2_p1_x;
        float p3_p2_m = (float) p3_p2_y / p3_p2_x;


        if(Float.isInfinite(p2_p1_m)) {
            p2_p1_m = (float) 100_000_000;
        }

        if(Float.isInfinite(p3_p2_m)) {
            p3_p2_m = (float) 100_000_000;
        }


        // 일직선인 경우
        if (p2_p1_m == p3_p2_m) {
            System.out.println(0);
        }
        
        // 일직선 아닐 때
        else {
            // 기울기가 더 p3_p1 쪽이 더 큰 경우,
            if (p3_p2_m > p2_p1_m) {
                // p3_p1 의 기울기가 양의 방향 일 때
                if(p3_p2_x >= 0) {
                    //p2_p1 의 기울기가 양의 방향 일 떄
                    if(p2_p1_x >= 0) {
                        System.out.println(1);
                    }
                    else {
                        System.out.println(-1);
                    }
                    
                }

                // p3_p1 의 기울기가 음의 방향 일 떄
                else {
                    if(p2_p1_x >= 0) {
                        System.out.println(-1);
                    }
                    else {
                        System.out.println(1);
                    }
                }
            }
            
            // 기울기가 p3_p1 쪽이 작은 경우
            else {
                if(p3_p2_x >= 0) {
                    //p2_p1 의 기울기가 양의 방향 일 떄
                    if(p2_p1_x >= 0) {
                        System.out.println(-1);
                    }
                    else {
                        System.out.println(1);
                    }

                }

                // p3_p1 의 기울기가 음의 방향 일 떄
                else {
                    if(p2_p1_x >= 0) {
                        System.out.println(1);
                    }
                    else {
                        System.out.println(-1);
                    }
                }
            }


        }






    }
}
