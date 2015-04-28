package de.haw.informatik.startup;

import de.haw.informatik.algorithms.BreadthFirstSearch;
import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.tools.GraphFileReader;
import org.jgrapht.graph.AbstractGraph;

import java.util.Set;


public class StartUp  {
	
	public static void main(String[] args) {
		
		GraphFileReader reader = new GraphFileReader("bsp/bsp1.graph");
		AbstractGraph<?, ?> graph = reader.getGraph();

		EFVertex a = new EFVertex("a");

		BreadthFirstSearch bfs = new BreadthFirstSearch(graph, a, new EFVertex("k"));
		Set<EFVertex> set = bfs.getNeighbourVertices(a);


//		JGraphModelAdapter ma = new JGraphModelAdapter(graph);
//		JGraph jgraph = new JGraph(ma);
//
//		MainWindow mw = new MainWindow("Graphentheorie");
//		mw.getPanelContainer().add(jgraph);
//
//		GraphFileWriter writer = new GraphFileWriter(graph, reader.getGraphProperties());
//		writer.print();
//
	}
}