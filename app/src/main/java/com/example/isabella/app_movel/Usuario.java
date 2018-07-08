package com.example.isabella.app_movel;

public class Usuario {

    String user_id;
    String email;
    String senha;
    String tipo;

    public Usuario(){

    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String user_id, String email, String senha, String tipo) {
        this.user_id = user_id;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
