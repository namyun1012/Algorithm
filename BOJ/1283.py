N = int(input())

arr = []
result_arr = []
alphabet_check = dict()

for i in range(N):

    temp_str = input()
    result_str = ""
    check = False
    
    ## First
    word_str = temp_str.split()
    word_count = 0
    for word in word_str:
        word_count += 1
        if(word_count > 1):
            result_str += " "
        
        if check:
            result_str += word
            continue
        
        c = word[0]
        
        if c.lower() in alphabet_check:
            result_str += word
            continue
        
        alphabet_check[c.lower()] = 1
        result_str += ("[" + c + "]") + word[1:]
        check = True
    
    if(check):
        result_arr.append(result_str)
        continue
    
    result_str = ""

    ## Second
    for c in temp_str:
        if not c.isalpha():
            result_str += c
            continue
        
        if check:
            result_str += c
            continue
        
        if c.lower() in alphabet_check:
            result_str += c
            continue
        
        alphabet_check[c.lower()] = 1
        result_str += ("[" + c + "]")
        check = True
              
    result_arr.append(result_str)
    
for ele in result_arr:
    print(ele)