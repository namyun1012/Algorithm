from bisect import bisect_left, bisect_right

N = int(input())
card = list(map(int, input().split()))

M = int(input())
quest = list(map(int, input().split()))

card.sort()
# bisect l,r 비교하는 것으로 확인 가능하다.
for answer in quest:
    result_l = bisect_left(card, answer)
    result_r = bisect_right(card, answer)
    if(result_l != result_r):
        print(1, end=" ")
    else:
        print(0, end=" ")
        

    
