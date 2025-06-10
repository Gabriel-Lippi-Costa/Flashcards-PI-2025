package telas;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import java.awt.BorderLayout;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.File;
import modelo.Baralho;
import modelo.Carta;
import modelo.Usuario;
import persistencia.BaralhoDAO;
import static persistencia.BaralhoDAO.listarBaralhos;
import persistencia.CardDAO;
import persistencia.UsuarioDAO;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.Grupo;
import persistencia.GrupoDAO;
import javax.swing.RowSorter;
import javax.swing.SwingUtilities;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/**
 *
 * @author zion
 */
public class CriarContaTela extends javax.swing.JFrame {

    /**
     * Creates new form CriarContaTela
     */
    public CriarContaTela() {
        this.setUndecorated(true);
        initComponents();
        var columnModel = minhasCartasTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(400);
        columnModel.getColumn(0).setMinWidth(50);
        columnModel.getColumn(0).setMaxWidth(400);
        columnModel.getColumn(1).setPreferredWidth(225);
        columnModel.getColumn(1).setMinWidth(50);
        columnModel.getColumn(1).setMaxWidth(225);
        columnModel.getColumn(4).setPreferredWidth(75);
        columnModel.getColumn(4).setMinWidth(50);
        columnModel.getColumn(4).setMaxWidth(75);
        columnModel = cartasDoGrupoTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(400);
        columnModel.getColumn(0).setMinWidth(50);
        columnModel.getColumn(0).setMaxWidth(400);
        columnModel.getColumn(1).setPreferredWidth(225);
        columnModel.getColumn(1).setMinWidth(50);
        columnModel.getColumn(1).setMaxWidth(225);
        for (int i = 0; i < minhasCartasTable.getColumnCount(); i++) {
            minhasCartasTable.getColumnModel().getColumn(i).setCellRenderer(new AlternarCorLinhasRenderer());
        }
        for (int i = 0; i < meusBaralhosTable.getColumnCount(); i++) {
            meusBaralhosTable.getColumnModel().getColumn(i).setCellRenderer(new AlternarCorLinhasRenderer());
        }
        for (int i = 0; i < meusGruposTable.getColumnCount(); i++) {
            meusGruposTable.getColumnModel().getColumn(i).setCellRenderer(new AlternarCorLinhasRenderer());
        }
        for (int i = 0; i < baralhosDoGrupoTable.getColumnCount(); i++) {
            baralhosDoGrupoTable.getColumnModel().getColumn(i).setCellRenderer(new AlternarCorLinhasRenderer());
        }
        for (int i = 0; i < cartasDoGrupoTable.getColumnCount(); i++) {
            cartasDoGrupoTable.getColumnModel().getColumn(i).setCellRenderer(new AlternarCorLinhasRenderer());
        }
        for (int i = 0; i < alunosDoGrupoTable.getColumnCount(); i++) {
            alunosDoGrupoTable.getColumnModel().getColumn(i).setCellRenderer(new AlternarCorLinhasRenderer());
        }
        for (int i = 0; i < selecionarBaralhoJogarTable.getColumnCount(); i++) {
            selecionarBaralhoJogarTable.getColumnModel().getColumn(i).setCellRenderer(new AlternarCorLinhasRenderer());
        }
        carregarMusica(caminhoMusica);
        tocarMusica();
        excluirAlunoBotao.setVisible(false);
        editarUsuarioButton.setContentAreaFilled(false);
        redirecionarTelaCriarContaButton.setContentAreaFilled(false);
        jaTenhoContaButton.setContentAreaFilled(false);
        alunosDoGrupoTable.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        baralhosDoGrupoTable.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        selecionarBaralhoJogarTable.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        meusBaralhosTable.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        minhasCartasTable.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        cartasDoGrupoTable.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        meusGruposTable.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        JScrollPane scrollPane = (JScrollPane) meusBaralhosTable.getParent().getParent();
        scrollPane.getViewport().setBackground(new Color(246, 231, 211));
        scrollPane.setBorder(null);
        JViewport headerViewport = scrollPane.getColumnHeader();
        headerViewport.setBackground(new Color(246, 231, 211));
        scrollPane = (JScrollPane) minhasCartasTable.getParent().getParent();
        scrollPane.getViewport().setBackground(new Color(246, 231, 211));
        scrollPane.setBorder(null);
        headerViewport = scrollPane.getColumnHeader();
        headerViewport.setBackground(new Color(246, 231, 211));
        scrollPane = (JScrollPane) selecionarBaralhoJogarTable.getParent().getParent();
        scrollPane.getViewport().setBackground(new Color(246, 231, 211));
        scrollPane.setBorder(null);
        headerViewport = scrollPane.getColumnHeader();
        headerViewport.setBackground(new Color(246, 231, 211));
        scrollPane = (JScrollPane) meusGruposTable.getParent().getParent();
        scrollPane.getViewport().setBackground(new Color(246, 231, 211));
        scrollPane.setBorder(null);
        headerViewport = scrollPane.getColumnHeader();
        headerViewport.setBackground(new Color(246, 231, 211));
        scrollPane = (JScrollPane) baralhosDoGrupoTable.getParent().getParent();
        scrollPane.getViewport().setBackground(new Color(246, 231, 211));
        scrollPane.setBorder(null);
        headerViewport = scrollPane.getColumnHeader();
        headerViewport.setBackground(new Color(246, 231, 211));
        scrollPane = (JScrollPane) alunosDoGrupoTable.getParent().getParent();
        scrollPane.getViewport().setBackground(new Color(246, 231, 211));
        scrollPane.setBorder(null);
        headerViewport = scrollPane.getColumnHeader();
        headerViewport.setBackground(new Color(246, 231, 211));
        scrollPane = (JScrollPane) cartasDoGrupoTable.getParent().getParent();
        scrollPane.getViewport().setBackground(new Color(246, 231, 211));
        scrollPane.setBorder(null);
        headerViewport = scrollPane.getColumnHeader();
        headerViewport.setBackground(new Color(246, 231, 211));
        baralhosDoGrupoPainel.setVisible(false);
        criarCardsPanel.setVisible(false);
        criarContaPanel.setVisible(false);
        criarBaralhoGrupoPainel.setVisible(false);
        adicionarAlunoGrupoPainel.setVisible(false);
        painelInicial.setVisible(false);
        alunosDoGrupoPainel.setVisible(false);
        confirmarExcluirBaralhoGrupoPainel.setVisible(false);
        editarBaralhoPainel.setVisible(false);
        painelJogoFinalizado.setVisible(false);
        painelJogoNormal.setVisible(false);
        adicionarGrupoPainel.setVisible(false);
        confirmarSairJogoPainel.setVisible(false);
        editarCartasGrupoPainel.setVisible(false);
        confirmarExcluirCartaPainel.setVisible(false);
        meusBaralhosPainel.setVisible(false);
        meusCardsPanel.setVisible(false);
        selecionarModoDeJogoPainel.setVisible(false);
        meusGruposPainel.setVisible(false);
        editarBaralhoGrupoPainel.setVisible(false);
        confirmarExcluirGrupoPainel.setVisible(false);
        adicionarBaralhosPainel.setVisible(false);
        editarCartasPainel.setVisible(false);
        editarUsuarioButton.setVisible(false);
        excluirContaPainel.setVisible(false);
        confirmarExcluirUsuarioPainel.setVisible(false);
        editarGrupoPainel.setVisible(false);
        cartasDoGrupoPainel.setVisible(false);
        painelJogoInserir.setVisible(false);
        painelJogoInserir1.setVisible(false);
        confirmarExcluirBaralhoPainel.setVisible(false);
        selecionarBaralhoJogarPainel.setVisible(false);
        importarBaralhoPainel.setVisible(false);
        jogoBaralhosDoGrupoPainel.setVisible(false);
        painelJogoFinalizado1.setVisible(false);
        selecionarModoDeJogoGrupoPainel.setVisible(false);
        criarCardsGrupoPanel.setVisible(false);
        editarUsuarioPainel.setVisible(false);
        editarSenhaPainel.setVisible(false);
        editarNomeUsuarioPainel.setVisible(false);
        editarEmailUsuarioPainel.setVisible(false);
        confirmarSairPainel.setVisible(false);
        confirmarExcluirAlunoGrupoPainel.setVisible(false);
        confirmarExcluirCartaGrupoPainel.setVisible(false);
        confirmarExcluirContaPainel.setVisible(false);
        meusGruposTable.getColumnModel().getColumn(2).setCellRenderer(new TableActionCellRendererGrupos());
        baralhosDoGrupoTable.getColumnModel().getColumn(2).setCellRenderer(new TableActionCellRendererBaralhosDoGrupo());
        meusBaralhosTable.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRenderer());
        minhasCartasTable.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRendererCartas());
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void verCards(int linha) {
                mensagemBaralhoDoGrupoSemCartas.setText("");
                DefaultTableModel modelo = (DefaultTableModel) baralhosDoGrupoTable.getModel();
                int modelRow = baralhosDoGrupoTable.convertRowIndexToModel(linha);
                String nomeBaralho = (String) modelo.getValueAt(modelRow, 0);
                System.out.println(nomeBaralho);
                for (f = 0; f < listaDeBaralhosGrupo.size(); f++) {
                    System.out.println(f);
                    if (listaDeBaralhosGrupo.get(f).getNomeBaralho().equals(nomeBaralho)) {
                        System.out.println(listaDeBaralhosGrupo.get(f).getNomeBaralho());
                        break;
                    }
                }
                try {
                    nomeDoBaralhoGrupoLabel.setText(listaDeBaralhosGrupo.get(f).getNomeBaralho());
                    int codigo = listaDeBaralhosGrupo.get(f).getIdBaralho();
                    codigoDoBaralhoGrupoLabel.setText(String.format("Código do baralho: %d", gerarY(codigo)));
                    listaDeCartas = CardDAO.listarCartas(listaDeBaralhosGrupo.get(f));
                    if (listaDeCartas.isEmpty()) {
                        cartasDoGrupoLabel.setText("Esse baralho ainda não tem cards");
                        cartasDoGrupoTable.getTableHeader().setVisible(false);
                    }
                    dtmCartasDoGrupo = (DefaultTableModel) cartasDoGrupoTable.getModel();
                    dtmCartasDoGrupo.setRowCount(0);
                    for (Carta carta : listaDeCartas) {
                        Object[] row = {
                            carta.getPergunta(),
                            carta.getResposta()
                        };
                        dtmCartasDoGrupo.addRow(row);
                    }
                    ordenar = new TableRowSorter<>(dtmCartasDoGrupo);
                    cartasDoGrupoTable.setRowSorter(ordenar);
                    ArrayList<RowSorter.SortKey> chaveOrdenada = new ArrayList<>();
                    chaveOrdenada.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
                    ordenar.setSortKeys(chaveOrdenada);
                    ordenar.sort();
                    cartasDoGrupoPainel.setVisible(true);
                    baralhosDoGrupoPainel.setVisible(false);
                    if (usuario.getTipoUsuario().equals("aluno")) {
                        criarCartasGrupoButton.setVisible(false);
                        irEditarCartasDoGrupoButton.setVisible(false);
                        excluirCardButton.setVisible(false);
                    } else {
                        criarCartasGrupoButton.setVisible(true);
                        irEditarCartasDoGrupoButton.setVisible(true);
                        excluirCardButton.setVisible(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("erro");
                }
            }

            @Override
            public void jogar(int linha) {
                DefaultTableModel modelo = (DefaultTableModel) baralhosDoGrupoTable.getModel();
                int modelRow = baralhosDoGrupoTable.convertRowIndexToModel(linha);
                String nomeBaralho = (String) modelo.getValueAt(modelRow, 0);
                for (f = 0; f < listaDeBaralhosGrupo.size(); f++) {
                    if (listaDeBaralhosGrupo.get(f).getNomeBaralho().equals(nomeBaralho)) {
                        break;
                    }
                }
                try {
                    listaDeCartas = CardDAO.listarCartas(listaDeBaralhosGrupo.get(f));
                    if (listaDeCartas.isEmpty()) {
                        mensagemBaralhoDoGrupoSemCartas.setText("Esse baralho ainda não tem cards");
                    } else {
                        Collections.shuffle(listaDeCartas);
                        mensagemBaralhoDoGrupoSemCartas.setText("");
                        selecionarModoDeJogoGrupoPainel.setVisible(true);
                        baralhosDoGrupoPainel.setVisible(false);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("erro");
                }
            }

            @Override
            public void deletarBaralho(int linha) {
                int modelRow = meusBaralhosTable.convertRowIndexToModel(linha);
                row = modelRow;
                if (meusBaralhosTable.isEditing()) {
                    meusBaralhosTable.getCellEditor().stopCellEditing();
                }
                dtmBaralhos = (DefaultTableModel) meusBaralhosTable.getModel();
                nomeBaralho = (String) dtmBaralhos.getValueAt(modelRow, 0);
                confirmarExcluirBaralhoPainel.setVisible(true);
                meusBaralhosPainel.setVisible(false);
            }

            @Override
            public void verBaralho(int row) {
                DefaultTableModel modelo = (DefaultTableModel) meusBaralhosTable.getModel();
                int modelRow = meusBaralhosTable.convertRowIndexToModel(row);
                String nomeBaralho = (String) modelo.getValueAt(modelRow, 0);
                for (i = 0; i < listaDeBaralhos.size(); i++) {
                    if (listaDeBaralhos.get(i).getNomeBaralho().equals(nomeBaralho)) {
                        break;
                    }
                }
                try {
                    nomeDoBaralhoLabel.setText(listaDeBaralhos.get(i).getNomeBaralho());
                    int codigo = listaDeBaralhos.get(i).getIdBaralho();
                    codigoDoBaralhoLabel.setText(String.format("Código do baralho: %d", gerarY(codigo)));
                    listaDeCartas = CardDAO.listarCartas(listaDeBaralhos.get(i));
                    if (listaDeCartas.isEmpty()) {
                        meusCardsLabel.setText("Esse baralho ainda não tem cards");
                        minhasCartasTable.getTableHeader().setVisible(false);
                    }
                    dtmCartas = (DefaultTableModel) minhasCartasTable.getModel();
                    dtmCartas.setRowCount(0);
                    for (Carta carta : listaDeCartas) {
                        Object[] linha = {
                            carta.getPergunta(),
                            carta.getResposta(), String.format("%.2f%%", carta.getMediaDeAcertos()), carta.getTotalDeAcertos() + carta.getTotalDeErros()};
                        dtmCartas.addRow(linha);
                    }
                    ordenar = new TableRowSorter<>(dtmCartas);
                    minhasCartasTable.setRowSorter(ordenar);
                    ArrayList<RowSorter.SortKey> chaveOrdenada = new ArrayList<>();
                    chaveOrdenada.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
                    ordenar.setSortKeys(chaveOrdenada);
                    ordenar.sort();
                    meusCardsPanel.setVisible(true);
                    meusBaralhosPainel.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("erro");
                }
            }

            @Override
            public void deletarCarta(int linha) {
                if (minhasCartasTable.isEditing()) {
                    minhasCartasTable.getCellEditor().stopCellEditing();
                }
                dtmCartas = (DefaultTableModel) minhasCartasTable.getModel();
                linhaParaExcluirCarta = minhasCartasTable.convertRowIndexToModel(linha);
                String pergunta = (String) dtmCartas.getValueAt(linhaParaExcluirCarta, 0);
                for (Carta carta : listaDeCartas) {
                    if (carta.getPergunta().equals(pergunta)) {
                        cartaParaExcluir = carta;
                        break;
                    }
                }
                confirmarExcluirCartaPainel.setVisible(true);
                meusCardsPanel.setVisible(false);
            }

            @Override
            public void editarCarta(int linha) {
                System.out.println("caiu nesse caso");
                dtmCartas = (DefaultTableModel) minhasCartasTable.getModel();
                int modelRow = minhasCartasTable.convertRowIndexToModel(linha);
                String pergunta = (String) dtmCartas.getValueAt(modelRow, 0);
                for (j = 0; j < listaDeCartas.size(); j++) {
                    System.out.println("caiu nesse caso");
                    if (listaDeCartas.get(j).getPergunta().equals(pergunta)) {
                        editarPerguntaCaixaDeTexto.setText(listaDeCartas.get(j).getPergunta());
                        editarRespostaCaixaDeTexto.setText(listaDeCartas.get(j).getResposta());
                        meusCardsPanel.setVisible(false);
                        editarCartasPainel.setVisible(true);
                        break;
                    }
                }
            }

            @Override
            public void verAlunos(int linha) {
                DefaultTableModel modelo = (DefaultTableModel) meusGruposTable.getModel();
                int modelRow = meusGruposTable.convertRowIndexToModel(linha);
                String nomeGrupo = (String) modelo.getValueAt(modelRow, 0);
                for (d = 0; d < listaDeGrupos.size(); d++) {
                    if (listaDeGrupos.get(d).getNomeGrupo().equals(nomeGrupo)) {
                        break;
                    }
                }
                try {
                    nomeDoGrupoLabel1.setText(listaDeGrupos.get(d).getNomeGrupo());
                    listaDeAlunosGrupo = GrupoDAO.listarAlunosDoGrupo(listaDeGrupos.get(d));
                    if (listaDeAlunosGrupo.isEmpty()) {
                        alunosDoGrupoLabel.setText("Esse grupo ainda não tem alunos");
                        alunosDoGrupoTable.getTableHeader().setVisible(false);
                    }
                    dtmAlunosDoGrupo = (DefaultTableModel) alunosDoGrupoTable.getModel();
                    dtmAlunosDoGrupo.setRowCount(0);
                    for (Usuario aluno : listaDeAlunosGrupo) {
                        Object[] row = {
                            aluno.getNomeUsuario(),
                            aluno.getEmail()};
                        dtmAlunosDoGrupo.addRow(row);
                    }
                    ordenar = new TableRowSorter<>(dtmAlunosDoGrupo);
                    alunosDoGrupoTable.setRowSorter(ordenar);
                    ArrayList<RowSorter.SortKey> chaveOrdenada = new ArrayList<>();
                    chaveOrdenada.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
                    ordenar.setSortKeys(chaveOrdenada);
                    ordenar.sort();
                    alunosDoGrupoPainel.setVisible(true);
                    meusGruposPainel.setVisible(false);
                    if (usuario.getTipoUsuario().equals("aluno")) {
                        irExcluirAlunosGruposButton.setVisible(false);
                        AdicionarAlunoButton.setVisible(false);
                    } else {
                        irExcluirAlunosGruposButton.setVisible(true);
                        AdicionarAlunoButton.setVisible(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("erro");
                }
            }

            @Override
            public void verBaralhos(int linha) {
                DefaultTableModel modelo = (DefaultTableModel) meusGruposTable.getModel();
                int modelRow = meusGruposTable.convertRowIndexToModel(linha);
                String nomeGrupo = (String) modelo.getValueAt(modelRow, 0);
                for (d = 0; d < listaDeGrupos.size(); d++) {
                    if (listaDeGrupos.get(d).getNomeGrupo().equals(nomeGrupo)) {
                        break;
                    }
                }
                try {
                    nomeDoGrupoLabel.setText(listaDeGrupos.get(d).getNomeGrupo());
                    listaDeBaralhosGrupo = GrupoDAO.listarBaralhosDoGrupo(listaDeGrupos.get(d));
                    if (listaDeBaralhosGrupo.isEmpty()) {
                        baralhosDoGrupoLabel.setText("Esse grupo ainda não tem baralhos");
                        baralhosDoGrupoTable.getTableHeader().setVisible(false);
                    }
                    dtmBaralhosDoGrupo = (DefaultTableModel) baralhosDoGrupoTable.getModel();
                    dtmBaralhosDoGrupo.setRowCount(0);
                    for (Baralho baralho : listaDeBaralhosGrupo) {
                        Object[] row = {
                            baralho.getNomeBaralho(),
                            baralho.getTema()};
                        dtmBaralhosDoGrupo.addRow(row);
                    }
                    ordenar = new TableRowSorter<>(dtmBaralhosDoGrupo);
                    baralhosDoGrupoTable.setRowSorter(ordenar);
                    ArrayList<RowSorter.SortKey> chaveOrdenada = new ArrayList<>();
                    chaveOrdenada.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
                    ordenar.setSortKeys(chaveOrdenada);
                    ordenar.sort();
                    baralhosDoGrupoPainel.setVisible(true);

                    if (usuario.getTipoUsuario().equals("aluno")) {
                        excluirBaralhoGrupoButton.setVisible(false);
                        editarBaralhoGrupoButton.setVisible(false);
                        criarBaralhoGrupoButton.setVisible(false);
                    } else {
                        excluirBaralhoGrupoButton.setVisible(true);
                        editarBaralhoGrupoButton.setVisible(true);
                        criarBaralhoGrupoButton.setVisible(true);
                    }
                    meusGruposPainel.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("erro");
                }
            }
        };

        meusBaralhosTable.getColumnModel()
                .getColumn(4).setCellEditor(new TableActionCellEditor(event));
        meusGruposTable.getColumnModel()
                .getColumn(2).setCellEditor(new TableActionCellEditorGrupos(event));
        baralhosDoGrupoTable.getColumnModel().getColumn(2).setCellEditor(new TableActionCellEditorBaralhosDoGrupo(event));
        minhasCartasTable.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditorCartas(event));
        this.setLocationRelativeTo(null);
    }
    Clip musica;
    boolean musicaTocando = false;
    ImageIcon imagemSomAtivado = new ImageIcon("src/resources/imagens/som ativado.png");
    ImageIcon imagemSomDesativado = new ImageIcon("src/resources/imagens/som desativado.png");
    Usuario usuario;
    Baralho baralho;
    UsuarioDAO usuarioDAO;
    TableRowSorter<DefaultTableModel> ordenar;
    DefaultTableModel dtmBaralhos;
    DefaultTableModel dtmAlunosDoGrupo;
    DefaultTableModel dtmJogar;
    JPanel ultimoPainel;
    DefaultTableModel dtmCartas;
    DefaultTableModel dtmBaralhosDoGrupo;
    DefaultTableModel dtmGrupos;
    DefaultTableModel dtmCartasDoGrupo;
    ArrayList<Baralho> listaDeBaralhos;
    ArrayList<Usuario> listaDeAlunosGrupo;
    ArrayList<Grupo> listaDeGrupos;
    ArrayList<Carta> listaDeCartas;
    ArrayList<Baralho> listaDeBaralhosGrupo;
    String nomeBaralho;
    Carta cartaJogo;
    Grupo grupoParaExcluir;
    Carta cartaParaExcluir;
    String caminhoMusica = "src/resources/audios/videoplayback.wav";
    final int[] indice = {0};
    int linhaSelecionada;
    int linhaParaExcluirCarta;
    int linhaAlunoParaExcluir;
    Usuario usuarioParaExcluir;
    Usuario alunoParaExcluir;
    int j;
    int a;
    int i;
    int c;
    int d;
    int acertosJogo;
    int errosJogo;
    int e;
    int f;
    int g;
    static final int angular = 65537;
    static final int base = 12345;
    static final int modulo = 100000000;
    int linhaSelecionadaExcluirBaralhoGrupo;
    Baralho baralhoParaExcluirGrupo;
    int linhaSelecionadaExcluirCartaGrupo;
    Carta cartaParaExcluirGrupo;
    int row;

    public static int inversoModular(int angular, int modulo) {
        int m0 = modulo, t, q;
        int x0 = 0, x1 = 1;
        if (modulo == 1) {
            return 0;
        }

        while (angular > 1) {
            q = angular / modulo;
            t = modulo;

            modulo = angular % modulo;
            angular = t;
            t = x0;

            x0 = x1 - q * x0;
            x1 = t;
        }

        if (x1 < 0) {
            x1 += m0;
        }

        return x1;
    }

    public static int gerarY(int x) {
        return (int) (((long) angular * x + base) % modulo);
    }

    public static int recuperarX(int y) {
        int angularInv = inversoModular(angular, modulo);
        return (int) ((long) angularInv * (y - base + modulo) % modulo);
    }

    private void tocarMusica() {
        if (musica != null && !musica.isRunning()) {
            musica.setFramePosition(0); // Reinicia do início
            musica.start();
            musica.loop(Clip.LOOP_CONTINUOUSLY); // Opcional: Loop contínuo
            musicaTocando = true;
        }
    }

    private void pararMusica() {
        if (musica != null && musica.isRunning()) {
            musica.stop();
            musicaTocando = false;
        }
    }

    private void carregarMusica(String caminhoArquivo) {
        try {
            File arquivoMusica = new File(caminhoArquivo);
            AudioInputStream audio = AudioSystem.getAudioInputStream(arquivoMusica);
            musica = AudioSystem.getClip();
            musica.open(audio);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String validarDados(String email, String senha) {
        if (senha.isEmpty() || email.isEmpty()) {
            return "erro";
        }
        if (email.endsWith("@sistemapoliedro.com")) {
            return "professor";
        }
        int arroba = email.indexOf('@');
        int ponto = email.lastIndexOf('.');
        if (arroba > 0 && ponto > arroba) {
            return "aluno";
        }
        return "email inválido";
    }

    public String validarEmail(String email) {
        int arroba = email.indexOf('@');
        int ponto = email.lastIndexOf('.');
        if (arroba > 0 && ponto > arroba) {
            return "ok";
        }
        return "email inválido";
    }

    public int cadastrarUser(String email, String senha, String nomeUsuario, String validar) throws Exception {
        usuario = new Usuario(0, email, nomeUsuario, senha, validar);
        var usuarioDAO = new UsuarioDAO();
        int id = usuarioDAO.cadastrar(usuario);
        return id;
    }

    public static Usuario verificarUser(String email, String senha) throws Exception {
        Usuario verificacao = new Usuario(0, email, "nomeTeste", senha, "valorX");
        var usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.autenticar(verificacao);
        return usuario;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel32 = new javax.swing.JLabel();
        editarBaralhoGrupoPainel = new javax.swing.JPanel();
        confirmarEditarBaralhoGrupoButton = new javax.swing.JButton();
        editarNomeBaralhoGrupoCaixaDeTexto = new javax.swing.JTextField();
        editarTemaBaralhoGrupoCaixaDeTexto = new javax.swing.JTextField();
        voltarBaralhosGrupoButton = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        mensagemEditarBaralhoGrupoInvalido = new javax.swing.JLabel();
        confirmarExcluirBaralhoPainel = new javax.swing.JPanel();
        mensagemDesejaExcluirBaralho = new javax.swing.JLabel();
        confirmarExcluirBaralhoButton = new javax.swing.JButton();
        voltarMeusBaralhosButton2 = new javax.swing.JButton();
        editarBaralhoPainel = new javax.swing.JPanel();
        confirmarEditarBaralhoButton = new javax.swing.JButton();
        editarNomeBaralhoCaixaDeTexto = new javax.swing.JTextField();
        editarTemaCaixaDeTexto = new javax.swing.JTextField();
        voltarMeusCardsButton3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        mensagemEditarBaralhoInvalido = new javax.swing.JLabel();
        meusBaralhosPainel = new javax.swing.JPanel();
        irParaCriarBaralhoButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        meusBaralhosTable = new javax.swing.JTable();
        meusBaralhosLabel = new javax.swing.JLabel();
        voltarPainelInicial1Button = new javax.swing.JButton();
        painelJogoFinalizado = new javax.swing.JPanel();
        estatisticasJogoLabel = new javax.swing.JLabel();
        terminarJogoVoltarPainelButton = new javax.swing.JButton();
        autenticarContaPanel = new javax.swing.JPanel();
        campoEmailAutenticarTextField = new javax.swing.JTextField();
        SenhaLabel = new javax.swing.JLabel();
        EntrarButton = new javax.swing.JButton();
        campoSenhaAutenticarPasswordField = new javax.swing.JPasswordField();
        redirecionarTelaCriarContaButton = new javax.swing.JButton();
        EmailLabel = new javax.swing.JLabel();
        dadosInvalidosMensagem = new javax.swing.JLabel();
        painelJogoNormal = new javax.swing.JPanel();
        cartaAnteriorButton = new javax.swing.JButton();
        finalizarJogoButton = new javax.swing.JButton();
        proximaCartaButton = new javax.swing.JButton();
        acerteiButton = new javax.swing.JButton();
        erreiButton = new javax.swing.JButton();
        virarCardButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        conteudoCartaLabel = new javax.swing.JLabel();
        meusGruposPainel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        meusGruposTable = new javax.swing.JTable();
        voltarPainelInicial1Button1 = new javax.swing.JButton();
        meusGruposLabel = new javax.swing.JLabel();
        irEditarGruposButton = new javax.swing.JButton();
        criarGrupoButton = new javax.swing.JButton();
        excluirGruposButton = new javax.swing.JButton();
        adicionarBaralhosPainel = new javax.swing.JPanel();
        inserirNomeBaralhoTextField = new javax.swing.JTextField();
        criarBaralhoButton = new javax.swing.JButton();
        voltarMeusBaralhos1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        inserirMateriaTextField = new javax.swing.JTextField();
        mensagemAdicionarBaralhoInvalido = new javax.swing.JLabel();
        importarBaralhoButton = new javax.swing.JButton();
        editarCartasPainel = new javax.swing.JPanel();
        confirmarEditarCartasButton = new javax.swing.JButton();
        editarPerguntaCaixaDeTexto = new javax.swing.JTextField();
        editarRespostaCaixaDeTexto = new javax.swing.JTextField();
        voltarMeusCardsButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        mensagemEditarPerguntaInvalida = new javax.swing.JLabel();
        selecionarBaralhoJogarPainel = new javax.swing.JPanel();
        jogarButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        selecionarBaralhoJogarTable = new javax.swing.JTable();
        voltarPainelInicialButton = new javax.swing.JButton();
        selecionarBaralhosJogarLabel = new javax.swing.JLabel();
        baralhoSemCartasLabel = new javax.swing.JLabel();
        painelInicial = new javax.swing.JPanel();
        irJogarButton = new javax.swing.JButton();
        abrirMeusBaralhosButton = new javax.swing.JButton();
        sairButton = new javax.swing.JButton();
        abrirMeusGruposButton = new javax.swing.JButton();
        meusCardsPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        minhasCartasTable = new javax.swing.JTable();
        irParaCriarCardButton = new javax.swing.JButton();
        voltarMeusBaralhosButton = new javax.swing.JButton();
        meusCardsLabel = new javax.swing.JLabel();
        irParaEditarBaralho = new javax.swing.JButton();
        nomeDoBaralhoLabel = new javax.swing.JLabel();
        codigoDoBaralhoLabel = new javax.swing.JLabel();
        criarContaPanel = new javax.swing.JPanel();
        criarContaButton = new javax.swing.JButton();
        jaTenhoContaButton = new javax.swing.JButton();
        campoUsuarioTextField = new javax.swing.JTextField();
        confirmarSenhaLabel = new javax.swing.JLabel();
        campoEmailTextField = new javax.swing.JTextField();
        senhaLabel = new javax.swing.JLabel();
        campoSenhaPasswordField = new javax.swing.JPasswordField();
        campoConfirmarSenhaPasswordField = new javax.swing.JPasswordField();
        usuarioLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        mensagemCriarContaInvalidaLabel = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        criarCardsPanel = new javax.swing.JPanel();
        confirmarCriarCardButton = new javax.swing.JButton();
        inserirPerguntaCaixaDeTexto = new javax.swing.JTextField();
        inserirRespostaCaixaDeTexto = new javax.swing.JTextField();
        voltarMeusCardsButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        mensagemCriarCardInvalidoLabel = new javax.swing.JLabel();
        editarGrupoPainel = new javax.swing.JPanel();
        confirmarEditarCartasButton1 = new javax.swing.JButton();
        editarNomeGrupoCaixaDeTexto = new javax.swing.JTextField();
        voltarEditarGrupoAcoesButton = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        mensagemEditarGrupoInvalido = new javax.swing.JLabel();
        adicionarAlunoGrupoPainel = new javax.swing.JPanel();
        confirmarAdicionarAluno = new javax.swing.JButton();
        inserirEmailAlunoCaixaDeTexto = new javax.swing.JTextField();
        voltarEditarGrupoAcoesButton2 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        mensagemEmailAlunoInvalido = new javax.swing.JLabel();
        baralhosDoGrupoPainel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        baralhosDoGrupoTable = new javax.swing.JTable();
        voltarMeusGrupos = new javax.swing.JButton();
        baralhosDoGrupoLabel = new javax.swing.JLabel();
        editarBaralhoGrupoButton = new javax.swing.JButton();
        criarBaralhoGrupoButton = new javax.swing.JButton();
        nomeDoGrupoLabel = new javax.swing.JLabel();
        excluirBaralhoGrupoButton = new javax.swing.JButton();
        mensagemBaralhoDoGrupoSemCartas = new javax.swing.JLabel();
        adicionarGrupoPainel = new javax.swing.JPanel();
        confirmarCriarGrupoButton = new javax.swing.JButton();
        inserirNomeGrupoCaixaDeTexto = new javax.swing.JTextField();
        voltarMeusGruposButton2 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        mensagemCriarGrupoInvalido = new javax.swing.JLabel();
        alunosDoGrupoPainel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        alunosDoGrupoTable = new javax.swing.JTable();
        voltarMeusGrupos1 = new javax.swing.JButton();
        alunosDoGrupoLabel = new javax.swing.JLabel();
        irExcluirAlunosGruposButton = new javax.swing.JButton();
        AdicionarAlunoButton = new javax.swing.JButton();
        nomeDoGrupoLabel1 = new javax.swing.JLabel();
        criarBaralhoGrupoPainel = new javax.swing.JPanel();
        inserirNomeBaralhoGrupoCaixaDeTexto = new javax.swing.JTextField();
        confirmarCriarBaralhoGrupoButton = new javax.swing.JButton();
        voltarMeusBaralhosGrupoButton = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        inserirTemaBaralhoGrupoCaixaDeTexto = new javax.swing.JTextField();
        mensagemAdicionarBaralhoGrupoInvalido = new javax.swing.JLabel();
        cartasDoGrupoPainel = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        cartasDoGrupoTable = new javax.swing.JTable();
        voltarBaralhosDoGrupoButton = new javax.swing.JButton();
        cartasDoGrupoLabel = new javax.swing.JLabel();
        irEditarCartasDoGrupoButton = new javax.swing.JButton();
        criarCartasGrupoButton = new javax.swing.JButton();
        nomeDoBaralhoGrupoLabel = new javax.swing.JLabel();
        codigoDoBaralhoGrupoLabel = new javax.swing.JLabel();
        excluirCardButton = new javax.swing.JButton();
        criarCardsGrupoPanel = new javax.swing.JPanel();
        confirmarCriarCardButton1 = new javax.swing.JButton();
        inserirPerguntaGrupoCaixaDeTexto = new javax.swing.JTextField();
        inserirRespostaGrupoCaixaDeTexto = new javax.swing.JTextField();
        voltarMeusCardsButton1 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        mensagemCriarCardGrupoInvalidoLabel = new javax.swing.JLabel();
        editarCartasGrupoPainel = new javax.swing.JPanel();
        confirmarEditarCartasGrupoButton = new javax.swing.JButton();
        editarPerguntaGrupoCaixaDeTexto = new javax.swing.JTextField();
        editarRespostaGrupoCaixaDeTexto = new javax.swing.JTextField();
        voltarCardsDoGrupoPainel = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        mensagemEditarPerguntaGrupoInvalida = new javax.swing.JLabel();
        selecionarModoDeJogoPainel = new javax.swing.JPanel();
        modoInserirRespostaButton = new javax.swing.JButton();
        modoDeJogoInvertidoButton = new javax.swing.JButton();
        voltarMeusGruposButton1 = new javax.swing.JButton();
        modoDeJogoNormalButton = new javax.swing.JButton();
        selecionarModoDeJogoGrupoPainel = new javax.swing.JPanel();
        modoInserirRespostaButton1 = new javax.swing.JButton();
        modoDeJogoInvertidoButton1 = new javax.swing.JButton();
        voltarMeusGruposButton3 = new javax.swing.JButton();
        modoDeJogoNormalButton1 = new javax.swing.JButton();
        jogoBaralhosDoGrupoPainel = new javax.swing.JPanel();
        cartaAnteriorButton1 = new javax.swing.JButton();
        finalizarJogoButton1 = new javax.swing.JButton();
        proximaCartaButton1 = new javax.swing.JButton();
        acerteiButton1 = new javax.swing.JButton();
        erreiButton1 = new javax.swing.JButton();
        virarCardButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        conteudoCartaLabel1 = new javax.swing.JLabel();
        painelJogoFinalizado1 = new javax.swing.JPanel();
        estatisticasJogoLabel1 = new javax.swing.JLabel();
        terminarJogoVoltarPainelButton1 = new javax.swing.JButton();
        painelJogoInserir = new javax.swing.JPanel();
        finalizarJogoButton2 = new javax.swing.JButton();
        pularCarta = new javax.swing.JButton();
        confirmarRespostaButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        conteudoCartaLabel2 = new javax.swing.JLabel();
        inserirRespostaTF = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        mensagemRespostaInvalida = new javax.swing.JLabel();
        painelJogoInserir1 = new javax.swing.JPanel();
        finalizarJogoButton3 = new javax.swing.JButton();
        pularCarta1 = new javax.swing.JButton();
        confirmarRespostaButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        conteudoCartaLabel3 = new javax.swing.JLabel();
        inserirRespostaTF1 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        mensagemRespostaInvalida1 = new javax.swing.JLabel();
        editarUsuarioPainel = new javax.swing.JPanel();
        editarNomeButton = new javax.swing.JButton();
        editarEmailButton = new javax.swing.JButton();
        voltarPainelInicialButton2 = new javax.swing.JButton();
        editarSenhaButton = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        excluirContaButton = new javax.swing.JButton();
        editarNomeUsuarioPainel = new javax.swing.JPanel();
        confirmarEditarNomeUsuario = new javax.swing.JButton();
        nomeUsuarioTF = new javax.swing.JTextField();
        voltarEditarUsuario = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        mensagemEditarNomeUsuarioInvalido = new javax.swing.JLabel();
        editarEmailUsuarioPainel = new javax.swing.JPanel();
        confirmarEditarEmailUsuario = new javax.swing.JButton();
        emailUsuarioTF = new javax.swing.JTextField();
        voltarEditarUsuario1 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        mensagemEditarEmailInvalido = new javax.swing.JLabel();
        editarSenhaPainel = new javax.swing.JPanel();
        confirmarEditarSenha = new javax.swing.JButton();
        voltarEditarUsuario2 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        mensagemEditarSenhaInvalido = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        senhaVelhaPF = new javax.swing.JPasswordField();
        senhaNovaPF = new javax.swing.JPasswordField();
        importarBaralhoPainel = new javax.swing.JPanel();
        confirmarImportarBaralhoButton = new javax.swing.JButton();
        codigoBaralhoTF = new javax.swing.JTextField();
        voltarCriarBaralhoButton = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        mensagemImportarBaralhoInvalido = new javax.swing.JLabel();
        confirmarSairJogoPainel = new javax.swing.JPanel();
        mensagemDesejaExcluirBaralho1 = new javax.swing.JLabel();
        confirmarExcluirBaralhoButton1 = new javax.swing.JButton();
        voltarMeusBaralhosButton3 = new javax.swing.JButton();
        confirmarExcluirGrupoPainel = new javax.swing.JPanel();
        mensagemDesejaExcluirBaralho2 = new javax.swing.JLabel();
        confirmarExcluirBaralhoButton2 = new javax.swing.JButton();
        voltarMeusBaralhosButton4 = new javax.swing.JButton();
        confirmarExcluirCartaPainel = new javax.swing.JPanel();
        mensagemDesejaExcluirBaralho3 = new javax.swing.JLabel();
        confirmarExcluirBaralhoButton3 = new javax.swing.JButton();
        voltarMeusBaralhosButton5 = new javax.swing.JButton();
        confirmarExcluirAlunoGrupoPainel = new javax.swing.JPanel();
        mensagemDesejaExcluirBaralho4 = new javax.swing.JLabel();
        confirmarExcluirBaralhoButton4 = new javax.swing.JButton();
        voltarMeusBaralhosButton6 = new javax.swing.JButton();
        confirmarExcluirCartaGrupoPainel = new javax.swing.JPanel();
        mensagemDesejaExcluirBaralho5 = new javax.swing.JLabel();
        confirmarExcluirBaralhoButton5 = new javax.swing.JButton();
        voltarMeusBaralhosButton7 = new javax.swing.JButton();
        confirmarExcluirBaralhoGrupoPainel = new javax.swing.JPanel();
        mensagemDesejaExcluirBaralho6 = new javax.swing.JLabel();
        confirmarExcluirBaralhoButton6 = new javax.swing.JButton();
        voltarMeusBaralhosButton8 = new javax.swing.JButton();
        confirmarExcluirContaPainel = new javax.swing.JPanel();
        mensagemDesejaExcluirBaralho7 = new javax.swing.JLabel();
        confirmarExcluirBaralhoButton7 = new javax.swing.JButton();
        voltarMeusBaralhosButton9 = new javax.swing.JButton();
        excluirContaPainel = new javax.swing.JPanel();
        confirmarExcluirUsuarioButton = new javax.swing.JButton();
        emailUsuarioParaExcluirTF = new javax.swing.JTextField();
        voltarEditarGrupoAcoesButton3 = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        mensagemEmailInvalido = new javax.swing.JLabel();
        confirmarExcluirUsuarioPainel = new javax.swing.JPanel();
        mensagemDesejaExcluirBaralho8 = new javax.swing.JLabel();
        confirmarExcluirBaralhoButton8 = new javax.swing.JButton();
        voltarMeusBaralhosButton10 = new javax.swing.JButton();
        confirmarSairPainel = new javax.swing.JPanel();
        mensagemDesejaExcluirBaralho9 = new javax.swing.JLabel();
        confirmarExcluirBaralhoButton9 = new javax.swing.JButton();
        voltarMeusBaralhosButton11 = new javax.swing.JButton();
        excluirAlunoBotao = new javax.swing.JButton();
        editarUsuarioButton = new javax.swing.JButton();
        botaoMusica = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        botaoSair = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        labelFundo = new javax.swing.JLabel();

        jLabel32.setText("jLabel32");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1080, 760));
        setMinimumSize(new java.awt.Dimension(1080, 760));
        setPreferredSize(new java.awt.Dimension(1080, 760));
        setSize(new java.awt.Dimension(760, 760));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        editarBaralhoGrupoPainel.setBackground(new java.awt.Color(246, 231, 211));
        editarBaralhoGrupoPainel.setForeground(new java.awt.Color(255, 255, 255));

        confirmarEditarBaralhoGrupoButton.setBackground(new java.awt.Color(237, 30, 82));
        confirmarEditarBaralhoGrupoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarEditarBaralhoGrupoButton.setForeground(new java.awt.Color(255, 255, 255));
        confirmarEditarBaralhoGrupoButton.setText("Confirmar");
        confirmarEditarBaralhoGrupoButton.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                confirmarEditarBaralhoGrupoButtonAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        confirmarEditarBaralhoGrupoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarEditarBaralhoGrupoButtonActionPerformed(evt);
            }
        });

        editarNomeBaralhoGrupoCaixaDeTexto.setBackground(new java.awt.Color(28, 181, 196));
        editarNomeBaralhoGrupoCaixaDeTexto.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        editarNomeBaralhoGrupoCaixaDeTexto.setForeground(new java.awt.Color(0, 0, 0));
        editarNomeBaralhoGrupoCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 38));

        editarTemaBaralhoGrupoCaixaDeTexto.setBackground(new java.awt.Color(28, 181, 196));
        editarTemaBaralhoGrupoCaixaDeTexto.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        editarTemaBaralhoGrupoCaixaDeTexto.setForeground(new java.awt.Color(0, 0, 0));
        editarTemaBaralhoGrupoCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 38));

        voltarBaralhosGrupoButton.setBackground(new java.awt.Color(246, 231, 211));
        voltarBaralhosGrupoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarBaralhosGrupoButton.setForeground(new java.awt.Color(255, 255, 255));
        voltarBaralhosGrupoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/e093e382-363e-45a3-a9df-99fc90c2ab85.png (4).png"))); // NOI18N
        voltarBaralhosGrupoButton.setBorder(null);
        voltarBaralhosGrupoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarBaralhosGrupoButtonActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Editar baralho");

        jLabel24.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Tema");

        jLabel25.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Nome");

        mensagemEditarBaralhoGrupoInvalido.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        mensagemEditarBaralhoGrupoInvalido.setForeground(new java.awt.Color(28, 181, 196));
        mensagemEditarBaralhoGrupoInvalido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout editarBaralhoGrupoPainelLayout = new javax.swing.GroupLayout(editarBaralhoGrupoPainel);
        editarBaralhoGrupoPainel.setLayout(editarBaralhoGrupoPainelLayout);
        editarBaralhoGrupoPainelLayout.setHorizontalGroup(
            editarBaralhoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editarBaralhoGrupoPainelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(confirmarEditarBaralhoGrupoButton)
                .addGap(162, 162, 162))
            .addGroup(editarBaralhoGrupoPainelLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(editarBaralhoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mensagemEditarBaralhoGrupoInvalido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editarTemaBaralhoGrupoCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editarNomeBaralhoGrupoCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 125, Short.MAX_VALUE))
            .addGroup(editarBaralhoGrupoPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarBaralhosGrupoButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        editarBaralhoGrupoPainelLayout.setVerticalGroup(
            editarBaralhoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarBaralhoGrupoPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarBaralhosGrupoButton)
                .addGap(36, 36, 36)
                .addComponent(jLabel23)
                .addGap(18, 18, 18)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editarNomeBaralhoGrupoCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editarTemaBaralhoGrupoCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mensagemEditarBaralhoGrupoInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(confirmarEditarBaralhoGrupoButton)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        getContentPane().add(editarBaralhoGrupoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        confirmarExcluirBaralhoPainel.setBackground(new java.awt.Color(246, 231, 211));

        mensagemDesejaExcluirBaralho.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        mensagemDesejaExcluirBaralho.setForeground(new java.awt.Color(0, 0, 0));
        mensagemDesejaExcluirBaralho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensagemDesejaExcluirBaralho.setText("Tem certeza que deseja excluir esse baralho?");

        confirmarExcluirBaralhoButton.setBackground(new java.awt.Color(237, 30, 82));
        confirmarExcluirBaralhoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarExcluirBaralhoButton.setForeground(new java.awt.Color(255, 255, 255));
        confirmarExcluirBaralhoButton.setText("Sim, excluir");
        confirmarExcluirBaralhoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarExcluirBaralhoButtonActionPerformed(evt);
            }
        });

        voltarMeusBaralhosButton2.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusBaralhosButton2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusBaralhosButton2.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusBaralhosButton2.setText("Não, voltar");
        voltarMeusBaralhosButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMeusBaralhosButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout confirmarExcluirBaralhoPainelLayout = new javax.swing.GroupLayout(confirmarExcluirBaralhoPainel);
        confirmarExcluirBaralhoPainel.setLayout(confirmarExcluirBaralhoPainelLayout);
        confirmarExcluirBaralhoPainelLayout.setHorizontalGroup(
            confirmarExcluirBaralhoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mensagemDesejaExcluirBaralho, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
            .addGroup(confirmarExcluirBaralhoPainelLayout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addGroup(confirmarExcluirBaralhoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(confirmarExcluirBaralhoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(voltarMeusBaralhosButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        confirmarExcluirBaralhoPainelLayout.setVerticalGroup(
            confirmarExcluirBaralhoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmarExcluirBaralhoPainelLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(mensagemDesejaExcluirBaralho, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(confirmarExcluirBaralhoButton)
                .addGap(34, 34, 34)
                .addComponent(voltarMeusBaralhosButton2)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        getContentPane().add(confirmarExcluirBaralhoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        editarBaralhoPainel.setBackground(new java.awt.Color(246, 231, 211));
        editarBaralhoPainel.setForeground(new java.awt.Color(255, 255, 255));

        confirmarEditarBaralhoButton.setBackground(new java.awt.Color(237, 30, 82));
        confirmarEditarBaralhoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarEditarBaralhoButton.setForeground(new java.awt.Color(255, 255, 255));
        confirmarEditarBaralhoButton.setText("Confirmar");
        confirmarEditarBaralhoButton.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                confirmarEditarBaralhoButtonAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        confirmarEditarBaralhoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarEditarBaralhoButtonActionPerformed(evt);
            }
        });

        editarNomeBaralhoCaixaDeTexto.setBackground(new java.awt.Color(28, 181, 196));
        editarNomeBaralhoCaixaDeTexto.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        editarNomeBaralhoCaixaDeTexto.setForeground(new java.awt.Color(0, 0, 0));
        editarNomeBaralhoCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 38));
        editarNomeBaralhoCaixaDeTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarNomeBaralhoCaixaDeTextoActionPerformed(evt);
            }
        });

        editarTemaCaixaDeTexto.setBackground(new java.awt.Color(28, 181, 196));
        editarTemaCaixaDeTexto.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        editarTemaCaixaDeTexto.setForeground(new java.awt.Color(0, 0, 0));
        editarTemaCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 38));

        voltarMeusCardsButton3.setBackground(new java.awt.Color(246, 231, 211));
        voltarMeusCardsButton3.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusCardsButton3.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusCardsButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/e093e382-363e-45a3-a9df-99fc90c2ab85.png (4).png"))); // NOI18N
        voltarMeusCardsButton3.setBorder(null);
        voltarMeusCardsButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMeusCardsButton3ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Editar baralho");

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Tema");

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Nome");

        mensagemEditarBaralhoInvalido.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        mensagemEditarBaralhoInvalido.setForeground(new java.awt.Color(28, 181, 196));
        mensagemEditarBaralhoInvalido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout editarBaralhoPainelLayout = new javax.swing.GroupLayout(editarBaralhoPainel);
        editarBaralhoPainel.setLayout(editarBaralhoPainelLayout);
        editarBaralhoPainelLayout.setHorizontalGroup(
            editarBaralhoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editarBaralhoPainelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(confirmarEditarBaralhoButton)
                .addGap(162, 162, 162))
            .addGroup(editarBaralhoPainelLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(editarBaralhoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mensagemEditarBaralhoInvalido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editarTemaCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editarNomeBaralhoCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 125, Short.MAX_VALUE))
            .addGroup(editarBaralhoPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarMeusCardsButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        editarBaralhoPainelLayout.setVerticalGroup(
            editarBaralhoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarBaralhoPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarMeusCardsButton3)
                .addGap(37, 37, 37)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editarNomeBaralhoCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editarTemaCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(confirmarEditarBaralhoButton)
                .addGap(47, 47, 47)
                .addComponent(mensagemEditarBaralhoInvalido)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        getContentPane().add(editarBaralhoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        meusBaralhosPainel.setBackground(new java.awt.Color(246, 231, 211));
        meusBaralhosPainel.setPreferredSize(new java.awt.Dimension(630, 400));

        irParaCriarBaralhoButton.setBackground(new java.awt.Color(237, 30, 82));
        irParaCriarBaralhoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        irParaCriarBaralhoButton.setForeground(new java.awt.Color(255, 255, 255));
        irParaCriarBaralhoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/adicionar.png"))); // NOI18N
        irParaCriarBaralhoButton.setBorder(null);
        irParaCriarBaralhoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irParaCriarBaralhoButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setCorner(JScrollPane.UPPER_RIGHT_CORNER, new JPanel() {{
            setBackground(new Color(246,231,211)); // cor combinando com seu trackColor do scroll
        }});
        JScrollBar verticalScrollBar = jScrollPane1.getVerticalScrollBar();
        verticalScrollBar.setPreferredSize(new Dimension(8, Integer.MAX_VALUE));
        verticalScrollBar.setBackground(new Color(0,0,0,0));
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                thumbColor = new Color(206,191,171);  // cor do "thumb"
                trackColor = new Color(246,231,211);  // cor da "trilha"
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });
        jScrollPane1.setBackground(new java.awt.Color(206, 191, 171));

        meusBaralhosTable.setAutoCreateRowSorter(true);
        meusBaralhosTable.setBackground(new java.awt.Color(206, 191, 171));
        meusBaralhosTable.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        meusBaralhosTable.setForeground(new java.awt.Color(0, 0, 0));
        meusBaralhosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Matéria", "Aproveitamento", "Cards Respondidos", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        meusBaralhosTable.setRowHeight(48);
        jScrollPane1.setViewportView(meusBaralhosTable);
        if (meusBaralhosTable.getColumnModel().getColumnCount() > 0) {
            meusBaralhosTable.getColumnModel().getColumn(2).setResizable(false);
            meusBaralhosTable.getColumnModel().getColumn(4).setPreferredWidth(40);
        }

        meusBaralhosLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        meusBaralhosLabel.setForeground(new java.awt.Color(0, 0, 0));
        meusBaralhosLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        meusBaralhosLabel.setText("Meus baralhos");

        voltarPainelInicial1Button.setBackground(new java.awt.Color(246, 231, 211));
        voltarPainelInicial1Button.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarPainelInicial1Button.setForeground(new java.awt.Color(255, 255, 255));
        voltarPainelInicial1Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarPainelInicial1Button.setBorder(null);
        voltarPainelInicial1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarPainelInicial1ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout meusBaralhosPainelLayout = new javax.swing.GroupLayout(meusBaralhosPainel);
        meusBaralhosPainel.setLayout(meusBaralhosPainelLayout);
        meusBaralhosPainelLayout.setHorizontalGroup(
            meusBaralhosPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, meusBaralhosPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(meusBaralhosPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(meusBaralhosPainelLayout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(meusBaralhosPainelLayout.createSequentialGroup()
                        .addComponent(voltarPainelInicial1Button)
                        .addGap(22, 22, 22)
                        .addComponent(meusBaralhosLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(irParaCriarBaralhoButton)
                        .addContainerGap())))
        );
        meusBaralhosPainelLayout.setVerticalGroup(
            meusBaralhosPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meusBaralhosPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(meusBaralhosPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(voltarPainelInicial1Button)
                    .addGroup(meusBaralhosPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(meusBaralhosLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(irParaCriarBaralhoButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        getContentPane().add(meusBaralhosPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 240, 630, 400));

        painelJogoFinalizado.setBackground(new java.awt.Color(246, 231, 211));

        estatisticasJogoLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        estatisticasJogoLabel.setForeground(new java.awt.Color(0, 0, 0));
        estatisticasJogoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        estatisticasJogoLabel.setText("jLabel7");

        terminarJogoVoltarPainelButton.setBackground(new java.awt.Color(237, 30, 82));
        terminarJogoVoltarPainelButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        terminarJogoVoltarPainelButton.setForeground(new java.awt.Color(255, 255, 255));
        terminarJogoVoltarPainelButton.setText("Finalizar");
        terminarJogoVoltarPainelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminarJogoVoltarPainelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelJogoFinalizadoLayout = new javax.swing.GroupLayout(painelJogoFinalizado);
        painelJogoFinalizado.setLayout(painelJogoFinalizadoLayout);
        painelJogoFinalizadoLayout.setHorizontalGroup(
            painelJogoFinalizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(estatisticasJogoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(painelJogoFinalizadoLayout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(terminarJogoVoltarPainelButton)
                .addContainerGap(168, Short.MAX_VALUE))
        );
        painelJogoFinalizadoLayout.setVerticalGroup(
            painelJogoFinalizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelJogoFinalizadoLayout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(estatisticasJogoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addComponent(terminarJogoVoltarPainelButton)
                .addGap(71, 71, 71))
        );

        getContentPane().add(painelJogoFinalizado, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        autenticarContaPanel.setBackground(new java.awt.Color(246, 231, 211));
        autenticarContaPanel.setPreferredSize(new java.awt.Dimension(440, 440));

        campoEmailAutenticarTextField.setBackground(new java.awt.Color(28, 181, 196));
        campoEmailAutenticarTextField.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        campoEmailAutenticarTextField.setForeground(new java.awt.Color(0, 0, 0));
        campoEmailAutenticarTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        campoEmailAutenticarTextField.setMaximumSize(new java.awt.Dimension(190, 35));
        campoEmailAutenticarTextField.setMinimumSize(new java.awt.Dimension(190, 35));
        campoEmailAutenticarTextField.setName(""); // NOI18N
        campoEmailAutenticarTextField.setPreferredSize(new java.awt.Dimension(190, 38));
        campoEmailAutenticarTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoEmailAutenticarTextFieldActionPerformed(evt);
            }
        });

        SenhaLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        SenhaLabel.setForeground(new java.awt.Color(0, 0, 0));
        SenhaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SenhaLabel.setText("Senha");

        EntrarButton.setBackground(new java.awt.Color(237, 30, 82));
        EntrarButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        EntrarButton.setForeground(new java.awt.Color(255, 255, 255));
        EntrarButton.setText("Entrar");
        EntrarButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EntrarButton.setPreferredSize(new java.awt.Dimension(190, 32));
        EntrarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EntrarButtonActionPerformed(evt);
            }
        });

        campoSenhaAutenticarPasswordField.setBackground(new java.awt.Color(28, 181, 196));
        campoSenhaAutenticarPasswordField.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        campoSenhaAutenticarPasswordField.setMaximumSize(new java.awt.Dimension(190, 35));
        campoSenhaAutenticarPasswordField.setMinimumSize(new java.awt.Dimension(190, 35));
        campoSenhaAutenticarPasswordField.setPreferredSize(new java.awt.Dimension(190, 38));
        campoSenhaAutenticarPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoSenhaAutenticarPasswordFieldActionPerformed(evt);
            }
        });

        redirecionarTelaCriarContaButton.setBackground(new java.awt.Color(60, 63, 65));
        redirecionarTelaCriarContaButton.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        redirecionarTelaCriarContaButton.setForeground(new java.awt.Color(28, 181, 196));
        redirecionarTelaCriarContaButton.setText("Criar Conta");
        redirecionarTelaCriarContaButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        redirecionarTelaCriarContaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redirecionarTelaCriarContaButtonActionPerformed(evt);
            }
        });

        EmailLabel.setBackground(new java.awt.Color(0, 0, 0));
        EmailLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        EmailLabel.setForeground(new java.awt.Color(0, 0, 0));
        EmailLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EmailLabel.setText("E-mail");

        dadosInvalidosMensagem.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        dadosInvalidosMensagem.setForeground(new java.awt.Color(28, 181, 196));
        dadosInvalidosMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout autenticarContaPanelLayout = new javax.swing.GroupLayout(autenticarContaPanel);
        autenticarContaPanel.setLayout(autenticarContaPanelLayout);
        autenticarContaPanelLayout.setHorizontalGroup(
            autenticarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(autenticarContaPanelLayout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addGroup(autenticarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dadosInvalidosMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(autenticarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(campoEmailAutenticarTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(campoSenhaAutenticarPasswordField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(EntrarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SenhaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(EmailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 124, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, autenticarContaPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(redirecionarTelaCriarContaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(174, 174, 174))
        );
        autenticarContaPanelLayout.setVerticalGroup(
            autenticarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(autenticarContaPanelLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(EmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(campoEmailAutenticarTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(SenhaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(campoSenhaAutenticarPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(redirecionarTelaCriarContaButton)
                .addGap(0, 0, 0)
                .addComponent(EntrarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dadosInvalidosMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        getContentPane().add(autenticarContaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        painelJogoNormal.setBackground(new java.awt.Color(246, 231, 211));
        painelJogoNormal.setPreferredSize(new java.awt.Dimension(440, 440));

        cartaAnteriorButton.setBackground(new java.awt.Color(237, 30, 82));
        cartaAnteriorButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        cartaAnteriorButton.setForeground(new java.awt.Color(255, 255, 255));
        cartaAnteriorButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Anterior carta.png"))); // NOI18N
        cartaAnteriorButton.setBorder(null);
        cartaAnteriorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartaAnteriorButtonActionPerformed(evt);
            }
        });

        finalizarJogoButton.setBackground(new java.awt.Color(237, 30, 82));
        finalizarJogoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        finalizarJogoButton.setForeground(new java.awt.Color(255, 255, 255));
        finalizarJogoButton.setText("Finalizar");
        finalizarJogoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarJogoButtonActionPerformed(evt);
            }
        });

        proximaCartaButton.setBackground(new java.awt.Color(237, 30, 82));
        proximaCartaButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        proximaCartaButton.setForeground(new java.awt.Color(255, 255, 255));
        proximaCartaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Proxima Carta.png"))); // NOI18N
        proximaCartaButton.setBorder(null);
        proximaCartaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proximaCartaButtonActionPerformed(evt);
            }
        });

        acerteiButton.setBackground(new java.awt.Color(246, 231, 211));
        acerteiButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        acerteiButton.setForeground(new java.awt.Color(255, 255, 255));
        acerteiButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/AcerteiButton.png"))); // NOI18N
        acerteiButton.setBorder(null);
        acerteiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acerteiButtonActionPerformed(evt);
            }
        });

        erreiButton.setBackground(new java.awt.Color(237, 30, 82));
        erreiButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        erreiButton.setForeground(new java.awt.Color(255, 255, 255));
        erreiButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/errreiButton.png"))); // NOI18N
        erreiButton.setBorder(null);
        erreiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                erreiButtonActionPerformed(evt);
            }
        });

        virarCardButton.setBackground(new java.awt.Color(237, 30, 82));
        virarCardButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        virarCardButton.setForeground(new java.awt.Color(255, 255, 255));
        virarCardButton.setText("Ver resposta");
        virarCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                virarCardButtonActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(226, 211, 191));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 191, 171), 5, true));

        conteudoCartaLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        conteudoCartaLabel.setForeground(new java.awt.Color(0, 0, 0));
        conteudoCartaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        conteudoCartaLabel.setText("jLabel7");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(conteudoCartaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(conteudoCartaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(124, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout painelJogoNormalLayout = new javax.swing.GroupLayout(painelJogoNormal);
        painelJogoNormal.setLayout(painelJogoNormalLayout);
        painelJogoNormalLayout.setHorizontalGroup(
            painelJogoNormalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoNormalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelJogoNormalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoNormalLayout.createSequentialGroup()
                        .addComponent(finalizarJogoButton)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoNormalLayout.createSequentialGroup()
                        .addGroup(painelJogoNormalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(painelJogoNormalLayout.createSequentialGroup()
                                .addComponent(cartaAnteriorButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(proximaCartaButton))
                            .addGroup(painelJogoNormalLayout.createSequentialGroup()
                                .addComponent(erreiButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(acerteiButton))
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73))))
            .addGroup(painelJogoNormalLayout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(virarCardButton)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        painelJogoNormalLayout.setVerticalGroup(
            painelJogoNormalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoNormalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(finalizarJogoButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelJogoNormalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painelJogoNormalLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(erreiButton))
                    .addComponent(acerteiButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(virarCardButton)
                .addGap(13, 13, 13)
                .addGroup(painelJogoNormalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(proximaCartaButton)
                    .addComponent(cartaAnteriorButton))
                .addGap(18, 18, 18))
        );

        getContentPane().add(painelJogoNormal, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        meusGruposPainel.setBackground(new java.awt.Color(246, 231, 211));
        meusGruposPainel.setPreferredSize(new java.awt.Dimension(580, 440));

        jScrollPane4.setCorner(JScrollPane.UPPER_RIGHT_CORNER, new JPanel() {{
            setBackground(new Color(246,231,211));
        }});
        verticalScrollBar = jScrollPane4.getVerticalScrollBar();
        verticalScrollBar.setPreferredSize(new Dimension(8, Integer.MAX_VALUE));
        verticalScrollBar.setBackground(new Color(0,0,0,0));
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                thumbColor = new Color(206,191,171);
                trackColor = new Color(246,231,211);
            }
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });
        jScrollPane4.setBackground(new java.awt.Color(206, 191, 171));

        meusGruposTable.setAutoCreateRowSorter(true);
        meusGruposTable.setBackground(new java.awt.Color(206, 191, 171));
        meusGruposTable.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        meusGruposTable.setForeground(new java.awt.Color(0, 0, 0));
        meusGruposTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Professor", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        meusGruposTable.setRowHeight(48);
        meusGruposTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(meusGruposTable);
        if (meusGruposTable.getColumnModel().getColumnCount() > 0) {
            meusGruposTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            meusGruposTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        }

        voltarPainelInicial1Button1.setBackground(new java.awt.Color(237, 30, 82));
        voltarPainelInicial1Button1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarPainelInicial1Button1.setForeground(new java.awt.Color(255, 255, 255));
        voltarPainelInicial1Button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarPainelInicial1Button1.setBorder(null);
        voltarPainelInicial1Button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarPainelInicial1Button1ActionPerformed(evt);
            }
        });

        meusGruposLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        meusGruposLabel.setForeground(new java.awt.Color(0, 0, 0));
        meusGruposLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        meusGruposLabel.setText("Meus grupos");

        irEditarGruposButton.setBackground(new java.awt.Color(237, 30, 82));
        irEditarGruposButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        irEditarGruposButton.setForeground(new java.awt.Color(255, 255, 255));
        irEditarGruposButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/editar.png"))); // NOI18N
        irEditarGruposButton.setBorder(null);
        irEditarGruposButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irEditarGruposButtonActionPerformed(evt);
            }
        });

        criarGrupoButton.setBackground(new java.awt.Color(237, 30, 82));
        criarGrupoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        criarGrupoButton.setForeground(new java.awt.Color(255, 255, 255));
        criarGrupoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/adicionar.png"))); // NOI18N
        criarGrupoButton.setBorder(null);
        criarGrupoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarGrupoButtonActionPerformed(evt);
            }
        });

        excluirGruposButton.setBackground(new java.awt.Color(237, 30, 82));
        excluirGruposButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        excluirGruposButton.setForeground(new java.awt.Color(255, 255, 255));
        excluirGruposButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/deletar (2).png"))); // NOI18N
        excluirGruposButton.setBorder(null);
        excluirGruposButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirGruposButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout meusGruposPainelLayout = new javax.swing.GroupLayout(meusGruposPainel);
        meusGruposPainel.setLayout(meusGruposPainelLayout);
        meusGruposPainelLayout.setHorizontalGroup(
            meusGruposPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meusGruposPainelLayout.createSequentialGroup()
                .addGroup(meusGruposPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(meusGruposPainelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(voltarPainelInicial1Button1)
                        .addGap(97, 97, 97)
                        .addComponent(excluirGruposButton)
                        .addGap(108, 108, 108)
                        .addComponent(irEditarGruposButton)
                        .addGap(108, 108, 108)
                        .addComponent(criarGrupoButton))
                    .addGroup(meusGruposPainelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(meusGruposPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(meusGruposLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18))
        );
        meusGruposPainelLayout.setVerticalGroup(
            meusGruposPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meusGruposPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(meusGruposPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(voltarPainelInicial1Button1)
                    .addComponent(excluirGruposButton)
                    .addComponent(irEditarGruposButton)
                    .addComponent(criarGrupoButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(meusGruposLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );

        getContentPane().add(meusGruposPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 580, 440));

        adicionarBaralhosPainel.setBackground(new java.awt.Color(246, 231, 211));
        adicionarBaralhosPainel.setMaximumSize(new java.awt.Dimension(440, 440));
        adicionarBaralhosPainel.setMinimumSize(new java.awt.Dimension(440, 440));
        adicionarBaralhosPainel.setPreferredSize(new java.awt.Dimension(440, 440));

        inserirNomeBaralhoTextField.setBackground(new java.awt.Color(28, 181, 196));
        inserirNomeBaralhoTextField.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        inserirNomeBaralhoTextField.setForeground(new java.awt.Color(0, 0, 0));
        inserirNomeBaralhoTextField.setPreferredSize(new java.awt.Dimension(190, 38));
        inserirNomeBaralhoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirNomeBaralhoTextFieldActionPerformed(evt);
            }
        });

        criarBaralhoButton.setBackground(new java.awt.Color(237, 30, 82));
        criarBaralhoButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        criarBaralhoButton.setForeground(new java.awt.Color(255, 255, 255));
        criarBaralhoButton.setText("Confirmar");
        criarBaralhoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarBaralhoButtonActionPerformed(evt);
            }
        });

        voltarMeusBaralhos1.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusBaralhos1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        voltarMeusBaralhos1.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusBaralhos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarMeusBaralhos1.setBorder(null);
        voltarMeusBaralhos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMeusBaralhos1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("matéria");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Adicionar baralho");

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("nome");

        inserirMateriaTextField.setBackground(new java.awt.Color(28, 181, 196));
        inserirMateriaTextField.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        inserirMateriaTextField.setForeground(new java.awt.Color(0, 0, 0));
        inserirMateriaTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inserirMateriaTextField.setPreferredSize(new java.awt.Dimension(190, 38));
        inserirMateriaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirMateriaTextFieldActionPerformed(evt);
            }
        });

        mensagemAdicionarBaralhoInvalido.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        mensagemAdicionarBaralhoInvalido.setForeground(new java.awt.Color(28, 181, 196));
        mensagemAdicionarBaralhoInvalido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        importarBaralhoButton.setBackground(new java.awt.Color(246, 231, 211));
        importarBaralhoButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        importarBaralhoButton.setForeground(new java.awt.Color(255, 255, 255));
        importarBaralhoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Imagem upload.png"))); // NOI18N
        importarBaralhoButton.setBorder(null);
        importarBaralhoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importarBaralhoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout adicionarBaralhosPainelLayout = new javax.swing.GroupLayout(adicionarBaralhosPainel);
        adicionarBaralhosPainel.setLayout(adicionarBaralhosPainelLayout);
        adicionarBaralhosPainelLayout.setHorizontalGroup(
            adicionarBaralhosPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adicionarBaralhosPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarMeusBaralhos1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(importarBaralhoButton)
                .addGap(33, 33, 33))
            .addGroup(adicionarBaralhosPainelLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(adicionarBaralhosPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mensagemAdicionarBaralhoInvalido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(adicionarBaralhosPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(inserirNomeBaralhoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(inserirMateriaTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(125, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adicionarBaralhosPainelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(criarBaralhoButton)
                .addGap(160, 160, 160))
        );
        adicionarBaralhosPainelLayout.setVerticalGroup(
            adicionarBaralhosPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adicionarBaralhosPainelLayout.createSequentialGroup()
                .addGroup(adicionarBaralhosPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(adicionarBaralhosPainelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(importarBaralhoButton))
                    .addGroup(adicionarBaralhosPainelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(voltarMeusBaralhos1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(31, 31, 31)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inserirNomeBaralhoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inserirMateriaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(mensagemAdicionarBaralhoInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(criarBaralhoButton)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        getContentPane().add(adicionarBaralhosPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        editarCartasPainel.setBackground(new java.awt.Color(246, 231, 211));
        editarCartasPainel.setForeground(new java.awt.Color(255, 255, 255));

        confirmarEditarCartasButton.setBackground(new java.awt.Color(237, 30, 82));
        confirmarEditarCartasButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarEditarCartasButton.setForeground(new java.awt.Color(255, 255, 255));
        confirmarEditarCartasButton.setText("Confirmar");
        confirmarEditarCartasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarEditarCartasButtonActionPerformed(evt);
            }
        });

        editarPerguntaCaixaDeTexto.setBackground(new java.awt.Color(28, 181, 196));
        editarPerguntaCaixaDeTexto.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        editarPerguntaCaixaDeTexto.setForeground(new java.awt.Color(0, 0, 0));
        editarPerguntaCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 38));

        editarRespostaCaixaDeTexto.setBackground(new java.awt.Color(28, 181, 196));
        editarRespostaCaixaDeTexto.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        editarRespostaCaixaDeTexto.setForeground(new java.awt.Color(0, 0, 0));
        editarRespostaCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 38));

        voltarMeusCardsButton2.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusCardsButton2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusCardsButton2.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusCardsButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarMeusCardsButton2.setBorder(null);
        voltarMeusCardsButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMeusCardsButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Editar card");

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Resposta");

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Pergunta");

        mensagemEditarPerguntaInvalida.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        mensagemEditarPerguntaInvalida.setForeground(new java.awt.Color(28, 181, 196));
        mensagemEditarPerguntaInvalida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout editarCartasPainelLayout = new javax.swing.GroupLayout(editarCartasPainel);
        editarCartasPainel.setLayout(editarCartasPainelLayout);
        editarCartasPainelLayout.setHorizontalGroup(
            editarCartasPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarCartasPainelLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(editarCartasPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mensagemEditarPerguntaInvalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editarRespostaCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editarPerguntaCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 125, Short.MAX_VALUE))
            .addGroup(editarCartasPainelLayout.createSequentialGroup()
                .addGroup(editarCartasPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editarCartasPainelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(voltarMeusCardsButton2))
                    .addGroup(editarCartasPainelLayout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(confirmarEditarCartasButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        editarCartasPainelLayout.setVerticalGroup(
            editarCartasPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarCartasPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarMeusCardsButton2)
                .addGap(37, 37, 37)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editarPerguntaCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editarRespostaCaixaDeTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mensagemEditarPerguntaInvalida, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(confirmarEditarCartasButton)
                .addGap(97, 97, 97))
        );

        getContentPane().add(editarCartasPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        selecionarBaralhoJogarPainel.setBackground(new java.awt.Color(246, 231, 211));
        selecionarBaralhoJogarPainel.setPreferredSize(new java.awt.Dimension(440, 458));

        jogarButton.setBackground(new java.awt.Color(237, 30, 82));
        jogarButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jogarButton.setForeground(new java.awt.Color(255, 255, 255));
        jogarButton.setText("Jogar");
        jogarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jogarButtonActionPerformed(evt);
            }
        });

        jScrollPane3.setCorner(JScrollPane.UPPER_RIGHT_CORNER, new JPanel() {{
            setBackground(new Color(246,231,211));
        }});
        verticalScrollBar = jScrollPane3.getVerticalScrollBar();
        verticalScrollBar.setPreferredSize(new Dimension(8, Integer.MAX_VALUE));
        verticalScrollBar.setBackground(new Color(0,0,0,0));
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                thumbColor = new Color(206,191,171);
                trackColor = new Color(246,231,211);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });

        selecionarBaralhoJogarTable.setBackground(new java.awt.Color(246, 231, 211));
        selecionarBaralhoJogarTable.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        selecionarBaralhoJogarTable.setForeground(new java.awt.Color(0, 0, 0));
        selecionarBaralhoJogarTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome", "Matéria", "Número de cards"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        selecionarBaralhoJogarTable.setRowHeight(45);
        selecionarBaralhoJogarTable.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane3.setViewportView(selecionarBaralhoJogarTable);

        voltarPainelInicialButton.setBackground(new java.awt.Color(246, 231, 211));
        voltarPainelInicialButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarPainelInicialButton.setForeground(new java.awt.Color(255, 255, 255));
        voltarPainelInicialButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarPainelInicialButton.setBorder(null);
        voltarPainelInicialButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarPainelInicialButtonActionPerformed(evt);
            }
        });

        selecionarBaralhosJogarLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        selecionarBaralhosJogarLabel.setForeground(new java.awt.Color(0, 0, 0));
        selecionarBaralhosJogarLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        selecionarBaralhosJogarLabel.setText("Selecione um baralho");

        baralhoSemCartasLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        baralhoSemCartasLabel.setForeground(new java.awt.Color(28, 181, 196));
        baralhoSemCartasLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout selecionarBaralhoJogarPainelLayout = new javax.swing.GroupLayout(selecionarBaralhoJogarPainel);
        selecionarBaralhoJogarPainel.setLayout(selecionarBaralhoJogarPainelLayout);
        selecionarBaralhoJogarPainelLayout.setHorizontalGroup(
            selecionarBaralhoJogarPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(baralhoSemCartasLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(selecionarBaralhosJogarLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selecionarBaralhoJogarPainelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jogarButton)
                .addGap(180, 180, 180))
            .addGroup(selecionarBaralhoJogarPainelLayout.createSequentialGroup()
                .addGroup(selecionarBaralhoJogarPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(selecionarBaralhoJogarPainelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(voltarPainelInicialButton))
                    .addGroup(selecionarBaralhoJogarPainelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        selecionarBaralhoJogarPainelLayout.setVerticalGroup(
            selecionarBaralhoJogarPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selecionarBaralhoJogarPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarPainelInicialButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(selecionarBaralhosJogarLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jogarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(baralhoSemCartasLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(selecionarBaralhoJogarPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 211, -1, -1));

        painelInicial.setBackground(new java.awt.Color(246, 231, 211));

        irJogarButton.setBackground(new java.awt.Color(237, 30, 82));
        irJogarButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        irJogarButton.setForeground(new java.awt.Color(255, 255, 255));
        irJogarButton.setText("Jogar");
        irJogarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irJogarButtonActionPerformed(evt);
            }
        });

        abrirMeusBaralhosButton.setBackground(new java.awt.Color(237, 30, 82));
        abrirMeusBaralhosButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        abrirMeusBaralhosButton.setForeground(new java.awt.Color(255, 255, 255));
        abrirMeusBaralhosButton.setText("Meus Baralhos");
        abrirMeusBaralhosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirMeusBaralhosButtonActionPerformed(evt);
            }
        });

        sairButton.setBackground(new java.awt.Color(237, 30, 82));
        sairButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        sairButton.setForeground(new java.awt.Color(255, 255, 255));
        sairButton.setText("Sair");
        sairButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairButtonActionPerformed(evt);
            }
        });

        abrirMeusGruposButton.setBackground(new java.awt.Color(237, 30, 82));
        abrirMeusGruposButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        abrirMeusGruposButton.setForeground(new java.awt.Color(255, 255, 255));
        abrirMeusGruposButton.setText("Meus Grupos");
        abrirMeusGruposButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirMeusGruposButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelInicialLayout = new javax.swing.GroupLayout(painelInicial);
        painelInicial.setLayout(painelInicialLayout);
        painelInicialLayout.setHorizontalGroup(
            painelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelInicialLayout.createSequentialGroup()
                .addGap(0, 141, Short.MAX_VALUE)
                .addGroup(painelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(irJogarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(abrirMeusBaralhosButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sairButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(abrirMeusGruposButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(141, 141, 141))
        );
        painelInicialLayout.setVerticalGroup(
            painelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelInicialLayout.createSequentialGroup()
                .addContainerGap(127, Short.MAX_VALUE)
                .addComponent(irJogarButton)
                .addGap(18, 18, 18)
                .addComponent(abrirMeusBaralhosButton)
                .addGap(18, 18, 18)
                .addComponent(abrirMeusGruposButton)
                .addGap(18, 18, 18)
                .addComponent(sairButton)
                .addGap(127, 127, 127))
        );

        getContentPane().add(painelInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        meusCardsPanel.setBackground(new java.awt.Color(246, 231, 211));
        meusCardsPanel.setPreferredSize(new java.awt.Dimension(900, 500));

        jScrollPane2.setCorner(JScrollPane.UPPER_RIGHT_CORNER, new JPanel() {{
            setBackground(new Color(246,231,211));
        }});
        verticalScrollBar = jScrollPane2.getVerticalScrollBar();
        verticalScrollBar.setPreferredSize(new Dimension(8, Integer.MAX_VALUE));
        verticalScrollBar.setBackground(new Color(0,0,0,0));
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                thumbColor = new Color(206,191,171);
                trackColor = new Color(246,231,211);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });

        minhasCartasTable.setBackground(new java.awt.Color(206, 191, 171));
        minhasCartasTable.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        minhasCartasTable.setForeground(new java.awt.Color(0, 0, 0));
        minhasCartasTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pergunta", "Resposta", "Aproveitamento", "Jogadas", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        minhasCartasTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        minhasCartasTable.setGridColor(new java.awt.Color(246, 231, 211));
        minhasCartasTable.setRowHeight(40);
        minhasCartasTable.setShowGrid(false);
        jScrollPane2.setViewportView(minhasCartasTable);
        if (minhasCartasTable.getColumnModel().getColumnCount() > 0) {
            minhasCartasTable.getColumnModel().getColumn(4).setPreferredWidth(162);
        }

        irParaCriarCardButton.setBackground(new java.awt.Color(237, 30, 82));
        irParaCriarCardButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        irParaCriarCardButton.setForeground(new java.awt.Color(255, 255, 255));
        irParaCriarCardButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/adicionar.png"))); // NOI18N
        irParaCriarCardButton.setBorder(null);
        irParaCriarCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irParaCriarCardButtonActionPerformed(evt);
            }
        });

        voltarMeusBaralhosButton.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusBaralhosButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusBaralhosButton.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusBaralhosButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarMeusBaralhosButton.setBorder(null);
        voltarMeusBaralhosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMeusBaralhosButtonActionPerformed(evt);
            }
        });

        meusCardsLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        meusCardsLabel.setForeground(new java.awt.Color(0, 0, 0));
        meusCardsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        meusCardsLabel.setText("Meus cards");

        irParaEditarBaralho.setBackground(new java.awt.Color(237, 30, 82));
        irParaEditarBaralho.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        irParaEditarBaralho.setForeground(new java.awt.Color(255, 255, 255));
        irParaEditarBaralho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/editar.png"))); // NOI18N
        irParaEditarBaralho.setBorder(null);
        irParaEditarBaralho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irParaEditarBaralhoActionPerformed(evt);
            }
        });

        nomeDoBaralhoLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        nomeDoBaralhoLabel.setForeground(new java.awt.Color(0, 0, 0));
        nomeDoBaralhoLabel.setText("jLabel14");

        codigoDoBaralhoLabel.setBackground(new java.awt.Color(0, 0, 0));
        codigoDoBaralhoLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        codigoDoBaralhoLabel.setForeground(new java.awt.Color(0, 0, 0));
        codigoDoBaralhoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        codigoDoBaralhoLabel.setText("Codigo do baralho: 1231314r2");

        javax.swing.GroupLayout meusCardsPanelLayout = new javax.swing.GroupLayout(meusCardsPanel);
        meusCardsPanel.setLayout(meusCardsPanelLayout);
        meusCardsPanelLayout.setHorizontalGroup(
            meusCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(meusCardsLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(meusCardsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(irParaEditarBaralho)
                .addGap(29, 29, 29)
                .addComponent(irParaCriarCardButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(meusCardsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarMeusBaralhosButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(codigoDoBaralhoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addComponent(nomeDoBaralhoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        meusCardsPanelLayout.setVerticalGroup(
            meusCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meusCardsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(meusCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(meusCardsPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(codigoDoBaralhoLabel))
                    .addComponent(voltarMeusBaralhosButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nomeDoBaralhoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(meusCardsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(meusCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(irParaEditarBaralho)
                    .addComponent(irParaCriarCardButton)))
        );

        getContentPane().add(meusCardsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 900, 500));

        criarContaPanel.setBackground(new java.awt.Color(246, 231, 211));

        criarContaButton.setBackground(new java.awt.Color(237, 30, 82));
        criarContaButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        criarContaButton.setForeground(new java.awt.Color(255, 255, 255));
        criarContaButton.setText("Criar conta");
        criarContaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarContaButtonActionPerformed(evt);
            }
        });

        jaTenhoContaButton.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jaTenhoContaButton.setForeground(new java.awt.Color(28, 181, 196));
        jaTenhoContaButton.setText("Já tenho conta");
        jaTenhoContaButton.setBorder(null);
        jaTenhoContaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jaTenhoContaButtonActionPerformed(evt);
            }
        });

        campoUsuarioTextField.setBackground(new java.awt.Color(28, 181, 196));
        campoUsuarioTextField.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        campoUsuarioTextField.setForeground(new java.awt.Color(0, 0, 0));
        campoUsuarioTextField.setPreferredSize(new java.awt.Dimension(190, 38));
        campoUsuarioTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoUsuarioTextFieldActionPerformed(evt);
            }
        });

        confirmarSenhaLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarSenhaLabel.setForeground(new java.awt.Color(0, 0, 0));
        confirmarSenhaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        confirmarSenhaLabel.setText("Confirmar senha");

        campoEmailTextField.setBackground(new java.awt.Color(28, 181, 196));
        campoEmailTextField.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        campoEmailTextField.setForeground(new java.awt.Color(0, 0, 0));
        campoEmailTextField.setPreferredSize(new java.awt.Dimension(190, 38));

        senhaLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        senhaLabel.setForeground(new java.awt.Color(0, 0, 0));
        senhaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        senhaLabel.setText("Senha");

        campoSenhaPasswordField.setBackground(new java.awt.Color(28, 181, 196));
        campoSenhaPasswordField.setFont(new java.awt.Font("Gill Sans MT", 1, 18)); // NOI18N
        campoSenhaPasswordField.setPreferredSize(new java.awt.Dimension(190, 38));

        campoConfirmarSenhaPasswordField.setBackground(new java.awt.Color(28, 181, 196));
        campoConfirmarSenhaPasswordField.setFont(new java.awt.Font("Gill Sans MT", 1, 18)); // NOI18N
        campoConfirmarSenhaPasswordField.setForeground(new java.awt.Color(0, 0, 0));
        campoConfirmarSenhaPasswordField.setPreferredSize(new java.awt.Dimension(190, 38));

        usuarioLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        usuarioLabel.setForeground(new java.awt.Color(0, 0, 0));
        usuarioLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usuarioLabel.setText("Usuário");

        emailLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(0, 0, 0));
        emailLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        emailLabel.setText("E-mail");

        mensagemCriarContaInvalidaLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        mensagemCriarContaInvalidaLabel.setForeground(new java.awt.Color(28, 181, 196));
        mensagemCriarContaInvalidaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel13.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Criar conta");

        javax.swing.GroupLayout criarContaPanelLayout = new javax.swing.GroupLayout(criarContaPanel);
        criarContaPanel.setLayout(criarContaPanelLayout);
        criarContaPanelLayout.setHorizontalGroup(
            criarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, criarContaPanelLayout.createSequentialGroup()
                .addGap(0, 109, Short.MAX_VALUE)
                .addGroup(criarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, criarContaPanelLayout.createSequentialGroup()
                        .addComponent(jaTenhoContaButton)
                        .addGap(169, 169, 169))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, criarContaPanelLayout.createSequentialGroup()
                        .addGroup(criarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(criarContaButton, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(mensagemCriarContaInvalidaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(107, 107, 107))))
            .addGroup(criarContaPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(criarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(criarContaPanelLayout.createSequentialGroup()
                        .addGroup(criarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(senhaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(campoSenhaPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(criarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(confirmarSenhaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(campoConfirmarSenhaPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(criarContaPanelLayout.createSequentialGroup()
                        .addGroup(criarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(campoEmailTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addGroup(criarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoUsuarioTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usuarioLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(14, Short.MAX_VALUE))
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        criarContaPanelLayout.setVerticalGroup(
            criarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(criarContaPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(criarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(usuarioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(criarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoEmailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoUsuarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(criarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(senhaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmarSenhaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(criarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoSenhaPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoConfirmarSenhaPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jaTenhoContaButton)
                .addGap(0, 0, 0)
                .addComponent(criarContaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mensagemCriarContaInvalidaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        getContentPane().add(criarContaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        criarCardsPanel.setBackground(new java.awt.Color(246, 231, 211));
        criarCardsPanel.setPreferredSize(new java.awt.Dimension(440, 440));

        confirmarCriarCardButton.setBackground(new java.awt.Color(237, 30, 82));
        confirmarCriarCardButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarCriarCardButton.setForeground(new java.awt.Color(255, 255, 255));
        confirmarCriarCardButton.setText("Confirmar");
        confirmarCriarCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarCriarCardButtonActionPerformed(evt);
            }
        });

        inserirPerguntaCaixaDeTexto.setBackground(new java.awt.Color(28, 181, 196));
        inserirPerguntaCaixaDeTexto.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        inserirPerguntaCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 38));

        inserirRespostaCaixaDeTexto.setBackground(new java.awt.Color(28, 181, 196));
        inserirRespostaCaixaDeTexto.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        inserirRespostaCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 38));

        voltarMeusCardsButton.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusCardsButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusCardsButton.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusCardsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarMeusCardsButton.setBorder(null);
        voltarMeusCardsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMeusCardsButtonActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Criar Card");

        jLabel11.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Pergunta");

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Resposta");

        mensagemCriarCardInvalidoLabel.setBackground(new java.awt.Color(28, 181, 196));
        mensagemCriarCardInvalidoLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        mensagemCriarCardInvalidoLabel.setForeground(new java.awt.Color(28, 181, 196));
        mensagemCriarCardInvalidoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout criarCardsPanelLayout = new javax.swing.GroupLayout(criarCardsPanel);
        criarCardsPanel.setLayout(criarCardsPanelLayout);
        criarCardsPanelLayout.setHorizontalGroup(
            criarCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(criarCardsPanelLayout.createSequentialGroup()
                .addGroup(criarCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(criarCardsPanelLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(criarCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(criarCardsPanelLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(confirmarCriarCardButton))
                            .addGroup(criarCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(inserirPerguntaCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(inserirRespostaCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mensagemCriarCardInvalidoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(criarCardsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(voltarMeusCardsButton)))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        criarCardsPanelLayout.setVerticalGroup(
            criarCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(criarCardsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarMeusCardsButton)
                .addGap(50, 50, 50)
                .addComponent(jLabel10)
                .addGap(34, 34, 34)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inserirPerguntaCaixaDeTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inserirRespostaCaixaDeTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(mensagemCriarCardInvalidoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmarCriarCardButton)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        getContentPane().add(criarCardsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, 440, 440));

        editarGrupoPainel.setBackground(new java.awt.Color(246, 231, 211));
        editarGrupoPainel.setForeground(new java.awt.Color(255, 255, 255));

        confirmarEditarCartasButton1.setBackground(new java.awt.Color(237, 30, 82));
        confirmarEditarCartasButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarEditarCartasButton1.setForeground(new java.awt.Color(255, 255, 255));
        confirmarEditarCartasButton1.setText("Confirmar");
        confirmarEditarCartasButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarEditarCartasButton1ActionPerformed(evt);
            }
        });

        editarNomeGrupoCaixaDeTexto.setBackground(new java.awt.Color(28, 181, 196));
        editarNomeGrupoCaixaDeTexto.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        editarNomeGrupoCaixaDeTexto.setForeground(new java.awt.Color(0, 0, 0));
        editarNomeGrupoCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 38));

        voltarEditarGrupoAcoesButton.setBackground(new java.awt.Color(237, 30, 82));
        voltarEditarGrupoAcoesButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarEditarGrupoAcoesButton.setForeground(new java.awt.Color(255, 255, 255));
        voltarEditarGrupoAcoesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarEditarGrupoAcoesButton.setBorder(null);
        voltarEditarGrupoAcoesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarEditarGrupoAcoesButtonActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Editar grupo");

        jLabel16.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Nome");

        mensagemEditarGrupoInvalido.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        mensagemEditarGrupoInvalido.setForeground(new java.awt.Color(28, 181, 196));
        mensagemEditarGrupoInvalido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout editarGrupoPainelLayout = new javax.swing.GroupLayout(editarGrupoPainel);
        editarGrupoPainel.setLayout(editarGrupoPainelLayout);
        editarGrupoPainelLayout.setHorizontalGroup(
            editarGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarGrupoPainelLayout.createSequentialGroup()
                .addGroup(editarGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editarGrupoPainelLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(editarGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editarGrupoPainelLayout.createSequentialGroup()
                                .addComponent(confirmarEditarCartasButton1)
                                .addGap(37, 37, 37))
                            .addGroup(editarGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(editarNomeGrupoCaixaDeTexto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(mensagemEditarGrupoInvalido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(editarGrupoPainelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(voltarEditarGrupoAcoesButton)))
                .addGap(0, 125, Short.MAX_VALUE))
        );
        editarGrupoPainelLayout.setVerticalGroup(
            editarGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarGrupoPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarEditarGrupoAcoesButton)
                .addGap(58, 58, 58)
                .addComponent(jLabel14)
                .addGap(44, 44, 44)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editarNomeGrupoCaixaDeTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mensagemEditarGrupoInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(confirmarEditarCartasButton1)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        getContentPane().add(editarGrupoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        adicionarAlunoGrupoPainel.setBackground(new java.awt.Color(246, 231, 211));
        adicionarAlunoGrupoPainel.setForeground(new java.awt.Color(255, 255, 255));

        confirmarAdicionarAluno.setBackground(new java.awt.Color(237, 30, 82));
        confirmarAdicionarAluno.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarAdicionarAluno.setForeground(new java.awt.Color(255, 255, 255));
        confirmarAdicionarAluno.setText("Confirmar");
        confirmarAdicionarAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarAdicionarAlunoActionPerformed(evt);
            }
        });

        inserirEmailAlunoCaixaDeTexto.setBackground(new java.awt.Color(28, 181, 196));
        inserirEmailAlunoCaixaDeTexto.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        inserirEmailAlunoCaixaDeTexto.setForeground(new java.awt.Color(0, 0, 0));
        inserirEmailAlunoCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 38));

        voltarEditarGrupoAcoesButton2.setBackground(new java.awt.Color(237, 30, 82));
        voltarEditarGrupoAcoesButton2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarEditarGrupoAcoesButton2.setForeground(new java.awt.Color(255, 255, 255));
        voltarEditarGrupoAcoesButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarEditarGrupoAcoesButton2.setBorder(null);
        voltarEditarGrupoAcoesButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarEditarGrupoAcoesButton2ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Adicionar aluno");

        jLabel17.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Email do aluno");

        mensagemEmailAlunoInvalido.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        mensagemEmailAlunoInvalido.setForeground(new java.awt.Color(28, 181, 196));
        mensagemEmailAlunoInvalido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout adicionarAlunoGrupoPainelLayout = new javax.swing.GroupLayout(adicionarAlunoGrupoPainel);
        adicionarAlunoGrupoPainel.setLayout(adicionarAlunoGrupoPainelLayout);
        adicionarAlunoGrupoPainelLayout.setHorizontalGroup(
            adicionarAlunoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adicionarAlunoGrupoPainelLayout.createSequentialGroup()
                .addGroup(adicionarAlunoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(adicionarAlunoGrupoPainelLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(adicionarAlunoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(adicionarAlunoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(inserirEmailAlunoCaixaDeTexto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(mensagemEmailAlunoInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(adicionarAlunoGrupoPainelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(voltarEditarGrupoAcoesButton2)))
                .addGap(0, 125, Short.MAX_VALUE))
            .addGroup(adicionarAlunoGrupoPainelLayout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addComponent(confirmarAdicionarAluno)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        adicionarAlunoGrupoPainelLayout.setVerticalGroup(
            adicionarAlunoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adicionarAlunoGrupoPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarEditarGrupoAcoesButton2)
                .addGap(58, 58, 58)
                .addComponent(jLabel15)
                .addGap(44, 44, 44)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inserirEmailAlunoCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mensagemEmailAlunoInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(confirmarAdicionarAluno)
                .addGap(113, 113, 113))
        );

        getContentPane().add(adicionarAlunoGrupoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        baralhosDoGrupoPainel.setBackground(new java.awt.Color(246, 231, 211));
        baralhosDoGrupoPainel.setPreferredSize(new java.awt.Dimension(440, 482));

        jScrollPane5.setCorner(JScrollPane.UPPER_RIGHT_CORNER, new JPanel() {{
            setBackground(new Color(246,231,211));
        }});
        verticalScrollBar = jScrollPane5.getVerticalScrollBar();
        verticalScrollBar.setPreferredSize(new Dimension(8, Integer.MAX_VALUE));
        verticalScrollBar.setBackground(new Color(0,0,0,0));
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                thumbColor = new Color(206,191,171);
                trackColor = new Color(246,231,211);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });
        jScrollPane5.setBackground(new java.awt.Color(206, 191, 171));

        baralhosDoGrupoTable.setAutoCreateRowSorter(true);
        baralhosDoGrupoTable.setBackground(new java.awt.Color(206, 191, 171));
        baralhosDoGrupoTable.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        baralhosDoGrupoTable.setForeground(new java.awt.Color(0, 0, 0));
        baralhosDoGrupoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Tema", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        baralhosDoGrupoTable.setRowHeight(48);
        baralhosDoGrupoTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(baralhosDoGrupoTable);
        if (baralhosDoGrupoTable.getColumnModel().getColumnCount() > 0) {
            baralhosDoGrupoTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            baralhosDoGrupoTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            baralhosDoGrupoTable.getColumnModel().getColumn(2).setHeaderValue("");
        }

        voltarMeusGrupos.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusGrupos.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusGrupos.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusGrupos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarMeusGrupos.setBorder(null);
        voltarMeusGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMeusGruposActionPerformed(evt);
            }
        });

        baralhosDoGrupoLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        baralhosDoGrupoLabel.setForeground(new java.awt.Color(0, 0, 0));
        baralhosDoGrupoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        baralhosDoGrupoLabel.setText("Baralhos do grupo");

        editarBaralhoGrupoButton.setBackground(new java.awt.Color(237, 30, 82));
        editarBaralhoGrupoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        editarBaralhoGrupoButton.setForeground(new java.awt.Color(255, 255, 255));
        editarBaralhoGrupoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/editar.png"))); // NOI18N
        editarBaralhoGrupoButton.setBorder(null);
        editarBaralhoGrupoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarBaralhoGrupoButtonActionPerformed(evt);
            }
        });

        criarBaralhoGrupoButton.setBackground(new java.awt.Color(237, 30, 82));
        criarBaralhoGrupoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        criarBaralhoGrupoButton.setForeground(new java.awt.Color(255, 255, 255));
        criarBaralhoGrupoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/adicionar.png"))); // NOI18N
        criarBaralhoGrupoButton.setBorder(null);
        criarBaralhoGrupoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarBaralhoGrupoButtonActionPerformed(evt);
            }
        });

        nomeDoGrupoLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        nomeDoGrupoLabel.setForeground(new java.awt.Color(0, 0, 0));
        nomeDoGrupoLabel.setText("jLabel18");

        excluirBaralhoGrupoButton.setBackground(new java.awt.Color(237, 30, 82));
        excluirBaralhoGrupoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        excluirBaralhoGrupoButton.setForeground(new java.awt.Color(255, 255, 255));
        excluirBaralhoGrupoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/deletar (2).png"))); // NOI18N
        excluirBaralhoGrupoButton.setBorder(null);
        excluirBaralhoGrupoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirBaralhoGrupoButtonActionPerformed(evt);
            }
        });

        mensagemBaralhoDoGrupoSemCartas.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        mensagemBaralhoDoGrupoSemCartas.setForeground(new java.awt.Color(0, 0, 0));
        mensagemBaralhoDoGrupoSemCartas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout baralhosDoGrupoPainelLayout = new javax.swing.GroupLayout(baralhosDoGrupoPainel);
        baralhosDoGrupoPainel.setLayout(baralhosDoGrupoPainelLayout);
        baralhosDoGrupoPainelLayout.setHorizontalGroup(
            baralhosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(baralhosDoGrupoLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(baralhosDoGrupoPainelLayout.createSequentialGroup()
                .addGroup(baralhosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(baralhosDoGrupoPainelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(baralhosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(baralhosDoGrupoPainelLayout.createSequentialGroup()
                                .addGap(126, 126, 126)
                                .addComponent(excluirBaralhoGrupoButton)
                                .addGap(30, 30, 30)
                                .addComponent(editarBaralhoGrupoButton)
                                .addGap(30, 30, 30)
                                .addComponent(criarBaralhoGrupoButton)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)))
                    .addGroup(baralhosDoGrupoPainelLayout.createSequentialGroup()
                        .addGroup(baralhosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomeDoGrupoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(baralhosDoGrupoPainelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(voltarMeusGrupos)
                                .addGap(206, 206, 206)))
                        .addGap(100, 100, 100)))
                .addContainerGap())
            .addComponent(mensagemBaralhoDoGrupoSemCartas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        baralhosDoGrupoPainelLayout.setVerticalGroup(
            baralhosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baralhosDoGrupoPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarMeusGrupos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomeDoGrupoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(baralhosDoGrupoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(mensagemBaralhoDoGrupoSemCartas, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(baralhosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(criarBaralhoGrupoButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(baralhosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(editarBaralhoGrupoButton)
                        .addComponent(excluirBaralhoGrupoButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(baralhosDoGrupoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 199, 440, 482));

        adicionarGrupoPainel.setBackground(new java.awt.Color(246, 231, 211));
        adicionarGrupoPainel.setForeground(new java.awt.Color(255, 255, 255));

        confirmarCriarGrupoButton.setBackground(new java.awt.Color(237, 30, 82));
        confirmarCriarGrupoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarCriarGrupoButton.setForeground(new java.awt.Color(255, 255, 255));
        confirmarCriarGrupoButton.setText("Confirmar");
        confirmarCriarGrupoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarCriarGrupoButtonActionPerformed(evt);
            }
        });

        inserirNomeGrupoCaixaDeTexto.setBackground(new java.awt.Color(28, 181, 196));
        inserirNomeGrupoCaixaDeTexto.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        inserirNomeGrupoCaixaDeTexto.setForeground(new java.awt.Color(0, 0, 0));
        inserirNomeGrupoCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 38));

        voltarMeusGruposButton2.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusGruposButton2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusGruposButton2.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusGruposButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarMeusGruposButton2.setBorder(null);
        voltarMeusGruposButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMeusGruposButton2ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Criar grupo");

        jLabel20.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Nome");

        mensagemCriarGrupoInvalido.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        mensagemCriarGrupoInvalido.setForeground(new java.awt.Color(28, 181, 196));
        mensagemCriarGrupoInvalido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout adicionarGrupoPainelLayout = new javax.swing.GroupLayout(adicionarGrupoPainel);
        adicionarGrupoPainel.setLayout(adicionarGrupoPainelLayout);
        adicionarGrupoPainelLayout.setHorizontalGroup(
            adicionarGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adicionarGrupoPainelLayout.createSequentialGroup()
                .addGroup(adicionarGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(adicionarGrupoPainelLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(adicionarGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(adicionarGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adicionarGrupoPainelLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(confirmarCriarGrupoButton)
                                    .addGap(37, 37, 37))
                                .addGroup(adicionarGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(inserirNomeGrupoCaixaDeTexto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(mensagemCriarGrupoInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(adicionarGrupoPainelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(voltarMeusGruposButton2)))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        adicionarGrupoPainelLayout.setVerticalGroup(
            adicionarGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adicionarGrupoPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarMeusGruposButton2)
                .addGap(25, 25, 25)
                .addComponent(jLabel19)
                .addGap(74, 74, 74)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inserirNomeGrupoCaixaDeTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(confirmarCriarGrupoButton)
                .addGap(65, 65, 65)
                .addComponent(mensagemCriarGrupoInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        getContentPane().add(adicionarGrupoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        alunosDoGrupoPainel.setBackground(new java.awt.Color(246, 231, 211));
        alunosDoGrupoPainel.setPreferredSize(new java.awt.Dimension(440, 440));

        jScrollPane6.setCorner(JScrollPane.UPPER_RIGHT_CORNER, new JPanel() {{
            setBackground(new Color(246,231,211));
        }});
        verticalScrollBar = jScrollPane6.getVerticalScrollBar();
        verticalScrollBar.setPreferredSize(new Dimension(8, Integer.MAX_VALUE));
        verticalScrollBar.setBackground(new Color(0,0,0,0));
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                thumbColor = new Color(206,191,171);
                trackColor = new Color(246,231,211);
            }
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });
        jScrollPane6.setBackground(new java.awt.Color(206, 191, 171));

        alunosDoGrupoTable.setAutoCreateRowSorter(true);
        alunosDoGrupoTable.setBackground(new java.awt.Color(206, 191, 171));
        alunosDoGrupoTable.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        alunosDoGrupoTable.setForeground(new java.awt.Color(0, 0, 0));
        alunosDoGrupoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        alunosDoGrupoTable.setRowHeight(48);
        alunosDoGrupoTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(alunosDoGrupoTable);
        if (alunosDoGrupoTable.getColumnModel().getColumnCount() > 0) {
            alunosDoGrupoTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            alunosDoGrupoTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        }

        voltarMeusGrupos1.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusGrupos1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusGrupos1.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusGrupos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarMeusGrupos1.setBorder(null);
        voltarMeusGrupos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMeusGrupos1ActionPerformed(evt);
            }
        });

        alunosDoGrupoLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        alunosDoGrupoLabel.setForeground(new java.awt.Color(0, 0, 0));
        alunosDoGrupoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        alunosDoGrupoLabel.setText("Alunos do grupo");

        irExcluirAlunosGruposButton.setBackground(new java.awt.Color(237, 30, 82));
        irExcluirAlunosGruposButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        irExcluirAlunosGruposButton.setForeground(new java.awt.Color(255, 255, 255));
        irExcluirAlunosGruposButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/deletar (2).png"))); // NOI18N
        irExcluirAlunosGruposButton.setBorder(null);
        irExcluirAlunosGruposButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irExcluirAlunosGruposButtonActionPerformed(evt);
            }
        });

        AdicionarAlunoButton.setBackground(new java.awt.Color(237, 30, 82));
        AdicionarAlunoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        AdicionarAlunoButton.setForeground(new java.awt.Color(255, 255, 255));
        AdicionarAlunoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/adicionar.png"))); // NOI18N
        AdicionarAlunoButton.setBorder(null);
        AdicionarAlunoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdicionarAlunoButtonActionPerformed(evt);
            }
        });

        nomeDoGrupoLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        nomeDoGrupoLabel1.setForeground(new java.awt.Color(0, 0, 0));
        nomeDoGrupoLabel1.setText("jLabel18");

        javax.swing.GroupLayout alunosDoGrupoPainelLayout = new javax.swing.GroupLayout(alunosDoGrupoPainel);
        alunosDoGrupoPainel.setLayout(alunosDoGrupoPainelLayout);
        alunosDoGrupoPainelLayout.setHorizontalGroup(
            alunosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(alunosDoGrupoPainelLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(alunosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(alunosDoGrupoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
            .addGroup(alunosDoGrupoPainelLayout.createSequentialGroup()
                .addGroup(alunosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(alunosDoGrupoPainelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(voltarMeusGrupos1))
                    .addComponent(nomeDoGrupoLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(irExcluirAlunosGruposButton)
                .addGap(79, 79, 79)
                .addComponent(AdicionarAlunoButton)
                .addGap(139, 139, 139))
        );
        alunosDoGrupoPainelLayout.setVerticalGroup(
            alunosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(alunosDoGrupoPainelLayout.createSequentialGroup()
                .addGroup(alunosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(alunosDoGrupoPainelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(voltarMeusGrupos1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nomeDoGrupoLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, alunosDoGrupoPainelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(alunosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(irExcluirAlunosGruposButton)
                            .addComponent(AdicionarAlunoButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(alunosDoGrupoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        getContentPane().add(alunosDoGrupoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, 440, 440));

        criarBaralhoGrupoPainel.setBackground(new java.awt.Color(246, 231, 211));
        criarBaralhoGrupoPainel.setMaximumSize(new java.awt.Dimension(440, 440));
        criarBaralhoGrupoPainel.setMinimumSize(new java.awt.Dimension(440, 440));

        inserirNomeBaralhoGrupoCaixaDeTexto.setBackground(new java.awt.Color(28, 181, 196));
        inserirNomeBaralhoGrupoCaixaDeTexto.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        inserirNomeBaralhoGrupoCaixaDeTexto.setForeground(new java.awt.Color(0, 0, 0));
        inserirNomeBaralhoGrupoCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 38));
        inserirNomeBaralhoGrupoCaixaDeTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirNomeBaralhoGrupoCaixaDeTextoActionPerformed(evt);
            }
        });

        confirmarCriarBaralhoGrupoButton.setBackground(new java.awt.Color(237, 30, 82));
        confirmarCriarBaralhoGrupoButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        confirmarCriarBaralhoGrupoButton.setForeground(new java.awt.Color(255, 255, 255));
        confirmarCriarBaralhoGrupoButton.setText("Confirmar");
        confirmarCriarBaralhoGrupoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarCriarBaralhoGrupoButtonActionPerformed(evt);
            }
        });

        voltarMeusBaralhosGrupoButton.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusBaralhosGrupoButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        voltarMeusBaralhosGrupoButton.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusBaralhosGrupoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarMeusBaralhosGrupoButton.setBorder(null);
        voltarMeusBaralhosGrupoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMeusBaralhosGrupoButtonActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("matéria");

        jLabel21.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Adicionar baralho");

        jLabel22.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("nome");

        inserirTemaBaralhoGrupoCaixaDeTexto.setBackground(new java.awt.Color(28, 181, 196));
        inserirTemaBaralhoGrupoCaixaDeTexto.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        inserirTemaBaralhoGrupoCaixaDeTexto.setForeground(new java.awt.Color(0, 0, 0));
        inserirTemaBaralhoGrupoCaixaDeTexto.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inserirTemaBaralhoGrupoCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 38));
        inserirTemaBaralhoGrupoCaixaDeTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirTemaBaralhoGrupoCaixaDeTextoActionPerformed(evt);
            }
        });

        mensagemAdicionarBaralhoGrupoInvalido.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        mensagemAdicionarBaralhoGrupoInvalido.setForeground(new java.awt.Color(28, 181, 196));
        mensagemAdicionarBaralhoGrupoInvalido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout criarBaralhoGrupoPainelLayout = new javax.swing.GroupLayout(criarBaralhoGrupoPainel);
        criarBaralhoGrupoPainel.setLayout(criarBaralhoGrupoPainelLayout);
        criarBaralhoGrupoPainelLayout.setHorizontalGroup(
            criarBaralhoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(criarBaralhoGrupoPainelLayout.createSequentialGroup()
                .addGroup(criarBaralhoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(criarBaralhoGrupoPainelLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(criarBaralhoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mensagemAdicionarBaralhoGrupoInvalido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(criarBaralhoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(inserirNomeBaralhoGrupoCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(inserirTemaBaralhoGrupoCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(criarBaralhoGrupoPainelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(voltarMeusBaralhosGrupoButton))
                    .addGroup(criarBaralhoGrupoPainelLayout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(confirmarCriarBaralhoGrupoButton)))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        criarBaralhoGrupoPainelLayout.setVerticalGroup(
            criarBaralhoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(criarBaralhoGrupoPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarMeusBaralhosGrupoButton)
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addGap(31, 31, 31)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inserirNomeBaralhoGrupoCaixaDeTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inserirTemaBaralhoGrupoCaixaDeTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(mensagemAdicionarBaralhoGrupoInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(confirmarCriarBaralhoGrupoButton)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        getContentPane().add(criarBaralhoGrupoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        cartasDoGrupoPainel.setBackground(new java.awt.Color(246, 231, 211));
        cartasDoGrupoPainel.setPreferredSize(new java.awt.Dimension(625, 482));

        jScrollPane7.setCorner(JScrollPane.UPPER_RIGHT_CORNER, new JPanel() {{
            setBackground(new Color(246,231,211));
        }});
        verticalScrollBar = jScrollPane7.getVerticalScrollBar();
        verticalScrollBar.setPreferredSize(new Dimension(8, Integer.MAX_VALUE));
        verticalScrollBar.setBackground(new Color(0,0,0,0));
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                thumbColor = new Color(206,191,171);
                trackColor = new Color(246,231,211);
            }
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });
        jScrollPane7.setBackground(new java.awt.Color(206, 191, 171));

        cartasDoGrupoTable.setAutoCreateRowSorter(true);
        cartasDoGrupoTable.setBackground(new java.awt.Color(206, 191, 171));
        cartasDoGrupoTable.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        cartasDoGrupoTable.setForeground(new java.awt.Color(0, 0, 0));
        cartasDoGrupoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pergunta", "Resposta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        cartasDoGrupoTable.setRowHeight(48);
        cartasDoGrupoTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(cartasDoGrupoTable);
        if (cartasDoGrupoTable.getColumnModel().getColumnCount() > 0) {
            cartasDoGrupoTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            cartasDoGrupoTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        }

        voltarBaralhosDoGrupoButton.setBackground(new java.awt.Color(237, 30, 82));
        voltarBaralhosDoGrupoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarBaralhosDoGrupoButton.setForeground(new java.awt.Color(255, 255, 255));
        voltarBaralhosDoGrupoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarBaralhosDoGrupoButton.setBorder(null);
        voltarBaralhosDoGrupoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarBaralhosDoGrupoButtonActionPerformed(evt);
            }
        });

        cartasDoGrupoLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        cartasDoGrupoLabel.setForeground(new java.awt.Color(0, 0, 0));
        cartasDoGrupoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cartasDoGrupoLabel.setText("Cards");

        irEditarCartasDoGrupoButton.setBackground(new java.awt.Color(237, 30, 82));
        irEditarCartasDoGrupoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        irEditarCartasDoGrupoButton.setForeground(new java.awt.Color(255, 255, 255));
        irEditarCartasDoGrupoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/editar.png"))); // NOI18N
        irEditarCartasDoGrupoButton.setBorder(null);
        irEditarCartasDoGrupoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irEditarCartasDoGrupoButtonActionPerformed(evt);
            }
        });

        criarCartasGrupoButton.setBackground(new java.awt.Color(237, 30, 82));
        criarCartasGrupoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        criarCartasGrupoButton.setForeground(new java.awt.Color(255, 255, 255));
        criarCartasGrupoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/adicionar.png"))); // NOI18N
        criarCartasGrupoButton.setBorder(null);
        criarCartasGrupoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarCartasGrupoButtonActionPerformed(evt);
            }
        });

        nomeDoBaralhoGrupoLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        nomeDoBaralhoGrupoLabel.setForeground(new java.awt.Color(0, 0, 0));
        nomeDoBaralhoGrupoLabel.setText("jLabel18");

        codigoDoBaralhoGrupoLabel.setBackground(new java.awt.Color(0, 0, 0));
        codigoDoBaralhoGrupoLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        codigoDoBaralhoGrupoLabel.setForeground(new java.awt.Color(0, 0, 0));
        codigoDoBaralhoGrupoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        codigoDoBaralhoGrupoLabel.setText("jLabel26");

        excluirCardButton.setBackground(new java.awt.Color(237, 30, 82));
        excluirCardButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        excluirCardButton.setForeground(new java.awt.Color(255, 255, 255));
        excluirCardButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/deletar (2).png"))); // NOI18N
        excluirCardButton.setBorder(null);
        excluirCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirCardButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cartasDoGrupoPainelLayout = new javax.swing.GroupLayout(cartasDoGrupoPainel);
        cartasDoGrupoPainel.setLayout(cartasDoGrupoPainelLayout);
        cartasDoGrupoPainelLayout.setHorizontalGroup(
            cartasDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nomeDoBaralhoGrupoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cartasDoGrupoPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarBaralhosDoGrupoButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(codigoDoBaralhoGrupoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
            .addComponent(cartasDoGrupoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(cartasDoGrupoPainelLayout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(excluirCardButton)
                .addGap(26, 26, 26)
                .addComponent(irEditarCartasDoGrupoButton)
                .addGap(26, 26, 26)
                .addComponent(criarCartasGrupoButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cartasDoGrupoPainelLayout.setVerticalGroup(
            cartasDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartasDoGrupoPainelLayout.createSequentialGroup()
                .addGroup(cartasDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(codigoDoBaralhoGrupoLabel)
                    .addGroup(cartasDoGrupoPainelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(voltarBaralhosDoGrupoButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomeDoBaralhoGrupoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cartasDoGrupoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cartasDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(irEditarCartasDoGrupoButton)
                    .addComponent(excluirCardButton)
                    .addComponent(criarCartasGrupoButton))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        getContentPane().add(cartasDoGrupoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(278, 199, 625, 482));

        criarCardsGrupoPanel.setBackground(new java.awt.Color(246, 231, 211));
        criarCardsGrupoPanel.setPreferredSize(new java.awt.Dimension(440, 440));

        confirmarCriarCardButton1.setBackground(new java.awt.Color(237, 30, 82));
        confirmarCriarCardButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarCriarCardButton1.setForeground(new java.awt.Color(255, 255, 255));
        confirmarCriarCardButton1.setText("Confirmar");
        confirmarCriarCardButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarCriarCardButton1ActionPerformed(evt);
            }
        });

        inserirPerguntaGrupoCaixaDeTexto.setBackground(new java.awt.Color(28, 181, 196));
        inserirPerguntaGrupoCaixaDeTexto.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        inserirPerguntaGrupoCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 38));

        inserirRespostaGrupoCaixaDeTexto.setBackground(new java.awt.Color(28, 181, 196));
        inserirRespostaGrupoCaixaDeTexto.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        inserirRespostaGrupoCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 38));

        voltarMeusCardsButton1.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusCardsButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusCardsButton1.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusCardsButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarMeusCardsButton1.setBorder(null);
        voltarMeusCardsButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMeusCardsButton1ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Criar Card");

        jLabel27.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Pergunta");

        jLabel28.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Resposta");

        mensagemCriarCardGrupoInvalidoLabel.setBackground(new java.awt.Color(28, 181, 196));
        mensagemCriarCardGrupoInvalidoLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        mensagemCriarCardGrupoInvalidoLabel.setForeground(new java.awt.Color(28, 181, 196));
        mensagemCriarCardGrupoInvalidoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout criarCardsGrupoPanelLayout = new javax.swing.GroupLayout(criarCardsGrupoPanel);
        criarCardsGrupoPanel.setLayout(criarCardsGrupoPanelLayout);
        criarCardsGrupoPanelLayout.setHorizontalGroup(
            criarCardsGrupoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(criarCardsGrupoPanelLayout.createSequentialGroup()
                .addGroup(criarCardsGrupoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(criarCardsGrupoPanelLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(confirmarCriarCardButton1))
                    .addGroup(criarCardsGrupoPanelLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(criarCardsGrupoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inserirPerguntaGrupoCaixaDeTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inserirRespostaGrupoCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mensagemCriarCardGrupoInvalidoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(criarCardsGrupoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(voltarMeusCardsButton1)))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        criarCardsGrupoPanelLayout.setVerticalGroup(
            criarCardsGrupoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(criarCardsGrupoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarMeusCardsButton1)
                .addGap(32, 32, 32)
                .addComponent(jLabel26)
                .addGap(34, 34, 34)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inserirPerguntaGrupoCaixaDeTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inserirRespostaGrupoCaixaDeTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(mensagemCriarCardGrupoInvalidoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmarCriarCardButton1)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        getContentPane().add(criarCardsGrupoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        editarCartasGrupoPainel.setBackground(new java.awt.Color(246, 231, 211));
        editarCartasGrupoPainel.setForeground(new java.awt.Color(255, 255, 255));

        confirmarEditarCartasGrupoButton.setBackground(new java.awt.Color(237, 30, 82));
        confirmarEditarCartasGrupoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarEditarCartasGrupoButton.setForeground(new java.awt.Color(255, 255, 255));
        confirmarEditarCartasGrupoButton.setText("Confirmar");
        confirmarEditarCartasGrupoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarEditarCartasGrupoButtonActionPerformed(evt);
            }
        });

        editarPerguntaGrupoCaixaDeTexto.setBackground(new java.awt.Color(28, 181, 196));
        editarPerguntaGrupoCaixaDeTexto.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        editarPerguntaGrupoCaixaDeTexto.setForeground(new java.awt.Color(0, 0, 0));
        editarPerguntaGrupoCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 38));

        editarRespostaGrupoCaixaDeTexto.setBackground(new java.awt.Color(28, 181, 196));
        editarRespostaGrupoCaixaDeTexto.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        editarRespostaGrupoCaixaDeTexto.setForeground(new java.awt.Color(0, 0, 0));
        editarRespostaGrupoCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 38));

        voltarCardsDoGrupoPainel.setBackground(new java.awt.Color(237, 30, 82));
        voltarCardsDoGrupoPainel.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarCardsDoGrupoPainel.setForeground(new java.awt.Color(255, 255, 255));
        voltarCardsDoGrupoPainel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarCardsDoGrupoPainel.setBorder(null);
        voltarCardsDoGrupoPainel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarCardsDoGrupoPainelActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Editar card");

        jLabel30.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Resposta");

        jLabel31.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Pergunta");

        mensagemEditarPerguntaGrupoInvalida.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        mensagemEditarPerguntaGrupoInvalida.setForeground(new java.awt.Color(28, 181, 196));
        mensagemEditarPerguntaGrupoInvalida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout editarCartasGrupoPainelLayout = new javax.swing.GroupLayout(editarCartasGrupoPainel);
        editarCartasGrupoPainel.setLayout(editarCartasGrupoPainelLayout);
        editarCartasGrupoPainelLayout.setHorizontalGroup(
            editarCartasGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarCartasGrupoPainelLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(editarCartasGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mensagemEditarPerguntaGrupoInvalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editarRespostaGrupoCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editarPerguntaGrupoCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 125, Short.MAX_VALUE))
            .addGroup(editarCartasGrupoPainelLayout.createSequentialGroup()
                .addGroup(editarCartasGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editarCartasGrupoPainelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(voltarCardsDoGrupoPainel))
                    .addGroup(editarCartasGrupoPainelLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(confirmarEditarCartasGrupoButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        editarCartasGrupoPainelLayout.setVerticalGroup(
            editarCartasGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarCartasGrupoPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarCardsDoGrupoPainel)
                .addGap(37, 37, 37)
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editarPerguntaGrupoCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editarRespostaGrupoCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mensagemEditarPerguntaGrupoInvalida, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(confirmarEditarCartasGrupoButton)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        getContentPane().add(editarCartasGrupoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        selecionarModoDeJogoPainel.setBackground(new java.awt.Color(246, 231, 211));

        modoInserirRespostaButton.setBackground(new java.awt.Color(237, 30, 82));
        modoInserirRespostaButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        modoInserirRespostaButton.setForeground(new java.awt.Color(255, 255, 255));
        modoInserirRespostaButton.setText("Responder");
        modoInserirRespostaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modoInserirRespostaButtonActionPerformed(evt);
            }
        });

        modoDeJogoInvertidoButton.setBackground(new java.awt.Color(237, 30, 82));
        modoDeJogoInvertidoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        modoDeJogoInvertidoButton.setForeground(new java.awt.Color(255, 255, 255));
        modoDeJogoInvertidoButton.setText("Invertido");
        modoDeJogoInvertidoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modoDeJogoInvertidoButtonActionPerformed(evt);
            }
        });

        voltarMeusGruposButton1.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusGruposButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusGruposButton1.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusGruposButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarMeusGruposButton1.setBorder(null);
        voltarMeusGruposButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMeusGruposButton1ActionPerformed(evt);
            }
        });

        modoDeJogoNormalButton.setBackground(new java.awt.Color(237, 30, 82));
        modoDeJogoNormalButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        modoDeJogoNormalButton.setForeground(new java.awt.Color(255, 255, 255));
        modoDeJogoNormalButton.setText("Normal");
        modoDeJogoNormalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modoDeJogoNormalButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout selecionarModoDeJogoPainelLayout = new javax.swing.GroupLayout(selecionarModoDeJogoPainel);
        selecionarModoDeJogoPainel.setLayout(selecionarModoDeJogoPainelLayout);
        selecionarModoDeJogoPainelLayout.setHorizontalGroup(
            selecionarModoDeJogoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selecionarModoDeJogoPainelLayout.createSequentialGroup()
                .addContainerGap(160, Short.MAX_VALUE)
                .addGroup(selecionarModoDeJogoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(modoInserirRespostaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modoDeJogoInvertidoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modoDeJogoNormalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(160, 160, 160))
            .addGroup(selecionarModoDeJogoPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarMeusGruposButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        selecionarModoDeJogoPainelLayout.setVerticalGroup(
            selecionarModoDeJogoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selecionarModoDeJogoPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarMeusGruposButton1)
                .addGap(117, 117, 117)
                .addComponent(modoDeJogoNormalButton)
                .addGap(18, 18, 18)
                .addComponent(modoDeJogoInvertidoButton)
                .addGap(18, 18, 18)
                .addComponent(modoInserirRespostaButton)
                .addContainerGap(153, Short.MAX_VALUE))
        );

        getContentPane().add(selecionarModoDeJogoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        selecionarModoDeJogoGrupoPainel.setBackground(new java.awt.Color(246, 231, 211));

        modoInserirRespostaButton1.setBackground(new java.awt.Color(237, 30, 82));
        modoInserirRespostaButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        modoInserirRespostaButton1.setForeground(new java.awt.Color(255, 255, 255));
        modoInserirRespostaButton1.setText("Responder");
        modoInserirRespostaButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modoInserirRespostaButton1ActionPerformed(evt);
            }
        });

        modoDeJogoInvertidoButton1.setBackground(new java.awt.Color(237, 30, 82));
        modoDeJogoInvertidoButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        modoDeJogoInvertidoButton1.setForeground(new java.awt.Color(255, 255, 255));
        modoDeJogoInvertidoButton1.setText("Invertido");
        modoDeJogoInvertidoButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modoDeJogoInvertidoButton1ActionPerformed(evt);
            }
        });

        voltarMeusGruposButton3.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusGruposButton3.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusGruposButton3.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusGruposButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarMeusGruposButton3.setBorder(null);
        voltarMeusGruposButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMeusGruposButton3ActionPerformed(evt);
            }
        });

        modoDeJogoNormalButton1.setBackground(new java.awt.Color(237, 30, 82));
        modoDeJogoNormalButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        modoDeJogoNormalButton1.setForeground(new java.awt.Color(255, 255, 255));
        modoDeJogoNormalButton1.setText("Normal");
        modoDeJogoNormalButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modoDeJogoNormalButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout selecionarModoDeJogoGrupoPainelLayout = new javax.swing.GroupLayout(selecionarModoDeJogoGrupoPainel);
        selecionarModoDeJogoGrupoPainel.setLayout(selecionarModoDeJogoGrupoPainelLayout);
        selecionarModoDeJogoGrupoPainelLayout.setHorizontalGroup(
            selecionarModoDeJogoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selecionarModoDeJogoGrupoPainelLayout.createSequentialGroup()
                .addContainerGap(160, Short.MAX_VALUE)
                .addGroup(selecionarModoDeJogoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(modoInserirRespostaButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modoDeJogoInvertidoButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modoDeJogoNormalButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(160, 160, 160))
            .addGroup(selecionarModoDeJogoGrupoPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarMeusGruposButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        selecionarModoDeJogoGrupoPainelLayout.setVerticalGroup(
            selecionarModoDeJogoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selecionarModoDeJogoGrupoPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarMeusGruposButton3)
                .addGap(117, 117, 117)
                .addComponent(modoDeJogoNormalButton1)
                .addGap(18, 18, 18)
                .addComponent(modoDeJogoInvertidoButton1)
                .addGap(18, 18, 18)
                .addComponent(modoInserirRespostaButton1)
                .addContainerGap(153, Short.MAX_VALUE))
        );

        getContentPane().add(selecionarModoDeJogoGrupoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        jogoBaralhosDoGrupoPainel.setBackground(new java.awt.Color(246, 231, 211));
        jogoBaralhosDoGrupoPainel.setPreferredSize(new java.awt.Dimension(440, 440));

        cartaAnteriorButton1.setBackground(new java.awt.Color(237, 30, 82));
        cartaAnteriorButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        cartaAnteriorButton1.setForeground(new java.awt.Color(255, 255, 255));
        cartaAnteriorButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Anterior carta.png"))); // NOI18N
        cartaAnteriorButton1.setBorder(null);
        cartaAnteriorButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartaAnteriorButton1ActionPerformed(evt);
            }
        });

        finalizarJogoButton1.setBackground(new java.awt.Color(237, 30, 82));
        finalizarJogoButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        finalizarJogoButton1.setForeground(new java.awt.Color(255, 255, 255));
        finalizarJogoButton1.setText("Finalizar");
        finalizarJogoButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarJogoButton1ActionPerformed(evt);
            }
        });

        proximaCartaButton1.setBackground(new java.awt.Color(237, 30, 82));
        proximaCartaButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        proximaCartaButton1.setForeground(new java.awt.Color(255, 255, 255));
        proximaCartaButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Proxima Carta.png"))); // NOI18N
        proximaCartaButton1.setBorder(null);
        proximaCartaButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proximaCartaButton1ActionPerformed(evt);
            }
        });

        acerteiButton1.setBackground(new java.awt.Color(237, 30, 82));
        acerteiButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        acerteiButton1.setForeground(new java.awt.Color(255, 255, 255));
        acerteiButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/AcerteiButton.png"))); // NOI18N
        acerteiButton1.setBorder(null);
        acerteiButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acerteiButton1ActionPerformed(evt);
            }
        });

        erreiButton1.setBackground(new java.awt.Color(237, 30, 82));
        erreiButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        erreiButton1.setForeground(new java.awt.Color(255, 255, 255));
        erreiButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/errreiButton.png"))); // NOI18N
        erreiButton1.setBorder(null);
        erreiButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                erreiButton1ActionPerformed(evt);
            }
        });

        virarCardButton1.setBackground(new java.awt.Color(237, 30, 82));
        virarCardButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        virarCardButton1.setForeground(new java.awt.Color(255, 255, 255));
        virarCardButton1.setText("Ver resposta");
        virarCardButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                virarCardButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(226, 211, 191));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 191, 171), 5, true));

        conteudoCartaLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        conteudoCartaLabel1.setForeground(new java.awt.Color(0, 0, 0));
        conteudoCartaLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        conteudoCartaLabel1.setText("jLabel7");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(conteudoCartaLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(conteudoCartaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(124, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jogoBaralhosDoGrupoPainelLayout = new javax.swing.GroupLayout(jogoBaralhosDoGrupoPainel);
        jogoBaralhosDoGrupoPainel.setLayout(jogoBaralhosDoGrupoPainelLayout);
        jogoBaralhosDoGrupoPainelLayout.setHorizontalGroup(
            jogoBaralhosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jogoBaralhosDoGrupoPainelLayout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addGroup(jogoBaralhosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jogoBaralhosDoGrupoPainelLayout.createSequentialGroup()
                        .addComponent(finalizarJogoButton1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jogoBaralhosDoGrupoPainelLayout.createSequentialGroup()
                        .addGroup(jogoBaralhosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jogoBaralhosDoGrupoPainelLayout.createSequentialGroup()
                                .addComponent(cartaAnteriorButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(proximaCartaButton1))
                            .addGroup(jogoBaralhosDoGrupoPainelLayout.createSequentialGroup()
                                .addComponent(erreiButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(acerteiButton1))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73))))
            .addGroup(jogoBaralhosDoGrupoPainelLayout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addComponent(virarCardButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jogoBaralhosDoGrupoPainelLayout.setVerticalGroup(
            jogoBaralhosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jogoBaralhosDoGrupoPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(finalizarJogoButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jogoBaralhosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acerteiButton1)
                    .addComponent(erreiButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(virarCardButton1)
                .addGap(18, 18, 18)
                .addGroup(jogoBaralhosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(proximaCartaButton1)
                    .addComponent(cartaAnteriorButton1))
                .addGap(22, 22, 22))
        );

        getContentPane().add(jogoBaralhosDoGrupoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        painelJogoFinalizado1.setBackground(new java.awt.Color(246, 231, 211));

        estatisticasJogoLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        estatisticasJogoLabel1.setForeground(new java.awt.Color(0, 0, 0));
        estatisticasJogoLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        estatisticasJogoLabel1.setText("jLabel7");

        terminarJogoVoltarPainelButton1.setBackground(new java.awt.Color(237, 30, 82));
        terminarJogoVoltarPainelButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        terminarJogoVoltarPainelButton1.setForeground(new java.awt.Color(255, 255, 255));
        terminarJogoVoltarPainelButton1.setText("Finalizar");
        terminarJogoVoltarPainelButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminarJogoVoltarPainelButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelJogoFinalizado1Layout = new javax.swing.GroupLayout(painelJogoFinalizado1);
        painelJogoFinalizado1.setLayout(painelJogoFinalizado1Layout);
        painelJogoFinalizado1Layout.setHorizontalGroup(
            painelJogoFinalizado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(estatisticasJogoLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(painelJogoFinalizado1Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(terminarJogoVoltarPainelButton1)
                .addContainerGap(168, Short.MAX_VALUE))
        );
        painelJogoFinalizado1Layout.setVerticalGroup(
            painelJogoFinalizado1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelJogoFinalizado1Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(estatisticasJogoLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addComponent(terminarJogoVoltarPainelButton1)
                .addGap(71, 71, 71))
        );

        getContentPane().add(painelJogoFinalizado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        painelJogoInserir.setBackground(new java.awt.Color(246, 231, 211));

        finalizarJogoButton2.setBackground(new java.awt.Color(237, 30, 82));
        finalizarJogoButton2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        finalizarJogoButton2.setForeground(new java.awt.Color(255, 255, 255));
        finalizarJogoButton2.setText("Finalizar");
        finalizarJogoButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarJogoButton2ActionPerformed(evt);
            }
        });

        pularCarta.setBackground(new java.awt.Color(237, 30, 82));
        pularCarta.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        pularCarta.setForeground(new java.awt.Color(255, 255, 255));
        pularCarta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Proxima Carta.png"))); // NOI18N
        pularCarta.setBorder(null);
        pularCarta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pularCartaActionPerformed(evt);
            }
        });

        confirmarRespostaButton.setBackground(new java.awt.Color(237, 30, 82));
        confirmarRespostaButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarRespostaButton.setForeground(new java.awt.Color(255, 255, 255));
        confirmarRespostaButton.setText("Confirmar");
        confirmarRespostaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarRespostaButtonActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(226, 211, 191));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 191, 171), 5, true));

        conteudoCartaLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        conteudoCartaLabel2.setForeground(new java.awt.Color(0, 0, 0));
        conteudoCartaLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        conteudoCartaLabel2.setText("jLabel7");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(conteudoCartaLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(conteudoCartaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119))
        );

        inserirRespostaTF.setBackground(new java.awt.Color(28, 181, 196));
        inserirRespostaTF.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        inserirRespostaTF.setForeground(new java.awt.Color(0, 0, 0));
        inserirRespostaTF.setPreferredSize(new java.awt.Dimension(190, 38));
        inserirRespostaTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirRespostaTFActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Insira sua resposta");

        mensagemRespostaInvalida.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        mensagemRespostaInvalida.setForeground(new java.awt.Color(28, 181, 196));
        mensagemRespostaInvalida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout painelJogoInserirLayout = new javax.swing.GroupLayout(painelJogoInserir);
        painelJogoInserir.setLayout(painelJogoInserirLayout);
        painelJogoInserirLayout.setHorizontalGroup(
            painelJogoInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelJogoInserirLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(painelJogoInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoInserirLayout.createSequentialGroup()
                        .addComponent(finalizarJogoButton2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(painelJogoInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoInserirLayout.createSequentialGroup()
                                .addComponent(pularCarta)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoInserirLayout.createSequentialGroup()
                                .addComponent(inserirRespostaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(125, 125, 125)))
                        .addComponent(mensagemRespostaInvalida, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoInserirLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoInserirLayout.createSequentialGroup()
                        .addComponent(confirmarRespostaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(156, 156, 156))))
        );
        painelJogoInserirLayout.setVerticalGroup(
            painelJogoInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoInserirLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(finalizarJogoButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inserirRespostaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmarRespostaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(painelJogoInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pularCarta)
                    .addComponent(mensagemRespostaInvalida, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(painelJogoInserir, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        painelJogoInserir1.setBackground(new java.awt.Color(246, 231, 211));

        finalizarJogoButton3.setBackground(new java.awt.Color(237, 30, 82));
        finalizarJogoButton3.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        finalizarJogoButton3.setForeground(new java.awt.Color(255, 255, 255));
        finalizarJogoButton3.setText("Finalizar");
        finalizarJogoButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarJogoButton3ActionPerformed(evt);
            }
        });

        pularCarta1.setBackground(new java.awt.Color(237, 30, 82));
        pularCarta1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        pularCarta1.setForeground(new java.awt.Color(255, 255, 255));
        pularCarta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Proxima Carta.png"))); // NOI18N
        pularCarta1.setBorder(null);
        pularCarta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pularCarta1ActionPerformed(evt);
            }
        });

        confirmarRespostaButton1.setBackground(new java.awt.Color(237, 30, 82));
        confirmarRespostaButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarRespostaButton1.setForeground(new java.awt.Color(255, 255, 255));
        confirmarRespostaButton1.setText("Confirmar");
        confirmarRespostaButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarRespostaButton1ActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(226, 211, 191));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(206, 191, 171), 5, true));

        conteudoCartaLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        conteudoCartaLabel3.setForeground(new java.awt.Color(0, 0, 0));
        conteudoCartaLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        conteudoCartaLabel3.setText("jLabel7");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(conteudoCartaLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(conteudoCartaLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119))
        );

        inserirRespostaTF1.setBackground(new java.awt.Color(28, 181, 196));
        inserirRespostaTF1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        inserirRespostaTF1.setForeground(new java.awt.Color(0, 0, 0));
        inserirRespostaTF1.setPreferredSize(new java.awt.Dimension(190, 38));
        inserirRespostaTF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirRespostaTF1ActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Insira sua resposta");

        mensagemRespostaInvalida1.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        mensagemRespostaInvalida1.setForeground(new java.awt.Color(28, 181, 196));
        mensagemRespostaInvalida1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout painelJogoInserir1Layout = new javax.swing.GroupLayout(painelJogoInserir1);
        painelJogoInserir1.setLayout(painelJogoInserir1Layout);
        painelJogoInserir1Layout.setHorizontalGroup(
            painelJogoInserir1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelJogoInserir1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(painelJogoInserir1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoInserir1Layout.createSequentialGroup()
                        .addComponent(finalizarJogoButton3)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoInserir1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(painelJogoInserir1Layout.createSequentialGroup()
                            .addComponent(mensagemRespostaInvalida1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pularCarta1)
                            .addContainerGap())
                        .addGroup(painelJogoInserir1Layout.createSequentialGroup()
                            .addGroup(painelJogoInserir1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(inserirRespostaTF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(125, 125, 125)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoInserir1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoInserir1Layout.createSequentialGroup()
                        .addComponent(confirmarRespostaButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(156, 156, 156))))
        );
        painelJogoInserir1Layout.setVerticalGroup(
            painelJogoInserir1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoInserir1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(finalizarJogoButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inserirRespostaTF1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmarRespostaButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(painelJogoInserir1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pularCarta1)
                    .addComponent(mensagemRespostaInvalida1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(painelJogoInserir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        editarUsuarioPainel.setBackground(new java.awt.Color(246, 231, 211));

        editarNomeButton.setBackground(new java.awt.Color(237, 30, 82));
        editarNomeButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        editarNomeButton.setForeground(new java.awt.Color(255, 255, 255));
        editarNomeButton.setText("Editar nome");
        editarNomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarNomeButtonActionPerformed(evt);
            }
        });

        editarEmailButton.setBackground(new java.awt.Color(237, 30, 82));
        editarEmailButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        editarEmailButton.setForeground(new java.awt.Color(255, 255, 255));
        editarEmailButton.setText("Editar email");
        editarEmailButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarEmailButtonActionPerformed(evt);
            }
        });

        voltarPainelInicialButton2.setBackground(new java.awt.Color(237, 30, 82));
        voltarPainelInicialButton2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarPainelInicialButton2.setForeground(new java.awt.Color(255, 255, 255));
        voltarPainelInicialButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarPainelInicialButton2.setBorder(null);
        voltarPainelInicialButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarPainelInicialButton2ActionPerformed(evt);
            }
        });

        editarSenhaButton.setBackground(new java.awt.Color(237, 30, 82));
        editarSenhaButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        editarSenhaButton.setForeground(new java.awt.Color(255, 255, 255));
        editarSenhaButton.setText("Editar senha");
        editarSenhaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarSenhaButtonActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Editar usuário");

        excluirContaButton.setBackground(new java.awt.Color(237, 30, 82));
        excluirContaButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        excluirContaButton.setForeground(new java.awt.Color(255, 255, 255));
        excluirContaButton.setText("Excluir conta");
        excluirContaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirContaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout editarUsuarioPainelLayout = new javax.swing.GroupLayout(editarUsuarioPainel);
        editarUsuarioPainel.setLayout(editarUsuarioPainelLayout);
        editarUsuarioPainelLayout.setHorizontalGroup(
            editarUsuarioPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarUsuarioPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarPainelInicialButton2)
                .addContainerGap(405, Short.MAX_VALUE))
            .addGroup(editarUsuarioPainelLayout.createSequentialGroup()
                .addContainerGap(141, Short.MAX_VALUE)
                .addGroup(editarUsuarioPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(editarNomeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editarEmailButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editarSenhaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(excluirContaButton))
                .addGap(153, 153, 153))
            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        editarUsuarioPainelLayout.setVerticalGroup(
            editarUsuarioPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editarUsuarioPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarPainelInicialButton2)
                .addGap(18, 18, 18)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(editarNomeButton)
                .addGap(18, 18, 18)
                .addComponent(editarEmailButton)
                .addGap(18, 18, 18)
                .addComponent(editarSenhaButton)
                .addGap(18, 18, 18)
                .addComponent(excluirContaButton)
                .addContainerGap(127, Short.MAX_VALUE))
        );

        getContentPane().add(editarUsuarioPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        editarNomeUsuarioPainel.setBackground(new java.awt.Color(246, 231, 211));
        editarNomeUsuarioPainel.setForeground(new java.awt.Color(255, 255, 255));

        confirmarEditarNomeUsuario.setBackground(new java.awt.Color(237, 30, 82));
        confirmarEditarNomeUsuario.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarEditarNomeUsuario.setForeground(new java.awt.Color(255, 255, 255));
        confirmarEditarNomeUsuario.setText("Confirmar");
        confirmarEditarNomeUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarEditarNomeUsuarioActionPerformed(evt);
            }
        });

        nomeUsuarioTF.setBackground(new java.awt.Color(28, 181, 196));
        nomeUsuarioTF.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        nomeUsuarioTF.setForeground(new java.awt.Color(0, 0, 0));
        nomeUsuarioTF.setPreferredSize(new java.awt.Dimension(190, 38));

        voltarEditarUsuario.setBackground(new java.awt.Color(237, 30, 82));
        voltarEditarUsuario.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarEditarUsuario.setForeground(new java.awt.Color(255, 255, 255));
        voltarEditarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarEditarUsuario.setBorder(null);
        voltarEditarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarEditarUsuarioActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Editar usuário");

        jLabel37.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Nome");

        mensagemEditarNomeUsuarioInvalido.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        mensagemEditarNomeUsuarioInvalido.setForeground(new java.awt.Color(28, 181, 196));
        mensagemEditarNomeUsuarioInvalido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout editarNomeUsuarioPainelLayout = new javax.swing.GroupLayout(editarNomeUsuarioPainel);
        editarNomeUsuarioPainel.setLayout(editarNomeUsuarioPainelLayout);
        editarNomeUsuarioPainelLayout.setHorizontalGroup(
            editarNomeUsuarioPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarNomeUsuarioPainelLayout.createSequentialGroup()
                .addGroup(editarNomeUsuarioPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editarNomeUsuarioPainelLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(editarNomeUsuarioPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editarNomeUsuarioPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nomeUsuarioTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(mensagemEditarNomeUsuarioInvalido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(editarNomeUsuarioPainelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(voltarEditarUsuario)))
                .addGap(0, 125, Short.MAX_VALUE))
            .addGroup(editarNomeUsuarioPainelLayout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(confirmarEditarNomeUsuario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        editarNomeUsuarioPainelLayout.setVerticalGroup(
            editarNomeUsuarioPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarNomeUsuarioPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarEditarUsuario)
                .addGap(58, 58, 58)
                .addComponent(jLabel36)
                .addGap(44, 44, 44)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomeUsuarioTF, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mensagemEditarNomeUsuarioInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(confirmarEditarNomeUsuario)
                .addGap(116, 116, 116))
        );

        getContentPane().add(editarNomeUsuarioPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        editarEmailUsuarioPainel.setBackground(new java.awt.Color(246, 231, 211));
        editarEmailUsuarioPainel.setForeground(new java.awt.Color(255, 255, 255));

        confirmarEditarEmailUsuario.setBackground(new java.awt.Color(237, 30, 82));
        confirmarEditarEmailUsuario.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarEditarEmailUsuario.setForeground(new java.awt.Color(255, 255, 255));
        confirmarEditarEmailUsuario.setText("Confirmar");
        confirmarEditarEmailUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarEditarEmailUsuarioActionPerformed(evt);
            }
        });

        emailUsuarioTF.setBackground(new java.awt.Color(28, 181, 196));
        emailUsuarioTF.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        emailUsuarioTF.setForeground(new java.awt.Color(0, 0, 0));
        emailUsuarioTF.setPreferredSize(new java.awt.Dimension(190, 38));
        emailUsuarioTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailUsuarioTFActionPerformed(evt);
            }
        });

        voltarEditarUsuario1.setBackground(new java.awt.Color(237, 30, 82));
        voltarEditarUsuario1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarEditarUsuario1.setForeground(new java.awt.Color(255, 255, 255));
        voltarEditarUsuario1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarEditarUsuario1.setBorder(null);
        voltarEditarUsuario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarEditarUsuario1ActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Editar usuário");

        jLabel39.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Email");

        mensagemEditarEmailInvalido.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        mensagemEditarEmailInvalido.setForeground(new java.awt.Color(28, 181, 196));
        mensagemEditarEmailInvalido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout editarEmailUsuarioPainelLayout = new javax.swing.GroupLayout(editarEmailUsuarioPainel);
        editarEmailUsuarioPainel.setLayout(editarEmailUsuarioPainelLayout);
        editarEmailUsuarioPainelLayout.setHorizontalGroup(
            editarEmailUsuarioPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarEmailUsuarioPainelLayout.createSequentialGroup()
                .addGroup(editarEmailUsuarioPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(editarEmailUsuarioPainelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(mensagemEditarEmailInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, editarEmailUsuarioPainelLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(editarEmailUsuarioPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(emailUsuarioTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 125, Short.MAX_VALUE))
            .addGroup(editarEmailUsuarioPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarEditarUsuario1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editarEmailUsuarioPainelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(confirmarEditarEmailUsuario)
                .addGap(161, 161, 161))
        );
        editarEmailUsuarioPainelLayout.setVerticalGroup(
            editarEmailUsuarioPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarEmailUsuarioPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarEditarUsuario1)
                .addGap(58, 58, 58)
                .addComponent(jLabel38)
                .addGap(44, 44, 44)
                .addComponent(jLabel39)
                .addGap(18, 18, 18)
                .addComponent(emailUsuarioTF, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mensagemEditarEmailInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmarEditarEmailUsuario)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        getContentPane().add(editarEmailUsuarioPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        editarSenhaPainel.setBackground(new java.awt.Color(246, 231, 211));
        editarSenhaPainel.setForeground(new java.awt.Color(255, 255, 255));
        editarSenhaPainel.setPreferredSize(new java.awt.Dimension(440, 440));

        confirmarEditarSenha.setBackground(new java.awt.Color(237, 30, 82));
        confirmarEditarSenha.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarEditarSenha.setForeground(new java.awt.Color(255, 255, 255));
        confirmarEditarSenha.setText("Confirmar");
        confirmarEditarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarEditarSenhaActionPerformed(evt);
            }
        });

        voltarEditarUsuario2.setBackground(new java.awt.Color(237, 30, 82));
        voltarEditarUsuario2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarEditarUsuario2.setForeground(new java.awt.Color(255, 255, 255));
        voltarEditarUsuario2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarEditarUsuario2.setBorder(null);
        voltarEditarUsuario2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarEditarUsuario2ActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Editar usuário");

        jLabel41.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("Senha antiga");

        mensagemEditarSenhaInvalido.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        mensagemEditarSenhaInvalido.setForeground(new java.awt.Color(28, 181, 196));
        mensagemEditarSenhaInvalido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel42.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("Senha nova");

        senhaVelhaPF.setBackground(new java.awt.Color(28, 181, 196));
        senhaVelhaPF.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        senhaVelhaPF.setPreferredSize(new java.awt.Dimension(190, 35));
        senhaVelhaPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                senhaVelhaPFActionPerformed(evt);
            }
        });

        senhaNovaPF.setBackground(new java.awt.Color(28, 181, 196));
        senhaNovaPF.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        senhaNovaPF.setPreferredSize(new java.awt.Dimension(190, 35));
        senhaNovaPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                senhaNovaPFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout editarSenhaPainelLayout = new javax.swing.GroupLayout(editarSenhaPainel);
        editarSenhaPainel.setLayout(editarSenhaPainelLayout);
        editarSenhaPainelLayout.setHorizontalGroup(
            editarSenhaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editarSenhaPainelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(confirmarEditarSenha)
                .addGap(162, 162, 162))
            .addGroup(editarSenhaPainelLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(editarSenhaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mensagemEditarSenhaInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(editarSenhaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(senhaVelhaPF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(senhaNovaPF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 125, Short.MAX_VALUE))
            .addGroup(editarSenhaPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarEditarUsuario2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        editarSenhaPainelLayout.setVerticalGroup(
            editarSenhaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarSenhaPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarEditarUsuario2)
                .addGap(58, 58, 58)
                .addComponent(jLabel40)
                .addGap(44, 44, 44)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(senhaVelhaPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(senhaNovaPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mensagemEditarSenhaInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmarEditarSenha)
                .addGap(80, 80, 80))
        );

        getContentPane().add(editarSenhaPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        importarBaralhoPainel.setBackground(new java.awt.Color(246, 231, 211));
        importarBaralhoPainel.setForeground(new java.awt.Color(255, 255, 255));

        confirmarImportarBaralhoButton.setBackground(new java.awt.Color(237, 30, 82));
        confirmarImportarBaralhoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarImportarBaralhoButton.setForeground(new java.awt.Color(255, 255, 255));
        confirmarImportarBaralhoButton.setText("Confirmar");
        confirmarImportarBaralhoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarImportarBaralhoButtonActionPerformed(evt);
            }
        });

        codigoBaralhoTF.setBackground(new java.awt.Color(28, 181, 196));
        codigoBaralhoTF.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        codigoBaralhoTF.setForeground(new java.awt.Color(0, 0, 0));
        codigoBaralhoTF.setPreferredSize(new java.awt.Dimension(190, 38));
        codigoBaralhoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoBaralhoTFActionPerformed(evt);
            }
        });

        voltarCriarBaralhoButton.setBackground(new java.awt.Color(237, 30, 82));
        voltarCriarBaralhoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarCriarBaralhoButton.setForeground(new java.awt.Color(255, 255, 255));
        voltarCriarBaralhoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarCriarBaralhoButton.setBorder(null);
        voltarCriarBaralhoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarCriarBaralhoButtonActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Importar baralho");

        jLabel44.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Digite o código do baralho");

        mensagemImportarBaralhoInvalido.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        mensagemImportarBaralhoInvalido.setForeground(new java.awt.Color(28, 181, 196));
        mensagemImportarBaralhoInvalido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout importarBaralhoPainelLayout = new javax.swing.GroupLayout(importarBaralhoPainel);
        importarBaralhoPainel.setLayout(importarBaralhoPainelLayout);
        importarBaralhoPainelLayout.setHorizontalGroup(
            importarBaralhoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(importarBaralhoPainelLayout.createSequentialGroup()
                .addGroup(importarBaralhoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(importarBaralhoPainelLayout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(importarBaralhoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(importarBaralhoPainelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(voltarCriarBaralhoButton))
                    .addGroup(importarBaralhoPainelLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(importarBaralhoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(mensagemImportarBaralhoInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(codigoBaralhoTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(importarBaralhoPainelLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(confirmarImportarBaralhoButton)))
                .addGap(103, 103, 103))
        );
        importarBaralhoPainelLayout.setVerticalGroup(
            importarBaralhoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(importarBaralhoPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarCriarBaralhoButton)
                .addGap(62, 62, 62)
                .addComponent(jLabel43)
                .addGap(52, 52, 52)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(codigoBaralhoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(mensagemImportarBaralhoInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmarImportarBaralhoButton)
                .addContainerGap(127, Short.MAX_VALUE))
        );

        getContentPane().add(importarBaralhoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        confirmarSairJogoPainel.setBackground(new java.awt.Color(246, 231, 211));

        mensagemDesejaExcluirBaralho1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        mensagemDesejaExcluirBaralho1.setForeground(new java.awt.Color(0, 0, 0));
        mensagemDesejaExcluirBaralho1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensagemDesejaExcluirBaralho1.setText("Tem certeza que deseja sair?");

        confirmarExcluirBaralhoButton1.setBackground(new java.awt.Color(237, 30, 82));
        confirmarExcluirBaralhoButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarExcluirBaralhoButton1.setForeground(new java.awt.Color(255, 255, 255));
        confirmarExcluirBaralhoButton1.setText("Sim, sair");
        confirmarExcluirBaralhoButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarExcluirBaralhoButton1ActionPerformed(evt);
            }
        });

        voltarMeusBaralhosButton3.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusBaralhosButton3.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusBaralhosButton3.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusBaralhosButton3.setText("Não, voltar");
        voltarMeusBaralhosButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMeusBaralhosButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout confirmarSairJogoPainelLayout = new javax.swing.GroupLayout(confirmarSairJogoPainel);
        confirmarSairJogoPainel.setLayout(confirmarSairJogoPainelLayout);
        confirmarSairJogoPainelLayout.setHorizontalGroup(
            confirmarSairJogoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mensagemDesejaExcluirBaralho1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
            .addGroup(confirmarSairJogoPainelLayout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addGroup(confirmarSairJogoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(confirmarExcluirBaralhoButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(voltarMeusBaralhosButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        confirmarSairJogoPainelLayout.setVerticalGroup(
            confirmarSairJogoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmarSairJogoPainelLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(mensagemDesejaExcluirBaralho1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(confirmarExcluirBaralhoButton1)
                .addGap(34, 34, 34)
                .addComponent(voltarMeusBaralhosButton3)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        getContentPane().add(confirmarSairJogoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        confirmarExcluirGrupoPainel.setBackground(new java.awt.Color(246, 231, 211));

        mensagemDesejaExcluirBaralho2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        mensagemDesejaExcluirBaralho2.setForeground(new java.awt.Color(0, 0, 0));
        mensagemDesejaExcluirBaralho2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensagemDesejaExcluirBaralho2.setText("Tem certeza que deseja excluir esse grupo?");

        confirmarExcluirBaralhoButton2.setBackground(new java.awt.Color(237, 30, 82));
        confirmarExcluirBaralhoButton2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarExcluirBaralhoButton2.setForeground(new java.awt.Color(255, 255, 255));
        confirmarExcluirBaralhoButton2.setText("Sim, excluir");
        confirmarExcluirBaralhoButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarExcluirBaralhoButton2ActionPerformed(evt);
            }
        });

        voltarMeusBaralhosButton4.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusBaralhosButton4.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusBaralhosButton4.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusBaralhosButton4.setText("Não, voltar");
        voltarMeusBaralhosButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMeusBaralhosButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout confirmarExcluirGrupoPainelLayout = new javax.swing.GroupLayout(confirmarExcluirGrupoPainel);
        confirmarExcluirGrupoPainel.setLayout(confirmarExcluirGrupoPainelLayout);
        confirmarExcluirGrupoPainelLayout.setHorizontalGroup(
            confirmarExcluirGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mensagemDesejaExcluirBaralho2, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
            .addGroup(confirmarExcluirGrupoPainelLayout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addGroup(confirmarExcluirGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(confirmarExcluirBaralhoButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(voltarMeusBaralhosButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        confirmarExcluirGrupoPainelLayout.setVerticalGroup(
            confirmarExcluirGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmarExcluirGrupoPainelLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(mensagemDesejaExcluirBaralho2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(confirmarExcluirBaralhoButton2)
                .addGap(34, 34, 34)
                .addComponent(voltarMeusBaralhosButton4)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        getContentPane().add(confirmarExcluirGrupoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        confirmarExcluirCartaPainel.setBackground(new java.awt.Color(246, 231, 211));

        mensagemDesejaExcluirBaralho3.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        mensagemDesejaExcluirBaralho3.setForeground(new java.awt.Color(0, 0, 0));
        mensagemDesejaExcluirBaralho3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensagemDesejaExcluirBaralho3.setText("Tem certeza que deseja excluir esse card?");

        confirmarExcluirBaralhoButton3.setBackground(new java.awt.Color(237, 30, 82));
        confirmarExcluirBaralhoButton3.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarExcluirBaralhoButton3.setForeground(new java.awt.Color(255, 255, 255));
        confirmarExcluirBaralhoButton3.setText("Sim, excluir");
        confirmarExcluirBaralhoButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarExcluirBaralhoButton3ActionPerformed(evt);
            }
        });

        voltarMeusBaralhosButton5.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusBaralhosButton5.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusBaralhosButton5.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusBaralhosButton5.setText("Não, voltar");
        voltarMeusBaralhosButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMeusBaralhosButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout confirmarExcluirCartaPainelLayout = new javax.swing.GroupLayout(confirmarExcluirCartaPainel);
        confirmarExcluirCartaPainel.setLayout(confirmarExcluirCartaPainelLayout);
        confirmarExcluirCartaPainelLayout.setHorizontalGroup(
            confirmarExcluirCartaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mensagemDesejaExcluirBaralho3, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
            .addGroup(confirmarExcluirCartaPainelLayout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addGroup(confirmarExcluirCartaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(confirmarExcluirBaralhoButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(voltarMeusBaralhosButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        confirmarExcluirCartaPainelLayout.setVerticalGroup(
            confirmarExcluirCartaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmarExcluirCartaPainelLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(mensagemDesejaExcluirBaralho3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(confirmarExcluirBaralhoButton3)
                .addGap(34, 34, 34)
                .addComponent(voltarMeusBaralhosButton5)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        getContentPane().add(confirmarExcluirCartaPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        confirmarExcluirAlunoGrupoPainel.setBackground(new java.awt.Color(246, 231, 211));

        mensagemDesejaExcluirBaralho4.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        mensagemDesejaExcluirBaralho4.setForeground(new java.awt.Color(0, 0, 0));
        mensagemDesejaExcluirBaralho4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensagemDesejaExcluirBaralho4.setText("Tem certeza que deseja remover esse aluno?");

        confirmarExcluirBaralhoButton4.setBackground(new java.awt.Color(237, 30, 82));
        confirmarExcluirBaralhoButton4.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarExcluirBaralhoButton4.setForeground(new java.awt.Color(255, 255, 255));
        confirmarExcluirBaralhoButton4.setText("Sim, remover");
        confirmarExcluirBaralhoButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarExcluirBaralhoButton4ActionPerformed(evt);
            }
        });

        voltarMeusBaralhosButton6.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusBaralhosButton6.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusBaralhosButton6.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusBaralhosButton6.setText("Não, voltar");
        voltarMeusBaralhosButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMeusBaralhosButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout confirmarExcluirAlunoGrupoPainelLayout = new javax.swing.GroupLayout(confirmarExcluirAlunoGrupoPainel);
        confirmarExcluirAlunoGrupoPainel.setLayout(confirmarExcluirAlunoGrupoPainelLayout);
        confirmarExcluirAlunoGrupoPainelLayout.setHorizontalGroup(
            confirmarExcluirAlunoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mensagemDesejaExcluirBaralho4, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
            .addGroup(confirmarExcluirAlunoGrupoPainelLayout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addGroup(confirmarExcluirAlunoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(confirmarExcluirBaralhoButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(voltarMeusBaralhosButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        confirmarExcluirAlunoGrupoPainelLayout.setVerticalGroup(
            confirmarExcluirAlunoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmarExcluirAlunoGrupoPainelLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(mensagemDesejaExcluirBaralho4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(confirmarExcluirBaralhoButton4)
                .addGap(34, 34, 34)
                .addComponent(voltarMeusBaralhosButton6)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        getContentPane().add(confirmarExcluirAlunoGrupoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        confirmarExcluirCartaGrupoPainel.setBackground(new java.awt.Color(246, 231, 211));

        mensagemDesejaExcluirBaralho5.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        mensagemDesejaExcluirBaralho5.setForeground(new java.awt.Color(0, 0, 0));
        mensagemDesejaExcluirBaralho5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensagemDesejaExcluirBaralho5.setText("Tem certeza que deseja excluir esse card?");

        confirmarExcluirBaralhoButton5.setBackground(new java.awt.Color(237, 30, 82));
        confirmarExcluirBaralhoButton5.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarExcluirBaralhoButton5.setForeground(new java.awt.Color(255, 255, 255));
        confirmarExcluirBaralhoButton5.setText("Sim, excluir");
        confirmarExcluirBaralhoButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarExcluirBaralhoButton5ActionPerformed(evt);
            }
        });

        voltarMeusBaralhosButton7.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusBaralhosButton7.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusBaralhosButton7.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusBaralhosButton7.setText("Não, voltar");
        voltarMeusBaralhosButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMeusBaralhosButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout confirmarExcluirCartaGrupoPainelLayout = new javax.swing.GroupLayout(confirmarExcluirCartaGrupoPainel);
        confirmarExcluirCartaGrupoPainel.setLayout(confirmarExcluirCartaGrupoPainelLayout);
        confirmarExcluirCartaGrupoPainelLayout.setHorizontalGroup(
            confirmarExcluirCartaGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mensagemDesejaExcluirBaralho5, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
            .addGroup(confirmarExcluirCartaGrupoPainelLayout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addGroup(confirmarExcluirCartaGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(confirmarExcluirBaralhoButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(voltarMeusBaralhosButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        confirmarExcluirCartaGrupoPainelLayout.setVerticalGroup(
            confirmarExcluirCartaGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmarExcluirCartaGrupoPainelLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(mensagemDesejaExcluirBaralho5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(confirmarExcluirBaralhoButton5)
                .addGap(34, 34, 34)
                .addComponent(voltarMeusBaralhosButton7)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        getContentPane().add(confirmarExcluirCartaGrupoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        confirmarExcluirBaralhoGrupoPainel.setBackground(new java.awt.Color(246, 231, 211));

        mensagemDesejaExcluirBaralho6.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        mensagemDesejaExcluirBaralho6.setForeground(new java.awt.Color(0, 0, 0));
        mensagemDesejaExcluirBaralho6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensagemDesejaExcluirBaralho6.setText("Tem certeza que deseja excluir esse baralho?");

        confirmarExcluirBaralhoButton6.setBackground(new java.awt.Color(237, 30, 82));
        confirmarExcluirBaralhoButton6.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarExcluirBaralhoButton6.setForeground(new java.awt.Color(255, 255, 255));
        confirmarExcluirBaralhoButton6.setText("Sim, excluir");
        confirmarExcluirBaralhoButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarExcluirBaralhoButton6ActionPerformed(evt);
            }
        });

        voltarMeusBaralhosButton8.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusBaralhosButton8.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusBaralhosButton8.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusBaralhosButton8.setText("Não, voltar");
        voltarMeusBaralhosButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMeusBaralhosButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout confirmarExcluirBaralhoGrupoPainelLayout = new javax.swing.GroupLayout(confirmarExcluirBaralhoGrupoPainel);
        confirmarExcluirBaralhoGrupoPainel.setLayout(confirmarExcluirBaralhoGrupoPainelLayout);
        confirmarExcluirBaralhoGrupoPainelLayout.setHorizontalGroup(
            confirmarExcluirBaralhoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mensagemDesejaExcluirBaralho6, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
            .addGroup(confirmarExcluirBaralhoGrupoPainelLayout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addGroup(confirmarExcluirBaralhoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(confirmarExcluirBaralhoButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(voltarMeusBaralhosButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        confirmarExcluirBaralhoGrupoPainelLayout.setVerticalGroup(
            confirmarExcluirBaralhoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmarExcluirBaralhoGrupoPainelLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(mensagemDesejaExcluirBaralho6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(confirmarExcluirBaralhoButton6)
                .addGap(34, 34, 34)
                .addComponent(voltarMeusBaralhosButton8)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        getContentPane().add(confirmarExcluirBaralhoGrupoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        confirmarExcluirContaPainel.setBackground(new java.awt.Color(246, 231, 211));

        mensagemDesejaExcluirBaralho7.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        mensagemDesejaExcluirBaralho7.setForeground(new java.awt.Color(0, 0, 0));
        mensagemDesejaExcluirBaralho7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensagemDesejaExcluirBaralho7.setText("Tem certeza que deseja excluir sua conta?");

        confirmarExcluirBaralhoButton7.setBackground(new java.awt.Color(237, 30, 82));
        confirmarExcluirBaralhoButton7.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarExcluirBaralhoButton7.setForeground(new java.awt.Color(255, 255, 255));
        confirmarExcluirBaralhoButton7.setText("Sim, excluir");
        confirmarExcluirBaralhoButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarExcluirBaralhoButton7ActionPerformed(evt);
            }
        });

        voltarMeusBaralhosButton9.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusBaralhosButton9.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusBaralhosButton9.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusBaralhosButton9.setText("Não, voltar");
        voltarMeusBaralhosButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMeusBaralhosButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout confirmarExcluirContaPainelLayout = new javax.swing.GroupLayout(confirmarExcluirContaPainel);
        confirmarExcluirContaPainel.setLayout(confirmarExcluirContaPainelLayout);
        confirmarExcluirContaPainelLayout.setHorizontalGroup(
            confirmarExcluirContaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mensagemDesejaExcluirBaralho7, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
            .addGroup(confirmarExcluirContaPainelLayout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addGroup(confirmarExcluirContaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(confirmarExcluirBaralhoButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(voltarMeusBaralhosButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        confirmarExcluirContaPainelLayout.setVerticalGroup(
            confirmarExcluirContaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmarExcluirContaPainelLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(mensagemDesejaExcluirBaralho7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(confirmarExcluirBaralhoButton7)
                .addGap(34, 34, 34)
                .addComponent(voltarMeusBaralhosButton9)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        getContentPane().add(confirmarExcluirContaPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        excluirContaPainel.setBackground(new java.awt.Color(246, 231, 211));
        excluirContaPainel.setForeground(new java.awt.Color(255, 255, 255));

        confirmarExcluirUsuarioButton.setBackground(new java.awt.Color(237, 30, 82));
        confirmarExcluirUsuarioButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarExcluirUsuarioButton.setForeground(new java.awt.Color(255, 255, 255));
        confirmarExcluirUsuarioButton.setText("Confirmar");
        confirmarExcluirUsuarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarExcluirUsuarioButtonActionPerformed(evt);
            }
        });

        emailUsuarioParaExcluirTF.setBackground(new java.awt.Color(28, 181, 196));
        emailUsuarioParaExcluirTF.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        emailUsuarioParaExcluirTF.setForeground(new java.awt.Color(0, 0, 0));
        emailUsuarioParaExcluirTF.setPreferredSize(new java.awt.Dimension(190, 38));

        voltarEditarGrupoAcoesButton3.setBackground(new java.awt.Color(237, 30, 82));
        voltarEditarGrupoAcoesButton3.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarEditarGrupoAcoesButton3.setForeground(new java.awt.Color(255, 255, 255));
        voltarEditarGrupoAcoesButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Voltar.png"))); // NOI18N
        voltarEditarGrupoAcoesButton3.setBorder(null);
        voltarEditarGrupoAcoesButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarEditarGrupoAcoesButton3ActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Excluir conta");

        jLabel46.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Insira o email");

        mensagemEmailInvalido.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        mensagemEmailInvalido.setForeground(new java.awt.Color(28, 181, 196));
        mensagemEmailInvalido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout excluirContaPainelLayout = new javax.swing.GroupLayout(excluirContaPainel);
        excluirContaPainel.setLayout(excluirContaPainelLayout);
        excluirContaPainelLayout.setHorizontalGroup(
            excluirContaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(excluirContaPainelLayout.createSequentialGroup()
                .addGroup(excluirContaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(excluirContaPainelLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(excluirContaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(excluirContaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(emailUsuarioParaExcluirTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(mensagemEmailInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(excluirContaPainelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(voltarEditarGrupoAcoesButton3)))
                .addGap(0, 125, Short.MAX_VALUE))
            .addGroup(excluirContaPainelLayout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addComponent(confirmarExcluirUsuarioButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        excluirContaPainelLayout.setVerticalGroup(
            excluirContaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(excluirContaPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(voltarEditarGrupoAcoesButton3)
                .addGap(58, 58, 58)
                .addComponent(jLabel45)
                .addGap(44, 44, 44)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailUsuarioParaExcluirTF, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mensagemEmailInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(confirmarExcluirUsuarioButton)
                .addGap(113, 113, 113))
        );

        getContentPane().add(excluirContaPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        confirmarExcluirUsuarioPainel.setBackground(new java.awt.Color(246, 231, 211));

        mensagemDesejaExcluirBaralho8.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        mensagemDesejaExcluirBaralho8.setForeground(new java.awt.Color(0, 0, 0));
        mensagemDesejaExcluirBaralho8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensagemDesejaExcluirBaralho8.setText("Tem certeza que deseja excluir essa conta?");

        confirmarExcluirBaralhoButton8.setBackground(new java.awt.Color(237, 30, 82));
        confirmarExcluirBaralhoButton8.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarExcluirBaralhoButton8.setForeground(new java.awt.Color(255, 255, 255));
        confirmarExcluirBaralhoButton8.setText("Sim, excluir");
        confirmarExcluirBaralhoButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarExcluirBaralhoButton8ActionPerformed(evt);
            }
        });

        voltarMeusBaralhosButton10.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusBaralhosButton10.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusBaralhosButton10.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusBaralhosButton10.setText("Não, voltar");
        voltarMeusBaralhosButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMeusBaralhosButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout confirmarExcluirUsuarioPainelLayout = new javax.swing.GroupLayout(confirmarExcluirUsuarioPainel);
        confirmarExcluirUsuarioPainel.setLayout(confirmarExcluirUsuarioPainelLayout);
        confirmarExcluirUsuarioPainelLayout.setHorizontalGroup(
            confirmarExcluirUsuarioPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mensagemDesejaExcluirBaralho8, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
            .addGroup(confirmarExcluirUsuarioPainelLayout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addGroup(confirmarExcluirUsuarioPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(confirmarExcluirBaralhoButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(voltarMeusBaralhosButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        confirmarExcluirUsuarioPainelLayout.setVerticalGroup(
            confirmarExcluirUsuarioPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmarExcluirUsuarioPainelLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(mensagemDesejaExcluirBaralho8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(confirmarExcluirBaralhoButton8)
                .addGap(34, 34, 34)
                .addComponent(voltarMeusBaralhosButton10)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        getContentPane().add(confirmarExcluirUsuarioPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        confirmarSairPainel.setBackground(new java.awt.Color(246, 231, 211));

        mensagemDesejaExcluirBaralho9.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        mensagemDesejaExcluirBaralho9.setForeground(new java.awt.Color(0, 0, 0));
        mensagemDesejaExcluirBaralho9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mensagemDesejaExcluirBaralho9.setText("Tem certeza que deseja fechar o programa?");

        confirmarExcluirBaralhoButton9.setBackground(new java.awt.Color(237, 30, 82));
        confirmarExcluirBaralhoButton9.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarExcluirBaralhoButton9.setForeground(new java.awt.Color(255, 255, 255));
        confirmarExcluirBaralhoButton9.setText("Sim, fechar");
        confirmarExcluirBaralhoButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarExcluirBaralhoButton9ActionPerformed(evt);
            }
        });

        voltarMeusBaralhosButton11.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusBaralhosButton11.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusBaralhosButton11.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusBaralhosButton11.setText("Não, voltar");
        voltarMeusBaralhosButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMeusBaralhosButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout confirmarSairPainelLayout = new javax.swing.GroupLayout(confirmarSairPainel);
        confirmarSairPainel.setLayout(confirmarSairPainelLayout);
        confirmarSairPainelLayout.setHorizontalGroup(
            confirmarSairPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mensagemDesejaExcluirBaralho9, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
            .addGroup(confirmarSairPainelLayout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addGroup(confirmarSairPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(confirmarExcluirBaralhoButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(voltarMeusBaralhosButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        confirmarSairPainelLayout.setVerticalGroup(
            confirmarSairPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmarSairPainelLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(mensagemDesejaExcluirBaralho9, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(confirmarExcluirBaralhoButton9)
                .addGap(34, 34, 34)
                .addComponent(voltarMeusBaralhosButton11)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        getContentPane().add(confirmarSairPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        excluirAlunoBotao.setBackground(new java.awt.Color(246, 231, 211));
        excluirAlunoBotao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/configuracoes botao_1.png"))); // NOI18N
        excluirAlunoBotao.setBorder(null);
        excluirAlunoBotao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirAlunoBotaoActionPerformed(evt);
            }
        });
        getContentPane().add(excluirAlunoBotao, new org.netbeans.lib.awtextra.AbsoluteConstraints(855, 10, -1, -1));

        editarUsuarioButton.setBackground(new java.awt.Color(246, 231, 211));
        editarUsuarioButton.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        editarUsuarioButton.setForeground(new java.awt.Color(28, 181, 196));
        editarUsuarioButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/usuario botao.png"))); // NOI18N
        editarUsuarioButton.setBorder(null);
        editarUsuarioButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editarUsuarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarUsuarioButtonActionPerformed(evt);
            }
        });
        getContentPane().add(editarUsuarioButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 42, 42));

        botaoMusica.setBackground(new java.awt.Color(35, 142, 104));
        botaoMusica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/som ativado.png"))); // NOI18N
        botaoMusica.setBorder(null);
        botaoMusica.setContentAreaFilled(false);
        botaoMusica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoMusicaActionPerformed(evt);
            }
        });
        getContentPane().add(botaoMusica, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 10, 42, 42));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/botao minimizar.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 10, -1, -1));

        botaoSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/sair button.png"))); // NOI18N
        botaoSair.setBorder(null);
        botaoSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSairActionPerformed(evt);
            }
        });
        getContentPane().add(botaoSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 10, -1, -1));

        jLabel47.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(28, 181, 196));
        jLabel47.setText("PoliFlashes");
        getContentPane().add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 50, -1, -1));

        labelFundo.setForeground(new java.awt.Color(168, 168, 168));
        labelFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Design sem nome.png"))); // NOI18N
        getContentPane().add(labelFundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 880));

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void criarContaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criarContaButtonActionPerformed
        // TODO add your handling code here:
        try {
            String email = campoEmailTextField.getText();
            String nomeUsuario = campoUsuarioTextField.getText();
            var pass = campoSenhaPasswordField.getPassword();
            String senha = new String(pass);
            String validar = validarDados(email, senha);
            String confirmarSenha = new String(campoConfirmarSenhaPasswordField.getPassword());
            if (validar.equals("erro")) {
                mensagemCriarContaInvalidaLabel.setText("Preencha todos os campos");
            } else if (validar.equals("email inválido")) {
                mensagemCriarContaInvalidaLabel.setText("Insira um email válido");
            } else {
                if (senha.equals(confirmarSenha)) {
                    int id = cadastrarUser(email, senha, nomeUsuario, validar);
                    BaralhoDAO.criarBaralhosAutomatico(id);
                    CardDAO.criarCartasAutomatica(id);
                    mensagemCriarContaInvalidaLabel.setText("Conta criada com sucesso!");
                    campoUsuarioTextField.setText("");
                    campoEmailTextField.setText("");
                    campoSenhaPasswordField.setText("");
                    campoConfirmarSenhaPasswordField.setText("");
                } else {
                    mensagemCriarContaInvalidaLabel.setText("As senhas precisam ser iguais");
                }
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            mensagemCriarContaInvalidaLabel.setText("Email ou usuário já registrados");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro inesperado");
        }
    }//GEN-LAST:event_criarContaButtonActionPerformed

    private void campoUsuarioTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoUsuarioTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoUsuarioTextFieldActionPerformed

    private void jaTenhoContaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jaTenhoContaButtonActionPerformed
        campoUsuarioTextField.setText("");
        campoEmailTextField.setText("");
        campoSenhaPasswordField.setText("");
        campoConfirmarSenhaPasswordField.setText("");
        mensagemCriarContaInvalidaLabel.setText("");
        criarContaPanel.setVisible(false);
        autenticarContaPanel.setVisible(true);
    }//GEN-LAST:event_jaTenhoContaButtonActionPerformed

    private void campoEmailAutenticarTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoEmailAutenticarTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoEmailAutenticarTextFieldActionPerformed

    private void EntrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EntrarButtonActionPerformed
        try {
            String email = campoEmailAutenticarTextField.getText();
            var pass = campoSenhaAutenticarPasswordField.getPassword();
            String senha = new String(pass);
            usuario = verificarUser(email, senha);
            if (usuario != null) {
                painelInicial.setVisible(true);
                autenticarContaPanel.setVisible(false);
                editarUsuarioButton.setVisible(true);
                listaDeBaralhos = listarBaralhos(usuario);
                if (usuario.getTipoUsuario().equals("aluno")) {
                    listaDeGrupos = GrupoDAO.listarGruposAlunos(usuario);
                } else if (usuario.getTipoUsuario().equals("professor")) {
                    listaDeGrupos = GrupoDAO.listarGruposProfessor(usuario);
                    excluirAlunoBotao.setVisible(true);
                }
                campoSenhaAutenticarPasswordField.setText("");
                campoEmailAutenticarTextField.setText("");
                dadosInvalidosMensagem.setText("");
            } else {
                dadosInvalidosMensagem.setText("insira dados válidos");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Tente novamente mais tarde!");
        }
    }//GEN-LAST:event_EntrarButtonActionPerformed

    private void campoSenhaAutenticarPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoSenhaAutenticarPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoSenhaAutenticarPasswordFieldActionPerformed

    private void redirecionarTelaCriarContaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redirecionarTelaCriarContaButtonActionPerformed
        campoSenhaAutenticarPasswordField.setText("");
        campoEmailAutenticarTextField.setText("");
        dadosInvalidosMensagem.setText("");
        autenticarContaPanel.setVisible(false);
        criarContaPanel.setVisible(true);
    }//GEN-LAST:event_redirecionarTelaCriarContaButtonActionPerformed

    private void abrirMeusBaralhosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirMeusBaralhosButtonActionPerformed
        editarUsuarioButton.setVisible(false);
        excluirAlunoBotao.setVisible(false);
        dtmBaralhos = (DefaultTableModel) meusBaralhosTable.getModel();
        dtmBaralhos.setRowCount(0);
        if (listaDeBaralhos.isEmpty()) {
            meusBaralhosLabel.setText("Você não tem baralhos criados!");
            meusBaralhosTable.getTableHeader().setVisible(false);
        } else {
            for (Baralho baralho : listaDeBaralhos) {
                Object[] linha = {
                    baralho.getNomeBaralho(),
                    baralho.getTema(), String.format("%.2f%%", baralho.getMediaDeAcertos()), (baralho.getTotalDeAcertos() + baralho.getTotalDeErros())};
                dtmBaralhos.addRow(linha);
            }
            ordenar = new TableRowSorter<>(dtmBaralhos);
            meusBaralhosTable.setRowSorter(ordenar);
            ArrayList<RowSorter.SortKey> chaveOrdenada = new ArrayList<>();
            chaveOrdenada.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
            ordenar.setSortKeys(chaveOrdenada);
            ordenar.sort();
        }
        painelInicial.setVisible(false);
        meusBaralhosPainel.setVisible(true);
    }//GEN-LAST:event_abrirMeusBaralhosButtonActionPerformed

    private void criarBaralhoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criarBaralhoButtonActionPerformed
        try {
            boolean baralhoNaLista = false;
            if (!inserirNomeBaralhoTextField.getText().equals("") && !inserirMateriaTextField.getText().equals("")) {
                for (Baralho baralho : listaDeBaralhos) {
                    if (baralho.getNomeBaralho().equals(inserirNomeBaralhoTextField.getText())) {
                        baralhoNaLista = true;
                        break;
                    }
                }
                if (!baralhoNaLista) {
                    baralho = new Baralho(0, inserirNomeBaralhoTextField.getText(), inserirMateriaTextField.getText(), usuario, 0, 0, 0);        // TODO add your handling code here:
                    baralho = BaralhoDAO.criarBaralho(baralho);
                    listaDeBaralhos.add(baralho);
                    meusBaralhosLabel.setText("Meus baralhos");
                    Object[] dados = {baralho.getNomeBaralho(), baralho.getTema(), "0%", 0};
                    dtmBaralhos.addRow(dados);
                    inserirNomeBaralhoTextField.setText("");
                    inserirMateriaTextField.setText("");
                    mensagemAdicionarBaralhoInvalido.setText("");
                    adicionarBaralhosPainel.setVisible(false);
                    ordenar = new TableRowSorter<>(dtmBaralhos);
                    meusBaralhosTable.setRowSorter(ordenar);
                    ArrayList<RowSorter.SortKey> chaveOrdenada = new ArrayList<>();
                    chaveOrdenada.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
                    ordenar.setSortKeys(chaveOrdenada);
                    ordenar.sort();
                    meusBaralhosPainel.setVisible(true);
                    meusBaralhosTable.getTableHeader().setVisible(true);
                } else {
                    mensagemAdicionarBaralhoInvalido.setText("Baralho já existente");
                }
            } else {
                mensagemAdicionarBaralhoInvalido.setText("Insira os valores");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_criarBaralhoButtonActionPerformed

    private void irParaCriarBaralhoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irParaCriarBaralhoButtonActionPerformed
        meusBaralhosPainel.setVisible(false);
        adicionarBaralhosPainel.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_irParaCriarBaralhoButtonActionPerformed

    private void confirmarEditarCartasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarEditarCartasButtonActionPerformed
        try {
            boolean perguntaJaExiste = false;
            if (editarPerguntaCaixaDeTexto.getText().equals("") || editarRespostaCaixaDeTexto.getText().equals("")) {
                mensagemEditarPerguntaInvalida.setText("Insira os valores");
            } else if (listaDeCartas.get(j).getPergunta().equals(editarPerguntaCaixaDeTexto.getText()) && listaDeCartas.get(j).getResposta().equals(editarRespostaCaixaDeTexto.getText())) {
                meusCardsPanel.setVisible(true);
                editarCartasPainel.setVisible(false);
            } else {
                for (Carta carta : listaDeCartas) {
                    if (carta.getPergunta().equals(editarPerguntaCaixaDeTexto.getText())) {
                        if (carta != listaDeCartas.get(j)) {
                            System.out.println("caiu nesse caso");
                            perguntaJaExiste = true;
                            break;
                        }
                    }
                }
                if (!perguntaJaExiste) {
                    listaDeCartas.get(j).setPergunta(editarPerguntaCaixaDeTexto.getText());
                    listaDeCartas.get(j).setResposta(editarRespostaCaixaDeTexto.getText());
                    CardDAO.editar(listaDeCartas.get(j));
                    dtmCartas.setRowCount(0);
                    for (Carta carta : listaDeCartas) {
                        Object[] linha = {
                            carta.getPergunta(),
                            carta.getResposta(),
                            String.format("%.2f%%", carta.getMediaDeAcertos()),
                            carta.getTotalDeAcertos() + carta.getTotalDeErros()};
                        dtmCartas.addRow(linha);
                    }
                    ordenar = new TableRowSorter<>(dtmCartas);
                    minhasCartasTable.setRowSorter(ordenar);
                    ArrayList<RowSorter.SortKey> chaveOrdenada = new ArrayList<>();
                    chaveOrdenada.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
                    ordenar.setSortKeys(chaveOrdenada);
                    ordenar.sort();
                    editarRespostaCaixaDeTexto.setText("");
                    editarPerguntaCaixaDeTexto.setText("");
                    mensagemEditarPerguntaInvalida.setText("");
                    meusCardsPanel.setVisible(true);
                    editarCartasPainel.setVisible(false);
                } else {
                    mensagemEditarPerguntaInvalida.setText("Pergunta já adicionada");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_confirmarEditarCartasButtonActionPerformed

    private void irParaCriarCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irParaCriarCardButtonActionPerformed
        meusCardsPanel.setVisible(false);
        criarCardsPanel.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_irParaCriarCardButtonActionPerformed

    private void confirmarCriarCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarCriarCardButtonActionPerformed

        try {
            boolean cartaNaLista = false;
            if (!inserirPerguntaCaixaDeTexto.getText().equals("") && !inserirRespostaCaixaDeTexto.getText().equals("")) {
                for (Carta carta : listaDeCartas) {
                    if (carta.getPergunta().equals(inserirPerguntaCaixaDeTexto.getText())) {
                        cartaNaLista = true;
                        break;
                    }
                }
                if (!cartaNaLista) {
                    Carta carta = new Carta(0, inserirPerguntaCaixaDeTexto.getText(), inserirRespostaCaixaDeTexto.getText(), listaDeBaralhos.get(i), 0, 0, 0);
                    carta = CardDAO.criarCard(carta);
                    listaDeCartas.add(carta);
                    Object[] dados = {carta.getPergunta(), carta.getResposta(), String.format("%.2f%%", carta.getMediaDeAcertos()), carta.getTotalDeAcertos() + carta.getTotalDeErros()
                    };
                    dtmCartas.addRow(dados);
                    ordenar = new TableRowSorter<>(dtmCartas);
                    minhasCartasTable.setRowSorter(ordenar);
                    ArrayList<RowSorter.SortKey> chaveOrdenada = new ArrayList<>();
                    chaveOrdenada.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
                    ordenar.setSortKeys(chaveOrdenada);
                    ordenar.sort();
                    meusCardsLabel.setText("Cards");
                    minhasCartasTable.getTableHeader().setVisible(true);
                    mensagemCriarCardInvalidoLabel.setText("");
                    inserirPerguntaCaixaDeTexto.setText("");
                    inserirRespostaCaixaDeTexto.setText("");
                    meusCardsPanel.setVisible(true);
                    criarCardsPanel.setVisible(false);
                } else {
                    mensagemCriarCardInvalidoLabel.setText("Pergunta já adicionada");
                }
            } else {
                mensagemCriarCardInvalidoLabel.setText("Insira as informações");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_confirmarCriarCardButtonActionPerformed

    private void voltarMeusCardsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusCardsButtonActionPerformed
        mensagemCriarCardInvalidoLabel.setText("");
        inserirPerguntaCaixaDeTexto.setText("");
        inserirRespostaCaixaDeTexto.setText("");
        criarCardsPanel.setVisible(false);
        meusCardsPanel.setVisible(true);
    }//GEN-LAST:event_voltarMeusCardsButtonActionPerformed

    private void voltarMeusCardsButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusCardsButton2ActionPerformed
        editarRespostaCaixaDeTexto.setText("");
        editarPerguntaCaixaDeTexto.setText("");
        mensagemEditarPerguntaInvalida.setText("");
        editarCartasPainel.setVisible(false);
        meusCardsPanel.setVisible(true);
    }//GEN-LAST:event_voltarMeusCardsButton2ActionPerformed

    private void jogarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jogarButtonActionPerformed
        try {
            Object nomeBaralho = selecionarBaralhoJogarTable.getValueAt(selecionarBaralhoJogarTable.getSelectedRow(), 0);
            for (a = 0; a < listaDeBaralhos.size(); a++) {
                if (listaDeBaralhos.get(a).getNomeBaralho().equals(nomeBaralho)) {
                    break;
                }
            }
            listaDeCartas = CardDAO.listarCartas(listaDeBaralhos.get(a));
            if (listaDeCartas.isEmpty()) {
                baralhoSemCartasLabel.setText("Crie cards para usar esse baralho!");
            } else {
                Collections.shuffle(listaDeCartas);
                selecionarModoDeJogoPainel.setVisible(true);
                selecionarBaralhoJogarPainel.setVisible(false);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jogarButtonActionPerformed

    private void irJogarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irJogarButtonActionPerformed
        editarUsuarioButton.setVisible(false);
        excluirAlunoBotao.setVisible(false);
        dtmJogar = (DefaultTableModel) selecionarBaralhoJogarTable.getModel();
        dtmJogar.setRowCount(0);
        if (listaDeBaralhos.isEmpty()) {
            selecionarBaralhosJogarLabel.setText("Você não tem baralhos criados");
            selecionarBaralhoJogarTable.getTableHeader().setVisible(false);
        } else {
            selecionarBaralhosJogarLabel.setText("Selecione um baralho para jogar");
            selecionarBaralhoJogarTable.getTableHeader().setVisible(true);
        }
        try {
            for (Baralho baralho : listaDeBaralhos) {
                Object[] linha = {
                    baralho.getNomeBaralho(),
                    baralho.getTema(), CardDAO.listarCartas(baralho).size()};

                dtmJogar.addRow(linha);
            }
            ordenar = new TableRowSorter<>(dtmJogar);
            selecionarBaralhoJogarTable.setRowSorter(ordenar);
            ArrayList<RowSorter.SortKey> chaveOrdenada = new ArrayList<>();
            chaveOrdenada.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
            ordenar.setSortKeys(chaveOrdenada);
            ordenar.sort();
        } catch (Exception e) {
            e.printStackTrace();
        }
        selecionarBaralhoJogarPainel.setVisible(true);
        painelInicial.setVisible(false);
    }//GEN-LAST:event_irJogarButtonActionPerformed

    private void voltarMeusBaralhos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusBaralhos1ActionPerformed
        inserirNomeBaralhoTextField.setText("");
        inserirMateriaTextField.setText("");
        mensagemAdicionarBaralhoInvalido.setText("");
        adicionarBaralhosPainel.setVisible(false);
        meusBaralhosPainel.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_voltarMeusBaralhos1ActionPerformed

    private void voltarPainelInicialButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarPainelInicialButtonActionPerformed
        editarUsuarioButton.setVisible(true);
        if (usuario.getTipoUsuario().equals("professor")) {
            excluirAlunoBotao.setVisible(true);
        }
        selecionarBaralhosJogarLabel.setText("Selecione um baralho");
        selecionarBaralhoJogarTable.getTableHeader().setVisible(true);
        baralhoSemCartasLabel.setText("");
        painelInicial.setVisible(true);
        selecionarBaralhoJogarPainel.setVisible(false);
    }//GEN-LAST:event_voltarPainelInicialButtonActionPerformed

    private void voltarMeusBaralhosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusBaralhosButtonActionPerformed
        meusCardsLabel.setText("Cards");
        minhasCartasTable.getTableHeader().setVisible(true);
        meusCardsPanel.setVisible(false);
        meusBaralhosPainel.setVisible(true);
    }//GEN-LAST:event_voltarMeusBaralhosButtonActionPerformed

    private void inserirNomeBaralhoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirNomeBaralhoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inserirNomeBaralhoTextFieldActionPerformed

    private void inserirMateriaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirMateriaTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inserirMateriaTextFieldActionPerformed

    private void proximaCartaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proximaCartaButtonActionPerformed
        cartaAnteriorButton.setVisible(true);
        if (indice[0] < listaDeCartas.size() - 2) {
            indice[0]++;
        } else {
            indice[0]++;
            proximaCartaButton.setVisible(false);
        }
        if ((virarCardButton.getText().equals("Ver pergunta") && acerteiButton.isVisible()) || (virarCardButton.getText().equals("Ver resposta") && !acerteiButton.isVisible())) {
            virarCardButton.setText("Ver resposta");
            conteudoCartaLabel.setText("<html>" + listaDeCartas.get(indice[0]).getPergunta() + "</html>");
        } else {
            virarCardButton.setText("Ver pergunta");
            conteudoCartaLabel.setText("<html>" + listaDeCartas.get(indice[0]).getResposta() + "</html>");
        }
        acerteiButton.setVisible(false);
        erreiButton.setVisible(false);
    }//GEN-LAST:event_proximaCartaButtonActionPerformed

    private void cartaAnteriorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartaAnteriorButtonActionPerformed
        proximaCartaButton.setVisible(true);
        if (indice[0] > 1) {
            indice[0]--;
        } else {
            indice[0]--;
            cartaAnteriorButton.setVisible(false);
        }
        if ((virarCardButton.getText().equals("Ver pergunta") && acerteiButton.isVisible()) || (virarCardButton.getText().equals("Ver resposta") && !acerteiButton.isVisible())) {
            virarCardButton.setText("Ver resposta");
            conteudoCartaLabel.setText("<html>" + listaDeCartas.get(indice[0]).getPergunta() + "</html>");
        } else {
            virarCardButton.setText("Ver pergunta");
            conteudoCartaLabel.setText("<html>" + listaDeCartas.get(indice[0]).getResposta() + "</html>");
        }
        acerteiButton.setVisible(false);
        erreiButton.setVisible(false);
    }//GEN-LAST:event_cartaAnteriorButtonActionPerformed

    private void virarCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_virarCardButtonActionPerformed
        if (virarCardButton.getText().equals("Ver pergunta")) {
            conteudoCartaLabel.setText("<html>" + listaDeCartas.get(indice[0]).getPergunta() + "</html>");
            virarCardButton.setText("Ver resposta");
        } else {
            conteudoCartaLabel.setText("<html>" + listaDeCartas.get(indice[0]).getResposta() + "</html>");
            virarCardButton.setText("Ver pergunta");
        }
        if (acerteiButton.isVisible()) {
            acerteiButton.setVisible(false);
            erreiButton.setVisible(false);
        } else {
            acerteiButton.setVisible(true);
            erreiButton.setVisible(true);
        }
    }//GEN-LAST:event_virarCardButtonActionPerformed

    private void acerteiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acerteiButtonActionPerformed
        try {
            cartaJogo = listaDeCartas.get(indice[0]);
            if (!cartaJogo.isRespondida()) {
                acertosJogo++;
                cartaJogo.setRespondida(true);
                cartaJogo.setAcertou(true);
                int acerto = cartaJogo.getTotalDeAcertos() + 1;
                int erro = cartaJogo.getTotalDeErros();
                cartaJogo.setTotalDeAcertos(acerto);
                double media = (double) acerto / (acerto + erro) * 100;
                cartaJogo.setMediaDeAcertos(Math.round(media * 100) / 100.0);
                CardDAO.editar(cartaJogo);
            } else if (!cartaJogo.isAcertou()) {
                cartaJogo.setAcertou(true);
                errosJogo--;
                acertosJogo++;
                int acerto = cartaJogo.getTotalDeAcertos() + 1;
                int erro = cartaJogo.getTotalDeErros() - 1;
                cartaJogo.setTotalDeAcertos(acerto);
                cartaJogo.setTotalDeErros(erro);
                double media = (double) acerto / (acerto + erro) * 100;
                cartaJogo.setMediaDeAcertos(Math.round(media * 100) / 100.0);
                CardDAO.editar(cartaJogo);
            }
            indice[0]++;
            if (indice[0] < listaDeCartas.size()) {
                if (virarCardButton.getText().equals("Ver pergunta")) {
                    conteudoCartaLabel.setText("<html>" + listaDeCartas.get(indice[0]).getPergunta() + "</html>");
                    virarCardButton.setText("Ver resposta");
                } else {
                    conteudoCartaLabel.setText("<html>" + listaDeCartas.get(indice[0]).getResposta() + "</html>");
                    virarCardButton.setText("Ver pergunta");
                }
                cartaAnteriorButton.setVisible(true);
                if (indice[0] == listaDeCartas.size() - 1) {
                    proximaCartaButton.setVisible(false);
                }
                cartaAnteriorButton.setVisible(true);
                acerteiButton.setVisible(false);
                erreiButton.setVisible(false);
            } else {
                painelJogoNormal.setVisible(false);
                painelJogoFinalizado.setVisible(true);
                estatisticasJogoLabel.setText(String.format("Você acertou %d/%d questões", acertosJogo, (acertosJogo + errosJogo)));
                int acertosTotal = listaDeBaralhos.get(a).getTotalDeAcertos();
                int errosTotal = listaDeBaralhos.get(a).getTotalDeErros();
                acertosTotal += acertosJogo;
                errosTotal += errosJogo;
                listaDeBaralhos.get(a).setTotalDeAcertos(acertosTotal);
                listaDeBaralhos.get(a).setTotalDeErros(errosTotal);
                double media = (double) acertosTotal / (acertosTotal + errosTotal) * 100;
                double mediaFormatada = Math.round(media * 100) / 100.0;
                listaDeBaralhos.get(a).setMediaDeAcertos(mediaFormatada);
                BaralhoDAO.editar(listaDeBaralhos.get(a));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_acerteiButtonActionPerformed

    private void erreiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_erreiButtonActionPerformed
        try {
            cartaJogo = listaDeCartas.get(indice[0]);
            if (!cartaJogo.isRespondida()) {
                errosJogo++;
                cartaJogo.setRespondida(true);
                int acerto = cartaJogo.getTotalDeAcertos();
                int erro = cartaJogo.getTotalDeErros() + 1;
                cartaJogo.setTotalDeErros(erro);
                double media = (double) acerto / (acerto + erro) * 100;
                cartaJogo.setMediaDeAcertos(Math.round(media * 100) / 100.0);
                CardDAO.editar(cartaJogo);
            } else if (cartaJogo.isAcertou()) {
                errosJogo++;
                acertosJogo--;
                cartaJogo.setAcertou(false);
                int acerto = cartaJogo.getTotalDeAcertos() - 1;
                int erro = cartaJogo.getTotalDeErros() + 1;
                cartaJogo.setTotalDeAcertos(acerto);
                cartaJogo.setTotalDeErros(erro);
                double media = (double) acerto / (acerto + erro) * 100;
                cartaJogo.setMediaDeAcertos(Math.round(media * 100) / 100.0);
                CardDAO.editar(cartaJogo);
            }
            indice[0]++;
            if (indice[0] < listaDeCartas.size()) {
                if (virarCardButton.getText().equals("Ver pergunta")) {
                    conteudoCartaLabel.setText("<html>" + listaDeCartas.get(indice[0]).getPergunta() + "</html>");
                    virarCardButton.setText("Ver resposta");
                } else {
                    conteudoCartaLabel.setText("<html>" + listaDeCartas.get(indice[0]).getResposta() + "</html>");
                    virarCardButton.setText("Ver pergunta");
                }
                cartaAnteriorButton.setVisible(true);
                if (indice[0] == listaDeCartas.size() - 1) {
                    proximaCartaButton.setVisible(false);
                }
                cartaAnteriorButton.setVisible(true);
                acerteiButton.setVisible(false);
                erreiButton.setVisible(false);
            } else {
                painelJogoNormal.setVisible(false);
                painelJogoFinalizado.setVisible(true);
                estatisticasJogoLabel.setText(String.format("Você acertou %d/%d questões", acertosJogo, acertosJogo + errosJogo));
                int acertosTotal = listaDeBaralhos.get(a).getTotalDeAcertos();
                int errosTotal = listaDeBaralhos.get(a).getTotalDeErros();
                acertosTotal += acertosJogo;
                errosTotal += errosJogo;
                listaDeBaralhos.get(a).setTotalDeAcertos(acertosTotal);
                listaDeBaralhos.get(a).setTotalDeErros(errosTotal);
                double media = (double) acertosTotal / (acertosTotal + errosTotal) * 100;
                double mediaFormatada = Math.round(media * 100) / 100.0;
                listaDeBaralhos.get(a).setMediaDeAcertos(mediaFormatada);
                BaralhoDAO.editar(listaDeBaralhos.get(a));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_erreiButtonActionPerformed

    private void finalizarJogoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarJogoButtonActionPerformed
        try {
            painelJogoNormal.setVisible(false);
            painelJogoFinalizado.setVisible(true);
            estatisticasJogoLabel.setText(String.format("Você acertou %d/%d questões", acertosJogo, acertosJogo + errosJogo));
            int acertosTotal = listaDeBaralhos.get(a).getTotalDeAcertos();
            int errosTotal = listaDeBaralhos.get(a).getTotalDeErros();
            acertosTotal += acertosJogo;
            errosTotal += errosJogo;
            listaDeBaralhos.get(a).setTotalDeAcertos(acertosTotal);
            listaDeBaralhos.get(a).setTotalDeErros(errosTotal);
            double media = (double) acertosTotal / (acertosTotal + errosTotal) * 100;
            double mediaFormatada = Math.round(media * 100) / 100.0;
            listaDeBaralhos.get(a).setMediaDeAcertos(mediaFormatada);
            BaralhoDAO.editar(listaDeBaralhos.get(a));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_finalizarJogoButtonActionPerformed

    private void terminarJogoVoltarPainelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminarJogoVoltarPainelButtonActionPerformed
        editarUsuarioButton.setVisible(true);
        if (usuario.getTipoUsuario().equals("professor")) {
            excluirAlunoBotao.setVisible(true);
        }
        painelJogoFinalizado.setVisible(false);
        painelInicial.setVisible(true);
    }//GEN-LAST:event_terminarJogoVoltarPainelButtonActionPerformed

    private void irParaEditarBaralhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irParaEditarBaralhoActionPerformed
        editarNomeBaralhoCaixaDeTexto.setText(listaDeBaralhos.get(i).getNomeBaralho());
        editarTemaCaixaDeTexto.setText(listaDeBaralhos.get(i).getTema());
        meusCardsPanel.setVisible(false);
        editarBaralhoPainel.setVisible(true);
    }//GEN-LAST:event_irParaEditarBaralhoActionPerformed

    private void sairButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairButtonActionPerformed
        confirmarSairJogoPainel.setVisible(true);
        painelInicial.setVisible(false);
        editarUsuarioButton.setVisible(false);
        excluirAlunoBotao.setVisible(false);
    }//GEN-LAST:event_sairButtonActionPerformed

    private void confirmarEditarBaralhoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarEditarBaralhoButtonActionPerformed
        try {
            boolean baralhoJaExiste = false;
            if (editarNomeBaralhoCaixaDeTexto.getText().equals("") || editarTemaCaixaDeTexto.getText().equals("")) {
                mensagemEditarPerguntaInvalida.setText("Insira os valores");
            } else if (listaDeBaralhos.get(i).getNomeBaralho().equals(editarNomeBaralhoCaixaDeTexto.getText()) && listaDeBaralhos.get(i).getTema().equals(editarTemaCaixaDeTexto.getText())) {
                meusCardsPanel.setVisible(true);
                editarBaralhoPainel.setVisible(false);
            } else {
                for (Baralho baralho : listaDeBaralhos) {
                    if (baralho.getNomeBaralho().equals(editarNomeBaralhoCaixaDeTexto.getText())) {
                        if (baralho != listaDeBaralhos.get(i)) {
                            System.out.println("caiu nesse caso");
                            baralhoJaExiste = true;
                            break;
                        }
                    }
                }
                if (!baralhoJaExiste) {
                    listaDeBaralhos.get(i).setNomeBaralho(editarNomeBaralhoCaixaDeTexto.getText());
                    listaDeBaralhos.get(i).setTema(editarTemaCaixaDeTexto.getText());
                    BaralhoDAO.editar(listaDeBaralhos.get(i));
                    dtmBaralhos.setRowCount(0);
                    for (Baralho baralho : listaDeBaralhos) {
                        Object[] linha = {
                            baralho.getNomeBaralho(),
                            baralho.getTema(),
                            String.format("%.2f%%", baralho.getMediaDeAcertos()),
                            baralho.getTotalDeAcertos() + baralho.getTotalDeErros()};
                        dtmBaralhos.addRow(linha);
                    }
                    ordenar = new TableRowSorter<>(dtmBaralhos);
                    meusBaralhosTable.setRowSorter(ordenar);
                    ArrayList<RowSorter.SortKey> chaveOrdenada = new ArrayList<>();
                    chaveOrdenada.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
                    ordenar.setSortKeys(chaveOrdenada);
                    ordenar.sort();
                    nomeDoBaralhoLabel.setText(listaDeBaralhos.get(i).getNomeBaralho());
                    editarNomeBaralhoCaixaDeTexto.setText("");
                    editarTemaCaixaDeTexto.setText("");
                    mensagemEditarBaralhoInvalido.setText("");
                    meusCardsPanel.setVisible(true);
                    editarBaralhoPainel.setVisible(false);
                } else {
                    mensagemEditarBaralhoInvalido.setText("Baralho já adicionado");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_confirmarEditarBaralhoButtonActionPerformed

    private void voltarMeusCardsButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusCardsButton3ActionPerformed
        editarNomeBaralhoCaixaDeTexto.setText("");
        editarTemaCaixaDeTexto.setText("");
        mensagemEditarBaralhoInvalido.setText("");
        editarBaralhoPainel.setVisible(false);
        meusCardsPanel.setVisible(true);
    }//GEN-LAST:event_voltarMeusCardsButton3ActionPerformed

    private void abrirMeusGruposButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirMeusGruposButtonActionPerformed
        editarUsuarioButton.setVisible(false);
        excluirAlunoBotao.setVisible(false);
        dtmGrupos = (DefaultTableModel) meusGruposTable.getModel();
        dtmGrupos.setRowCount(0);
        if (listaDeGrupos.isEmpty()) {
            meusGruposLabel.setText("Você não está em grupo nenhum!");
            meusGruposTable.getTableHeader().setVisible(false);
        } else {
            for (Grupo grupo : listaDeGrupos) {
                Object[] linha = {
                    grupo.getNomeGrupo(),
                    grupo.getProfessor().getNomeUsuario()};
                dtmGrupos.addRow(linha);
            }
            ordenar = new TableRowSorter<>(dtmGrupos);
            meusGruposTable.setRowSorter(ordenar);
            ArrayList<RowSorter.SortKey> chaveOrdenada = new ArrayList<>();
            chaveOrdenada.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
            ordenar.setSortKeys(chaveOrdenada);
            ordenar.sort();
        }
        meusGruposPainel.setVisible(true);
        if (usuario.getTipoUsuario().equals("aluno")) {
            excluirGruposButton.setVisible(false);
            irEditarGruposButton.setVisible(false);
            criarGrupoButton.setVisible(false);
        } else {
            excluirGruposButton.setVisible(true);
            irEditarGruposButton.setVisible(true);
            criarGrupoButton.setVisible(true);
        }
        painelInicial.setVisible(false);
    }//GEN-LAST:event_abrirMeusGruposButtonActionPerformed

    private void voltarPainelInicial1Button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarPainelInicial1Button1ActionPerformed
        editarUsuarioButton.setVisible(true);
        if (usuario.getTipoUsuario().equals("professor")) {
            excluirAlunoBotao.setVisible(true);
        }
        meusGruposTable.getTableHeader().setVisible(true);
        meusGruposLabel.setText("Meus grupos");
        meusGruposPainel.setVisible(false);
        painelInicial.setVisible(true);
    }//GEN-LAST:event_voltarPainelInicial1Button1ActionPerformed

    private void irEditarGruposButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irEditarGruposButtonActionPerformed
        Object nomeGrupo = meusGruposTable.getValueAt(meusGruposTable.getSelectedRow(), 0);
        for (c = 0; c < listaDeGrupos.size(); c++) {
            if (listaDeGrupos.get(c).getNomeGrupo().equals(nomeGrupo)) {
                break;
            }
        }
        editarGrupoPainel.setVisible(true);
        meusGruposPainel.setVisible(false);
        editarNomeGrupoCaixaDeTexto.setText(listaDeGrupos.get(c).getNomeGrupo());
    }//GEN-LAST:event_irEditarGruposButtonActionPerformed

    private void criarGrupoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criarGrupoButtonActionPerformed
        adicionarGrupoPainel.setVisible(true);
        meusGruposPainel.setVisible(false);
    }//GEN-LAST:event_criarGrupoButtonActionPerformed

    private void confirmarEditarCartasButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarEditarCartasButton1ActionPerformed
        try {
            boolean grupoJaExiste = false;
            if (editarNomeGrupoCaixaDeTexto.getText().equals("")) {
                mensagemEditarGrupoInvalido.setText("Insira os valores");
            } else if (listaDeGrupos.get(c).getNomeGrupo().equals(editarNomeGrupoCaixaDeTexto.getText())) {
                meusGruposPainel.setVisible(true);
                editarGrupoPainel.setVisible(false);
            } else {
                for (Grupo grupo : listaDeGrupos) {
                    if (grupo.getNomeGrupo().equals(editarNomeGrupoCaixaDeTexto.getText())) {
                        if (grupo != listaDeGrupos.get(c)) {
                            grupoJaExiste = true;
                            break;
                        }
                    }
                }
                if (!grupoJaExiste) {
                    listaDeGrupos.get(c).setNomeGrupo(editarNomeGrupoCaixaDeTexto.getText());
                    GrupoDAO.editarGrupo(listaDeGrupos.get(c));
                    dtmGrupos.setRowCount(0);
                    for (Grupo grupo : listaDeGrupos) {
                        Object[] linha = {
                            grupo.getNomeGrupo(),
                            grupo.getProfessor().getNomeUsuario()};
                        dtmGrupos.addRow(linha);
                    }
                    ordenar = new TableRowSorter<>(dtmGrupos);
                    meusGruposTable.setRowSorter(ordenar);
                    ArrayList<RowSorter.SortKey> chaveOrdenada = new ArrayList<>();
                    chaveOrdenada.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
                    ordenar.setSortKeys(chaveOrdenada);
                    ordenar.sort();
                    mensagemEditarGrupoInvalido.setText("");
                    editarNomeGrupoCaixaDeTexto.setText("");
                    meusGruposPainel.setVisible(true);
                    editarGrupoPainel.setVisible(false);
                } else {
                    mensagemEditarGrupoInvalido.setText("Grupo já adicionado");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_confirmarEditarCartasButton1ActionPerformed

    private void voltarEditarGrupoAcoesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarEditarGrupoAcoesButtonActionPerformed

    }//GEN-LAST:event_voltarEditarGrupoAcoesButtonActionPerformed

    private void confirmarExcluirBaralhoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarExcluirBaralhoButtonActionPerformed
        try {
            Baralho baralhoParaExcluir = null;
            for (Baralho baralho : listaDeBaralhos) {
                if (baralho.getNomeBaralho().equals(nomeBaralho)) {
                    baralhoParaExcluir = baralho;
                    break;
                }
            }
            BaralhoDAO.excluir(baralhoParaExcluir);

            listaDeBaralhos.remove(baralhoParaExcluir);
            dtmBaralhos.removeRow(row);
            if (listaDeBaralhos.isEmpty()) {
                meusBaralhosLabel.setText("Você não tem baralhos criados");
                meusBaralhosTable.getTableHeader().setVisible(false);
            }
            meusBaralhosPainel.setVisible(true);
            confirmarExcluirBaralhoPainel.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_confirmarExcluirBaralhoButtonActionPerformed

    private void voltarMeusBaralhosButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusBaralhosButton2ActionPerformed
        // TODO add your handling code here:
        confirmarExcluirBaralhoPainel.setVisible(false);
        meusBaralhosPainel.setVisible(true);
    }//GEN-LAST:event_voltarMeusBaralhosButton2ActionPerformed

    private void confirmarAdicionarAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarAdicionarAlunoActionPerformed
        try {
            boolean usuarioJaExiste = false;
            if (inserirEmailAlunoCaixaDeTexto.getText().equals("")) {
                mensagemEmailAlunoInvalido.setText("Digite um email!");
            } else {
                Usuario aluno = GrupoDAO.selecionarAluno(inserirEmailAlunoCaixaDeTexto.getText());
                if (aluno == null) {
                    mensagemEmailAlunoInvalido.setText("Aluno não encontrado");
                } else {
                    for (Usuario usuario : listaDeAlunosGrupo) {
                        if (usuario.equals(aluno)) {
                            mensagemEmailAlunoInvalido.setText("Aluno já inserido");
                            usuarioJaExiste = true;
                            break;
                        }
                    }
                    if (!usuarioJaExiste) {
                        GrupoDAO.adicionarAlunoGrupo(aluno, listaDeGrupos.get(d));
                        dtmAlunosDoGrupo = (DefaultTableModel) alunosDoGrupoTable.getModel();
                        dtmAlunosDoGrupo.setRowCount(0);
                        Object[] row = {
                            aluno.getNomeUsuario(),
                            aluno.getEmail()};
                        listaDeAlunosGrupo.add(aluno);
                        dtmAlunosDoGrupo.addRow(row);
                        ordenar = new TableRowSorter<>(dtmAlunosDoGrupo);
                        alunosDoGrupoTable.setRowSorter(ordenar);
                        ArrayList<RowSorter.SortKey> chaveOrdenada = new ArrayList<>();
                        chaveOrdenada.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
                        ordenar.setSortKeys(chaveOrdenada);
                        ordenar.sort();
                        mensagemEmailAlunoInvalido.setText("");
                        inserirEmailAlunoCaixaDeTexto.setText("");
                        alunosDoGrupoLabel.setText("Alunos do grupo");
                        alunosDoGrupoTable.getTableHeader().setVisible(true);
                        alunosDoGrupoPainel.setVisible(true);
                        adicionarAlunoGrupoPainel.setVisible(false);
                    }
                }

            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_confirmarAdicionarAlunoActionPerformed

    private void voltarEditarGrupoAcoesButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarEditarGrupoAcoesButton2ActionPerformed
        mensagemEmailAlunoInvalido.setText("");
        inserirEmailAlunoCaixaDeTexto.setText("");
        alunosDoGrupoPainel.setVisible(true);
        adicionarAlunoGrupoPainel.setVisible(false);
    }//GEN-LAST:event_voltarEditarGrupoAcoesButton2ActionPerformed

    private void voltarMeusGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusGruposActionPerformed
        mensagemBaralhoDoGrupoSemCartas.setText("");
        baralhosDoGrupoTable.getTableHeader().setVisible(true);
        baralhosDoGrupoLabel.setText("Baralhos do grupo");
        mensagemBaralhoDoGrupoSemCartas.setText("");
        meusGruposPainel.setVisible(true);
        baralhosDoGrupoPainel.setVisible(false);
    }//GEN-LAST:event_voltarMeusGruposActionPerformed

    private void editarBaralhoGrupoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarBaralhoGrupoButtonActionPerformed
        mensagemBaralhoDoGrupoSemCartas.setText("");
        Object nomeBaralho = baralhosDoGrupoTable.getValueAt(baralhosDoGrupoTable.getSelectedRow(), 0);
        for (e = 0; e < listaDeBaralhosGrupo.size(); e++) {
            if (listaDeBaralhosGrupo.get(e).getNomeBaralho().equals(nomeBaralho)) {
                break;
            }
        }
        editarNomeBaralhoGrupoCaixaDeTexto.setText(listaDeBaralhosGrupo.get(e).getNomeBaralho());
        editarTemaBaralhoGrupoCaixaDeTexto.setText(listaDeBaralhosGrupo.get(e).getTema());
        baralhosDoGrupoPainel.setVisible(false);
        editarBaralhoGrupoPainel.setVisible(true);
    }//GEN-LAST:event_editarBaralhoGrupoButtonActionPerformed

    private void criarBaralhoGrupoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criarBaralhoGrupoButtonActionPerformed
        mensagemBaralhoDoGrupoSemCartas.setText("");
        baralhosDoGrupoPainel.setVisible(false);
        criarBaralhoGrupoPainel.setVisible(true);
    }//GEN-LAST:event_criarBaralhoGrupoButtonActionPerformed

    private void confirmarCriarGrupoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarCriarGrupoButtonActionPerformed
        try {
            boolean grupoNaLista = false;
            if (!inserirNomeGrupoCaixaDeTexto.getText().equals("")) {
                for (Grupo grupo : listaDeGrupos) {
                    if (grupo.getNomeGrupo().equals(inserirNomeGrupoCaixaDeTexto.getText())) {
                        System.out.println("esta na lista");
                        grupoNaLista = true;
                        break;
                    }
                }
                if (!grupoNaLista) {
                    Grupo grupo = new Grupo(0, usuario, inserirNomeGrupoCaixaDeTexto.getText());        // TODO add your handling code here:
                    grupo = GrupoDAO.criarGrupo(grupo, usuario);
                    listaDeGrupos.add(grupo);
                    meusGruposLabel.setText("Meus grupos");
                    Object[] dados = {grupo.getNomeGrupo(), grupo.getProfessor().getNomeUsuario()};
                    dtmGrupos.addRow(dados);
                    ordenar = new TableRowSorter<>(dtmGrupos);
                    meusGruposTable.setRowSorter(ordenar);
                    ArrayList<RowSorter.SortKey> chaveOrdenada = new ArrayList<>();
                    chaveOrdenada.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
                    ordenar.setSortKeys(chaveOrdenada);
                    ordenar.sort();
                    inserirNomeGrupoCaixaDeTexto.setText("");
                    mensagemCriarGrupoInvalido.setText("");
                    adicionarGrupoPainel.setVisible(false);
                    meusGruposPainel.setVisible(true);
                    meusGruposTable.getTableHeader().setVisible(true);
                } else {
                    mensagemCriarGrupoInvalido.setText("Grupo já existente");
                }
            } else {
                mensagemCriarGrupoInvalido.setText("Insira os valores");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_confirmarCriarGrupoButtonActionPerformed

    private void voltarMeusGruposButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusGruposButton2ActionPerformed
        adicionarGrupoPainel.setVisible(false);
        meusGruposPainel.setVisible(true);
    }//GEN-LAST:event_voltarMeusGruposButton2ActionPerformed

    private void confirmarEditarBaralhoButtonAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_confirmarEditarBaralhoButtonAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmarEditarBaralhoButtonAncestorAdded

    private void voltarMeusGrupos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusGrupos1ActionPerformed
        alunosDoGrupoLabel.setText("Alunos do grupo");
        alunosDoGrupoTable.getTableHeader().setVisible(true);
        alunosDoGrupoTable.getTableHeader().setVisible(true);
        alunosDoGrupoLabel.setText("Alunos do grupo");
        meusGruposPainel.setVisible(true);
        alunosDoGrupoPainel.setVisible(false);
    }//GEN-LAST:event_voltarMeusGrupos1ActionPerformed

    private void irExcluirAlunosGruposButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irExcluirAlunosGruposButtonActionPerformed
        linhaAlunoParaExcluir = alunosDoGrupoTable.getSelectedRow();
        if (alunosDoGrupoTable.isEditing()) {
            alunosDoGrupoTable.getCellEditor().stopCellEditing();
        }
        dtmAlunosDoGrupo = (DefaultTableModel) alunosDoGrupoTable.getModel();
        String nomeAluno = (String) dtmAlunosDoGrupo.getValueAt(linhaAlunoParaExcluir, 0);

        for (Usuario aluno : listaDeAlunosGrupo) {
            if (aluno.getNomeUsuario().equals(nomeAluno)) {
                alunoParaExcluir = aluno;
                break;
            }
        }
        alunosDoGrupoPainel.setVisible(false);
        confirmarExcluirAlunoGrupoPainel.setVisible(true);
    }//GEN-LAST:event_irExcluirAlunosGruposButtonActionPerformed

    private void AdicionarAlunoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdicionarAlunoButtonActionPerformed
        adicionarAlunoGrupoPainel.setVisible(true);
        alunosDoGrupoPainel.setVisible(false);
    }//GEN-LAST:event_AdicionarAlunoButtonActionPerformed

    private void inserirNomeBaralhoGrupoCaixaDeTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirNomeBaralhoGrupoCaixaDeTextoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inserirNomeBaralhoGrupoCaixaDeTextoActionPerformed

    private void confirmarCriarBaralhoGrupoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarCriarBaralhoGrupoButtonActionPerformed
        try {
            boolean baralhoNaLista = false;
            if (!inserirTemaBaralhoGrupoCaixaDeTexto.getText().equals("") && !inserirNomeBaralhoGrupoCaixaDeTexto.getText().equals("")) {
                for (Baralho baralho : listaDeBaralhosGrupo) {
                    if (baralho.getNomeBaralho().equals(inserirNomeBaralhoGrupoCaixaDeTexto.getText())) {
                        baralhoNaLista = true;
                        break;
                    }
                }
                if (!baralhoNaLista) {
                    Baralho baralho = new Baralho(0, inserirNomeBaralhoGrupoCaixaDeTexto.getText(), inserirTemaBaralhoGrupoCaixaDeTexto.getText(), usuario, 0, 0, 0);
                    baralho = BaralhoDAO.criarBaralho(baralho);
                    GrupoDAO.adicionarBaralhoGrupo(baralho, listaDeGrupos.get(d));
                    listaDeBaralhosGrupo.add(baralho);
                    Object[] dados = {baralho.getNomeBaralho(), baralho.getTema()
                    };
                    dtmBaralhosDoGrupo.addRow(dados);
                    ordenar = new TableRowSorter<>(dtmBaralhosDoGrupo);
                    baralhosDoGrupoTable.setRowSorter(ordenar);
                    ArrayList<RowSorter.SortKey> chaveOrdenada = new ArrayList<>();
                    chaveOrdenada.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
                    ordenar.setSortKeys(chaveOrdenada);
                    ordenar.sort();
                    baralhosDoGrupoLabel.setText("Baralhos do grupo");
                    baralhosDoGrupoTable.getTableHeader().setVisible(true);
                    mensagemAdicionarBaralhoGrupoInvalido.setText("");
                    inserirNomeBaralhoGrupoCaixaDeTexto.setText("");
                    inserirTemaBaralhoGrupoCaixaDeTexto.setText("");
                    baralhosDoGrupoPainel.setVisible(true);
                    criarBaralhoGrupoPainel.setVisible(false);
                } else {
                    mensagemAdicionarBaralhoGrupoInvalido.setText("Baralho já adicionado");
                }
            } else {
                mensagemAdicionarBaralhoGrupoInvalido.setText("Insira as informações");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_confirmarCriarBaralhoGrupoButtonActionPerformed

    private void voltarMeusBaralhosGrupoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusBaralhosGrupoButtonActionPerformed
        mensagemAdicionarBaralhoGrupoInvalido.setText("");
        inserirNomeBaralhoGrupoCaixaDeTexto.setText("");
        inserirTemaBaralhoGrupoCaixaDeTexto.setText("");
        baralhosDoGrupoPainel.setVisible(true);
        criarBaralhoGrupoPainel.setVisible(false);
    }//GEN-LAST:event_voltarMeusBaralhosGrupoButtonActionPerformed

    private void inserirTemaBaralhoGrupoCaixaDeTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirTemaBaralhoGrupoCaixaDeTextoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inserirTemaBaralhoGrupoCaixaDeTextoActionPerformed

    private void confirmarEditarBaralhoGrupoButtonAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_confirmarEditarBaralhoGrupoButtonAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmarEditarBaralhoGrupoButtonAncestorAdded

    private void confirmarEditarBaralhoGrupoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarEditarBaralhoGrupoButtonActionPerformed
        try {
            boolean baralhoJaExiste = false;
            if (editarNomeBaralhoGrupoCaixaDeTexto.getText().equals("") || editarTemaBaralhoGrupoCaixaDeTexto.getText().equals("")) {
                mensagemEditarBaralhoGrupoInvalido.setText("Insira os valores");
            } else if (listaDeBaralhosGrupo.get(e).getNomeBaralho().equals(editarNomeBaralhoGrupoCaixaDeTexto.getText())) {
                baralhosDoGrupoPainel.setVisible(true);
                editarBaralhoGrupoPainel.setVisible(false);
            } else {
                for (Baralho baralho : listaDeBaralhosGrupo) {
                    if (baralho.getNomeBaralho().equals(editarNomeBaralhoGrupoCaixaDeTexto.getText())) {
                        if (baralho != listaDeBaralhosGrupo.get(e)) {
                            baralhoJaExiste = true;
                            break;
                        }
                    }
                }
                if (!baralhoJaExiste) {
                    listaDeBaralhosGrupo.get(e).setNomeBaralho(editarNomeBaralhoGrupoCaixaDeTexto.getText());
                    listaDeBaralhosGrupo.get(e).setTema(editarTemaBaralhoGrupoCaixaDeTexto.getText());
                    System.out.println(usuario.getIdUsuario());
                    System.out.println(listaDeBaralhosGrupo.get(e).getIdBaralho());
                    BaralhoDAO.editar(listaDeBaralhosGrupo.get(e));
                    dtmBaralhosDoGrupo.setRowCount(0);
                    for (Baralho baralho : listaDeBaralhosGrupo) {
                        Object[] linha = {
                            baralho.getNomeBaralho(),
                            baralho.getTema()};
                        dtmBaralhosDoGrupo.addRow(linha);
                    }
                    ordenar = new TableRowSorter<>(dtmBaralhosDoGrupo);
                    baralhosDoGrupoTable.setRowSorter(ordenar);
                    ArrayList<RowSorter.SortKey> chaveOrdenada = new ArrayList<>();
                    chaveOrdenada.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
                    ordenar.setSortKeys(chaveOrdenada);
                    ordenar.sort();
                    mensagemEditarBaralhoGrupoInvalido.setText("");
                    editarNomeBaralhoGrupoCaixaDeTexto.setText("");
                    editarTemaBaralhoGrupoCaixaDeTexto.setText("");
                    baralhosDoGrupoPainel.setVisible(true);
                    editarBaralhoGrupoPainel.setVisible(false);
                } else {
                    mensagemEditarBaralhoGrupoInvalido.setText("Baralho já adicionado");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_confirmarEditarBaralhoGrupoButtonActionPerformed

    private void voltarBaralhosGrupoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarBaralhosGrupoButtonActionPerformed
        mensagemEditarBaralhoGrupoInvalido.setText("");
        editarNomeBaralhoGrupoCaixaDeTexto.setText("");
        editarTemaBaralhoGrupoCaixaDeTexto.setText("");
        baralhosDoGrupoPainel.setVisible(true);
        editarBaralhoGrupoPainel.setVisible(false);
    }//GEN-LAST:event_voltarBaralhosGrupoButtonActionPerformed

    private void voltarBaralhosDoGrupoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarBaralhosDoGrupoButtonActionPerformed
        cartasDoGrupoLabel.setText("Cards");
        cartasDoGrupoTable.getTableHeader().setVisible(true);
        baralhosDoGrupoPainel.setVisible(true);
        cartasDoGrupoPainel.setVisible(false);
    }//GEN-LAST:event_voltarBaralhosDoGrupoButtonActionPerformed

    private void irEditarCartasDoGrupoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irEditarCartasDoGrupoButtonActionPerformed
        Object pergunta = cartasDoGrupoTable.getValueAt(cartasDoGrupoTable.getSelectedRow(), 0);
        for (g = 0; g < listaDeCartas.size(); g++) {
            if (listaDeCartas.get(g).getPergunta().equals(pergunta)) {
                break;
            }
        }
        editarPerguntaGrupoCaixaDeTexto.setText(listaDeCartas.get(g).getPergunta());
        editarRespostaGrupoCaixaDeTexto.setText(listaDeCartas.get(g).getResposta());
        cartasDoGrupoPainel.setVisible(false);
        editarCartasGrupoPainel.setVisible(true);
    }//GEN-LAST:event_irEditarCartasDoGrupoButtonActionPerformed

    private void criarCartasGrupoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criarCartasGrupoButtonActionPerformed
        // TODO add your handling code here:
        criarCardsGrupoPanel.setVisible(true);
        cartasDoGrupoPainel.setVisible(false);
    }//GEN-LAST:event_criarCartasGrupoButtonActionPerformed

    private void confirmarCriarCardButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarCriarCardButton1ActionPerformed
        try {
            boolean cartaNaLista = false;
            if (!inserirPerguntaGrupoCaixaDeTexto.getText().equals("") && !inserirRespostaGrupoCaixaDeTexto.getText().equals("")) {
                for (Carta carta : listaDeCartas) {
                    if (carta.getPergunta().equals(inserirPerguntaGrupoCaixaDeTexto.getText())) {
                        cartaNaLista = true;
                        break;
                    }
                }
                if (!cartaNaLista) {
                    Carta carta = new Carta(0, inserirPerguntaGrupoCaixaDeTexto.getText(), inserirRespostaGrupoCaixaDeTexto.getText(), listaDeBaralhosGrupo.get(f), 0, 0, 0);
                    carta = CardDAO.criarCard(carta);
                    listaDeCartas.add(carta);
                    Object[] dados = {carta.getPergunta(), carta.getResposta()
                    };
                    dtmCartasDoGrupo.addRow(dados);
                    ordenar = new TableRowSorter<>(dtmCartasDoGrupo);
                    cartasDoGrupoTable.setRowSorter(ordenar);
                    ArrayList<RowSorter.SortKey> chaveOrdenada = new ArrayList<>();
                    chaveOrdenada.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
                    ordenar.setSortKeys(chaveOrdenada);
                    ordenar.sort();
                    cartasDoGrupoLabel.setText("Cards");
                    cartasDoGrupoTable.getTableHeader().setVisible(true);
                    mensagemCriarCardGrupoInvalidoLabel.setText("");
                    inserirPerguntaGrupoCaixaDeTexto.setText("");
                    inserirRespostaGrupoCaixaDeTexto.setText("");
                    cartasDoGrupoPainel.setVisible(true);
                    criarCardsGrupoPanel.setVisible(false);
                } else {
                    mensagemCriarCardGrupoInvalidoLabel.setText("Pergunta já adicionada");
                }
            } else {
                mensagemCriarCardGrupoInvalidoLabel.setText("Insira as informações");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_confirmarCriarCardButton1ActionPerformed

    private void voltarMeusCardsButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusCardsButton1ActionPerformed
        mensagemCriarCardGrupoInvalidoLabel.setText("");
        inserirPerguntaGrupoCaixaDeTexto.setText("");
        inserirRespostaGrupoCaixaDeTexto.setText("");
        cartasDoGrupoPainel.setVisible(true);
        criarCardsGrupoPanel.setVisible(false);
    }//GEN-LAST:event_voltarMeusCardsButton1ActionPerformed

    private void excluirBaralhoGrupoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirBaralhoGrupoButtonActionPerformed
        mensagemBaralhoDoGrupoSemCartas.setText("");
        linhaSelecionadaExcluirBaralhoGrupo = baralhosDoGrupoTable.getSelectedRow();
        if (baralhosDoGrupoTable.isEditing()) {
            baralhosDoGrupoTable.getCellEditor().stopCellEditing();
        }
        dtmBaralhosDoGrupo = (DefaultTableModel) baralhosDoGrupoTable.getModel();
        String nomeBaralho = (String) dtmBaralhosDoGrupo.getValueAt(linhaSelecionadaExcluirBaralhoGrupo, 0);

        for (Baralho baralho : listaDeBaralhosGrupo) {
            if (baralho.getNomeBaralho().equals(nomeBaralho)) {
                baralhoParaExcluirGrupo = baralho;
                break;
            }
        }
        confirmarExcluirBaralhoGrupoPainel.setVisible(true);
        baralhosDoGrupoPainel.setVisible(false);
    }//GEN-LAST:event_excluirBaralhoGrupoButtonActionPerformed

    private void excluirGruposButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirGruposButtonActionPerformed
        linhaSelecionada = meusGruposTable.getSelectedRow();
        if (meusGruposTable.isEditing()) {
            meusGruposTable.getCellEditor().stopCellEditing();
        }
        dtmGrupos = (DefaultTableModel) meusGruposTable.getModel();
        String nomeGrupo = (String) dtmGrupos.getValueAt(linhaSelecionada, 0);

        for (Grupo grupo : listaDeGrupos) {
            if (grupo.getNomeGrupo().equals(nomeGrupo)) {
                grupoParaExcluir = grupo;
                break;
            }
        }
        confirmarExcluirGrupoPainel.setVisible(true);
        meusGruposPainel.setVisible(false);

    }//GEN-LAST:event_excluirGruposButtonActionPerformed

    private void confirmarEditarCartasGrupoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarEditarCartasGrupoButtonActionPerformed
        try {
            boolean perguntaJaExiste = false;
            if (editarPerguntaGrupoCaixaDeTexto.getText().equals("") || editarRespostaGrupoCaixaDeTexto.getText().equals("")) {
                mensagemEditarPerguntaGrupoInvalida.setText("Insira os valores");
            } else if (listaDeCartas.get(g).getPergunta().equals(editarPerguntaGrupoCaixaDeTexto.getText()) && listaDeCartas.get(g).getResposta().equals(editarRespostaGrupoCaixaDeTexto.getText())) {
                cartasDoGrupoPainel.setVisible(true);
                editarCartasGrupoPainel.setVisible(false);
            } else {
                for (Carta carta : listaDeCartas) {
                    if (carta.getPergunta().equals(editarPerguntaGrupoCaixaDeTexto.getText())) {
                        if (carta != listaDeCartas.get(g)) {
                            System.out.println("caiu nesse caso");
                            perguntaJaExiste = true;
                            break;
                        }
                    }
                }
                if (!perguntaJaExiste) {
                    listaDeCartas.get(g).setPergunta(editarPerguntaGrupoCaixaDeTexto.getText());
                    listaDeCartas.get(g).setResposta(editarRespostaGrupoCaixaDeTexto.getText());
                    CardDAO.editar(listaDeCartas.get(g));
                    dtmCartasDoGrupo.setRowCount(0);
                    for (Carta carta : listaDeCartas) {
                        Object[] linha = {
                            carta.getPergunta(),
                            carta.getResposta()
                        };
                        dtmCartasDoGrupo.addRow(linha);
                    }
                    ordenar = new TableRowSorter<>(dtmCartasDoGrupo);
                    cartasDoGrupoTable.setRowSorter(ordenar);
                    ArrayList<RowSorter.SortKey> chaveOrdenada = new ArrayList<>();
                    chaveOrdenada.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
                    ordenar.setSortKeys(chaveOrdenada);
                    ordenar.sort();
                    editarRespostaGrupoCaixaDeTexto.setText("");
                    editarPerguntaGrupoCaixaDeTexto.setText("");
                    mensagemEditarPerguntaGrupoInvalida.setText("");
                    cartasDoGrupoPainel.setVisible(true);
                    editarCartasGrupoPainel.setVisible(false);
                } else {
                    mensagemEditarPerguntaGrupoInvalida.setText("Pergunta já adicionada");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_confirmarEditarCartasGrupoButtonActionPerformed

    private void voltarCardsDoGrupoPainelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarCardsDoGrupoPainelActionPerformed
        editarRespostaGrupoCaixaDeTexto.setText("");
        editarPerguntaGrupoCaixaDeTexto.setText("");
        mensagemEditarPerguntaGrupoInvalida.setText("");
        cartasDoGrupoPainel.setVisible(true);
        editarCartasGrupoPainel.setVisible(false);
    }//GEN-LAST:event_voltarCardsDoGrupoPainelActionPerformed

    private void excluirCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirCardButtonActionPerformed
        linhaSelecionadaExcluirCartaGrupo = cartasDoGrupoTable.getSelectedRow();
        if (cartasDoGrupoTable.isEditing()) {
            cartasDoGrupoTable.getCellEditor().stopCellEditing();
        }
        dtmCartasDoGrupo = (DefaultTableModel) cartasDoGrupoTable.getModel();
        String pergunta = (String) dtmCartasDoGrupo.getValueAt(linhaSelecionadaExcluirCartaGrupo, 0);

        for (Carta carta : listaDeCartas) {
            if (carta.getPergunta().equals(pergunta)) {
                cartaParaExcluirGrupo = carta;
                break;
            }
        }
        cartasDoGrupoPainel.setVisible(false);
        confirmarExcluirCartaGrupoPainel.setVisible(true);
    }//GEN-LAST:event_excluirCardButtonActionPerformed

    private void modoInserirRespostaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modoInserirRespostaButtonActionPerformed
        conteudoCartaLabel2.setText("<html>" + listaDeCartas.get(0).getPergunta() + "</html>");
        confirmarRespostaButton.setText("Confirmar");
        mensagemRespostaInvalida.setText("");
        acertosJogo = 0;
        errosJogo = 0;
        indice[0] = 0;
        virarCardButton.setText("Ver resposta");
        if (indice[0] == listaDeCartas.size() - 1) {
            pularCarta.setVisible(false);
        }
        painelJogoInserir.setVisible(true);
        selecionarModoDeJogoPainel.setVisible(false);
    }//GEN-LAST:event_modoInserirRespostaButtonActionPerformed

    private void modoDeJogoInvertidoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modoDeJogoInvertidoButtonActionPerformed
        conteudoCartaLabel.setText("<html>" + listaDeCartas.get(0).getResposta() + "</html>");
        painelJogoNormal.setVisible(true);
        cartaAnteriorButton.setVisible(false);
        acerteiButton.setVisible(false);
        erreiButton.setVisible(false);
        acertosJogo = 0;
        errosJogo = 0;
        indice[0] = 0;
        virarCardButton.setText("Ver pergunta");
        proximaCartaButton.setVisible(true);
        if (listaDeCartas.size() == 1) {
            proximaCartaButton.setVisible(false);
        }
        selecionarModoDeJogoPainel.setVisible(false);
    }//GEN-LAST:event_modoDeJogoInvertidoButtonActionPerformed

    private void voltarMeusGruposButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusGruposButton1ActionPerformed
        selecionarModoDeJogoPainel.setVisible(false);
        selecionarBaralhoJogarPainel.setVisible(true);
    }//GEN-LAST:event_voltarMeusGruposButton1ActionPerformed

    private void modoDeJogoNormalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modoDeJogoNormalButtonActionPerformed
        conteudoCartaLabel.setText("<html>" + listaDeCartas.get(0).getPergunta() + "</html>");
        painelJogoNormal.setVisible(true);
        cartaAnteriorButton.setVisible(false);
        acerteiButton.setVisible(false);
        erreiButton.setVisible(false);
        acertosJogo = 0;
        errosJogo = 0;
        indice[0] = 0;
        virarCardButton.setText("Ver resposta");
        proximaCartaButton.setVisible(true);
        if (listaDeCartas.size() == 1) {
            proximaCartaButton.setVisible(false);
        }
        selecionarModoDeJogoPainel.setVisible(false);
    }//GEN-LAST:event_modoDeJogoNormalButtonActionPerformed

    private void modoInserirRespostaButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modoInserirRespostaButton1ActionPerformed
        conteudoCartaLabel3.setText("<html>" + listaDeCartas.get(0).getPergunta() + "</html>");
        confirmarRespostaButton1.setText("Confirmar");
        mensagemRespostaInvalida1.setText("");
        acertosJogo = 0;
        errosJogo = 0;
        indice[0] = 0;
        virarCardButton1.setText("Ver resposta");
        if (indice[0] == listaDeCartas.size() - 1) {
            pularCarta1.setVisible(false);
        }
        painelJogoInserir1.setVisible(true);
        selecionarModoDeJogoGrupoPainel.setVisible(false);
    }//GEN-LAST:event_modoInserirRespostaButton1ActionPerformed

    private void modoDeJogoInvertidoButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modoDeJogoInvertidoButton1ActionPerformed
        conteudoCartaLabel1.setText("<html>" + listaDeCartas.get(0).getResposta() + "</html>");
        jogoBaralhosDoGrupoPainel.setVisible(true);
        cartaAnteriorButton1.setVisible(false);
        acerteiButton1.setVisible(false);
        erreiButton1.setVisible(false);
        acertosJogo = 0;
        errosJogo = 0;
        indice[0] = 0;
        virarCardButton1.setText("Ver pergunta");
        proximaCartaButton1.setVisible(true);
        if (listaDeCartas.size() == 1) {
            proximaCartaButton1.setVisible(false);
        }
        selecionarModoDeJogoGrupoPainel.setVisible(false);
    }//GEN-LAST:event_modoDeJogoInvertidoButton1ActionPerformed

    private void voltarMeusGruposButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusGruposButton3ActionPerformed
        // TODO add your handling code here:
        baralhosDoGrupoPainel.setVisible(true);
        selecionarModoDeJogoGrupoPainel.setVisible(false);
    }//GEN-LAST:event_voltarMeusGruposButton3ActionPerformed

    private void modoDeJogoNormalButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modoDeJogoNormalButton1ActionPerformed
        conteudoCartaLabel1.setText("<html>" + listaDeCartas.get(0).getPergunta() + "</html>");
        jogoBaralhosDoGrupoPainel.setVisible(true);
        cartaAnteriorButton1.setVisible(false);
        acerteiButton1.setVisible(false);
        erreiButton1.setVisible(false);
        acertosJogo = 0;
        errosJogo = 0;
        indice[0] = 0;
        virarCardButton1.setText("Ver resposta");
        proximaCartaButton1.setVisible(true);
        if (listaDeCartas.size() == 1) {
            proximaCartaButton1.setVisible(false);
        }
        selecionarModoDeJogoGrupoPainel.setVisible(false);
    }//GEN-LAST:event_modoDeJogoNormalButton1ActionPerformed

    private void cartaAnteriorButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartaAnteriorButton1ActionPerformed
        proximaCartaButton1.setVisible(true);
        if (indice[0] > 1) {
            indice[0]--;
        } else {
            indice[0]--;
            cartaAnteriorButton1.setVisible(false);
        }
        if ((virarCardButton1.getText().equals("Ver pergunta") && acerteiButton1.isVisible()) || (virarCardButton1.getText().equals("Ver resposta") && !acerteiButton1.isVisible())) {
            virarCardButton1.setText("Ver resposta");
            conteudoCartaLabel1.setText("<html>" + listaDeCartas.get(indice[0]).getPergunta() + "</html>");
        } else {
            virarCardButton1.setText("Ver pergunta");
            conteudoCartaLabel1.setText("<html>" + listaDeCartas.get(indice[0]).getResposta() + "</html>");
        }
        acerteiButton1.setVisible(false);
        erreiButton1.setVisible(false);
    }//GEN-LAST:event_cartaAnteriorButton1ActionPerformed

    private void finalizarJogoButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarJogoButton1ActionPerformed
        jogoBaralhosDoGrupoPainel.setVisible(false);
        painelJogoFinalizado1.setVisible(true);
        estatisticasJogoLabel1.setText(String.format("Você acertou %d/%d questões", acertosJogo, acertosJogo + errosJogo));
    }//GEN-LAST:event_finalizarJogoButton1ActionPerformed

    private void proximaCartaButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proximaCartaButton1ActionPerformed
        cartaAnteriorButton1.setVisible(true);
        if (indice[0] < listaDeCartas.size() - 2) {
            indice[0]++;
        } else {
            indice[0]++;
            proximaCartaButton1.setVisible(false);
        }
        if ((virarCardButton1.getText().equals("Ver pergunta") && acerteiButton1.isVisible()) || (virarCardButton1.getText().equals("Ver resposta") && !acerteiButton1.isVisible())) {
            virarCardButton1.setText("Ver resposta");
            conteudoCartaLabel1.setText("<html>" + listaDeCartas.get(indice[0]).getPergunta() + "</html>");
        } else {
            virarCardButton1.setText("Ver pergunta");
            conteudoCartaLabel1.setText("<html>" + listaDeCartas.get(indice[0]).getResposta() + "<html>");
        }
        acerteiButton1.setVisible(false);
        erreiButton1.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_proximaCartaButton1ActionPerformed

    private void acerteiButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acerteiButton1ActionPerformed
        try {
            cartaJogo = listaDeCartas.get(indice[0]);
            if (!cartaJogo.isRespondida()) {
                acertosJogo++;
                cartaJogo.setRespondida(true);
                cartaJogo.setAcertou(true);
            } else if (!cartaJogo.isAcertou()) {
                cartaJogo.setAcertou(true);
                errosJogo--;
                acertosJogo++;
            }
            indice[0]++;
            if (indice[0] < listaDeCartas.size()) {
                if (virarCardButton1.getText().equals("Ver pergunta")) {
                    conteudoCartaLabel1.setText("<html>" + listaDeCartas.get(indice[0]).getPergunta() + "</html>");
                    virarCardButton1.setText("Ver resposta");
                } else {
                    conteudoCartaLabel1.setText("<html>" + listaDeCartas.get(indice[0]).getResposta() + "</html>");
                    virarCardButton1.setText("Ver pergunta");
                }
                cartaAnteriorButton1.setVisible(true);
                if (indice[0] == listaDeCartas.size() - 1) {
                    proximaCartaButton1.setVisible(false);
                }
                cartaAnteriorButton1.setVisible(true);
                acerteiButton1.setVisible(false);
                erreiButton1.setVisible(false);
            } else {
                jogoBaralhosDoGrupoPainel.setVisible(false);
                painelJogoFinalizado1.setVisible(true);
                estatisticasJogoLabel1.setText(String.format("Você acertou %d/%d questões", acertosJogo, (acertosJogo + errosJogo)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_acerteiButton1ActionPerformed

    private void erreiButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_erreiButton1ActionPerformed
        try {
            cartaJogo = listaDeCartas.get(indice[0]);
            if (!cartaJogo.isRespondida()) {
                errosJogo++;
                cartaJogo.setRespondida(true);
            } else if (cartaJogo.isAcertou()) {
                errosJogo++;
                acertosJogo--;
                cartaJogo.setAcertou(false);
            }
            indice[0]++;
            if (indice[0] < listaDeCartas.size()) {
                if (virarCardButton1.getText().equals("Ver pergunta")) {
                    conteudoCartaLabel1.setText("<html>" + listaDeCartas.get(indice[0]).getPergunta() + "</html>");
                    virarCardButton1.setText("Ver resposta");
                } else {
                    conteudoCartaLabel1.setText("<html>" + listaDeCartas.get(indice[0]).getResposta() + "</html>");
                    virarCardButton1.setText("Ver pergunta");
                }
                cartaAnteriorButton1.setVisible(true);
                if (indice[0] == listaDeCartas.size() - 1) {
                    proximaCartaButton1.setVisible(false);
                }
                cartaAnteriorButton1.setVisible(true);
                acerteiButton1.setVisible(false);
                erreiButton1.setVisible(false);
            } else {
                jogoBaralhosDoGrupoPainel.setVisible(false);
                painelJogoFinalizado1.setVisible(true);
                estatisticasJogoLabel1.setText(String.format("Você acertou %d/%d questões", acertosJogo, acertosJogo + errosJogo));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }           // TODO add your handling code here:
    }//GEN-LAST:event_erreiButton1ActionPerformed

    private void virarCardButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_virarCardButton1ActionPerformed
        if (virarCardButton1.getText().equals("Ver pergunta")) {
            conteudoCartaLabel1.setText("<html>" + listaDeCartas.get(indice[0]).getPergunta() + "</html>");
            virarCardButton1.setText("Ver resposta");
        } else {
            conteudoCartaLabel1.setText("<html>" + listaDeCartas.get(indice[0]).getResposta() + "</html>");
            virarCardButton1.setText("Ver pergunta");
        }
        if (acerteiButton1.isVisible()) {
            acerteiButton1.setVisible(false);
            erreiButton1.setVisible(false);
        } else {
            acerteiButton1.setVisible(true);
            erreiButton1.setVisible(true);
        }
    }//GEN-LAST:event_virarCardButton1ActionPerformed

    private void terminarJogoVoltarPainelButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminarJogoVoltarPainelButton1ActionPerformed
        baralhosDoGrupoPainel.setVisible(true);
        painelJogoFinalizado1.setVisible(false);
    }//GEN-LAST:event_terminarJogoVoltarPainelButton1ActionPerformed

    private void finalizarJogoButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarJogoButton2ActionPerformed
        try {
            inserirRespostaTF.setText("");
            pularCarta.setVisible(true);
            mensagemRespostaInvalida.setText("");
            painelJogoInserir.setVisible(false);
            painelJogoFinalizado.setVisible(true);
            estatisticasJogoLabel.setText(String.format("Você acertou %d/%d questões", acertosJogo, acertosJogo + errosJogo));
            int acertosTotal = listaDeBaralhos.get(a).getTotalDeAcertos();
            int errosTotal = listaDeBaralhos.get(a).getTotalDeErros();
            acertosTotal += acertosJogo;
            errosTotal += errosJogo;
            listaDeBaralhos.get(a).setTotalDeAcertos(acertosTotal);
            listaDeBaralhos.get(a).setTotalDeErros(errosTotal);
            double media = (double) acertosTotal / (acertosTotal + errosTotal) * 100;
            double mediaFormatada = Math.round(media * 100) / 100.0;
            listaDeBaralhos.get(a).setMediaDeAcertos(mediaFormatada);
            BaralhoDAO.editar(listaDeBaralhos.get(a));
        } catch (Exception e) {
        }        // TODO add your handling code here:
    }//GEN-LAST:event_finalizarJogoButton2ActionPerformed

    private void pularCartaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pularCartaActionPerformed
        if (indice[0] < listaDeCartas.size() - 2) {
            indice[0]++;
        } else {
            indice[0]++;
            pularCarta.setVisible(false);
        }
        conteudoCartaLabel2.setText("<html>" + listaDeCartas.get(indice[0]).getPergunta() + "</html>");
        mensagemRespostaInvalida.setText("");
        inserirRespostaTF.setText("");
        confirmarRespostaButton.setText("Confirmar");
    }//GEN-LAST:event_pularCartaActionPerformed

    private void confirmarRespostaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarRespostaButtonActionPerformed
        try {
            switch (confirmarRespostaButton.getText()) {
                case "Confirmar" -> {
                    if (inserirRespostaTF.getText().equals("")) {
                        mensagemRespostaInvalida.setText("Insira a resposta");
                    } else {
                        cartaJogo = listaDeCartas.get(indice[0]);
                        String resposta = Normalizer.normalize(inserirRespostaTF.getText().trim(), Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
                        conteudoCartaLabel2.setText("<html>" + listaDeCartas.get(indice[0]).getResposta() + "</html>");
                        String respostaCorreta = Normalizer.normalize(listaDeCartas.get(indice[0]).getResposta().trim(), Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
                        if (resposta.equals(respostaCorreta)) {
                            mensagemRespostaInvalida.setText("Acertou!");
                            acertosJogo++;
                            int acerto = cartaJogo.getTotalDeAcertos() + 1;
                            int erro = cartaJogo.getTotalDeErros();
                            cartaJogo.setTotalDeAcertos(acerto);
                            double media = (double) acerto / (acerto + erro) * 100;
                            cartaJogo.setMediaDeAcertos(Math.round(media * 100) / 100.0);
                            CardDAO.editar(cartaJogo);
                        } else {
                            errosJogo++;
                            int acerto = cartaJogo.getTotalDeAcertos();
                            int erro = cartaJogo.getTotalDeErros() + 1;
                            cartaJogo.setTotalDeErros(erro);
                            double media = (double) acerto / (acerto + erro) * 100;
                            cartaJogo.setMediaDeAcertos(Math.round(media * 100) / 100.0);
                            CardDAO.editar(cartaJogo);
                            mensagemRespostaInvalida.setText("Errou...");
                        }
                        indice[0]++;
                        if (indice[0] < listaDeCartas.size()) {
                            confirmarRespostaButton.setText("Próxima");
                        } else {
                            confirmarRespostaButton.setText("Finalizar");
                        }
                    }
                }
                case "Próxima" -> {
                    inserirRespostaTF.setText("");
                    if (indice[0] == listaDeCartas.size() - 1) {
                        pularCarta.setVisible(false);
                    }
                    confirmarRespostaButton.setText("Confirmar");
                    mensagemRespostaInvalida.setText("");
                    conteudoCartaLabel2.setText("<html>" + listaDeCartas.get(indice[0]).getPergunta() + "</html>");
                }
                default -> {
                    try {
                        inserirRespostaTF.setText("");
                        pularCarta.setVisible(true);
                        mensagemRespostaInvalida.setText("");
                        painelJogoInserir.setVisible(false);
                        painelJogoFinalizado.setVisible(true);
                        estatisticasJogoLabel.setText(String.format("Você acertou %d/%d questões", acertosJogo, acertosJogo + errosJogo));
                        int acertosTotal = listaDeBaralhos.get(a).getTotalDeAcertos();
                        int errosTotal = listaDeBaralhos.get(a).getTotalDeErros();
                        acertosTotal += acertosJogo;
                        errosTotal += errosJogo;
                        listaDeBaralhos.get(a).setTotalDeAcertos(acertosTotal);
                        listaDeBaralhos.get(a).setTotalDeErros(errosTotal);
                        double media = (double) acertosTotal / (acertosTotal + errosTotal) * 100;
                        double mediaFormatada = Math.round(media * 100) / 100.0;
                        listaDeBaralhos.get(a).setMediaDeAcertos(mediaFormatada);
                        BaralhoDAO.editar(listaDeBaralhos.get(a));
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
        }
// TODO add your handling code here:
    }//GEN-LAST:event_confirmarRespostaButtonActionPerformed

    private void inserirRespostaTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirRespostaTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inserirRespostaTFActionPerformed

    private void finalizarJogoButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarJogoButton3ActionPerformed
        inserirRespostaTF1.setText("");
        pularCarta1.setVisible(true);
        mensagemRespostaInvalida1.setText("");
        painelJogoInserir1.setVisible(false);
        painelJogoFinalizado1.setVisible(true);
        estatisticasJogoLabel1.setText(String.format("Você acertou %d/%d questões", acertosJogo, acertosJogo + errosJogo));
    }//GEN-LAST:event_finalizarJogoButton3ActionPerformed

    private void pularCarta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pularCarta1ActionPerformed
        if (indice[0] < listaDeCartas.size() - 2) {
            indice[0]++;
        } else {
            indice[0]++;
            pularCarta1.setVisible(false);
        }
        conteudoCartaLabel3.setText("<html>" + listaDeCartas.get(indice[0]).getPergunta() + "</html>");
        mensagemRespostaInvalida1.setText("");
        inserirRespostaTF1.setText("");
        confirmarRespostaButton1.setText("Confirmar");
    }//GEN-LAST:event_pularCarta1ActionPerformed

    private void confirmarRespostaButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarRespostaButton1ActionPerformed
        switch (confirmarRespostaButton1.getText()) {
            case "Confirmar" -> {
                if (inserirRespostaTF1.getText().equals("")) {
                    mensagemRespostaInvalida1.setText("Insira a resposta");
                } else {
                    cartaJogo = listaDeCartas.get(indice[0]);
                    String resposta = Normalizer.normalize(inserirRespostaTF.getText().trim(), Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
                    conteudoCartaLabel3.setText("<html>" + listaDeCartas.get(indice[0]).getResposta() + "</html>");
                    String respostaCorreta = Normalizer.normalize(listaDeCartas.get(indice[0]).getResposta().trim(), Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
                    if (resposta.equals(respostaCorreta)) {
                        mensagemRespostaInvalida1.setText("Acertou!");
                        acertosJogo++;
                    } else {
                        errosJogo++;
                        mensagemRespostaInvalida1.setText("Errou...");
                    }
                    indice[0]++;
                    if (indice[0] < listaDeCartas.size()) {
                        confirmarRespostaButton1.setText("Próxima");
                    } else {
                        confirmarRespostaButton1.setText("Finalizar");
                    }
                }
            }
            case "Próxima" -> {
                inserirRespostaTF1.setText("");
                if (indice[0] == listaDeCartas.size() - 1) {
                    pularCarta1.setVisible(false);
                }
                confirmarRespostaButton1.setText("Confirmar");
                mensagemRespostaInvalida1.setText("");
                conteudoCartaLabel3.setText("<html>" + listaDeCartas.get(indice[0]).getPergunta() + "</html>");
            }
            default -> {
                inserirRespostaTF1.setText("");
                pularCarta1.setVisible(true);
                mensagemRespostaInvalida1.setText("");
                painelJogoInserir1.setVisible(false);
                painelJogoFinalizado1.setVisible(true);
                estatisticasJogoLabel1.setText(String.format("Você acertou %d/%d questões", acertosJogo, acertosJogo + errosJogo));
            }
        }
    }//GEN-LAST:event_confirmarRespostaButton1ActionPerformed

    private void inserirRespostaTF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirRespostaTF1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inserirRespostaTF1ActionPerformed

    private void editarUsuarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarUsuarioButtonActionPerformed
        editarUsuarioPainel.setVisible(true);
        painelInicial.setVisible(false);
        editarUsuarioButton.setVisible(false);
        excluirAlunoBotao.setVisible(false);
    }//GEN-LAST:event_editarUsuarioButtonActionPerformed

    private void editarNomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarNomeButtonActionPerformed
        editarNomeUsuarioPainel.setVisible(true);
        nomeUsuarioTF.setText(usuario.getNomeUsuario());
        editarUsuarioPainel.setVisible(false);
    }//GEN-LAST:event_editarNomeButtonActionPerformed

    private void editarEmailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarEmailButtonActionPerformed
        editarEmailUsuarioPainel.setVisible(true);
        emailUsuarioTF.setText(usuario.getEmail());
        editarUsuarioPainel.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_editarEmailButtonActionPerformed

    private void voltarPainelInicialButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarPainelInicialButton2ActionPerformed
        // TODO add your handling code here:
        editarUsuarioPainel.setVisible(false);
        painelInicial.setVisible(true);
        editarUsuarioButton.setVisible(true);
        if (usuario.getTipoUsuario().equals("professor")) {
            excluirAlunoBotao.setVisible(true);
        }
    }//GEN-LAST:event_voltarPainelInicialButton2ActionPerformed

    private void editarSenhaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarSenhaButtonActionPerformed
        editarSenhaPainel.setVisible(true);
        editarUsuarioPainel.setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_editarSenhaButtonActionPerformed

    private void confirmarEditarNomeUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarEditarNomeUsuarioActionPerformed
        String nomeAntigo = "";
        try {
            if (nomeUsuarioTF.getText().equals("")) {
                mensagemEditarNomeUsuarioInvalido.setText("Insira um nome válido");
            } else {
                if (!nomeUsuarioTF.getText().equals(usuario.getNomeUsuario())) {
                    nomeAntigo = usuario.getNomeUsuario();
                    usuario.setNomeUsuario(nomeUsuarioTF.getText());
                    UsuarioDAO.editar(usuario);
                }
                editarUsuarioButton.setVisible(true);
                if (usuario.getTipoUsuario().equals("professor")) {
                    excluirAlunoBotao.setVisible(true);
                }
                mensagemEditarNomeUsuarioInvalido.setText("");
                editarNomeUsuarioPainel.setVisible(false);
                painelInicial.setVisible(true);

            }
        } catch (Exception e) {
            usuario.setNomeUsuario(nomeAntigo);
            mensagemEditarNomeUsuarioInvalido.setText("Nome já registrado");
        }
    }//GEN-LAST:event_confirmarEditarNomeUsuarioActionPerformed

    private void voltarEditarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarEditarUsuarioActionPerformed
        editarNomeUsuarioPainel.setVisible(false);
        mensagemEditarNomeUsuarioInvalido.setText("");
        editarUsuarioPainel.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_voltarEditarUsuarioActionPerformed

    private void confirmarEditarEmailUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarEditarEmailUsuarioActionPerformed
        String emailAntigo = "";
        try {
            if (emailUsuarioTF.getText().equals("")) {
                mensagemEditarEmailInvalido.setText("Insira um email");
            } else {
                if (!emailUsuarioTF.getText().equals(usuario.getEmail())) {
                    emailAntigo = usuario.getEmail();
                    String validacao = validarEmail(emailUsuarioTF.getText());
                    if (validacao.equals("ok")) {
                        usuario.setEmail(emailUsuarioTF.getText());
                        UsuarioDAO.editar(usuario);
                        editarUsuarioButton.setVisible(true);
                        if (usuario.getTipoUsuario().equals("professor")) {
                            excluirAlunoBotao.setVisible(true);
                        }
                        editarEmailUsuarioPainel.setVisible(false);
                        mensagemEditarEmailInvalido.setText("");
                        painelInicial.setVisible(true);
                    } else {
                        mensagemEditarEmailInvalido.setText("Insira um email válido");
                    }

                } else {
                    editarUsuarioButton.setVisible(true);
                    if (usuario.getTipoUsuario().equals("professor")) {
                        excluirAlunoBotao.setVisible(true);
                    }
                    editarEmailUsuarioPainel.setVisible(false);
                    mensagemEditarEmailInvalido.setText("");
                    painelInicial.setVisible(true);
                }
            }
        } catch (Exception e) {
            usuario.setEmail(emailAntigo);
            mensagemEditarEmailInvalido.setText("Email já registrado");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_confirmarEditarEmailUsuarioActionPerformed

    private void voltarEditarUsuario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarEditarUsuario1ActionPerformed
        editarEmailUsuarioPainel.setVisible(false);
        mensagemEditarEmailInvalido.setText("");
        editarUsuarioPainel.setVisible(true);
    }//GEN-LAST:event_voltarEditarUsuario1ActionPerformed

    private void confirmarEditarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarEditarSenhaActionPerformed
        try {
            String senhaAntiga = new String(senhaVelhaPF.getPassword());
            String senhaNova = new String(senhaNovaPF.getPassword());
            if (senhaNova.equals("") || senhaAntiga.equals("")) {
                mensagemEditarSenhaInvalido.setText("Preencha os campos");
            } else if (senhaAntiga.equals(usuario.getSenha())) {
                if (!senhaNova.equals(senhaAntiga)) {
                    usuario.setSenha(senhaNova);
                    UsuarioDAO.editar(usuario);
                }
                mensagemEditarSenhaInvalido.setText("");
                painelInicial.setVisible(true);
                editarUsuarioButton.setVisible(true);
                if (usuario.getTipoUsuario().equals("professor")) {
                    excluirAlunoBotao.setVisible(true);
                }
                editarSenhaPainel.setVisible(false);
            } else {
                mensagemEditarSenhaInvalido.setText("Senha antiga incorreta");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_confirmarEditarSenhaActionPerformed

    private void voltarEditarUsuario2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarEditarUsuario2ActionPerformed
        senhaNovaPF.setText("");
        senhaVelhaPF.setText("");
        mensagemEditarSenhaInvalido.setText("");
        editarSenhaPainel.setVisible(false);
        editarUsuarioPainel.setVisible(true);
    }//GEN-LAST:event_voltarEditarUsuario2ActionPerformed

    private void senhaVelhaPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_senhaVelhaPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_senhaVelhaPFActionPerformed

    private void senhaNovaPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_senhaNovaPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_senhaNovaPFActionPerformed

    private void emailUsuarioTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailUsuarioTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailUsuarioTFActionPerformed

    private void importarBaralhoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importarBaralhoButtonActionPerformed
        inserirMateriaTextField.setText("");
        inserirNomeBaralhoTextField.setText("");
        mensagemAdicionarBaralhoInvalido.setText("");
        importarBaralhoPainel.setVisible(true);
        adicionarBaralhosPainel.setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_importarBaralhoButtonActionPerformed

    private void confirmarImportarBaralhoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarImportarBaralhoButtonActionPerformed
        boolean baralhoExiste = false;
        try {
            String codigo = codigoBaralhoTF.getText();
            if (codigo.equals("")) {
                mensagemImportarBaralhoInvalido.setText("Insira um código");
            } else {
                int id = Integer.parseInt(codigo);
                int xRecuperado = recuperarX(id);
                for (Baralho baralho : listaDeBaralhos) {
                    if (baralho.getIdBaralho() == xRecuperado) {
                        codigoBaralhoTF.setText("");
                        mensagemImportarBaralhoInvalido.setText("");
                        meusBaralhosPainel.setVisible(true);
                        importarBaralhoPainel.setVisible(false);
                        baralhoExiste = true;
                        break;
                    }
                }
                if (!baralhoExiste) {
                    Baralho baralho = BaralhoDAO.importar(xRecuperado, usuario);
                    if (baralho != null) {
                        listaDeBaralhos.add(baralho);
                        meusBaralhosLabel.setText("Meus baralhos");
                        Object[] dados = {baralho.getNomeBaralho(), baralho.getTema(), "0%", 0};
                        dtmBaralhos.addRow(dados);
                        codigoBaralhoTF.setText("");
                        mensagemImportarBaralhoInvalido.setText("");
                        importarBaralhoPainel.setVisible(false);
                        ordenar = new TableRowSorter<>(dtmBaralhos);
                        meusBaralhosTable.setRowSorter(ordenar);
                        ArrayList<RowSorter.SortKey> chaveOrdenada = new ArrayList<>();
                        chaveOrdenada.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
                        ordenar.setSortKeys(chaveOrdenada);
                        ordenar.sort();
                        meusBaralhosTable.getTableHeader().setVisible(true);
                        meusBaralhosPainel.setVisible(true);
                    } else {
                        mensagemImportarBaralhoInvalido.setText("Baralho não encontrado");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_confirmarImportarBaralhoButtonActionPerformed

    private void codigoBaralhoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoBaralhoTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigoBaralhoTFActionPerformed

    private void voltarCriarBaralhoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarCriarBaralhoButtonActionPerformed
        mensagemImportarBaralhoInvalido.setText("");
        adicionarBaralhosPainel.setVisible(true);
        importarBaralhoPainel.setVisible(false);
    }//GEN-LAST:event_voltarCriarBaralhoButtonActionPerformed

    private void voltarPainelInicial1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarPainelInicial1ButtonActionPerformed
        meusBaralhosLabel.setText("Meus baralhos");
        meusBaralhosTable.getTableHeader().setVisible(true);
        editarUsuarioButton.setVisible(true);
        if (usuario.getTipoUsuario().equals("professor")) {
            excluirAlunoBotao.setVisible(true);
        }
        meusBaralhosPainel.setVisible(false);
        painelInicial.setVisible(true);
// TODO add your handling code here:
    }//GEN-LAST:event_voltarPainelInicial1ButtonActionPerformed

    private void botaoMusicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoMusicaActionPerformed
        if (musicaTocando) {
            pararMusica();
            botaoMusica.setIcon(imagemSomDesativado);
        } else {
            tocarMusica();
            botaoMusica.setIcon(imagemSomAtivado);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_botaoMusicaActionPerformed

    private void confirmarExcluirBaralhoButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarExcluirBaralhoButton1ActionPerformed
        confirmarSairJogoPainel.setVisible(false);
        autenticarContaPanel.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_confirmarExcluirBaralhoButton1ActionPerformed

    private void voltarMeusBaralhosButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusBaralhosButton3ActionPerformed
        confirmarSairJogoPainel.setVisible(false);
        editarUsuarioButton.setVisible(true);
        if (usuario.getTipoUsuario().equals("professor")) {
            excluirAlunoBotao.setVisible(true);
        }
        painelInicial.setVisible(true);          // TODO add your handling code here:
    }//GEN-LAST:event_voltarMeusBaralhosButton3ActionPerformed

    private void confirmarExcluirBaralhoButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarExcluirBaralhoButton2ActionPerformed

        try {
            GrupoDAO.excluirGrupo(grupoParaExcluir);

            listaDeGrupos.remove(grupoParaExcluir);
            dtmGrupos.removeRow(linhaSelecionada);
            if (listaDeGrupos.isEmpty()) {
                meusGruposLabel.setText("Você ainda não está em nenhum grupo");
                meusGruposTable.getTableHeader().setVisible(false);
            }
            meusGruposPainel.setVisible(true);
            confirmarExcluirGrupoPainel.setVisible(false);
        } catch (Exception e) {
        }        // TODO add your handling code here:
    }//GEN-LAST:event_confirmarExcluirBaralhoButton2ActionPerformed

    private void voltarMeusBaralhosButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusBaralhosButton4ActionPerformed
        meusGruposPainel.setVisible(true);
        confirmarExcluirGrupoPainel.setVisible(false);
    }//GEN-LAST:event_voltarMeusBaralhosButton4ActionPerformed

    private void confirmarExcluirBaralhoButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarExcluirBaralhoButton3ActionPerformed
        try {
            CardDAO.excluir(cartaParaExcluir);

            listaDeCartas.remove(cartaParaExcluir);
            dtmCartas.removeRow(linhaParaExcluirCarta);
            if (listaDeCartas.isEmpty()) {
                meusCardsLabel.setText("Esse baralho ainda não tem cards");
                minhasCartasTable.getTableHeader().setVisible(false);
            }
            meusCardsPanel.setVisible(true);
            confirmarExcluirCartaPainel.setVisible(false);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_confirmarExcluirBaralhoButton3ActionPerformed

    private void voltarMeusBaralhosButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusBaralhosButton5ActionPerformed
        confirmarExcluirCartaPainel.setVisible(false);
        meusCardsPanel.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_voltarMeusBaralhosButton5ActionPerformed

    private void confirmarExcluirBaralhoButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarExcluirBaralhoButton4ActionPerformed
        try {
            GrupoDAO.removerAlunoGrupo(alunoParaExcluir, listaDeGrupos.get(d));

            listaDeAlunosGrupo.remove(alunoParaExcluir);
            dtmAlunosDoGrupo.removeRow(linhaAlunoParaExcluir);
            if (listaDeAlunosGrupo.isEmpty()) {
                alunosDoGrupoLabel.setText("Esse grupo ainda não tem alunos");
                alunosDoGrupoTable.getTableHeader().setVisible(false);
            }
            alunosDoGrupoPainel.setVisible(true);
            confirmarExcluirAlunoGrupoPainel.setVisible(false);
        } catch (Exception e) {
        }        // TODO add your handling code here:
    }//GEN-LAST:event_confirmarExcluirBaralhoButton4ActionPerformed

    private void voltarMeusBaralhosButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusBaralhosButton6ActionPerformed
        alunosDoGrupoPainel.setVisible(true);
        confirmarExcluirAlunoGrupoPainel.setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_voltarMeusBaralhosButton6ActionPerformed

    private void confirmarExcluirBaralhoButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarExcluirBaralhoButton5ActionPerformed
        try {
            CardDAO.excluir(cartaParaExcluirGrupo);

            listaDeCartas.remove(cartaParaExcluirGrupo);
            dtmCartasDoGrupo.removeRow(linhaSelecionadaExcluirCartaGrupo);
            if (listaDeCartas.isEmpty()) {
                cartasDoGrupoLabel.setText("Este baralho não tem cartas");
                cartasDoGrupoTable.getTableHeader().setVisible(false);
            }
            cartasDoGrupoPainel.setVisible(true);
            confirmarExcluirCartaGrupoPainel.setVisible(false);
        } catch (Exception e) {
        }        // TODO add your handling code here:
    }//GEN-LAST:event_confirmarExcluirBaralhoButton5ActionPerformed

    private void voltarMeusBaralhosButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusBaralhosButton7ActionPerformed
        cartasDoGrupoPainel.setVisible(true);
        confirmarExcluirCartaGrupoPainel.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_voltarMeusBaralhosButton7ActionPerformed

    private void confirmarExcluirBaralhoButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarExcluirBaralhoButton6ActionPerformed
        try {
            GrupoDAO.removerBaralhoGrupo(baralhoParaExcluirGrupo, listaDeGrupos.get(d));

            listaDeBaralhosGrupo.remove(baralhoParaExcluirGrupo);
            dtmBaralhosDoGrupo.removeRow(linhaSelecionadaExcluirBaralhoGrupo);
            if (listaDeBaralhosGrupo.isEmpty()) {
                baralhosDoGrupoLabel.setText("Esse grupo ainda não tem baralhos");
                baralhosDoGrupoTable.getTableHeader().setVisible(false);
            }
            confirmarExcluirBaralhoGrupoPainel.setVisible(false);
            baralhosDoGrupoPainel.setVisible(true);
        } catch (Exception e) {
        }        // TODO add your handling code here:
    }//GEN-LAST:event_confirmarExcluirBaralhoButton6ActionPerformed

    private void voltarMeusBaralhosButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusBaralhosButton8ActionPerformed
        confirmarExcluirBaralhoGrupoPainel.setVisible(false);
        baralhosDoGrupoPainel.setVisible(true);
    }//GEN-LAST:event_voltarMeusBaralhosButton8ActionPerformed

    private void excluirContaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirContaButtonActionPerformed
        editarUsuarioPainel.setVisible(false);
        confirmarExcluirContaPainel.setVisible(true);
    }//GEN-LAST:event_excluirContaButtonActionPerformed

    private void confirmarExcluirBaralhoButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarExcluirBaralhoButton7ActionPerformed
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.excluir(usuario);
            autenticarContaPanel.setVisible(true);
            confirmarExcluirContaPainel.setVisible(false);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_confirmarExcluirBaralhoButton7ActionPerformed

    private void voltarMeusBaralhosButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusBaralhosButton9ActionPerformed
        editarUsuarioPainel.setVisible(true);
        confirmarExcluirContaPainel.setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_voltarMeusBaralhosButton9ActionPerformed

    private void excluirAlunoBotaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirAlunoBotaoActionPerformed
        excluirAlunoBotao.setVisible(false);
        excluirContaPainel.setVisible(true);
        editarUsuarioButton.setVisible(false);
        painelInicial.setVisible(false);
    }//GEN-LAST:event_excluirAlunoBotaoActionPerformed

    private void confirmarExcluirUsuarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarExcluirUsuarioButtonActionPerformed
        try {
            String emailUsuario = emailUsuarioParaExcluirTF.getText();
            if (emailUsuario.equals("")) {
                mensagemEmailInvalido.setText("Insira um email");
            } else {
                usuarioDAO = new UsuarioDAO();
                usuarioParaExcluir = GrupoDAO.selecionarAluno(emailUsuario);
                if (usuarioParaExcluir == null) {
                    mensagemEmailInvalido.setText("Usuário não encontrado");
                } else {
                    emailUsuarioParaExcluirTF.setText("");
                    mensagemEmailInvalido.setText("");
                    confirmarExcluirUsuarioPainel.setVisible(true);
                    excluirContaPainel.setVisible(false);
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_confirmarExcluirUsuarioButtonActionPerformed

    private void voltarEditarGrupoAcoesButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarEditarGrupoAcoesButton3ActionPerformed
        emailUsuarioParaExcluirTF.setText("");
        mensagemEmailInvalido.setText("");
        painelInicial.setVisible(true);
        excluirAlunoBotao.setVisible(true);
        editarUsuarioButton.setVisible(true);
        excluirContaPainel.setVisible(false);
    }//GEN-LAST:event_voltarEditarGrupoAcoesButton3ActionPerformed

    private void confirmarExcluirBaralhoButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarExcluirBaralhoButton8ActionPerformed
        try {
            if (usuarioParaExcluir.getIdUsuario() == usuario.getIdUsuario()) {
                autenticarContaPanel.setVisible(true);
                confirmarExcluirUsuarioPainel.setVisible(false);
            } else {
                confirmarExcluirUsuarioPainel.setVisible(false);
                painelInicial.setVisible(true);
                editarUsuarioButton.setVisible(true);
                excluirAlunoBotao.setVisible(true);
            }
            usuarioDAO.excluir(usuarioParaExcluir);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_confirmarExcluirBaralhoButton8ActionPerformed

    private void voltarMeusBaralhosButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusBaralhosButton10ActionPerformed
        confirmarExcluirUsuarioPainel.setVisible(false);
        excluirContaPainel.setVisible(true);
    }//GEN-LAST:event_voltarMeusBaralhosButton10ActionPerformed

    private void editarNomeBaralhoCaixaDeTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarNomeBaralhoCaixaDeTextoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editarNomeBaralhoCaixaDeTextoActionPerformed

    private void botaoSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSairActionPerformed
        for (Component comp : this.getContentPane().getComponents()) {
            if (comp instanceof JPanel) {
                if (comp.isVisible()) {
                    ultimoPainel = (JPanel) comp;
                    comp.setVisible(false);
                    botaoSair.setVisible(false);
                    editarUsuarioButton.setVisible(false);
                    excluirAlunoBotao.setVisible(false);
                    confirmarSairPainel.setVisible(true);
                    break;
                }
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoSairActionPerformed

    private void confirmarExcluirBaralhoButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarExcluirBaralhoButton9ActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_confirmarExcluirBaralhoButton9ActionPerformed

    private void voltarMeusBaralhosButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusBaralhosButton11ActionPerformed
        ultimoPainel.setVisible(true);
        if (ultimoPainel == painelInicial) {
            editarUsuarioButton.setVisible(true);
            if (usuario.getTipoUsuario().equals("professor")) {
                excluirAlunoBotao.setVisible(true);
            }
        }
        botaoSair.setVisible(true);
        confirmarSairPainel.setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_voltarMeusBaralhosButton11ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       new Thread(() -> {
        try {
            Rectangle bounds = this.getBounds();
            Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
            int targetX = screenBounds.x + 10;
            int targetY = screenBounds.y + screenBounds.height - 40;
            int steps = 9;
            int delay = 14;
            for (int i = 0; i < steps; i++) {
                int width = bounds.width - (bounds.width * i / steps);
                int height = bounds.height - (bounds.height * i / steps);
                int x = bounds.x + (targetX - bounds.x) * i / steps;
                int y = bounds.y + (targetY - bounds.y) * i / steps;
                float opacity = 1.0f - ((float) i / steps);
                SwingUtilities.invokeLater(() -> {
                    this.setBounds(x, y, width, height);
                    this.setOpacity(opacity);
                });

                Thread.sleep(delay);
            }
            SwingUtilities.invokeLater(() -> this.setState(JFrame.ICONIFIED));
            SwingUtilities.invokeLater(() -> {
                this.setBounds(bounds);
                this.setOpacity(1.0f);
            });

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }).start();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CriarContaTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CriarContaTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CriarContaTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CriarContaTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CriarContaTela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AdicionarAlunoButton;
    private javax.swing.JLabel EmailLabel;
    private javax.swing.JButton EntrarButton;
    private javax.swing.JLabel SenhaLabel;
    private javax.swing.JButton abrirMeusBaralhosButton;
    private javax.swing.JButton abrirMeusGruposButton;
    private javax.swing.JButton acerteiButton;
    private javax.swing.JButton acerteiButton1;
    private javax.swing.JPanel adicionarAlunoGrupoPainel;
    private javax.swing.JPanel adicionarBaralhosPainel;
    private javax.swing.JPanel adicionarGrupoPainel;
    private javax.swing.JLabel alunosDoGrupoLabel;
    private javax.swing.JPanel alunosDoGrupoPainel;
    private javax.swing.JTable alunosDoGrupoTable;
    private javax.swing.JPanel autenticarContaPanel;
    private javax.swing.JLabel baralhoSemCartasLabel;
    private javax.swing.JLabel baralhosDoGrupoLabel;
    private javax.swing.JPanel baralhosDoGrupoPainel;
    private javax.swing.JTable baralhosDoGrupoTable;
    private javax.swing.JButton botaoMusica;
    private javax.swing.JButton botaoSair;
    private javax.swing.JPasswordField campoConfirmarSenhaPasswordField;
    private javax.swing.JTextField campoEmailAutenticarTextField;
    private javax.swing.JTextField campoEmailTextField;
    private javax.swing.JPasswordField campoSenhaAutenticarPasswordField;
    private javax.swing.JPasswordField campoSenhaPasswordField;
    private javax.swing.JTextField campoUsuarioTextField;
    private javax.swing.JButton cartaAnteriorButton;
    private javax.swing.JButton cartaAnteriorButton1;
    private javax.swing.JLabel cartasDoGrupoLabel;
    private javax.swing.JPanel cartasDoGrupoPainel;
    private javax.swing.JTable cartasDoGrupoTable;
    private javax.swing.JTextField codigoBaralhoTF;
    private javax.swing.JLabel codigoDoBaralhoGrupoLabel;
    private javax.swing.JLabel codigoDoBaralhoLabel;
    private javax.swing.JButton confirmarAdicionarAluno;
    private javax.swing.JButton confirmarCriarBaralhoGrupoButton;
    private javax.swing.JButton confirmarCriarCardButton;
    private javax.swing.JButton confirmarCriarCardButton1;
    private javax.swing.JButton confirmarCriarGrupoButton;
    private javax.swing.JButton confirmarEditarBaralhoButton;
    private javax.swing.JButton confirmarEditarBaralhoGrupoButton;
    private javax.swing.JButton confirmarEditarCartasButton;
    private javax.swing.JButton confirmarEditarCartasButton1;
    private javax.swing.JButton confirmarEditarCartasGrupoButton;
    private javax.swing.JButton confirmarEditarEmailUsuario;
    private javax.swing.JButton confirmarEditarNomeUsuario;
    private javax.swing.JButton confirmarEditarSenha;
    private javax.swing.JPanel confirmarExcluirAlunoGrupoPainel;
    private javax.swing.JButton confirmarExcluirBaralhoButton;
    private javax.swing.JButton confirmarExcluirBaralhoButton1;
    private javax.swing.JButton confirmarExcluirBaralhoButton2;
    private javax.swing.JButton confirmarExcluirBaralhoButton3;
    private javax.swing.JButton confirmarExcluirBaralhoButton4;
    private javax.swing.JButton confirmarExcluirBaralhoButton5;
    private javax.swing.JButton confirmarExcluirBaralhoButton6;
    private javax.swing.JButton confirmarExcluirBaralhoButton7;
    private javax.swing.JButton confirmarExcluirBaralhoButton8;
    private javax.swing.JButton confirmarExcluirBaralhoButton9;
    private javax.swing.JPanel confirmarExcluirBaralhoGrupoPainel;
    private javax.swing.JPanel confirmarExcluirBaralhoPainel;
    private javax.swing.JPanel confirmarExcluirCartaGrupoPainel;
    private javax.swing.JPanel confirmarExcluirCartaPainel;
    private javax.swing.JPanel confirmarExcluirContaPainel;
    private javax.swing.JPanel confirmarExcluirGrupoPainel;
    private javax.swing.JButton confirmarExcluirUsuarioButton;
    private javax.swing.JPanel confirmarExcluirUsuarioPainel;
    private javax.swing.JButton confirmarImportarBaralhoButton;
    private javax.swing.JButton confirmarRespostaButton;
    private javax.swing.JButton confirmarRespostaButton1;
    private javax.swing.JPanel confirmarSairJogoPainel;
    private javax.swing.JPanel confirmarSairPainel;
    private javax.swing.JLabel confirmarSenhaLabel;
    private javax.swing.JLabel conteudoCartaLabel;
    private javax.swing.JLabel conteudoCartaLabel1;
    private javax.swing.JLabel conteudoCartaLabel2;
    private javax.swing.JLabel conteudoCartaLabel3;
    private javax.swing.JButton criarBaralhoButton;
    private javax.swing.JButton criarBaralhoGrupoButton;
    private javax.swing.JPanel criarBaralhoGrupoPainel;
    private javax.swing.JPanel criarCardsGrupoPanel;
    private javax.swing.JPanel criarCardsPanel;
    private javax.swing.JButton criarCartasGrupoButton;
    private javax.swing.JButton criarContaButton;
    private javax.swing.JPanel criarContaPanel;
    private javax.swing.JButton criarGrupoButton;
    private javax.swing.JLabel dadosInvalidosMensagem;
    private javax.swing.JButton editarBaralhoGrupoButton;
    private javax.swing.JPanel editarBaralhoGrupoPainel;
    private javax.swing.JPanel editarBaralhoPainel;
    private javax.swing.JPanel editarCartasGrupoPainel;
    private javax.swing.JPanel editarCartasPainel;
    private javax.swing.JButton editarEmailButton;
    private javax.swing.JPanel editarEmailUsuarioPainel;
    private javax.swing.JPanel editarGrupoPainel;
    private javax.swing.JTextField editarNomeBaralhoCaixaDeTexto;
    private javax.swing.JTextField editarNomeBaralhoGrupoCaixaDeTexto;
    private javax.swing.JButton editarNomeButton;
    private javax.swing.JTextField editarNomeGrupoCaixaDeTexto;
    private javax.swing.JPanel editarNomeUsuarioPainel;
    private javax.swing.JTextField editarPerguntaCaixaDeTexto;
    private javax.swing.JTextField editarPerguntaGrupoCaixaDeTexto;
    private javax.swing.JTextField editarRespostaCaixaDeTexto;
    private javax.swing.JTextField editarRespostaGrupoCaixaDeTexto;
    private javax.swing.JButton editarSenhaButton;
    private javax.swing.JPanel editarSenhaPainel;
    private javax.swing.JTextField editarTemaBaralhoGrupoCaixaDeTexto;
    private javax.swing.JTextField editarTemaCaixaDeTexto;
    private javax.swing.JButton editarUsuarioButton;
    private javax.swing.JPanel editarUsuarioPainel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailUsuarioParaExcluirTF;
    private javax.swing.JTextField emailUsuarioTF;
    private javax.swing.JButton erreiButton;
    private javax.swing.JButton erreiButton1;
    private javax.swing.JLabel estatisticasJogoLabel;
    private javax.swing.JLabel estatisticasJogoLabel1;
    private javax.swing.JButton excluirAlunoBotao;
    private javax.swing.JButton excluirBaralhoGrupoButton;
    private javax.swing.JButton excluirCardButton;
    private javax.swing.JButton excluirContaButton;
    private javax.swing.JPanel excluirContaPainel;
    private javax.swing.JButton excluirGruposButton;
    private javax.swing.JButton finalizarJogoButton;
    private javax.swing.JButton finalizarJogoButton1;
    private javax.swing.JButton finalizarJogoButton2;
    private javax.swing.JButton finalizarJogoButton3;
    private javax.swing.JButton importarBaralhoButton;
    private javax.swing.JPanel importarBaralhoPainel;
    private javax.swing.JTextField inserirEmailAlunoCaixaDeTexto;
    private javax.swing.JTextField inserirMateriaTextField;
    private javax.swing.JTextField inserirNomeBaralhoGrupoCaixaDeTexto;
    private javax.swing.JTextField inserirNomeBaralhoTextField;
    private javax.swing.JTextField inserirNomeGrupoCaixaDeTexto;
    private javax.swing.JTextField inserirPerguntaCaixaDeTexto;
    private javax.swing.JTextField inserirPerguntaGrupoCaixaDeTexto;
    private javax.swing.JTextField inserirRespostaCaixaDeTexto;
    private javax.swing.JTextField inserirRespostaGrupoCaixaDeTexto;
    private javax.swing.JTextField inserirRespostaTF;
    private javax.swing.JTextField inserirRespostaTF1;
    private javax.swing.JTextField inserirTemaBaralhoGrupoCaixaDeTexto;
    private javax.swing.JButton irEditarCartasDoGrupoButton;
    private javax.swing.JButton irEditarGruposButton;
    private javax.swing.JButton irExcluirAlunosGruposButton;
    private javax.swing.JButton irJogarButton;
    private javax.swing.JButton irParaCriarBaralhoButton;
    private javax.swing.JButton irParaCriarCardButton;
    private javax.swing.JButton irParaEditarBaralho;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JButton jaTenhoContaButton;
    private javax.swing.JButton jogarButton;
    private javax.swing.JPanel jogoBaralhosDoGrupoPainel;
    private javax.swing.JLabel labelFundo;
    private javax.swing.JLabel mensagemAdicionarBaralhoGrupoInvalido;
    private javax.swing.JLabel mensagemAdicionarBaralhoInvalido;
    private javax.swing.JLabel mensagemBaralhoDoGrupoSemCartas;
    private javax.swing.JLabel mensagemCriarCardGrupoInvalidoLabel;
    private javax.swing.JLabel mensagemCriarCardInvalidoLabel;
    private javax.swing.JLabel mensagemCriarContaInvalidaLabel;
    private javax.swing.JLabel mensagemCriarGrupoInvalido;
    private javax.swing.JLabel mensagemDesejaExcluirBaralho;
    private javax.swing.JLabel mensagemDesejaExcluirBaralho1;
    private javax.swing.JLabel mensagemDesejaExcluirBaralho2;
    private javax.swing.JLabel mensagemDesejaExcluirBaralho3;
    private javax.swing.JLabel mensagemDesejaExcluirBaralho4;
    private javax.swing.JLabel mensagemDesejaExcluirBaralho5;
    private javax.swing.JLabel mensagemDesejaExcluirBaralho6;
    private javax.swing.JLabel mensagemDesejaExcluirBaralho7;
    private javax.swing.JLabel mensagemDesejaExcluirBaralho8;
    private javax.swing.JLabel mensagemDesejaExcluirBaralho9;
    private javax.swing.JLabel mensagemEditarBaralhoGrupoInvalido;
    private javax.swing.JLabel mensagemEditarBaralhoInvalido;
    private javax.swing.JLabel mensagemEditarEmailInvalido;
    private javax.swing.JLabel mensagemEditarGrupoInvalido;
    private javax.swing.JLabel mensagemEditarNomeUsuarioInvalido;
    private javax.swing.JLabel mensagemEditarPerguntaGrupoInvalida;
    private javax.swing.JLabel mensagemEditarPerguntaInvalida;
    private javax.swing.JLabel mensagemEditarSenhaInvalido;
    private javax.swing.JLabel mensagemEmailAlunoInvalido;
    private javax.swing.JLabel mensagemEmailInvalido;
    private javax.swing.JLabel mensagemImportarBaralhoInvalido;
    private javax.swing.JLabel mensagemRespostaInvalida;
    private javax.swing.JLabel mensagemRespostaInvalida1;
    private javax.swing.JLabel meusBaralhosLabel;
    private javax.swing.JPanel meusBaralhosPainel;
    private javax.swing.JTable meusBaralhosTable;
    private javax.swing.JLabel meusCardsLabel;
    private javax.swing.JPanel meusCardsPanel;
    private javax.swing.JLabel meusGruposLabel;
    private javax.swing.JPanel meusGruposPainel;
    private javax.swing.JTable meusGruposTable;
    private javax.swing.JTable minhasCartasTable;
    private javax.swing.JButton modoDeJogoInvertidoButton;
    private javax.swing.JButton modoDeJogoInvertidoButton1;
    private javax.swing.JButton modoDeJogoNormalButton;
    private javax.swing.JButton modoDeJogoNormalButton1;
    private javax.swing.JButton modoInserirRespostaButton;
    private javax.swing.JButton modoInserirRespostaButton1;
    private javax.swing.JLabel nomeDoBaralhoGrupoLabel;
    private javax.swing.JLabel nomeDoBaralhoLabel;
    private javax.swing.JLabel nomeDoGrupoLabel;
    private javax.swing.JLabel nomeDoGrupoLabel1;
    private javax.swing.JTextField nomeUsuarioTF;
    private javax.swing.JPanel painelInicial;
    private javax.swing.JPanel painelJogoFinalizado;
    private javax.swing.JPanel painelJogoFinalizado1;
    private javax.swing.JPanel painelJogoInserir;
    private javax.swing.JPanel painelJogoInserir1;
    private javax.swing.JPanel painelJogoNormal;
    private javax.swing.JButton proximaCartaButton;
    private javax.swing.JButton proximaCartaButton1;
    private javax.swing.JButton pularCarta;
    private javax.swing.JButton pularCarta1;
    private javax.swing.JButton redirecionarTelaCriarContaButton;
    private javax.swing.JButton sairButton;
    private javax.swing.JPanel selecionarBaralhoJogarPainel;
    private javax.swing.JTable selecionarBaralhoJogarTable;
    private javax.swing.JLabel selecionarBaralhosJogarLabel;
    private javax.swing.JPanel selecionarModoDeJogoGrupoPainel;
    private javax.swing.JPanel selecionarModoDeJogoPainel;
    private javax.swing.JLabel senhaLabel;
    private javax.swing.JPasswordField senhaNovaPF;
    private javax.swing.JPasswordField senhaVelhaPF;
    private javax.swing.JButton terminarJogoVoltarPainelButton;
    private javax.swing.JButton terminarJogoVoltarPainelButton1;
    private javax.swing.JLabel usuarioLabel;
    private javax.swing.JButton virarCardButton;
    private javax.swing.JButton virarCardButton1;
    private javax.swing.JButton voltarBaralhosDoGrupoButton;
    private javax.swing.JButton voltarBaralhosGrupoButton;
    private javax.swing.JButton voltarCardsDoGrupoPainel;
    private javax.swing.JButton voltarCriarBaralhoButton;
    private javax.swing.JButton voltarEditarGrupoAcoesButton;
    private javax.swing.JButton voltarEditarGrupoAcoesButton2;
    private javax.swing.JButton voltarEditarGrupoAcoesButton3;
    private javax.swing.JButton voltarEditarUsuario;
    private javax.swing.JButton voltarEditarUsuario1;
    private javax.swing.JButton voltarEditarUsuario2;
    private javax.swing.JButton voltarMeusBaralhos1;
    private javax.swing.JButton voltarMeusBaralhosButton;
    private javax.swing.JButton voltarMeusBaralhosButton10;
    private javax.swing.JButton voltarMeusBaralhosButton11;
    private javax.swing.JButton voltarMeusBaralhosButton2;
    private javax.swing.JButton voltarMeusBaralhosButton3;
    private javax.swing.JButton voltarMeusBaralhosButton4;
    private javax.swing.JButton voltarMeusBaralhosButton5;
    private javax.swing.JButton voltarMeusBaralhosButton6;
    private javax.swing.JButton voltarMeusBaralhosButton7;
    private javax.swing.JButton voltarMeusBaralhosButton8;
    private javax.swing.JButton voltarMeusBaralhosButton9;
    private javax.swing.JButton voltarMeusBaralhosGrupoButton;
    private javax.swing.JButton voltarMeusCardsButton;
    private javax.swing.JButton voltarMeusCardsButton1;
    private javax.swing.JButton voltarMeusCardsButton2;
    private javax.swing.JButton voltarMeusCardsButton3;
    private javax.swing.JButton voltarMeusGrupos;
    private javax.swing.JButton voltarMeusGrupos1;
    private javax.swing.JButton voltarMeusGruposButton1;
    private javax.swing.JButton voltarMeusGruposButton2;
    private javax.swing.JButton voltarMeusGruposButton3;
    private javax.swing.JButton voltarPainelInicial1Button;
    private javax.swing.JButton voltarPainelInicial1Button1;
    private javax.swing.JButton voltarPainelInicialButton;
    private javax.swing.JButton voltarPainelInicialButton2;
    // End of variables declaration//GEN-END:variables
}

// teste commit
