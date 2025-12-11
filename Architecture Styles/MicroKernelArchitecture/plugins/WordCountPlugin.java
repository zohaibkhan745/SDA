package plugins;

import core.TextEditorCore;

public class WordCountPlugin implements Plugin {
    @Override
    public String getName() { return "WordCount"; }

    @Override
    public void execute(TextEditorCore core) {
        String t = core.getText().trim();
        if (t.isEmpty()) {
            System.out.println("No text found.");
            return;
        }
        int words = t.split("\s+").length;
        System.out.println("Word count: " + words);
    }
}
