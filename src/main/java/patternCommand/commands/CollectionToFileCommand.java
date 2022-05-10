package patternCommand.commands;

import collectionClasses.StudyGroup;
import parser.Parser;
import patternCommand.Command;
import patternCommand.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class CollectionToFileCommand implements Command {
    @Override
    public void execute(String x) {
        File file = new File(Document.getFileWithCollectionPath());
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            for (Map.Entry<Long, StudyGroup> entry : Document.getCustomHashMap().entrySet()) {
                fileWriter.write(Parser.parseEntryToCSV(entry) + "\n");
            }
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("No write access");
        }
    }
}
