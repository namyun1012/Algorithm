package Programmers;
import java.io.*;
import java.util.*;

public class Q42627 {


    class Task implements Comparable<Task> {
        int cost, required, num;
        int end;

        public Task(int cost, int required, int num) {
            this.cost = cost;
            this.required = required;
            this.num = num;
        }

        public int compareTo(Task o) {
            if (this.cost != o.cost) return this.cost - o.cost;

            if (this.required != o.required) return this.required - o.required;

            return this.num - o.num;
        }
    }


    class Solution {
        public int solution(int[][] jobs) {

            int size = jobs.length;

            List<Task> tasks = new ArrayList<Task>();
            PriorityQueue<Task> schedule= new PriorityQueue<Task>();

            int t = 0; // 시간
            int completed = 0; // 완료한 일의 수

            int work_remain   = 0; // 해당 업무의 남은 시간
            int work_idx    = -1; // -1 일시 일 안하는 중
            Task current_task = null; // 현재 진행 중인 업무

            while(completed < size) {

                // 1. 요청 받기
                for (int i = 0; i < size; i++) {
                    int required = jobs[i][0];
                    int cost     = jobs[i][1];

                    if (t == required) {
                        Task task = new Task(cost, required, i);
                        tasks.add(task);
                        schedule.add(task);
                    }
                }

                // 2. 일 처리. 일 안하고 있을 시 일 가져옴
                if (work_remain ==  0) {
                    // 기존 work 완료 -> end time 기록
                    if (current_task != null) {
                        current_task.end = t;
                        current_task = null;

                        completed++;
                    }

                    Task task = schedule.poll();

                    if (task != null) {
                        work_remain     = task.cost;
                        work_idx        = task.num;
                        current_task    = task;

                        System.out.println("Current Work : " + work_idx + "  Time :" + t);
                    }
                }

                // 일함
                if (work_remain > 0) work_remain -= 1;

                t++;
            }

            int result = 0;
            for (Task task : tasks) {
                result += (task.end - task.required);
            }

            System.out.println(t);

            return (result / size);
        }
    }
}
