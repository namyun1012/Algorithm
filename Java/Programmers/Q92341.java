package Programmers;

import java.io.*;
import java.util.*;
public class Q92341 {


// 차량 별 입차, 출차 기록 후

// 차량 번호 오름차순으로 총 결과..
// Map 으로 묶음 // 하루 전체가 기준인 듯함.

// 문자열, Map 잘 쓰면 해결 될 듯함
// 런타임 에러 발생


    class Solution {

        static int MAX = 23 * 60 + 59;

        public int[] solution(int[] fees, String[] records) {
            int size = records.length;

            int basic_minute = fees[0];
            int basic_fee = fees[1];
            int unit_minute = fees[2];
            int unit_fee = fees[3];

            Map<Integer, Integer> car_inputs = new HashMap<Integer, Integer>();
            Map<Integer, Integer> car_fees = new HashMap<Integer, Integer>(); // 시간만 기록함

            for (int t = 0; t < size; t++) {
                String cur = records[t];

                StringTokenizer st = new StringTokenizer(cur);

                String time = st.nextToken();
                int minutes = Integer.parseInt(time.substring(0, 2)) * 60
                        + Integer.parseInt(time.substring(3, 5));

                int car_id = Integer.parseInt(st.nextToken());
                String type = st.nextToken();

                // 입차
                if (type.equals("IN")) {
                    car_inputs.put(car_id, minutes);
                }

                // 출차
                else {
                    int time_diff = minutes - car_inputs.get(car_id);
                    car_inputs.remove(car_id); // 추후 전부 체크 필요

                    if (car_fees.containsKey(car_id)) time_diff += car_fees.get(car_id);
                    car_fees.put(car_id, time_diff);
                }
            }

            // Order 종료, MAX 시간대에 빠졌다고 간주함.
            for (Integer car_id : car_inputs.keySet()) {
                if(car_inputs.get(car_id) == null) continue;

                int time_diff = MAX - car_inputs.get(car_id);

                if (car_fees.containsKey(car_id)) time_diff += car_fees.get(car_id);
                car_fees.put(car_id, time_diff);
            }

            ArrayList<int[]> results = new ArrayList<int[]>();

            for (Integer car_id : car_fees.keySet()) {
                int time_diff = car_fees.get(car_id);

                int cur_fee = basic_fee;

                // 초과시
                if (time_diff > basic_minute) {
                    cur_fee +=  ((int) Math.ceil((float)(time_diff - basic_minute)
                            / (float) unit_minute)) * unit_fee;
                }


                results.add(new int[] {car_id, cur_fee});
            }

            Collections.sort(results, (a,b) -> {
                return a[0] - b[0];
            });


            int[] answer = new int[results.size()];

            for (int i = 0; i < results.size(); i++)
                answer[i] = results.get(i)[1];


            return answer;
        }
    }
}
