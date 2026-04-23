#include <bits/stdc++.h>

// 병사 n 명.. 무적권 k 번 째 사용 가능
// enemy 가 가장 많은 곳에만 쓰면됨.
// 그냥 쭉 이어가다가.. 체력 0 도달하면 재 회복..? 쓰는 건 PQ
// 몇몇 경우 실패함.. => long long 문제는 아닌 듯 함. 등호 문제.


using namespace std;

int solution(int n, int k, vector<int> enemy) {
    int M = enemy.size();
    
    priority_queue<int> pq;
    
    int used = 0;
    int answer = 0;
    
    
    for (int i = 0; i < M; i++) {
        int cur = enemy[i];
        
        // PQ 에 우선 넣음
        pq.push(cur);
        
        // 체력 감소 및 검증 실행
        n -= cur;
        
        if (n < 0) {
            if (used < k) {
                n += pq.top();
                pq.pop();
                used++;
            }
                
            else {
                return i;
            }
        }    
    }
    
    return M;
}