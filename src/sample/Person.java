package sample;

import java.time.LocalDate;
import java.util.Date;

public class Person {

    String name, evidenceNumber, email, telephoneNumber;
    LocalDate dateOfBirth;

    public Person(){}

    public Person(String name, LocalDate dateOfBirth, String evidenceNumber, String email, String telephoneNumber){
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.evidenceNumber = evidenceNumber;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.replace("<(.*)>", "");
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEvidenceNumber() {
        return evidenceNumber;
    }

    public void setEvidenceNumber(String evidenceNumber) {
        this.evidenceNumber = evidenceNumber.replace("<(.*)>", "");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.replace("<(.*)>", "");
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber.replace("<(.*)>", "");
    }

}
