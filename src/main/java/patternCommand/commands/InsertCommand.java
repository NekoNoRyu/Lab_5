package patternCommand.commands;

import collectionClasses.*;
import parser.InvalidDataException;
import parser.Parser;
import patternCommand.Command;
import patternCommand.Document;

import java.util.Date;
import java.util.Map;

public class InsertCommand implements Command {
    @Override
    public void execute(String x) {
        String[] elements = Parser.parseCSVToArray(x);
        if (elements.length != 10) {
            System.out.println(x + ": number of elements for insert can only be 10");
        } else {
            try {
                CustomHashMap<Long, StudyGroup> customHashMap = Document.getCustomHashMap();
                Long key = Parser.parseKey(elements[0]);
                if (customHashMap.containsKey(key)) {
                    throw new InvalidDataException(key + ": element with this key already exists");
                }
                long id = 0;
                for (Map.Entry<Long, StudyGroup> entry : customHashMap.entrySet()) {
                    if (id < entry.getValue().getId()) {
                        id = entry.getValue().getId();
                    }
                }
                id++;
                String name = Parser.parseName(elements[1]);
                Coordinates coordinates = Coordinates.builder().
                        x(Parser.parseCoordinatesX(elements[2])).
                        y(Parser.parseCoordinatesY(elements[3])).
                        build();
                Date creationDate = new Date();
                long studentsCount = Parser.parseStudentsCount(elements[4]);
                FormOfEducation formOfEducation = Parser.parseFormOfEducation(elements[5]);
                if (formOfEducation.equals(FormOfEducation.NULL)) {
                    formOfEducation = null;
                }
                Semester semesterEnum = Parser.parseSemesterEnum(elements[6]);
                Person groupAdmin = null;
                String groupAdminName = Parser.parseGroupAdminName(elements[7]);
                if (!groupAdminName.equals("")) {
                    groupAdmin = Person.builder().
                            name(groupAdminName).
                            birthday(Parser.parseGroupAdminBirthday(elements[8])).
                            weight(Parser.parseGroupAdminWeight(elements[9])).
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
            } catch (InvalidDataException e) {
                System.out.println(x + ": problem with parsing");
                System.out.println(e.getMessage());
            }
        }
    }
}
