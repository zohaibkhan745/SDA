package com.example.pipefilter;

/**
 * The base interface for all filters in the Pipe & Filter pattern.
 * Each filter must implement the process method.
 */
@FunctionalInterface
public interface Filter {
    /**
     * Processes the input data and returns the transformed data.
     * @param input The string data to be processed.
     * @return The processed string data.
     */
    String process(String input);
}