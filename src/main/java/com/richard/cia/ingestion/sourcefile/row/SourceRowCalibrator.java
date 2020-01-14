package com.richard.cia.ingestion.sourcefile.row;

import com.richard.cia.ingestion.metadata.fileattributes.IngestionFileAttributes;
import com.richard.cia.ingestion.sourcefile.row.field.SourceField;
import com.softwareonpurpose.calibrator4test.Calibrator;

public class SourceRowCalibrator extends Calibrator {
    private static String DESCRIPTION = "Source Row";
    private final IngestionFileAttributes expected;
    private final SourceRow actual;

    private SourceRowCalibrator(IngestionFileAttributes expected, SourceRow actual) {
        super(DESCRIPTION, expected, actual);
        this.expected = expected;
        this.actual = actual;
    }

    public static SourceRowCalibrator getInstance(IngestionFileAttributes expected, SourceRow actual) {
        return new SourceRowCalibrator(expected, actual);
    }

    @Override
    protected void executeVerifications() {
        int columnNumber = 0;
        for (String fieldType : expected.getFieldTypes()) {
            columnNumber += 1;
            SourceField field = actual.getField(columnNumber);
            String fieldValue = field == null ? null : field.getValue();
            boolean actual = field != null && field.isTransformable(fieldType);
            verify(String.format("Is Column %d '%s' transformable to %s", columnNumber, fieldValue, fieldType), true, actual);
        }
        verify("Field Count", columnNumber, actual.getFieldCount());
    }
}
