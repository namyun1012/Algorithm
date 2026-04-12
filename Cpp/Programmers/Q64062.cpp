#include <bits/stdc++.h>

// stones 가 거의 MAX 크기 20만, 각 배열의 원소는 20억.,,
// 니나즈 친구들의 수가 무제한..?
// 1 씩 깍아서는 무조건 시간 초과 발생함
// O(N) 에 대한 해결책을 내야 할 듯 함.
// Parametric Search 해볼만 한 듯..?
// 시간 초과는 안나고 실패 계속 발생 중


using namespace std;

bool check (vector<int>& stones, int mid, int k) {
    int N = stones.size();
    
    int zero_value = 0;
    
    for (int i = 0; i < N; i++) {
        if (stones[i] - mid < 0) zero_value++;
        else zero_value = 0;
        
        if (zero_value >= k) return false;
    }
    
    return true;
}

int solution(vector<int> stones, int k) {
    int answer = 0;
    int max_value = *max_element(stones.begin(), stones.end());
    
    int start = 0;
    int end = 200000000;
    
    while(start <= end) {
        
        // 지나간 사람의 수..
        int mid = (start + end) / 2;
        // cout << mid << endl;
        
        // 지나갈 수 있음. 
        if (check(stones, mid, k)) {
            // mid 를 더 늘려 보기
            start = mid + 1;
            answer = max(answer, mid); 
        }
        
        // 못 지나감... mid 를 줄여야 함.
        else {
            end = mid - 1;
        } 
    }
    
    
    return answer;
}