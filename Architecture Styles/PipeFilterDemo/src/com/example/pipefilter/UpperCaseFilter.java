package com.example.pipefilter;

/**
 * A filter that converts the input string to uppercase.
 */
public class UpperCaseFilter implements Filter {

    @Override
    public String process(String input) {
        System.out.println("-> UpperCaseFilter: Processing...");
        return input.toUpperCase();
    }
}