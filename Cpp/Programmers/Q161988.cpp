#include <bits/stdc++.h>


using namespace std;


// 1, -1 을, -1, 1 에서 곱한 것에서 각각 Max 값 구한 후 비교 ㄱ
// 일단 연속 부분 수열 구하는 파트는 O(N) 이어야 함.
// 실패가 1개라면 예외 처리 문제인 듯하다..

long long compute(vector<int> arr) {
    int N = arr.size();
    
    vector<long long> dp(N, 0);
    long long result = -2e9;
    
    dp[0] = arr[0];
    result = max(dp[0], result);
    
    
    for (int i = 1; i < N; i++) {
        dp[i] = arr[i];
        
        dp[i] = max(dp[i-1] + arr[i], dp[i]);
        result = max(dp[i], result);
    }    

    return result;
}



long long solution(vector<int> sequence) {
    long long answer = -2e9;
    
    int N = sequence.size();
    
    vector<int> arr1(N, 0);
    vector<int> arr2(N, 0);
    
    for (int i = 0; i < N; i++) {
        
        if (i % 2 == 0) {
            arr1[i] = sequence[i];
            arr2[i] = -sequence[i];
        }
        
        else {
            arr2[i] = sequence[i];
            arr1[i] = -sequence[i];
        }
    }
    
    // arr1, arr2 에서 연속되는 합중 제일 큰 것 구하기
    answer = max(compute(arr2), compute(arr1));
    
    return answer;
}