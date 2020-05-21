
package prog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/** 
 * Carrega e controla a tela do jogo.
 * @author Jonatha Cardoso
 */
public class Jogo implements Runnable
{
    
    private JLabel painelImagem;
    private JLabel painelLegenda;
    private JLabel painelBotoes;
    private JLabel painelPlacar;

    private JLabel tempo;
    private JLabel placar1;
    private JLabel placar2;
    private JLabel miniatura1;
    private JLabel miniatura2;
    private JLabel imagemJogador1;
    private JLabel imagemJogador2;
    
    private int pontos1;
    private int pontos2;
    private int jogador1;
    private int jogador2;
    
    private boolean jogoAtivo;
    
    /** 
     * Construtor da classe, que monta a tela.
     */
    public Jogo()
    {        
        this.montarPainelJogadores();
        this.montarPainelPlacar();
        this.montarPainelImagem();
        this.montarPainelBotoes();
        this.montarPainelMensagem();
    }
    
    /** 
     * Monta o painel que exibe as imagens dos jogadores.
     */
    private void montarPainelJogadores()
    {        
        this.imagemJogador1 = new JLabel();        
        this.imagemJogador1.setBounds(
                                        0,
                                        0,
                                        Client.getTela().getlPersonagem(),
                                        Client.getTela().getaPersonagem()
        );

        this.imagemJogador2 = new JLabel();
        this.imagemJogador2.setBounds(
                                        ( Client.getTela().getLargura()-Client.getTela().getlPersonagem() ),
                                        0,
                                        Client.getTela().getlPersonagem(),
                                        Client.getTela().getaPersonagem()
        );
        
        this.imagemJogador1.setIcon( null );
        this.imagemJogador2.setIcon( null );
        
        Client.getTelaJogo().add(this.imagemJogador1);
        Client.getTelaJogo().add(this.imagemJogador2);
    }
    
    /** 
     * Monta o painel que exibe os botões ao fim do jogo
     */
    private void montarPainelBotoes()
    {
        int wBotoes = 500;
        int hBotoes = 60;
        int xBotoes = (Client.getTela().getLargura() - wBotoes)/2;
        int yBotoes = 350;
        
        this.painelBotoes = new JLabel();
        this.painelBotoes.setBounds(xBotoes, yBotoes, wBotoes, hBotoes);
        this.painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        
        JButton menu = Componentes.criarJButton("SAIR");
        menu.setPreferredSize(new Dimension(150, hBotoes-10));
        menu.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        
        this.painelBotoes.add(menu);

        Client.getTelaJogo().add(this.painelBotoes);
        
    }

    /** 
     * Monta o painel que exibe o placar durante o jogo.
     */
    private void montarPainelPlacar()
    {
        int wPlacar = 350;
        int hPlacar = 40;
        int xPlacar = 20;
        int yPlacar = (Client.getTela().getAltura() - (xPlacar*2) - 50);
        
        this.painelPlacar = new JLabel();
        this.painelPlacar.setBounds(xPlacar, yPlacar, wPlacar, hPlacar);
        this.painelPlacar.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
        this.painelPlacar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.painelPlacar.setIcon(Client.getTela().getEfeitoIcon());
        
        this.tempo = Componentes.criarJLabel("02:00", SwingConstants.CENTER);
        this.tempo.setPreferredSize(new Dimension(70, hPlacar-10));
        
        this.miniatura1 = new JLabel();
        this.miniatura1.setPreferredSize(new Dimension(30, 30));
        
        this.placar1 = Componentes.criarJLabel("", SwingConstants.LEFT);
        this.placar1.setPreferredSize(new Dimension(40, hPlacar-10));
        
        this.miniatura2 = new JLabel();
        this.miniatura2.setPreferredSize(new Dimension(30, 30));
        
        this.placar2 = Componentes.criarJLabel("", SwingConstants.CENTER);
        this.placar2.setPreferredSize(new Dimension(40, hPlacar-10));
        
        this.painelPlacar.add(this.tempo);
        this.painelPlacar.add(this.miniatura1);
        this.painelPlacar.add(this.placar1);
        this.painelPlacar.add(this.miniatura2);
        this.painelPlacar.add(this.placar2);

        Client.getTelaJogo().add(this.painelPlacar, 0);
    }

