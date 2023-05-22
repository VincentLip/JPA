package org.example.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idPeople")
public class Professor extends People {

    private String matiere;

    public Professor(){
        super();
    }

    public Professor(String matiere) {
        super();
        this.matiere = matiere;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    @Override
    public String toString() {
        return super.toString()
                + "Professor{" +
                "matiere='" + matiere + '\'' +
                '}';
    }
}
