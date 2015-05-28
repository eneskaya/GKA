package de.haw.informatik.gui;

interface FileChooserInterface {

    /**
     * Open a file.
     *
     * @return path
     * The absolute path to selected file.
     */
    String open();

    /**
     * Save a given file to the filesystem
     *
     * @return path
     * The absolute path to selected file.
     */
    String save();

}
