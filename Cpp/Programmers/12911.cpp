#include <bits/stdc++.h>

using namespace std;

// 쉬운 문제인 듯. 효율성도 바로 통과

int solution(int n) {
    
    const int max = 1000000;
    
    int temp = n;
    int bits = 0;
    while (temp > 0) {
        if (temp % 2 == 1) bits++;
        temp /= 2;
    }
    
    
    
    for (int i = n + 1; i <= max; i++) {
        
        int cur = i;
        int cur_bit = 0;
        while (cur > 0) {
            if (cur % 2 == 1) cur_bit++;
            cur /= 2;
        }
        
        if (cur_bit == bits) return i;
    }
    
    return -1;
}