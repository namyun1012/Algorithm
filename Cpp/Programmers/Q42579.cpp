#include <bits/stdc++.h>

// unordered_map 대신 그냥 map 쓰기
// 장르별로 가장 많이 재생된 노래가 2개임... 
using namespace std;


int N;
map<string, int> dict;

struct song {
    int idx;
    int play;
    string genre;   
};


bool compare(song o1, song o2) {
    
    if (o1.genre == o2.genre && o1.play == o2.play) {
        return o1.idx < o2.idx;
    }
    
    
    if (o1.genre == o2.genre) {
        return o1.play > o2.play;
    } 
    
    return dict[o1.genre] > dict[o2.genre];
}

vector<int> solution(vector<string> genres, vector<int> plays) {
    vector<int> answer;
    
    vector<song> vec;
    
    
    N = genres.size();
    
    // Init Dict
    for (int i = 0; i < N; i++) {
        string genre = genres[i];
        int play = plays[i];
        dict[genre] += play;
        
        vec.push_back({i, play, genre});
    }
    
    sort(vec.begin(), vec.end(), compare);
    
    unordered_map<string, int> count;
    
    for (auto o : vec) {
        
        if (count[o.genre] >= 2) continue;
        
        answer.push_back(o.idx);
        count[o.genre]++;
    }
    
    return answer;
}