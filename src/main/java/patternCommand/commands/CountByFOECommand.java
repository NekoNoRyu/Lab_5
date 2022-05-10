package patternCommand.commands;

import collectionClasses.CustomHashMap;
import collectionClasses.FormOfEducation;
import collectionClasses.StudyGroup;
import parser.InvalidDataException;
import parser.Parser;
import patternCommand.Command;
import patternCommand.Document;

import java.util.Map;

public class CountByFOECommand implements Command {
    @Override
    public void execute(String x) {
        try {
            FormOfEducation formOfEducation = Parser.parseFormOfEducation(x);
            CustomHashMap<Long, StudyGroup> customHashMap = Document.getCustomHashMap();
            int i = 0;
            for (Map.Entry<Long, StudyGroup> entry : customHashMap.entrySet()) {
                if (entry.getValue().getFormOfEducation() == formOfEducation) {
                    i++;
                }
            }
            System.out.println(i);
        } catch (InvalidDataException e) {
            System.out.println(x + ": problem with parsing");
            System.out.println(e.getMessage());
        }
    }
}
