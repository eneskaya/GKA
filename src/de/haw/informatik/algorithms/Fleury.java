package de.haw.informatik.algorithms;

import de.haw.informatik.datatypes.EFEdge;
import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.datatypes.EFWeightedEdge;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.EulerianCircuit;
import org.jgrapht.graph.WeightedPseudograph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("all")
public class Fleury {

    public static List<EFEdge> getCircuit(UndirectedGraph graph) {

        if (checkIsEulerian(graph)) throw new IllegalArgumentException("Kein Eulergraph.");

        EFVertex currentVertex = (EFVertex) graph.vertexSet().toArray()[0];
        List<EFEdge> currentTrail = new ArrayList<>();

        return null;
    }

    public static boolean isBridge(Graph graph, EFEdge edge) {

        Graph testGraph = new WeightedPseudograph(EFWeightedEdge.class);

        for (EFVertex v : (Set<EFVertex>) graph.vertexSet()) {
            testGraph.addVertex(v);
        }

        for (EFEdge e : (Set<EFEdge>) graph.edgeSet()) {
            testGraph.addEdge(graph.getEdgeSource(e), graph.getEdgeTarget(e));
        }

        testGraph.removeEdge(edge);
        boolean isBridge = false;

        EFVertex startVertex = (EFVertex) graph.vertexSet().toArray()[0];

        for (EFVertex v : (Set<EFVertex>) graph.vertexSet()) {

            if (v.equals(startVertex)) continue;

            BreadthFirstSearch bfs = new BreadthFirstSearch(graph, startVertex, v);

            if (bfs.doSearch().equals("Kein Ergebnis.")) {
                isBridge = true;
                break;
            }
        }
        return isBridge;
    }

    private static boolean checkIsEulerian(UndirectedGraph graph) {

        ArrayList<EFVertex> oddCounter = new ArrayList<>();

        boolean eularian = false;


        Set<EFVertex> vertexSet = graph.vertexSet();
        for (EFVertex ef : vertexSet) {

            if (graph.edgesOf(ef).isEmpty()) {
                throw new IllegalArgumentException("Isolated EFVertex");
            } else if (graph.edgesOf(ef).size() % 2 == 1) {
                oddCounter.add(ef);
            }
        }

        if (oddCounter.size() == 0) {
            eularian = true;
        }

        return eularian;

    }
}
