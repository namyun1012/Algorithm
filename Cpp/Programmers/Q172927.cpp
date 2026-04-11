#include <bits/stdc++.h>

using namespace std;


// picks -> 곡괭이, 부서질 때 까지 사용

// 광물 5개 캔 후 사용 불가
// 최소한의 피로도 
// 다이아는 다이아, 철은 철, 돌은 돌로 캐야 함;;
// 정렬은 힘들고, 완전 탐색이 가능한가..?
// 3^15 승

int N;
int dia, iron, stone;
int total;

int result;

void backtracking(vector<int> used, vector<int> picks, vector<string> minerals, int tired, int idx) {
    
    bool check = false;
    if (used[0] == picks[0] && used[1] == picks[1] && used[2] == picks[2])
        check = true;
    
    
    if (idx >= N || check) {
        result = min(result, tired);
        return ;
    }
    
    
    for (int dir = 0; dir < 3; dir++) {
        if (used[dir] == picks[dir]) continue;
        used[dir]++;
        
        int new_tired = tired;
        
        int i = 0;
        for (i = idx; i < idx + 5; i++) {
            if (i >= N) break;
            
            string cur = minerals[i];
            if (cur == "diamond") {
                if (dir == 0) new_tired += 1;
                else if(dir == 1) new_tired += 5;
                else new_tired += 25;
            }
            
            else if (cur == "iron") {
                if (dir == 0 || dir == 1) new_tired += 1;
                else new_tired += 5;
            }
            else new_tired += 1;
        }
        
        backtracking(used, picks, minerals, new_tired, i);
        
        used[dir] -= 1;
        
    }
} 

int solution(vector<int> picks, vector<string> minerals) {
    N = minerals.size();
      
    result = 1e9;
    
    backtracking(vector<int>(3, 0), picks, minerals, 0, 0);

    return result;
}




