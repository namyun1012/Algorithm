# BFS 문제

from collections import deque;

N, M = map(int, input().split())

board = [[0] * M for _ in range(N)]
visited = [[0] * M for _ in range(N)]
start = (0, 0)

dx = [0,0,1,-1]
dy = [1,-1,0,0]
result = 0

for i in range(N):
    
    str = input()
    for j in range(M):
        if str[j] == 'O':
            board[i][j] = 0 
        elif str[j] == 'P':
            board[i][j] = 2
        elif str[j] == 'I':
            start = (i, j)
            board[i][j] = 0
        elif str[j] == 'X':
            board[i][j] = -1

def OOP(y, x):
    if(y < 0 or x < 0 or y >= N or x >= M or board[y][x] == -1):
        return False
    
    if(visited[y][x] == 1):
        return False

    return True
        
def bfs():
    global result
    q = deque()
    
    visited[start[0]][start[1]] = 1
    q.append(start)
    
    while q:
        cur = q.pop()
        #print(cur[0], cur[1])
        for dir in range(4):
            nxt = (cur[0] + dy[dir], cur[1] + dx[dir])

            if(not OOP(nxt[0], nxt[1])):
                continue
            
            if(board[nxt[0]][nxt[1]] == 2):
                result += 1
            
            
            visited[nxt[0]][nxt[1]] = 1
            q.append(nxt)
            
    
bfs()

if result:
    print(result)
else :
    print("TT")
    
    