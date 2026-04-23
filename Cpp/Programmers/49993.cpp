#include <bits/stdc++.h>

using namespace std;

// 그냥 위상 정렬 한번씩 하기..? => X 그냥 string idx 체크해 가면서 진행마면 될 듯함

int solution(string skill, vector<string> skill_trees) {
    int answer = 0;
    
    unordered_map<char, int> m; 
        
    for (int i = 0; i < skill.size(); i++)
        m[skill[i]] = (i + 1);
    
    for (int i = 0; i < skill_trees.size(); i++) {
        int skill_idx = 1;
        string skill_tree = skill_trees[i];
        bool check = true;
        
        for (int j = 0; j < skill_tree.size(); j++) {
            char cur = skill_tree[j];
            
            if (m[cur] == 0) continue;
            
            if (m[cur] == skill_idx) skill_idx++;
            else {
                check = false;
                break;
            }
        }
        
        if (check) answer++;
    }
    
    
    
    
    
    return answer;
}