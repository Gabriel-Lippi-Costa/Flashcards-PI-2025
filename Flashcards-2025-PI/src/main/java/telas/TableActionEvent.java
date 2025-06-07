/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package telas;

/**
 *
 * @author zion
 */
public interface TableActionEvent {
    public void editarCarta(int row);
    public void deletarCarta(int row);
    public void deletarBaralho(int row);
    public void verBaralho(int row);
    public void verAlunos(int row);
    public void verBaralhos (int row);
    public void jogar(int row);
    public void verCards(int row);
}
