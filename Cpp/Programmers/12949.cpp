#include <bits/stdc++.h>

using namespace std;

// (3*2) * (2*2) => 3*2

vector<vector<int>> solution(vector<vector<int>> arr1, vector<vector<int>> arr2) {
    
    
    int N = arr1.size();
    int K = arr2.size();
    int M = arr2[0].size();
    
    vector<vector<int>> answer(N, vector<int>(M, 0));
    
    for (int i = 0; i < N ;i++) {
        for (int j = 0; j < M; j++) {
            
            int sum = 0;
            
            for (int k = 0; k < K; k++)
                sum += (arr1[i][k] * arr2[k][j]);
        
            answer[i][j] = sum;    
        }
    }
    
    
    
    
    return answer;
}