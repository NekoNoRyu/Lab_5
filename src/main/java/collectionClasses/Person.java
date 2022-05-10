package collectionClasses;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.ZonedDateTime birthday; //Поле не может быть null
    private int weight; //Значение поля должно быть больше 0
}