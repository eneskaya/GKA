package de.haw.informatik.algorithms;

import de.haw.informatik.datatypes.EFEdge;
import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.datatypes.EFWeightedEdge;
import org.jgrapht.Graph;
import org.jgrapht.graph.WeightedPseudograph;

import java.util.*;

public class Kruskal {

    public static ArrayList<EFWeightedEdge> allEdges = new ArrayList<>();

    public static Graph computeGraph(Graph graph) {

        // Put all edges of the graph in a list
        for (EFEdge edge : (Set<EFEdge>) graph.edgeSet()) {

            if (!(graph.getEdgeSource(edge).equals(graph.getEdgeTarget(edge)))) {
                allEdges.add((EFWeightedEdge) edge);
            }
        }

        // Sort the edges based on the edge weight
        allEdges.sort((o1, o2) -> o1.compareTo(o2));

        WeightedPseudograph<EFVertex, EFWeightedEdge> spanningTree =
                new WeightedPseudograph<>(EFWeightedEdge.class);

        for (EFEdge edge : allEdges) {

            EFVertex target = (EFVertex) graph.getEdgeTarget(edge);
            EFVertex source = (EFVertex) graph.getEdgeSource(edge);

            BreadthFirstSearch check = new BreadthFirstSearch(spanningTree, source, target);

            if (check.doSearch().equals("Kein Ergebnis.")) {

                if (!(spanningTree.containsVertex(target))) {
                    spanningTree.addVertex(target);
                }

                if (!(spanningTree.containsVertex(source))) {
                    spanningTree.addVertex(source);
                }

                EFWeightedEdge temporaryEdge = spanningTree.addEdge(source, target);
                spanningTree.setEdgeWeight(temporaryEdge, graph.getEdgeWeight(edge));
            }
        }

        return spanningTree;
    }
}
