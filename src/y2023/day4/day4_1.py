def find_winning(line):
    numbers = line.split(': ')[1].split(' | ')
    left = numbers[0].split()
    right = numbers[1].split()
    inter = list(set(left).intersection(right))
    print(inter)
    return inter


def solution():
    with open('input.txt') as f:
        lines = f.readlines()
        res = 0
        for line in lines:
            wp = find_winning(line)
            if len(wp) > 0:
                res += 2**(len(wp)-1)

        return res


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print(solution())

