package com.example.isabella.app_movel;

public class Usuario {
    String nome;
    String email;
    String uid;

    public Usuario(String nome, String email, String uid) {
        this.nome = nome;
        this.email = email;
        this.uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
