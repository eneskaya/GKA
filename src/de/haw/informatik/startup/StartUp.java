package de.haw.informatik.startup;

import de.haw.informatik.algorithms.BreadthFirstSearch;
import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.gui.GenericDialog;
import de.haw.informatik.gui.FileChooser;
import de.haw.informatik.gui.MainWindow;
import de.haw.informatik.tools.GraphFileReader;
import de.haw.informatik.tools.GraphFileWriter;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphModelAdapter;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.*;

public class StartUp  {

	private static Graph _graph;
	private static FileChooser _fc;
	private static GraphFileReader _reader;
	private static GraphFileWriter _writer;
	private static JGraphModelAdapter _adapter;

	public static void main(String[] args) {
		_fc = new FileChooser();
		registerUIActions();
	}

	private static void registerUIActions() {
		MainWindow mw = new MainWindow("Graphentheorie");

		// Graph - Graph laden...
		mw.getFileOpenMenuItem().addActionListener(e -> {

			String result = _fc.open();

            if(!result.equals("Keine Datei ausgewählt.")) {

				mw.getPanelContainer().removeAll();

				_reader = new GraphFileReader(result);
				_graph = _reader.getGraph();

				Set<EFVertex> vertices = _graph.vertexSet();
				_adapter = new JGraphModelAdapter(_graph);

				int x, y = 0;

				for (EFVertex v : vertices) {
					x = (int) (Math.random() * 900);
					y = (int) (Math.random() * 900);
					positionVertexAt(v, x, y);
				}

				JGraph jgraph = new JGraph(_adapter);

				jgraph.setGridEnabled(true);
				jgraph.setAntiAliased(true);
				jgraph.setBendable(true);

				mw.getPanelContainer().add(jgraph);
				mw.getPanelContainer().updateUI();
			}
        });

		// Graph - Graph speichern...
		mw.getFileSaveMenuItem().addActionListener(e -> {

			String result = _fc.save();

			if(!result.equals("Kein Speicherort ausgewählt.")) {
				_writer = new GraphFileWriter(_graph, _reader.getGraphProperties());

				try {
					_writer.write(result);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		// GenericDialog
		mw.getAlgoBFSMenuItem().addActionListener(e -> {
			Set<EFVertex> vertexSet = _graph.vertexSet();

			GenericDialog genericDialog = new GenericDialog();

			for (EFVertex v : vertexSet) {
				genericDialog.getComboBox1().addItem(v.toString());
				genericDialog.getComboBox2().addItem(v.toString());
			}

			genericDialog.getButtonOK().addActionListener(e1 -> {

				String source = (String) genericDialog.getComboBox1().getSelectedItem();
				String target = (String) genericDialog.getComboBox2().getSelectedItem();

				BreadthFirstSearch bfss = new BreadthFirstSearch(_graph, new EFVertex(source), new EFVertex(target));

				genericDialog.getTextArea1().setText("");
				genericDialog.getTextArea1().append(bfss.doSearch());
			});

			genericDialog.setVisible(true);

		});

		// Dijkstra
		mw.getAlgoDijkstraMenuItem().addActionListener(e -> {
			Set<EFVertex> vertexSet = _graph.vertexSet();

			GenericDialog genericDialog = new GenericDialog();

			for (EFVertex v : vertexSet) {
				genericDialog.getComboBox1().addItem(v.getName());
				genericDialog.getComboBox2().addItem(v.getName());
			}

			//ArrayList<EFVertex> vertexArray = new ArrayList<EFVertex>(Arrays.asList(vertexSet.toArray()));

			genericDialog.getButtonOK().addActionListener(e1 -> {
			});

			genericDialog.setVisible(true);

		});

		// A*
		mw.getAlgoAStarMenuItem().addActionListener(e -> {
			// TODO
		});
	}

	/**
	 * Randomly position a vertex.
	 *
	 * @param vertex
	 * @param x
	 * @param y
	 */
	private static void positionVertexAt(Object vertex, int x, int y)
	{
		DefaultGraphCell cell = _adapter.getVertexCell(vertex);
		Map<?, ?> attr = cell.getAttributes();
		Rectangle2D b = GraphConstants.getBounds(attr);

		Rectangle2D rect = new Rectangle2D.Double(x, y, b.getWidth(),
				b.getHeight());

		GraphConstants.setBounds(attr, rect);

		Map<DefaultGraphCell, Map<?, ?>> cellAttr = new HashMap<>();
		cellAttr.put(cell, attr);
		_adapter.edit(cellAttr, null, null, null);
	}
}