N = int(input())

board = [[0] * (N) for _ in range(N)]

white = 0
blue = 0

for i in range(N):
    board[i] = list(map(int, input().split()))
    
def recursive(paper):
    global white, blue
    check = 0
    cur = paper[0][0]
    size = len(paper)
    
    for i in range(size):
        for j in range(size):
            if paper[i][j] != cur:
                newPaper = [[0] * int(size / 2) for _ in range(int(size / 2))]
                
                for y in range(int(size / 2)):
                    newPaper[y] = paper[y][0 : int(size / 2)]
                
                recursive(newPaper)
                
                for y in range(int(size / 2)):
                    newPaper[y] = paper[y][int(size / 2) : size]
                recursive(newPaper)
                
                for y in range(int(size / 2)):
                    newPaper[y] = paper[y + int(size / 2)][0 : int(size / 2)]
                recursive(newPaper)
                
                for y in range(int(size / 2)):
                    newPaper[y] = paper[y + int(size / 2)][int(size / 2) : size]
                recursive(newPaper)
                
                return
    
    
    if not cur:
        white += 1
    else:
        blue += 1
        
recursive(board)
print(white)
print(blue)
