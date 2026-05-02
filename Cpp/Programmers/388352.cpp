#include <bits/stdc++.h>

using namespace std;


// 비밀 코드로 가능한 정수 조합 개수..
// 이미 오름차 순으로 정렬되어 있긴 함.

// 그냥 가능한 것들 만 따짐, 곱하기 k..?
// 비밀 코드로 가능한 수.. 음..
// q 의 길이는 10임.. 음.. backtracking 이 가능할 까..
// 그냥 조건 만족시.. result ++ 하기..?
// n 이 30이라 좀 애매하긴 한데.. 일단 전체 backtracking 으로 가봄..

// 2^30  2^10 10^9 

int result = 0;

void backtracking(int n, vector<vector<int>>& q, vector<int>& ans, int idx, unordered_map<int, int>& used, int number) {
    
    // 결과 값 반환..?
    if (number == 5) {
        
        bool check = true;
        // 검증 시작
        for (int t = 0; t < ans.size(); t++) {
            
            int total_count = ans[t];
            int count = 0;
            
            for (int ele : q[t]) {
                if (used[ele]) count++;
            }
            
            
            if (total_count != count) {
                check = false;
                break;
            }
        }
        
        if (check) result++;
        return ;
    }
    
    
    
    for (int i = idx; i <= n; i++) {
        used[i] = 1;
        backtracking(n, q, ans, i + 1, used, number + 1);
        used[i] = 0;
    }
}


int solution(int n, vector<vector<int>> q, vector<int> ans) {
    int answer = 0;

    unordered_map<int, int> used;
    backtracking(n, q, ans, 1, used, 0);
    
    return result;
}