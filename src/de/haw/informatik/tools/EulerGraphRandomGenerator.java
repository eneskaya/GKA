package de.haw.informatik.tools;

import de.haw.informatik.datatypes.EFEdge;
import de.haw.informatik.datatypes.EFVertex;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.Pseudograph;

import java.util.*;

public class EulerGraphRandomGenerator {


    public static Graph constructGraph(int verticesAmount, int edgesAmount) {

        if (!isArgumentStatisfied(verticesAmount, edgesAmount))
            throw new IllegalArgumentException(
                    "The amount if edges must be bigger or equal the amount of vertices");

        UndirectedGraph graph = new Pseudograph(EFEdge.class);

        addVertices(graph, verticesAmount);

        addEdges(graph, verticesAmount, edgesAmount);

        return graph;
    }

    private static void addEdges(UndirectedGraph graph, int verticesAmount,
                                 int edgesAmount) {

        int eAmount = edgesAmount;

        //
        LinkedList<EFVertex> oddDegree = new LinkedList<EFVertex>();

        //
        LinkedList<EFVertex> evenDegree = new LinkedList<EFVertex>();

        // if()
        //
        makeGraphConnected(graph, verticesAmount, edgesAmount);

        //
        eAmount = eAmount - graph.edgeSet().size();

        for (EFVertex vertex : (Set<EFVertex>) graph.vertexSet()) {

            if (graph.degreeOf(vertex) % 2 == 1) {
                oddDegree.addFirst(vertex);
            } else {
                evenDegree.addFirst(vertex);
            }
        }

        //
        while (!oddDegree.isEmpty()) {

            Collections.shuffle(oddDegree);

            EFVertex v1 = oddDegree.pollFirst();
            EFVertex v2 = oddDegree.pollFirst();

            graph.addEdge(v1, v2);

            evenDegree.addFirst(v1);
            evenDegree.addFirst(v2);

            eAmount--;

        }

        while (eAmount > 1) {

            int k = Math.min(eAmount, verticesAmount);

            Collections.shuffle(evenDegree);

            for (int i = 0; i < k / 2; i++) {
                EFVertex v1 = evenDegree.pollFirst();
                EFVertex v2 = evenDegree.pollFirst();

                graph.addEdge(v1, v2);

                oddDegree.addFirst(v1);
                oddDegree.addFirst(v2);

                eAmount--;
            }

            Collections.shuffle(oddDegree);

            while (!oddDegree.isEmpty()) {
                EFVertex v1 = oddDegree.pollFirst();
                EFVertex v2 = oddDegree.pollFirst();

                graph.addEdge(v1, v2);

                evenDegree.addFirst(v1);
                evenDegree.addFirst(v2);

                eAmount--;
            }
        }

        if (eAmount == 1) {
            EFVertex v = evenDegree.getFirst();

            graph.addEdge(v, v);
            eAmount--;
        }

    }

    @SuppressWarnings("unchecked")
    private static void makeGraphConnected(Graph graph,
                                           int verticesAmount, int edgesAmount) {

        Set<EFVertex> setOfVertices = graph.vertexSet();

        if (verticesAmount <= edgesAmount
                && edgesAmount < (verticesAmount + verticesAmount / 2)) {

            List<EFVertex> listOfVertex = new ArrayList<>(setOfVertices);

            for (int i = 0; i < listOfVertex.size() - 1; i++) {
                EFVertex v1 = listOfVertex.get(i);
                EFVertex v2 = listOfVertex.get(i + 1);

                graph.addEdge(v1, v2);
            }

            graph.addEdge(listOfVertex.get(listOfVertex.size() - 1), listOfVertex.get(0));

        } else {

            List<EFVertex> reachable = new ArrayList<EFVertex>();
            List<EFVertex> unreached = new ArrayList<EFVertex>(setOfVertices);

            reachable.add(unreached.remove(0));

            // Make graph connected
            while (!unreached.isEmpty()) {

                EFVertex v1 = unreached.remove(0);
                EFVertex v2 = reachable.get((int) (Math.random() * reachable.size()));

                double weight = (v1.getAttributeValue() + v2.getAttributeValue()) / 2;

                EFEdge edge = (EFEdge) graph.addEdge(v1, v2);
                ((AbstractBaseGraph<EFVertex, EFEdge>) graph).setEdgeWeight(edge, weight);

                reachable.add(v1);
            }
        }

    }

    private static void addVertices(Graph graph, int verticesAmount) {
        for (int i = 0; i < verticesAmount; i++) {

            EFVertex v = new EFVertex("" + i + 1);
            graph.addVertex(v);
        }
    }

    public static boolean isArgumentStatisfied(int verticesAmount, int edgesAmount) {
        return edgesAmount >= verticesAmount;
    }
}
