package patternCommand.commands;

import collectionClasses.CustomHashMap;
import collectionClasses.StudyGroup;
import parser.InteractiveParser;
import patternCommand.Command;
import patternCommand.Document;

import java.util.Map;

public class InteractiveRemoveLowerCommand implements Command {
    @Override
    public void execute(String x) {
        StudyGroup studyGroup = InteractiveParser.parseStudyGroup();
        CustomHashMap<Long, StudyGroup> customHashMap = Document.getCustomHashMap();
        for (Map.Entry<Long, StudyGroup> entry : customHashMap.entrySet()) {
            if (entry.getValue().compareTo(studyGroup) < 0) {
                customHashMap.remove(entry.getKey());
            }
        }
        Document.setCustomHashMap(customHashMap);
    }
}
