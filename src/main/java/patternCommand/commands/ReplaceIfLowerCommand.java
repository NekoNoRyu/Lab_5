package patternCommand.commands;

import collectionClasses.CustomHashMap;
import collectionClasses.StudyGroup;
import parser.InvalidDataException;
import parser.Parser;
import patternCommand.Command;
import patternCommand.Document;

public class ReplaceIfLowerCommand implements Command {
    @Override
    public void execute(String x) {
        String[] elements = Parser.parseCSVToArray(x);
        if (elements.length != 10) {
            System.out.println(x + ": number of elements for replace_if_lower can only be 10");
        } else {
            try {
                CustomHashMap<Long, StudyGroup> customHashMap = Document.getCustomHashMap();
                Long key = Parser.parseKey(elements[0]);
                if (customHashMap.containsKey(key)) {
                    String[] toStudyGroup = new String[9];
                    System.arraycopy(elements, 1, toStudyGroup, 0, 9);
                    StudyGroup studyGroup = Parser.parseArrayToStudyGroup(toStudyGroup);
                    if (customHashMap.get(key).compareTo(studyGroup) > 0) {
                        customHashMap.replace(key, studyGroup);
                        Document.setCustomHashMap(customHashMap);
                    }
                } else {
                    System.out.println(key + ": element with this key is not exist");
                }
            } catch (InvalidDataException e) {
                System.out.println(x + ": problem with parsing");
                System.out.println(e.getMessage());
            }
        }
    }
}
