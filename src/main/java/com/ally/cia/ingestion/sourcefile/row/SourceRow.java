package com.ally.cia.ingestion.sourcefile.row;

import com.ally.cia.ingestion.sourcefile.row.field.SourceField;

import java.util.ArrayList;
import java.util.List;

public class SourceRow {
    private List<String> rows = new ArrayList<>();

    public SourceRow(String data) {
        char SEPARATOR = ',';
        StringBuilder currentStr = new StringBuilder();
        boolean inQuotes = false;
        // For each character
        for (char b : data.toCharArray()) {
            if (b == '\"') // Quotes are closing or opening
                inQuotes = !inQuotes;
            else if (b == SEPARATOR) // Comma
            {
                if (!inQuotes) // If not in quotes, end of current string, add it to result
                {
                    rows.add(currentStr.toString());
                    currentStr = new StringBuilder();
                } else
                    currentStr.append(b); // If in quotes, just add it
            } else // Add any other character to current string
                currentStr.append(b);
        }
        rows.add(currentStr.toString().replaceAll("^\"|\"$", ""));
    }

    SourceField getField(int columnNumber) {
        int index = columnNumber - 1;
        return index < rows.size() ? SourceField.getInstance(rows.get(index)) : null;
    }

    int getFieldCount() {
        return rows.size();
    }
}
