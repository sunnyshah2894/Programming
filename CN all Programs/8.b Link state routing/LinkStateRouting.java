//Sumit Gouthaman
//TE Comps - D
//UID: 2012130056

/*
I have written this program according to the method discribed in Wikipedia.
Read the page on Link State Routing for more information.

I have included as many detailed comments as possible.
In case of doubts contact me.
*/

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

class LinkStateRouting
{
    public static void main(String[] args) throws IOException
    {
        int n; //Number of nodes
        System.out.println("Enter number of nodes: ");
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[][] globalGraph = new int[n][n]; //Graph of the entire Network
        System.out.println("Enter network graph: ");
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                globalGraph[i][j] = sc.nextInt();
            }
        }

        //Array of Node objects, one for each node
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++)
        {
            //The constructor takes the index of the node as well as total number of nodes
            nodes[i] = new Node(i, n);
        }

        /*
         * Here the output is going to be pretty detailed and long.
         * So for convenience, we are sending it to a file called "Output.txt"
         * The PrintWriter object is sent to every method of the Node Class
         * while we call it, so that the stuff each method prints cann be sent
         * to the file.
         */
        PrintWriter pw = new PrintWriter(new FileWriter("Output.txt"), true);

        /*
         * Now every node will run a routine to discover its neighbours.
         * For that we use the global graph.
         * In a real scenario, this would be achieved using a reachability
         * protocol. We are not using that here.
         */
        for (int i = 0; i < n; i++)
        {
            nodes[i].discoverNeighbours(globalGraph, pw);
        }

        /*
         * Now, every Node broadcasts information it has about its neighbours
         * to every node in the Network.
         *
         * This information is called Link State Advertisement
         */
        for (int i = 0; i < n; i++)
        {
            nodes[i].broadcastLinkStateAdvertisement(globalGraph, nodes, pw);

            pw.println("Current information in every node:");
            for (int j = 0; j < n; j++)
            {
                nodes[j].printInternalGraph(pw);
            }
			pw.println();
        }

        /*
         * Later  every node applies a path algorithm like Dijkstra's to find
         * optimum route to every other node.
         * In this code, the printDistanceToOtherNodes method does this.
         */

        pw.println("\nAfter every node applies Dijkstra's to the information it has...");
        for (int i = 0; i < n; i++)
        {
            nodes[i].printDistanceToOtherNodes(pw);
        }

        //Close the PrintWriter
        pw.close();
    }
}

/*
 * This is a very simple class that has fields to hold necessary data about a
 * LinkStateAdvertisement that node sents out to other nodes.
 */
class LinkStateAdvertisement
{
    int nodeNumber; //Index of the node that created this LSR
    int[] adjactentNodes;
    int[] distances;
    int age; //No of more hops this LSR can travel.
    int sequenceNumber; //Every LSR has a sequence number.
}


class Node
{

    int nodeNumber; //Index of this node
    int totalNodes;
    /*
     * the array graph[][] represents the information of the network
     * according to THIS node. It might differ from the globalGraph.
     * As more information comes along, the node updates this graph to be more
     * accurate.
     * After all node have finished sending their information, this internal
     * graph of every node should become almost similar to the globalGraph.
     * In short, each node learns about the network. And as it learns, it keeps
     * updating its internal graph.
     */
    int[][] graph;
    int[] sequenceNumbers; //Last sequuence number received from each node
    int selfSequenceNumber = 0;
    int[] neighbours;

    public Node(int number, int totalNodes)
    {
        nodeNumber = number;
        graph = new int[totalNodes][totalNodes];
        for (int i = 0; i < totalNodes; i++)
        {
            for (int j = 0; j < totalNodes; j++)
            {
                graph[i][j] = -1; //-1 represents no connection

            }
            graph[i][i] = 0;
        }
        graph[number][number] = 0;
        sequenceNumbers = new int[totalNodes];
        this.totalNodes = totalNodes;
    }

    public void discoverNeighbours(int[][] globalGraph, PrintWriter pw)
    {
        pw.println("For node " + nodeNumber + " Neighbours discovered: ");
        int numberOfAdjacentNodes = 0;
        for (int i = 0; i < totalNodes; i++)
        {
            if (globalGraph[nodeNumber][i] != -1)
            {
                numberOfAdjacentNodes++;
            }
        }
        numberOfAdjacentNodes--; //Not counting itself
        neighbours = new int[numberOfAdjacentNodes];
        int j = 0;
        for (int i = 0; i < totalNodes; i++)
        {
            if (globalGraph[nodeNumber][i] != -1)
            {
                if (nodeNumber != i)
                {
                    neighbours[j] = i;
                    pw.println("Node " + i);
                    graph[nodeNumber][i] = globalGraph[nodeNumber][i];
                    graph[i][nodeNumber] = globalGraph[nodeNumber][i];
                    j++;
                }
            }
        }
    }

