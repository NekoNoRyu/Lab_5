package patternCommand.commands;

import collectionClasses.CustomHashMap;
import collectionClasses.StudyGroup;
import parser.InvalidDataException;
import parser.Parser;
import patternCommand.Command;
import patternCommand.Document;

import java.util.Map;

public class UpdateCommand implements Command {
    @Override
    public void execute(String x) {
        String[] elements = Parser.parseCSVToArray(x);
        if (elements.length != 10) {
            System.out.println(x + ": number of elements for update can only be 10");
        } else {
            try {
                CustomHashMap<Long, StudyGroup> customHashMap = Document.getCustomHashMap();
                long id = Parser.parseId(elements[0]);
                boolean containsId = false;
                Long key = 0L;
                for (Map.Entry<Long, StudyGroup> entry : customHashMap.entrySet()) {
                    if (entry.getValue().getId() == id) {
                        containsId = true;
                        key = entry.getKey();
                    }
                }
                if (!containsId) {
                    throw new InvalidDataException(id + ": element with this id is not exists");
                }
                String[] toStudyGroup = new String[9];
                System.arraycopy(elements, 1, toStudyGroup, 0, 9);
                StudyGroup studyGroup = Parser.parseArrayToStudyGroup(toStudyGroup);
                studyGroup.setId(id);
                customHashMap.replace(key, studyGroup);
            } catch (InvalidDataException e) {
                System.out.println(x + ": problem with parsing");
                System.out.println(e.getMessage());
            }
        }
    }
}
