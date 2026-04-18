package com.darvin.codeedit;

import java.util.zip.ZipEntry;

public class ZipDeconstructor {
    public String determinePlacement(ZipEntry entry) {
        String name = entry.getName();
        if (name.endsWith(".java") || name.endsWith(".kt")) return "/src/";
        if (name.contains("manifest")) return "/root/";
        if (name.endsWith(".json") || name.endsWith(".xml")) return "/assets/";
        return "/misc/";
    }
}