    public void broadcastLinkStateAdvertisement(int[][] globalGraph, Node[] allNodes, PrintWriter pw)
    {
        /*
         * globalGraph is used to discover adjacent nodes
         * In the absence of this graph, in a real scenario this would be
         * achieved using a reachability protocol
         */

        /*
         * Now, every Node broadcasts information it has about its neighbours
         * to every node in the Network.
         * But to do so, it needs to have information of every other node.
         * But initiall this information is not available (which is why we use
         * Link State Routing in the first place!).
         * So, as a compromise, it sends the information only to its neighbours
         * (which it discovered in the previous step).
         * The neighbour then forwards it to its neighbours and so on.
         * The information is then flooded in the network.
         */

        pw.println("Node " + nodeNumber + " about to create LinkStateAdvertisement");

        LinkStateAdvertisement lsa = new LinkStateAdvertisement();
        lsa.nodeNumber = nodeNumber;
        lsa.sequenceNumber = ++selfSequenceNumber;
        lsa.age = 10; //Maximum length a packet can travel
        /*
         * Now the age can be selected as a random number if you want.
         * That would be more realistic.
         * It is not done here.
         */
        lsa.adjactentNodes = new int[neighbours.length];
        lsa.distances = new int[neighbours.length];

        for (int i = 0; i < neighbours.length; i++) {
            lsa.adjactentNodes[i] = neighbours[i];
            lsa.distances[i] = graph[nodeNumber][neighbours[i]];
        }

        for (int i = 0; i < neighbours.length; i++) {
            pw.println("Node " + nodeNumber + " sent LSA to node " + neighbours[i]);
            allNodes[neighbours[i]].receiveLinkStateAdvertisement(lsa, allNodes, pw);
            //Sent the LSA to every neighbour
        }

        pw.println();

    }

    public void receiveLinkStateAdvertisement(LinkStateAdvertisement lsa, Node[] allNodes, PrintWriter pw)
    {
        if (lsa.age == 0 || lsa.nodeNumber == nodeNumber || sequenceNumbers[lsa.nodeNumber] >= lsa.sequenceNumber)
        {
            return;
            //Ignore it if its too old
            //Ignore if from same node
            //Ignore if sequence of lsa is smaller than one already received
        }
        pw.println("Node " + nodeNumber + " received LSA of Node " + lsa.nodeNumber);
        lsa.age--; //Reduce age count after every hop.
        sequenceNumbers[lsa.nodeNumber] = lsa.sequenceNumber;

        for (int i = 0; i < lsa.adjactentNodes.length; i++)
        {
            int fromNode = lsa.nodeNumber;
            int toNode = lsa.adjactentNodes[i];

            /*
             * Now, the node tries to learn from the LSA that it received.
             * If it finds any new or better information, it updates the
             * internal graph
             */
            if (graph[fromNode][toNode] == -1 || graph[fromNode][toNode] > lsa.distances[i])
            {
                graph[fromNode][toNode] = lsa.distances[i];
                graph[toNode][fromNode] = lsa.distances[i];
            }
        }

        //Retransmit info to neighbours

        for (int i = 0; i < neighbours.length; i++)
        {
            allNodes[neighbours[i]].receiveLinkStateAdvertisement(lsa, allNodes, pw);
            pw.println("Node " + nodeNumber + " retransmitted LSA of node " + lsa.nodeNumber + " to node " + neighbours[i]);
        }

    }

    public void printDistanceToOtherNodes(PrintWriter pw)
    {
        int[] minDistances = Dijkstra.getShrotestPathToOtherNodes(graph, nodeNumber);
        pw.println("Distance to other nodes from node " + nodeNumber);
        for (int i = 0; i < totalNodes; i++)
        {
            pw.println(nodeNumber + " -> " + i + " : " + minDistances[i]);
        }
    }

    public void printInternalGraph(PrintWriter pw)
    {
        pw.println("Internal graph of node " + nodeNumber + ": ");
        for (int i = 0; i < totalNodes; i++)
        {
            for (int j = 0; j < totalNodes; j++)
            {
                pw.print(graph[i][j] + " ");
            }
            pw.println();
        }
    }
}

/*
 * This a class that implements a static method to figure out shortest
 * distance from a source node to every other node using Dijkstra's Algo.
 */
class Dijkstra
{

    /*
     * The method returns an array that has distance of the sourceNode
     * to every other node.
     * We are pretty nuch only concerned with the minimum distance and not the
     * actual path, so I have modified the algo to keep track of only the
     * minimum distance. This is a condensed version.
     *
     * You can implement this part of the code in any manner you want as long
     * as you are returning the dstances array correctly.
     */
    static int[] getShrotestPathToOtherNodes(int[][] graph, int startNode) {
        int n = graph.length;
        int[] distances = new int[n];
        ArrayList<Integer> visitedNodes = new ArrayList<Integer>();
        Arrays.fill(distances, -1);
        distances[startNode] = 0; //distance to self

        boolean[] visited = new boolean[n];
        visited[startNode] = true;
        int noOfNodesVisited = 1; //for the first firstNode
        visitedNodes.add(startNode);

        while (noOfNodesVisited < n)
        {
            int nearestNode = -1;
            int distanceToNearest = -1;
            for (int i = 0; i < visitedNodes.size(); i++)
            {
                int currentNode = visitedNodes.get(i);
                for (int j = 0; j < n; j++)
                {
                    if (j == currentNode || graph[currentNode][j] == -1 || visited[j])
                    {
                        continue;
                    }
                    if (graph[currentNode][j] + distances[currentNode] < distanceToNearest || nearestNode == -1)
                    {
                        nearestNode = j;
                        distanceToNearest = graph[currentNode][j] + distances[currentNode];
                    }
                }
            }
            visited[nearestNode] = true;
            distances[nearestNode] = distanceToNearest;
            visitedNodes.add(nearestNode);
            noOfNodesVisited++;
        }
        return distances;
    }
}
