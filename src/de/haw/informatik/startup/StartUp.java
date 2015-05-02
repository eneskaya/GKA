package de.haw.informatik.startup;

import de.haw.informatik.algorithms.Dijkstra;
import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.tools.GraphFileReader;
import org.jgrapht.Graph;

import java.util.List;
import java.util.Set;

public class StartUp  {

	public static void main(String[] args) {
		
		GraphFileReader reader = new GraphFileReader("bsp/bsp3.graph");
		Graph graph = reader.getGraph();

		Set<EFVertex> set = (Set<EFVertex>) graph.vertexSet();

		Object[] a = set.toArray();

		EFVertex source = (EFVertex) a[6]; // Kiel
		EFVertex target = (EFVertex) a[1]; // Minden

		Dijkstra.computePath(graph, source);

		for (Object v : graph.vertexSet()) {
			System.out.println(((EFVertex) v).minimalDistance);
			List<EFVertex> path = Dijkstra.getShortestPathTo(target);
			System.out.println("Path: " + path);
		}
	}
}