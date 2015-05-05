package de.haw.informatik.startup;

import de.haw.informatik.algorithms.BreadthFirstSearch;
import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.gui.BFS;
import de.haw.informatik.gui.FileChooser;
import de.haw.informatik.gui.MainWindow;
import de.haw.informatik.tools.GraphFileReader;
import de.haw.informatik.tools.GraphFileWriter;
import org.jgraph.JGraph;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphModelAdapter;

import java.io.IOException;
import java.util.Set;

public class StartUp  {

	private static Graph _graph;
	private static FileChooser _fc;
	private static GraphFileReader _reader;
	private static GraphFileWriter _writer;

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


				JGraph jgraph = new JGraph(new JGraphModelAdapter(_graph));

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

		// BFS
		mw.getAlgoBFSMenuItem().addActionListener(e -> {
			Set<EFVertex> vertexSet = _graph.vertexSet();

			BFS bfs = new BFS();

			for (EFVertex v : vertexSet) {
				bfs.getComboBox1().addItem(v.toString());
				bfs.getComboBox2().addItem(v.toString());
			}

			bfs.getButtonOK().addActionListener(e1 -> {

				String source = (String) bfs.getComboBox1().getSelectedItem();
				String target = (String) bfs.getComboBox2().getSelectedItem();

				BreadthFirstSearch bfss = new BreadthFirstSearch(_graph, new EFVertex(source), new EFVertex(target));

				bfs.getTextArea1().append(bfss.doSearch());
			});

			bfs.setVisible(true);

		});

		// Dijkstra
		mw.getAlgoDijkstraMenuItem().addActionListener(e -> {

		});

		// A*
		mw.getAlgoAStarMenuItem().addActionListener(e -> {

		});
	}
}