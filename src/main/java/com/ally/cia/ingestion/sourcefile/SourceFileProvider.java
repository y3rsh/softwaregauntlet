package com.ally.cia.ingestion.sourcefile;

import java.io.File;

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
        return SourceFile.getInstance(new File(filePath));
    }
}
