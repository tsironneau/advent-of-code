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
        return sum(map(find_number, lines))


if __name__ == '__main__':
    print(solution())
