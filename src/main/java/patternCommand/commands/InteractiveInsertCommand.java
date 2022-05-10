package patternCommand.commands;

import collectionClasses.*;
import parser.InteractiveParser;
import parser.InvalidDataException;
import parser.Parser;
import patternCommand.Command;
import patternCommand.Document;

import java.util.Date;
import java.util.Map;

public class InteractiveInsertCommand implements Command {
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
            if (customHashMap.containsKey(key)) {
                System.out.println("Element with this key already exists");
                key = InteractiveParser.parseKey();
            } else {
                break;
            }
        }
        long id = 0;
        for (Map.Entry<Long, StudyGroup> entry : customHashMap.entrySet()) {
            if (id < entry.getValue().getId()) {
                id = entry.getValue().getId();
            }
        }
        id++;
        System.out.println("Enter new element");
        String name = InteractiveParser.parseName();
        Coordinates coordinates = Coordinates.builder().
                x(InteractiveParser.parseCoordinatesX()).
                y(InteractiveParser.parseCoordinatesY()).
                build();
        Date creationDate = new Date();
        long studentsCount = InteractiveParser.parseStudentsCount();
        FormOfEducation formOfEducation = InteractiveParser.parseFormOfEducation();
        if (formOfEducation.equals(FormOfEducation.NULL)) {
            formOfEducation = null;
        }
        Semester semesterEnum = InteractiveParser.parseSemesterEnum();
        Person groupAdmin = null;
        String groupAdminName = InteractiveParser.parseGroupAdminName();
        if (!groupAdminName.equals("")) {
            groupAdmin = Person.builder().
                    name(groupAdminName).
                    birthday(InteractiveParser.parseGroupAdminBirthday()).
                    weight(InteractiveParser.parseGroupAdminWeight()).
                    build();
        }
        customHashMap.put(key, StudyGroup.builder().
                id(id).
                name(name).
                coordinates(coordinates).
                creationDate(creationDate).
                studentsCount(studentsCount).
                formOfEducation(formOfEducation).
                semesterEnum(semesterEnum).
                groupAdmin(groupAdmin).
                build());
        Document.setCustomHashMap(customHashMap);
    }
}
