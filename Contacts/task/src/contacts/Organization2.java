package contacts;

import contacts.ContactAbstract;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Organization2 extends ContactAbstract implements Serializable {

    private String address;






    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public List<Field> returnListOfFields() {
        return null;
    }

    @Override
    public List<String> returnListOfFieldsStrings() {

          List<String> lista = new ArrayList<>();
        lista.add("name");
        lista.add("address");
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

            case "address":
                response = "Enter address: ";
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

            case "address":
                setAddress(value);
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

    @Override
    public Object getFieldValue(String field) {
        return null;
    }

    @Override
    public void printFullInformations() {
        System.out.println("Organization name: " + this.getName());
        System.out.println("Address: " + this.getAddress());
        System.out.println("Number: " + this.getNumber());
        System.out.println("Time created: " + this.getTimeCreated());
        System.out.println("Time last edit: " + this.getTimeEdit());
    }

    @Override
    public String getBasicInformations() {
        return this.getName();
    }

    @Override
    public void setFullInformations() {

        Scanner scanner = new Scanner(System.in);

        LocalDateTime lc = LocalDateTime.now();
        setTimeCreated(String.valueOf(lc));
        setTimeEdit(String.valueOf(lc));

        System.out.print("Enter name:");
        String name = scanner.nextLine();
        setName(name);

        System.out.print("Enter address:");
        String address = scanner.nextLine();
        setAddress(address);


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
}
