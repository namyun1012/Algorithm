#include <bits/stdc++.h>

using namespace std;

// 각 종류 별로 최대 1가지
// 서로 다른 옷의 조합의 수, 일부 겹쳐도 의상 더 착용 혹은 다른 거 안 겹칠 시 다른 방법
// 최소 1개의 옷은 입음.

// vector 에서 i 종류의 옷을 입음
// 각 map 에서 옷 안 입는 경우 더해서 곱함
// 마지막에 옷 전부 안 입는 경우 뻄
 

int solution(vector<vector<string>> clothes) {
    int N = clothes.size();
    
    unordered_map<string, int> map;
    
    for (int i = 0; i < N; i++) {
        map[clothes[i][1]]++;
    }
    
    int result = 1;
    
    for (auto cur : map) {
        result *= (cur.second + 1);
    }
    
    
   
    
    return result - 1;
}