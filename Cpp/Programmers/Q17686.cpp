#include <bits/stdc++.h>

using namespace std;

unordered_map<string, int> orders;

bool compare(string& a, string& b) {
    string head_a;
    string head_b;
    
    string num_a;
    string num_b;
    
    
    int i,j;
    for (i = 0; i < a.size(); i++) {
        if (a[i] >= '0' && a[i] <= '9') break;
        
    }
    for (j = 0; j < b.size(); j++) {
        if (b[j] >= '0' && b[j] <= '9') break;
    }
    
    // i, j 는 숫자가 시작되는 부분의 idx
    head_a = a.substr(0, i);
    head_b = b.substr(0, j);
    
    for (int i = 0; i < head_a.size(); i++) head_a[i] = toupper(head_a[i]);
    for (int i = 0; i < head_b.size(); i++) head_b[i] = toupper(head_b[i]);
    
    if (head_a != head_b) return head_a < head_b;
    
    // 숫자 비교하기
    
    int idx_a = i;
    int idx_b = j;
    
    // length 용도임
    i = 0;
    j = 0;
    
    for (i = 0; i + idx_a < a.size(); i++) {
        if (a[i + idx_a] < '0' || a[i + idx_a] > '9') break;
    }
    
    for (j = 0; j + idx_b < b.size(); j++) {
        if (b[j + idx_b] < '0' || b[j + idx_b] > '9') break;
    }
    
    num_a = a.substr(idx_a, i);
    num_b = b.substr(idx_b, j);
        
    if (stoi(num_a) != stoi(num_b)) return stoi(num_a) < stoi(num_b);
    
    // 원래 입력된 순서 유지하기..
    return orders[a] < orders[b];
}


vector<string> solution(vector<string> files) {
    
    
    vector<string> answer;
    
    for (int i = 0; i < files.size(); i++) {
        
        orders[files[i]] = i;
    }
    
    
    sort(files.begin(), files.end(), compare);

    
    return files;
}