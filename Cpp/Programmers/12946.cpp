#include <bits/stdc++.h>

using namespace std;

void hanoi(int n, int start, int end, int bypass, vector<vector<int>>& answer) {
    
    if (n == 1) {
        vector<int> vec;
        vec.push_back(start);
        vec.push_back(end);
        answer.push_back(vec);
        return ;
    }
    
    // n- 1 개를 경유지로 이동
    hanoi(n-1, start, bypass, end, answer);
    
    vector<int> vec;
    vec.push_back(start);
    vec.push_back(end);
    answer.push_back(vec);
    
    // 경유지의 n - 1개를 목적지로 이동
    hanoi(n - 1, bypass, end, start, answer);
}


vector<vector<int>> solution(int n) {
    vector<vector<int>> answer;
    
    hanoi(n, 1, 3, 2, answer);
    
    
    return answer;
}