package com.ally.cia.ingestion.sourcefile.row.field;

public class SourceField {
    private final String value;

    private SourceField(String fieldValue) {
        value = fieldValue;
    }

    public static SourceField getInstance(String fieldValue) {
        return new SourceField(fieldValue);
    }

    public boolean isTransformable(String fieldType) {
        int endIndex = fieldType.contains("(") ? fieldType.indexOf("(") : fieldType.length();
        String type = fieldType.substring(0, endIndex).toLowerCase();
        try {
            if ("int".equals(type)) {
                Integer.parseInt(value);
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return isTransformableAsFixedLengthString(fieldType);
    }

    private boolean isTransformableAsFixedLengthString(String fieldType) {
        int lengthBeginningIndex = fieldType.indexOf("(") + 1;
        int lengthEndingIndex = fieldType.indexOf(")");
        String substring = fieldType.substring(lengthBeginningIndex, lengthEndingIndex);
        int typeLength = Integer.parseInt(substring);
        return value.length() <= typeLength;
    }

    public String getValue() {
        return value;
    }
}
