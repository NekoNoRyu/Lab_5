package parser;

import collectionClasses.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;

public class InteractiveParser {
    public static StudyGroup parseStudyGroup() {//without id
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
        return StudyGroup.builder().
                id(0).
                name(name).
                coordinates(coordinates).
                creationDate(creationDate).
                studentsCount(studentsCount).
                formOfEducation(formOfEducation).
                semesterEnum(semesterEnum).
                groupAdmin(groupAdmin).
                build();
    }

    public static long parseId() {
        System.out.println("Enter id (it can only be long and higher than 0)");
        FunctionalParser<Long> parser = new FunctionalParser<>();
        return parser.parseSomething((x) -> {
            try {
                long result = Long.parseLong(x);
                if (result <= 0) {
                    System.out.println("This field can only be higher than 0");
                    return Optional.empty();
                }
                return Optional.of(result);
            } catch (NumberFormatException e) {
                System.out.println("This field can only be long");
                return Optional.empty();
            }
        });
    }

    public static Long parseKey() {
        System.out.println("Enter key (it can only be long)");
        FunctionalParser<Long> parser = new FunctionalParser<>();
        return parser.parseSomething((x) -> {
            try {
                Long result = Long.parseLong(x);
                return Optional.of(result);
            } catch (NumberFormatException e) {
                System.out.println("This field can only be long");
                return Optional.empty();
            }
        });
    }

    public static String parseName() {
        System.out.println("Enter name (it can not be empty)");
        FunctionalParser<String> parser = new FunctionalParser<>();
        return parser.parseSomething((x) -> {
            if (x.equals("")) {
                System.out.println("This field can not be empty");
                return Optional.empty();
            }
            return Optional.of(x);
        });
    }

    public static Double parseCoordinatesX() {
        System.out.println("Enter coordinates x (it can only be double and higher than -682)");
        FunctionalParser<Double> parser = new FunctionalParser<>();
        return parser.parseSomething((x) -> {
            try {
                Double result = Double.parseDouble(x);
                if (result <= -682) {
                    System.out.println("This field can only be higher than -682");
                    return Optional.empty();
                }
                return Optional.of(result);
            } catch (NumberFormatException e) {
                System.out.println("This field can only be a double");
                return Optional.empty();
            }
        });
    }

    public static Double parseCoordinatesY() {
        System.out.println("Enter coordinates y (it can only be double)");
        FunctionalParser<Double> parser = new FunctionalParser<>();
        return parser.parseSomething((x) -> {
            try {
                return Optional.of(Double.parseDouble(x));
            } catch (NumberFormatException e) {
                System.out.println("This field can only be a double");
                return Optional.empty();
            }
        });
    }

    public static Long parseStudentsCount() {
        System.out.println("Enter studentsCount (it can only be long and higher than 0)");
        FunctionalParser<Long> parser = new FunctionalParser<>();
        return parser.parseSomething((x) -> {
            try {
                Long result = Long.parseLong(x);
                if (result <= 0) {
                    System.out.println("This field can only be higher than 0");
                    return Optional.empty();
                }
                return Optional.of(result);
            } catch (NumberFormatException e) {
                System.out.println("This field can only be long");
                return Optional.empty();
            }
        });
    }

    public static FormOfEducation parseFormOfEducation() {
        System.out.println("Enter formOfEducation (it can only be DISTANCE_EDUCATION, FULL_TIME_EDUCATION, EVENING_CLASSES or empty)");
        FunctionalParser<FormOfEducation> parser = new FunctionalParser<>();
        return parser.parseSomething((x) -> {
            try {
                if (x.equals("")) {
                    return Optional.of(FormOfEducation.NULL);
                }
                return Optional.of(FormOfEducation.valueOf(x));
            } catch (IllegalArgumentException e) {
                System.out.println("This field can only be DISTANCE_EDUCATION, FULL_TIME_EDUCATION, EVENING_CLASSES or empty");
                return Optional.empty();
            }
        });
    }

    public static Semester parseSemesterEnum() {
        System.out.println("Enter semesterEnum (it can only be FIRST, SECOND, FOURTH, SIXTH or EIGHTH)");
        FunctionalParser<Semester> parser = new FunctionalParser<>();
        return parser.parseSomething((x) -> {
            try {
                return Optional.of(Semester.valueOf(x));
            } catch (IllegalArgumentException e) {
                System.out.println("This field can only be FIRST, SECOND, FOURTH, SIXTH or EIGHTH");
                return Optional.empty();
            }
        });
    }

    public static String parseGroupAdminName() {
        System.out.println("Enter groupAdmin name (if this field is empty, groupAdmin will be null)");
        FunctionalParser<String> parser = new FunctionalParser<>();
        return parser.parseSomething((x) -> Optional.of(x));
    }

    public static ZonedDateTime parseGroupAdminBirthday() {
        System.out.println("Enter groupAdmin birthday (Format: yyyy-MM-ddThh:mm + ZoneId.SHORT_IDS key)");
        FunctionalParser<ZonedDateTime> parser = new FunctionalParser<>();
        return parser.parseSomething((x) -> {
            try {
                int year = Integer.parseInt(x.substring(0, 4));
                int month = Integer.parseInt(x.substring(5, 7));
                int day = Integer.parseInt(x.substring(8, 10));
                int hour = Integer.parseInt(x.substring(11, 13));
                int minute = Integer.parseInt(x.substring(14, 16));
                ZoneId zoneId = ZoneId.of(ZoneId.SHORT_IDS.get(x.substring(16, 19)));
                LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute);
                ZonedDateTime result = ZonedDateTime.of(localDateTime, zoneId);
                return Optional.of(result);
            } catch (Exception e) {
                System.out.println("Format: yyyy-MM-ddThh:mm + ZoneId.SHORT_IDS key");
                System.out.println(e.getMessage());
                return Optional.empty();
            }
        });
    }

    public static Integer parseGroupAdminWeight() {
        System.out.println("Enter groupAdmin weight (it can only be integer and higher than 0)");
        FunctionalParser<Integer> parser = new FunctionalParser<>();
        return parser.parseSomething((x) -> {
            try {
                Integer result = Integer.parseInt(x);
                if (result <= 0) {
                    System.out.println("This field can only be higher than 0");
                    return Optional.empty();
                }
                return Optional.of(result);
            } catch (NumberFormatException e) {
                System.out.println("This field can only be a integer");
                return Optional.empty();
            }
        });
    }

    private static class FunctionalParser<T> {
        private T parseSomething(Function<String, Optional<T>> lambda) {
            Scanner scanner = new Scanner(System.in);
            Optional<T> result = Optional.empty();
            while (result.isEmpty()) {
                result = lambda.apply(scanner.nextLine());
            }
            return result.get();
        }
    }
}


