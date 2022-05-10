package patternCommand.commands;

import collectionClasses.CustomHashMap;
import collectionClasses.StudyGroup;
import parser.InteractiveParser;
import parser.InvalidDataException;
import parser.Parser;
import patternCommand.Command;
import patternCommand.Document;

import java.util.Map;

public class InteractiveUpdateCommand implements Command {
    @Override
    public void execute(String x) {
        CustomHashMap<Long, StudyGroup> customHashMap = Document.getCustomHashMap();
        long id;
        try {
            id = Parser.parseId(x);
        } catch (InvalidDataException e) {
            id = InteractiveParser.parseId();
        }
        boolean containsId = false;
        Long key = 0L;
        while (true) {
            for (Map.Entry<Long, StudyGroup> entry : customHashMap.entrySet()) {
                if (entry.getValue().getId() == id) {
                    containsId = true;
                    key = entry.getKey();
                }
            }
            if (!containsId) {
                System.out.println("Element with this id is not exists");
                id = InteractiveParser.parseId();
            } else {
                break;
            }
        }
        System.out.println("Enter new element");
        StudyGroup studyGroup = InteractiveParser.parseStudyGroup();
        studyGroup.setId(id);
        customHashMap.replace(key, studyGroup);
    }
}
