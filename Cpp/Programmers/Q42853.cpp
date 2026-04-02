#include <bits/stdc++.h>
#include <string>
#include <vector>


using namespace std;

// 다리 지나는데 걸리는 최소 시간

// bridge_length 대 만, weight 이하 무게 까지만 버티기 가능
// 다리는 순차적으로 지나는 듯..
// 1초에 bridge_length 1만큼 지나가는 듯함.
// 뒤가 텅 비었을 때나 무게 제한으로 못 지나갈 때 -1 을 넣으면서 전체 다 지나간 개수 채우면 될 듯함..
// bride_legnth 가 채워지면 계속 빼는 방식..?

int solution(int bridge_length, int weight, vector<int> truck_weights) {
    
    queue<int> bridge;
    
    int N = truck_weights.size();
    int t = 0; // 결과
    int idx = 0; // 최대 N - 1
    int acrossed = 0; // 지나간 차량의 수 
    int cur_weight = 0; // 현재 무게
    
    // 전부 지나갈 때까지 계속
    while (acrossed < N) {
        
        // 1. 빠져나가는 것 부터.
        if (bridge.size() >= bridge_length) {
            int exited = bridge.front();
            bridge.pop();
            
            // 임의 값이 아닌 경우
            if (exited != -1) {
                
                cur_weight -= exited;
                acrossed++;
            }
            
           // cout << exited << " exited " << t << endl;
        }
        
        // 2. 채우기
        
        // N - 1 초과인 경우 트럭 전부다 들어왔으므로 무조건 -1 을 넣기
        if (idx > N -1) {
            bridge.push(-1);
        }
        
        // 다 안들어 옴
        else {
            int cur = truck_weights[idx];
            
            // 못 넣음
            if (cur + cur_weight > weight) {
                bridge.push(-1);
            }
            
            // 넣음
            else {
                bridge.push(cur);
                
                cur_weight += cur;
                idx++;
            }
        }
        
        t++;
    }
    
    return t;
}