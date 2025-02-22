# 조건 설정 실수해서 약간 밀림
# 중복되는 조건들도 꽤나 많을 듯하다.
# 8가지 경우를 체크 
def titacto_check(board, cur):
    result = 0
    
    if(board[0][0] == cur and board[0][1] == cur and board[0][2] == cur):
        result += 1
    if(board[1][0] == cur and board[1][1] == cur and board[1][2] == cur):
        result += 1
    if(board[2][0] == cur and board[2][1] == cur and board[2][2] == cur):
        result += 1
    
    if(board[0][0] == cur and board[1][0] == cur and board[2][0] == cur):
        result += 1
    if(board[0][1] == cur and board[1][1] == cur and board[2][1] == cur):
        result += 1
    if(board[0][2] == cur and board[1][2] == cur and board[2][2] == cur):
        result += 1
        
    if(board[0][0] == cur and board[1][1] == cur and board[2][2] == cur):
        result += 1
    if(board[0][2] == cur and board[1][1] == cur and board[2][0] == cur):
        result += 1
        
    return result

result_stirng = []

while 1:
    temp = input()
    check = 1
    
    if temp == "end":
        break
    
    board = [[''] * 3 for _ in range(3)]
    
    x_count = 0
    o_count = 0
    empty_count = 0
    # input 받는 과정
    for i in range(3):
        for j in range(3):
            board[i][j] = temp[(i * 3 + j)]
            if (board[i][j] == 'O'):
                o_count += 1
            elif (board[i][j] == 'X'):
                x_count += 1
            else :
                empty_count += 1
    
    # 틱택토 결과 확인
    # 결과는 1개가 있어야 한다.
    result = 0
    
    # X가 이긴 경우
    result += titacto_check(board, 'X')
    
    # result 가 1인 경우
    if result >= 1:
        o_check = titacto_check(board, 'O')
        
        # Result 가 1개 이상인데 empty count 가 0이 아니면 실패? => O는 4개로는 불가능 X는 가능 한 듯
        if result > 1 and empty_count > 0:
            check = 0
        
        # O 체크 후 Result 가 증가하면 실패
        if o_check >= 1:
            check = 0
        
        # count 검사
        if x_count != o_count + 1:
            check = 0            
        
    # X가 이기지 못한 경우
    else:
        result += titacto_check(board, 'O')

        # O 가 이긴 것이 2개 이상 존재하는 경우
        if result > 1:
            check = 0
         
        # O가 이긴 경우
        elif result == 1:
            if x_count != o_count:
                check = 0
        
        # 이긴 사람이 없는 경우
        else:
            if not (x_count == 5 and o_count == 4):
                check = 0
            
    if check :
        result_stirng.append("valid")
    else:
        result_stirng.append("invalid")


for result in result_stirng:
    print(result)        