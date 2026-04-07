#include <bits/stdc++.h>

using namespace std;


// 그냥 위치 + Wait 기억하게 해 놓기..?

int N; 

int solution(vector<int> priorities, int location) {
    
    N = priorities.size();
    
    
    // priorities, 원래 location 저장 용도의 q
    queue<pair<int, int>> q;
    
    
    // 원래 location -> 1 일시 처리 완료
    vector<int> processed(N, 0);
    
    // q 에 일단 전부 집어넣기
    for (int i = 0; i < N; i++) {
        q.push({priorities[i], i});
    }
    
    int result = 0;
    
    while (!q.empty()) {
        pair<int, int> cur = q.front();
        q.pop();
        
        int cur_prior = cur.first;
        int cur_locate = cur.second;
        
        bool check = true;
        
        for (int i = 0; i < N; i++) {
            // 더 큰 것이 있는 경우 다시 넣기
            if (processed[i] == 0 && priorities[i] > cur_prior) {
                check = false;
                q.push(cur);
                break;
            }
        }
        
        // 가장 우선순위 높음.
        if (check) {
            processed[cur_locate] = 1;
            result++;
            if (cur_locate == location) return result;
        }
    }

    
    // error
    return -1;
}