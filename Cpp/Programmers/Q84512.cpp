#include <bits/stdc++.h>

using namespace std;

// 글자 수가 최대 5라서 그냥 전부다 unordered_map 에 넣어놓고 돌리면 될 듯..?

unordered_map<string, int> dict;
int idx = 1;
char alphabets[] = {'A', 'E', 'I', 'O', 'U'};

void make_dict(int length, string cur);

int solution(string word) {
    
    
    
    make_dict(0, "");
    
    
    return dict[word];
}

void make_dict(int length, string cur) {
    if (length >= 5) return;
    
    for (int i = 0; i < 5; i++) {
        string nxt = cur + alphabets[i];
        
        auto it = dict.find(nxt);
        if (it != dict.end()) continue;
        
        dict[nxt] = idx++;
        make_dict(length + 1, nxt);
    }
}