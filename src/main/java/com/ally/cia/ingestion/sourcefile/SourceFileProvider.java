package com.ally.cia.ingestion.sourcefile;

import com.softwareonpurpose.gauntlet.Environment;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;

public class SourceFileProvider {
    private static final String INGESTION_FILE_PATH = Environment.getInstance().getIngestionFilePath();
    private static SourceFileProvider provider;

    public static SourceFileProvider getInstance() {
        if (provider == null) {
            provider = new SourceFileProvider();
        }
        return provider;
    }

    public SourceFile get(int jobId) {
        final String filePath = String.format("%s/jobNumber%d.txt", INGESTION_FILE_PATH, jobId);
        URL resource = this.getClass().getClassLoader().getResource(filePath);
        if(resource == null){
            LoggerFactory.getLogger(this.getClass()).warn(String.format("Resource file %s NOT FOUND", filePath));
            return null;
        }
        return SourceFile.getInstance(new File(resource.getFile()));
    }
}
