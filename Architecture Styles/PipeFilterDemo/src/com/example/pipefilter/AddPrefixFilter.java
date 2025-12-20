package com.example.pipefilter;

/**
 * A filter that adds a predefined prefix to the input string.
 */
public class AddPrefixFilter implements Filter {

    private final String prefix;

    /**
     * Constructs the filter with a specific prefix.
     * @param prefix The string to prepend to the input.
     */
    public AddPrefixFilter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String process(String input) {
        System.out.println("-> AddPrefixFilter: Processing...");
        return prefix + input;
    }
}