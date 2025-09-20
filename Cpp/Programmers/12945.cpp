#include <string>
#include <vector>
#include <iostream>
#define TEMP 1234567
using namespace std;

int fibo(int n , int* dp);

int solution(int n) {
    
    int dp[100001];
    
    for (int i = 0; i <= n ; i++) 
        dp[i] = -1;
    
    
    dp[0] = 0;
    dp[1] = 1;
    dp[2] = 1;

    
    fibo(n, dp);
    
    
    int answer = dp[n];
    return answer;
}


int fibo(int n , int* dp) {    
    if (dp[n] != -1) return dp[n];
    dp[n] = (fibo(n - 1, dp) % TEMP + fibo(n - 2, dp) % TEMP) % TEMP;
    
    return dp[n];
}

int main() {
    std::cout << solution(10) << std::endl;
    return 0;
}