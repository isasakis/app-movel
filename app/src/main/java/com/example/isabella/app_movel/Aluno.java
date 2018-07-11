package com.example.isabella.app_movel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Aluno {
    String nome;
    String uid;
    String dataNasc;
    String cpf;
    String rg;
    String nomePai;
    String nomeMae;
    String sexo;
    String fonePai;
    String foneMae;
    String problemaSaude;
    String restricaoAlimentar;
    Remedio remedio;
    ArrayList<Remedio> remedios;

    public Aluno() {
    }

    public String getNome() {
        return nome;
    }

    public void setNomeAluno(String nome) {
        this.nome = nome;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFonePai() {
        return fonePai;
    }

    public void setFonePai(String fonePai) {
        this.fonePai = fonePai;
    }

    public String getFoneMae() {
        return foneMae;
    }

    public void setFoneMae(String foneMae) {
        this.foneMae = foneMae;
    }

    public String getProblemaSaude() {
        return problemaSaude;
    }

    public void setProblemaSaude(String problemaSaude) {
        this.problemaSaude = problemaSaude;
    }

    public String getRestricaoAlimentar() {
        return restricaoAlimentar;
    }

    public void setRestricaoAlimentar(String restricaoAlimentar) {
        this.restricaoAlimentar = restricaoAlimentar;
    }

    public Remedio getRemedio() {
        return remedio;
    }

    public void setRemedio(Remedio remedio) {
        this.remedio = remedio;
    }

    public ArrayList<Remedio> getRemedios() {
        return remedios;
    }

    public void setRemedios(ArrayList<Remedio> remedios) {
        this.remedios = remedios;
    }
}
