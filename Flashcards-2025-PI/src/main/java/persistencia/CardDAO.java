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
"VALUES ('Como calcular a área total de um cilindro?', '2πr(h + r)', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Geometria Espacial' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Em que ano ocorreu a Proclamação da República?', '1889', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'História do Brasil' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Quem foi o primeiro imperador do Brasil?', 'Dom Pedro I', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'História do Brasil' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Quando ocorreu a independência do Brasil?', '1822', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'História do Brasil' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Quem proclamou a independência do Brasil?', 'Dom Pedro I', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'História do Brasil' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual foi a capital do Brasil antes de Brasília?', 'Rio de Janeiro', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'História do Brasil' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual foi a principal causa da guerra?', 'Assassinato do arquiduque Franz Ferdinand', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Primeira Guerra Mundial' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Quais países formavam a Tríplice Entente?', 'França, Inglaterra e Rússia', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Primeira Guerra Mundial' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('O que foi a “Guerra de Trincheiras”?', 'Conflito estático com soldados em valas', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Primeira Guerra Mundial' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Quando a guerra começou?', '1914', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Primeira Guerra Mundial' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Quando terminou a guerra?', '1918', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Primeira Guerra Mundial' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual era a principal forma de governo em Atenas clássica?', 'Democracia direta', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Antiguidades Clássicas' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Quem foi Sócrates?', 'Filósofo ateniense que usava o método da maiêutica', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Antiguidades Clássicas' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual povo fundou Roma segundo a lenda?', 'Os irmãos Rômulo e Remo', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Antiguidades Clássicas' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('O que foi a Guerra do Peloponeso?', 'Conflito entre Atenas e Esparta no séc. V a.C.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Antiguidades Clássicas' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual era a base econômica de Roma Antiga?', 'Agricultura e trabalho escravo', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Antiguidades Clássicas' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('O que é calor específico?', 'Quantidade de calor para aquecer 1g da substância em 1°C.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Físico-Química' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual é a fórmula da dilatação linear?', 'ΔL = L₀·α·ΔT', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Físico-Química' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('O que representa a constante de Avogadro?', 'Número de partículas em 1 mol: 6,02×10²³.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Físico-Química' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual é a unidade da pressão no SI?', 'Pascal (Pa)', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Físico-Química' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('O que é entalpia?', 'Quantidade de energia de um sistema em forma de calor.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Físico-Química' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('O que são hidrocarbonetos?', 'Compostos formados só por carbono e hidrogênio.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Química Orgânica' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual a fórmula geral dos alcanos?', 'CₙH₂ₙ₊₂', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Química Orgânica' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('O que caracteriza os álcoois?', 'Presença do grupo -OH.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Química Orgânica' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual é a função do grupo carboxila?', 'Ácidos carboxílicos.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Química Orgânica' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('O que é isomeria?', 'Moléculas com mesma fórmula molecular, mas diferentes estruturas.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Química Orgânica' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('O que é número atômico?', 'Número de prótons no núcleo.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Atomística' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual partícula tem carga negativa?', 'Elétron.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Atomística' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('O que define o isótopo de um elemento?', 'Mesmo número atômico, diferente número de massa.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Atomística' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual partícula é neutra?', 'Nêutron.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Atomística' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual modelo atômico propôs elétrons em órbitas circulares?', 'Modelo de Bohr.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Atomística' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('O que é uma bacia hidrográfica?', 'Área de terra drenada por um rio e seus afluentes.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Geografia Física' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual é o principal agente do relevo?', 'Agentes externos como água e vento.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Geografia Física' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('O que é um ecossistema?', 'Conjunto de seres vivos e ambiente que interagem.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Geografia Física' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual é a função das correntes marítimas?', 'Regular temperatura e transportar nutrientes.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Geografia Física' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('O que caracteriza um clima tropical?', 'Verões quentes e chuvosos, invernos secos.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Geografia Física' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n"+ 
"VALUES ('O que é um Estado-nação?', 'Território com governo próprio e população definida.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Geografia Política' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual a função das fronteiras políticas?', 'Delimitar territórios entre países.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Geografia Política' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('O que é soberania?', 'Autoridade suprema de um Estado sobre seu território.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Geografia Política' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('O que define uma região geopolítica?', 'Área delimitada por fatores políticos e estratégicos.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Geografia Política' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('O que são blocos econômicos?', 'Grupos de países que promovem integração econômica.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Geografia Política' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('O que é desmatamento?', 'Remoção da vegetação natural.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Problemas Ambientais' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual é a principal causa do efeito estufa?', 'Aumento dos gases como CO₂ na atmosfera.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Problemas Ambientais' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('O que causa a poluição hídrica?', 'Descarte inadequado de resíduos em rios e lagos.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Problemas Ambientais' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('O que é a camada de ozônio?', 'Camada que protege a Terra da radiação ultravioleta.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Problemas Ambientais' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual a consequência do aquecimento global?', 'Mudanças climáticas e aumento do nível do mar.', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Problemas Ambientais' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES('Qual cor de luz desvia mais ao passar por um prisma de vidro?', 'A luz violeta, por ter menor comprimento de onda', (SELECT id_baralho from tb_baralhos WHERE nome_baralho = 'Óptica' AND id_usuario = ?), 0,0,0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES('Na reflexão regular, o que acontece com os raios refletidos?', 'Saem paralelos entre si', (SELECT id_baralho from tb_baralhos WHERE nome_baralho = 'Óptica' AND id_usuario = ?), 0,0,0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES('Como é chamado o fenômeno da decomposição da luz branca em várias cores?', 'Dispersão', (SELECT id_baralho from tb_baralhos WHERE nome_baralho = 'Óptica' AND id_usuario = ?), 0,0,0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES('Em qual tipo de espelho a imagem sempre é virtual, direita e menor que o objeto?', 'Convexo', (SELECT id_baralho from tb_baralhos WHERE nome_baralho = 'Óptica' AND id_usuario = ?), 0,0,0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES('Lei correspondente à seguinte frase: \"Um corpo em repouso permanecerá em repouso, a menos que uma força resultante atue sobre ele\".', 'Primeira Lei de Newton', (SELECT id_baralho from tb_baralhos WHERE nome_baralho = 'Mecânica' AND id_usuario = ?), 0,0,0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES('Um carro percorre 180 km em 2 horas. Qual é sua velocidade média em m/s?', '25 m/s', (SELECT id_baralho from tb_baralhos WHERE nome_baralho = 'Mecânica' AND id_usuario = ?), 0,0,0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES('Um corpo de 5 kg é elevado a uma altura de 10 metros. Qual é sua energia potencial gravitacional? (Considere g = 10 m/s²)', '500 J', (SELECT id_baralho from tb_baralhos WHERE nome_baralho = 'Mecânica' AND id_usuario = ?), 0,0,0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES('Uma força de 20 N é aplicada a um bloco de 4 kg. Qual é a aceleração adquirida pelo bloco?', '5 m/s²', (SELECT id_baralho from tb_baralhos WHERE nome_baralho = 'Mecânica' AND id_usuario = ?), 0,0,0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES('Misturam-se 200 g de água a 80°C com 100 g de água a 20°C. Qual será a temperatura final da mistura? (Água tem calor específico c = 1 cal/g°C)', '60°C', (SELECT id_baralho from tb_baralhos WHERE nome_baralho = 'Termologia' AND id_usuario = ?), 0,0,0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES('Qual a quantidade de calor necessária para transformar 100 g de gelo a 0°C em água a 0°C? (Calor latente de fusão da água: Lf = 80 cal/g)', '8.000 cal', (SELECT id_baralho from tb_baralhos WHERE nome_baralho = 'Termologia' AND id_usuario = ?), 0,0,0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES('Sabendo que o calor específico do ferro é c = 0,46 cal/g°C, qual a quantidade de calor recebida?', '46.000 cal', (SELECT id_baralho from tb_baralhos WHERE nome_baralho = 'Termologia' AND id_usuario = ?), 0,0,0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES('Quando dois corpos com temperaturas diferentes entram em contato e trocam calor até igualarem suas temperaturas, dizemos que atingiram:', 'Equilíbrio térmico', (SELECT id_baralho from tb_baralhos WHERE nome_baralho = 'Termologia' AND id_usuario = ?), 0,0,0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Em qual grupo de plantas ocorre a dupla fecundação?', 'Angiospermas', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Botânica' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual estrutura responsável pelo transporte de seiva bruta nas plantas?', 'Xilema', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Botânica' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual é o nome do gameta masculino flagelado encontrado em briófitas e pteridófitas?', 'Anterozoide', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Botânica' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual é a principal função das folhas nas plantas?', 'Fotossíntese', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Botânica' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Se um heterozigoto Bb (olhos castanhos) casa com bb (olhos azuis), qual a proporção esperada na prole?', '50% castanhos e 50% azuis', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Genética' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual é a probabilidade de um casal heterozigoto Aa x Aa ter um filho homozigoto recessivo (aa)?', '25%', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Genética' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('O que é fenótipo?', 'Características físicas expressas pelo genótipo', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Genética' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual é a unidade básica da hereditariedade?', 'Gene', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Genética' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual o termo para a área onde um organismo vive?', 'Habitat', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Ecologia' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Como são chamados os organismos que produzem seu próprio alimento via fotossíntese?', 'Autótrofos (produtores)', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Ecologia' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Como são chamados os fatores não vivos que influenciam um ecossistema?', 'Fatores abióticos', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Ecologia' AND id_usuario = ?), 0, 0, 0);",
"INSERT INTO tb_cards (pergunta, resposta, id_baralho, total_de_acertos, total_de_erros, media_de_acertos) \n" +
"VALUES ('Qual o nome do modelo que representa níveis tróficos de produtores a consumidores?', 'Pirâmide de energia', (SELECT id_baralho FROM tb_baralhos WHERE nome_baralho = 'Ecologia' AND id_usuario = ?), 0, 0, 0);"

                
                
               
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
