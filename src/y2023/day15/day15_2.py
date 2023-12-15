from collections import defaultdict
import re

EXPECTED = 145


def solution(file):
    with open(file) as f:
        lines = f.read().splitlines()

        split = lines[0].split(',')

        boxes = defaultdict(list)
        lens = {}
        for entry in split:
            key = 0
            code = re.split("[= -]", entry)
            lens_id = code[0]
            for c in lens_id:
                key += ord(c)
                key = key * 17
                key = key % 256

            if '=' in entry:
                lens[lens_id] = int(code[1])
                if not lens_id in boxes[key]:
                    boxes[key].append(lens_id)
            elif '-' in entry:
                if lens_id in boxes[key]:
                    boxes[key].remove(lens_id)
            else:
                print('error')

        result = 0
        for k, v in boxes.items():
            for l in v:
                result += (k + 1) * (v.index(l) + 1) * lens[l]

        return result


if __name__ == '__main__':
    ex = solution('example.txt')

    if ex == EXPECTED:
        print("example", ex)
        print("solution", solution('input.txt'))
