#include <bits/stdc++.h>

using namespace std;


int GetGCM(int a, int b);


// 각 arr 들의 최소공배수 끼리의 공배수를 구하면 될 듯..?
// 그냥 무난하게 정답
int solution(vector<int> arr) {
    int answer = 0;
    
    int prev = arr[0];
    
    
    for (int i = 1; i < arr.size(); i++) {
        int LCM = prev * arr[i] / GetGCM(prev, arr[i]);
        prev = LCM;   
    }
    
    return prev;
}

int GetGCM(int a, int b) {
    while (b != 0) {
        int r = a % b;
        a = b;
        b = r;
    }
    
    return a;
}

