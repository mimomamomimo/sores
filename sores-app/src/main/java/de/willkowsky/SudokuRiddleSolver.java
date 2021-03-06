package de.willkowsky;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;

public class SudokuRiddleSolver {

    private static final Log LOG = LogFactory.getLog(SudokuRiddleSolver.class);

    private static final String EXAMPLE =
            "800904630\n" +
            "073020800\n" +
            "009005402\n" +
            "050700006\n" +
            "080502090\n" +
            "600003080\n" +
            "908600100\n" +
            "006090540\n" +
            "012308007\n";

    private static final String EXAMPLE_FILENAME = "example.txt";

    public static void main(String... args) throws IOException {

        if (args.length != 1) {
            LOG.info("Bitte den Dateinamen der Rätseldatei angeben!");
            LOG.info("( example legt ein Beispiel an )");
            System.exit(-1);
        }

        String fileName = args[0];

        if (fileName.equals("example")) {
            createExampleFile();
            System.exit(-1);
        }

        Playground playground = getPlaygroundForInputFile(fileName);

        String format = String.format("Beginne mit %s ...", fileName);
        LOG.info(format);
        LOG.info(playground.toString());
        playground.resolve(new BruteForceStrategy());

        LOG.info(playground.toString());
        String format1 = String.format(".. %s", playground.isValid() ? "geschafft!" : "hat nicht geklappt!");
        LOG.info(format1);

        System.exit(playground.isValid() ? 1 : -1);
    }

    private static Playground getPlaygroundForInputFile(String fileName) {
        Playground playground = null;
        try {
            File file = FileUtils.getFile(fileName);
            playground = new Playground();
            playground.init(file);
        } catch (IOException e) {
            LOG.error(String.format("Fehler beim öffnen der Datei %s:", fileName), e);
            System.exit(-1);
        }
        return playground;
    }

    private static String createExampleFile() throws IOException {
        File file = new File(EXAMPLE_FILENAME);
        FileUtils.writeStringToFile(file, EXAMPLE);
        return file.getAbsolutePath();
    }

}
