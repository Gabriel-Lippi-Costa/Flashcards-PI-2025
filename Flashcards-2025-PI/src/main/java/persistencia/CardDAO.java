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

    public static void excluir(Carta carta) throws Exception {
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

    public static void criarCartasAutomatica(int id) throws Exception {
        String[] comandos = {
    """
    INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos)
    VALUES ('Which word is used to form questions in the past simple?', 'Did', 
    (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Past Simple' AND id_usuario = ?), 0, 0, 0)
    """,
    """
    INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos)
    VALUES ('What is the past simple form of the verb "go"?', 'Went', 
    (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Past Simple' AND id_usuario = ?), 0, 0, 0)
    """,
    """
    INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos)
    VALUES ('How do you say "Ele não gostou da comida" in English using the past simple?', 
    'He didn\\'t like the food', 
    (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Past Simple' AND id_usuario = ?), 0, 0, 0)
    """,
    """
    INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos)
    VALUES ('Traduza para português a seguinte frase: "I wrote a letter to her".', 
    'Eu escrevi uma carta para ela', 
    (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Past Simple' AND id_usuario = ?), 0, 0, 0)
    """,
    """
    INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos)
    VALUES ('What auxiliary verb is used to make questions in the present simple (except with "to be")?', 
    'Do / Does', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Present Simple' AND id_usuario = ?), 0, 0, 0)
    """,
    """
    INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos)
    VALUES ('How do you say "Ele não gosta de café" in English using the present simple?', 
    'He doesn\\'t like coffee', 
    (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Present Simple' AND id_usuario = ?), 0, 0, 0)
    """,
    """
    INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos)
    VALUES ('Traduza para português a seguinte frase: "I work in a bank".', 
    'Eu trabalho em um banco', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Present Simple' AND id_usuario = ?), 0, 0, 0)
    """,
    """
    INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos)
    VALUES ('What is the third person singular form of the verb "go"?', 
    'Goes', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Present Simple' AND id_usuario = ?), 0, 0, 0)
    """,
    """
    INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos)
    VALUES ('What auxiliary verb is used to form the simple future in English?', 
    'Will', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Future' AND id_usuario = ?), 0, 0, 0)
    """,
    """
    INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos)
    VALUES ('How do you say "Ela viajará amanhã" in English using the simple future?', 
    'She will travel tomorrow', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Future' AND id_usuario = ?), 0, 0, 0)
    """,
    """
    INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos)
    VALUES ('What is the structure of a question in the simple future?', 
    'Will + subject + base verb', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Future' AND id_usuario = ?), 0, 0, 0)
    """,
    """
    INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos)
    VALUES ('What contraction is commonly used for "will not"?', 
    'Won\\'t', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Future' AND id_usuario = ?), 0, 0, 0)
    """,
    "INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual é o resultado de (a + b)²?', 'a² + 2ab + b²', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Produtos Notáveis' AND id_usuario = ?), 0, 0, 0);",
    "INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Como se chama a expressão (a - b)(a + b)?', 'Diferença de quadrados', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Produtos Notáveis' AND id_usuario = ?), 0, 0, 0);",
    "INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual é o produto notável de (x - 3)²?', 'x² - 6x + 9', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Produtos Notáveis' AND id_usuario = ?), 0, 0, 0);",
    "INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Dê o nome da expressão a² - b².', 'Diferença de quadrados', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Produtos Notáveis' AND id_usuario = ?), 0, 0, 0);",
    "INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Resolva: (2x + 5)²', '4x² + 20x + 25', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Produtos Notáveis' AND id_usuario = ?), 0, 0, 0);",
    "INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual é o quadrado da diferença (a - b)²?', 'a² - 2ab + b²', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Produtos Notáveis' AND id_usuario = ?), 0, 0, 0);",
    "INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual produto notável resulta em três termos?', 'Quadrado da soma ou da diferença', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Produtos Notáveis' AND id_usuario = ?), 0, 0, 0);",
    "INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual é o produto de (x + y)(x - y)?', 'x² - y²', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Produtos Notáveis' AND id_usuario = ?), 0, 0, 0);",
    "INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Transforme: x² - 2xy + y²', '(x - y)²', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Produtos Notáveis' AND id_usuario = ?), 0, 0, 0);",
    "INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Transforme: x² + 6x + 9', '(x + 3)²', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Produtos Notáveis' AND id_usuario = ?), 0, 0, 0);",
    "INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('O que define uma função?', 'Cada elemento do domínio está ligado a apenas um do contradomínio', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Funções' AND id_usuario = ?), 0, 0, 0);",
    "INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual é a fórmula geral de uma função do 1º grau?', 'f(x) = ax + b', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Funções' AND id_usuario = ?), 0, 0, 0);",
    "INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('O que representa o coeficiente ''a'' em f(x) = ax + b?', 'A inclinação da reta', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Funções' AND id_usuario = ?), 0, 0, 0);",
    "INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual é a fórmula da função quadrática?', 'f(x) = ax² + bx + c', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Funções' AND id_usuario = ?), 0, 0, 0);",
    "INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Como se chama o ponto mínimo ou máximo de uma função quadrática?', 'Vértice da parábola', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Funções' AND id_usuario = ?), 0, 0, 0);",
    "INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Quando uma função é crescente?', 'Quando a > 0', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Funções' AND id_usuario = ?), 0, 0, 0);",
    "INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual é o gráfico de uma função do 2º grau?', 'Uma parábola', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Funções' AND id_usuario = ?), 0, 0, 0);",
    "INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Em f(x) = ax² + bx + c, como se calcula o vértice?', 'x = -b/(2a)', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Funções' AND id_usuario = ?), 0, 0, 0);",
    "INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('O que a função modular faz com valores negativos?', 'Torna positivos', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Funções' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Como é o gráfico da função f(x) = |x|?', 'Um V com vértice na origem', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Funções' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual é a fórmula do volume do cubo?', 'V = a³', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Geometria Espacial' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual é a fórmula do volume do paralelepípedo?', 'V = comprimento × largura × altura', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Geometria Espacial' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual é o volume do cilindro?', 'V = πr²h', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Geometria Espacial' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Como se calcula o volume da esfera?', 'V = 4/3 πr³', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Geometria Espacial' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual é a área total de um cubo?', '6a²', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Geometria Espacial' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Quantas faces tem uma pirâmide de base quadrada?', '5 faces', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Geometria Espacial' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual é a área da base de um cone?', 'πr²', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Geometria Espacial' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Quantas arestas tem um cubo?', '12 arestas', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Geometria Espacial' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual fórmula calcula o volume de uma pirâmide?', 'V = (Ab × h)/3', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Geometria Espacial' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Como calcular a área total de um cilindro?', '2πr(h + r)', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Geometria Espacial' AND id_usuario = ?), 0, 0, 0);"
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
