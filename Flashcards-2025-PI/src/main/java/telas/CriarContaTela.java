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
        painelJogoNormal.setVisible(false);
        selecionarBaralhoJogarTable.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        meusBaralhosTable.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        minhasCartasTable.getTableHeader().setDefaultRenderer(new HeaderRenderer());
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
        criarCardsPanel.setVisible(false);
        criarContaPanel.setVisible(false);
        painelInicial.setVisible(false);
        meusBaralhosPainel.setVisible(false);
        meusCardsPanel.setVisible(false);
        adicionarBaralhosPainel.setVisible(false);
        editarCartasPainel.setVisible(false);
        selecionarBaralhoJogarPainel.setVisible(false);
        meusBaralhosTable.getColumnModel().getColumn(2).setCellRenderer(new TableActionCellRenderer());
        minhasCartasTable.getColumnModel().getColumn(2).setCellRenderer(new TableActionCellRendererCartas());
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void deletarBaralho(int row) {
                try {
                    if (meusBaralhosTable.isEditing()) {
                        meusBaralhosTable.getCellEditor().stopCellEditing();
                    }
                    DefaultTableModel modelo = (DefaultTableModel) meusBaralhosTable.getModel();
                    String nomeBaralho = (String) modelo.getValueAt(row, 0);

                    Baralho baralhoParaExcluir = null;
                    for (Baralho baralho : listaDeBaralhos) {
                        if (baralho.getNomeBaralho().equals(nomeBaralho)) {
                            baralhoParaExcluir = baralho;
                            break;
                        }
                    }
                    BaralhoDAO baralhoDAO = new BaralhoDAO();
                    baralhoDAO.excluir(baralhoParaExcluir);

                    listaDeBaralhos.remove(baralhoParaExcluir);
                    modelo.removeRow(row);
                    if (listaDeBaralhos.isEmpty()) {
                        meusBaralhosLabel.setText("Você não tem baralhos criados");
                        meusBaralhosTable.getTableHeader().setVisible(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void editarBaralho(int row) {
                DefaultTableModel modelo = (DefaultTableModel) meusBaralhosTable.getModel();
                String nomeBaralho = (String) modelo.getValueAt(row, 0);
                for (i = 0; i < listaDeBaralhos.size(); i++) {
                    if (listaDeBaralhos.get(i).getNomeBaralho().equals(nomeBaralho)) {
                        break;
                    }
                }
                try {
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
                            carta.getResposta(),};
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
                    DefaultTableModel modelo = (DefaultTableModel) minhasCartasTable.getModel();
                    String pergunta = (String) modelo.getValueAt(linha, 0);

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
                    modelo.removeRow(linha);
                    if (listaDeCartas.isEmpty()) {
                        meusCardsLabel.setText("Esse baralho ainda não tem cards");
                        minhasCartasTable.getTableHeader().setVisible(false);
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void editarCarta(int linha) {
                System.out.println("linha " + linha);
                DefaultTableModel modelo = (DefaultTableModel) minhasCartasTable.getModel();
                String pergunta = (String) modelo.getValueAt(linha, 0);
                System.out.println("loop iniciando");
                for (j = 0; j < listaDeCartas.size(); j++) {
                    if (listaDeCartas.get(j).getPergunta().equals(pergunta)) {
                        System.out.println("card encontrado");
                        System.out.println(j);
                        editarPerguntaCaixaDeTexto.setText(listaDeCartas.get(j).getPergunta());
                        editarRespostaCaixaDeTexto.setText(listaDeCartas.get(j).getResposta());
                        meusCardsPanel.setVisible(false);
                        editarCartasPainel.setVisible(true);
                        break;
                    }
                }
            }
        };

        meusBaralhosTable.getColumnModel()
                .getColumn(2).setCellEditor(new TableActionCellEditor(event));
        minhasCartasTable.getColumnModel().getColumn(2).setCellEditor(new TableActionCellEditorCartas(event));
        this.setLocationRelativeTo(null);
        meusBaralhosTable.setDefaultRenderer(Object.class, new AlternarCorLinhasRenderer());
        minhasCartasTable.setDefaultRenderer(Object.class, new AlternarCorLinhasRenderer());
        selecionarBaralhoJogarTable.setDefaultRenderer(Object.class, new AlternarCorLinhasRenderer());
    }

    Usuario usuario;
    Baralho baralho;
    DefaultTableModel dtmBaralhos;
    DefaultTableModel dtmJogar;
    DefaultTableModel dtmCartas;
    ArrayList<Baralho> listaDeBaralhos;
    ArrayList<Carta> listaDeCartas;
    Carta cartaJogo;
    final int[] indice = {0};
    int j;
    int a;
    int i;

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

        painelJogoNormal = new javax.swing.JPanel();
        cartaAnteriorButton = new javax.swing.JButton();
        finalizarJogoButton = new javax.swing.JButton();
        conteudoCartaLabel = new javax.swing.JLabel();
        proximaCartaButton = new javax.swing.JButton();
        acerteiButton = new javax.swing.JButton();
        erreiButton = new javax.swing.JButton();
        virarCardButton = new javax.swing.JButton();
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
        meusBaralhosPainel = new javax.swing.JPanel();
        irParaCriarBaralhoButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        meusBaralhosTable = new javax.swing.JTable();
        voltarPainelInicial1Button = new javax.swing.JButton();
        meusBaralhosLabel = new javax.swing.JLabel();
        meusCardsPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        minhasCartasTable = new javax.swing.JTable();
        irParaCriarCardButton = new javax.swing.JButton();
        voltarMeusBaralhosButton = new javax.swing.JButton();
        meusCardsLabel = new javax.swing.JLabel();
        painelInicial = new javax.swing.JPanel();
        irJogarButton = new javax.swing.JButton();
        abrirMeusBaralhosButton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
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
        labelFundo = new javax.swing.JLabel();
        painelJogoFinalizado = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1080, 760));
        setMinimumSize(new java.awt.Dimension(1080, 760));
        setPreferredSize(new java.awt.Dimension(1080, 760));
        setSize(new java.awt.Dimension(760, 760));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        conteudoCartaLabel.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        conteudoCartaLabel.setForeground(new java.awt.Color(0, 0, 0));
        conteudoCartaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        conteudoCartaLabel.setText("jLabel7");

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

        javax.swing.GroupLayout painelJogoNormalLayout = new javax.swing.GroupLayout(painelJogoNormal);
        painelJogoNormal.setLayout(painelJogoNormalLayout);
        painelJogoNormalLayout.setHorizontalGroup(
            painelJogoNormalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(conteudoCartaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoNormalLayout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(painelJogoNormalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoNormalLayout.createSequentialGroup()
                        .addComponent(erreiButton)
                        .addGap(118, 118, 118)
                        .addComponent(acerteiButton)
                        .addGap(76, 76, 76))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoNormalLayout.createSequentialGroup()
                        .addComponent(finalizarJogoButton)
                        .addGap(21, 21, 21))))
            .addGroup(painelJogoNormalLayout.createSequentialGroup()
                .addGroup(painelJogoNormalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelJogoNormalLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(cartaAnteriorButton)
                        .addGap(175, 175, 175)
                        .addComponent(proximaCartaButton))
                    .addGroup(painelJogoNormalLayout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(virarCardButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelJogoNormalLayout.setVerticalGroup(
            painelJogoNormalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelJogoNormalLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(finalizarJogoButton)
                .addGap(72, 72, 72)
                .addComponent(conteudoCartaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(painelJogoNormalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acerteiButton)
                    .addComponent(erreiButton))
                .addGap(30, 30, 30)
                .addComponent(virarCardButton)
                .addGap(26, 26, 26)
                .addGroup(painelJogoNormalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(proximaCartaButton)
                    .addComponent(cartaAnteriorButton))
                .addGap(22, 22, 22))
        );

        getContentPane().add(painelJogoNormal, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 440, 440));

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

        meusBaralhosPainel.setBackground(new java.awt.Color(246, 231, 211));
        meusBaralhosPainel.setPreferredSize(new java.awt.Dimension(460, 440));

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
                "nome", "materia", "Botoes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        meusBaralhosTable.setRowHeight(45);
        jScrollPane1.setViewportView(meusBaralhosTable);

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
            .addGroup(meusBaralhosPainelLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(voltarPainelInicial1Button, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(irParaCriarBaralhoButton)
                .addGap(37, 37, 37))
            .addComponent(meusBaralhosLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(meusBaralhosPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        meusBaralhosPainelLayout.setVerticalGroup(
            meusBaralhosPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meusBaralhosPainelLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(meusBaralhosLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(meusBaralhosPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(irParaCriarBaralhoButton)
                    .addComponent(voltarPainelInicial1Button))
                .addGap(17, 17, 17))
        );

        getContentPane().add(meusBaralhosPainel, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, -1, -1));

        meusCardsPanel.setBackground(new java.awt.Color(246, 231, 211));

        minhasCartasTable.setBackground(new java.awt.Color(206, 191, 171));
        minhasCartasTable.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        minhasCartasTable.setForeground(new java.awt.Color(0, 0, 0));
        minhasCartasTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pergunta", "Resposta", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
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
            minhasCartasTable.getColumnModel().getColumn(1).setResizable(false);
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
        meusCardsLabel.setText("Cards");

        javax.swing.GroupLayout meusCardsPanelLayout = new javax.swing.GroupLayout(meusCardsPanel);
        meusCardsPanel.setLayout(meusCardsPanelLayout);
        meusCardsPanelLayout.setHorizontalGroup(
            meusCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meusCardsPanelLayout.createSequentialGroup()
                .addComponent(meusCardsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(meusCardsPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(meusCardsPanelLayout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(voltarMeusBaralhosButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(irParaCriarCardButton)
                .addGap(98, 98, 98))
        );
        meusCardsPanelLayout.setVerticalGroup(
            meusCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meusCardsPanelLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(meusCardsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(meusCardsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(irParaCriarCardButton)
                    .addComponent(voltarMeusBaralhosButton))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(meusCardsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 205, 620, 480));

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

        jButton3.setBackground(new java.awt.Color(237, 30, 82));
        jButton3.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Sair");

        jButton4.setBackground(new java.awt.Color(237, 30, 82));
        jButton4.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Meus Grupos");

        javax.swing.GroupLayout painelInicialLayout = new javax.swing.GroupLayout(painelInicial);
        painelInicial.setLayout(painelInicialLayout);
        painelInicialLayout.setHorizontalGroup(
            painelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelInicialLayout.createSequentialGroup()
                .addGap(0, 141, Short.MAX_VALUE)
                .addGroup(painelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(irJogarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(abrirMeusBaralhosButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
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

        labelFundo.setForeground(new java.awt.Color(168, 168, 168));
        labelFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src.main.resources/Imagens/Design sem nome.png"))); // NOI18N
        getContentPane().add(labelFundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 880));

        javax.swing.GroupLayout painelJogoFinalizadoLayout = new javax.swing.GroupLayout(painelJogoFinalizado);
        painelJogoFinalizado.setLayout(painelJogoFinalizadoLayout);
        painelJogoFinalizadoLayout.setHorizontalGroup(
            painelJogoFinalizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        painelJogoFinalizadoLayout.setVerticalGroup(
            painelJogoFinalizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(painelJogoFinalizado, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

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
                    baralho.getTema(),};
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
                    Object[] dados = {baralho.getNomeBaralho(), baralho.getTema()};
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
            } else if (listaDeCartas.get(j).getPergunta().equals(editarPerguntaCaixaDeTexto.getText())) {
                meusCardsPanel.setVisible(true);
                editarCartasPainel.setVisible(false);
            } else {
                for (Carta carta : listaDeCartas) {
                    if (carta.getPergunta().equals(editarPerguntaCaixaDeTexto.getText())) {
                        perguntaJaExiste = true;
                        break;
                    }
                }
                if (!perguntaJaExiste) {
                    listaDeCartas.get(j).setPergunta(editarPerguntaCaixaDeTexto.getText());
                    listaDeCartas.get(j).setResposta(editarRespostaCaixaDeTexto.getText());
                    CardDAO.editar(listaDeCartas.get(j));
                    System.out.println("deu certo");
                    dtmCartas.setRowCount(0);
                    for (Carta carta : listaDeCartas) {
                        Object[] linha = {
                            carta.getPergunta(),
                            carta.getResposta(),};
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
                    Object[] dados = {carta.getPergunta(), carta.getResposta()};
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
            conteudoCartaLabel.setText(listaDeCartas.get(indice[0]).getPergunta());
        } else {
            indice[0]++;
            conteudoCartaLabel.setText(listaDeCartas.get(indice[0]).getPergunta());
            proximaCartaButton.setVisible(false);
        }
    }//GEN-LAST:event_proximaCartaButtonActionPerformed

    private void cartaAnteriorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartaAnteriorButtonActionPerformed
        proximaCartaButton.setVisible(true);
        if (indice[0] > 1) {
            indice[0]--;
            conteudoCartaLabel.setText(listaDeCartas.get(indice[0]).getPergunta());
        } else {
            indice[0]--;
            conteudoCartaLabel.setText(listaDeCartas.get(indice[0]).getPergunta());
            cartaAnteriorButton.setVisible(false);
        }
    }//GEN-LAST:event_cartaAnteriorButtonActionPerformed

    private void virarCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_virarCardButtonActionPerformed
        if (virarCardButton.getText().equals("Ver pergunta")) {
            conteudoCartaLabel.setText(listaDeCartas.get(indice[0]).getPergunta());
            virarCardButton.setText("Ver resposta");
        } else {
            conteudoCartaLabel.setText(listaDeCartas.get(indice[0]).getResposta());
            virarCardButton.setText("Ver pergunta");
        }
    }//GEN-LAST:event_virarCardButtonActionPerformed

    private void acerteiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acerteiButtonActionPerformed
        try {
            cartaJogo = listaDeCartas.get(indice[0]);
            if (!cartaJogo.isRespondida()) {
                cartaJogo.setRespondida(true);
                cartaJogo.setAcertou(true);
                int acerto = cartaJogo.getTotalDeAcertos() + 1;
                int erro = cartaJogo.getTotalDeErros();
                cartaJogo.setTotalDeAcertos(acerto);
                cartaJogo.setMediaDeAcertos((double) acerto / (acerto + erro) * 100);
                CardDAO.editar(cartaJogo);
            } else if (!cartaJogo.isAcertou()) {
                cartaJogo.setAcertou(true);
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
            } else {
                System.out.println("lista encerrada");
                painelJogoFinalizado.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_acerteiButtonActionPerformed

    private void erreiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_erreiButtonActionPerformed
        try {
            cartaJogo = listaDeCartas.get(indice[0]);
            if (!cartaJogo.isRespondida()) {
                cartaJogo.setRespondida(true);
                int acerto = cartaJogo.getTotalDeAcertos();
                int erro = cartaJogo.getTotalDeErros() + 1;
                cartaJogo.setTotalDeErros(erro);
                cartaJogo.setMediaDeAcertos( (double) acerto / (acerto + erro) * 100);
                CardDAO.editar(cartaJogo);
            } else if (cartaJogo.isAcertou()) {
                cartaJogo.setAcertou(false);
                int acerto = cartaJogo.getTotalDeAcertos() - 1;
                int erro = cartaJogo.getTotalDeErros() + 1;
                cartaJogo.setTotalDeAcertos(acerto);
                cartaJogo.setTotalDeErros(erro);
                cartaJogo.setMediaDeAcertos((double)acerto / (acerto + erro) * 100);
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
            } else {
                System.out.println("lista encerrada");
                painelJogoFinalizado.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_erreiButtonActionPerformed

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
    private javax.swing.JButton acerteiButton;
    private javax.swing.JPanel adicionarBaralhosPainel;
    private javax.swing.JPanel autenticarContaPanel;
    private javax.swing.JLabel baralhoSemCartasLabel;
    private javax.swing.JPasswordField campoConfirmarSenhaPasswordField;
    private javax.swing.JTextField campoEmailAutenticarTextField;
    private javax.swing.JTextField campoEmailTextField;
    private javax.swing.JPasswordField campoSenhaAutenticarPasswordField;
    private javax.swing.JPasswordField campoSenhaPasswordField;
    private javax.swing.JTextField campoUsuarioTextField;
    private javax.swing.JButton cartaAnteriorButton;
    private javax.swing.JButton confirmarCriarCardButton;
    private javax.swing.JButton confirmarEditarCartasButton;
    private javax.swing.JLabel confirmarSenhaLabel;
    private javax.swing.JLabel conteudoCartaLabel;
    private javax.swing.JButton criarBaralhoButton;
    private javax.swing.JPanel criarCardsPanel;
    private javax.swing.JButton criarContaButton;
    private javax.swing.JPanel criarContaPanel;
    private javax.swing.JLabel dadosInvalidosMensagem;
    private javax.swing.JPanel editarCartasPainel;
    private javax.swing.JTextField editarPerguntaCaixaDeTexto;
    private javax.swing.JTextField editarRespostaCaixaDeTexto;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JButton erreiButton;
    private javax.swing.JButton finalizarJogoButton;
    private javax.swing.JTextField inserirMateriaTextField;
    private javax.swing.JTextField inserirNomeBaralhoTextField;
    private javax.swing.JTextField inserirPerguntaCaixaDeTexto;
    private javax.swing.JTextField inserirRespostaCaixaDeTexto;
    private javax.swing.JButton irJogarButton;
    private javax.swing.JButton irParaCriarBaralhoButton;
    private javax.swing.JButton irParaCriarCardButton;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jaTenhoContaButton;
    private javax.swing.JButton jogarButton;
    private javax.swing.JLabel labelFundo;
    private javax.swing.JLabel mensagemAdicionarBaralhoInvalido;
    private javax.swing.JLabel mensagemCriarCardInvalidoLabel;
    private javax.swing.JLabel mensagemCriarContaInvalidaLabel;
    private javax.swing.JLabel mensagemEditarPerguntaInvalida;
    private javax.swing.JLabel meusBaralhosLabel;
    private javax.swing.JPanel meusBaralhosPainel;
    private javax.swing.JTable meusBaralhosTable;
    private javax.swing.JLabel meusCardsLabel;
    private javax.swing.JPanel meusCardsPanel;
    private javax.swing.JTable minhasCartasTable;
    private javax.swing.JPanel painelInicial;
    private javax.swing.JPanel painelJogoFinalizado;
    private javax.swing.JPanel painelJogoNormal;
    private javax.swing.JButton proximaCartaButton;
    private javax.swing.JButton redirecionarTelaCriarContaButton;
    private javax.swing.JPanel selecionarBaralhoJogarPainel;
    private javax.swing.JTable selecionarBaralhoJogarTable;
    private javax.swing.JLabel selecionarBaralhosJogarLabel;
    private javax.swing.JLabel senhaLabel;
    private javax.swing.JLabel usuarioLabel;
    private javax.swing.JButton virarCardButton;
    private javax.swing.JButton voltarMeusBaralhos1;
    private javax.swing.JButton voltarMeusBaralhosButton;
    private javax.swing.JButton voltarMeusCardsButton;
    private javax.swing.JButton voltarMeusCardsButton2;
    private javax.swing.JButton voltarPainelInicial1Button;
    private javax.swing.JButton voltarPainelInicialButton;
    // End of variables declaration//GEN-END:variables
}
