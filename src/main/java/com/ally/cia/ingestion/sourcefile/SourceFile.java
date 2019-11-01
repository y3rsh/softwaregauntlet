package com.ally.cia.ingestion.sourcefile;

import com.ally.cia.ingestion.metadata.fileattributes.IngestionFileAttributes;
import com.ally.cia.ingestion.sourcefile.row.SourceRow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SourceFile {
    private final InputStream inputStream;
    private final IngestionFileAttributes fileSchema;
    private final List<SourceRow> fileRows;

    private SourceFile(InputStream inputStream, IngestionFileAttributes fileSchema) {
        this.inputStream = inputStream;
        this.fileSchema = fileSchema;
        this.fileRows = getFileRows();
    }

    public static SourceFile getInstance(InputStream inputStream, IngestionFileAttributes fileSchema) {
        return new SourceFile(inputStream, fileSchema);
    }

    List<SourceRow> getFileRows() {
        List<SourceRow> rows = new ArrayList<>();
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                rows.add(new SourceRow(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }
}
