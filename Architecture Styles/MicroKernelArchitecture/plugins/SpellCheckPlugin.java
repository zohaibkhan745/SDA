package plugins;

import core.TextEditorCore;

public class SpellCheckPlugin implements Plugin {
    @Override
    public String getName() { return "SpellCheck"; }

    @Override
    public void execute(TextEditorCore core) {
        String text = core.getText();
        System.out.println("Spell Check Results:");
        if (!text.contains("the")) System.out.println("Possibly missing common article: 'the'");
        if (text.contains("teh")) System.out.println("Found misspelling: 'teh' → 'the'");
        System.out.println("Spell check completed.");
    }
}
