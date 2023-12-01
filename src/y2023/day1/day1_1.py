def find_number(line):
    res = ""
    for c in line:
        if c.isdigit():
            res = res + c
            break
    for c in reversed(line):
        if c.isdigit():
            res = res + c
            break
    return int(res)


def solution():
    with open('input.txt') as f:
        lines = f.readlines()
        res = 0
        for line in lines:
            res += find_number(line)
        return res


if __name__ == '__main__':
    print(solution())
