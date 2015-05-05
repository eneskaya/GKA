package de.haw.informatik.gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser implements FileChooserInterface{

    JFileChooser _chooser;

    public FileChooser() {
        _chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.graph Dateien", "graph");
        _chooser.setFileFilter(filter);
    }

    @Override
    public String open() {

        int action = _chooser.showOpenDialog(null);

        if (action == JFileChooser.APPROVE_OPTION) {
            return _chooser.getSelectedFile().getAbsolutePath();
        }

        return "Keine Datei ausgewählt.";
    }

    @Override
    public String save() {
        int action = _chooser.showSaveDialog(null);

        if (action == JFileChooser.APPROVE_OPTION) {
            return _chooser.getSelectedFile().getAbsolutePath();
        }

        return "Kein Speicherort ausgewählt.";
    }

}
