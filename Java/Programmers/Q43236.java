package Programmers;

import java.io.*;
import java.util.*;
public class Q43236 {


// Parametric Search 인듯..?
// 바위는 5만개.. => 바위 사이의 거리의 최솟값
// 백트래킹은 당연히 안됨..
// mid 를 거리의 최솟값으로..?
// mid 검증 시 만약에 다음 거리가 넘을 경우 그것을 없앰
// 없애는 양이 초과해 버리면 mid 를 줄임
// n 에 미달하면 mid 를 늘림
// 일단 다 지워넣고 실제 최솟값을 찾아야 함..?
// 최소값 증가 위해서는 삭제 돌 증가 필요
// 출발 지점, 도착 지점 고려;;

    class Solution {
        public int solution(int distance, int[] rocks, int n) {
            int size = rocks.length;
            int[] places = new int[size + 2];

            places[0] = 0;
            places[1] = distance;

            for (int i = 2; i < size + 2; i++)
                places[i] = rocks[i - 2];

            Arrays.sort(places);

            // start, end 는 거리;;
            int start = 0;
            int end = distance + 1;

            int mid;

            int result = 0;

            while(start <= end) {
                mid = (start + end) / 2;

                int erased = check(mid, places);

                // 돌 제거량 초과 -> 최소 거리 감소 해야 함
                if (erased > n) {
                    end = mid - 1;
                }

                // 돌 제거량 부족 혹은 동일 -> 최소 거리 늘려보기
                else {
                    start = mid + 1;
                    result = Math.max(mid, result);
                }
            }

            // 마지막으로 result 를 갖고 실제 거리의 최솟값을 찾아야 함..
            return result;
        }


        // value 는 두 돌 사이 거리의 최솟값
        int check(int value, int[] rocks) {
            int size = rocks.length;

            int erase_count = 0;

            // 정상 종료가 불가능 한 경우
            boolean check = false;


            int left = 0;
            int right = 1;

            while (right < rocks.length) {
                if (rocks[right] - rocks[left] < value) {
                    right++;
                    erase_count++;
                }

                else {
                    left = right;
                    right++;
                }
            }

            // System.out.println(value + " " + erase_count);
            return erase_count;
        }
    }
}
