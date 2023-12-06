def compute(time, distance):
    count = 0
    for i in range(1, time):
        newDistance = (time - i) * i
        if(newDistance > distance):
            count = count + 1
    return count


def solution():
    with open('input.txt') as f:
        lines = f.readlines()
        times, distances = (line.split(':')[1].replace(" ", "").split() for line in lines)

        res = 1
        for i in range(len(times)):
            res *= compute(int(times[i]), int(distances[i]))

        return res


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print(solution())

