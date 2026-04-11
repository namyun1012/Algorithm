#include <bits/stdc++.h>

using namespace std;


// 그냥 전부다 한 번 씩 조회하면 끝날 듯, max_x, max_y 비교하면서;;
int solution(vector<vector<int>> sizes) {
    
    int max_x = 0;
    int max_y = 0;
    int result = 0;
    int N = sizes.size();
    
    // 전부 앞에 것이 더 크게 정렬함.
    for (int i = 0; i < N; i++) {
        sort(sizes[i].begin(), sizes[i].end());
    }
    
    for (auto size : sizes) {
        int x = size[0];
        int y = size[1];
        
        if (x > max_x) {
            max_x = x;       
        }
        
        if (y > max_y) {
            max_y = y;
        }
        
        // cout << max_x << " " << max_y << endl;
        result = max_x * max_y;
    }
    
    
    return result;
}