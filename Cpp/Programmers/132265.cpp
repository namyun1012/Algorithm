#include <bits/stdc++.h>

using namespace std;

// 롤케이크를 2조각으로 나눠 먹음
// 동일한 가짓수의 토핑이 올라가야함.
// 단순 topping 길이 자르기. 못함.
// topping 의 원소는 최대 10000 까지.
// 공평하게 자르는 방법의 수.
// 그냥 위치 별 배열이면 될 듯.


int solution(vector<int> topping) {
    int answer = 0;
    int N = topping.size();
    
    unordered_set<int> s;
    vector<int> vec(N + 1, 0); // i 포함 왼쪽
    vector<int> revec(N + 1, 0); // i 미 포함 오른 쪽
    
    for (int i = 0; i < N; i++) {
        s.insert(topping[i]);
        vec[i] = s.size();
    }
    
    s.clear();
    for (int i = N - 1; i >= 0; i--) {
        revec[i] = s.size();
        s.insert(topping[i]);
    }
    
    for (int i = 0; i < N; i++) {
        if (vec[i] == revec[i]) answer++;
    }
    
    
    return answer;
}