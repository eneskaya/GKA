package de.haw.informatik.algorithms;

import de.haw.informatik.datatypes.EFEdge;
import de.haw.informatik.datatypes.EFVertex;
import org.jgrapht.Graph;

import java.util.*;

public class Dijkstra {

    /**
     * Computes the shortest path for two given vertices and a graph.
     *
     * @param graph
     *          A graph
     * @param source
     *          The source vertex
     */
    public static void computePath(Graph graph, EFVertex source) {

        source.minimalDistance = 0.0;
        PriorityQueue<EFVertex> leQueue = new PriorityQueue<>();
        leQueue.add(source);

        while(!leQueue.isEmpty()) {
            EFVertex currentVertex = leQueue.poll();

            for(EFEdge e : (Set<EFEdge>) graph.edgesOf(currentVertex)) {

                EFVertex target = (EFVertex) graph.getEdgeTarget(e);
                double weight = graph.getEdgeWeight(e);
                double distanceFromCurrentToTarget = currentVertex.minimalDistance + weight;

                if(distanceFromCurrentToTarget < target.minimalDistance) {
                    leQueue.remove(target);
                    target.minimalDistance = distanceFromCurrentToTarget;
                    target.predecessor = currentVertex;
                    leQueue.add(target);
                }

            }
        }
    }

    public static List<EFVertex> getShortestPathTo(EFVertex target)
    {
        List<EFVertex> path = new ArrayList<>();

        for (EFVertex v = target; v != null; v = v.predecessor) {
            path.add(v);
        }

        Collections.reverse(path);

        return path;
    }

}
