package Programmers;
import java.io.*;
import java.util.*;
public class 서버증설횟수 {
    class Solution {
        public int solution(int[] players, int m, int k) {
            int N = 24;
            int answer = 0;

            int[] servers = new int[N];

            for (int i = 0; i < N; i++) {
                int player = players[i];
                int server = servers[i];

                int need = player / m;

                // 서버 증설 필요
                if (need > server) {
                    int additional_server = need - server;

                    for (int j = 0; j < k && i + j < N; j++) {
                        servers[i + j] += additional_server;
                    }
                    answer += additional_server;
                }
            }

            return answer;
        }
    }
}
