#include <bits/stdc++.h>

using namespace std;

// 돈 주기..
// 
// 
int solution(int n, vector<int> money) {
    int answer = 0;
    const int divide = 1000000007;
    
    // Money 종류의 수를 우선 정렬 해 놓음..
    sort(money.begin(), money.end(), [](int a, int b) {
        return a < b;
    });
    
    // DP 로 ㄱㄱ
    
    vector<int> dp(n + 1, 0);
    
    // 아무것도 없는 것 1
    dp[0] = 1;

    // 중복 없도록 coin 을 위 쪽으로 뺌.. 
    for (int coin : money) {
        for (int i = coin; i <= n; i++) {
            dp[i] = (dp[i - coin] + dp[i]) % divide; 
        }
    }
    
    return dp[n];
}