package de.haw.informatik.tools;

import de.haw.informatik.datatypes.EFEdge;
import org.jgrapht.Graph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class GraphFileWriter {

    private Graph _graph;
    private String _header;

    /**
     * The GraphFileWriter extracts a given Graph to a .graph file
     *
     * @param graph        The Graph to write to a file to
     * @param propertyCode 0	undirected <br/>
     *                     1	directed <br/>
     *                     2 	attributed <br/>
     *                     3	weighted <br/>
     *                     4	directed AND attributed <br/>
     *                     5	directed AND weighted <br/>
     *                     6	directed AND attributed AND weighted <br/>
     *                     7	attributed AND weighted <br/>
     */
    public GraphFileWriter(Graph graph, int propertyCode) {
        _graph = graph;
        createHeader(propertyCode);
    }

    /**
     * Writes the given Graph to a file in the file system.
     *
     * @param pathToFile The name of the file.
     */
    public File write(String pathToFile) throws IOException {

        File file = new File(pathToFile);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);


        // If the header is empty leave out the line
        if (!_header.equals("")) {
            bw.write(_header);
            bw.newLine();
        }

        Set<EFEdge> edgesSet = (Set<EFEdge>) _graph.edgeSet();

        for (EFEdge efEdge : edgesSet) {
            bw.write(efEdge.formatted());
            bw.newLine();
        }

        bw.close();

        return file;
    }

    /**
     * Create the header based on the porperty code.
     */
    private void createHeader(int propertyCode) {
        switch (propertyCode) {
            case 0:
                _header = "";
                break;
            case 1:
                _header = "#directed";
                break;
            case 2:
                _header = "#attributed";
                break;
            case 3:
                _header = "#weighted";
                break;
            case 4:
                _header = "#directed #attributed";
                break;
            case 5:
                _header = "#directed #weighted";
                break;
            case 6:
                _header = "#directed #attributed #weighted";
                break;
            case 7:
                _header = "#attributed #weighted";
                break;
        }
    }
}
