package patternCommand.commands;

import collectionClasses.CustomHashMap;
import collectionClasses.StudyGroup;
import parser.InvalidDataException;
import parser.Parser;
import patternCommand.Command;
import patternCommand.Document;

public class RemoveKeyCommand implements Command {
    @Override
    public void execute(String x) {
        try {
            Long key = Parser.parseKey(x);
            CustomHashMap<Long, StudyGroup> customHashMap = Document.getCustomHashMap();
            if (customHashMap.containsKey(key)) {
                customHashMap.remove(key);
            } else {
                System.out.println(key + ": element with this key is not exist");
            }
        } catch (InvalidDataException e) {
            System.out.println(x + ": problem with parsing");
            System.out.println(e.getMessage());
        }
    }
}
