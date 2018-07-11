package com.example.isabella.app_movel;

public class Remedio {

    String nome;
    String horario;
    String observacao;

    public Remedio( ) {

    }

    public Remedio(String nome, String horario, String observacao) {
        this.nome = nome;
        this.horario = horario;
        this.observacao = observacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
