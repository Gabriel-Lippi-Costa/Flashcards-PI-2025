
package persistencia;

import modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UsuarioDAO {

    public int cadastrar(Usuario usuario) throws Exception {
    var sql = "INSERT INTO tb_usuarios (email, nome_usuario, senha, tipo_usuario) VALUES (?, ?, ?, ?)";
    var fabricaDeConexoes = new ConnectionFactory();
    
    try (
        Connection conexao = fabricaDeConexoes.obterConexao();
        PreparedStatement ps = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
    ) {
        ps.setString(1, usuario.getEmail());
        ps.setString(2, usuario.getNomeUsuario());
        ps.setString(3, usuario.getSenha());
        ps.setString(4, usuario.getTipoUsuario());
       
        ps.executeUpdate();
        
        try (var rs = ps.getGeneratedKeys()) {
            rs.next();
                return rs.getInt(1);
    }
}}

    public Usuario autenticar(Usuario usuario) throws Exception {
        var sql = "SELECT  id_usuario, email, senha, nome_usuario, tipo_usuario FROM tb_usuarios WHERE email = ? AND senha = ?";
        var fabricaDeConexoes = new ConnectionFactory();
        try (
            Connection conexao = fabricaDeConexoes.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql)
        ) {
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getSenha());
            try (
                ResultSet rs = ps.executeQuery()
            ) {
                if (rs.next()){
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNomeUsuario(rs.getString("nome_usuario"));
                usuario.setTipoUsuario(rs.getString("tipo_usuario"));
                return usuario;
                }
                return null;
            }
        }
    }
        public void excluir(Usuario usuario) throws Exception {
        var sql = "DELETE FROM tb_usuarios WHERE email = ?";
        var fabricaDeConexoes = new ConnectionFactory();
        try(
            Connection conexao = fabricaDeConexoes.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql)
        ){
            ps.setString(1, usuario.getEmail());
            ps.execute();
        }
    }
        public static void editar(Usuario novo) throws Exception {
        var sql = "UPDATE tb_usuarios SET email = ?, nome_usuario = ?, senha = ?, tipo_usuario = ? WHERE id_usuario = ?";
        var fabricaDeConexoes = new ConnectionFactory();
        try(
            Connection conexao = fabricaDeConexoes.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql)
        ){
            ps.setString(1, novo.getEmail());
            ps.setString(2, novo.getNomeUsuario());
            ps.setString(3, novo.getSenha());
            ps.setString(4, novo.getTipoUsuario());
            ps.setInt(5, novo.getIdUsuario());
            ps.execute();
        }
    }
}
