package com.darvin.codeedit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.widget.EditText;
import android.widget.Toast;
import java.io.OutputStream;
import java.util.HashMap;

public class CodeTerminalActivity extends Activity {

    private EditText terminalInput;
    private String detectedLanguage = "Plain Text";
    private HashMap<String, Integer> colorMap = new HashMap<>();
    private boolean isInternalUpdate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        terminalInput = new EditText(this);
        terminalInput.setHint("Paste code here...");
        terminalInput.setBackgroundColor(Color.BLACK);
        terminalInput.setTextColor(Color.GREEN);
        terminalInput.setTypeface(android.graphics.Typeface.MONOSPACE);
        setContentView(terminalInput);

        initColorMap();

        terminalInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (!isInternalUpdate) {
                    analyzeInput(s.toString());
                }
            }
        });
    }

    private void initColorMap() {
        colorMap.put("Kotlin", Color.parseColor("#3F51B5")); // Blue
        colorMap.put("Python", Color.parseColor("#4CAF50")); // Green
        colorMap.put("JavaScript", Color.parseColor("#FFEB3B")); // Yellow
        colorMap.put("Gradle", Color.parseColor("#FF5722")); // Deep Orange
        colorMap.put("React Native", Color.parseColor("#00BCD4")); // Cyan
    }

    private void analyzeInput(String rawCode) {
        if (rawCode.contains("fun main()") || rawCode.contains("val ")) {
            detectedLanguage = "Kotlin";
        } else if (rawCode.contains("def ") || rawCode.contains("import os")) {
            detectedLanguage = "Python";
        } else if (rawCode.contains("const ") || rawCode.contains("export default")) {
            detectedLanguage = "React Native / JS";
        } else if (rawCode.contains("dependencies {")) {
            detectedLanguage = "Gradle";
        }
        applyImprovisationHighlights(rawCode);
    }

    private void applyImprovisationHighlights(String code) {
        isInternalUpdate = true;
        SpannableString spannable = new SpannableString(code);
        
        if (detectedLanguage.equals("Kotlin") && code.contains("fun main()")) {
            int start = code.indexOf("fun main()");
            spannable.setSpan(new BackgroundColorSpan(colorMap.get("Kotlin")), 
                start, start + 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        if (detectedLanguage.equals("Gradle") && code.contains("dependencies {")) {
            int start = code.indexOf("dependencies {");
            spannable.setSpan(new BackgroundColorSpan(colorMap.get("Gradle")), 
                start, start + 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        terminalInput.setText(spannable);
        terminalInput.setSelection(terminalInput.getText().length());
        isInternalUpdate = false;
    }

    private void exportCodeToFile(String fileName) {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, fileName);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                OutputStream outputStream = getContentResolver().openOutputStream(uri);
                outputStream.write(terminalInput.getText().toString().getBytes());
                outputStream.close();
                Toast.makeText(this, "Project Saved Successfully", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
