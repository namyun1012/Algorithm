#include <bits/stdc++.h>

using namespace std;


// 카드를 나눠 가진 후 가장 큰 양의 정수 a 의 값 구하기.
// 철수 거는 모두 나눌 수 있음, 영희 거는 하나도 나눌 수 없는 양의 정수.
// arrayA, arrayB 의 길이 각각 50만
// 원소는 1억 대 이므로.. O(N^2) 나 숫자별로 하나씩은 당연히 힘듬.
// 조건은 당연하지만 둘 중 하나만 만족시 ㅇㅋ.
// 각 배열에서 가장 큰 수로 확인하기.?
// 최소 공약 수 찾기..? 

int getGCD(int a, int b) {
    while (b != 0) {
        int r = a % b;
        a = b;
        b = r;
    }
    
    return a;
}

int solution(vector<int> arrayA, vector<int> arrayB) {
    int answer = 1;
    
    int GCD_A = 1;
    
    if (arrayA.size() == 1) GCD_A = arrayA[0];
    else {  
        GCD_A = getGCD(arrayA[0], arrayA[1]);
    
        for (int i = 1; i < arrayA.size(); i++) {
            GCD_A = getGCD(GCD_A, arrayA[i]);        
        }
    }
    
    // 아닐 시에만 체크 
    if (GCD_A != 1) {
        bool check = true;
        for (int i = 0; i < arrayB.size(); i++) {
            if (arrayB[i] % GCD_A == 0) {
                check = false;
                break;
            }
        }
        
        if (check) answer = GCD_A;
    
    }
    
    // cout << answer << endl;
    
    // GCD_B
    int GCD_B = 1;
    
    if (arrayB.size() == 1) GCD_B = arrayB[0];
    else {  
        GCD_B = getGCD(arrayB[0], arrayB[1]);
    
        for (int i = 1; i < arrayB.size(); i++) {
            GCD_B = getGCD(GCD_B, arrayB[i]);        
        }
    }
    
    // 아닐 시에만 체크 
    if (GCD_B != 1) {
        bool check = true;
        for (int i = 0; i < arrayA.size(); i++) {
            if (arrayA[i] % GCD_B == 0) {
                check = false;
                break;
            }
        }
        
        if (check) answer = max(answer, GCD_B);
    }
    
    if (answer == 1) answer = 0;
    return answer;
}

