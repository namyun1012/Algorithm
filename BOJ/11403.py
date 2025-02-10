N = int(input())

board = [[0] * (N + 1) for _ in range(N + 1)]


for i in range(N):
    board[i] = list(map(int, input().split()))

# i - 1번 반복
for k in range(N):
    for i in range(N):
        for j in range(N):
            if board[i][k] == 1 and board[k][j] == 1:
                board[i][j] = 1
                
for i in range(N):
    for j in range(N):
        print(board[i][j], end = " ")
    print()
