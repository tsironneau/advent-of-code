from collections import defaultdict

def neighborStars(iLine, iCol, lines):
    res = set()
    for _l in (-1, 0, 1):
        for _c in (-1, 0, 1):
            if _l == 0 and _c == 0:
                continue
            l = iLine + _l
            c = iCol + _c
            if l < 0 or c < 0 or l >= len(lines) or c >= len(lines[0]) - 1:
                continue

            char = lines[l][c]
            if char == '*':
                res.add((l, c))
    return res


def solution():
    with open('input.txt') as f:
        lines = f.readlines()

        res = 0
        current_number = ''

        gears = defaultdict(list)
        stars = set()
        for iLine in range(len(lines)):
            line = lines[iLine]
            for iCol in range(len(line)):
                c = line[iCol]
                if c.isdigit():
                    current_number = current_number + c
                    neighbor_stars = neighborStars(iLine, iCol, lines)
                    if len(neighbor_stars) > 0:
                        stars.update(neighbor_stars)
                else:
                    for star in stars:
                        gears[star].append(int(current_number))
                    current_number = ''
                    stars = set()

        for gear in gears.values():
            if len(gear) == 2:
                res = res + gear[0] * gear[1]

        return res


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print(solution())
