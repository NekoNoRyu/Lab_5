package patternCommand.commands;

import patternCommand.Application;
import patternCommand.Command;
import patternCommand.Document;
import patternCommand.InvalidCommandException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileToCollectionCommand implements Command {
    @Override
    public void execute(String x) {
        try {
            File file = new File(Document.getFileWithCollectionPath());
            Scanner scanner = new Scanner(file);
            while (true) {
                String line;
                try {
                    line = scanner.nextLine();
                } catch (NoSuchElementException e) {
                    System.out.println("Collection is recorded");
                    break;
                }
                try {
                    Application.requestMenu("dev:insert " + line);
                } catch (InvalidCommandException ignored) {
                    ignored.printStackTrace();
                }
            }
        } catch (NullPointerException | FileNotFoundException e) {
            System.out.println("File with collection not found, it can only be readable");
        }
    }
}
