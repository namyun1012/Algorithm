package Softeer;

import java.io.*;
import java.util.*;

// N 은 최대 10만
// 시간 복잡도 고려
// 일단 누적합 방식 고려?
// 대회가 3개임, 시간 초과 중임
// 알고리즘 고려해 볼 것, Sort 는 필요 없다. => 그래도 시간 초과
// 문제를 잘 읽자
// O(N)

public class EvaluateScore {
    public static int N;
    public static class Score implements Comparable<Score> {
        int score, index;
        int rank;


        public Score(int score, int index) {
            this.score = score;
            this.index = index;
        }

        public int compareTo(Score s) {
            return Integer.compare(this.score, s.score);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());


        int[] sum = new int[N];

        for (int i = 0; i < 3; i++) {
            Score[] arr = new Score[N];
            int[] scores = new int[1001];
            int[] ranks = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int score = Integer.parseInt(st.nextToken());
                arr[j] = new Score(score, j);
                scores[score] += 1;
                sum[j] += score;
            }

            for (int j = 1; j <= 1000; j++) {
                scores[j] = scores[j - 1] + scores[j];
            }

            // ranking 을 설정함
            for(int j = 0; j < N; j++) {
                Score cur = arr[j];
                cur.rank = (N - scores[cur.score] + 1);
                ranks[cur.index] = cur.rank;
            }

            for(int j = 0; j < N; j++) {
                bw.write(ranks[j] + " ");
            }
            bw.newLine();
        }

        int[] ranks = new int[N];
        int[] scores = new int[3001];

        for (int i = 0; i < N; i++) {
           scores[sum[i]] += 1;
        }

        for (int j = 1; j <= 3000; j++) {
            scores[j] = scores[j - 1] + scores[j];
        }

        for(int j = 0; j < N; j++) {
            int cur = sum[j];
            ranks[j] = N - scores[cur] + 1;
        }

        for(int j = 0; j < N; j++) {
            bw.write(ranks[j] + " ");
        }
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }


}
