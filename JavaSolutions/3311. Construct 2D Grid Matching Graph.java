/*
2D integer array edges representing an undirected graph having n nodes

edges[i] = [ui, vi] denotes an edge between nodes ui and vi.

Construct a 2D grid that satisfies these conditions:
The grid contains all nodes from 0 to n - 1 in its cells, 
with each node appearing exactly once.

Two nodes should be in adjacent grid cells (horizontally or vertically) 
if and only if there is an edge between them in edges.

It is guaranteed that edges can form a 2D grid that satisfies the conditions.

Questions...
1. Are the inputs guaranteed to start from 0 and are sorted in ascending order based
on the first element?
Answer: Yes
2. Are adjacent pieces on vertical and horizontal only? so diagonal pieces where only corners touch are not
considered adjacent?
Answer: Correct, a piece is only adjacent when it is next to another peice on horizontal and vertical axes only. 
3. Are the inputs guaranteed to be strictly a sequence of numbers from 0 to x where x is an integer and that the difference
between lets say y a number in the sequence and z being the next number in the sequence is only ever of magnitude 1? 
So in other words is the sequence rule defined by increasing the next number by 1? 
Answer: Yes

Brainstorming
- Three types of nodes, just like jigsaw, there are corner pieces, edge pieces, and center pieces.
	Each piece is distinguished off of the number of pieces adjacent.
	1. Corner pieces only have two adjacent.
	2. Edge pieces only have three adjacent. (these are the pieces on the perimeter)
	3. Center pieces have four adjacent. 

- I need to traverse the given edges 2d array, and deterimine whether a piece is one of the 
three types.

- Following a jigsaw analogy, I ususally solve jigsaw puzzles by first starting with a corner
piece, then I work my way down. I go row by row the hardest part about jigsaw just tends to be searching for pieces.
However, searching shouldnt be too hard here because adjacency relationships are already given to us. We know what pieces should
be next to what, the problem then becomes arranging them. 
 
	Algorithm
		1. Is this a corner piece? 
			-> yes? ok, check the pieces that can be adjacent to it
				-> is this adjacent piece an edge piece? 
					-> yes? ok, place it and check the pieces that can be adjacent to it
					-> no? ok, move onto a different piece. 
			-> no? ok, move onto a different piece. 

	That being said, in this process I would need to keep track 
	of what pieces I already tried so I have a visited array of some sort. 

	In context of the problem it would also be convenient to have some data structure that 
	I can easily search for the adjacent cells. Adjacency lists should work well here given that we are already
	provided the edges array containing all adjacency relationships between nodes.


Pseudocode
---------------

// define some constants
CORNER_PIECE = 2
EDGE_PIECE = 3
CENTER_PIECE = 4

function [][] constructAdjacencyList(edges[][]){
	nodes[] = findUniqueNodes();
	adjacencyList[][];

	for each edge in edges{
		node = edge[0];
		adjacent = edge[1];
		adjacencyList[node].append(adjacent);
		adjacencyList[adjacent].append(node);
	}

	return adjacencyList;
}

function [] findUniqueNodes(edges[][]){
	// get the first elements of each pair in edge
	firstElements[] = [];

	for each pair in edges
		firstElements.append(pair[0]);
	
	// convert firstElements to a set containing unique elements
	set = toSet(firstElements);	// this function may be language specific but since this
					// process is trivialised

	// convert the set to an array
	result = toArray(set);

	return result;
}

function int findCorner(adjacencyList[][]){
	for (int i = 0; i < adjacencyList.length; i++){
		if (adjacencyList[i].length == CORNER_PIECE){
			return i;
		}
}	

function [] constructFirstRow(adjacencyList[][], visited[], int indexOfCorner){
	firstRow[];
	firstRow.append(indexOfCorner);
	visited[indexOfCorner] = true;
	
	int indexOfCurNode = 1;	// index representing the position of the current node in the first row
	int curNode = indexOfCorner;
	int i = 0;	// tracks adjacent indeces
	int nextNode = adjacencyList[curNode][i];
	boolean finished = false;

	while (!finished){
		if (adjacencyList[curNode][i] == EDGE_PIECE){
			if (!visited[curNode]){
				firstRow.append(nextNode);
				visited[curNode] = true;
				
				// due to variables as pointers
				int temp = nextNode;
				curNode = temp;
				nextNode = adjacencyList[curNode][i];
			} else {
				i++;
				nextNode = adjacencyList[curNode][i];
			}
		} else if (adjacencyList[curNode][i] == CORNER_PIECE && visited[curNode] == false){
			firstRow.append(curNode);
			finished = true;
		} else {
			i++;
			nextNode = adjacencyList[curNode][i];
		}		
	}

	return finished;
}

function [] constructNextRow(adjacencyList[][], currentRow[], visited[]){
	nextRow[];

	for (i = 0; i < currentRow.length; i++){
		for each node in adjacencyList[currentRow[i]]{
			if (!visited[node]){
				nextRow.append(node);
				visited[node] = true;
				break;
			}
		}
	}

	returning nextRow;
}

// n is the number of grid cells total
def [][] constructGridLayout(edges[][], int n){
	adjacencyList[][] = constructAdjacencyList(edges);

	
	// find a corner piece
	int indexOfCorner = findCorner(adjacencyList);
	
	// construct the first row and getting final arrangement dimensions
	firstRow[] = constructFirstRow()
	col = firstRow.length();
	row = n/col;
	visited[adjacencyList.length]
	
	// arranging the grid
	finalArrangement[row][col]
	finalArrangment.append(firstRow);

	
	for (int i = 0; i < row-1; i++){
		for (int j = 0; j < col; j++){
			nextRow[] = consrtuctNextRow(finalArrangement[i]);
			finalArrangement[i+1] = nextRow;
		}
	}	
	
}
*/

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[][] constructGridLayout(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        boolean[] visit = new boolean[n];
        List<Integer> firstRow = populateFirstRow(graph, visit);
        int noOfColumn = firstRow.size();
        int noOfRow = n / noOfColumn;

        int[][] result = new int[noOfRow][noOfColumn];
        for (int j = 0; j < noOfColumn; j++) {
            result[0][j] = firstRow.get(j);
        }
        for (int i = 0; i < noOfRow - 1; i++) {
            for (int j = 0; j < noOfColumn; j++) {
                int currentNode = result[i][j];
                int nextNode = -1;
                for (int child : graph[currentNode]) {
                    if (!visit[child]) {
                        nextNode = child;
                        break;
                    }
                }
                result[i + 1][j] = nextNode;
                visit[nextNode] = true;
            }
        }
        return result;
    }

    private List<Integer> populateFirstRow(List<Integer>[] graph, boolean[] visit) {
        int lowestInDegreeNode = 0;
        for (int i = 0; i < graph.length; i++) {
            if (graph[lowestInDegreeNode].size() > graph[i].size()) {
                lowestInDegreeNode = i;
            }
        }

        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(lowestInDegreeNode);

        int currentNode = lowestInDegreeNode;
        visit[currentNode] = true;
        while (currentNode == lowestInDegreeNode || graph[currentNode].size() != graph[lowestInDegreeNode].size()) {
            int nextNode = -1;
            for (int child : graph[currentNode]) {
                if (!visit[child] && (nextNode == -1 || graph[nextNode].size() > graph[child].size())) {
                    nextNode = child;
                }
            }
            firstRow.add(nextNode);
            currentNode = nextNode;
            visit[currentNode] = true;
        }

        return firstRow;
    }
}