package patternCommand.commands;

import collectionClasses.CustomHashMap;
import collectionClasses.StudyGroup;
import parser.InvalidDataException;
import parser.Parser;
import patternCommand.Command;
import patternCommand.Document;

import java.util.Map;

public class RemoveLowerCommand implements Command {
    @Override
    public void execute(String x) {
        String[] elements = Parser.parseCSVToArray(x);
        if (elements.length != 9) {
            System.out.println(x + ": number of elements for remove_lower can only be 9");
        } else {
            try {
                StudyGroup studyGroup = Parser.parseArrayToStudyGroup(elements);
                CustomHashMap<Long, StudyGroup> customHashMap = Document.getCustomHashMap();
                for (Map.Entry<Long, StudyGroup> entry : customHashMap.entrySet()) {
                    if (entry.getValue().compareTo(studyGroup) < 0) {
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
}
