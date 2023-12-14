from collections import defaultdict


def load(line):
    blockedPos = 0
    result = 0
    for i in range(len(line)):
        if line[i] == '#':
            blockedPos = i + 1
        if line[i] == 'O':
            result += len(line) - blockedPos
            blockedPos += 1

    return result


def solution():
    with open('input.txt') as f:
        lines = f.read().splitlines()

        swapped = list(zip(*lines))

        result = 0
        for line in swapped:
            result += load(line)

        return result


if __name__ == '__main__':
    print(solution())
