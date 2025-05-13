package br.lippi.flashcards.pi.persistencia;

import br.lippi.flashcards.pi.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {
    
    public void cadastrar(Usuario usuario) throws Exception{
        var sql = "INSERT INTO tb_usuarios (email, nome_usuario, senha, tipo_usuario) VALUES (?, ?, ?, ?)";
        var fabricaDeConexoes = new ConnectionFactory();
        Connection conexao = fabricaDeConexoes.obterConexao();
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setString(1, usuario.getEmail());
        ps.setString(2, usuario.getNome_usuario());
        ps.setString(3, usuario.getSenha());
        ps.setString(4, usuario.getTipo_usuario());
        ps.execute();
        ps.close();
        conexao.close();
    }
    
}
