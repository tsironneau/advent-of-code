from collections import defaultdict
import re

EXPECTED = 0


def solution(file):
    with open(file) as f:
        lines = f.read().splitlines()

        return 0


if __name__ == '__main__':

    ex = solution('example.txt')
    if ex == EXPECTED:
        print("example", ex)
        print("solution", solution('input.txt'))
