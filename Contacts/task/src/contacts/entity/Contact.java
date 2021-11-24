package contacts.entity;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Contact extends BaseClass implements Serializable {

    //default serialVersion id
    private static final long serialVersionUID = 1L;


    private String name;
    private String number;
    private LocalDateTime timeCreated;
    private LocalDateTime timeEdit;
    private boolean isPerson;




    public void jakasMetoda(Field field){


    }


    private void writeObject(ObjectOutputStream oos) throws Exception {
        // write the custom serialization code here
        oos.defaultWriteObject();


    }


    private void readObject(ObjectInputStream ois) throws Exception {
        // write the custom deserialization code here



    }


    public Contact() {
    }

    public Contact(String name, String number, LocalDateTime timeCreated, LocalDateTime timeEdit, boolean isPerson) {
        this.name = name;
        this.number = number;
        this.timeCreated = timeCreated;
        this.timeEdit = timeEdit;
        this.isPerson = isPerson;
    }

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

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public LocalDateTime getTimeEdit() {
        return timeEdit;
    }

    public void setTimeEdit(LocalDateTime timeEdit) {
        this.timeEdit = timeEdit;
    }

    public boolean isPerson() {
        return isPerson;
    }

    public void setPerson(boolean person) {
        isPerson = person;
    }


    @Override
    public List<Field> returnListOfFields() {
        Field[] fields = Contact.class.getFields();
        List<Field> collect = Arrays.stream(fields).collect(Collectors.toList());
        return collect;
    }


    /*
    public List<Field> returnListOfFields(){
        Field[] fields = Contact.class.getFields();
        List<Field> collect = Arrays.stream(fields).collect(Collectors.toList());
    return collect;
    }
*/


}
