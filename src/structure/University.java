package structure;

import exception.CountException;

import java.util.*;

public class University extends Building {

    private String uniName;
    private int dateOfEstablishment;
    private List<Faculty> faculties;

    public University(String uniName, int dateOfEstablishment) {
        this.uniName = uniName;
        this.dateOfEstablishment = dateOfEstablishment;
    }

    @Override
    public String toString() {
        return this.uniName + ", established in " + this.dateOfEstablishment;
    }

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public int getDateOfEstablishment() {
        return dateOfEstablishment;
    }

    public void setDateOfEstablishment(int dateOfEstablishment) {
        this.dateOfEstablishment = dateOfEstablishment;
    }

    public List<Faculty> getFaculty() {
        return faculties;
    }

    public void setFaculty(List<Faculty> faculties) {
        if (faculties.size() <= 1) {
            throw new CountException("Number of faculties is invalid");
        }
        this.faculties = faculties;
    }
}
