package org.example.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="idPeople" )
public class Student extends People{

    private String classe;

    public Student(){
        super();
    }

    public Student(String classe) {
        super();
        this.classe = classe;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    @Override
    public String toString() {
        return super.toString()
                + "Student{" +
                "classe='" + classe + '\'' +
                '}';
    }
}
