// Inside afterTextChanged(Editable s)
@Override
public void afterTextChanged(Editable s) {
    String code = s.toString();
    analyzeInput(code); // Existing language detection

    // New: Proactive Validation
    CodeAnalyzerService analyzer = new CodeAnalyzerService();
    if (!analyzer.isSyntaxValid(code)) {
        // Flag error visually using Red background
        applyErrorHighlight(code); 
    }
}

private void applyErrorHighlight(String code) {
    SpannableString spannable = new SpannableString(code);
    // Highlight first 5 characters as a warning if syntax is broken
    spannable.setSpan(new BackgroundColorSpan(Color.RED), 0, Math.min(code.length(), 5), 0);
    terminalInput.setText(spannable); 
}
