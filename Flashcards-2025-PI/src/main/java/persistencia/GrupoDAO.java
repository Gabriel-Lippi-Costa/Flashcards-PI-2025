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

    public static void adicionarAlunoGrupo(Usuario aluno, Grupo grupo) throws Exception {
        var sql = "INSERT INTO tb_associacao_alunos_grupos (id_grupo_alunos, id_usuario) VALUES (?, ?)";
        var fabricaDeConexoes = new ConnectionFactory();
        try (
                Connection conexao = fabricaDeConexoes.obterConexao(); PreparedStatement ps = conexao.prepareStatement(sql);) {
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
            }
        }
    }

    public static ArrayList<Grupo> listarGruposAlunos(Usuario usuario) throws Exception {
        ArrayList<Grupo> listaDeGrupos = new ArrayList<>();
        String sql = "SELECT g.id_grupo_alunos, nome, p.nome_usuario, p.email, p.id_usuario FROM tb_grupo_alunos AS g JOIN tb_associacao_alunos_grupos AS a USING(id_grupo_alunos) JOIN tb_usuarios AS p ON g.id_usuario = p.id_usuario WHERE a.id_usuario = ?";
        ConnectionFactory fabricaDeConexoes = new ConnectionFactory();
        try (Connection conexao = fabricaDeConexoes.obterConexao(); PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, usuario.getIdUsuario());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Grupo grupo = new Grupo(
                            rs.getInt("id_grupo_alunos"),
                            new Usuario(rs.getInt("p.id_usuario"),rs.getString("email"), rs.getString("nome_usuario"), "senha X", "professor"),
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
                            professor,
                            rs.getString("nome")
                    );
                    listaDeGrupos.add(grupo);
                }
            }
        }

        return listaDeGrupos;
    }

    public static ArrayList<Baralho> listarBaralhosDoGrupo(Grupo grupo) throws Exception {
        ArrayList<Baralho> listaDeBaralhos = new ArrayList<>();
        String sql = "SELECT b.id_baralho, email, nome_baralho, nome_usuario, tema FROM tb_baralhos AS b JOIN tb_associacao_baralhos_grupos USING(id_baralho) JOIN tb_grupo_alunos AS p USING(id_grupo_alunos) JOIN tb_usuarios AS u ON u.id_usuario = p.id_usuario WHERE id_grupo_alunos = ?";
        ConnectionFactory fabricaDeConexoes = new ConnectionFactory();
        try (Connection conexao = fabricaDeConexoes.obterConexao(); PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, grupo.getIdGrupo());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Baralho baralho = new Baralho(
                            rs.getInt("id_baralho"),
                            rs.getString("nome_baralho"),
                            rs.getString("tema"),
                            grupo.getProfessor(),
                            0,
                            0, 
                            0
                    );
                    listaDeBaralhos.add(baralho);
                }
            }
        }

        return listaDeBaralhos;
    }
    public static ArrayList<Usuario> listarAlunosDoGrupo(Grupo grupo) throws Exception {
        ArrayList<Usuario> listaDeAlunos = new ArrayList<>();
        String sql = "SELECT a.id_usuario, nome_usuario, email, senha, tipo_usuario FROM tb_usuarios AS a JOIN tb_associacao_alunos_grupos USING(id_usuario) WHERE id_grupo_alunos = ?";
        ConnectionFactory fabricaDeConexoes = new ConnectionFactory();
        try (Connection conexao = fabricaDeConexoes.obterConexao(); PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, grupo.getIdGrupo());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Usuario aluno = new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("email"),
                            rs.getString("nome_usuario"),
                            rs.getString("senha"),
                            rs.getString("tipo_usuario")
                    );
                    listaDeAlunos.add(aluno);
                }
            }
        }

        return listaDeAlunos;
    }

    public static void adicionarBaralhoGrupo(Baralho baralho, Grupo grupo) throws Exception {
        var sql = "INSERT INTO tb_associacao_baralhos_grupos (id_baralho,id_grupo_alunos) VALUES (?, ?)";
        var fabricaDeConexoes = new ConnectionFactory();
        try (
                Connection conexao = fabricaDeConexoes.obterConexao(); PreparedStatement ps = conexao.prepareStatement(sql);) {
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
     public static void removerAlunoGrupo(Usuario aluno, Grupo grupo) throws Exception {
        var sql = "DELETE FROM tb_associacao_alunos_grupos WHERE id_grupo_alunos = ? AND id_usuario = ?";
        var fabricaDeConexoes = new ConnectionFactory();
        try (
                Connection conexao = fabricaDeConexoes.obterConexao(); PreparedStatement ps = conexao.prepareStatement(sql);) {
            ps.setInt(1, grupo.getIdGrupo());
            ps.setInt(2, aluno.getIdUsuario());
            ps.executeUpdate();
        }
    }
     public static void removerBaralhoGrupo(Baralho baralho, Grupo grupo) throws Exception {
        var sql = "DELETE FROM tb_associacao_baralhos_grupos WHERE id_grupo_alunos = ? AND id_baralho = ?";
        var fabricaDeConexoes = new ConnectionFactory();
        try (
                Connection conexao = fabricaDeConexoes.obterConexao(); PreparedStatement ps = conexao.prepareStatement(sql);) {
            ps.setInt(1, grupo.getIdGrupo());
            ps.setInt(2, baralho.getIdBaralho());
            ps.executeUpdate();
        }
    }
      public static void excluirGrupo(Grupo grupo) throws Exception {
        var sql = "DELETE FROM tb_grupo_alunos WHERE id_grupo_alunos = ?";
        var fabricaDeConexoes = new ConnectionFactory();
        try (
                Connection conexao = fabricaDeConexoes.obterConexao(); PreparedStatement ps = conexao.prepareStatement(sql);) {
            ps.setInt(1, grupo.getIdGrupo());
            ps.executeUpdate();
        }
    }
}
