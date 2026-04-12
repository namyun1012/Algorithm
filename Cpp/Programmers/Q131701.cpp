#include <bits/stdc++.h>

using namespace std;
// 누적합으로 해결했지만 좀. 직감으로 하는 듯한 느낌
// 원형 순열... 전체 합에서 특정 연속 순열 빼면 그것이 답임..
// set 에 넣을 때 그거랑 그거 제외한 거 두 개 씩 넣어 줄 것.
// 합의 개수 이므로. unordered_set 에 던져주고 나중에 size 로 계산
int solution(vector<int> elements) {
    int N = elements.size();
    int total = 0;
    for (int i = 0; i < N; i++) total += elements[i];
    
    unordered_set<int> s;
    vector<int> vec;
    
    int sum = 0;
    
    vec.push_back(0); // 0 체크 값.
    for (int i = 0; i < N; i++) {
        sum += elements[i];
        vec.push_back(sum);
    }
    
    for (int length = 1; length < N ; length++) {
        for (int start = 0; start < N; start++) {    
            
            int end = start + length;   
            if (end > N) break;
            
            int cur = vec[end] - vec[start];
            
            // cout << cur << endl;
            s.insert(cur);
            s.insert(total - cur);
        }
    }
    
    s.insert(total);
    

    return s.size();
}