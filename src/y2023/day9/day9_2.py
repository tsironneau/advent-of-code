from collections import defaultdict


def compute(nums):
    diffs = []
    if nums.count(0) == len(nums):
        return 0

    for i in range(len(nums) - 1):
        diffs.append(int(nums[i + 1]) - int(nums[i]))

    res = compute(diffs) + int(nums[-1])
    return res


def solution():
    with open('input.txt') as f:
        lines = f.read().split('\n')

        res = 0
        for line in lines:
            res += compute(line.split()[::-1])

        return res


if __name__ == '__main__':
    print(solution())
