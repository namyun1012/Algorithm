#include <bits/stdc++.h>

// 약간 푸는 동안 맛이 간 것 같음..? 피곤한가..?

using namespace std;

vector<vector<int>> solution(vector<vector<int>> data, string ext, int val_ext, string sort_by) {
    
    vector<vector<int>> new_data;
    
    int idx;

    if (ext == "code") idx = 0;
    else if (ext == "date") idx = 1;
    else if (ext == "maximum") idx = 2;
    else idx = 3; // remain
    
    for (int i = 0; i < data.size(); i++) {
        vector<int> cur = data[i];
        
        int val = cur[idx];
        if (val < val_ext) new_data.push_back(cur);
    }
    
    if (sort_by == "code") idx = 0;
    else if (sort_by == "date") idx = 1;
    else if (sort_by == "maximum") idx = 2;
    else idx = 3; // remain
    
    
    
    sort(new_data.begin(), new_data.end(), [idx](auto& a, auto& b) {
        return a[idx] < b[idx];
    });

    return new_data;
}