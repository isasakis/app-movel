package com.example.isabella.app_movel;

public class Turma {
    Integer codTurma;
    String nomeTurma;
    String turnoTurma;

    public Turma() {

    }

    public Turma(Integer codTurma, String nomeTurma, String turnoTurma) {
        this.codTurma = codTurma;
        this.nomeTurma = nomeTurma;
        this.turnoTurma = turnoTurma;
    }

    public Integer getCodTurma() {
        return codTurma;
    }

    public void setCodTurma(Integer codTurma) {
        this.codTurma = codTurma;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public String getTurnoTurma() {
        return turnoTurma;
    }

    public void setTurnoTurma(String turnoTurma) {
        this.turnoTurma = turnoTurma;
    }
}
