package com.richard.cia.ingestion.sourcefile;

import com.richard.cia.ingestion.metadata.fileattributes.IngestionFileAttributes;
import com.richard.cia.ingestion.sourcefile.row.SourceRow;
import com.richard.cia.ingestion.sourcefile.row.SourceRowCalibrator;
import com.softwareonpurpose.calibrator4test.Calibrator;

public class SourceFileCalibrator extends Calibrator {
    private static final String DESCRIPTION = "Source File";

    private SourceFileCalibrator(IngestionFileAttributes expected, SourceFile actual) {
        super(DESCRIPTION, expected, actual);
        if (actual != null) {
            for (SourceRow row : actual.getRows()) {
                addChildCalibrator(SourceRowCalibrator.getInstance(expected, row));
            }
        }
    }

    public static SourceFileCalibrator getInstance(IngestionFileAttributes expected, SourceFile actual) {
        return new SourceFileCalibrator(expected, actual);
    }

    @Override
    protected void executeVerifications() {
    }
}