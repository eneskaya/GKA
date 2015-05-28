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

        for (EFEdge ef : (Set<EFEdge>) graph.edgeSet()) {
            allEdges.add((EFWeightedEdge) ef);

        }

        allEdges.sort((o1, o2) -> o1.compareTo(o2));

        WeightedPseudograph<EFVertex, EFWeightedEdge> spanningTree =
                new WeightedPseudograph<>(EFWeightedEdge.class);

        for (EFEdge ef : allEdges) {

            EFVertex target = (EFVertex) graph.getEdgeTarget(ef);
            EFVertex source = (EFVertex) graph.getEdgeSource(ef);
            BreadthFirstSearch check = new BreadthFirstSearch(spanningTree, source, target);

            if ((check.doSearch().equals("Kein Ergebnis."))) {

                if (!(spanningTree.containsVertex(target))) {
                    spanningTree.addVertex(target);
                }
                if (!(spanningTree.containsVertex(source))) {
                    spanningTree.addVertex(source);
                }
                EFWeightedEdge e = spanningTree.addEdge(source, target);
                spanningTree.setEdgeWeight(e, graph.getEdgeWeight(ef));

            }
        }

        return spanningTree;
    }

}
