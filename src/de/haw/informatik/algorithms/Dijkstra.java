package de.haw.informatik.algorithms;

import de.haw.informatik.datatypes.EFEdge;
import de.haw.informatik.datatypes.EFVertex;

import org.jgrapht.Graph;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.WeightedPseudograph;

import java.util.*;

public class Dijkstra {

	public static EFVertex _source;
	public static EFVertex _target;
	public static Map<EFVertex, Double> _map = new HashMap<>();
	public static int _graphAccesses;

	/**
	 * Computes the shortest path for two given vertices and a graph.
	 *
	 * @param graph
	 *            A graph
	 * @param source
	 *            The source vertex
	 */
	public static void computePath(Graph graph, EFVertex source) {
		_source = source;
		_graphAccesses = 0;

		Set<EFVertex> vertexes = graph.vertexSet();
		_graphAccesses++;

		for (EFVertex v : vertexes) {
			_map.put(v, Double.POSITIVE_INFINITY);
		}

		PriorityQueue<EFVertex> leQueue = new PriorityQueue<>((o1, o2) -> {
            return Double.compare(_map.get(o1), _map.get(o2));
        });

		leQueue.add(source);
		_map.put(source, 0.0);

		while (!leQueue.isEmpty()) {
			EFVertex currentVertex = leQueue.poll();

			Set<EFEdge> set = graph.edgesOf(currentVertex);
			_graphAccesses++;

			for (EFEdge e : set) {

				double weight = graph.getEdgeWeight(e);
				_graphAccesses++;
				double distanceFromCurrentToTarget = _map.get(currentVertex) + weight;

				EFVertex target = (EFVertex) graph.getEdgeTarget(e);
				_graphAccesses++;
				

				if (graph instanceof WeightedPseudograph || graph instanceof Pseudograph){
					EFVertex targetVertex2 = (EFVertex) graph.getEdgeSource(e);
					_graphAccesses++;

					if (!_map.containsKey(targetVertex2)){
						_map.put(targetVertex2, distanceFromCurrentToTarget);
					}
					
					if (distanceFromCurrentToTarget < _map.get(targetVertex2)){
						leQueue.remove(targetVertex2);
						_map.replace(targetVertex2, distanceFromCurrentToTarget);
						targetVertex2._predecessor = currentVertex;
						leQueue.add(targetVertex2);
					}
				}

				if (!_map.containsKey(target)){
					_map.put(target, distanceFromCurrentToTarget);
				}
				
				if (distanceFromCurrentToTarget < _map.get(target)){
					leQueue.remove(target);
					_map.replace(target, distanceFromCurrentToTarget);
					target._predecessor = currentVertex;
					leQueue.add(target);
				}
			}
		}
	}

	/**
	 * Returns the shortest path to the given target vertex. Already formatted
	 * output.
	 *
	 * @param target
	 *            The target EFVertex
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
