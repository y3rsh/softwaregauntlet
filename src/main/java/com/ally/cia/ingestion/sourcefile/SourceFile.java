package com.ally.cia.ingestion.sourcefile;

import com.ally.cia.ingestion.sourcefile.row.SourceRow;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SourceFile {
    private List<SourceRow> fileRows;
    private Boolean exists;

    private SourceFile(File sourceFile) {
        importFileContent(sourceFile);
    }

    public static SourceFile getInstance(File sourceFile) {
        return new SourceFile(sourceFile);
    }

    private void importFileContent(File sourceFile) {
        final BufferedReader bufferedReader = getFileReader(sourceFile);
        exists = bufferedReader != null;
        fileRows = bufferedReader == null ? new ArrayList<>() : extractRows(bufferedReader);
    }

    private BufferedReader getFileReader(File sourceFile) {
        try {
            FileInputStream stream = new FileInputStream(sourceFile);
            return new BufferedReader(new InputStreamReader(stream));
        } catch (Exception e) {
            String filePathname = sourceFile == null ? "<NULL>" : sourceFile.getAbsolutePath();
            LoggerFactory.getLogger(this.getClass()).warn(String.format("Unable to read file at %s", filePathname));
            e.printStackTrace();
            return null;
        }
    }

    private List<SourceRow> extractRows(BufferedReader bufferedReader) {
        List<SourceRow> rowsRead = new ArrayList<>();
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                rowsRead.add(new SourceRow(line));
            }
        } catch (IOException e) {
            LoggerFactory.getLogger(this.getClass()).warn("Unable to read line from ingestion source file");
            e.printStackTrace();
        }
        return rowsRead;

    }

    List<SourceRow> getRows() {
        return fileRows;
    }

    public Boolean exists() {
        return exists;
    }
}
