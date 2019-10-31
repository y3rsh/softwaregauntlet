package com.ally.cia.ingestion.sourcefile;

import com.ally.cia.ingestion.metadata.fileattributes.IngestionFileAttributes;
import com.ally.cia.ingestion.metadata.fileattributes.IngestionFileAttributesProvider;
import com.ally.cia.ingestion.sourcefile.row.SourceRow;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SourceFile {
    private final List<SourceRow> fileRows = new ArrayList<>();
    private final IngestionFileAttributes fileSchema;

    private SourceFile(File jobId) {
        fileRows.addAll(getFileRows(jobId));
        fileSchema = IngestionFileAttributesProvider.getInstance().get(jobId);
    }

    public static SourceFile getInstance(File sourceFile) {
        return new SourceFile(sourceFile);
    }

    private List<SourceRow> getFileRows(Integer jobId) {
        List<SourceRow> rows = new ArrayList<>();
        final String filePath = String.format("/ingestionsource/jobNumber%d.txt", jobId);
        final InputStream resourceAsStream = getClass().getResourceAsStream(filePath);
        final BufferedReader bufferedReader;
        bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
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

    public SourceFileCalibrator getCalibrator() {
        return SourceFileCalibrator.getInstance(fileSchema, fileRows);
    }
}
