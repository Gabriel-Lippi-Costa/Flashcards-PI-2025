/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import modelo.Baralho;
import modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Carta;

/**
 *
 * @author zion
 */
public class BaralhoDAO {

    public static Baralho criarBaralho(Baralho baralho) throws Exception {
        var sql = "INSERT INTO tb_baralhos (nome_baralho, tema, id_usuario, total_de_erros, total_de_acertos, media_de_acertos) VALUES (?, ?, ?, ?, ?, ?)";
        var fabricaDeConexoes = new ConnectionFactory();
        try (
                Connection conexao = fabricaDeConexoes.obterConexao(); PreparedStatement ps = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, baralho.getNomeBaralho());
            ps.setString(2, baralho.getTema());
            ps.setInt(3, baralho.getUsuario().getIdUsuario());
            ps.setInt(4, baralho.getTotalDeErros());
            ps.setInt(5, baralho.getTotalDeAcertos());
            ps.setDouble(6, baralho.getMediaDeAcertos());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    baralho.setIdBaralho(rs.getInt(1));
                }
            }
        }
        return baralho;
    }

    public static ArrayList<Baralho> listarBaralhos(Usuario usuario) throws Exception {
        ArrayList<Baralho> listaDeBaralhos = new ArrayList<>();
        String sql = "SELECT id_baralho, nome_baralho, tema, id_usuario, media_de_acertos, total_de_erros, total_de_acertos FROM tb_baralhos WHERE id_usuario = ?";
        ConnectionFactory fabricaDeConexoes = new ConnectionFactory();
        try (Connection conexao = fabricaDeConexoes.obterConexao(); PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, usuario.getIdUsuario());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Baralho baralho = new Baralho(
                            rs.getInt("id_baralho"),
                            rs.getString("nome_baralho"),
                            rs.getString("tema"),
                            usuario,
                            rs.getInt("total_de_erros"),
                            rs.getInt("total_de_acertos"),
                            rs.getDouble("media_de_acertos")
                    );
                    listaDeBaralhos.add(baralho);
                }
            }
        }

        return listaDeBaralhos;
    }

    public static void excluir(Baralho baralho) throws Exception {
        var sql = "DELETE FROM tb_baralhos WHERE nome_baralho = ? and id_usuario = ?";
        var fabricaDeConexoes = new ConnectionFactory();
        try (
                Connection conexao = fabricaDeConexoes.obterConexao(); PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, baralho.getNomeBaralho());
            ps.setInt(2, baralho.getUsuario().getIdUsuario());
            ps.execute();
        }
    }

    public static void editar(Baralho baralho) throws Exception {
        var sql = "UPDATE tb_baralhos SET nome_baralho = ?, tema = ?, id_usuario = ?, total_de_erros = ?, total_de_acertos = ?, media_de_acertos = ? WHERE id_usuario = ? AND id_baralho = ?";
        var fabricaDeConexoes = new ConnectionFactory();
        try (
                Connection conexao = fabricaDeConexoes.obterConexao(); PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, baralho.getNomeBaralho());
            ps.setString(2, baralho.getTema());
            ps.setInt(3, baralho.getUsuario().getIdUsuario());
            ps.setInt(4, baralho.getTotalDeErros());
            ps.setInt(5, baralho.getTotalDeAcertos());
            ps.setDouble(6, baralho.getMediaDeAcertos());
            ps.setInt(7, baralho.getUsuario().getIdUsuario());
            ps.setInt(8, baralho.getIdBaralho());
            ps.execute();
        }
    }

    public static Baralho importar(int id, Usuario usuario) throws Exception {
        var sql = "SELECT id_baralho, nome_baralho, tema, id_usuario, media_de_acertos, total_de_erros, total_de_acertos FROM tb_baralhos WHERE id_baralho  = ?";
        var fabricaDeConexoes = new ConnectionFactory();
        Connection conexao = fabricaDeConexoes.obterConexao();
        PreparedStatement ps = conexao.prepareStatement(sql);
        {
            ps.setInt(1, id);
            Baralho baralho = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                baralho = new Baralho(
                        12,
                        rs.getString("nome_baralho"),
                        rs.getString("tema"),
                        usuario,
                        0, 0, 0
                );
            }
            if (baralho != null) {
                ArrayList<Carta> listaDeCartas = new ArrayList<>();
                baralho = criarBaralho(baralho);
                sql = "SELECT id_card, pergunta, resposta FROM tb_cards WHERE id_baralho  = ?";
                ps = conexao.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                {
                    while (rs.next()) {
                        Carta carta = new Carta(
                                0,
                                rs.getString("pergunta"),
                                rs.getString("resposta"),
                                baralho,
                                0, 0, 0
                        );
                        listaDeCartas.add(carta);
                    }
                    for (Carta carta : listaDeCartas) {
                        Carta cartaNova = CardDAO.criarCard(carta);
                    }
                }
            }
            rs.close();
            ps.close();
            conexao.close();
            return baralho;
        }
    }

    public static void criarBaralhosAutomatico(int id) throws Exception {
        String[] comandos = {
            "INSERT INTO tb_baralhos (nome_baralho, tema, id_usuario, total_de_erros, total_de_acertos, media_de_acertos) VALUES ('Botânica', 'Biologia', ?, 0, 0, 0)",
            "INSERT INTO tb_baralhos (nome_baralho, tema, id_usuario, total_de_erros, total_de_acertos, media_de_acertos) VALUES ('Genética', 'Biologia', ?, 0, 0, 0)",
            "INSERT INTO tb_baralhos (nome_baralho, tema, id_usuario, total_de_erros, total_de_acertos, media_de_acertos) VALUES ('Ecologia', 'Biologia', ?, 0, 0, 0)",
            "INSERT INTO tb_baralhos (nome_baralho, tema, id_usuario, total_de_erros, total_de_acertos, media_de_acertos) VALUES ('Past Simple', 'Língua Inglesa', ?, 0, 0, 0)",
            "INSERT INTO tb_baralhos (nome_baralho, tema, id_usuario, total_de_erros, total_de_acertos, media_de_acertos) VALUES ('Present Simple', 'Língua Inglesa', ?, 0, 0, 0)",
            "INSERT INTO tb_baralhos (nome_baralho, tema, id_usuario, total_de_erros, total_de_acertos, media_de_acertos) VALUES ('Future', 'Língua Inglesa', ?, 0, 0, 0)",
            "INSERT INTO tb_baralhos (nome_baralho, tema, id_usuario, total_de_erros, total_de_acertos, media_de_acertos)\n"
            + "VALUES ('Produtos Notáveis', 'Matemática', ?, 0, 0, 0);",
            "INSERT INTO tb_baralhos (nome_baralho, tema, id_usuario, total_de_erros, total_de_acertos, media_de_acertos)\n"
            + "VALUES ('Funções', 'Matemática', ?, 0, 0, 0);",
            "INSERT INTO tb_baralhos (nome_baralho, tema, id_usuario, total_de_erros, total_de_acertos, media_de_acertos)\n"
            + "VALUES ('Geometria Espacial', 'Matemática', ?, 0, 0, 0);"
        };

        var fabricaDeConexoes = new ConnectionFactory();
        try (Connection conexao = fabricaDeConexoes.obterConexao()) {
            for (String comando : comandos) {
                try (PreparedStatement ps = conexao.prepareStatement(comando)) {
                    ps.setInt(1, id);
                    ps.executeUpdate();
                }
            }
        }
    }
}
