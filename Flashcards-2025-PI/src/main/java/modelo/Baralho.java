/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;



/**
 *
 * @author zion
 */

public class Baralho {

    private int idBaralho;
    private String nomeBaralho;
    private String tema;
    private Usuario usuario;;
    private int totalDeErros;
    private int totalDeAcertos;
    private double mediaDeAcertos;

    public Baralho(int idBaralho, String nomeBaralho, String tema, Usuario usuario, int totalDeErros, int totalDeAcertos, double mediaDeAcertos) {
        this.idBaralho = idBaralho;
        this.nomeBaralho = nomeBaralho;
        this.tema = tema;
        this.usuario = usuario;
        this.totalDeErros = totalDeErros;
        this.totalDeAcertos = totalDeAcertos;
        this.mediaDeAcertos = mediaDeAcertos;
    }

    public int getIdBaralho() {
        return idBaralho;
    }

    public void setIdBaralho(int idBaralho) {
        this.idBaralho = idBaralho;
    }

    public String getNomeBaralho() {
        return nomeBaralho;
    }

    public void setNomeBaralho(String nomeBaralho) {
        this.nomeBaralho = nomeBaralho;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getTotalDeErros() {
        return totalDeErros;
    }

    public void setTotalDeErros(int totalDeErros) {
        this.totalDeErros = totalDeErros;
    }

    public int getTotalDeAcertos() {
        return totalDeAcertos;
    }

    public void setTotalDeAcertos(int totalDeAcertos) {
        this.totalDeAcertos = totalDeAcertos;
    }

    public double getMediaDeAcertos() {
        return mediaDeAcertos;
    }

    public void setMediaDeAcertos(double mediaDeAcertos) {
        this.mediaDeAcertos = mediaDeAcertos;
    }

}
   