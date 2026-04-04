import java.util.*;

// 뭔가 이분 탐색은 아닌 듯함.

// route 범위 마다 포함되는 것이 1개씩은 있어야 함.
// 6만개 를 가지고 완전 탐색도 불가
// 범위 전부 기록 후 -30000 ~ 30000 제일 많은 거 순으로 정렬
// 많은 순 대로 설치하면서 전부 가능해 질 때 까지 확인..?
// Map<위치, 개수>?
// 해당 로직 반복하기..?
// 일단 사용할 때 전부 30000 씩 더 해서 사용할 것
// 효율성 테스트를 통과 못함;;

// 점차 작은 곳에서 지나가면서 최대한 나가는 지점에만 설치함
class Q42884 {
    
  public int solution(int[][] routes) {
        // 1. 나가는 지점(End)을 기준으로 오름차순 정렬
        Arrays.sort(routes, (a,b) -> (a[1] - b[1]));

        int count = 0;
      
        // 초기 카메라 위치를 가장 첫 차의 나가는 지점보다 훨씬 뒤로 설정
        int lastCamera = -30001; 

        for (int[] route : routes) {
            int start = route[0];
            int end = route[1];

            // 현재 카메라가 이 차의 진입보다 앞일시
            if (lastCamera < start) {
                count++; // 새 카메라 설치
                lastCamera = end; // 나가는 지점에 설치, 이미 정렬 해놓은 상태이므로..
            }
        }

        return count;
    }
}