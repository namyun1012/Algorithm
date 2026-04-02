#include <bits/stdc++.h>
#include <vector>

using namespace std;

// N 마리 중 N/2 마리, 항상 짝수
// 최대한 많은 포켓몬 중 가장 많은 포켓몬 종류의 수
// 딱 1케이스에서 실패


int solution(vector<int> nums)
{
    int size = nums.size();
    
    unordered_map<int, int> ponketmons;
    
    for (int i = 0; i < size; i++) {
        int cur = nums[i];
        
        ponketmons[cur]++;
    }
    
    vector<int> vec;
    
    // 포켓몬의 개수들 들어옴..
    for (auto it = ponketmons.begin(); it != ponketmons.end(); it++) {
        vec.push_back(it->second);
    }
    
    sort(vec.begin(), vec.end());
    
    int total = 0;
    int idx = 0;
    int result = 0;
    
    // 그냥 배열 돌면서 1개씩 까먹으면 될 듯함
    while(true) {
        
        result++;
        total++;
        
        if (total == size / 2) return result;
        
        
        idx++;
        if(idx == vec.size()) return vec.size();
    }
    
    return 0;
}