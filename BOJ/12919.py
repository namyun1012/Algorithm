S = input()
T = input()

check = 0

def recursive(temp):
    global check
    if temp == S:
        check = 1
        return
    
    if len(temp) == 0:
        return
    if check:
        return
    ## first
    
    if temp[-1] == "A":
        recursive(temp[:-1])
    if temp[0] == "B":
        reverse_str = temp[::-1]
        recursive(reverse_str[:-1])
    
recursive(T)
print(check)