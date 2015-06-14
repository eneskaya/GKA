package de.haw.informatik.algorithms;

import de.haw.informatik.datatypes.EFEdge;
import de.haw.informatik.datatypes.EFVertex;
import org.jgrapht.Graph;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.WeightedPseudograph;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra {

    public static EFVertex _source;
    public static EFVertex _target;
    public static Map<EFVertex, Double> _map = new HashMap<>();
    public static int _graphAccesses;

    /**
     * Computes the shortest path for two given vertices and a graph.
     *
     * @param graph  A graph
     * @param source The source EFVertex
     */
    public static void computePath(Graph graph, EFVertex source) {
        _source = source;
        _graphAccesses = 0;

        Set<EFVertex> EFVertexes = graph.vertexSet();
        _graphAccesses++;

        for (EFVertex v : EFVertexes) {
            _map.put(v, Double.POSITIVE_INFINITY);
        }

        PriorityQueue<EFVertex> leQueue = new PriorityQueue<>((o1, o2) -> {
            return Double.compare(_map.get(o1), _map.get(o2));
        });

        leQueue.add(source);
        _map.put(source, 0.0);

        while (!leQueue.isEmpty()) {
            EFVertex currentEFVertex = leQueue.poll();

            Set<EFEdge> set = graph.edgesOf(currentEFVertex);
            _graphAccesses++;

            for (EFEdge e : set) {

                double weight = graph.getEdgeWeight(e);
                _graphAccesses++;
                double distanceFromCurrentToTarget = _map.get(currentEFVertex) + weight;

                EFVertex target = (EFVertex) graph.getEdgeTarget(e);
                _graphAccesses++;


                if (graph instanceof WeightedPseudograph || graph instanceof Pseudograph) {
                    EFVertex targetEFVertex2 = (EFVertex) graph.getEdgeSource(e);
                    _graphAccesses++;

                    if (!_map.containsKey(targetEFVertex2)) {
                        _map.put(targetEFVertex2, distanceFromCurrentToTarget);
                    }

                    if (distanceFromCurrentToTarget < _map.get(targetEFVertex2)) {
                        leQueue.remove(targetEFVertex2);
                        _map.replace(targetEFVertex2, distanceFromCurrentToTarget);
                        targetEFVertex2._predecessor = currentEFVertex;
                        leQueue.add(targetEFVertex2);
                    }
                }

                if (!_map.containsKey(target)) {
                    _map.put(target, distanceFromCurrentToTarget);
                }

                if (distanceFromCurrentToTarget < _map.get(target)) {
                    leQueue.remove(target);
                    _map.replace(target, distanceFromCurrentToTarget);
                    target._predecessor = currentEFVertex;
                    leQueue.add(target);
                }
            }
        }
    }

    /**
     * Returns the shortest path to the given target EFVertex. Already formatted
     * output.
     *
     * @param target The target EFEFVertex
     * @return Formatted path representation
     */
    public static String getShortestPathTo(EFVertex target) {
        String path = "";
        _target = target;

        for (EFVertex v = target; v != null; v = v._predecessor) {
            path = " --> " + v.getName() + path;
        }

        return "Der Kürzeste Weg von "
                + _source.getName() + " nach "
                + _target.getName() + " ist:\n" + path
                + "\n" + "Strecke: " + _map.get(target)
                + "\n" + "Anzahl der Zugriffe: " + _graphAccesses;
    }
}
