package core;

import java.util.Scanner;

public class TextEditorCore {
    private StringBuilder text = new StringBuilder();
    private Scanner sc = new Scanner(System.in);

    public void writeText() {
        System.out.println("Enter text (type .end to finish):");
        while (true) {
            String line = sc.nextLine();
            if (line.equals(".end")) break;
            text.append(line).append("\n");
        }
    }

    public void showText() {
        System.out.println("---- Document ----");
        System.out.println(text.toString());
    }

    public String getText() { return text.toString(); }

    public void setText(String t) {
        text.setLength(0);
        text.append(t);
    }
}
