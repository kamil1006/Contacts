package contacts;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ContactAbstract implements Serializable {

    //default serialVersion id
    private static final long serialVersionUID = 1L;

    String name;
    String number;
    String timeCreated;
    String timeEdit;

    //*************************************************************************

    public abstract List<Field> returnListOfFields();
    public abstract List<String> returnListOfFieldsStrings();
    public abstract String getTekst(String fieldName);


    public abstract String changeFieldValue(String field, String value);
    public abstract Object getFieldValue(String field);
    public abstract void printFullInformations();
    public abstract String getBasicInformations() ;
    public abstract void setFullInformations();



    //*************************************************************************

    public static boolean validatePhone(String numberToCheck){

        String regex =
                "^(?i)([+](\\w )?([(]?\\w{2,}[)]?)?|[(]\\w{2,}[)]|\\w{1,}|\\w{2,}[ -][(]\\w{2,}[)])([ -][0-9a-z]{2,})?([ -][0-9a-z]{2,})?([ -][0-9a-z]{2,})?$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(numberToCheck);

        return matcher.matches();
    }

    //*************************************************************************

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getTimeEdit() {
        return timeEdit;
    }

    public void setTimeEdit(String timeEdit) {
        this.timeEdit = timeEdit;
    }
    //*************************************************************************
}
