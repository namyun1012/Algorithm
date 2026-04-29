#include <bits/stdc++.h>

using namespace std;


// terms 는 맵으로.
// 모든 달이 28 일 까지 있다고 가정임..

vector<int> solution(string today, vector<string> terms, vector<string> privacies) {
    vector<int> answer;
    
    unordered_map<string, int> m;
    
    for (string term : terms) {
        m[term.substr(0,1)] = stoi(term.substr(2));
    }
    
    int cur_year = 0;
    int cur_month = 0;
    int cur_day = 0;
    
    cur_year = stoi(today.substr(0, 4));
    cur_month = stoi(today.substr(5,2));
    cur_day = stoi(today.substr(8,2));
    
    int cur = cur_year * 12 * 28 + cur_month * 28 + cur_day;
    
     
    for (int p = 0; p < privacies.size(); p++) {
        string privacy = privacies[p];
        
        int year = stoi(privacy.substr(0, 4));
        int month = stoi(privacy.substr(5, 2));
        int day = stoi(privacy.substr(8, 2));
        
        int term = m[privacy.substr(11, 1)];
        
        int cal = year * 12 * 28 + month * 28 + day;
        cal += term * 28;
        
        if (cur >= cal) answer.push_back(p + 1);
        
    }
    
    
    
    return answer;
}