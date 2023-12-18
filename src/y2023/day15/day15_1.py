from collections import defaultdict


def solution():
    with open('input.txt') as f:
        lines = f.read().splitlines()

        split = lines[0].split(',')

        return sum(map(hash, split))


def hash(entry):
    current = 0
    for c in entry:
        current = (current + ord(c)) * 17 % 256
    return current


if __name__ == '__main__':
    print(solution())
