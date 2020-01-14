package com.richard.cia.ingestion.sourcefile;

public class SourceFileProvider {
    private static SourceFileProvider provider;

    public static SourceFileProvider getInstance() {
        if (provider == null) {
            provider = new SourceFileProvider();
        }
        return provider;
    }

    public SourceFile get(int jobId) {
        return SourceFileRepository.getInstance().query(jobId);
    }
}
