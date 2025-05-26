package modelo;

public class Usuario {
    private int idUsuario;
   private String email;
   private String nomeUsuario;
   private String senha;
   private String tipoUsuario;
   
   public Usuario (int idUsuario, String email, String nomeUsuario, String senha, String tipoUsuario) {
       this.idUsuario = idUsuario;
       this.email = email;
       this.nomeUsuario = nomeUsuario;
       this.senha = senha;
       this.tipoUsuario = tipoUsuario;
   }

   public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
}
