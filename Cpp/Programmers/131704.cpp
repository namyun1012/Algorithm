#include <bits/stdc++.h>

using namespace std;

// 크기 같음, 번호 증가 순서.
// 택배 기사가 알려준 순서대로..?
// 보조 컨테이너는 Stack


int solution(vector<int> order) {
    int N = order.size();
    
    int answer = 0;
    
    stack<int> s;
    
    int idx = 0;
    int item = 1;
    
    while (item <= N) {
        
        if (order[idx] == item) {
            idx++;
            item++;
            answer++;
            continue;
        }
        
        if (!s.empty() && s.top() == order[idx]) {
            answer++;
            idx++;
            s.pop();
        }
        
        // 아무것도 못함
        else {
            s.push(item);
            item++;
        }        
    }
    
    // 마무리 처리.
    while (!s.empty() && s.top() == order[idx]) {
        answer++;
        idx++;
        s.pop();
    }
    
    
    
    return answer;
}