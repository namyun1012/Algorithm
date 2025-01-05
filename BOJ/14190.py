from collections import deque

N, M = map(int, input().split())

arr = [[0] * M for _ in range(N)]
visited = [[0] * M for _ in range(N)]
distance = [[0] * M for _ in range(N)]
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

for i in range(N):
    arr[i] = list(map(int, input().split()))

def bfs(y, x):
    queue = deque()
    queue.append((y, x))
    visited[y][x] = 1
    distance[y][x] = 0
    
    while queue:
        cur_y, cur_x = queue.popleft()
        cur_distance = distance[cur_y][cur_x]
        
        for dir in range(4):
            nxt_y = cur_y + dy[dir]
            nxt_x = cur_x + dx[dir]
            
            # 체크 통과시
            if(0 <= nxt_y < N and 0 <= nxt_x < M and arr[nxt_y][nxt_x] == 1 and visited[nxt_y][nxt_x] == 0):
                queue.append((nxt_y, nxt_x))
                visited[nxt_y][nxt_x] = 1
                distance[nxt_y][nxt_x] = cur_distance + 1

    for i in range(N):
        for j in range(M):
            # 못 갈시 -1 로 변경
            if visited[i][j] == 0 and arr[i][j] == 1:
                distance[i][j] = -1
            print(distance[i][j], end=" ")
        print()
        
check = False
## find start
for i in range(N):
    for j in range(M):
        if arr[i][j] == 2:
            bfs(i, j)
            check = True
            break
    if check:
        break