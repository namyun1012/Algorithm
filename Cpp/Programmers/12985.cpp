#include <bits/stdc++.h>

using namespace std;

// 죄다 실패.

int solution(int n, int a, int b)
{
    int answer = 0;
    int count = 1;
    
    while (n > 0) {
        if (a == b) return count - 1;
        
        if (a % 2 == 1) a = a / 2 + 1;
        else a /= 2;
        
        if (b % 2 == 1) b = b / 2 + 1;
        else b /= 2;
    
        n /= 2;
        count++;
    }

    return -1;
}