    /** 
     * Monta o painel que exibe a imagem do vencedor.
     */
    private void montarPainelImagem()
    {        
        int xLogo = (Client.getTela().getLargura() - 200)/2;
        int yLogo = 100;
        
        this.painelImagem = new JLabel();   
        this.painelImagem.setBounds(xLogo, yLogo, 200, 200);
        
        Client.getTelaJogo().add(this.painelImagem);
        
    }
    
    /** 
     * Monta o painel que exibe a mensagem ao final do jogo.
     */
    private void montarPainelMensagem()
    {    
        int wMensagem = 500;
        int hMensagem = 100;
        int xMensagem = (Client.getTela().getLargura() - wMensagem)/2;
        int yMensagem = 25;
        
        this.painelLegenda = Componentes.criarJLabel("", SwingConstants.CENTER);
        this.painelLegenda.setBounds(xMensagem, yMensagem, wMensagem, hMensagem);
        
        Client.getTelaJogo().add(this.painelLegenda);
    }
    
    /** 
     * Exibe os componentes da tela, para iniciar a seleção do adversário
     */
    public void exibirTela()
    {   
        this.imagemJogador1.setVisible(true);
        this.imagemJogador2.setVisible(true);
        this.painelPlacar.setVisible(true);
        this.painelImagem.setVisible(false);
        this.painelLegenda.setVisible(false);
        this.painelBotoes.setVisible(false);
        this.alterarPontuacao("", "");
        
        this.painelImagem.setBackground(null);
        this.painelImagem.setOpaque(false);
        
        Client.getTela().getEfeito().setVisible(false);
        Client.getTela().addKeyListener(new JogoListener());
        Client.getTelaJogo().setVisible(true);
        
        Client.getOut().println("INI");
        
        JogoTempo j = new JogoTempo(60);
        Thread t = new Thread(j);
        t.start();
    }

