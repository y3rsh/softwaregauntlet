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

    private IngestionFileAttributes getIngestionFileAttributes(int jobId) {
        return IngestionFileAttributes.getInstance(jobId);
    }

    public SourceFile getSourceFileFromClassPath(int jobId) {
        final String filePath = String.format("/ingestionsource/jobNumber%d.txt", jobId);
        final InputStream resourceAsStream = getClass().getResourceAsStream(filePath);
        return SourceFile.getInstance(resourceAsStream, getIngestionFileAttributes(jobId));
    }

    public SourceFile getSourceFile(int jobId) throws FileNotFoundException {
        final String filePath = String.format("/ingestionsource/jobNumber%d.txt", jobId);
        return SourceFile.getInstance(new FileInputStream(new File(filePath)), getIngestionFileAttributes(jobId));
    }

}
