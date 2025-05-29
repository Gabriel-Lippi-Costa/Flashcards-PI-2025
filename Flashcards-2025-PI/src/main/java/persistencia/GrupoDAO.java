/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Baralho;
import modelo.Grupo;
import modelo.Usuario;

/**
 *
 * @author zion
 */
public class GrupoDAO {

    public static Grupo criarGrupo(Grupo grupo, Usuario professor) throws Exception {
        var sql = "INSERT INTO tb_grupo_alunos (nome,id_usuario) VALUES (?, ?)";
        var fabricaDeConexoes = new ConnectionFactory();
        try (
                Connection conexao = fabricaDeConexoes.obterConexao(); PreparedStatement ps = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, grupo.getNomeGrupo());
            ps.setInt(2, professor.getIdUsuario());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    grupo.setIdGrupo(rs.getInt(1));
                }
            }
        }
        return grupo;
    }

    public void adicionarAlunoGrupo(Usuario aluno, Grupo grupo) throws Exception {
    var sql = "INSERT INTO tb_associacao_alunos_grupos (id_grupo_alunos, id_usuario) VALUES (?, ?)";
    var fabricaDeConexoes = new ConnectionFactory();
    try (
        Connection conexao = fabricaDeConexoes.obterConexao(); 
        PreparedStatement ps = conexao.prepareStatement(sql);
    ) {
        ps.setInt(1, grupo.getIdGrupo());
        ps.setInt(2, aluno.getIdUsuario());
        ps.executeUpdate();
    }
}
    public static Usuario selecionarAluno(String email) throws Exception {
        Usuario aluno = null;
        String sql = "SELECT id_usuario, email, nome_usuario, tipo_usuario FROM tb_usuarios WHERE email = ?";
        ConnectionFactory fabricaDeConexoes = new ConnectionFactory();
        try (Connection conexao = fabricaDeConexoes.obterConexao(); PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    aluno = new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("email"),
                            rs.getString("nome_usuario"),
                            "senha X", rs.getString("tipo_usuario")
                          
                    );
                }
                    return aluno;
            }}
    }
    public static ArrayList<Grupo> listarGruposAlunos(Usuario usuario) throws Exception {
        ArrayList<Grupo> listaDeGrupos = new ArrayList<>();
        String sql = "SELECT g.id_grupo_alunos, nome, p.nome_usuario FROM tb_grupo_alunos AS g JOIN tb_associacao_alunos_grupos AS a USING(id_grupo_alunos) JOIN tb_usuarios AS p ON g.id_usuario = p.id_usuario WHERE a.id_usuario = ?";
        ConnectionFactory fabricaDeConexoes = new ConnectionFactory();
        try (Connection conexao = fabricaDeConexoes.obterConexao(); PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, usuario.getIdUsuario());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Grupo grupo = new Grupo(
                            rs.getInt("id_grupo_alunos"),
                            rs.getString("nome_usuario"),
                            rs.getString("nome")
                    );
                    listaDeGrupos.add(grupo);
                }
            }
        }

        return listaDeGrupos;
    }
    public static ArrayList<Grupo> listarGruposProfessor(Usuario professor) throws Exception {
        ArrayList<Grupo> listaDeGrupos = new ArrayList<>();
        String sql = "SELECT g.id_grupo_alunos, nome, p.nome_usuario FROM tb_grupo_alunos AS g JOIN tb_usuarios AS p ON g.id_usuario = p.id_usuario WHERE p.id_usuario = ?";
        ConnectionFactory fabricaDeConexoes = new ConnectionFactory();
        try (Connection conexao = fabricaDeConexoes.obterConexao(); PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, professor.getIdUsuario());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Grupo grupo = new Grupo(
                            rs.getInt("id_grupo_alunos"),
                            rs.getString("nome_usuario"),
                            rs.getString("nome")
                    );
                    listaDeGrupos.add(grupo);
                }
            }
        }

        return listaDeGrupos;
    }
    public void adicionarBaralhoGrupo(Baralho baralho, Grupo grupo) throws Exception {
    var sql = "INSERT INTO tb_associacao_baralhos_grupos (id_baralho,id_grupo_alunos) VALUES (?, ?)";
    var fabricaDeConexoes = new ConnectionFactory();
    try (
        Connection conexao = fabricaDeConexoes.obterConexao(); 
        PreparedStatement ps = conexao.prepareStatement(sql);
    ) {
        ps.setInt(1, baralho.getIdBaralho());
        ps.setInt(2, grupo.getIdGrupo());
        ps.executeUpdate();
    }
}
    public static void editarGrupo(Grupo grupo) throws Exception {
        var sql = "UPDATE tb_grupo_alunos SET nome = ? WHERE id_grupo_alunos = ?";
        var fabricaDeConexoes = new ConnectionFactory();
        try (
                Connection conexao = fabricaDeConexoes.obterConexao(); PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, grupo.getNomeGrupo());
            ps.setInt(2, grupo.getIdGrupo());
            ps.execute();
        }
    }
}
