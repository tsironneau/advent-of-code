from collections import defaultdict
import re

EXPECTED = 51

directions = {
    "e": (1, 0),
    "w": (-1, 0),
    "n": (0, -1),
    "s": (0, 1),
}


def nextBeams(beam, lines):
    pos = beam[0]
    yMax = len(lines)
    xMax = len(lines[0])
    if not 0 <= pos[0] < xMax or not 0 <= pos[1] < yMax:
        return []

    symbol = get_symbol(pos, lines)
    direction = beam[1]
    next = nextPos(pos, direction)

    if symbol == '.' or (symbol == '-' and direction in ('e', 'w')) or (symbol == '|' and direction in ('s', 'n')):
        return [((next), direction, get_symbol(next, lines))]
    newDirection = direction
    if symbol == '/':
        if direction == 'e':
            newDirection = 'n'
        if direction == 'w':
            newDirection = 's'
        if direction == 'n':
            newDirection = 'e'
        if direction == 's':
            newDirection = 'w'
        next = nextPos(pos, newDirection)
        return [((next), newDirection, get_symbol(next, lines))]
    if symbol == '\\':
        if direction == 'e':
            newDirection = 's'
        if direction == 'w':
            newDirection = 'n'
        if direction == 'n':
            newDirection = 'w'
        if direction == 's':
            newDirection = 'e'
        next = nextPos(pos, newDirection)
        return [((next), newDirection, get_symbol(next, lines))]
    if symbol == '|' and direction in ('e', 'w'):
        return [((nextPos(pos, 'n')), 'n', get_symbol(nextPos(pos, 'n'), lines)),
                (nextPos(pos, 's'), 's', get_symbol(nextPos(pos, 's'), lines))]
    if symbol == '-' and direction in ('n', 's'):
        return [(nextPos(pos, 'e'), 'e', get_symbol(nextPos(pos, 'e'), lines)),
                (nextPos(pos, 'w'), 'w', get_symbol(nextPos(pos, 'w'), lines))]

    return []


def get_symbol(pos, lines):
    yMax = len(lines)
    xMax = len(lines[0])
    if not 0 <= pos[0] < xMax or not 0 <= pos[1] < yMax:
        return '?'
    return lines[pos[1]][pos[0]]


def nextPos(pos, direction):
    next = (pos[0] + directions[direction][0], pos[1] + directions[direction][1])
    return next


def solution(file):
    with open(file) as f:
        lines = f.read().splitlines()

        yMax = len(lines)
        xMax = len(lines[0])

        result = []
        for x in range(xMax):
            start = ((x, 0), 's', lines[x][0])
            result = result + energizedCount(start, lines, xMax, yMax)
            start = ((x, yMax - 1), 'n', lines[x][yMax - 1])
            result = result + energizedCount(start, lines, xMax, yMax)
        for y in range(yMax):
            start = ((0, y), 'e', lines[xMax - 1][y])
            result = result + energizedCount(start, lines, xMax, yMax)
            start = ((xMax - 1, y), 'w', lines[xMax - 1][y])
            result = result + energizedCount(start, lines, xMax, yMax)
    return max(result)


def energizedCount(start, lines, xMax, yMax):
    beams = []
    beams.append(start)
    energized = set()
    energized.add(start[0])
    SEEN = set()
    while not len(beams) == 0:
        beam = beams.pop()
        next_beams = list(map(tuple, nextBeams(beam, lines)))
        for next_beam in next_beams:
            if next_beam not in SEEN:
                SEEN.add(next_beam)
                beams.append(next_beam)
        for beam in next_beams:
            pos = beam[0]
            if 0 <= pos[0] < xMax and 0 <= pos[1] < yMax:
                energized.add(pos)
    result = len(energized)
    return [result]


if __name__ == '__main__':

    ex = solution('example.txt')
    print("example", ex)
    if ex == EXPECTED:
        print("solution", solution('input.txt'))
