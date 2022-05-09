import os


class BLOSUM62ScoringMatrix(object):
    def __init__(self):
        with open(os.path.join(os.path.dirname(__file__), 'BLOSUM62.txt')) as input_data:
            items = [line.strip().split() for line in input_data.readlines()]
            self.scoring_matrix = {(item[0], item[1]): int(item[2]) for item in items}

    def __getitem__(self, pair):
        return self.scoring_matrix[pair[0], pair[1]]


def global_alignment(string1, string2, scoringMatrix, indelPenalty):
    matrix = [[0] * (len(string2) + 1) for _ in range(len(string1) + 1)]
    backtrack = [[0] * (len(string2) + 1) for _ in range(len(string1) + 1)]

    for i in range(1, len(string1) + 1):
        matrix[i][0] = -i * indelPenalty
    for j in range(1, len(string2) + 1):
        matrix[0][j] = -j * indelPenalty

    for i in range(1, len(string1) + 1):
        for j in range(1, len(string2) + 1):
            scores = [matrix[i - 1][j] - indelPenalty, matrix[i][j - 1] - indelPenalty, matrix[i - 1][j - 1] + scoringMatrix[string1[i - 1], string2[j - 1]]]
            matrix[i][j] = max(scores)
            backtrack[i][j] = scores.index(matrix[i][j])

    addIndelPenalty = lambda word, i: word[:i] + '-' + word[i:]
    str1Aligned, str2Aligned = string1, string2
    i, j = len(string1), len(string2)
    max_score = str(matrix[i][j])

    while i * j != 0:
        if backtrack[i][j] == 0:
            i -= 1
            str2Aligned = addIndelPenalty(str2Aligned, j)
        elif backtrack[i][j] == 1:
            j -= 1
            str1Aligned = addIndelPenalty(str1Aligned, i)
        else:
            i -= 1
            j -= 1

    for _ in range(i):
        str2Aligned = addIndelPenalty(str2Aligned, 0)
    for _ in range(j):
        str1Aligned = addIndelPenalty(str1Aligned, 0)

    return max_score, str1Aligned, str2Aligned


def main():
    str1, str2 = "PLEASANTLY", "MEANLY"
    alignment = global_alignment(str1, str2, BLOSUM62ScoringMatrix(), 5)
    print('\n'.join(alignment))


if __name__ == '__main__':
    main()
