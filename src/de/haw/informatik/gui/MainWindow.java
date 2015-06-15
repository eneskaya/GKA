package de.haw.informatik.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow {

    private JFrame _frame;
    private JPanel _panelContainer;

    JMenuItem fileOpen;
    JMenuItem fileSave;

    JMenuItem kruskal;

    JMenuItem algoBFS;
    JMenuItem algoDijkstra;
    JMenuItem algoAStar;
    JMenuItem algoKruskal;
    JMenuItem algoPrim;
    JMenuItem algoPrimFib;

    JMenuItem algoHierholzer;
    JMenuItem algoFleury;

    JMenuItem randomGraphGenerate;
    JMenuItem randomConnectedGraphGenerate;

    JMenuItem randomEulerianGraphGenerate;

    public MainWindow() {
        _frame = new JFrame("Graphentheorie");
        _frame.setSize(1200, 900);
        _frame.setResizable(false);

        _panelContainer = new JPanel();
        _panelContainer.setLayout(new BorderLayout());

        initMenu();

        _frame.add(_panelContainer);
        _frame.setVisible(true);
    }

    /**
     * Initialize the Menu.
     */
    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Graphen");

        fileOpen = new JMenuItem("Graph laden...");
        fileSave = new JMenuItem("Graph speichern...");

        kruskal = new JMenuItem("kruskal.graph");

        fileMenu.add(fileOpen);
        fileMenu.add(fileSave);

        fileMenu.addSeparator();

        fileMenu.add(kruskal);

        menuBar.add(fileMenu);

        JMenu algoMenu = new JMenu("Algorithmen");

        algoBFS = new JMenuItem("Breadth First Search");
        algoDijkstra = new JMenuItem("Dijkstra Shortest Path");
        algoAStar = new JMenuItem("A*");
        algoPrim = new JMenuItem("Prim (Priority Queue)");
        algoPrimFib = new JMenuItem("Prim (Fibonacci Heap)");

        algoKruskal = new JMenuItem("Kruskal");

        algoHierholzer = new JMenuItem("Hierholzer");
        algoFleury = new JMenuItem("Fleury");

        algoMenu.add(algoBFS);
        algoMenu.add(algoDijkstra);
        algoMenu.add(algoAStar);
        algoMenu.addSeparator();
        algoMenu.add(algoKruskal);
        algoMenu.add(algoPrim);
        algoMenu.add(algoPrimFib);
        algoMenu.add(algoHierholzer);
        algoMenu.add(algoFleury);

        menuBar.add(algoMenu);

        JMenu randomMenu = new JMenu("Random");

        randomGraphGenerate = new JMenuItem("Randomisierten Graphen erstellen...");
        randomConnectedGraphGenerate = new JMenuItem("Randomisierten, zusammenhängenden Graphen erstellen...");
        randomEulerianGraphGenerate = new JMenuItem("Randomisierten eulerschen Graphen erstellen...");

        randomMenu.add(randomGraphGenerate);
        randomMenu.add(randomConnectedGraphGenerate);
        randomMenu.add(randomEulerianGraphGenerate);

        menuBar.add(randomMenu);

        _frame.setJMenuBar(menuBar);
    }


    public JMenuItem getFileOpenMenuItem() {
        return fileOpen;
    }

    public JMenuItem getFileSaveMenuItem() {
        return fileSave;
    }

    public JMenuItem getAlgoBFSMenuItem() {
        return algoBFS;
    }

    public JMenuItem getAlgoDijkstraMenuItem() {
        return algoDijkstra;
    }

    public JMenuItem getAlgoAStarMenuItem() {
        return algoAStar;
    }

    public JMenuItem getRandomGraphGenerateItem() {
        return randomGraphGenerate;
    }

    public JMenuItem getAlgoKruskalMenuItem() {
        return algoKruskal;
    }

    public JMenuItem getAlgoPrimMenuItem() {
        return algoPrim;
    }

    public JMenuItem getKruskal() {
        return kruskal;
    }

    public JMenuItem getRandomConnectedGraphGenerate() {
        return randomConnectedGraphGenerate;
    }

    public JMenuItem getRandomEulerianGraphGenerate() {
        return randomEulerianGraphGenerate;
    }

    public JMenuItem getAlgoPrimFib() {
        return algoPrimFib;
    }

    public JMenuItem getAlgoFleury() {
        return algoFleury;
    }

    public JMenuItem getAlgoHierholzer() {
        return algoHierholzer;
    }

    /**
     * Return the JPanelContainer in which the Graph should be drawn.
     *
     * @return JPanel
     * the panelContainer
     */
    public JPanel getPanelContainer() {
        return _panelContainer;
    }

}
