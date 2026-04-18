package com.darvin.codeedit;

import android.graphics.Color;
import java.util.Stack;

public class CodeAnalyzerService {

    // Revised: Adds Syntax Validation for brackets/parentheses
    public boolean isSyntaxValid(String code) {
        Stack<Character> stack = new Stack<>();
        for (char c : code.toCharArray()) {
            if (c == '{' || c == '(' || c == '[') {
                stack.push(c);
            } else if (c == '}' || c == ')' || c == ']') {
                if (stack.isEmpty()) return false;
                char last = stack.pop();
                if ((c == '}' && last != '{') || (c == ')' && last != '(') || (c == ']' && last != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    // Revised: Basic Auto-Formatter for indentation
    public String autoFormat(String code) {
        StringBuilder formatted = new StringBuilder();
        int indentLevel = 0;
        String[] lines = code.split("\n");

        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("}") || line.startsWith("]")) indentLevel--;
            
            for (int i = 0; i < indentLevel; i++) formatted.append("    "); // 4-space indent
            formatted.append(line).append("\n");

            if (line.endsWith("{") || line.endsWith("[")) indentLevel++;
        }
        return formatted.toString().trim();
    }
}
