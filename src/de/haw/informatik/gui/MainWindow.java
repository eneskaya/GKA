package de.haw.informatik.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow {

    private JFrame _frame;
    private JPanel _panelContainer;

    JMenuItem fileOpen;
    JMenuItem fileSave;

    JMenuItem bsp1;
    JMenuItem bsp2;
    JMenuItem bsp3;
    JMenuItem bsp4;
    JMenuItem bsp5;
    JMenuItem bsp6;
    JMenuItem kruskal;

    JMenuItem algoBFS;
    JMenuItem algoDijkstra;
    JMenuItem algoAStar;
    JMenuItem algoKruskal;
    JMenuItem algoPrim;

    JMenuItem randomGraphGenerate;
    JMenuItem randomConnectedGraphGenerate;

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

        bsp1 = new JMenuItem("bsp1.graph");
        bsp2 = new JMenuItem("bsp2.graph");
        bsp3 = new JMenuItem("bsp3.graph");
        bsp4 = new JMenuItem("bsp4.graph");
        bsp5 = new JMenuItem("bsp5.graph");
        bsp6 = new JMenuItem("bsp6.graph");
        kruskal = new JMenuItem("kruskal.graph");

        fileMenu.add(fileOpen);
        fileMenu.add(fileSave);

        fileMenu.addSeparator();

        fileMenu.add(bsp1);
        fileMenu.add(bsp2);
        fileMenu.add(bsp3);
        fileMenu.add(bsp4);
        fileMenu.add(bsp5);
        fileMenu.add(bsp6);
        fileMenu.add(kruskal);

        menuBar.add(fileMenu);

        JMenu algoMenu = new JMenu("Algorithmen");

        algoBFS = new JMenuItem("Breadth First Search");
        algoDijkstra = new JMenuItem("Dijkstra Shortest Path");
        algoAStar = new JMenuItem("A*");
        algoPrim = new JMenuItem("Prim (Priority Queue)");

        algoKruskal = new JMenuItem("Kruskal");

        algoMenu.add(algoBFS);
        algoMenu.add(algoDijkstra);
        algoMenu.add(algoAStar);
        algoMenu.addSeparator();
        algoMenu.add(algoKruskal);
        algoMenu.add(algoPrim);

        menuBar.add(algoMenu);

        JMenu randomMenu = new JMenu("Random");

        randomGraphGenerate = new JMenuItem("Zufälligen Graphen erstellen...");
        randomConnectedGraphGenerate = new JMenuItem("Zufälligen, zusammenhängenden Graphen erstellen...");

        randomMenu.add(randomGraphGenerate);
        randomMenu.add(randomConnectedGraphGenerate);
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

    // Temporary, remove

    public JMenuItem getBsp1() {
        return bsp1;
    }

    public JMenuItem getBsp2() {
        return bsp2;
    }

    public JMenuItem getBsp3() {
        return bsp3;
    }

    public JMenuItem getBsp4() {
        return bsp4;
    }

    public JMenuItem getBsp5() {
        return bsp5;
    }

    public JMenuItem getBsp6() {
        return bsp6;
    }

    public JMenuItem getKruskal() {
        return kruskal;
    }

    public JMenuItem getRandomConnectedGraphGenerate() {
        return randomConnectedGraphGenerate;
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
