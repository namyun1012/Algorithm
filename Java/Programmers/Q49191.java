package Programmers;
import java.io.*;
import java.util.*;
public class Q49191 {



// 위상 정렬? =>
// 순서가 명확한 것들에 대한 구별 필요..
// 동일 위상에 여러 개 있는 것들은 제외 방식으로 가능할까..?
// 우선 그래프는 results
// => 플로이드 워셜로;;


    class Solution {

        static int N;
        static int M;

        public int solution(int n, int[][] results) {

            N = n;
            M = results.length;

            int[][] graphs = new int[N + 1][N + 1];

            for (int i = 0; i < M; i++) {
                int[] cur = results[i];

                graphs[cur[0]][cur[1]] = 1;
            }


            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        if(graphs[i][k] == 1 && graphs[k][j] == 1) {
                            graphs[i][j] = 1;
                        }
                    }
                }
            }

            int answer = 0;

            for (int i = 1; i <= N; i++) {
                int count = 0;

                for (int j = 1; j <= N; j++) {
                    if (graphs[i][j] == 1 || graphs[j][i] == 1) count++;
                }

                if (count == N - 1) answer++;
            }



            return answer;
        }
    }
}
