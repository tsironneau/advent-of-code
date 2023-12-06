def isValidNumber(iLine, iCol, lines):
    for _l in (-1, 0, 1):
        for _c in (-1, 0, 1):
            if _l == 0 and _c == 0:
                continue
            l = iLine + _l
            c = iCol + _c
            if l < 0 or c < 0 or l >= len(lines) or c >= len(lines[0]) - 1:
                continue

            char = lines[l][c]
            if char != '.' and not char.isdigit():
                return True
    return False


def solution():
    with open('input.txt') as f:
        lines = f.readlines()

        res = 0
        currentNumber = ''
        isValid = False

        for iLine in range(len(lines)):
            line = lines[iLine]
            for iCol in range(len(line)):
                c = line[iCol]
                if c.isdigit():
                    currentNumber = currentNumber + c
                    if isValidNumber(iLine, iCol, lines):
                        isValid = True
                else:
                    if isValid:
                        res = res + int(currentNumber)
                    currentNumber = ''
                    isValid = False

        if isValid:
            res = res + int(currentNumber)

        return res


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print(solution())
