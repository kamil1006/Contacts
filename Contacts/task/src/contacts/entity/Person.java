package contacts.entity;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Person extends  Contact implements Serializable {


    private String surname;
    private String birtDate;
    private String gender;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirtDate() {
        return birtDate;
    }

    public void setBirtDate(String birtDate) {
        this.birtDate = birtDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public List<Field> returnListOfFields() {
        Field[] fields = Person.class.getFields();
        List<Field> collect = Arrays.stream(fields).collect(Collectors.toList());
        return collect;
    }
}
