from collections import defaultdict


def solution():
    with open('input.txt') as f:
        lines = f.read().splitlines()

        split = lines[0].split(',')

        result = 0
        for entry in split:
            current = 0
            for c in entry:
                current += ord(c)
                current = current * 17
                current = current % 256
            result += current

        return result


if __name__ == '__main__':
    print(solution())
