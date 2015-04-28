package de.haw.informatik.startup;

import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.tools.GraphFileReader;
import org.jgrapht.graph.AbstractGraph;


public class StartUp  {
	
	public static void main(String[] args) {
		
		GraphFileReader reader = new GraphFileReader("bsp/bsp1.graph");
		AbstractGraph<?, ?> graph = reader.getGraph();

		EFVertex a = new EFVertex("a");


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