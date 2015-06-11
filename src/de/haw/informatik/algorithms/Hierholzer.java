package de.haw.informatik.algorithms;

import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.datatypes.EFWeightedEdge;
import org.jgrapht.Graph;

import java.util.*;

/**
 * Created by finnmasurat on 10.06.15.
 */
public class Hierholzer {


    public static List<EFVertex> getPath(Graph graph) {

        EFVertex start;
        EFVertex end;

        ArrayList<EFVertex> oddCounter = new ArrayList<>();
        Map<EFVertex, Double> map = new HashMap<>();


        Set<EFVertex> vertexSet = graph.vertexSet();
        for (EFVertex ef : vertexSet) {

            if (graph.edgesOf(ef).isEmpty()) {
                throw new IllegalArgumentException("Isolated vertex");
            } else if (graph.edgesOf(ef).size() % 2 == 1) {
                oddCounter.add(ef);
            }
        }

        if (oddCounter.size() > 2) {
            throw new IllegalArgumentException("More than two odd vertices");
        }

        if (oddCounter.size() == 2) {
            start = oddCounter.get(0);
            end = oddCounter.get(1);
        } else {
            start = (EFVertex) graph.vertexSet().iterator().next();
            end = start;
        }

        ArrayList<EFVertex> way = new ArrayList<>();
        int insertIndex = 0;
        while (graph.edgeSet().size() > 0) {

            List<EFVertex> w = findWay(graph, start, end);
            way.addAll(insertIndex, w);

            for (int i = 0; i < way.size(); i++) {
                EFVertex v = way.get(i);
                if (graph.containsVertex(v)) {
                    start = v;
                    end = v;
                    insertIndex = i;
                    break;
                }
            }

        }
        return way;
    }


    private static List<EFVertex> findWay(Graph graph, EFVertex start, EFVertex end) {
        ArrayList<EFVertex> way = new ArrayList<>();
        EFVertex currentVertex = start;

        do {
            way.add(currentVertex);
            ArrayList<EFVertex> neighbours = new ArrayList<>();
            Set<EFVertex> set = graph.edgesOf(currentVertex);
            for (EFVertex ef : set) {

                if (graph.getEdgeSource(ef) == currentVertex) {
                    neighbours.add((EFVertex) graph.getEdgeTarget(ef));
                } else {
                    neighbours.add((EFVertex) graph.getEdgeSource(ef));
                }

            }

            EFVertex next = neighbours.iterator().next();
            graph.removeEdge(currentVertex, next);
        } while (!currentVertex.equals(end));

        if (start.equals(end)) {
            way.add(end);
        }

        return way;

    }

}