
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.util.Map;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;

public class JanelaInicial extends javax.swing.JFrame {

    String nomeBaralho;
    Map<String, ArrayList<String>> baralhos = new HashMap<>();
    java.util.List<String> nomesBaralhos = new ArrayList<>();

    public JanelaInicial() {
        initComponents();
        painelMenu.setVisible(false);
        painelConfirmacaoFecharJogo.setVisible(false);
        painelMudarSenha.setVisible(false);
        painelCriarBaralho.setVisible(false);
        painelCriarCards.setVisible(false);
        painelEditarBaralhos.setVisible(false);
        painelEscolherPerguntas.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        painelMenu = new javax.swing.JPanel();
        botaoSair = new javax.swing.JButton();
        botaoRetomar = new javax.swing.JButton();
        botaoAtivarMusica = new javax.swing.JButton();
        botaoAlterarSenha = new javax.swing.JButton();
        painelCriarBaralho = new javax.swing.JPanel();
        textBaralhoNome = new javax.swing.JTextField();
        botaoProsseguirCriarBaralho = new javax.swing.JButton();
        botaoVoltarCriarBaralho = new javax.swing.JButton();
        mensagemNomeIncorreto = new javax.swing.JLabel();
        painelConfirmacaoFecharJogo = new javax.swing.JPanel();
        mensagem = new javax.swing.JLabel();
        botaoSairDeVez = new javax.swing.JButton();
        botaoDesistirSair = new javax.swing.JButton();
        painelMudarSenha = new javax.swing.JPanel();
        textoSenhaVelha = new javax.swing.JTextField();
        textoSenhaNova = new javax.swing.JTextField();
        botaoVoltarAlterarSenha = new javax.swing.JButton();
        botaoConfirmarMudarSenha = new javax.swing.JButton();
        mensagemSenhaIncorreta = new javax.swing.JLabel();
        painelCriarCards = new javax.swing.JPanel();
        textResposta = new javax.swing.JTextField();
        textPergunta = new javax.swing.JTextField();
        botaoAdicionarCard = new javax.swing.JButton();
        botaoVoltarCriarCards = new javax.swing.JButton();
        mensagemConteudoIncorreto = new javax.swing.JLabel();
        painelBotoesIniciais = new javax.swing.JPanel();
        botaoCriarBaralho = new javax.swing.JButton();
        botaoAbrirMenu = new javax.swing.JButton();
        botaoJogar = new javax.swing.JButton();
        botaoEditarBaralhos = new javax.swing.JButton();
        painelEditarBaralhos = new javax.swing.JPanel();
        painelEscolherPerguntas = new javax.swing.JPanel();
        botaoFinalizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 600));

        jLayeredPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        painelMenu.setBackground(new java.awt.Color(200, 0, 0));
        painelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botaoSair.setText("Sair");
        botaoSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSairActionPerformed(evt);
            }
        });
        painelMenu.add(botaoSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 120, -1));

        botaoRetomar.setText("Retomar");
        botaoRetomar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRetomarActionPerformed(evt);
            }
        });
        painelMenu.add(botaoRetomar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 120, -1));

        botaoAtivarMusica.setText("Desativar música");
        botaoAtivarMusica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAtivarMusicaActionPerformed(evt);
            }
        });
        painelMenu.add(botaoAtivarMusica, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, -1, -1));

        botaoAlterarSenha.setText("Alterar senha");
        botaoAlterarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarSenhaActionPerformed(evt);
            }
        });
        painelMenu.add(botaoAlterarSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 120, -1));

        jLayeredPane1.add(painelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 510, 300));

        painelCriarBaralho.setBackground(new java.awt.Color(200, 150, 75));

        textBaralhoNome.setForeground(new java.awt.Color(128, 128, 128));
        textBaralhoNome.setText("Insira o nome do baralho");
        textBaralhoNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textBaralhoNomeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textBaralhoNomeFocusLost(evt);
            }
        });
        textBaralhoNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBaralhoNomeActionPerformed(evt);
            }
        });

        botaoProsseguirCriarBaralho.setText("Criar");
        botaoProsseguirCriarBaralho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoProsseguirCriarBaralhoActionPerformed(evt);
            }
        });

        botaoVoltarCriarBaralho.setText("Voltar");
        botaoVoltarCriarBaralho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVoltarCriarBaralhoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelCriarBaralhoLayout = new javax.swing.GroupLayout(painelCriarBaralho);
        painelCriarBaralho.setLayout(painelCriarBaralhoLayout);
        painelCriarBaralhoLayout.setHorizontalGroup(
            painelCriarBaralhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCriarBaralhoLayout.createSequentialGroup()
                .addGroup(painelCriarBaralhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelCriarBaralhoLayout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(painelCriarBaralhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botaoVoltarCriarBaralho)
                            .addComponent(botaoProsseguirCriarBaralho)))
                    .addGroup(painelCriarBaralhoLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(textBaralhoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelCriarBaralhoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mensagemNomeIncorreto, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelCriarBaralhoLayout.setVerticalGroup(
            painelCriarBaralhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCriarBaralhoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(textBaralhoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoProsseguirCriarBaralho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoVoltarCriarBaralho)
                .addGap(33, 33, 33)
                .addComponent(mensagemNomeIncorreto)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jLayeredPane1.add(painelCriarBaralho, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        painelConfirmacaoFecharJogo.setBackground(new java.awt.Color(0, 200, 0));

        mensagem.setBackground(new java.awt.Color(0, 0, 200));
        mensagem.setForeground(new java.awt.Color(0, 0, 200));
        mensagem.setText("quer mesmo sair?");

        botaoSairDeVez.setText("sim, sair");
        botaoSairDeVez.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSairDeVezActionPerformed(evt);
            }
        });

        botaoDesistirSair.setText("não, voltar");
        botaoDesistirSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDesistirSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelConfirmacaoFecharJogoLayout = new javax.swing.GroupLayout(painelConfirmacaoFecharJogo);
        painelConfirmacaoFecharJogo.setLayout(painelConfirmacaoFecharJogoLayout);
        painelConfirmacaoFecharJogoLayout.setHorizontalGroup(
            painelConfirmacaoFecharJogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConfirmacaoFecharJogoLayout.createSequentialGroup()
                .addGroup(painelConfirmacaoFecharJogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelConfirmacaoFecharJogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(painelConfirmacaoFecharJogoLayout.createSequentialGroup()
                            .addGap(59, 59, 59)
                            .addComponent(botaoDesistirSair, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelConfirmacaoFecharJogoLayout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(botaoSairDeVez, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(painelConfirmacaoFecharJogoLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(mensagem)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        painelConfirmacaoFecharJogoLayout.setVerticalGroup(
            painelConfirmacaoFecharJogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConfirmacaoFecharJogoLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(mensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoSairDeVez)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoDesistirSair)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jLayeredPane1.add(painelConfirmacaoFecharJogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 210, 190));

        painelMudarSenha.setBackground(new java.awt.Color(0, 0, 255));

        textoSenhaVelha.setForeground(new java.awt.Color(128, 128, 128));
        textoSenhaVelha.setText("Insira a senha antiga");
        textoSenhaVelha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textoSenhaVelhaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textoSenhaVelhaFocusLost(evt);
            }
        });

        textoSenhaNova.setForeground(new java.awt.Color(128, 128, 128));
        textoSenhaNova.setText("Insira a nova senha");
        textoSenhaNova.setActionCommand("<Not Set>");
        textoSenhaNova.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textoSenhaNovaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textoSenhaNovaFocusLost(evt);
            }
        });

        botaoVoltarAlterarSenha.setBackground(new java.awt.Color(200, 0, 0));
        botaoVoltarAlterarSenha.setText("Voltar");
        botaoVoltarAlterarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVoltarAlterarSenhaActionPerformed(evt);
            }
        });

        botaoConfirmarMudarSenha.setBackground(new java.awt.Color(0, 200, 0));
        botaoConfirmarMudarSenha.setText("Alterar");
        botaoConfirmarMudarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoConfirmarMudarSenhaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelMudarSenhaLayout = new javax.swing.GroupLayout(painelMudarSenha);
        painelMudarSenha.setLayout(painelMudarSenhaLayout);
        painelMudarSenhaLayout.setHorizontalGroup(
            painelMudarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelMudarSenhaLayout.createSequentialGroup()
                .addGroup(painelMudarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mensagemSenhaIncorreta, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelMudarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(painelMudarSenhaLayout.createSequentialGroup()
                            .addGap(37, 37, 37)
                            .addGroup(painelMudarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(textoSenhaNova, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textoSenhaVelha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(painelMudarSenhaLayout.createSequentialGroup()
                            .addGap(62, 62, 62)
                            .addGroup(painelMudarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(botaoVoltarAlterarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(botaoConfirmarMudarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        painelMudarSenhaLayout.setVerticalGroup(
            painelMudarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelMudarSenhaLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(textoSenhaVelha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textoSenhaNova, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoConfirmarMudarSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoVoltarAlterarSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mensagemSenhaIncorreta)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jLayeredPane1.add(painelMudarSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, -1, -1));

        painelCriarCards.setBackground(new java.awt.Color(120, 0, 65));

        textResposta.setForeground(new java.awt.Color(128, 128, 128));
        textResposta.setText("Insira a resposta");
        textResposta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textRespostaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textRespostaFocusLost(evt);
            }
        });

        textPergunta.setForeground(new java.awt.Color(128, 128, 128));
        textPergunta.setText("Insira a pergunta");
        textPergunta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textPerguntaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textPerguntaFocusLost(evt);
            }
        });

        botaoAdicionarCard.setText("Adicionar Card");
        botaoAdicionarCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAdicionarCardActionPerformed(evt);
            }
        });

        botaoVoltarCriarCards.setText("Finalizar");
        botaoVoltarCriarCards.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVoltarCriarCardsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelCriarCardsLayout = new javax.swing.GroupLayout(painelCriarCards);
        painelCriarCards.setLayout(painelCriarCardsLayout);
        painelCriarCardsLayout.setHorizontalGroup(
            painelCriarCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCriarCardsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelCriarCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelCriarCardsLayout.createSequentialGroup()
                        .addGroup(painelCriarCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textPergunta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textResposta, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoAdicionarCard)
                            .addComponent(botaoVoltarCriarCards, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 29, Short.MAX_VALUE))
                    .addComponent(mensagemConteudoIncorreto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        painelCriarCardsLayout.setVerticalGroup(
            painelCriarCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCriarCardsLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(textPergunta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(textResposta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoAdicionarCard)
                .addGap(18, 18, 18)
                .addComponent(botaoVoltarCriarCards)
                .addGap(31, 31, 31)
                .addComponent(mensagemConteudoIncorreto)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        jLayeredPane1.add(painelCriarCards, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 150, 300));

        botaoCriarBaralho.setText("Criar baralhos");
        botaoCriarBaralho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCriarBaralhoActionPerformed(evt);
            }
        });

        botaoAbrirMenu.setText("Menu");
        botaoAbrirMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAbrirMenuActionPerformed(evt);
            }
        });

        botaoJogar.setText("Jogar");

        botaoEditarBaralhos.setText("Editar baralhos");
        botaoEditarBaralhos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditarBaralhosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelBotoesIniciaisLayout = new javax.swing.GroupLayout(painelBotoesIniciais);
        painelBotoesIniciais.setLayout(painelBotoesIniciaisLayout);
        painelBotoesIniciaisLayout.setHorizontalGroup(
            painelBotoesIniciaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelBotoesIniciaisLayout.createSequentialGroup()
                .addGroup(painelBotoesIniciaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelBotoesIniciaisLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(painelBotoesIniciaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(botaoEditarBaralhos)
                            .addComponent(botaoJogar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoCriarBaralho, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(painelBotoesIniciaisLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(botaoAbrirMenu)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        painelBotoesIniciaisLayout.setVerticalGroup(
            painelBotoesIniciaisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelBotoesIniciaisLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(botaoJogar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoEditarBaralhos)
                .addGap(18, 18, 18)
                .addComponent(botaoCriarBaralho)
                .addGap(18, 18, 18)
                .addComponent(botaoAbrirMenu)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        jLayeredPane1.add(painelBotoesIniciais, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, -1, -1));

        painelEditarBaralhos.setBackground(new java.awt.Color(120, 35, 200));

        javax.swing.GroupLayout painelEditarBaralhosLayout = new javax.swing.GroupLayout(painelEditarBaralhos);
        painelEditarBaralhos.setLayout(painelEditarBaralhosLayout);
        painelEditarBaralhosLayout.setHorizontalGroup(
            painelEditarBaralhosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        painelEditarBaralhosLayout.setVerticalGroup(
            painelEditarBaralhosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jLayeredPane1.add(painelEditarBaralhos, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 400, 300));

        painelEscolherPerguntas.setBackground(new java.awt.Color(120, 0, 120));

        botaoFinalizar.setText("Finalizar");
        botaoFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFinalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelEscolherPerguntasLayout = new javax.swing.GroupLayout(painelEscolherPerguntas);
        painelEscolherPerguntas.setLayout(painelEscolherPerguntasLayout);
        painelEscolherPerguntasLayout.setHorizontalGroup(
            painelEscolherPerguntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEscolherPerguntasLayout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(botaoFinalizar)
                .addContainerGap(176, Short.MAX_VALUE))
        );
        painelEscolherPerguntasLayout.setVerticalGroup(
            painelEscolherPerguntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelEscolherPerguntasLayout.createSequentialGroup()
                .addContainerGap(241, Short.MAX_VALUE)
                .addComponent(botaoFinalizar)
                .addGap(66, 66, 66))
        );

        jLayeredPane1.add(painelEscolherPerguntas, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 410, 330));

        getContentPane().add(jLayeredPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private static void limparCaixaDeTexto(JTextField texto, String mensagem) {
        if (texto.getText().equals(mensagem)) {
            texto.setText("");
            texto.setForeground(Color.BLACK);
        }
    }

    private static void retornarCaixaDeTexto(JTextField texto, String mensagem) {
        if (texto.getText().equals("")) {
            texto.setText(mensagem);
            texto.setForeground(Color.GRAY);
        }
    }
    private void botaoAbrirMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAbrirMenuActionPerformed
        painelMenu.setVisible(true);
        painelBotoesIniciais.setVisible(false);
    }//GEN-LAST:event_botaoAbrirMenuActionPerformed

    private void botaoAlterarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarSenhaActionPerformed
        painelMudarSenha.setVisible(true);
        painelMenu.setVisible(false);
        botaoConfirmarMudarSenha.requestFocusInWindow();
    }//GEN-LAST:event_botaoAlterarSenhaActionPerformed

    private void botaoAtivarMusicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtivarMusicaActionPerformed
        if (botaoAtivarMusica.getText().equals("Desativar música")) {
            botaoAtivarMusica.setText("    Ativar música  ");
        } else {
            botaoAtivarMusica.setText("Desativar música");
        }
    }//GEN-LAST:event_botaoAtivarMusicaActionPerformed

    private void botaoRetomarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRetomarActionPerformed
        painelMenu.setVisible(false);
        painelBotoesIniciais.setVisible(true);
    }//GEN-LAST:event_botaoRetomarActionPerformed

    private void botaoSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSairActionPerformed
        painelConfirmacaoFecharJogo.setVisible(true);
        painelMenu.setVisible(false);
    }//GEN-LAST:event_botaoSairActionPerformed

    private void botaoSairDeVezActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSairDeVezActionPerformed
        System.exit(0);
    }//GEN-LAST:event_botaoSairDeVezActionPerformed

    private void botaoDesistirSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDesistirSairActionPerformed
        painelConfirmacaoFecharJogo.setVisible(false);
        painelMenu.setVisible(true);
    }//GEN-LAST:event_botaoDesistirSairActionPerformed

    private void botaoVoltarAlterarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVoltarAlterarSenhaActionPerformed
        painelMudarSenha.setVisible(false);
        painelMenu.setVisible(true);
        textoSenhaVelha.setText("Insira a senha antiga");
        textoSenhaVelha.setForeground(Color.GRAY);
        textoSenhaNova.setText("Insira a nova senha");
        textoSenhaNova.setForeground(Color.GRAY);
        mensagemSenhaIncorreta.setText("");
    }//GEN-LAST:event_botaoVoltarAlterarSenhaActionPerformed

    private void botaoCriarBaralhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCriarBaralhoActionPerformed
        painelCriarBaralho.setVisible(true);
        painelBotoesIniciais.setVisible(false);
        botaoProsseguirCriarBaralho.requestFocusInWindow();// TODO add your handling code here:
    }//GEN-LAST:event_botaoCriarBaralhoActionPerformed

    private void textBaralhoNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBaralhoNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBaralhoNomeActionPerformed

    private void botaoVoltarCriarBaralhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVoltarCriarBaralhoActionPerformed
        painelCriarBaralho.setVisible(false);
        painelBotoesIniciais.setVisible(true);
        mensagemNomeIncorreto.setText("");
        textBaralhoNome.setText("Insira o nome do baralho");
        textBaralhoNome.setForeground(Color.GRAY);
    }//GEN-LAST:event_botaoVoltarCriarBaralhoActionPerformed

    private void botaoProsseguirCriarBaralhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoProsseguirCriarBaralhoActionPerformed
        if (textBaralhoNome.getText().equals("Insira o nome do baralho")) {
            mensagemNomeIncorreto.setText("Insira um nome de baralho válido");
        } else {
            nomeBaralho = textBaralhoNome.getText();
            if (baralhos.containsKey(nomeBaralho)) {
                //criar janela de exibicao que o baralho ja existe
            } else {
                nomesBaralhos.add(nomeBaralho);
                painelCriarBaralho.setVisible(false);
                painelCriarCards.setVisible(true);
                baralhos.put(nomeBaralho, new ArrayList<>());
            }
            mensagemNomeIncorreto.setText("");
            textBaralhoNome.setText("Insira o nome do baralho");
            textBaralhoNome.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_botaoProsseguirCriarBaralhoActionPerformed

    private void botaoVoltarCriarCardsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVoltarCriarCardsActionPerformed
        painelCriarCards.setVisible(false);
        painelBotoesIniciais.setVisible(true);
        textBaralhoNome.setText("Insira o nome do baralho");
        textBaralhoNome.setForeground(Color.GRAY);
        mensagemConteudoIncorreto.setText("");
        textResposta.setText("Insira a resposta");
        textPergunta.setText("Insira a pergunta");
        textPergunta.setForeground(Color.GRAY);
        textResposta.setForeground(Color.GRAY);
    }//GEN-LAST:event_botaoVoltarCriarCardsActionPerformed

    private void textPerguntaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textPerguntaFocusGained
        limparCaixaDeTexto(textPergunta, "Insira a pergunta");
        mensagemConteudoIncorreto.setText("");
    }//GEN-LAST:event_textPerguntaFocusGained

    private void textPerguntaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textPerguntaFocusLost
        retornarCaixaDeTexto(textPergunta, "Insira a pergunta");
    }//GEN-LAST:event_textPerguntaFocusLost

    private void textRespostaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textRespostaFocusGained
        limparCaixaDeTexto(textResposta, "Insira a resposta");
        mensagemConteudoIncorreto.setText("");
    }//GEN-LAST:event_textRespostaFocusGained

    private void textRespostaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textRespostaFocusLost
        retornarCaixaDeTexto(textResposta, "Insira a resposta");
    }//GEN-LAST:event_textRespostaFocusLost

    private void textoSenhaNovaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textoSenhaNovaFocusGained
        limparCaixaDeTexto(textoSenhaNova, "Insira a nova senha");
        mensagemSenhaIncorreta.setText("");
    }//GEN-LAST:event_textoSenhaNovaFocusGained

    private void textoSenhaNovaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textoSenhaNovaFocusLost
        retornarCaixaDeTexto(textoSenhaNova, "Insira a nova senha");
    }//GEN-LAST:event_textoSenhaNovaFocusLost

    private void textoSenhaVelhaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textoSenhaVelhaFocusGained
        limparCaixaDeTexto(textoSenhaVelha, "Insira a senha antiga");
        mensagemSenhaIncorreta.setText("");
    }//GEN-LAST:event_textoSenhaVelhaFocusGained

    private void textoSenhaVelhaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textoSenhaVelhaFocusLost
        retornarCaixaDeTexto(textoSenhaVelha, "Insira a senha antiga");
    }//GEN-LAST:event_textoSenhaVelhaFocusLost

    private void textBaralhoNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textBaralhoNomeFocusGained
        limparCaixaDeTexto(textBaralhoNome, "Insira o nome do baralho");
        mensagemNomeIncorreto.setText("");
    }//GEN-LAST:event_textBaralhoNomeFocusGained

    private void textBaralhoNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textBaralhoNomeFocusLost
        retornarCaixaDeTexto(textBaralhoNome, "Insira o nome do baralho");
    }//GEN-LAST:event_textBaralhoNomeFocusLost

    private void botaoConfirmarMudarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoConfirmarMudarSenhaActionPerformed
        if (textoSenhaVelha.getText().equals("Insira a senha antiga") || textoSenhaNova.getText().equals("Insira a nova senha")) {        // TODO add your handling code here:
            mensagemSenhaIncorreta.setText("Insira valores válidos");
        } else {
            mensagemSenhaIncorreta.setText("Senha alterada com sucesso");
            textoSenhaNova.setText("Insira a nova senha");
            textoSenhaVelha.setText("Insira a senha antiga");
            textoSenhaVelha.setForeground(Color.GRAY);
            textoSenhaNova.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_botaoConfirmarMudarSenhaActionPerformed

    private void botaoAdicionarCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarCardActionPerformed
        if (textPergunta.getText().equals("Insira a pergunta") || textResposta.getText().equals("Insira a resposta")) {
            mensagemConteudoIncorreto.setText("Insira valores válidos");
        } else {
            baralhos.get(nomeBaralho).add(textPergunta.getText());
            baralhos.get(nomeBaralho).add(textResposta.getText());
            textPergunta.setText("Insira a pergunta");
            textResposta.setText("Insira a resposta");
            textPergunta.setForeground(Color.GRAY);
            textResposta.setForeground(Color.GRAY);
            mensagemConteudoIncorreto.setText("Card adicionado com sucesso");
        }
    }//GEN-LAST:event_botaoAdicionarCardActionPerformed

    private void botaoEditarBaralhosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarBaralhosActionPerformed
        // TODO add your handling code here:
        painelBotoesIniciais.setVisible(false);
        painelEditarBaralhos.removeAll();
        painelEditarBaralhos.setLayout(new GridLayout(0, 3, 10, 10));
        JButton botaoVoltarEditarBaralho = new JButton("Voltar");
        botaoVoltarEditarBaralho.addActionListener(e -> {
            painelBotoesIniciais.setVisible(true);
            painelEditarBaralhos.removeAll();
            painelEditarBaralhos.setVisible(false);
        });
        painelEditarBaralhos.add(botaoVoltarEditarBaralho);
        JLabel mensagemQualBaralhoEscolher = new JLabel("Selecione o baralho que deseja editar");
        painelEditarBaralhos.add(mensagemQualBaralhoEscolher);
        for (int i = 0; i < nomesBaralhos.size(); i++) {
            final int index = i;
            JButton button = new JButton(nomesBaralhos.get(i));
            button.addActionListener(e -> {
                painelEditarBaralhos.setVisible(false);
                criarBotoes(index);
                painelEscolherPerguntas.setVisible(true);
            });
            painelEditarBaralhos.add(button);
        }
        painelEditarBaralhos.revalidate();
        painelEditarBaralhos.repaint();
        painelEditarBaralhos.setVisible(true);
    }//GEN-LAST:event_botaoEditarBaralhosActionPerformed

    private void botaoFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFinalizarActionPerformed
        painelEscolherPerguntas.removeAll();
        painelEscolherPerguntas.setVisible(false);
        painelBotoesIniciais.setVisible(true);
    }//GEN-LAST:event_botaoFinalizarActionPerformed

    private void criarBotoes(int index) {
        painelEscolherPerguntas.removeAll();
        if (painelEscolherPerguntas.getLayout() == null) {
            painelEscolherPerguntas.setLayout(new BoxLayout(painelEscolherPerguntas, BoxLayout.Y_AXIS));
        }
        for (int j = 0; j < baralhos.get(nomesBaralhos.get(index)).size(); j += 2) {
            int numero = j;

            JPanel cartaPanel = criarPainel(numero, index);
            painelEscolherPerguntas.add(cartaPanel);
        }

        painelEscolherPerguntas.revalidate();
        painelEscolherPerguntas.repaint();
    }

    private JPanel criarPainel(int numero, int index) {
        JPanel cartaPanel = new JPanel();
        cartaPanel.setLayout(new BoxLayout(cartaPanel, BoxLayout.Y_AXIS));
        cartaPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                String.format("Carta %d", (numero / 2) + 1),
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 12)
        ));

        JLabel mensagemPergunta = new JLabel("Pergunta:");
        JTextField textoPergunta = new JTextField(20);
        textoPergunta.setText(baralhos.get(nomesBaralhos.get(index)).get(numero));
        JLabel mensagemResposta = new JLabel("Resposta:");
        JTextField textoResposta = new JTextField(20);
        textoResposta.setText(baralhos.get(nomesBaralhos.get(index)).get(numero + 1));
        JButton botao = new JButton("excluir");
        botao.addActionListener((ActionEvent e) -> {
            baralhos.get(nomesBaralhos.get(index)).remove(numero+1);
            baralhos.get(nomesBaralhos.get(index)).remove(numero);
            criarBotoes(index);
        });
        cartaPanel.add(mensagemPergunta);
        cartaPanel.add(textoPergunta);
        cartaPanel.add(mensagemResposta);
        cartaPanel.add(textoResposta);
        cartaPanel.add(botao);
        return cartaPanel;
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new FlatLightLaf());
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                new JanelaInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAbrirMenu;
    private javax.swing.JButton botaoAdicionarCard;
    private javax.swing.JButton botaoAlterarSenha;
    private javax.swing.JButton botaoAtivarMusica;
    private javax.swing.JButton botaoConfirmarMudarSenha;
    private javax.swing.JButton botaoCriarBaralho;
    private javax.swing.JButton botaoDesistirSair;
    private javax.swing.JButton botaoEditarBaralhos;
    private javax.swing.JButton botaoFinalizar;
    private javax.swing.JButton botaoJogar;
    private javax.swing.JButton botaoProsseguirCriarBaralho;
    private javax.swing.JButton botaoRetomar;
    private javax.swing.JButton botaoSair;
    private javax.swing.JButton botaoSairDeVez;
    private javax.swing.JButton botaoVoltarAlterarSenha;
    private javax.swing.JButton botaoVoltarCriarBaralho;
    private javax.swing.JButton botaoVoltarCriarCards;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel mensagem;
    private javax.swing.JLabel mensagemConteudoIncorreto;
    private javax.swing.JLabel mensagemNomeIncorreto;
    private javax.swing.JLabel mensagemSenhaIncorreta;
    private javax.swing.JPanel painelBotoesIniciais;
    private javax.swing.JPanel painelConfirmacaoFecharJogo;
    private javax.swing.JPanel painelCriarBaralho;
    private javax.swing.JPanel painelCriarCards;
    private javax.swing.JPanel painelEditarBaralhos;
    private javax.swing.JPanel painelEscolherPerguntas;
    private javax.swing.JPanel painelMenu;
    private javax.swing.JPanel painelMudarSenha;
    private javax.swing.JTextField textBaralhoNome;
    private javax.swing.JTextField textPergunta;
    private javax.swing.JTextField textResposta;
    private javax.swing.JTextField textoSenhaNova;
    private javax.swing.JTextField textoSenhaVelha;
    // End of variables declaration//GEN-END:variables
}
