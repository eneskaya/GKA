package de.haw.informatik.tools;

import de.haw.informatik.datatypes.EFDefaultEdge;
import de.haw.informatik.datatypes.EFEdge;
import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.datatypes.EFWeightedEdge;
import org.jgrapht.Graph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Reads a .graph file and extracts data to generate 
 * a JGraph ADT.
 * 
 * @author eneskaya, finnmasurat
 *
 */
public class GraphFileReader {

	private Scanner	_scanner;

	private List<String> _contentOfFile;
	
	private String _header;

	private Graph _graph;

	/**
	 * Reads a .graph file and extracts data to generate 
	 * a JGraphT ADT.
	 * 
	 * @param pathToFile 
	 * 				String representation for a path
	 */
	public GraphFileReader(String pathToFile) {
		File file = new File(pathToFile);

		try {
			_scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		_contentOfFile = new ArrayList<>();

		this.extractFileToArray();

		if (this.checkIfHeaderIsPresentAndValid()) {
			_header = _contentOfFile.get(0);
			_contentOfFile.remove(0);
		}
	}
	
	/**
	 * Puts the contents of a .graph file into an array for later usage.
	 * 
	 */
	private void extractFileToArray() {
		while(_scanner.hasNextLine()) {
			_contentOfFile.add(_scanner.nextLine());
		}

		List<String> temporary = new ArrayList<>();

		for (String s : _contentOfFile) {
			if (!s.equals("")) {
				temporary.add(s);
			}
		}

		_contentOfFile = temporary;
	}

	/**
	 * 
	 *
	 * @param graph
	 */
	private void parse(Graph graph) {

		Scanner scanner;
		Pattern pattern = Pattern.compile("[:\\s,]+");

		switch (this.getGraphProperties()) {

			case 0:
			case 1:
			case 3:
			case 5:

				for (String s : _contentOfFile) {

					scanner = new Scanner(s);
					scanner.useDelimiter(pattern);

					EFVertex firstVertex = new EFVertex(scanner.next());
					graph.addVertex(firstVertex);

					if (scanner.hasNext()) {
						EFVertex secondVertex = new EFVertex(scanner.next());
						graph.addVertex(secondVertex);
						EFEdge e = (EFEdge) graph.addEdge(firstVertex, secondVertex);

						if(scanner.hasNextInt()) {
							((WeightedGraph) graph).setEdgeWeight(e, scanner.nextInt());
						}
					}
				}

				break;

			case 2:
			case 4:
			case 6:
			case 7:

				for (String s : _contentOfFile) {

					scanner = new Scanner(s);
					scanner.useDelimiter(pattern);

					EFVertex firstVertex = new EFVertex(scanner.next(), scanner.nextInt());
					graph.addVertex(firstVertex);

					if (scanner.hasNext()) {
						EFVertex secondVertex = new EFVertex(scanner.next(), scanner.nextInt());
						graph.addVertex(secondVertex);
						EFEdge e = (EFEdge) graph.addEdge(firstVertex, secondVertex);

						if(scanner.hasNextInt()) {
							((WeightedGraph) graph).setEdgeWeight(e, scanner.nextInt());
						}
					}
				}

				break;
		}

		_graph = graph;

	}
	
	/**
	 * Checks if the file has a header with attributes for the graph.
	 * 
	 * @return true if, and only if, a header is present
	 */
	public boolean checkIfHeaderIsPresentAndValid() {
		return _contentOfFile.get(0).matches("(#(directed|weighted|attributed) ?)+");
	}
	
	/**
	 * Returns the properties of the graph specified in the header of a .graph file.
	 * 
	 * @return 	0	undirected <br/>
	 * 			1	directed <br/>
	 * 			2 	attributed <br/>
	 * 			3	weighted <br/>
	 * 			4	directed AND attributed <br/>
	 * 			5	directed AND weighted <br/>
	 * 			6	directed AND attributed AND weighted <br/>
	 * 			7	attributed AND weighted <br/>
	 */
	public int getGraphProperties() {
				
		if (_header != null) {
			
			// Put the attributes from the header in an array
			String[] properties = _header.split(" ");

			if (properties.length == 1) {
				
				// directed
				if (properties[0].equals("#directed")) {
					return 1;
				}
				
				// attributed
				if (properties[0].equals("#attributed")) {
					return 2;
				}
				
				// weighted
				if (properties[0].equals("#weighted")) {
					return 3;
				}
			}
			
			if (properties.length == 2) {
				
				// directed AND attributed
				if (properties[0].equals("#directed") && properties[1].equals("#attributed")) {
					return 4;
				}
				
				// directed AND weighted
				if (properties[0].equals("#directed") && properties[1].equals("#weighted")) {
					return 5;
				}
				
				// directed AND weighted
				if (properties[0].equals("#attributed") && properties[1].equals("#weighted")) {
					return 7;
				}
				
			}
			
			// all of the above
			if (properties.length == 3) {
				return 6;
			}
			
		}
		
		// undirected
		return 0;
	}

	
	/**
	 * Create the graph based on attribute code of header.
	 * 
	 */
	private void createGraph() {
		
		int graphAttributeCode = this.getGraphProperties();

		AbstractGraph<EFVertex, ?> graph;

		switch (graphAttributeCode) {
			case 0:
			case 2:
				graph = new Pseudograph<EFVertex, EFDefaultEdge>(EFDefaultEdge.class);
				this.parse(graph);
				break;
				
			case 1:
			case 4:
				graph = new DirectedPseudograph<EFVertex, EFDefaultEdge>(EFDefaultEdge.class);
				this.parse(graph);
				break;

			case 3:
			case 7:
				graph = new WeightedPseudograph<EFVertex, EFWeightedEdge>(EFWeightedEdge.class);
				this.parse(graph);
				break;

			case 5:
			case 6:
				graph = new DefaultDirectedWeightedGraph<EFVertex, EFWeightedEdge>(EFWeightedEdge.class);
				this.parse(graph);
				break;
		}
	}


	/**
	 * Get the graph.
	 * 
	 * @return Instance of a Graph
	 */
	public Graph getGraph() {
		
		this.createGraph();
		return _graph;
	}
	
}