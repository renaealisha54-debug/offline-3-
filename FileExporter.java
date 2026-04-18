package com.darvin.codeedit;

import android.content.Intent;
import android.net.Uri;

public class FileExporter {
    // Maps detected language to correct extension
    public String getExtension(String language) {
        switch (language) {
            case "Kotlin": return ".kt";
            case "Python": return ".py";
            case "JavaScript": return ".js";
            case "Gradle": return ".gradle";
            default: return ".txt";
        }
    }

    public Intent getExportIntent(String fileName, String language) {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain"); // Standard for most code exports
        intent.putExtra(Intent.EXTRA_TITLE, fileName + getExtension(language));
        return intent;
    }
}
