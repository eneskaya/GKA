package de.haw.informatik.tools;

import de.haw.informatik.datatypes.EFDefaultEdge;
import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.datatypes.EFWeightedEdge;
import org.jgrapht.graph.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Reads a .graph file and extracts data to generate 
 * a JGraph ADT.
 * 
 * @author eneskaya
 *
 */
public class GraphFileReader {

	private Scanner	_scanner;
	
	private File _file;
	
	private List<String> _contentOfFile;
	
	private String _header;
	
	private List<String> _verticesAndEdges;
	
	private AbstractGraph<?, ?> _graph;
	
	
	/**
	 * Reads a .graph file and extracts data to generate 
	 * a JGraphT ADT.
	 * 
	 * @param pathToFile 
	 * 				String representation for a path
	 */
	public GraphFileReader(String pathToFile) {
		_file = new File(pathToFile);
		
		try {
			_scanner = new Scanner(_file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		_contentOfFile = new ArrayList<String>();
		
		this.extractFileToArray();
		
		if (this.checkIfHeaderIsPresentAndValid()) {
			_header = _contentOfFile.get(0);
		}
		
		_verticesAndEdges = new ArrayList<String>();
		
		this.cleanUp();
	}
	
	/**
	 * Puts the contents of a .graph file into an array for later usage.
	 * 
	 */
	private void extractFileToArray() {
		while(_scanner.hasNextLine()) {
			_contentOfFile.add(_scanner.nextLine());
		}
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
				
		if (this.checkIfHeaderIsPresentAndValid()) {
			
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
	 * Remove the header and empty lines from _contentOfFile
	 * and puts it into _verticesAndEdges array list.
	 * 
	 */
	private void cleanUp() {		
		
		for (int i = 0; i < _contentOfFile.size(); i++) {
			
			if (_contentOfFile.get(i).equals("")) {
				continue;
			}
			
			_verticesAndEdges.add(_contentOfFile.get(i).replaceAll("\\s+", ""));
		}
		
		if (this.checkIfHeaderIsPresentAndValid()) {
			_verticesAndEdges.remove(0);
		}
	}
	
	/**
	 * Call the method based on attribute code of header.
	 * 
	 */
	private void createGraph() {
		
		int graphAttributeCode = this.getGraphProperties();
				
		switch (graphAttributeCode) {
			
			case 0:
				this.createUndirectedGraph();
				break;
				
			case 1:
				this.createDirectedGraph();
				break;

			case 2:
				this.createAttributedGraph();
				break;
				
			case 3:
				this.createWeightedGraph();
				break;
		
			case 4:
				this.createDirectedAttributedGraph();
				break;
				
			case 5:
				this.createDirectedWeightedGraph();
				break;
				
			case 6:
				this.createDirectedAttributedWeightedGraph();
				break;
				
			case 7:
				this.createAttributedWeightedGraph();
				break;
		}
	}
	
	// TODO Extract to other methods!
	
	private void createUndirectedGraph() {		
		AbstractGraph<EFVertex, EFDefaultEdge> graph = 
				new Pseudograph<EFVertex, EFDefaultEdge>(EFDefaultEdge.class);
		
		for (int i = 0; i < _verticesAndEdges.size(); i++) {
			
			String[] v = _verticesAndEdges.get(i).split(",");
			
			EFVertex knoten1 = new EFVertex(v[0]);
			EFVertex knoten2 = new EFVertex(v[1]);
			
			graph.addVertex(knoten1);
			graph.addVertex(knoten2);
			
			graph.addEdge(knoten1, knoten2);
		}
		
		_graph = graph;
	}
	
	private void createDirectedGraph() {
		AbstractGraph<EFVertex, EFDefaultEdge> graph = 
				new DirectedPseudograph<EFVertex, EFDefaultEdge>(EFDefaultEdge.class);
		
		for (int i = 0; i < _verticesAndEdges.size(); i++) {
			
			String[] v = _verticesAndEdges.get(i).split(",");
			
			EFVertex knoten1 = new EFVertex(v[0]);
			EFVertex knoten2 = new EFVertex(v[1]);
			
			graph.addVertex(knoten1);
			graph.addVertex(knoten2);
			
			graph.addEdge(knoten1, knoten2);
		}
		
		_graph = graph;
	}
	
	private void createAttributedGraph() {
		AbstractGraph<EFVertex, EFDefaultEdge> graph = 
				new Pseudograph<EFVertex, EFDefaultEdge>(EFDefaultEdge.class);
		
		for (int i = 0; i < _verticesAndEdges.size(); i++) {
			
			String[] v = _verticesAndEdges.get(i).split(",");
			
			String[] v1 = v[0].split(":");
			String[] v2 = v[1].split(":");
			
			EFVertex knoten1 = new EFVertex(v1[0], Integer.parseInt(v1[1]));
			EFVertex knoten2 = new EFVertex(v2[0], Integer.parseInt(v2[1]));
			
			graph.addVertex(knoten1);
			graph.addVertex(knoten2);

			graph.addEdge(knoten1, knoten2);
		}
		
		_graph = graph;
	}
	
	private void createWeightedGraph() {
		AbstractGraph<EFVertex, EFWeightedEdge> graph = 
				new WeightedPseudograph<EFVertex, EFWeightedEdge>(EFWeightedEdge.class);
		
		for (int i = 0; i < _verticesAndEdges.size(); i++) {
			
			String[] v = _verticesAndEdges.get(i).split(",");
			
			double weight = Double.valueOf(v[1].split("::")[1]);
			
			EFVertex knoten1 = new EFVertex(v[0]);
			EFVertex knoten2 = new EFVertex(v[1]);
			
			graph.addVertex(knoten1);
			graph.addVertex(knoten2);
						
			EFWeightedEdge edge = graph.addEdge(knoten1, knoten1);
			
			((WeightedPseudograph<EFVertex, EFWeightedEdge>) graph).setEdgeWeight(edge, weight);
		}

		_graph = graph;
	}
	
	private void createDirectedWeightedGraph() {
		AbstractGraph<EFVertex, EFWeightedEdge> graph = 
				new DefaultDirectedWeightedGraph<EFVertex, EFWeightedEdge>(EFWeightedEdge.class);
		
		for (int i = 0; i < _verticesAndEdges.size(); i++) {
			
			String[] v = _verticesAndEdges.get(i).split(",");
			
			double weight = Double.valueOf(v[1].split("::")[1]);
			
			EFVertex knoten1 = new EFVertex(v[0]);
			EFVertex knoten2 = new EFVertex(v[1].split("::")[0]);
			
			graph.addVertex(knoten1);
			graph.addVertex(knoten2);
						
			EFWeightedEdge edge = graph.addEdge(knoten1, knoten2);
			
			((DefaultDirectedWeightedGraph<EFVertex, EFWeightedEdge>) graph).setEdgeWeight(edge, weight);
		}

		_graph = graph;
	}
	
	private void createDirectedAttributedGraph() {
		AbstractGraph<EFVertex, EFDefaultEdge> graph = 
				new DirectedPseudograph<EFVertex, EFDefaultEdge>(EFDefaultEdge.class);
		
		for (int i = 0; i < _verticesAndEdges.size(); i++) {
			
			String[] v = _verticesAndEdges.get(i).split(",");
			
			String[] v1 = v[0].split(":");
			String[] v2 = v[1].split(":");
			
			EFVertex knoten1 = new EFVertex(v1[0], Integer.parseInt(v1[1]));
			EFVertex knoten2 = new EFVertex(v2[0], Integer.parseInt(v2[1]));
			
			graph.addVertex(knoten1);
			graph.addVertex(knoten2);

			graph.addEdge(knoten1, knoten2);
		}
		
		_graph = graph;
	}
	
	private void createAttributedWeightedGraph() {
		AbstractGraph<EFVertex, EFWeightedEdge> graph = 
				new WeightedPseudograph<EFVertex, EFWeightedEdge>(EFWeightedEdge.class);
		
		for (int i = 0; i < _verticesAndEdges.size(); i++) {
			
			String[] v = _verticesAndEdges.get(i).split(",");
			
			double weight = Double.valueOf(v[1].split("::")[1]);
			
			EFVertex knoten1 = 
					new EFVertex(v[0].split(":")[0], Integer.parseInt(v[0].split(":")[1]));
			
			EFVertex knoten2 = 
					new EFVertex(v[1].split(":")[0], Integer.parseInt( v[1].split(":")[1].split("::")[0]));

			graph.addVertex(knoten1);
			graph.addVertex(knoten2);
			
			EFWeightedEdge edge = graph.addEdge(knoten1, knoten2);
			
			((WeightedPseudograph<EFVertex, EFWeightedEdge>) graph).setEdgeWeight(edge, weight);
		}

		_graph = graph;
	}
	
	private void createDirectedAttributedWeightedGraph() {
		AbstractGraph<EFVertex, EFWeightedEdge> graph = 
				new DefaultDirectedWeightedGraph<EFVertex, EFWeightedEdge>(EFWeightedEdge.class);
		
		for (int i = 0; i < _verticesAndEdges.size(); i++) {
			
			String[] v = _verticesAndEdges.get(i).split(",");
			
			double weight = Double.valueOf(v[1].split("::")[1]);
			
			EFVertex knoten1 = 
					new EFVertex(v[0].split(":")[0], Integer.parseInt(v[0].split(":")[1]));
			
			EFVertex knoten2 = 
					new EFVertex(v[1].split(":")[0], Integer.parseInt( v[1].split(":")[1].split("::")[0]));

			
			graph.addVertex(knoten1);
			graph.addVertex(knoten2);

			
			EFWeightedEdge edge = graph.addEdge(knoten1, knoten2);
			
			((DefaultDirectedWeightedGraph<EFVertex, EFWeightedEdge>) graph).setEdgeWeight(edge, weight);
		}

		_graph = graph;
	}
	
	
	/**
	 * Returns the contents of a .graph file in an array
	 * 
	 * @return ArrayList<String> 
	 * 			Contents of the file in an array.
	 */
	public List<String> getArrayFromFile() {
		return _contentOfFile;
	}
	
	/**
	 * Get the graph.
	 * 
	 * @return Instance of a Graph
	 */
	public AbstractGraph<?, ?> getGraph() {
		
		this.createGraph();
		return _graph;
	}
	
}