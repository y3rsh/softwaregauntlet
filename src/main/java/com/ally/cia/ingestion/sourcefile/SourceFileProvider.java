package com.ally.cia.ingestion.sourcefile;

import com.ally.cia.ingestion.metadata.fileattributes.IngestionFileAttributes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SourceFileProvider {
    private static SourceFileProvider provider;

    public static SourceFileProvider getInstance() {
        if (provider == null) {
            provider = new SourceFileProvider();
        }
        return provider;
    }

    public SourceFile get(int jobId) {
        final String filePath = String.format("/ingestionsource/jobNumber%d.txt", jobId);
        final InputStream resourceAsStream = getClass().getResourceAsStream(filePath);
        return SourceFile.getInstance(resourceAsStream, getIngestionFileAttributes(jobId));
    }

    public SourceFile getSourceFile(int jobId) throws FileNotFoundException {
        final String filePath = String.format("/ingestionsource/jobNumber%d.txt", jobId);
        File sourceFile = new File(filePath);
        return SourceFile.getInstance(new FileInputStream(sourceFile), getIngestionFileAttributes(jobId));
    }

    private IngestionFileAttributes getIngestionFileAttributes(int jobId) {
        return IngestionFileAttributes.getInstance(jobId);
    }
}
