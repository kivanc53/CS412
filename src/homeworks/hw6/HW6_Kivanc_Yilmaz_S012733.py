def deleteEdge(graph, source, destination):
    for i, (neighbor, length) in enumerate(graph[source]):
        if neighbor == destination:
            break
    del graph[source][i]

    for i, (neighbor, length) in enumerate(graph[destination]):
        if neighbor == source:
            break
    del graph[destination][i]


def getPath(graph, source, destination, visitedPlaces):
    visitedPlaces[source] = True
    for first, second in graph[source]:
        if visitedPlaces[first]:
            continue

        if first == destination:
            return [(source, second), (destination, 0)]

        path = getPath(graph, first, destination, visitedPlaces)
        if path is not None:
            return [(source, second)] + path

    return None


def insertNew(graph, source, destination, distance, visited, nodeName):
    remainingLength = distance
    path = getPath(graph, source, destination, visited)
    edgeLength = path[0][1]
    currrent = 0
    while remainingLength >= edgeLength:
        remainingLength -= edgeLength
        currrent += 1
        edgeLength = path[currrent][1]

    currentNode = path[currrent][0]
    nextNode = path[currrent + 1][0]

    graph[currentNode].append((nodeName, remainingLength))
    graph[nextNode].append((nodeName, edgeLength - remainingLength))
    graph[nodeName] = [(currentNode, remainingLength), (nextNode, edgeLength - remainingLength)]
    deleteEdge(graph, currentNode, nextNode)


def limbLength(distanceMatrix, size, column):
    return min(distanceMatrix[i][column] + distanceMatrix[k][column] - distanceMatrix[i][k] \
               for i in range(size) for k in range(size) if i != column and k != column) // 2


def additivePhylogeny(distanceMatrix, size):
    return additivePhylogenyHelper(distanceMatrix, size, nodeName=2 * len(distanceMatrix) - 3)


def additivePhylogenyHelper(distanceMatrix, size, nodeName):
    if size == 2:
        distance = distanceMatrix[0][1]
        return {0: [(1, distance)], 1: [(0, distance)]}

    length = limbLength(distanceMatrix, size=size, column=size - 1)
    for i in range(size - 1):
        distanceMatrix[i][size - 1] -= length
        distanceMatrix[size - 1][i] = distanceMatrix[i][size - 1]

    for i in range(size - 1):
        for j in range(i + 1, size - 1):
            if distanceMatrix[i][j] == distanceMatrix[i][size - 1] + distanceMatrix[j][size - 1]:
                x = distanceMatrix[i][size - 1]
                src, dst = i, j
                break

    graph = additivePhylogenyHelper(distanceMatrix, size - 1, nodeName=nodeName - 1)

    visited = [False] * (2 * len(distanceMatrix))
    insertNew(graph, src, dst, x, visited, nodeName)

    graph[size - 1] = [(nodeName, length)]
    graph[nodeName].append((size - 1, length))

    return graph


class Cluster(object):
    def __init__(self, id, age, nodes):
        self.id = id
        self.age = age
        self.nodes = nodes

    def computeDistanceWithCluster(self, cluster, distanceMatrix):
        distanceSum = sum(distanceMatrix[i][j] for i in self.nodes for j in cluster.nodes)
        return distanceSum / float(len(self.nodes) * len(cluster.nodes))


def mergeClusters(c1, c2, id, age):
    return Cluster(id, age, c1.nodes + c2.nodes)


def findClosestClusters(clusterList, clusters, distanceMatrix):
    c1, c2 = min([(c1, c2) for c1 in clusters for c2 in clusters if c1 != c2],
                 key=lambda tup: clusterList[tup[0]].computeDistanceWithCluster(clusterList[tup[1]], distanceMatrix))
    return clusterList[c1], clusterList[c2]


def connectNodes(graph, parent, child):
    distance = parent.age - child.age

    graph[parent.id].append((child.id, distance))
    graph[child.id].append((parent.id, distance))


def updateDistanceMatrix(newCluster, clusterList, distanceMatrix):
    distances = [newCluster.computeDistanceWithCluster(cluster, distanceMatrix) for cluster in clusterList]

    for i in range(len(distanceMatrix)):
        distanceMatrix[i].append(distances[i])

    distanceMatrix.append(distances + [0])


def upgma(distanceMatrix, n):
    from collections import defaultdict

    clusterList = [Cluster(id, age=0, nodes=[id]) for id in range(n)]
    clusters = set([id for id in range(n)])
    graph = defaultdict(list)
    currentId = n

    while len(clusters) > 1:
        client1, client2 = findClosestClusters(clusterList, clusters, distanceMatrix)

        age = client1.computeDistanceWithCluster(client2, distanceMatrix) / 2

        newCluster = mergeClusters(client1, client2, currentId, age=age)
        currentId += 1

        connectNodes(graph, newCluster, client1)
        connectNodes(graph, newCluster, client2)

        clusters.remove(client1.id)
        clusters.remove(client2.id)
        clusters.add(newCluster.id)
        clusterList.append(newCluster)

        updateDistanceMatrix(newCluster, clusterList, distanceMatrix)

    return graph


if __name__ == '__main__':
    with open('data7C.txt') as inFile:
        n = int(inFile.readline())
        distanceMatrix = [list(map(int, line.split())) for line in inFile.readlines()]
        graph = additivePhylogeny(distanceMatrix, n)

    print("**********\nQuestion 1\n**********\n")

    for src in graph:
        for dst, length in graph[src]:
            print('%d->%d:%d' % (src, dst, length))

    print("\n**********\nQuestion 2\n**********\n")

    with open('data7D.txt') as inFile:
        n = int(inFile.readline())
        distanceMatrix = [list(map(int, inFile.readline().split())) for _ in range(n)]

        T = upgma(distanceMatrix, n)
    nodeCount = len(T)
    for u in range(nodeCount):
        for v, w in T[u]:
            print('%d->%d:%.3f' % (u, v, w))
