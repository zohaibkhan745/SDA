package com.example.pipefilter;

/**
 * A filter that removes any leading or trailing whitespace from the input string.
 */
public class TrimFilter implements Filter {

    @Override
    public String process(String input) {
        System.out.println("-> TrimFilter: Processing...");
        return input.trim();
    }
}