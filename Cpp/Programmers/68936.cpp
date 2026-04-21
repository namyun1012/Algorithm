#include <bits/stdc++.h>

using namespace std;

// n 은 1 이상 1024 이하 => 브루트 포스 해도 ㄱㅊ을 듯..? => 4중 for 문 인 것이 좀 걸리긴 함..
// 압축 한 지점은 따로 size 배열을 운용해서 압축한 곳 전부에다가. 그냥 한 번 합쳐질 경우 두 번 다시 합쳐지지 않음. 


vector<int> solution(vector<vector<int>> arr) {
    int N = arr.size();
    
    vector<int> answer(2, 0);
    vector<vector<int>> visited(N, vector<int>(N, 0));
    
    int len = N;
    
    while (len > 1) {
        
        for (int y = 0; y < N; y += len) {
            for (int x = 0; x < N; x += len) {
                
                // 이미 합쳐진 대상
                if (visited[y][x]) continue;
                
                int cur = arr[y][x];
                bool check = true;
                
                // 합쳐진 대상이 아님.
                for (int i = y; i < y + len; i++) {
                    for (int j = x; j < x + len; j++) {
                        if (cur != arr[i][j]) {
                            check = false;
                            break;
                        }
                    }
                    
                    if (!check) break;
                }
                
                
                // 전부 다 같음
                if (check) {
                    answer[cur]++;
                    for (int i = y; i < y + len; i++) {
                        for (int j = x; j < x + len; j++) {
                            visited[i][j] = 1;
                        }
                    }
                }
            }
        }
        

        len /= 2;
    }
    
    
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (visited[i][j] == 0) answer[arr[i][j]]++;
        }
    }
    
    
   
    return answer;
}