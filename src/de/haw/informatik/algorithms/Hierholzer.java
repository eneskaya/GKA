package de.haw.informatik.algorithms;

import de.haw.informatik.datatypes.EFEdge;
import de.haw.informatik.datatypes.EFEFVertex;
import org.jgrapht.Graph;

import java.util.*;

public class Hierholzer {

    public static List<EFEFVertex> getPath(Graph graph) {

        EFEFVertex start;
        EFEFVertex end;

        ArrayList<EFEFVertex> oddCounter = new ArrayList<>();
        Map<EFEFVertex, Double> map = new HashMap<>();


        Set<EFEFVertex> EFVertexSet = graph.EFVertexSet();
        for (EFEFVertex ef : EFVertexSet) {

            if (graph.edgesOf(ef).isEmpty()) {
                throw new IllegalArgumentException("Isolated EFVertex");
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
            start = (EFEFVertex) graph.EFVertexSet().iterator().next();
            end = start;
        }

        //---------------------------------------------------------

        ArrayList<EFEFVertex> way = new ArrayList<>();
        int insertIndex = 0;
        while (graph.edgeSet().size() > 0) {

            List<EFEFVertex> w = findWay(graph, start, end);
            way.addAll(insertIndex, w);

            for (int i = 0; i < way.size(); i++) {
                EFEFVertex v = way.get(i);
                if (graph.containsEFVertex(v)) {
                    start = v;
                    end = v;
                    insertIndex = i;
                    break;
                }
            }

        }
        return way;
    }


    private static List<EFEFVertex> findWay(Graph graph, EFEFVertex start, EFEFVertex end) {
        ArrayList<EFEFVertex> way = new ArrayList<>();
        EFEFVertex currentEFVertex = start;

        do {
            way.add(currentEFVertex);
            ArrayList<EFEFVertex> neighbours = new ArrayList<>();
            Set<EFEdge> set = graph.edgesOf(currentEFVertex);
            for (EFEdge ef : set) {

                if (graph.getEdgeSource(ef).equals(currentEFVertex)) {
                    neighbours.add((EFEFVertex) graph.getEdgeTarget(ef));
                } else {
                    neighbours.add((EFEFVertex) graph.getEdgeSource(ef));
                }
            }

            EFEFVertex next = neighbours.iterator().next();

            graph.removeEdge(currentEFVertex, next);
            if (graph.edgesOf(currentEFVertex).isEmpty()) {
                graph.removeEFVertex(currentEFVertex);
            }

            if (graph.edgesOf(next).isEmpty()) {
                graph.removeEFVertex(next);
            }
            currentEFVertex = next;
        } while (!currentEFVertex.equals(end));

        if (!start.equals(end)) {
            way.add(end);
        }

        return way;

    }

}
