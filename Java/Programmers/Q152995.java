package Programmers;

import java.io.*;
import java.util.*;

public class Q152995 {


// 임의 사원 보다 두 점수가 모두 낮은 경우 인센티브 없음
// 두 점수의 합이 높은 순으로 석차
// 합이 동일할 경우 동석차, 다음 석차는 건너 뜀

// 배열 20만 짜리 선언?
// 두 점수가 모두 낮은 경우 인센티브가 없음. => O(N^2) 로 하기에는 N 이 너무 큼
// 두 번 비교하면 될지도..?
// 1번은 a[0] 로 비교
// 당연히 실패, 인센티브 못 받는 사원 구하는 방법 고민해야 할 듯함..
// 순서 매기는 건 잘한 것 같은데. 인센티브 못 받는 사원을 제대로 못 구함

    class Solution {

        static int MAX = 200000;

        public int solution(int[][] scores) {
            int answer = 0;
            int N = scores.length;
            int[] ranking = new int[MAX + 1];
            int find = scores[0][0] + scores[0][1];

            ArrayList<Employee> list = new ArrayList<Employee>();

            for (int i = 0; i < N; i++) {
                list.add(new Employee(i, scores[i][0], scores[i][1]));
            }

            // a 기준으로 내림차순 정렬
            Collections.sort(list);


            // 인센티브 못받는 사원 구하기.
            int max_b = list.get(0).b;
            int max_a = list.get(0).a;

            // a는 항상 이전 것보다 작거나 같음
            for (int i = 0; i < N; i++) {
                Employee cur = list.get(i);

                if (cur.a < max_a && cur.b < max_b) {
                    cur.rank = -1;
                    if (cur.idx == 0) return -1;
                }

                if (cur.b > max_b) {
                    max_b = cur.b;
                }
            }




            // 낮은 것들은 제거함.
            // 10, 1. 5,5 있을 시 3,2 같은 건 제거해야 함..

            //
            for (Employee emp : list) {
                if (emp.rank == -1) continue;
                int score = emp.a + emp.b;
                ranking[score]++;
            }

            int cur_rank = 1;
            for (int i = MAX; i >= 0; i--) {
                if (ranking[i] == 0) continue;

                if (i == find) return cur_rank;

                cur_rank += ranking[i];
            }

            return -1;
        }


        public class Employee implements Comparable<Employee>{
            public int a, b;
            public int idx;
            public int rank;

            public Employee(int idx, int a, int b) {
                this.a = a;
                this.b = b;
                this.idx = idx;
            }

            public int compareTo(Employee o) {
                if (this.a == o.a) return this.b - o.b;
                return o.a - this.a;
            }
        }
    }
}
