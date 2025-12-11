package app;

import java.util.Scanner;
import core.TextEditorCore;
import plugins.Plugin;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TextEditorCore core = new TextEditorCore();

        while (true) {
            System.out.println("\n=== Microkernel Text Editor ===");
            System.out.println("1) Write text");
            System.out.println("2) View text");
            System.out.println("3) List plugins");
            System.out.println("4) Run plugin");
            System.out.println("0) Exit");
            System.out.print("Choose: ");
            String c = sc.nextLine();

            switch (c) {
                case "1":
                    core.writeText();
                    break;
                case "2":
                    core.showText();
                    break;
                case "3":
                    System.out.println("Available plugins:");
                     for (String name : PluginRegistry.getPlugins().keySet()) {
                        System.out.println(" - " + name);
                    break;
                case "4":
                    System.out.print("Enter plugin name: ");
                    String name = sc.nextLine();
                    
                    // Use the factory method 
                    Plugin p = PluginRegistry.createPlugin(name);
                    
                    if (p == null) {
                        System.out.println("Plugin not found.");
                        break;
                    }
                    System.out.println("Running plugin: " + p.getName());
                    p.execute(core);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}