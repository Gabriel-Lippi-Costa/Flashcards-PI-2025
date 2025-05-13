package br.lippi.flashcards.pi.modelo;

public class Usuario {
    
   private String email;
   private String nome_usuario;
   private String senha;
   private String tipo_usuario;
   
   public Usuario (String email, String nome_usuario, String senha, String tipo_usuario) {
       this.email = email;
       this.nome_usuario = nome_usuario;
       this.senha = senha;
       this.tipo_usuario = tipo_usuario;
   }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
    
}
