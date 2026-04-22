#include <bits/stdc++.h>

using namespace std;

// 서로 다른 종류의 수의 최솟값.. 
// 이진 탐색은 아닌 듯함
// 걍 그리디 쓰면 풀릴 문젤 듯..


int solution(int k, vector<int> tangerine) {
    
    int N = tangerine.size();
    
    unordered_map<int, int> m;

    for (int i = 0; i < N; i++) {
        int cur = tangerine[i];
        m[cur]++;
    }
    
    vector<int> vec;
    
    for (auto tang : m) {
        vec.push_back(tang.second);
    }
    
    sort(vec.begin(), vec.end(), greater<int>());
    
    int cur = 0;
    int idx = 0;
    
    while (cur < k) {
        cur += vec[idx++];
    }
    
    return idx;
}