# 시간 초과남
import sys
from collections import deque

def OOP(x, y):
    if(x < 0 or y < 0 or x >= N  or y >= N):
        return False
    return True

def bfs(x, y):
    queue = deque()
    queue.append((x,y))
    visited[x][y] = 1
    sum = arr[x][y]
    place_array = [(x,y)]
    ## visited, 처리 주의하기 queue 에 append 시점에서 바로 visited 처리 해주기
    while queue:
        x,y = queue.popleft()
        
        for dir in range(4):
            nx = x + dx[dir]
            ny = y + dy[dir]
            
            if(not OOP(nx, ny)):
                continue
            if(visited[nx][ny]):
                continue
            arr_diff = abs(arr[x][y] - arr[nx][ny])
            if(not (arr_diff >= L and arr_diff <= R)):
                continue
            queue.append((nx, ny))
            visited[nx][ny] = 1
            sum += arr[nx][ny]
            place_array.append((nx,ny))
    result = sum // len(place_array)
    for ele in place_array:
        arr[ele[0]][ele[1]] = result
    
    return len(place_array) > 1

N, L, R = map(int, input().split())

arr = [[0] * N for _ in range(N)]
visited = [[0] * N for _ in range(N)] ## N 개의 국가에서 갈수 있는 4방위 확인하기

dx = [0,1,-1,0]
dy = [1,0,0,-1]
day = 0

# 인구 초기화
for i in range(N):
    arr[i] = list(map(int, sys.stdin.readline().split()))

while True:
    ## visited 초기화
    visited = [[0] * N for _ in range(N)] ## N 개의 국가에서 갈수 있는 4방위 확인하기
    check = False
    
    ## 연합 결성
    for i in range(N):
        for j in range(N):
            if(visited[i][j]):
                continue
            if bfs(i, j):
                check = True
    
    if not check:
        break
    day += 1

print(day)