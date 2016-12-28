package com.reskitow.horari_m8.Model;

/**
 * Created by RW-PC on 27/12/2016.
 */

public class Horari {

    private int id;
    private String grup;
    private String modul;
    private String professor;
    private String aula;
    private String horaInici;
    private String horaFinal;
    private int diaSetmana;

    public Horari(int id, String grup, String modul, String professor, String aula, String horaInici, String horaFinal, int diaSetmana) {
        this.id = id;
        this.grup = grup;
        this.modul = modul;
        this.professor = professor;
        this.aula = aula;
        this.horaInici = horaInici;
        this.horaFinal = horaFinal;
        this.diaSetmana = diaSetmana;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGrup() {
        return grup;
    }

    public void setGrup(String grup) {
        this.grup = grup;
    }

    public String getModul() {
        return modul;
    }

    public void setModul(String modul) {
        this.modul = modul;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getHoraInici() {
        return horaInici;
    }

    public void setHoraInici(String horaInici) {
        this.horaInici = horaInici;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public int getDiaSetmana() {
        return diaSetmana;
    }

    public void setDiaSetmana(int diaSetmana) {
        this.diaSetmana = diaSetmana;
    }

    @Override
    public String toString() {
        return "Horari{" +
                "id=" + id +
                ", grup='" + grup + '\'' +
                ", modul='" + modul + '\'' +
                ", professor='" + professor + '\'' +
                ", aula='" + aula + '\'' +
                ", horaInici='" + horaInici + '\'' +
                ", horaFinal='" + horaFinal + '\'' +
                ", diaSetmana=" + diaSetmana +
                '}';
    }
}
