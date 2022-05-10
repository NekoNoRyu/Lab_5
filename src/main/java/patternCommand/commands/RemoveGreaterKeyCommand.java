package patternCommand.commands;

import collectionClasses.CustomHashMap;
import collectionClasses.StudyGroup;
import parser.InvalidDataException;
import parser.Parser;
import patternCommand.Command;
import patternCommand.Document;

import java.util.Map;


public class RemoveGreaterKeyCommand implements Command {
    @Override
    public void execute(String x) {
        try {
            CustomHashMap<Long, StudyGroup> customHashMap = Document.getCustomHashMap();
            Long key = Parser.parseKey(x);
            for (Map.Entry<Long, StudyGroup> entry : customHashMap.entrySet()) {
                if (entry.getKey() > key) {
                    customHashMap.remove(entry.getKey());
                }
            }
            Document.setCustomHashMap(customHashMap);
        } catch (InvalidDataException e) {
            System.out.println(x + ": problem with parsing");
            System.out.println(e.getMessage());
        }
    }
}
