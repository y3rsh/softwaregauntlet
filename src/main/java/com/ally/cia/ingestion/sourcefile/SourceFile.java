package com.ally.cia.ingestion.sourcefile;

import com.ally.cia.ingestion.sourcefile.row.SourceRow;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SourceFile {
    private final List<SourceRow> fileRows = new ArrayList<>();
    private final Boolean exists;

    private SourceFile(File sourceFile) {
        final BufferedReader bufferedReader = getFileReader(sourceFile);
        exists = bufferedReader != null;
        if (exists) {
            importFileRows(bufferedReader);
        }
    }

    public static SourceFile getInstance(File file) {
        SourceFile sourceFile = new SourceFile(file);
        return sourceFile.exists() ? sourceFile : null;
    }

    private void importFileRows(BufferedReader bufferedReader) {
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileRows.add(new SourceRow(line));
            }
        } catch (IOException e) {
            LoggerFactory.getLogger(this.getClass()).warn("Unable to read line from ingestion source file");
            e.printStackTrace();
        }
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

    List<SourceRow> getRows() {
        return fileRows;
    }

    private Boolean exists() {
        return exists;
    }
}
