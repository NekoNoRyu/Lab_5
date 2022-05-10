package patternCommand.commands;

import collectionClasses.CustomHashMap;
import collectionClasses.FormOfEducation;
import collectionClasses.StudyGroup;
import parser.InvalidDataException;
import parser.Parser;
import patternCommand.Command;
import patternCommand.Document;

import java.util.Map;

public class RemoveAnyByFOECommand implements Command {
    @Override
    public void execute(String x) {
        try {
            FormOfEducation formOfEducation = Parser.parseFormOfEducation(x);
            CustomHashMap<Long, StudyGroup> customHashMap = Document.getCustomHashMap();
            for (Map.Entry<Long, StudyGroup> entry : customHashMap.entrySet()) {
                if (entry.getValue().getFormOfEducation() == formOfEducation) {
                    customHashMap.remove(entry.getKey());
                    break;
                }
            }
            Document.setCustomHashMap(customHashMap);
        } catch (InvalidDataException e) {
            System.out.println(x + ": problem with parsing");
            System.out.println(e.getMessage());
        }
    }
}