    /** 
     * Ativa o jogo e recebe as mensagens do servidor, até que o jogo acabe ou haja uma desconexão.
     */ 
    public void run()
    {
        this.setJogoAtivo(true);
        
        try{
            String mensagem = "";
            while(this.isJogoAtivo())
            {
                mensagem = Client.getIn().readLine();
                if(! mensagem.equals("FIM"))
                {
                    this.processarJogada(mensagem);
                    System.out.println(mensagem);
                }
                else
                {
                    System.out.println("JOGO ENCERRADO!");
                    this.finalizar(false);
                }
            }
     } catch (SocketException se){
            System.out.println("DESCONECTADO!");
            this.finalizar(true);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /** 
     * Recebe a jogada do servidor e toma as ações necessárias, como reposicionar, atacar e marcar pontuação.
     * @param jogada String - Texto contendo a jogada recebida do servidor.
     */ 
    public void processarJogada(String jogada)
    {
        String posicoes[] = jogada.split(";");
        
        this.imagemJogador1.setBounds(
                                        Integer.parseInt(posicoes[1]),
                                        Integer.parseInt(posicoes[2]),
                                        this.imagemJogador1.getWidth(),
                                        this.imagemJogador1.getHeight()
        );

        this.imagemJogador2.setBounds(
                                        Integer.parseInt(posicoes[7]),
                                        Integer.parseInt(posicoes[8]),
                                        this.imagemJogador2.getWidth(),
                                        this.imagemJogador2.getHeight()
        );
        
        int ataque = 0;
        if(Integer.parseInt(posicoes[4]) == 1) {
            ataque = 1;
        } 
        else if(Integer.parseInt(posicoes[10]) == 1){
            ataque = 2;
        }

        exibirPersonagens(
                            Integer.parseInt(posicoes[0]),
                            Integer.parseInt(posicoes[3]),
                            Integer.parseInt(posicoes[6]),
                            Integer.parseInt(posicoes[9]),
                            ataque
        );
        
        this.jogador1 = Integer.parseInt(posicoes[0]);
        this.jogador2 = Integer.parseInt(posicoes[6]);
        
        this.alterarPontuacao( posicoes[5], posicoes[11] );
        
        this.pontos1 = Integer.parseInt(posicoes[5]);
        this.pontos2 = Integer.parseInt(posicoes[11]);
    }
    
    /** 
     * Exibe os dois jogadores
     * @param j1 int - Código do jogador 1
     * @param l1 int - Lado/posição do jogador 1
     * @param j2 int - Código do jogador 2
     * @param l2 int - Lado/posição do jogador 1
     * @param ataque int - Código do jogador que efetuou o ataque, se houve
     */
    private void exibirPersonagens(int j1, int l1, int j2, int l2, int ataque)
    {
        try {
            
            if(ataque == 1) {
                this.imagemJogador1.setIcon( Client.getJogadores().getFotoJogo(j1, 3 ) );
                this.imagemJogador2.setIcon( Client.getJogadores().getFotoJogo( j2, l2 ) );
                Thread.sleep(100);
            }
            else if(ataque == 2) {
                this.imagemJogador1.setIcon( Client.getJogadores().getFotoJogo( j1, l1 ) );
                this.imagemJogador2.setIcon( Client.getJogadores().getFotoJogo( j2, 3 ) );
                Thread.sleep(100);
            }
            
            this.imagemJogador1.setIcon( Client.getJogadores().getFotoJogo( j1, l1 ) );
            this.imagemJogador2.setIcon( Client.getJogadores().getFotoJogo( j2, l2 ) );
            
            this.miniatura1.setIcon( Client.getJogadores().getFotoPlacar(j1) );
            this.miniatura2.setIcon( Client.getJogadores().getFotoPlacar(j2) );
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
    /** 
     * Altera a pontuação no placar do jogo.
     * @param p1 String - Pontuação do jogador 1.
     * @param p2 String - Pontuação do jogador 2.
     */ 
    private void alterarPontuacao(String p1, String p2)
    {
        this.placar1.setText(p1);
        this.placar2.setText(p2);
    }
    
    /** 
     * Altera o tempo no placar do jogo.
     * @param t String - Tempo do cronômetro.
     */ 
    public void alterarTempo(String t)
    {
        this.tempo.setText(t);
    }
    
    /** 
     * Carrega a imagem do vencedor do jogo.
     * @param i int - Código do jogador
     */
    private void carregarImagem(int i)
    {
        this.painelImagem.setIcon(Client.getJogadores().getFotoSelecao(i));
    }
    
    /** 
     * Retorna se o jogo está ou não ativo
     * @return boolean
     */
    public boolean isJogoAtivo()
    {
        return jogoAtivo;
    }

    /** 
     * Ativa ou não o jogo
     * @param jogoAtivo boolean - Ativa ou desativa
     */
    public void setJogoAtivo(boolean jogoAtivo)
    {
        this.jogoAtivo = jogoAtivo;
    }

    /** 
     * Finaliza o jogo, definindo o vencedor, exibindo os componentes finais
     * e encerrando comunicação com o servidor.
     * @param desconectado boolean - Verdadeiro se houve desconexão
     */
    public void finalizar(boolean desconectado)
    {
        this.setJogoAtivo(false);
        
        if(desconectado)
        {
            this.painelImagem.setBackground(Color.white);
            this.painelImagem.setOpaque(true);
            this.painelLegenda.setText("O SERVIDOR SE DESCONECTOU!");
        }
        else
        {
            if( pontos1 > pontos2 ) {
                this.carregarImagem(jogador1);
                this.painelLegenda.setText("O VENCEDOR FOI...");
            } else if (pontos2 > pontos1) {
                this.carregarImagem(jogador2);
                this.painelLegenda.setText("O VENCEDOR FOI...");
            } else {
                this.painelImagem.setBackground(Color.white);
                this.painelImagem.setOpaque(true);
                this.painelLegenda.setText("JOGO EMPATADO!");
            }
        }

        this.imagemJogador1.setVisible(false);
        this.imagemJogador2.setVisible(false);
        this.painelPlacar.setVisible(false);
        this.painelImagem.setVisible(true);
        this.painelLegenda.setVisible(true);
        this.painelBotoes.setVisible(true);

        Client.getTela().getEfeito().setVisible(true);
        Client.getTela().addKeyListener(null);     
        Client.getOut().println("FIM");
    }

    /** 
     * Oculta a tela em detrimento de outra que será exibida.
     */
    public void ocultarTela()
    {
        Client.getTelaJogo().setVisible(false);
    }
    
}