/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author zion
 */
public class Carta {

    private int idCarta;
    private String pergunta;
    private String resposta;
    private Baralho baralho;
    private double mediaDeAcertos;
    private int totalDeAcertos;
    private int totalDeErros;
    private boolean respondida = false;
    private boolean acertou = false;

    public Carta(int idCarta, String pergunta, String resposta, Baralho baralho, double mediaDeAcertos, int totalDeAcertos, int totalDeErros) {
        this.idCarta = idCarta;
        this.pergunta = pergunta;
        this.resposta = resposta;
        this.baralho = baralho;
        this.mediaDeAcertos = mediaDeAcertos;
        this.totalDeAcertos = totalDeAcertos;
        this.totalDeErros = totalDeErros;
    }

    public int getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(int idCarta) {
        this.idCarta = idCarta;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Baralho getBaralho() {
        return baralho;
    }

    public void setBaralho(Baralho baralho) {
        this.baralho = baralho;
    }

    public double getMediaDeAcertos() {
        return mediaDeAcertos;
    }

    public void setMediaDeAcertos(double mediaDeAcertos) {
        this.mediaDeAcertos = mediaDeAcertos;
    }

    public int getTotalDeAcertos() {
        return totalDeAcertos;
    }

    public void setTotalDeAcertos(int totalDeAcertos) {
        this.totalDeAcertos = totalDeAcertos;
    }

    public int getTotalDeErros() {
        return totalDeErros;
    }

    public void setTotalDeErros(int totalDeErros) {
        this.totalDeErros = totalDeErros;
    }

    public boolean isRespondida() {
        return respondida;
    }

    public void setRespondida(boolean respondida) {
        this.respondida = respondida;
    }

    public boolean isAcertou() {
        return acertou;
    }

    public void setAcertou(boolean acertou) {
        this.acertou = acertou;
    }
    
}