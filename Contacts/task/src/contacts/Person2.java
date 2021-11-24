package contacts;



import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Person2 extends ContactAbstract implements Serializable {


    //default serialVersion id
    private static final long serialVersionUID = 1L;


    private String surname;
    private String birthDate;
    private String gender;

    @Override
    public List<Field> returnListOfFields() {
        Field[] fields = Person2.class.getFields();
        List<Field> collect = Arrays.stream(fields).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<String> returnListOfFieldsStrings() {

        List<String> lista = new ArrayList<>();
        lista.add("name");
        lista.add("surname");
        lista.add("birth");
        lista.add("gender");
        lista.add("number");

        return lista;
    }

    @Override
    public String getTekst(String fieldName) {

        String response = "";

        switch (fieldName) {
            case "name":
                response = "Enter name: ";
                break;

            case "surname":
                response = "Enter surname: ";
                break;
            case "birth":
                response = "Enter the birth date: ";

                break;
            case "gender":
                response = "Enter the gender (M, F): ";

                break;
            case "number":
                response = "Enter number: ";

                break;

        }

        return response;
    }

    @Override
    public String changeFieldValue(String fieldName, String value) {

        String response = "";

        switch (fieldName) {
            case "name":
                setName(value);
                break;

            case "surname":
                setSurname(value);
                break;
            case "birth":
                try {
                    LocalDateTime localDateTime = LocalDateTime.parse(value);

                }catch (Exception e) {
                    value = "[no data]";
                    response = "Bad birth date!";
                }
                setBirthDate(value);
                break;
            case "gender":
                value =value.toUpperCase();
                if(!(value.equals("M") || value.equals("F")))
                {
                    response = "Bad gender!";
                    value ="[no data]";
                }
                setGender(value);
                break;


            case "number":
                if(validatePhone(value)){
                    setNumber(value);
                }
                else{
                    setNumber("[no data]");
                    response ="Wrong number format!";
                }

                break;

        }

        LocalDateTime localDateTime = LocalDateTime.now();
        setTimeEdit(localDateTime.toString());
        return response;
    }



    /*
    @Override
    public boolean changeFieldValue(String fieldName, String value) {
        List<Field> fields = returnListOfFields();
        Field f = null;
        for(Field field:fields){
            if(field.getName().equals(fieldName)){
                f=field;
            }

         f.setAccessible(true);
           try {
               f.set(this,value);
           }
           catch (Exception e){
               return false;
           }
      }
        return true;
    }
*/
    @Override
    public Object getFieldValue(String fieldName) {
        List<Field> fields = returnListOfFields();
        Field f = null;
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                f = field;
                f.setAccessible(true);
                try {
                    Object o = f.get(this);
                    return o;
                }catch (Exception e){
                    return null;
                }

            }


        }
        return null;
    }

    @Override
    public void printFullInformations() {
        System.out.println("Name: " + this.getName());
        System.out.println("Surname: " + this.getSurname());
        System.out.println("Birth date: " + this.getBirthDate());
        System.out.println("Gender: " + this.getGender());
        System.out.println("Number: " + this.getNumber());
        System.out.println("Time created: " + this.getTimeCreated());
        System.out.println("Time last edit: " + this.getTimeEdit());
    }

    @Override
    public String getBasicInformations() {
        return this.getName()+ " " + this.getSurname();
    }

    @Override
    public void setFullInformations() {

        Scanner scanner = new Scanner(System.in);

        LocalDateTime lc = LocalDateTime.now();
        setTimeCreated(String.valueOf(lc));
        setTimeEdit(String.valueOf(lc));

        System.out.print("Enter the name:");
        String name = scanner.nextLine();
        setName(name);

        System.out.print("Enter the surname:");
        String surname = scanner.nextLine();
        setSurname(surname);

        System.out.print("Enter the birth date:");
        String birthdate = scanner.nextLine();

        try {
            LocalDateTime localDateTime = LocalDateTime.parse(birthdate);
        }catch (Exception e){
            birthdate = "[no data]";
            System.out.println("Bad birth date!");
        }

        setBirthDate(birthdate);
        System.out.print("Enter the gender (M, F):");
        String gender = scanner.nextLine();
        gender =gender.toUpperCase();
        if(!(gender.equals("M") || gender.equals("F")))
        {
            System.out.println("Bad gender!");
            gender ="[no data]";
        }
        setGender(gender);
        System.out.print("Enter the number:");
        String number = scanner.nextLine();

        if(validatePhone(number)){
            setNumber(number);
        }
        else{
            setNumber("[no data]");
            System.out.println("Wrong number format!");
        }

    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
