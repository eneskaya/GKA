package de.haw.informatik.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow {

	private JFrame _frame;
	private JPanel _panelContainer;

	JMenuItem fileOpen;
	JMenuItem fileSave;

	JMenuItem algoBFS;
	JMenuItem algoDijkstra;
	JMenuItem algoAStar;

	JMenuItem randomGraphGenerate;

	public MainWindow(String title) {
		_frame = new JFrame(title);
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
	 *
	 */
	private void initMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("Graphen");

		fileOpen = new JMenuItem("Graph laden...");
		fileSave = new JMenuItem("Graph speichern...");

		fileMenu.add(fileOpen);
		fileMenu.add(fileSave);
		//fileMenu.addSeparator();

		menuBar.add(fileMenu);

		JMenu algoMenu = new JMenu("Algorithmen");

		algoBFS = new JMenuItem("Breadth First Search");
		algoDijkstra = new JMenuItem("Dijkstra Shortest Path");
		algoAStar = new JMenuItem("A*");

		algoMenu.add(algoBFS);
		algoMenu.add(algoDijkstra);
		algoMenu.add(algoAStar);

		menuBar.add(algoMenu);

		JMenu randomMenu = new JMenu("Random");

		randomGraphGenerate = new JMenuItem("Zufälligen Graph erstellen...");

		randomMenu.add(randomGraphGenerate);
		menuBar.add(randomMenu);

		_frame.setJMenuBar(menuBar);
	}


	public JMenuItem getFileOpenMenuItem() {
		return fileOpen;
	}

	public  JMenuItem getFileSaveMenuItem() {
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

	/**
	 * Return the JPanelContainer in which the Graph should be drawn.
	 *
	 * @return JPanel
	 * 			the panelContainer
	 */
	public JPanel getPanelContainer() {
		return _panelContainer;
	}

}
