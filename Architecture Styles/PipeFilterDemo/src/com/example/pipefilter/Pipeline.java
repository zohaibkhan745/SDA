package com.example.pipefilter;

import java.util.ArrayList;
import java.util.List;

/**
 * The Pipeline class manages a series of filters.
 * It processes an input string by passing it through each filter in the order they were added.
 */
public class Pipeline {

    private final List<Filter> filters = new ArrayList<>();

    /**
     * Adds a filter to the end of the pipeline.
     * @param filter The filter to be added.
     */
    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    /**
     * Runs the input string through the entire sequence of filters.
     * @param input The initial string to be processed.
     * @return The final string after all filters have been applied.
     */
    public String process(String input) {
        String result = input;
        for (Filter filter : filters) {
            result = filter.process(result);
        }
        return result;
    }
}