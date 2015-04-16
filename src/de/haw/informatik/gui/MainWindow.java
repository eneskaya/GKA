package de.haw.informatik.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainWindow {
	
	private JFrame _frame;
	private JPanel _panelContainer;
	private JMenuBar _menuBar;
	private JMenuItem _menuItem;
	private JMenu _menu;
	
	public MainWindow(String title) {
		_frame = new JFrame(title);
		_frame.setSize(1200, 600);
		_frame.setResizable(false);
		
		_panelContainer = new JPanel();
		_panelContainer.setLayout(new BorderLayout());
		
		_menuBar = new JMenuBar();
		_menu = new JMenu("Datei");
		
		_menuBar.add(_menu);
		
		_menuItem = new JMenuItem();

		_menuBar.add(_menuItem);
		
		
		_frame.add(_panelContainer);
		_frame.setJMenuBar(_menuBar);
		_frame.setVisible(true);
	}

	/**
	 * @return the _panelContainer
	 */
	public JPanel get_panelContainer() {
		return _panelContainer;
	}
	
	
	
}
