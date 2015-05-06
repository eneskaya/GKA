package de.haw.informatik.algorithms;

import de.haw.informatik.datatypes.EFEdge;
import de.haw.informatik.datatypes.EFVertex;

import org.jgrapht.Graph;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.WeightedPseudograph;

import java.util.*;

public class Dijkstra {

	public static String _source;
	public static String _target;
	public static Map<EFVertex, Double> _map = new HashMap<>();

	/**
	 * Computes the shortest path for two given vertices and a graph.
	 *
	 * @param graph
	 *            A graph
	 * @param source
	 *            The source vertex
	 */
	public static void computePath(Graph graph, EFVertex source) {
		_source = source.toString();
		PriorityQueue<EFVertex> leQueue = new PriorityQueue<>();
		leQueue.add(source);
		_map.put(source, 0.0);

		while (!leQueue.isEmpty()) {
			EFVertex currentVertex = leQueue.poll();

			for (EFEdge e : (Set<EFEdge>) graph.edgesOf(currentVertex)) {

				double weight = graph.getEdgeWeight(e);
				double distanceFromCurrentToTarget = _map.get(currentVertex)
						+ weight;// currentVertex.minimalDistance + weight;
				EFVertex target = (EFVertex) graph.getEdgeTarget(e);
				
				

				if (graph instanceof WeightedPseudograph || graph instanceof Pseudograph){
					EFVertex targetVertex2 = (EFVertex) graph.getEdgeSource(e);

					
					if (!_map.containsKey(targetVertex2)){
						_map.put(targetVertex2, distanceFromCurrentToTarget);
					}
					
					else if (distanceFromCurrentToTarget < _map.get(targetVertex2)){
						leQueue.remove(targetVertex2);
					

						_map.replace(targetVertex2, distanceFromCurrentToTarget);
						targetVertex2.predecessor = currentVertex;
						leQueue.add(targetVertex2);
					}
				}

				if (!_map.containsKey(target)){
					_map.put(target, distanceFromCurrentToTarget);
				}
				
				else if (distanceFromCurrentToTarget < _map.get(target)){
					leQueue.remove(target);
					_map.replace(target, distanceFromCurrentToTarget);
					target.predecessor = currentVertex;
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
		_target = target.toString();
		
		for (EFVertex v = target; v != null; v = v.predecessor) {
			path = " --> " + v.toString() + path;
		}

		return "Der Kürzeste Weg von " + _source.toString() + " nach "
				+ _target.toString() + " ist:\n" + _source.toString() + path
				+ "\n" + "Strecke: " + _map.get(target);
	}
}