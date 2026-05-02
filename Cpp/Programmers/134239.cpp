#include <bits/stdc++.h>

using namespace std;


// 초항이 k인 우박수열
// 수열 진행할 때마다 누적합 형식으로 미리 다 구해 놓고
// 추후 결과 나올 때마다. 하기..
vector<double> solution(int k, vector<vector<int>> ranges) {
    vector<double> answer;
    vector<double> sum;
    
    // 누적합의 처음은 0.0
    sum.push_back(0.0);
    
    int prev_k = -1;
    int n = 0;
    
    while (k > 1) {
        
        int new_k;
    
        if (k % 2 == 0) new_k = k / 2;
        else new_k = k * 3 + 1;
        
        double area = 0.0;
        
        // 우선 정사각형 부분
        area += (double) min(new_k, k);
        
        // 삼각형 부분.
        area += (double) abs(new_k - k) / 2.0;
        
        sum.push_back(area);
        
        k = new_k;
        n++;
    }
    
    for (int i = 1; i <= n; i++) sum[i] += sum[i - 1];
    
    
    // for (double ele : sum) {cout << ele << endl;}
    // cout << n << endl;
    for (vector<int>& range : ranges) {
        int start = range[0];
        int end = n + range[1]; 
        
        if (start > end) {
            answer.push_back(-1.0);
            continue;
        }
        
        
        answer.push_back(sum[end] - sum[start]);
    }
    
    
    
    return answer;
}