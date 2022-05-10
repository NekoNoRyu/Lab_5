package patternCommand.commands;

import collectionClasses.CustomHashMap;
import collectionClasses.StudyGroup;
import parser.InvalidDataException;
import parser.Parser;
import patternCommand.Command;
import patternCommand.Document;

import java.util.Map;

public class FilterGreaterThanSCCommand implements Command {
    @Override
    public void execute(String x) {
        try {
            long studentsCount = Parser.parseStudentsCount(x);
            CustomHashMap<Long, StudyGroup> customHashMap = Document.getCustomHashMap();
            for (Map.Entry<Long, StudyGroup> entry : customHashMap.entrySet()) {
                if (entry.getValue().getStudentsCount() > studentsCount) {
                    System.out.println(entry);
                }
            }
        } catch (InvalidDataException e) {
            System.out.println(x + ": problem with parsing");
            System.out.println(e.getMessage());
        }
    }
}
