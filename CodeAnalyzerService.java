

public void updateTerminalHighlights(EditText terminal, String code) {
    SpannableString spannable = new SpannableString(code);
    
    // Example: Highlight 'fun main()' in Green
    if (code.contains("fun main()")) {
        int start = code.indexOf("fun main()");
        int end = start + 10;
        spannable.setSpan(new BackgroundColorSpan(Color.GREEN), start, end, 0);
    }
    
    // Example: Highlight Framework/Storage keywords in Yellow
    if (code.contains("StorageAccessFramework")) {
        int start = code.indexOf("StorageAccessFramework");
        spannable.setSpan(new BackgroundColorSpan(Color.YELLOW), start, start + 22, 0);
    }

    terminal.setText(spannable);
}

