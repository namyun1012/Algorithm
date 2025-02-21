# 비교적 Well Known Greedy 라고 한다.
# 버튼을 누르는 순서는 상관이 없어, 순차적으로 가능하다.
# 배열 복사에 주의하자, 배열 복사는 기본이 Deep 이고, Shallow 를 하면 시간을 많이 먹는다.


N = int(input())

bulb = list(input().strip())
bulb = list(map(int, bulb))

expect = list(input().strip())
expect = list(map(int, expect))

MAX = int(1e9)
result = MAX

def turn_bulb(i, cur_bulb):
    if cur_bulb[i]:
        cur_bulb[i] = 0
    else:
        cur_bulb[i] = 1


def turn_switch(i, cur_bulb):
    
    turn_bulb(i, cur_bulb)
    
    if i > 0:
        turn_bulb(i - 1, cur_bulb)
    if i < N - 1:
        turn_bulb(i + 1, cur_bulb)

    return cur_bulb

bulb_off = bulb[:]
bulb_on  = turn_switch(0, bulb[:])

# Initial, 전환 한 것과 안한 것 저장

def solution(para_bulb, count):
    cur_bulb = para_bulb
    for i in range(1, N):
        if cur_bulb[i-1] != expect[i-1]:
            cur_bulb = turn_switch(i, cur_bulb)
            count += 1

    if cur_bulb == expect:
        return count
    else:
        return MAX

result = min(result, solution(bulb_off[:], 0))
result = min(result, solution(bulb_on[:], 1))

if result != MAX:
    print(result)
else:
    print(-1)