package br.lippi.flashcards.pi.persistencia;

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
    
    public void atualizar(Pessoa pessoa) throws Exception {
        var sql = "UPDATE tb_pessoa SET nome=?, fone=?, email=? WHERE cod_pessoa=?";
        var fabricaDeConexoes = new ConnectionFactory();
        try(
           var conexao = fabricaDeConexoes.obterConexao();
           var ps = conexao.prepareStatement(sql);
        ) {
           ps.setString(1, pessoa.getNome());
           ps.setString(2, pessoa.getFone());
           ps.setString(3, pessoa.getEmail());
           ps.setInt(1, pessoa.getCodigo());
           ps.execute();
           
        }
    }
    public void listar() throws Exception {
        var sql = "SELECT * FROM tb_pessoa";
        var fabricaDeConexoes = new ConnectionFactory();
        try(
           var conexao = fabricaDeConexoes.obterConexao();
           var ps = conexao.prepareStatement(sql);
        ){
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
           var nome = rs.getString("nome");
           var fone = rs.getString("fone");
           var email = rs.getString("email");  
           System.out.printf("%s,%s,%s\n", nome, fone, email);
           }
        }
    }
    
}
