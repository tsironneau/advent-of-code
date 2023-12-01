digits = {
    'one': '1',
    'two': '2',
    'three': '3',
    'four': '4',
    'five': '5',
    'six': '6',
    'seven': '7',
    'eight': '8',
    'nine': '9',
    '1': '1',
    '2': '2',
    '3': '3',
    '4': '4',
    '5': '5',
    '6': '6',
    '7': '7',
    '8': '8',
    '9': '9',
}


def find_code(line):
    index_of_first = 1000
    index_of_last = -1
    first = ""
    last = ""
    for key in digits.keys():
        first_occ = line.find(key)
        last_occ = line.rfind(key)
        if -1 < first_occ < index_of_first:
            index_of_first = first_occ
            first = digits[key]
        if last_occ > index_of_last:
            index_of_last = last_occ
            last = digits[key]

    res = first + last
    return int(res)


def solution():
    with open('input.txt') as f:
        lines = f.readlines()
        res = 0
        for line in lines:
            res += find_code(line)
        return res


if __name__ == '__main__':
    print(solution())
