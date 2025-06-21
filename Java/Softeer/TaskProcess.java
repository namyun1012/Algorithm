package Softeer;
import java.io.*;
import java.util.*;

// 현재 Runtime Error 발생중
// 이상한 Memory 에 접근하는 듯?
// 코드 실수
// O 2^H*R*H)
public class TaskProcess {
    public static int H;
    public static int K;
    public static int R;

    public static int[][] tasks;
    public static int result = 0;
    public static class Personnel {
        int index;
        Queue<Integer> left_queue = new ArrayDeque<Integer>();
        Queue<Integer> right_queue = new ArrayDeque<Integer>();
        public Personnel(int index) {
            this.index = index;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        int numbers = (int)Math.pow(2, H);
        tasks = new int[numbers][K];

        // i 번째 말단 직원
        for (int i = 0; i < numbers; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < K; j++) {
                tasks[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int t = 1;

        Personnel[][] tree = new Personnel[H][numbers];

        int len = 1;
        for(int i = 0; i <= H-1; i++) {
            for(int j = 0; j < len; j++) {
                tree[i][j] = new Personnel(j);
            }
            len *= 2;
        }

        // Leaf 하고 Non-Leaf, root 나누기
        while (t <= R) {
            int cur_process = t % 2;
            // first root 처리
            Personnel cur = tree[0][0];

            if (cur_process == 1) {
                if(!cur.left_queue.isEmpty()) {
                    int task = cur.left_queue.remove();
                    result += task;
                }
            }

            else {
                if(!cur.right_queue.isEmpty()) {
                    int task = cur.right_queue.remove();
                    result += task;
                }
            }
            
            // 나머지 상사들 처리
            int cur_len = 2;
            for(int i = 1; i <= H - 1; i++) {
                for(int j = 0; j < cur_len; j++) {
                    cur = tree[i][j];
                    
                    // 오늘 수행한 Task 설정
                    Integer cur_task = null;

                    if (cur_process == 1) {
                        if(!cur.left_queue.isEmpty()) {
                            cur_task = cur.left_queue.remove();
                        }
                    }

                    else {
                        if(!cur.right_queue.isEmpty()) {
                            cur_task = cur.right_queue.remove();
                        }
                    }

                    if (cur_task != null) {
                        // left
                        if (j % 2 == 0) {
                            tree[i - 1][j / 2].left_queue.add(cur_task);
                        }

                        else {
                            tree[i - 1][j / 2].right_queue.add(cur_task);
                        }
                    }
                }

                cur_len *= 2;
            }
            if (t - 1 < K) {
                // 말단 직원들 처리
                for (int i = 0; i < numbers; i++) {
                    if (i % 2 == 0) {
                        tree[H - 1][i / 2].left_queue.add(tasks[i][t - 1]);
                    } else {
                        tree[H - 1][i / 2].right_queue.add(tasks[i][t - 1]);
                    }
                }
            }
            t++;
        }

        System.out.println(result);
    }
}
