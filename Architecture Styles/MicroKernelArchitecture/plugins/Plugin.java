package plugins;

import core.TextEditorCore;

public interface Plugin {
    String getName();
    void execute(TextEditorCore core);
}
