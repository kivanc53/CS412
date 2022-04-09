from random import choice


def removeEdge(eulerianGraph, startingPoint, destinationPoint):
    eulerianGraph[startingPoint].remove(destinationPoint)
    if not eulerianGraph[startingPoint]:
        del eulerianGraph[startingPoint]
    return eulerianGraph


def eulerianCycle(eulerianGraph):
    start, edges = choice(list(eulerianGraph.items()))
    target = choice(edges)
    eulerianGraph = removeEdge(eulerianGraph, start, target)

    current = target
    cycle = [start, target]
    while current != start:
        edges = eulerianGraph[current]
        target = choice(edges)
        eulerianGraph = removeEdge(eulerianGraph, current, target)
        current = target
        cycle.append(current)

    while eulerianGraph:
        potentials = [(edge1, node) for edge1, node in enumerate(cycle) if node in eulerianGraph]
        edge, newStarting = choice(potentials)
        newCycle = cycle[edge:] + cycle[1:edge + 1]

        target = choice(eulerianGraph[newStarting])
        eulerianGraph = removeEdge(eulerianGraph, newStarting, target)
        current = target
        newCycle.append(current)
        while current != newStarting:
            edges = eulerianGraph[current]
            target = choice(edges)
            eulerianGraph = removeEdge(eulerianGraph, current, target)
            current = target
            newCycle.append(current)
        cycle = newCycle
    return cycle


def test():
    print("**************\nQuestion 4")
    eulerianGraph = {'0': ['3'],
                     '1': ['0'],
                     '2': ['1', '6'],
                     '3': ['2'],
                     '4': ['2'],
                     '5': ['4'],
                     '6': ['5', '8'],
                     '7': ['9'],
                     '8': ['7'],
                     '9': ['6']}

    print(eulerianCycle(eulerianGraph))
    print("****************")


def run():
    test()

run()

