package com.ally.cia.ingestion.sourcefile;

import com.ally.cia.ingestion.metadata.fileattributes.IngestionFileAttributes;
import com.ally.cia.ingestion.metadata.fileattributes.IngestionFileAttributesProvider;
import com.ally.cia.ingestion.sourcefile.row.SourceRow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SourceFile {
    private final List<SourceRow> fileRows = new ArrayList<>();
    private final IngestionFileAttributes fileSchema;

    private SourceFile(Integer jobId) {
        fileRows.addAll(getFileRows(jobId));
        fileSchema = IngestionFileAttributesProvider.getInstance().get(jobId);
    }

    public static SourceFile getInstance(Integer jobId) {
        return new SourceFile(jobId);
    }

    private List<SourceRow> getFileRows(Integer jobId) {
        List<SourceRow> rows = new ArrayList<>();
        final String filePath = String.format("/jobNumber%d.txt", jobId);
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
