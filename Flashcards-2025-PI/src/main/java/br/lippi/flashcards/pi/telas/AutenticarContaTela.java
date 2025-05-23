/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.lippi.flashcards.pi.telas;

import br.lippi.flashcards.pi.modelo.Usuario;
import br.lippi.flashcards.pi.persistencia.UsuarioDAO;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class AutenticarContaTela extends javax.swing.JFrame {

    /**
     * Creates new form AutenticarContaTela
     */
    public AutenticarContaTela() {
        initComponents();
        criarContaPainel.setVisible(false);
        redirecionarTelaCriarContaButton.setContentAreaFilled(false);
        redirecionarTelaAlterarSenhaButton.setContentAreaFilled(false);
        jaTenhoContaButton.setContentAreaFilled(false);
        inicioJogoPanel.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        var usuario = new Usuario(email, nomeUsuario, senha, validar);
        var usuarioDAO = new UsuarioDAO();
        usuarioDAO.cadastrar(usuario);
    }

    public static Usuario verificarUser(String email, String senha) throws Exception {
        Usuario verificacao = new Usuario(email, "nomeTeste", senha, "valorFodase");
        var usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.autenticar(verificacao);
        return usuario;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        autenticarContaPainel = new javax.swing.JPanel();
        EmailLabel = new javax.swing.JLabel();
        campoEmailAutenticarTextField = new javax.swing.JTextField();
        SenhaLabel = new javax.swing.JLabel();
        EntrarButton = new javax.swing.JButton();
        campoSenhaAutenticarPasswordField = new javax.swing.JPasswordField();
        redirecionarTelaAlterarSenhaButton = new javax.swing.JButton();
        redirecionarTelaCriarContaButton = new javax.swing.JButton();
        dadosInvalidosLabel = new javax.swing.JLabel();
        inicioJogoPanel = new javax.swing.JPanel();
        botoesIniciaisPanel = new javax.swing.JPanel();
        jogarButton = new javax.swing.JButton();
        verBaralhosButton = new javax.swing.JButton();
        verGrupoAlunosButton = new javax.swing.JButton();
        sairJogoButton = new javax.swing.JButton();
        ativarDesativarMusicaButton = new javax.swing.JButton();
        criarContaPainel = new javax.swing.JPanel();
        emailLabel = new javax.swing.JLabel();
        campoEmailTextField = new javax.swing.JTextField();
        senhaLabel = new javax.swing.JLabel();
        campoSenhaPasswordField = new javax.swing.JPasswordField();
        usuarioLabel = new javax.swing.JLabel();
        confirmarSenhaLabel = new javax.swing.JLabel();
        criarContaButton = new javax.swing.JButton();
        campoUsuarioTextField = new javax.swing.JTextField();
        campoConfirmarSenhaPasswordField = new javax.swing.JPasswordField();
        mensagemCriarContaInvalidaLabel = new javax.swing.JLabel();
        jaTenhoContaButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Autenticação de Conta");

        EmailLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        EmailLabel.setText("E-mail");

        campoEmailAutenticarTextField.setBackground(new java.awt.Color(28, 181, 196));
        campoEmailAutenticarTextField.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        campoEmailAutenticarTextField.setForeground(new java.awt.Color(255, 255, 255));
        campoEmailAutenticarTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        campoEmailAutenticarTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoEmailAutenticarTextFieldActionPerformed(evt);
            }
        });

        SenhaLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        SenhaLabel.setText("Senha");

        EntrarButton.setBackground(new java.awt.Color(237, 30, 82));
        EntrarButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
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

        redirecionarTelaAlterarSenhaButton.setBackground(new java.awt.Color(60, 63, 65));
        redirecionarTelaAlterarSenhaButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        redirecionarTelaAlterarSenhaButton.setForeground(new java.awt.Color(28, 181, 196));
        redirecionarTelaAlterarSenhaButton.setText("Alterar Senha");
        redirecionarTelaAlterarSenhaButton.setBorder(null);
        redirecionarTelaAlterarSenhaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redirecionarTelaAlterarSenhaButtonActionPerformed(evt);
            }
        });

        redirecionarTelaCriarContaButton.setBackground(new java.awt.Color(60, 63, 65));
        redirecionarTelaCriarContaButton.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        redirecionarTelaCriarContaButton.setForeground(new java.awt.Color(28, 181, 196));
        redirecionarTelaCriarContaButton.setText("Criar Conta");
        redirecionarTelaCriarContaButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        redirecionarTelaCriarContaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redirecionarTelaCriarContaButtonActionPerformed(evt);
            }
        });

        dadosInvalidosLabel.setForeground(new java.awt.Color(28, 181, 196));

        javax.swing.GroupLayout autenticarContaPainelLayout = new javax.swing.GroupLayout(autenticarContaPainel);
        autenticarContaPainel.setLayout(autenticarContaPainelLayout);
        autenticarContaPainelLayout.setHorizontalGroup(
            autenticarContaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(autenticarContaPainelLayout.createSequentialGroup()
                .addGroup(autenticarContaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(autenticarContaPainelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(autenticarContaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(campoEmailAutenticarTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoSenhaAutenticarPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(autenticarContaPainelLayout.createSequentialGroup()
                                .addComponent(redirecionarTelaAlterarSenhaButton)
                                .addGap(26, 26, 26)
                                .addComponent(redirecionarTelaCriarContaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(autenticarContaPainelLayout.createSequentialGroup()
                                .addComponent(dadosInvalidosLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11))))
                    .addGroup(autenticarContaPainelLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(SenhaLabel))
                    .addGroup(autenticarContaPainelLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(EmailLabel))
                    .addGroup(autenticarContaPainelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(EntrarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        autenticarContaPainelLayout.setVerticalGroup(
            autenticarContaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(autenticarContaPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(EmailLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoEmailAutenticarTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SenhaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoSenhaAutenticarPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(dadosInvalidosLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(autenticarContaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(redirecionarTelaAlterarSenhaButton)
                    .addComponent(redirecionarTelaCriarContaButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EntrarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jogarButton.setBackground(new java.awt.Color(237, 30, 82));
        jogarButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jogarButton.setForeground(new java.awt.Color(255, 255, 255));
        jogarButton.setText("Jogar");

        verBaralhosButton.setBackground(new java.awt.Color(237, 30, 82));
        verBaralhosButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        verBaralhosButton.setForeground(new java.awt.Color(255, 255, 255));
        verBaralhosButton.setText("Meus Baralhos");

        verGrupoAlunosButton.setBackground(new java.awt.Color(237, 30, 82));
        verGrupoAlunosButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        verGrupoAlunosButton.setForeground(new java.awt.Color(255, 255, 255));
        verGrupoAlunosButton.setText("Meus Grupos");
        verGrupoAlunosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verGrupoAlunosButtonActionPerformed(evt);
            }
        });

        sairJogoButton.setBackground(new java.awt.Color(237, 30, 82));
        sairJogoButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        sairJogoButton.setForeground(new java.awt.Color(255, 255, 255));
        sairJogoButton.setText("Sair");
        sairJogoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairJogoButtonActionPerformed(evt);
            }
        });

        ativarDesativarMusicaButton.setText("jButton1");

        javax.swing.GroupLayout botoesIniciaisPanelLayout = new javax.swing.GroupLayout(botoesIniciaisPanel);
        botoesIniciaisPanel.setLayout(botoesIniciaisPanelLayout);
        botoesIniciaisPanelLayout.setHorizontalGroup(
            botoesIniciaisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botoesIniciaisPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(botoesIniciaisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jogarButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sairJogoButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(verGrupoAlunosButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(verBaralhosButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                    .addGroup(botoesIniciaisPanelLayout.createSequentialGroup()
                        .addComponent(ativarDesativarMusicaButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        botoesIniciaisPanelLayout.setVerticalGroup(
            botoesIniciaisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botoesIniciaisPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ativarDesativarMusicaButton)
                .addGap(38, 38, 38)
                .addComponent(jogarButton)
                .addGap(18, 18, 18)
                .addComponent(verBaralhosButton)
                .addGap(18, 18, 18)
                .addComponent(verGrupoAlunosButton)
                .addGap(18, 18, 18)
                .addComponent(sairJogoButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout inicioJogoPanelLayout = new javax.swing.GroupLayout(inicioJogoPanel);
        inicioJogoPanel.setLayout(inicioJogoPanelLayout);
        inicioJogoPanelLayout.setHorizontalGroup(
            inicioJogoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inicioJogoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botoesIniciaisPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        inicioJogoPanelLayout.setVerticalGroup(
            inicioJogoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inicioJogoPanelLayout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addComponent(botoesIniciaisPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        emailLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        emailLabel.setText("E-mail");

        campoEmailTextField.setBackground(new java.awt.Color(28, 181, 196));
        campoEmailTextField.setFont(new java.awt.Font("Gill Sans MT", 1, 18)); // NOI18N

        senhaLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        senhaLabel.setText("Senha");

        campoSenhaPasswordField.setBackground(new java.awt.Color(28, 181, 196));
        campoSenhaPasswordField.setFont(new java.awt.Font("Gill Sans MT", 1, 18)); // NOI18N

        usuarioLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        usuarioLabel.setText("Usuário");

        confirmarSenhaLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        confirmarSenhaLabel.setText("Confirmar senha");

        criarContaButton.setBackground(new java.awt.Color(237, 30, 82));
        criarContaButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        criarContaButton.setForeground(new java.awt.Color(255, 255, 255));
        criarContaButton.setText("Criar conta");
        criarContaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarContaButtonActionPerformed(evt);
            }
        });

        campoUsuarioTextField.setBackground(new java.awt.Color(28, 181, 196));
        campoUsuarioTextField.setFont(new java.awt.Font("Gill Sans MT", 1, 18)); // NOI18N
        campoUsuarioTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoUsuarioTextFieldActionPerformed(evt);
            }
        });

        campoConfirmarSenhaPasswordField.setBackground(new java.awt.Color(28, 181, 196));
        campoConfirmarSenhaPasswordField.setFont(new java.awt.Font("Gill Sans MT", 1, 18)); // NOI18N

        mensagemCriarContaInvalidaLabel.setForeground(new java.awt.Color(28, 181, 196));

        jaTenhoContaButton.setForeground(new java.awt.Color(28, 181, 196));
        jaTenhoContaButton.setText("Já tenho conta");
        jaTenhoContaButton.setBorder(null);
        jaTenhoContaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jaTenhoContaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout criarContaPainelLayout = new javax.swing.GroupLayout(criarContaPainel);
        criarContaPainel.setLayout(criarContaPainelLayout);
        criarContaPainelLayout.setHorizontalGroup(
            criarContaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(criarContaPainelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(criarContaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(emailLabel)
                    .addComponent(senhaLabel)
                    .addComponent(campoEmailTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(campoSenhaPasswordField))
                .addGroup(criarContaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(criarContaPainelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(usuarioLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, criarContaPainelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(criarContaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(confirmarSenhaLabel)
                            .addComponent(campoConfirmarSenhaPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(criarContaPainelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(campoUsuarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(15, Short.MAX_VALUE))))
            .addGroup(criarContaPainelLayout.createSequentialGroup()
                .addGroup(criarContaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(criarContaPainelLayout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jaTenhoContaButton))
                    .addGroup(criarContaPainelLayout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(criarContaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(criarContaPainelLayout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(mensagemCriarContaInvalidaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        criarContaPainelLayout.setVerticalGroup(
            criarContaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(criarContaPainelLayout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addGroup(criarContaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel)
                    .addComponent(usuarioLabel))
                .addGap(18, 18, 18)
                .addGroup(criarContaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoEmailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoUsuarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(criarContaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(senhaLabel)
                    .addComponent(confirmarSenhaLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(criarContaPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoSenhaPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoConfirmarSenhaPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(mensagemCriarContaInvalidaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jaTenhoContaButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(criarContaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addComponent(criarContaPainel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(inicioJogoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102)
                .addComponent(autenticarContaPainel, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(818, 818, 818))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(criarContaPainel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(338, 338, 338))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(autenticarContaPainel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(281, 281, 281))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(inicioJogoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(194, 194, 194))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EntrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EntrarButtonActionPerformed
        try {
            String email = campoEmailAutenticarTextField.getText();
            var pass = campoSenhaAutenticarPasswordField.getPassword();
            String senha = new String(pass);
            Usuario usuario = verificarUser(email, senha);
            if (usuario != null) {
                inicioJogoPanel.setVisible(true);
                autenticarContaPainel.setVisible(false);
            } else {
                dadosInvalidosLabel.setText("Insira valores validos");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Tente novamente mais tarde!");
        }
    }//GEN-LAST:event_EntrarButtonActionPerformed

    private void redirecionarTelaAlterarSenhaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redirecionarTelaAlterarSenhaButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_redirecionarTelaAlterarSenhaButtonActionPerformed

    private void redirecionarTelaCriarContaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redirecionarTelaCriarContaButtonActionPerformed
        autenticarContaPainel.setVisible(false);
        criarContaPainel.setVisible(true);
    }//GEN-LAST:event_redirecionarTelaCriarContaButtonActionPerformed

    private void sairJogoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairJogoButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sairJogoButtonActionPerformed

    private void verGrupoAlunosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verGrupoAlunosButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_verGrupoAlunosButtonActionPerformed

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
            } else if (senha.equals(confirmarSenha)){
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

    private void campoEmailAutenticarTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoEmailAutenticarTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoEmailAutenticarTextFieldActionPerformed

    private void campoSenhaAutenticarPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoSenhaAutenticarPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoSenhaAutenticarPasswordFieldActionPerformed

    private void campoUsuarioTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoUsuarioTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoUsuarioTextFieldActionPerformed

    private void jaTenhoContaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jaTenhoContaButtonActionPerformed
        // TODO add your handling code here:
        criarContaPainel.setVisible(false);
        autenticarContaPainel.setVisible(true);
    }//GEN-LAST:event_jaTenhoContaButtonActionPerformed

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
            java.util.logging.Logger.getLogger(AutenticarContaTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AutenticarContaTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AutenticarContaTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AutenticarContaTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AutenticarContaTela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EmailLabel;
    private javax.swing.JButton EntrarButton;
    private javax.swing.JLabel SenhaLabel;
    private javax.swing.JButton ativarDesativarMusicaButton;
    private javax.swing.JPanel autenticarContaPainel;
    private javax.swing.JPanel botoesIniciaisPanel;
    private javax.swing.JPasswordField campoConfirmarSenhaPasswordField;
    private javax.swing.JTextField campoEmailAutenticarTextField;
    private javax.swing.JTextField campoEmailTextField;
    private javax.swing.JPasswordField campoSenhaAutenticarPasswordField;
    private javax.swing.JPasswordField campoSenhaPasswordField;
    private javax.swing.JTextField campoUsuarioTextField;
    private javax.swing.JLabel confirmarSenhaLabel;
    private javax.swing.JButton criarContaButton;
    private javax.swing.JPanel criarContaPainel;
    private javax.swing.JLabel dadosInvalidosLabel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JPanel inicioJogoPanel;
    private javax.swing.JButton jaTenhoContaButton;
    private javax.swing.JButton jogarButton;
    private javax.swing.JLabel mensagemCriarContaInvalidaLabel;
    private javax.swing.JButton redirecionarTelaAlterarSenhaButton;
    private javax.swing.JButton redirecionarTelaCriarContaButton;
    private javax.swing.JButton sairJogoButton;
    private javax.swing.JLabel senhaLabel;
    private javax.swing.JLabel usuarioLabel;
    private javax.swing.JButton verBaralhosButton;
    private javax.swing.JButton verGrupoAlunosButton;
    // End of variables declaration//GEN-END:variables
}
