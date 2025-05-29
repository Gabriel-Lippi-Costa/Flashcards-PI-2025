package telas;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import java.awt.Color;
import java.awt.Image;
import modelo.Baralho;
import modelo.Carta;
import modelo.Usuario;
import persistencia.BaralhoDAO;
import static persistencia.BaralhoDAO.listarBaralhos;
import persistencia.CardDAO;
import persistencia.UsuarioDAO;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import modelo.Grupo;
import persistencia.GrupoDAO;
import telas.AlternarCorLinhasRenderer;
import telas.HeaderRenderer;
import telas.TableActionCellEditor;
import telas.TableActionCellEditorCartas;
import telas.TableActionCellRenderer;
import telas.TableActionCellRendererCartas;
import telas.TableActionEvent;

/**
 *
 * @author zion
 */
public class CriarContaTela extends javax.swing.JFrame {

    /**
     * Creates new form CriarContaTela
     */
    public CriarContaTela() {
        initComponents();
        redirecionarTelaCriarContaButton.setContentAreaFilled(false);
        jaTenhoContaButton.setContentAreaFilled(false);
        if (labelFundo.getIcon() instanceof ImageIcon) {
            ImageIcon labelIcon = (ImageIcon) labelFundo.getIcon();
            Image image = labelIcon.getImage();
            System.out.println("Sucesso");
        } else {
            System.out.println("erro");
        }
        editarBaralhoPainel.setVisible(false);
        painelJogoFinalizado.setVisible(false);
        painelJogoNormal.setVisible(false);
        adicionarGrupoPainel.setVisible(false);
        selecionarBaralhoJogarTable.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        meusBaralhosTable.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        minhasCartasTable.getTableHeader().setDefaultRenderer(new HeaderRenderer());
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
        baralhosDoGrupoPainel.setVisible(false);
        criarCardsPanel.setVisible(false);
        criarContaPanel.setVisible(false);
        adicionarAlunoGrupoPainel.setVisible(false);
        painelInicial.setVisible(false);
        meusBaralhosPainel.setVisible(false);
        meusCardsPanel.setVisible(false);
        meusGruposPainel.setVisible(false);
        adicionarBaralhosPainel.setVisible(false);
        editarCartasPainel.setVisible(false);
        editarGrupoPainel.setVisible(false);
        editarGrupoAcoesPainel.setVisible(false);
        confirmarExcluirBaralhoPainel.setVisible(false);
        selecionarBaralhoJogarPainel.setVisible(false);
        meusGruposTable.getColumnModel().getColumn(2).setCellRenderer(new TableActionCellRendererGrupos());
        meusBaralhosTable.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRenderer());
        minhasCartasTable.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRendererCartas());
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void deletarBaralho(int linha) {
                row = linha;
                if (meusBaralhosTable.isEditing()) {
                    meusBaralhosTable.getCellEditor().stopCellEditing();
                }
                dtmBaralhos = (DefaultTableModel) meusBaralhosTable.getModel();
                nomeBaralho = (String) dtmBaralhos.getValueAt(linha, 0);
                confirmarExcluirBaralhoPainel.setVisible(true);
                meusBaralhosPainel.setVisible(false);
            }

            @Override
            public void verBaralho(int row) {
                DefaultTableModel modelo = (DefaultTableModel) meusBaralhosTable.getModel();
                String nomeBaralho = (String) modelo.getValueAt(row, 0);
                for (i = 0; i < listaDeBaralhos.size(); i++) {
                    if (listaDeBaralhos.get(i).getNomeBaralho().equals(nomeBaralho)) {
                        break;
                    }
                }
                try {
                    nomeDoBaralhoLabel.setText(listaDeBaralhos.get(i).getNomeBaralho());
                    codigoDoBaralhoLabel.setText(String.format("Código do baralho: %d", listaDeBaralhos.get(i).getIdBaralho()));
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
                            carta.getResposta(), carta.getTotalDeAcertos() + carta.getTotalDeErros(), carta.getMediaDeAcertos()};
                        dtmCartas.addRow(linha);
                    }
                    meusCardsPanel.setVisible(true);
                    meusBaralhosPainel.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("erro");
                }
            }

            @Override
            public void deletarCarta(int linha) {
                try {
                    if (minhasCartasTable.isEditing()) {
                        minhasCartasTable.getCellEditor().stopCellEditing();
                    }
                    dtmCartas = (DefaultTableModel) minhasCartasTable.getModel();
                    String pergunta = (String) dtmCartas.getValueAt(linha, 0);

                    Carta cartaParaExcluir = null;
                    for (Carta carta : listaDeCartas) {
                        if (carta.getPergunta().equals(pergunta)) {
                            cartaParaExcluir = carta;
                            break;
                        }
                    }
                    CardDAO cartaDAO = new CardDAO();
                    cartaDAO.excluir(cartaParaExcluir);

                    listaDeCartas.remove(cartaParaExcluir);
                    dtmCartas.removeRow(linha);
                    if (listaDeCartas.isEmpty()) {
                        meusCardsLabel.setText("Esse baralho ainda não tem cards");
                        minhasCartasTable.getTableHeader().setVisible(false);
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void editarCarta(int linha) {
                System.out.println("caiu nesse caso");
                dtmCartas = (DefaultTableModel) minhasCartasTable.getModel();
                String pergunta = (String) dtmCartas.getValueAt(linha, 0);
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
                System.out.println("Ola");
            }

            @Override
            public void verBaralhos(int linha) {
                System.out.println("Baralhos");
            }
        };

        meusBaralhosTable.getColumnModel()
                .getColumn(4).setCellEditor(new TableActionCellEditor(event));
        meusGruposTable.getColumnModel()
                .getColumn(2).setCellEditor(new TableActionCellEditorGrupos(event));
        minhasCartasTable.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditorCartas(event));
        this.setLocationRelativeTo(null);
        meusBaralhosTable.setDefaultRenderer(Object.class, new AlternarCorLinhasRenderer());
        minhasCartasTable.setDefaultRenderer(Object.class, new AlternarCorLinhasRenderer());
        meusGruposTable.setDefaultRenderer(Object.class, new AlternarCorLinhasRenderer());
        selecionarBaralhoJogarTable.setDefaultRenderer(Object.class, new AlternarCorLinhasRenderer());
    }
    int acertosJogo;
    int errosJogo;
    Usuario usuario;
    Baralho baralho;
    DefaultTableModel dtmBaralhos;
    DefaultTableModel dtmJogar;
    DefaultTableModel dtmCartas;
    DefaultTableModel dtmGrupos;
    ArrayList<Baralho> listaDeBaralhos;
    ArrayList<Grupo> listaDeGrupos;
    ArrayList<Carta> listaDeCartas;
    String nomeBaralho;
    Carta cartaJogo;
    final int[] indice = {0};
    int j;
    int a;
    int i;
    int c;
    int row;

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
        return "erro";
    }

    public void cadastrarUser(String email, String senha, String nomeUsuario, String validar) throws Exception {
        usuario = new Usuario(0, email, nomeUsuario, senha, validar);
        var usuarioDAO = new UsuarioDAO();
        usuarioDAO.cadastrar(usuario);
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

        confirmarExcluirBaralhoPainel = new javax.swing.JPanel();
        estatisticasJogoLabel1 = new javax.swing.JLabel();
        confirmarExcluirBaralhoButton = new javax.swing.JButton();
        voltarMeusBaralhosButton2 = new javax.swing.JButton();
        editarGrupoAcoesPainel = new javax.swing.JPanel();
        editarGrupoButton = new javax.swing.JButton();
        adicionarAlunoGrupoButton = new javax.swing.JButton();
        voltarMeusGruposButton = new javax.swing.JButton();
        adicionarBaralhoGrupoButton = new javax.swing.JButton();
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
        voltarPainelInicial1Button = new javax.swing.JButton();
        meusBaralhosLabel = new javax.swing.JLabel();
        painelJogoFinalizado = new javax.swing.JPanel();
        estatisticasJogoLabel = new javax.swing.JLabel();
        terminarJogoVoltarPainelButton = new javax.swing.JButton();
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
        autenticarContaPanel = new javax.swing.JPanel();
        campoEmailAutenticarTextField = new javax.swing.JTextField();
        SenhaLabel = new javax.swing.JLabel();
        EntrarButton = new javax.swing.JButton();
        campoSenhaAutenticarPasswordField = new javax.swing.JPasswordField();
        redirecionarTelaCriarContaButton = new javax.swing.JButton();
        EmailLabel = new javax.swing.JLabel();
        dadosInvalidosMensagem = new javax.swing.JLabel();
        adicionarBaralhosPainel = new javax.swing.JPanel();
        inserirNomeBaralhoTextField = new javax.swing.JTextField();
        criarBaralhoButton = new javax.swing.JButton();
        voltarMeusBaralhos1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        inserirMateriaTextField = new javax.swing.JTextField();
        mensagemAdicionarBaralhoInvalido = new javax.swing.JLabel();
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
        meusCardsPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        minhasCartasTable = new javax.swing.JTable();
        irParaCriarCardButton = new javax.swing.JButton();
        voltarMeusBaralhosButton = new javax.swing.JButton();
        meusCardsLabel = new javax.swing.JLabel();
        irParaEditarBaralho = new javax.swing.JButton();
        nomeDoBaralhoLabel = new javax.swing.JLabel();
        codigoDoBaralhoLabel = new javax.swing.JLabel();
        painelInicial = new javax.swing.JPanel();
        irJogarButton = new javax.swing.JButton();
        abrirMeusBaralhosButton = new javax.swing.JButton();
        sairButton = new javax.swing.JButton();
        abrirMeusGruposButton = new javax.swing.JButton();
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
        irParaCriarBaralhoButton2 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        meusGruposTable1 = new javax.swing.JTable();
        voltarPainelInicial1Button2 = new javax.swing.JButton();
        meusGruposLabel1 = new javax.swing.JLabel();
        irEditarGruposButton1 = new javax.swing.JButton();
        criarGrupoButton1 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        irParaCriarBaralhoButton3 = new javax.swing.JButton();
        adicionarGrupoPainel = new javax.swing.JPanel();
        confirmarCriarGrupoButton = new javax.swing.JButton();
        inserirNomeGrupoCaixaDeTexto = new javax.swing.JTextField();
        voltarMeusGruposButton2 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        mensagemCriarGrupoInvalido = new javax.swing.JLabel();
        labelFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1080, 760));
        setMinimumSize(new java.awt.Dimension(1080, 760));
        setPreferredSize(new java.awt.Dimension(1080, 760));
        setSize(new java.awt.Dimension(760, 760));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        confirmarExcluirBaralhoPainel.setBackground(new java.awt.Color(246, 231, 211));

        estatisticasJogoLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        estatisticasJogoLabel1.setForeground(new java.awt.Color(0, 0, 0));
        estatisticasJogoLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        estatisticasJogoLabel1.setText("Tem certeza que deseja excluir esse baralho?");

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
            .addComponent(estatisticasJogoLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
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
                .addGap(58, 58, 58)
                .addComponent(estatisticasJogoLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(confirmarExcluirBaralhoButton)
                .addGap(34, 34, 34)
                .addComponent(voltarMeusBaralhosButton2)
                .addContainerGap(167, Short.MAX_VALUE))
        );

        getContentPane().add(confirmarExcluirBaralhoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        editarGrupoAcoesPainel.setBackground(new java.awt.Color(246, 231, 211));

        editarGrupoButton.setBackground(new java.awt.Color(237, 30, 82));
        editarGrupoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        editarGrupoButton.setForeground(new java.awt.Color(255, 255, 255));
        editarGrupoButton.setText("Editar nome");
        editarGrupoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarGrupoButtonActionPerformed(evt);
            }
        });

        adicionarAlunoGrupoButton.setBackground(new java.awt.Color(237, 30, 82));
        adicionarAlunoGrupoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        adicionarAlunoGrupoButton.setForeground(new java.awt.Color(255, 255, 255));
        adicionarAlunoGrupoButton.setText("Adicionar aluno");
        adicionarAlunoGrupoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarAlunoGrupoButtonActionPerformed(evt);
            }
        });

        voltarMeusGruposButton.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusGruposButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusGruposButton.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusGruposButton.setText("Sair");
        voltarMeusGruposButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarMeusGruposButtonActionPerformed(evt);
            }
        });

        adicionarBaralhoGrupoButton.setBackground(new java.awt.Color(237, 30, 82));
        adicionarBaralhoGrupoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        adicionarBaralhoGrupoButton.setForeground(new java.awt.Color(255, 255, 255));
        adicionarBaralhoGrupoButton.setText("Adicionar baralho");
        adicionarBaralhoGrupoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarBaralhoGrupoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout editarGrupoAcoesPainelLayout = new javax.swing.GroupLayout(editarGrupoAcoesPainel);
        editarGrupoAcoesPainel.setLayout(editarGrupoAcoesPainelLayout);
        editarGrupoAcoesPainelLayout.setHorizontalGroup(
            editarGrupoAcoesPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editarGrupoAcoesPainelLayout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addGroup(editarGrupoAcoesPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(editarGrupoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(adicionarAlunoGrupoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(voltarMeusGruposButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(adicionarBaralhoGrupoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(127, 127, 127))
        );
        editarGrupoAcoesPainelLayout.setVerticalGroup(
            editarGrupoAcoesPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editarGrupoAcoesPainelLayout.createSequentialGroup()
                .addContainerGap(127, Short.MAX_VALUE)
                .addComponent(adicionarBaralhoGrupoButton)
                .addGap(18, 18, 18)
                .addComponent(adicionarAlunoGrupoButton)
                .addGap(18, 18, 18)
                .addComponent(editarGrupoButton)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(voltarMeusGruposButton)
                .addGap(127, 127, 127))
        );

        getContentPane().add(editarGrupoAcoesPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        editarBaralhoPainel.setBackground(new java.awt.Color(246, 231, 211));
        editarBaralhoPainel.setForeground(new java.awt.Color(255, 255, 255));

        confirmarEditarBaralhoButton.setBackground(new java.awt.Color(237, 30, 82));
        confirmarEditarBaralhoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        confirmarEditarBaralhoButton.setForeground(new java.awt.Color(255, 255, 255));
        confirmarEditarBaralhoButton.setText("Confirmar");
        confirmarEditarBaralhoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarEditarBaralhoButtonActionPerformed(evt);
            }
        });

        editarNomeBaralhoCaixaDeTexto.setBackground(new java.awt.Color(28, 181, 196));
        editarNomeBaralhoCaixaDeTexto.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        editarNomeBaralhoCaixaDeTexto.setForeground(new java.awt.Color(0, 0, 0));
        editarNomeBaralhoCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 32));

        editarTemaCaixaDeTexto.setBackground(new java.awt.Color(28, 181, 196));
        editarTemaCaixaDeTexto.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        editarTemaCaixaDeTexto.setForeground(new java.awt.Color(0, 0, 0));
        editarTemaCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 32));

        voltarMeusCardsButton3.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusCardsButton3.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusCardsButton3.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusCardsButton3.setText("Voltar");
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
                .addGroup(editarBaralhoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(voltarMeusCardsButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmarEditarBaralhoButton))
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
        );
        editarBaralhoPainelLayout.setVerticalGroup(
            editarBaralhoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarBaralhoPainelLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editarNomeBaralhoCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editarTemaCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addComponent(confirmarEditarBaralhoButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(voltarMeusCardsButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mensagemEditarBaralhoInvalido)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        getContentPane().add(editarBaralhoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        meusBaralhosPainel.setBackground(new java.awt.Color(246, 231, 211));
        meusBaralhosPainel.setPreferredSize(new java.awt.Dimension(780, 440));

        irParaCriarBaralhoButton.setBackground(new java.awt.Color(237, 30, 82));
        irParaCriarBaralhoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        irParaCriarBaralhoButton.setForeground(new java.awt.Color(255, 255, 255));
        irParaCriarBaralhoButton.setText("Adicionar Baralho");
        irParaCriarBaralhoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irParaCriarBaralhoButtonActionPerformed(evt);
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
                "Nome", "Matéria", "Aproveitamento", "Vezes Jogadas", ""
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
            meusBaralhosTable.getColumnModel().getColumn(2).setHeaderValue("Aproveitamento");
            meusBaralhosTable.getColumnModel().getColumn(3).setHeaderValue("Vezes Jogadas");
            meusBaralhosTable.getColumnModel().getColumn(4).setPreferredWidth(40);
            meusBaralhosTable.getColumnModel().getColumn(4).setHeaderValue("");
        }

        voltarPainelInicial1Button.setBackground(new java.awt.Color(237, 30, 82));
        voltarPainelInicial1Button.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarPainelInicial1Button.setForeground(new java.awt.Color(255, 255, 255));
        voltarPainelInicial1Button.setText("Voltar");
        voltarPainelInicial1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarPainelInicial1ButtonActionPerformed(evt);
            }
        });

        meusBaralhosLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        meusBaralhosLabel.setForeground(new java.awt.Color(0, 0, 0));
        meusBaralhosLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        meusBaralhosLabel.setText("Meus baralhos");

        javax.swing.GroupLayout meusBaralhosPainelLayout = new javax.swing.GroupLayout(meusBaralhosPainel);
        meusBaralhosPainel.setLayout(meusBaralhosPainelLayout);
        meusBaralhosPainelLayout.setHorizontalGroup(
            meusBaralhosPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(meusBaralhosLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(meusBaralhosPainelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(meusBaralhosPainelLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(voltarPainelInicial1Button, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(irParaCriarBaralhoButton)
                .addGap(120, 120, 120))
        );
        meusBaralhosPainelLayout.setVerticalGroup(
            meusBaralhosPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meusBaralhosPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(meusBaralhosLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(meusBaralhosPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(voltarPainelInicial1Button)
                    .addComponent(irParaCriarBaralhoButton))
                .addGap(22, 22, 22))
        );

        getContentPane().add(meusBaralhosPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 780, 440));

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
                .addContainerGap(169, Short.MAX_VALUE))
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

        painelJogoNormal.setBackground(new java.awt.Color(246, 231, 211));

        cartaAnteriorButton.setBackground(new java.awt.Color(237, 30, 82));
        cartaAnteriorButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        cartaAnteriorButton.setForeground(new java.awt.Color(255, 255, 255));
        cartaAnteriorButton.setText("Voltar");
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
        proximaCartaButton.setText("Proxima");
        proximaCartaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proximaCartaButtonActionPerformed(evt);
            }
        });

        acerteiButton.setBackground(new java.awt.Color(237, 30, 82));
        acerteiButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        acerteiButton.setForeground(new java.awt.Color(255, 255, 255));
        acerteiButton.setText("Acertei");
        acerteiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acerteiButtonActionPerformed(evt);
            }
        });

        erreiButton.setBackground(new java.awt.Color(237, 30, 82));
        erreiButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        erreiButton.setForeground(new java.awt.Color(255, 255, 255));
        erreiButton.setText("Errei");
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
            .addGroup(painelJogoNormalLayout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(virarCardButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoNormalLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(cartaAnteriorButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(proximaCartaButton)
                .addGap(43, 43, 43))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoNormalLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(painelJogoNormalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoNormalLayout.createSequentialGroup()
                        .addComponent(finalizarJogoButton)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoNormalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoNormalLayout.createSequentialGroup()
                            .addComponent(erreiButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(acerteiButton)
                            .addGap(76, 76, 76))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoNormalLayout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(73, 73, 73)))))
        );
        painelJogoNormalLayout.setVerticalGroup(
            painelJogoNormalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoNormalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(finalizarJogoButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(painelJogoNormalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acerteiButton)
                    .addComponent(erreiButton))
                .addGap(20, 20, 20)
                .addComponent(virarCardButton)
                .addGap(18, 18, 18)
                .addGroup(painelJogoNormalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(proximaCartaButton)
                    .addComponent(cartaAnteriorButton))
                .addGap(22, 22, 22))
        );

        getContentPane().add(painelJogoNormal, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        meusGruposPainel.setBackground(new java.awt.Color(246, 231, 211));
        meusGruposPainel.setPreferredSize(new java.awt.Dimension(580, 460));

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
        voltarPainelInicial1Button1.setText("Voltar");
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
        irEditarGruposButton.setText("Editar");
        irEditarGruposButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irEditarGruposButtonActionPerformed(evt);
            }
        });

        criarGrupoButton.setBackground(new java.awt.Color(237, 30, 82));
        criarGrupoButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        criarGrupoButton.setForeground(new java.awt.Color(255, 255, 255));
        criarGrupoButton.setText("Criar");
        criarGrupoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarGrupoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout meusGruposPainelLayout = new javax.swing.GroupLayout(meusGruposPainel);
        meusGruposPainel.setLayout(meusGruposPainelLayout);
        meusGruposPainelLayout.setHorizontalGroup(
            meusGruposPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meusGruposPainelLayout.createSequentialGroup()
                .addGroup(meusGruposPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(meusGruposPainelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(meusGruposPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(meusGruposLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)))
                    .addGroup(meusGruposPainelLayout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(irEditarGruposButton)
                        .addGap(123, 123, 123)
                        .addComponent(criarGrupoButton))
                    .addGroup(meusGruposPainelLayout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(voltarPainelInicial1Button1)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        meusGruposPainelLayout.setVerticalGroup(
            meusGruposPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meusGruposPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(meusGruposLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(voltarPainelInicial1Button1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(meusGruposPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(criarGrupoButton)
                    .addComponent(irEditarGruposButton))
                .addGap(16, 16, 16))
        );

        getContentPane().add(meusGruposPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 580, 460));

        autenticarContaPanel.setBackground(new java.awt.Color(246, 231, 211));
        autenticarContaPanel.setPreferredSize(new java.awt.Dimension(440, 440));

        campoEmailAutenticarTextField.setBackground(new java.awt.Color(28, 181, 196));
        campoEmailAutenticarTextField.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        campoEmailAutenticarTextField.setForeground(new java.awt.Color(0, 0, 0));
        campoEmailAutenticarTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
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
        EntrarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EntrarButtonActionPerformed(evt);
            }
        });

        campoSenhaAutenticarPasswordField.setBackground(new java.awt.Color(28, 181, 196));
        campoSenhaAutenticarPasswordField.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
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
                        .addComponent(campoEmailAutenticarTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(campoSenhaAutenticarPasswordField, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(EntrarButton, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
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
                .addComponent(EntrarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dadosInvalidosMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        getContentPane().add(autenticarContaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        adicionarBaralhosPainel.setBackground(new java.awt.Color(246, 231, 211));
        adicionarBaralhosPainel.setMaximumSize(new java.awt.Dimension(440, 440));
        adicionarBaralhosPainel.setMinimumSize(new java.awt.Dimension(440, 440));
        adicionarBaralhosPainel.setPreferredSize(new java.awt.Dimension(440, 440));

        inserirNomeBaralhoTextField.setBackground(new java.awt.Color(28, 181, 196));
        inserirNomeBaralhoTextField.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        inserirNomeBaralhoTextField.setForeground(new java.awt.Color(0, 0, 0));
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
        voltarMeusBaralhos1.setText("Voltar");
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
        inserirMateriaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirMateriaTextFieldActionPerformed(evt);
            }
        });

        mensagemAdicionarBaralhoInvalido.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        mensagemAdicionarBaralhoInvalido.setForeground(new java.awt.Color(28, 181, 196));
        mensagemAdicionarBaralhoInvalido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout adicionarBaralhosPainelLayout = new javax.swing.GroupLayout(adicionarBaralhosPainel);
        adicionarBaralhosPainel.setLayout(adicionarBaralhosPainelLayout);
        adicionarBaralhosPainelLayout.setHorizontalGroup(
            adicionarBaralhosPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adicionarBaralhosPainelLayout.createSequentialGroup()
                .addGroup(adicionarBaralhosPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(adicionarBaralhosPainelLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(adicionarBaralhosPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mensagemAdicionarBaralhoInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(adicionarBaralhosPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(inserirNomeBaralhoTextField)
                                .addComponent(inserirMateriaTextField)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(adicionarBaralhosPainelLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addGroup(adicionarBaralhosPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(voltarMeusBaralhos1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(criarBaralhoButton))))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        adicionarBaralhosPainelLayout.setVerticalGroup(
            adicionarBaralhosPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adicionarBaralhosPainelLayout.createSequentialGroup()
                .addGap(53, 53, 53)
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
                .addComponent(criarBaralhoButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(voltarMeusBaralhos1)
                .addGap(12, 12, 12)
                .addComponent(mensagemAdicionarBaralhoInvalido)
                .addContainerGap(100, Short.MAX_VALUE))
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
        editarPerguntaCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 32));

        editarRespostaCaixaDeTexto.setBackground(new java.awt.Color(28, 181, 196));
        editarRespostaCaixaDeTexto.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        editarRespostaCaixaDeTexto.setForeground(new java.awt.Color(0, 0, 0));
        editarRespostaCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 32));

        voltarMeusCardsButton2.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusCardsButton2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusCardsButton2.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusCardsButton2.setText("Voltar");
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editarCartasPainelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(editarCartasPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(voltarMeusCardsButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmarEditarCartasButton))
                .addGap(162, 162, 162))
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
        );
        editarCartasPainelLayout.setVerticalGroup(
            editarCartasPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarCartasPainelLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editarPerguntaCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editarRespostaCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addComponent(confirmarEditarCartasButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(voltarMeusCardsButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mensagemEditarPerguntaInvalida)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        getContentPane().add(editarCartasPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        selecionarBaralhoJogarPainel.setBackground(new java.awt.Color(246, 231, 211));

        jogarButton.setBackground(new java.awt.Color(237, 30, 82));
        jogarButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jogarButton.setForeground(new java.awt.Color(255, 255, 255));
        jogarButton.setText("Jogar");
        jogarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jogarButtonActionPerformed(evt);
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

        voltarPainelInicialButton.setBackground(new java.awt.Color(237, 30, 82));
        voltarPainelInicialButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarPainelInicialButton.setForeground(new java.awt.Color(255, 255, 255));
        voltarPainelInicialButton.setText("Voltar");
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
            .addGroup(selecionarBaralhoJogarPainelLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(voltarPainelInicialButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jogarButton)
                .addGap(74, 74, 74))
            .addGroup(selecionarBaralhoJogarPainelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
            .addComponent(selecionarBaralhosJogarLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(baralhoSemCartasLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        selecionarBaralhoJogarPainelLayout.setVerticalGroup(
            selecionarBaralhoJogarPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selecionarBaralhoJogarPainelLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(selecionarBaralhosJogarLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(selecionarBaralhoJogarPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jogarButton)
                    .addComponent(voltarPainelInicialButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(baralhoSemCartasLabel)
                .addContainerGap())
        );

        getContentPane().add(selecionarBaralhoJogarPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        meusCardsPanel.setBackground(new java.awt.Color(246, 231, 211));

        minhasCartasTable.setBackground(new java.awt.Color(206, 191, 171));
        minhasCartasTable.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        minhasCartasTable.setForeground(new java.awt.Color(0, 0, 0));
        minhasCartasTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pergunta", "Resposta", "Jogadas", "Aproveitamento", ""
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
        irParaCriarCardButton.setText("Adicionar Card");
        irParaCriarCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irParaCriarCardButtonActionPerformed(evt);
            }
        });

        voltarMeusBaralhosButton.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusBaralhosButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusBaralhosButton.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusBaralhosButton.setText("Voltar");
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
        irParaEditarBaralho.setText("Editar baralho");
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
        codigoDoBaralhoLabel.setText("Codigo do baralho: 123131");

        javax.swing.GroupLayout meusCardsPanelLayout = new javax.swing.GroupLayout(meusCardsPanel);
        meusCardsPanel.setLayout(meusCardsPanelLayout);
        meusCardsPanelLayout.setHorizontalGroup(
            meusCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meusCardsPanelLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(voltarMeusBaralhosButton)
                .addGap(61, 61, 61)
                .addComponent(irParaEditarBaralho)
                .addGap(61, 61, 61)
                .addComponent(irParaCriarCardButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(meusCardsLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(meusCardsPanelLayout.createSequentialGroup()
                .addGroup(meusCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, meusCardsPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(codigoDoBaralhoLabel)
                        .addGap(14, 14, 14))
                    .addGroup(meusCardsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nomeDoBaralhoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, meusCardsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        meusCardsPanelLayout.setVerticalGroup(
            meusCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meusCardsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(codigoDoBaralhoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomeDoBaralhoLabel)
                .addGap(18, 18, 18)
                .addComponent(meusCardsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(meusCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(irParaCriarCardButton)
                    .addComponent(irParaEditarBaralho)
                    .addComponent(voltarMeusBaralhosButton))
                .addContainerGap())
        );

        getContentPane().add(meusCardsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 194, 620, 492));

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

        senhaLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        senhaLabel.setForeground(new java.awt.Color(0, 0, 0));
        senhaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        senhaLabel.setText("Senha");

        campoSenhaPasswordField.setBackground(new java.awt.Color(28, 181, 196));
        campoSenhaPasswordField.setFont(new java.awt.Font("Gill Sans MT", 1, 18)); // NOI18N

        campoConfirmarSenhaPasswordField.setBackground(new java.awt.Color(28, 181, 196));
        campoConfirmarSenhaPasswordField.setFont(new java.awt.Font("Gill Sans MT", 1, 18)); // NOI18N
        campoConfirmarSenhaPasswordField.setForeground(new java.awt.Color(0, 0, 0));

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

        jLabel13.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Criar conta");

        javax.swing.GroupLayout criarContaPanelLayout = new javax.swing.GroupLayout(criarContaPanel);
        criarContaPanel.setLayout(criarContaPanelLayout);
        criarContaPanelLayout.setHorizontalGroup(
            criarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, criarContaPanelLayout.createSequentialGroup()
                .addGap(0, 107, Short.MAX_VALUE)
                .addGroup(criarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, criarContaPanelLayout.createSequentialGroup()
                        .addComponent(criarContaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, criarContaPanelLayout.createSequentialGroup()
                        .addComponent(jaTenhoContaButton)
                        .addGap(169, 169, 169))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, criarContaPanelLayout.createSequentialGroup()
                        .addComponent(mensagemCriarContaInvalidaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 150, 150))))
            .addGroup(criarContaPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(criarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(criarContaPanelLayout.createSequentialGroup()
                        .addGroup(criarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(senhaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(campoSenhaPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addGroup(criarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(confirmarSenhaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                            .addComponent(campoConfirmarSenhaPasswordField)))
                    .addGroup(criarContaPanelLayout.createSequentialGroup()
                        .addGroup(criarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(campoEmailTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                            .addComponent(emailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addGroup(criarContaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoUsuarioTextField)
                            .addComponent(usuarioLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(116, Short.MAX_VALUE))
        );

        getContentPane().add(criarContaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(331, 284, -1, -1));

        criarCardsPanel.setBackground(new java.awt.Color(246, 231, 211));

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

        inserirRespostaCaixaDeTexto.setBackground(new java.awt.Color(28, 181, 196));
        inserirRespostaCaixaDeTexto.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N

        voltarMeusCardsButton.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusCardsButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusCardsButton.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusCardsButton.setText("Voltar");
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
                        .addGap(160, 160, 160)
                        .addGroup(criarCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(voltarMeusCardsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(confirmarCriarCardButton)))
                    .addGroup(criarCardsPanelLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(criarCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inserirPerguntaCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(inserirRespostaCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mensagemCriarCardInvalidoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(235, Short.MAX_VALUE))
        );
        criarCardsPanelLayout.setVerticalGroup(
            criarCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(criarCardsPanelLayout.createSequentialGroup()
                .addGap(67, 67, 67)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(voltarMeusCardsButton)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        getContentPane().add(criarCardsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, 550, 420));

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
        editarNomeGrupoCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 32));

        voltarEditarGrupoAcoesButton.setBackground(new java.awt.Color(237, 30, 82));
        voltarEditarGrupoAcoesButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarEditarGrupoAcoesButton.setForeground(new java.awt.Color(255, 255, 255));
        voltarEditarGrupoAcoesButton.setText("Voltar");
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
                .addGap(125, 125, 125)
                .addGroup(editarGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(editarGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editarGrupoPainelLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(editarGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(voltarEditarGrupoAcoesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(confirmarEditarCartasButton1))
                            .addGap(37, 37, 37))
                        .addGroup(editarGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editarNomeGrupoCaixaDeTexto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(mensagemEditarGrupoInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 125, Short.MAX_VALUE))
        );
        editarGrupoPainelLayout.setVerticalGroup(
            editarGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarGrupoPainelLayout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel14)
                .addGap(44, 44, 44)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editarNomeGrupoCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(38, 38, 38)
                .addComponent(confirmarEditarCartasButton1)
                .addGap(18, 18, 18)
                .addComponent(voltarEditarGrupoAcoesButton)
                .addGap(18, 18, 18)
                .addComponent(mensagemEditarGrupoInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
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
        inserirEmailAlunoCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 32));

        voltarEditarGrupoAcoesButton2.setBackground(new java.awt.Color(237, 30, 82));
        voltarEditarGrupoAcoesButton2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarEditarGrupoAcoesButton2.setForeground(new java.awt.Color(255, 255, 255));
        voltarEditarGrupoAcoesButton2.setText("Voltar");
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
                .addGap(125, 125, 125)
                .addGroup(adicionarAlunoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(adicionarAlunoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adicionarAlunoGrupoPainelLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(adicionarAlunoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(voltarEditarGrupoAcoesButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(confirmarAdicionarAluno))
                            .addGap(37, 37, 37))
                        .addGroup(adicionarAlunoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inserirEmailAlunoCaixaDeTexto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(mensagemEmailAlunoInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 125, Short.MAX_VALUE))
        );
        adicionarAlunoGrupoPainelLayout.setVerticalGroup(
            adicionarAlunoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adicionarAlunoGrupoPainelLayout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel15)
                .addGap(44, 44, 44)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inserirEmailAlunoCaixaDeTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(38, 38, 38)
                .addComponent(confirmarAdicionarAluno)
                .addGap(18, 18, 18)
                .addComponent(voltarEditarGrupoAcoesButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mensagemEmailAlunoInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        getContentPane().add(adicionarAlunoGrupoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

        baralhosDoGrupoPainel.setBackground(new java.awt.Color(246, 231, 211));
        baralhosDoGrupoPainel.setPreferredSize(new java.awt.Dimension(440, 590));

        irParaCriarBaralhoButton2.setBackground(new java.awt.Color(237, 30, 82));
        irParaCriarBaralhoButton2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        irParaCriarBaralhoButton2.setForeground(new java.awt.Color(255, 255, 255));
        irParaCriarBaralhoButton2.setText("Jogar");
        irParaCriarBaralhoButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irParaCriarBaralhoButton2ActionPerformed(evt);
            }
        });

        jScrollPane5.setBackground(new java.awt.Color(206, 191, 171));

        meusGruposTable1.setAutoCreateRowSorter(true);
        meusGruposTable1.setBackground(new java.awt.Color(206, 191, 171));
        meusGruposTable1.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        meusGruposTable1.setForeground(new java.awt.Color(0, 0, 0));
        meusGruposTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        meusGruposTable1.setRowHeight(48);
        jScrollPane5.setViewportView(meusGruposTable1);
        if (meusGruposTable1.getColumnModel().getColumnCount() > 0) {
            meusGruposTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
            meusGruposTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
        }

        voltarPainelInicial1Button2.setBackground(new java.awt.Color(237, 30, 82));
        voltarPainelInicial1Button2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarPainelInicial1Button2.setForeground(new java.awt.Color(255, 255, 255));
        voltarPainelInicial1Button2.setText("Voltar");
        voltarPainelInicial1Button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarPainelInicial1Button2ActionPerformed(evt);
            }
        });

        meusGruposLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        meusGruposLabel1.setForeground(new java.awt.Color(0, 0, 0));
        meusGruposLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        meusGruposLabel1.setText("Baralhos do grupo");

        irEditarGruposButton1.setBackground(new java.awt.Color(237, 30, 82));
        irEditarGruposButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        irEditarGruposButton1.setForeground(new java.awt.Color(255, 255, 255));
        irEditarGruposButton1.setText("Editar");
        irEditarGruposButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irEditarGruposButton1ActionPerformed(evt);
            }
        });

        criarGrupoButton1.setBackground(new java.awt.Color(237, 30, 82));
        criarGrupoButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        criarGrupoButton1.setForeground(new java.awt.Color(255, 255, 255));
        criarGrupoButton1.setText("Criar");
        criarGrupoButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarGrupoButton1ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("jLabel18");

        irParaCriarBaralhoButton3.setBackground(new java.awt.Color(237, 30, 82));
        irParaCriarBaralhoButton3.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        irParaCriarBaralhoButton3.setForeground(new java.awt.Color(255, 255, 255));
        irParaCriarBaralhoButton3.setText("Ver");
        irParaCriarBaralhoButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irParaCriarBaralhoButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout baralhosDoGrupoPainelLayout = new javax.swing.GroupLayout(baralhosDoGrupoPainel);
        baralhosDoGrupoPainel.setLayout(baralhosDoGrupoPainelLayout);
        baralhosDoGrupoPainelLayout.setHorizontalGroup(
            baralhosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baralhosDoGrupoPainelLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(baralhosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(baralhosDoGrupoPainelLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(irEditarGruposButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(criarGrupoButton1))
                    .addGroup(baralhosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(meusGruposLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, baralhosDoGrupoPainelLayout.createSequentialGroup()
                            .addComponent(voltarPainelInicial1Button2)
                            .addGap(43, 43, 43)
                            .addComponent(irParaCriarBaralhoButton3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(irParaCriarBaralhoButton2))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        baralhosDoGrupoPainelLayout.setVerticalGroup(
            baralhosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baralhosDoGrupoPainelLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(meusGruposLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(baralhosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(voltarPainelInicial1Button2)
                    .addComponent(irParaCriarBaralhoButton2)
                    .addComponent(irParaCriarBaralhoButton3))
                .addGap(51, 51, 51)
                .addGroup(baralhosDoGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(irEditarGruposButton1)
                    .addComponent(criarGrupoButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(baralhosDoGrupoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, 440, 590));

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
        inserirNomeGrupoCaixaDeTexto.setPreferredSize(new java.awt.Dimension(190, 32));

        voltarMeusGruposButton2.setBackground(new java.awt.Color(237, 30, 82));
        voltarMeusGruposButton2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        voltarMeusGruposButton2.setForeground(new java.awt.Color(255, 255, 255));
        voltarMeusGruposButton2.setText("Voltar");
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
                .addGap(125, 125, 125)
                .addGroup(adicionarGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(adicionarGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adicionarGrupoPainelLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(adicionarGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(voltarMeusGruposButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(confirmarCriarGrupoButton))
                            .addGap(37, 37, 37))
                        .addGroup(adicionarGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inserirNomeGrupoCaixaDeTexto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(mensagemCriarGrupoInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        adicionarGrupoPainelLayout.setVerticalGroup(
            adicionarGrupoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adicionarGrupoPainelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel19)
                .addGap(74, 74, 74)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inserirNomeGrupoCaixaDeTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(confirmarCriarGrupoButton)
                .addGap(18, 18, 18)
                .addComponent(voltarMeusGruposButton2)
                .addGap(18, 18, 18)
                .addComponent(mensagemCriarGrupoInvalido, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        getContentPane().add(adicionarGrupoPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

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
                mensagemCriarContaInvalidaLabel.setText("Insira dados validos");
            } else if (senha.equals(confirmarSenha)) {
                cadastrarUser(email, senha, nomeUsuario, validar);
                JOptionPane.showMessageDialog(null, "Deu certo!");
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            mensagemCriarContaInvalidaLabel.setText("Email já registrado");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro inesperado");
        }
    }//GEN-LAST:event_criarContaButtonActionPerformed

    private void campoUsuarioTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoUsuarioTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoUsuarioTextFieldActionPerformed

    private void jaTenhoContaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jaTenhoContaButtonActionPerformed
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
                listaDeBaralhos = listarBaralhos(usuario);
                if (usuario.getTipoUsuario().equals("aluno")) {
                    listaDeGrupos = GrupoDAO.listarGruposAlunos(usuario);
                } else if (usuario.getTipoUsuario().equals("professor")) {
                    listaDeGrupos = GrupoDAO.listarGruposProfessor(usuario);
                }
            } else {
                dadosInvalidosMensagem.setText("insira dados validos");
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
        autenticarContaPanel.setVisible(false);
        criarContaPanel.setVisible(true);
    }//GEN-LAST:event_redirecionarTelaCriarContaButtonActionPerformed

    private void abrirMeusBaralhosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirMeusBaralhosButtonActionPerformed
        dtmBaralhos = (DefaultTableModel) meusBaralhosTable.getModel();
        dtmBaralhos.setRowCount(0);
        if (listaDeBaralhos.isEmpty()) {
            meusBaralhosLabel.setText("Você não tem baralhos criados!");
            meusBaralhosTable.getTableHeader().setVisible(false);
        } else {
            for (Baralho baralho : listaDeBaralhos) {
                Object[] linha = {
                    baralho.getNomeBaralho(),
                    baralho.getTema(), baralho.getMediaDeAcertos(), (baralho.getTotalDeAcertos() + baralho.getTotalDeErros())};
                dtmBaralhos.addRow(linha);
            }
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
                    BaralhoDAO baralhoDAO = new BaralhoDAO();
                    baralho = baralhoDAO.criarBaralho(baralho);
                    listaDeBaralhos.add(baralho);
                    meusBaralhosLabel.setText("Meus baralhos");
                    Object[] dados = {baralho.getNomeBaralho(), baralho.getTema(), 0, 0};
                    dtmBaralhos.addRow(dados);
                    adicionarBaralhosPainel.setVisible(false);
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
                            carta.getTotalDeAcertos() + carta.getTotalDeErros(),
                            carta.getMediaDeAcertos()};
                        dtmCartas.addRow(linha);
                    }
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
                    Object[] dados = {carta.getPergunta(), carta.getResposta(), carta.getTotalDeAcertos() + carta.getTotalDeErros(), carta.getMediaDeAcertos()
                    };
                    dtmCartas.addRow(dados);
                    meusCardsLabel.setText("Cards");
                    minhasCartasTable.getTableHeader().setVisible(true);
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
        criarCardsPanel.setVisible(false);
        meusCardsPanel.setVisible(true);
    }//GEN-LAST:event_voltarMeusCardsButtonActionPerformed

    private void voltarMeusCardsButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusCardsButton2ActionPerformed
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
                conteudoCartaLabel.setText(listaDeCartas.get(0).getPergunta());
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
                selecionarBaralhoJogarPainel.setVisible(false);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jogarButtonActionPerformed

    private void irJogarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irJogarButtonActionPerformed
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        selecionarBaralhoJogarPainel.setVisible(true);
        painelInicial.setVisible(false);
    }//GEN-LAST:event_irJogarButtonActionPerformed

    private void voltarMeusBaralhos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusBaralhos1ActionPerformed
        adicionarBaralhosPainel.setVisible(false);
        meusBaralhosPainel.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_voltarMeusBaralhos1ActionPerformed

    private void voltarPainelInicialButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarPainelInicialButtonActionPerformed
        painelInicial.setVisible(true);
        selecionarBaralhoJogarPainel.setVisible(false);
    }//GEN-LAST:event_voltarPainelInicialButtonActionPerformed

    private void voltarPainelInicial1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarPainelInicial1ButtonActionPerformed
        // TODO add your handling code here:
        painelInicial.setVisible(true);
        meusBaralhosPainel.setVisible(false);
    }//GEN-LAST:event_voltarPainelInicial1ButtonActionPerformed

    private void voltarMeusBaralhosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusBaralhosButtonActionPerformed
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
        virarCardButton.setText("Ver resposta");
        conteudoCartaLabel.setText(listaDeCartas.get(indice[0]).getPergunta());
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
        virarCardButton.setText("Ver resposta");
        conteudoCartaLabel.setText(listaDeCartas.get(indice[0]).getPergunta());
        acerteiButton.setVisible(false);
        erreiButton.setVisible(false);
    }//GEN-LAST:event_cartaAnteriorButtonActionPerformed

    private void virarCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_virarCardButtonActionPerformed
        if (virarCardButton.getText().equals("Ver pergunta")) {
            conteudoCartaLabel.setText(listaDeCartas.get(indice[0]).getPergunta());
            virarCardButton.setText("Ver resposta");
            acerteiButton.setVisible(false);
            erreiButton.setVisible(false);
        } else {
            conteudoCartaLabel.setText(listaDeCartas.get(indice[0]).getResposta());
            virarCardButton.setText("Ver pergunta");
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
                cartaJogo.setMediaDeAcertos((double) acerto / (acerto + erro) * 100);
                CardDAO.editar(cartaJogo);
            } else if (!cartaJogo.isAcertou()) {
                cartaJogo.setAcertou(true);
                errosJogo--;
                acertosJogo++;
                int acerto = cartaJogo.getTotalDeAcertos() + 1;
                int erro = cartaJogo.getTotalDeErros() - 1;
                cartaJogo.setTotalDeAcertos(acerto);
                cartaJogo.setTotalDeErros(erro);
                cartaJogo.setMediaDeAcertos((double) acerto / (acerto + erro) * 100);
                CardDAO.editar(cartaJogo);
            }
            indice[0]++;
            if (indice[0] < listaDeCartas.size()) {
                conteudoCartaLabel.setText(listaDeCartas.get(indice[0]).getPergunta());
                cartaAnteriorButton.setVisible(true);
                if (indice[0] == listaDeCartas.size() - 1) {
                    proximaCartaButton.setVisible(false);
                    cartaAnteriorButton.setVisible(true);
                }
                virarCardButton.setText("Ver resposta");
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
                listaDeBaralhos.get(a).setMediaDeAcertos((double) acertosTotal / (acertosTotal + errosTotal) * 100);
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
                cartaJogo.setMediaDeAcertos((double) acerto / (acerto + erro) * 100);
                CardDAO.editar(cartaJogo);
            } else if (cartaJogo.isAcertou()) {
                errosJogo++;
                acertosJogo--;
                cartaJogo.setAcertou(false);
                int acerto = cartaJogo.getTotalDeAcertos() - 1;
                int erro = cartaJogo.getTotalDeErros() + 1;
                cartaJogo.setTotalDeAcertos(acerto);
                cartaJogo.setTotalDeErros(erro);
                cartaJogo.setMediaDeAcertos((double) acerto / (acerto + erro) * 100);
                CardDAO.editar(cartaJogo);
            }
            indice[0]++;
            if (indice[0] < listaDeCartas.size()) {
                conteudoCartaLabel.setText(listaDeCartas.get(indice[0]).getPergunta());
                cartaAnteriorButton.setVisible(true);
                if (indice[0] == listaDeCartas.size() - 1) {
                    proximaCartaButton.setVisible(false);
                    cartaAnteriorButton.setVisible(true);
                }
                virarCardButton.setText("Ver resposta");
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
                listaDeBaralhos.get(a).setMediaDeAcertos((double) acertosTotal / (acertosTotal + errosTotal) * 100);
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
            listaDeBaralhos.get(a).setMediaDeAcertos((double) acertosTotal / (acertosTotal + errosTotal) * 100);
            BaralhoDAO.editar(listaDeBaralhos.get(a));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_finalizarJogoButtonActionPerformed

    private void terminarJogoVoltarPainelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminarJogoVoltarPainelButtonActionPerformed
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
        painelInicial.setVisible(false);
        autenticarContaPanel.setVisible(true);
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
                            baralho.getMediaDeAcertos(),
                            baralho.getTotalDeAcertos() + baralho.getTotalDeErros()};
                        dtmBaralhos.addRow(linha);
                    }
                    nomeDoBaralhoLabel.setText(listaDeBaralhos.get(i).getNomeBaralho());
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
        // TODO add your handling code here:
    }//GEN-LAST:event_voltarMeusCardsButton3ActionPerformed

    private void abrirMeusGruposButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirMeusGruposButtonActionPerformed
        dtmGrupos = (DefaultTableModel) meusGruposTable.getModel();
        dtmGrupos.setRowCount(0);
        if (listaDeGrupos.isEmpty()) {
            meusGruposLabel.setText("Você não está em grupo nenhum!");
            meusGruposTable.getTableHeader().setVisible(false);
        } else {
            for (Grupo grupo : listaDeGrupos) {
                Object[] linha = {
                    grupo.getNomeGrupo(),
                    grupo.getNomeProfessor()};
                dtmGrupos.addRow(linha);
            }
        }
        meusGruposPainel.setVisible(true);
        if (usuario.getTipoUsuario().equals("aluno")) {
            irEditarGruposButton.setVisible(false);
            criarGrupoButton.setVisible(false);
        } else {
            irEditarGruposButton.setVisible(true);
            criarGrupoButton.setVisible(true);
        }
        painelInicial.setVisible(false);
    }//GEN-LAST:event_abrirMeusGruposButtonActionPerformed

    private void voltarPainelInicial1Button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarPainelInicial1Button1ActionPerformed
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
        editarGrupoAcoesPainel.setVisible(true);
        meusGruposPainel.setVisible(false);
    }//GEN-LAST:event_irEditarGruposButtonActionPerformed

    private void criarGrupoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criarGrupoButtonActionPerformed
        adicionarGrupoPainel.setVisible(true);
        meusGruposPainel.setVisible(false);
    }//GEN-LAST:event_criarGrupoButtonActionPerformed

    private void editarGrupoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarGrupoButtonActionPerformed
        editarGrupoPainel.setVisible(true);
        editarGrupoAcoesPainel.setVisible(false);
        editarNomeGrupoCaixaDeTexto.setText(listaDeGrupos.get(c).getNomeGrupo());
    }//GEN-LAST:event_editarGrupoButtonActionPerformed

    private void adicionarAlunoGrupoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarAlunoGrupoButtonActionPerformed
        adicionarAlunoGrupoPainel.setVisible(true);
        editarGrupoAcoesPainel.setVisible(false);
    }//GEN-LAST:event_adicionarAlunoGrupoButtonActionPerformed

    private void voltarMeusGruposButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarMeusGruposButtonActionPerformed
        // TODO add your handling code here:
        editarGrupoAcoesPainel.setVisible(false);
        meusGruposPainel.setVisible(true);
    }//GEN-LAST:event_voltarMeusGruposButtonActionPerformed

    private void adicionarBaralhoGrupoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarBaralhoGrupoButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adicionarBaralhoGrupoButtonActionPerformed

    private void confirmarEditarCartasButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarEditarCartasButton1ActionPerformed
        try {
            boolean grupoJaExiste = false;
            if (editarNomeGrupoCaixaDeTexto.getText().equals("")) {
                mensagemEditarGrupoInvalido.setText("Insira os valores");
            } else if (listaDeGrupos.get(c).getNomeGrupo().equals(editarNomeGrupoCaixaDeTexto.getText())) {
                editarGrupoAcoesPainel.setVisible(true);
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
                            grupo.getNomeProfessor()};
                        dtmGrupos.addRow(linha);
                    }
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
        editarGrupoAcoesPainel.setVisible(true);
        editarGrupoPainel.setVisible(false);
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
            if (inserirEmailAlunoCaixaDeTexto.getText().equals("")) {
                mensagemEmailAlunoInvalido.setText("Digite um email!");
            } else {
                Usuario aluno = GrupoDAO.selecionarAluno(inserirEmailAlunoCaixaDeTexto.getText());
                if (aluno == null) {
                    mensagemEmailAlunoInvalido.setText("Aluno não encontrado");
                }
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_confirmarAdicionarAlunoActionPerformed

    private void voltarEditarGrupoAcoesButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarEditarGrupoAcoesButton2ActionPerformed
        editarGrupoAcoesPainel.setVisible(true);
        adicionarAlunoGrupoPainel.setVisible(false);
    }//GEN-LAST:event_voltarEditarGrupoAcoesButton2ActionPerformed

    private void irParaCriarBaralhoButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irParaCriarBaralhoButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_irParaCriarBaralhoButton2ActionPerformed

    private void voltarPainelInicial1Button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarPainelInicial1Button2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_voltarPainelInicial1Button2ActionPerformed

    private void irEditarGruposButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irEditarGruposButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_irEditarGruposButton1ActionPerformed

    private void criarGrupoButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criarGrupoButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_criarGrupoButton1ActionPerformed

    private void irParaCriarBaralhoButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irParaCriarBaralhoButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_irParaCriarBaralhoButton3ActionPerformed

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
                    System.out.println("Grupo n esta na lista");
                    Grupo grupo = new Grupo(0, usuario.getNomeUsuario(), inserirNomeGrupoCaixaDeTexto.getText());        // TODO add your handling code here:
                    grupo = GrupoDAO.criarGrupo(grupo, usuario);
                    listaDeGrupos.add(grupo);
                    meusGruposLabel.setText("Meus grupos");
                    Object[] dados = {grupo.getNomeGrupo(), grupo.getNomeProfessor()};
                    dtmGrupos.addRow(dados);
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
    private javax.swing.JLabel EmailLabel;
    private javax.swing.JButton EntrarButton;
    private javax.swing.JLabel SenhaLabel;
    private javax.swing.JButton abrirMeusBaralhosButton;
    private javax.swing.JButton abrirMeusGruposButton;
    private javax.swing.JButton acerteiButton;
    private javax.swing.JButton adicionarAlunoGrupoButton;
    private javax.swing.JPanel adicionarAlunoGrupoPainel;
    private javax.swing.JButton adicionarBaralhoGrupoButton;
    private javax.swing.JPanel adicionarBaralhosPainel;
    private javax.swing.JPanel adicionarGrupoPainel;
    private javax.swing.JPanel autenticarContaPanel;
    private javax.swing.JLabel baralhoSemCartasLabel;
    private javax.swing.JPanel baralhosDoGrupoPainel;
    private javax.swing.JPasswordField campoConfirmarSenhaPasswordField;
    private javax.swing.JTextField campoEmailAutenticarTextField;
    private javax.swing.JTextField campoEmailTextField;
    private javax.swing.JPasswordField campoSenhaAutenticarPasswordField;
    private javax.swing.JPasswordField campoSenhaPasswordField;
    private javax.swing.JTextField campoUsuarioTextField;
    private javax.swing.JButton cartaAnteriorButton;
    private javax.swing.JLabel codigoDoBaralhoLabel;
    private javax.swing.JButton confirmarAdicionarAluno;
    private javax.swing.JButton confirmarCriarCardButton;
    private javax.swing.JButton confirmarCriarGrupoButton;
    private javax.swing.JButton confirmarEditarBaralhoButton;
    private javax.swing.JButton confirmarEditarCartasButton;
    private javax.swing.JButton confirmarEditarCartasButton1;
    private javax.swing.JButton confirmarExcluirBaralhoButton;
    private javax.swing.JPanel confirmarExcluirBaralhoPainel;
    private javax.swing.JLabel confirmarSenhaLabel;
    private javax.swing.JLabel conteudoCartaLabel;
    private javax.swing.JButton criarBaralhoButton;
    private javax.swing.JPanel criarCardsPanel;
    private javax.swing.JButton criarContaButton;
    private javax.swing.JPanel criarContaPanel;
    private javax.swing.JButton criarGrupoButton;
    private javax.swing.JButton criarGrupoButton1;
    private javax.swing.JLabel dadosInvalidosMensagem;
    private javax.swing.JPanel editarBaralhoPainel;
    private javax.swing.JPanel editarCartasPainel;
    private javax.swing.JPanel editarGrupoAcoesPainel;
    private javax.swing.JButton editarGrupoButton;
    private javax.swing.JPanel editarGrupoPainel;
    private javax.swing.JTextField editarNomeBaralhoCaixaDeTexto;
    private javax.swing.JTextField editarNomeGrupoCaixaDeTexto;
    private javax.swing.JTextField editarPerguntaCaixaDeTexto;
    private javax.swing.JTextField editarRespostaCaixaDeTexto;
    private javax.swing.JTextField editarTemaCaixaDeTexto;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JButton erreiButton;
    private javax.swing.JLabel estatisticasJogoLabel;
    private javax.swing.JLabel estatisticasJogoLabel1;
    private javax.swing.JButton finalizarJogoButton;
    private javax.swing.JTextField inserirEmailAlunoCaixaDeTexto;
    private javax.swing.JTextField inserirMateriaTextField;
    private javax.swing.JTextField inserirNomeBaralhoTextField;
    private javax.swing.JTextField inserirNomeGrupoCaixaDeTexto;
    private javax.swing.JTextField inserirPerguntaCaixaDeTexto;
    private javax.swing.JTextField inserirRespostaCaixaDeTexto;
    private javax.swing.JButton irEditarGruposButton;
    private javax.swing.JButton irEditarGruposButton1;
    private javax.swing.JButton irJogarButton;
    private javax.swing.JButton irParaCriarBaralhoButton;
    private javax.swing.JButton irParaCriarBaralhoButton2;
    private javax.swing.JButton irParaCriarBaralhoButton3;
    private javax.swing.JButton irParaCriarCardButton;
    private javax.swing.JButton irParaEditarBaralho;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton jaTenhoContaButton;
    private javax.swing.JButton jogarButton;
    private javax.swing.JLabel labelFundo;
    private javax.swing.JLabel mensagemAdicionarBaralhoInvalido;
    private javax.swing.JLabel mensagemCriarCardInvalidoLabel;
    private javax.swing.JLabel mensagemCriarContaInvalidaLabel;
    private javax.swing.JLabel mensagemCriarGrupoInvalido;
    private javax.swing.JLabel mensagemEditarBaralhoInvalido;
    private javax.swing.JLabel mensagemEditarGrupoInvalido;
    private javax.swing.JLabel mensagemEditarPerguntaInvalida;
    private javax.swing.JLabel mensagemEmailAlunoInvalido;
    private javax.swing.JLabel meusBaralhosLabel;
    private javax.swing.JPanel meusBaralhosPainel;
    private javax.swing.JTable meusBaralhosTable;
    private javax.swing.JLabel meusCardsLabel;
    private javax.swing.JPanel meusCardsPanel;
    private javax.swing.JLabel meusGruposLabel;
    private javax.swing.JLabel meusGruposLabel1;
    private javax.swing.JPanel meusGruposPainel;
    private javax.swing.JTable meusGruposTable;
    private javax.swing.JTable meusGruposTable1;
    private javax.swing.JTable minhasCartasTable;
    private javax.swing.JLabel nomeDoBaralhoLabel;
    private javax.swing.JPanel painelInicial;
    private javax.swing.JPanel painelJogoFinalizado;
    private javax.swing.JPanel painelJogoNormal;
    private javax.swing.JButton proximaCartaButton;
    private javax.swing.JButton redirecionarTelaCriarContaButton;
    private javax.swing.JButton sairButton;
    private javax.swing.JPanel selecionarBaralhoJogarPainel;
    private javax.swing.JTable selecionarBaralhoJogarTable;
    private javax.swing.JLabel selecionarBaralhosJogarLabel;
    private javax.swing.JLabel senhaLabel;
    private javax.swing.JButton terminarJogoVoltarPainelButton;
    private javax.swing.JLabel usuarioLabel;
    private javax.swing.JButton virarCardButton;
    private javax.swing.JButton voltarEditarGrupoAcoesButton;
    private javax.swing.JButton voltarEditarGrupoAcoesButton2;
    private javax.swing.JButton voltarMeusBaralhos1;
    private javax.swing.JButton voltarMeusBaralhosButton;
    private javax.swing.JButton voltarMeusBaralhosButton2;
    private javax.swing.JButton voltarMeusCardsButton;
    private javax.swing.JButton voltarMeusCardsButton2;
    private javax.swing.JButton voltarMeusCardsButton3;
    private javax.swing.JButton voltarMeusGruposButton;
    private javax.swing.JButton voltarMeusGruposButton2;
    private javax.swing.JButton voltarPainelInicial1Button;
    private javax.swing.JButton voltarPainelInicial1Button1;
    private javax.swing.JButton voltarPainelInicial1Button2;
    private javax.swing.JButton voltarPainelInicialButton;
    // End of variables declaration//GEN-END:variables
}
