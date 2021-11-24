package contacts;

import contacts.entity.Contact;
import contacts.entity.Organization;
import contacts.entity.Person;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainOld {


    static List<Contact> lista = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);


    private static final String filepath="phonebook.db";

/*
    public static void main(String[] args) {



        String line ="";
        while (!line.equals("exit")){
            System.out.println();
            //System.out.print("Enter action (add, remove, edit, count, info, exit):");
            System.out.print("[menu] Enter action (add, list, search, count, exit):");
           // System.out.print("Enter action (add, info, exit):");
            line = scanner.nextLine();
            if(!line.equals("exit")) menuFirst(line);
        }


        Stage 1

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the person:");
        String name = scanner.nextLine();
        System.out.println("Enter the surname of the person:");
        String surname = scanner.nextLine();
        System.out.println("Enter the number:");
        String number = scanner.nextLine();

        Contact contact = new Contact(name,surname,number);

        System.out.println("A record created!");
        System.out.println("A Phone Book with a single record created!");



    }

*/

    //*************************************************************************
    public static void menuFirst(String line) {
        switch (line){
            case "add":
                menuSecond();


                break;
            case "remove":
                if(lista.size()==0){
                    System.out.println("No records to remove!");

                }else{
                    removeMenu();

                }
                break;
            case "edit":
                    if(lista.size()==0){
                        System.out.println("No records to edit!");

                    }else{

                        editRecords();



                    }
                break;

            case "count":
                System.out.println("The Phone Book has "+lista.size()+" records.");
                break;

            case "info":
                if(lista.size()==0){
                    System.out.println("No records to list!");

                }else{
                    for ( int i = 0; i < lista.size(); i++){

                        String name = "";
                        if(lista.get(i).isPerson()){
                            Person person = (Person) lista.get(i);
                            name = person.getName() +" " + person.getSurname();
                        }else {
                            name = lista.get(i).getName();
                        }
                        System.out.println(i+1 + " " + name);
                    }

                    showInfo();


                }


                break;

            case "list":
                if(lista.size()==0){
                    System.out.println("No records to list!");

                }else{
                    for ( int i = 0; i < lista.size(); i++){

                        String name = lista.get(i).getName();
                        //String surname = lista.get(i).getSurname();
                        String number = lista.get(i).getNumber();
                        number = (number == "" ? "[no number]": number);
                        //System.out.println(i+1+". " + name +" " + surname +", " + number );

                    }

                }
                break;

        }
    }
    //*************************************************************************
    private static void showInfo() {
        System.out.print("Enter index to show info:");
        int indeks = Integer.valueOf(scanner.nextLine());
        Contact contact = lista.get(indeks - 1);
        if(contact.isPerson()){

            Person person = (Person) contact;
            System.out.print("Name: ");
            System.out.println(person.getName());
            System.out.print("Surname: ");
            System.out.println(person.getSurname());
            System.out.print("Birth date: ");
            String birth = person.getBirtDate() == "" ? "[no data]": person.getBirtDate();
            System.out.println(birth);
            System.out.print("Gender: ");
            String gender = person.getGender() == "" ? "[no data]": person.getGender();
            System.out.println(gender);
            System.out.print("Number: ");
            String numer = person.getNumber() == "" ? "[no data]": person.getNumber();
            System.out.println(numer);
            System.out.print("Time created: ");
            System.out.println(person.getTimeCreated());
            System.out.print("Time last edit: ");
            System.out.println(person.getTimeEdit());

        }else {
            Organization organization = (Organization)  contact;
            System.out.print("Organization name: ");
            System.out.println(organization.getName());
            System.out.print("Address: ");
            System.out.println(organization.getAddress());
            System.out.print("Number: ");
            String numer = organization.getNumber() == "" ? "[no data]": organization.getNumber();
            System.out.println(numer);
            System.out.print("Time created: ");
            System.out.println(organization.getTimeCreated());
            System.out.print("Time last edit: ");
            System.out.println(organization.getTimeEdit());

        }

    }

    //*************************************************************************
    private static void removeMenu() {

        for ( int i = 0; i < lista.size(); i++){

            String name = lista.get(i).getName();
          //  String surname = lista.get(i).getSurname();
            String number = lista.get(i).getNumber();
            number = (number == "" ? "[no number]": number);
          //  System.out.println(i+1+". " + name +" " + surname +", " + number );

        }
        System.out.print("Select a record: ");
        int x = Integer.valueOf(scanner.nextLine());
        lista.remove(x-1);
        System.out.println("The record removed!");

    }
    //*************************************************************************
    private static void editRecords() {
/*
        for ( int i = 0; i < lista.size(); i++){

            String name = lista.get(i).getName();
           // String surname = lista.get(i).getSurname();
            String number = lista.get(i).getNumber();
            number = (number == "" ? "[no number]": number);
           // System.out.println(i+1+". " + name +" " + surname +", " + number );

        }
*/
        for ( int i = 0; i < lista.size(); i++){

            String name = "";
            if(lista.get(i).isPerson()){
                Person person = (Person) lista.get(i);
                name = person.getName() +" " + person.getSurname();
            }else {
                name = lista.get(i).getName();
            }
            System.out.println(i+1 + " " + name);
        }

        System.out.print("Select a record: ");
        int x = Integer.valueOf(scanner.nextLine());

        if(lista.get(x-1).isPerson()){

            System.out.print("Select a field (name, surname, birth, gender, number):");
            String field = scanner.nextLine();

            switch (field){
                case "name":
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    lista.get(x-1).setName(name);

                    break;
                case "surname":
                    System.out.print("Enter surname: ");
                    String surname = scanner.nextLine();
                    ((Person)lista.get(x-1)).setSurname(surname);
                    break;

                case "birth":
                    System.out.print("Enter the birth date:");
                    String birthdate = scanner.nextLine();

                    try {
                        LocalDateTime localDateTime = LocalDateTime.parse(birthdate);
                    }catch (Exception e){
                        birthdate = "";
                        System.out.println("Bad birth date!");
                    }
                    ((Person)lista.get(x-1)).setBirtDate(birthdate);


                    break;

                case "gender":
                    System.out.print("Enter the gender (M, F):");
                    String gender = scanner.nextLine();
                    gender =gender.toUpperCase();
                    if(!(gender.equals("M") || gender.equals("F")))
                    {
                        System.out.println("Bad gender!");
                        gender ="";
                    }
                    ((Person)lista.get(x-1)).setGender(gender);

                    break;

                case "number":
                    System.out.print("Enter number: ");
                    String number = scanner.nextLine();
                    if(validatePhone(number)){
                        lista.get(x-1).setNumber(number);
                    }
                    else{
                        lista.get(x-1).setNumber("");
                        System.out.println("Wrong number format!");
                        System.out.println("The record updated!");
                    }

                    break;
            }



        }else{
            System.out.print("Select a field (name, address, number):");
            String field = scanner.nextLine();
            switch (field) {
                case "name":
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    lista.get(x-1).setName(name);

                    break;

                case "address":
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    ((Organization)lista.get(x-1)).setAddress(address);

                    break;


                case "number":
                    System.out.print("Enter number: ");
                    String number = scanner.nextLine();
                    if(validatePhone(number)){
                        lista.get(x-1).setNumber(number);
                    }
                    else{
                        lista.get(x-1).setNumber("");
                        System.out.println("Wrong number format!");
                        System.out.println("The record updated!");
                    }

                    break;


            }

        }

            LocalDateTime localDateTime = LocalDateTime.now();
            lista.get(x-1).setTimeEdit(localDateTime);
            System.out.println("The record updated!");




    }

    //*************************************************************************
    private static void menuSecond() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the type (person, organization):");
        String type = scanner.nextLine();

        switch (type){
            case "person":

                Person person = new Person();
                person.setPerson(true);

                LocalDateTime lc = LocalDateTime.now();
                person.setTimeCreated(lc);
                person.setTimeEdit(lc);

                System.out.print("Enter the name:");
                String name = scanner.nextLine();
                person.setName(name);

                System.out.print("Enter the surname:");
                String surname = scanner.nextLine();
                person.setSurname(surname);
                System.out.print("Enter the birth date:");
                String birthdate = scanner.nextLine();

                try {
                    LocalDateTime localDateTime = LocalDateTime.parse(birthdate);
                }catch (Exception e){
                    birthdate = "";
                    System.out.println("Bad birth date!");
                }

                person.setBirtDate(birthdate);
                System.out.print("Enter the gender (M, F):");
                String gender = scanner.nextLine();
                gender =gender.toUpperCase();
                if(!(gender.equals("M") || gender.equals("F")))
                {
                    System.out.println("Bad gender!");
                    gender ="";
                }

                person.setGender(gender);
                System.out.print("Enter the number:");
                String number = scanner.nextLine();
                if(validatePhone(number)){
                    person.setNumber(number);
                }
                else{
                    person.setNumber("");
                    System.out.println("Wrong number format!");
                }

                lista.add(person);
                System.out.println("The record added.");
                break;

            case "organization":

                Organization organization = new Organization();
                organization.setPerson(false);

                lc = LocalDateTime.now();
                organization.setTimeCreated(lc);
                organization.setTimeEdit(lc);


                System.out.print("Enter the organization name:");
                 name = scanner.nextLine();
                organization.setName(name);

                System.out.print("Enter the address:");
               String address = scanner.nextLine();
                organization.setAddress(address);

                System.out.print("Enter the number:");
                 number = scanner.nextLine();
                if(validatePhone(number)){
                    organization.setNumber(number);
                }
                else{
                    organization.setNumber("");
                    System.out.println("Wrong number format!");
                }

                lista.add(organization);
                System.out.println("The record added.");

                break;


        }


    }
    //*************************************************************************
    private static boolean validatePhone(String numberToCheck){

        String regex =
                "^(?i)([+](\\w )?([(]?\\w{2,}[)]?)?|[(]\\w{2,}[)]|\\w{1,}|\\w{2,}[ -][(]\\w{2,}[)])([ -][0-9a-z]{2,})?([ -][0-9a-z]{2,})?([ -][0-9a-z]{2,})?$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(numberToCheck);

        return matcher.matches();
    }

    //*************************************************************************

}
