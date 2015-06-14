package de.haw.informatik.startup;

import de.haw.informatik.algorithms.*;
import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.gui.*;
import de.haw.informatik.tools.ConnectedGraphRandomGenerator;
import de.haw.informatik.tools.GraphFileReader;
import de.haw.informatik.tools.GraphFileWriter;
import de.haw.informatik.tools.GraphRandomGenerator;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphModelAdapter;

import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StartUp {

    private static Graph _graph;
    private static FileChooser _fc;
    private static JGraphModelAdapter _adapter;
    private static MainWindow _mw;
    private static int _propertyCodeForActualGraph;

    public static void main(String[] args) {

        _mw = new MainWindow();
        _fc = new FileChooser();
        registerUIActions();
    }

    private static void registerUIActions() {

        _mw.getFileOpenMenuItem().addActionListener(e -> fileOpenAction());

        _mw.getFileSaveMenuItem().addActionListener(e -> fileSaveAction());

        _mw.getAlgoBFSMenuItem().addActionListener(e -> bfsAction());

        _mw.getAlgoDijkstraMenuItem().addActionListener(e -> dijkstraAction());

        _mw.getAlgoAStarMenuItem().addActionListener(e -> aStarAction());

        _mw.getAlgoKruskalMenuItem().addActionListener(e -> kruskalAction());

        _mw.getRandomGraphGenerateItem().addActionListener(e -> randomGraphAction());

        _mw.getKruskal().addActionListener(e -> loadKruskal());

        _mw.getAlgoPrimMenuItem().addActionListener(e -> primAction());

        _mw.getRandomConnectedGraphGenerate().addActionListener(e -> randomConnectedGraphAction());

        _mw.getAlgoPrimFib().addActionListener(e -> primFibAction());
    }

    private static void fileOpenAction() {
        String result = _fc.open();

        if (!result.equals("Keine Datei ausgewählt.")) {

            _mw.getPanelContainer().removeAll();

            GraphFileReader _reader = new GraphFileReader(result);
            _graph = _reader.getGraph();
            _propertyCodeForActualGraph = _reader.getGraphProperties();

            Set<EFVertex> vertices = _graph.vertexSet();
            _adapter = new JGraphModelAdapter(_graph);

            int x, y = 0;

            for (EFVertex v : vertices) {
                x = (int) (Math.random() * 900 + 50);
                y = (int) (Math.random() * 700 + 50);
                positionVertexAt(v, x, y);
            }

            JGraph jgraph = new JGraph(_adapter);

            jgraph.setGridEnabled(true);
            jgraph.setAntiAliased(true);
            jgraph.setBendable(true);

            _mw.getPanelContainer().add(jgraph);
            _mw.getPanelContainer().updateUI();
        }
    }

    private static void fileSaveAction() {
        String result = _fc.save();

        if (!result.equals("Kein Speicherort ausgewählt.")) {
            // TODO
            GraphFileWriter _writer = new GraphFileWriter(_graph, _propertyCodeForActualGraph);

            try {
                _writer.write(result);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private static void bfsAction() {
        Set<EFVertex> vertexSet = _graph.vertexSet();

        AlgorithmsDialog algorithmsDialog = new AlgorithmsDialog();

        for (EFVertex v : vertexSet) {
            algorithmsDialog.getComboBox1().addItem(v);
            algorithmsDialog.getComboBox2().addItem(v);
        }

        algorithmsDialog.getButtonOK().addActionListener(e1 -> {

            EFVertex source = (EFVertex) algorithmsDialog.getComboBox1().getSelectedItem();
            EFVertex target = (EFVertex) algorithmsDialog.getComboBox2().getSelectedItem();

            BreadthFirstSearch bfss =
                    new BreadthFirstSearch(_graph, source, target);

            algorithmsDialog.getTextArea1().setText("");
            algorithmsDialog.getTextArea1().append(bfss.doSearch());
        });

        algorithmsDialog.setVisible(true);
    }

    private static void dijkstraAction() {
        Set<EFVertex> vertexSet = _graph.vertexSet();

        AlgorithmsDialog algorithmsDialog = new AlgorithmsDialog();

        for (EFVertex v : vertexSet) {
            algorithmsDialog.getComboBox1().addItem(v);
            algorithmsDialog.getComboBox2().addItem(v);
        }

        algorithmsDialog.getButtonOK().addActionListener(e1 -> {

            EFVertex source = (EFVertex) algorithmsDialog.getComboBox1().getSelectedItem();
            EFVertex target = (EFVertex) algorithmsDialog.getComboBox2().getSelectedItem();

            Dijkstra.computePath(_graph, source);

            algorithmsDialog.getTextArea1().setText("");

            algorithmsDialog.getTextArea1().append(Dijkstra.getShortestPathTo(target));

        });

        algorithmsDialog.setVisible(true);
    }

    private static void aStarAction() {
        Set<EFVertex> vertexSet = _graph.vertexSet();

        AlgorithmsDialog algorithmsDialog = new AlgorithmsDialog();

        for (EFVertex v : vertexSet) {
            algorithmsDialog.getComboBox1().addItem(v);
            algorithmsDialog.getComboBox2().addItem(v);
        }

        algorithmsDialog.getButtonOK().addActionListener(e1 -> {

            EFVertex source = (EFVertex) algorithmsDialog.getComboBox1().getSelectedItem();
            EFVertex target = (EFVertex) algorithmsDialog.getComboBox2().getSelectedItem();

            AStar.computePath(_graph, source);

            algorithmsDialog.getTextArea1().setText("");

            algorithmsDialog.getTextArea1().append(AStar.getShortestPathTo(target));

        });

        algorithmsDialog.setVisible(true);
    }

    private static void randomGraphAction() {
        RandomGenerateDialog rg = new RandomGenerateDialog();

        rg.getButtonOK().addActionListener(e1 -> {

            _mw.getPanelContainer().removeAll();

            _graph = GraphRandomGenerator
                    .getRandomGraph(
                            Integer.parseInt(rg.getTextField1().getText()),
                            Integer.parseInt(rg.getTextField2().getText())
                    );

            // Attributed, weighted
            _propertyCodeForActualGraph = 7;

            Set<EFVertex> vertices = _graph.vertexSet();
            _adapter = new JGraphModelAdapter(_graph);

            int x, y = 0;

            for (EFVertex v : vertices) {
                x = (int) (Math.random() * 900 + 50);
                y = (int) (Math.random() * 700 + 50);
                positionVertexAt(v, x, y);
            }

            JGraph jgraph = new JGraph(_adapter);

            jgraph.setGridEnabled(true);
            jgraph.setAntiAliased(true);
            jgraph.setBendable(true);

            _mw.getPanelContainer().add(jgraph);
            _mw.getPanelContainer().updateUI();

            rg.dispose();
        });

        rg.setVisible(true);
    }

    private static void randomConnectedGraphAction() {
        RandomGenerateDialog rg = new RandomGenerateDialog();

        rg.getButtonOK().addActionListener(e1 -> {

            _mw.getPanelContainer().removeAll();


            _graph = (new ConnectedGraphRandomGenerator())
                    .getRandomConnectedGraph(
                            Integer.parseInt(rg.getTextField1().getText())
                    );

            // Attributed, weighted
            _propertyCodeForActualGraph = 7;

            Set<EFVertex> vertices = _graph.vertexSet();
            _adapter = new JGraphModelAdapter(_graph);

            int x, y = 0;

            for (EFVertex v : vertices) {
                x = (int) (Math.random() * 900 + 50);
                y = (int) (Math.random() * 700 + 50);
                positionVertexAt(v, x, y);
            }

            JGraph jgraph = new JGraph(_adapter);

            jgraph.setGridEnabled(true);
            jgraph.setAntiAliased(true);
            jgraph.setBendable(true);

            _mw.getPanelContainer().add(jgraph);
            _mw.getPanelContainer().updateUI();

            rg.dispose();
        });

        rg.setVisible(true);
    }

    private static void kruskalAction() {

        if (_graph == null) {
            JOptionPane.showMessageDialog(_mw.getPanelContainer(), "Please open a graph first or create a random one.");
        } else {
            _mw.getPanelContainer().removeAll();

            _graph = Kruskal.computeGraph(_graph);

            Set<EFVertex> vertices = _graph.vertexSet();
            _adapter = new JGraphModelAdapter(_graph);

            int x, y;

            for (EFVertex v : vertices) {
                x = (int) (Math.random() * 900 + 50);
                y = (int) (Math.random() * 700 + 50);
                positionVertexAt(v, x, y);
            }

            JGraph jgraph = new JGraph(_adapter);

            jgraph.setGridEnabled(true);
            jgraph.setAntiAliased(true);
            jgraph.setBendable(true);

            _mw.getPanelContainer().add(jgraph);
            _mw.getPanelContainer().updateUI();
        }
    }

    private static void primAction() {

        if (_graph == null) {

            JOptionPane.showMessageDialog(_mw.getPanelContainer(), "Please open a graph first or create a random one.");
        } else {

            _mw.getPanelContainer().removeAll();

            PrimDialog pg = new PrimDialog();

            Set<EFVertex> vertices = _graph.vertexSet();

            for (EFVertex vertex : vertices) {
                pg.getComboBox1().addItem(vertex);
            }

            pg.getButtonOK().addActionListener(e -> {
                _graph = Prim.computeGraph(_graph, (EFVertex) pg.getComboBox1().getSelectedItem());

                _adapter = new JGraphModelAdapter(_graph);

                int x, y;

                for (EFVertex v : vertices) {
                    x = (int) (Math.random() * 900 + 50);
                    y = (int) (Math.random() * 700 + 50);
                    positionVertexAt(v, x, y);
                }

                JGraph jgraph = new JGraph(_adapter);

                jgraph.setGridEnabled(true);
                jgraph.setAntiAliased(true);
                jgraph.setBendable(true);

                _mw.getPanelContainer().add(jgraph);
                _mw.getPanelContainer().updateUI();

                pg.dispose();
            });

            pg.setVisible(true);
        }

    }

    private static void primFibAction() {
        if (_graph == null) {

            JOptionPane.showMessageDialog(_mw.getPanelContainer(), "Please open a graph first or create a random one.");
        } else {

            _mw.getPanelContainer().removeAll();

            PrimDialog pg = new PrimDialog();

            Set<EFVertex> vertices = _graph.vertexSet();

            for (EFVertex vertex : vertices) {
                pg.getComboBox1().addItem(vertex);
            }

            pg.getButtonOK().addActionListener(e -> {
                _graph = PrimFibonacciHeap.computeGraph(_graph, (EFVertex) pg.getComboBox1().getSelectedItem());

                _adapter = new JGraphModelAdapter(_graph);

                int x, y;

                for (EFVertex v : vertices) {
                    x = (int) (Math.random() * 900 + 50);
                    y = (int) (Math.random() * 700 + 50);
                    positionVertexAt(v, x, y);
                }

                JGraph jgraph = new JGraph(_adapter);

                jgraph.setGridEnabled(true);
                jgraph.setAntiAliased(true);
                jgraph.setBendable(true);

                _mw.getPanelContainer().add(jgraph);
                _mw.getPanelContainer().updateUI();

                pg.dispose();
            });

            pg.setVisible(true);
        }
    }

    private static void loadKruskal() {
        _mw.getPanelContainer().removeAll();

        GraphFileReader _reader = new GraphFileReader("bsp/kruskal.graph");
        _graph = _reader.getGraph();
        _propertyCodeForActualGraph = _reader.getGraphProperties();

        Set<EFVertex> vertices = _graph.vertexSet();
        _adapter = new JGraphModelAdapter(_graph);

        int x, y;

        for (EFVertex v : vertices) {
            x = (int) (Math.random() * 900 + 50);
            y = (int) (Math.random() * 700 + 50);
            positionVertexAt(v, x, y);
        }

        JGraph jgraph = new JGraph(_adapter);

        jgraph.setGridEnabled(true);
        jgraph.setAntiAliased(true);
        jgraph.setBendable(true);

        _mw.getPanelContainer().add(jgraph);
        _mw.getPanelContainer().updateUI();
    }

    private static void positionVertexAt(Object vertex, int x, int y) {
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