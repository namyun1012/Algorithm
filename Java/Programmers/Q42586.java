package Programmers;
import java.io.*;
import java.util.*;
public class Q42586 {



    // 걍 배열 잘 쓰기
    class Solution {
        public int[] solution(int[] progresses, int[] speeds) {

            int size = progresses.length;
            List<Integer> result = new ArrayList<Integer>();


            int cur_index = 0;
            int deployed = 0;

            // 전부 배포 될 때 까지 진행
            while (deployed < size) {

                // development
                for (int i = 0; i < size; i++) {
                    if (progresses[i] < 100 && progresses[i] != -1) progresses[i] += speeds[i];
                }

                // deploy
                if (progresses[cur_index] >= 100) {
                    int count = 0;

                    for (int i = cur_index; i < size; i++) {
                        if (progresses[i] >= 100) {
                            progresses[i] = -1;
                            count++;
                        }

                        else break;
                    }

                    deployed += count;
                    result.add(count);
                }

                // find new cur_index

                for (int i = 0; i < size; i++) {
                    if (progresses[i] < 100 && progresses[i] != -1) {
                        cur_index = i;
                        break;
                    }
                }
            }


            int[] answer = new int[result.size()];


            for (int i = 0; i < result.size(); i++)
                answer[i] = result.get(i);

            return answer;
        }
    }
}
