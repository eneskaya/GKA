package de.haw.informatik.tools;

import de.haw.informatik.datatypes.EFEdge;
import org.jgrapht.graph.AbstractGraph;

import java.util.Set;

public class GraphFileWriter {
	
	private AbstractGraph<?, ?> _graph;
	private String _header;
	
	/**
	 * The GraphFileWriter extracts a given Graph to a .graph file
	 * 
	 * @param graph
	 * 			The Graph to write to a file to
	 * 
	 * @param propertyCode
	 * 			0	undirected <br/>
	 * 			1	directed <br/>
	 * 			2 	attributed <br/>
	 * 			3	weighted <br/>
	 * 			4	directed AND attributed <br/>
	 * 			5	directed AND weighted <br/>
	 * 			6	directed AND attributed AND weighted <br/>
	 * 			7	attributed AND weighted <br/>
	 */
	public GraphFileWriter(AbstractGraph<?, ?> graph, int propertyCode) {
		_graph = graph;
		createHeader(propertyCode);
	}
	
	public void print() {
		System.out.println(_header);
		
		Set<EFEdge> edgesSet = (Set<EFEdge>) _graph.edgeSet();
		
		for (EFEdge efEdge : edgesSet) {
			System.out.println(efEdge.formatted());
		}
	}

	/**
	 * Create the header based on the porperty code.
	 *
	 * @param propertyCode
	 */
	private void createHeader(int propertyCode) {
		switch (propertyCode) {
			case 0:
				_header = "";
				break;
			case 1:
				_header = "#directed";
				break;
			case 2:
				_header = "#attributed";
				break;
			case 3:
				_header = "#weighted";
				break;
			case 4:
				_header = "#directed #attributed";
				break;
			case 5:
				_header = "#directed #weighted";
				break;
			case 6:
				_header = "#directed #attributed #weighted";
				break;
			case 7:
				_header = "#attributed #weighted";
				break;
		}
	}
}
