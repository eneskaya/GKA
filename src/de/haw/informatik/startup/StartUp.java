package de.haw.informatik.startup;

import org.jgraph.JGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.AbstractGraph;

import de.haw.informatik.gui.MainWindow;
import de.haw.informatik.tools.GraphFileReader;
import de.haw.informatik.tools.GraphFileWriter;


public class StartUp  {
	
	public static void main(String[] args) {
		
		GraphFileReader reader = new GraphFileReader("bsp/bsp6.graph");
		AbstractGraph<?, ?> graph = reader.getGraph();
		
		JGraphModelAdapter ma = new JGraphModelAdapter(graph);
		JGraph jgraph = new JGraph(ma);
		
		MainWindow mw = new MainWindow("Graphentheorie");
		mw.get_panelContainer().add(jgraph);
		
		GraphFileWriter writer = new GraphFileWriter(graph, reader.getGraphProperties());
		writer.print();
		
	}
}