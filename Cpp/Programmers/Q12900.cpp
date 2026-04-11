#include <bits/stdc++.h>

using namespace std;

// 그냥 피보나치

int solution(int n) {

    vector<long long> dp(n + 1, 0);
    
    dp[1] = 1;
    dp[2] = 2;
    
    for (int i = 3; i <= n; i++) {
        dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
    }
    
    return dp[n];
}