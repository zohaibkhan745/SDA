package com.example.pipefilter;

public class Main {
    public static void main(String[] args) {
        // 1. Create a new pipeline
        Pipeline textProcessor = new Pipeline();

        // 2. Add filters to the pipeline in the desired order
        // The order is important!
        textProcessor.addFilter(new TrimFilter());
        textProcessor.addFilter(new UpperCaseFilter());
        textProcessor.addFilter(new AddPrefixFilter("RESULT: "));

        // 3. Define the input data
        String initialData = "   hello, pipe & filter world!   ";

        System.out.println("Initial data: '" + initialData + "'");
        System.out.println("\n--- Starting Pipeline Processing ---");

        // 4. Process the data through the pipeline
        String finalResult = textProcessor.process(initialData);

        System.out.println("--- Pipeline Processing Finished ---\n");
        System.out.println("Final result: '" + finalResult + "'");
    }
}