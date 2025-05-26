/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import modelo.Baralho;
import modelo.Carta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author zion
 */
public class CardDAO {

    public static Carta criarCard(Carta carta) throws Exception {
        var sql = "INSERT INTO tb_cards (pergunta, resposta, id_baralho,total_de_acertos, total_de_erros, media_de_acertos) VALUES (?, ?, ?, ?, ?,?)";
        var fabricaDeConexoes = new ConnectionFactory();
        try (
                Connection conexao = fabricaDeConexoes.obterConexao(); PreparedStatement ps = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, carta.getPergunta());
            ps.setString(2, carta.getResposta());
            ps.setInt(3, carta.getBaralho().getIdBaralho());
            ps.setInt(4, carta.getTotalDeAcertos());
            ps.setInt(5, carta.getTotalDeErros());
            ps.setDouble(6, carta.getMediaDeAcertos());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    carta.setIdCarta(rs.getInt(1));
                }
            }
        }
        return carta;
    }

    public static ArrayList<Carta> listarCartas(Baralho baralho) throws Exception {
        ArrayList<Carta> listaDeCartas = new ArrayList<>();
        String sql = "SELECT id_card, pergunta, resposta, id_baralho, media_de_acertos, total_de_acertos, total_de_erros FROM tb_cards WHERE id_baralho = ? AND (SELECT id_usuario FROM tb_baralhos WHERE id_baralho = ?) = ?";
        ConnectionFactory fabricaDeConexoes = new ConnectionFactory();
        try (Connection conexao = fabricaDeConexoes.obterConexao(); PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, baralho.getIdBaralho());
            ps.setInt(2, baralho.getIdBaralho());
            ps.setInt(3, baralho.getUsuario().getIdUsuario());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Carta carta = new Carta(
                            rs.getInt("id_card"),
                            rs.getString("pergunta"),
                            rs.getString("resposta"),
                            baralho,
                            rs.getDouble("media_de_acertos"),
                            rs.getInt("total_de_acertos"),
                            rs.getInt("total_de_erros")
                    );
                    listaDeCartas.add(carta);
                }
            }
        }

        return listaDeCartas;
    }

    public void excluir(Carta carta) throws Exception {
        var sql = "DELETE FROM tb_cards WHERE id_baralho = ? AND pergunta = ? AND resposta = ?";
        var fabricaDeConexoes = new ConnectionFactory();
        try (
                Connection conexao = fabricaDeConexoes.obterConexao(); PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, carta.getBaralho().getIdBaralho());
            ps.setString(2, carta.getPergunta());
            ps.setString(3, carta.getResposta());
            ps.execute();
        }
    }

    public static void editar(Carta cartaNova) throws Exception {
        var sql = "UPDATE tb_cards SET pergunta = ?, resposta = ?, id_baralho = ?, media_de_acertos = ?, total_de_acertos = ?, total_de_erros = ? WHERE id_card = ? AND id_baralho = ?";
        var fabricaDeConexoes = new ConnectionFactory();
        try (
                Connection conexao = fabricaDeConexoes.obterConexao(); PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, cartaNova.getPergunta());
            ps.setString(2, cartaNova.getResposta());
            ps.setInt(3, cartaNova.getBaralho().getIdBaralho());
            ps.setDouble(4, cartaNova.getMediaDeAcertos());
            ps.setInt(5, cartaNova.getTotalDeAcertos());
            ps.setInt(6, cartaNova.getTotalDeErros());
            ps.setInt(7, cartaNova.getIdCarta());
            ps.setInt(8, cartaNova.getBaralho().getIdBaralho());
            ps.execute();
        }
    }
}
