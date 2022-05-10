package patternCommand.commands;

import collectionClasses.CustomHashMap;
import collectionClasses.StudyGroup;
import parser.InteractiveParser;
import parser.InvalidDataException;
import parser.Parser;
import patternCommand.Command;
import patternCommand.Document;

public class InteractiveReplaceIfLowerCommand implements Command {
    @Override
    public void execute(String x) {
        CustomHashMap<Long, StudyGroup> customHashMap = Document.getCustomHashMap();
        Long key;
        try {
            key = Parser.parseKey(x);
        } catch (InvalidDataException e) {
            key = InteractiveParser.parseKey();
        }
        while (true) {
            if (!customHashMap.containsKey(key)) {
                System.out.println("Element with this key is not exist");
                key = InteractiveParser.parseKey();
            } else {
                break;
            }
        }
        StudyGroup studyGroup = InteractiveParser.parseStudyGroup();
        if (customHashMap.get(key).compareTo(studyGroup) > 0) {
            customHashMap.replace(key, studyGroup);
            Document.setCustomHashMap(customHashMap);
        }
    }
}
