
package prog;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/** 
 * Carrega e controla a tela de seleção do jogador.
 * @author Jonatha Cardoso
 */
public class Selecao
{
    
    private JLabel painelMensagem;
    private JLabel painelImagem;
    private JLabel painelLegenda;
    private JLabel painelBotoes;
    private JLabel painelLogo;
    
    private int jogadorAtual;
    
    /** 
     * Construtor da classe, que monta a tela.
     */
    public Selecao()
    {        
        this.montarPainelMensagem();
        this.montarPainelImagem();
        this.montarPainelLegenda();
        this.montarPainelBotoes(); 
        this.montarPainelLogo();
    }
    
    /** 
     * Monta o painel que exibe as mensagens sobre a escolha do jogador.
     */
    private void montarPainelMensagem()
    {    
        int wMensagem = 500;
        int hMensagem = 100;
        int xMensagem = (Client.getTela().getLargura() - wMensagem)/2;
        int yMensagem = 25;
        
        this.painelMensagem = Componentes.criarJLabel("SELECIONE O SEU JOGADOR:", SwingConstants.CENTER);
        this.painelMensagem.setBounds(xMensagem, yMensagem, wMensagem, hMensagem);
        
        Client.getTelaSelecao().add(this.painelMensagem);
    }
    
    /** 
     * Monta o painel aonde aparece a imagem do jogador escolhido.
     */
    private void montarPainelImagem()
    {        
        int xLogo = (Client.getTela().getLargura() - 200)/2;
        int yLogo = 100;
        
        this.painelImagem = new JLabel();   
        this.painelImagem.setBounds(xLogo, yLogo, 200, 200);
        
        Client.getTelaSelecao().add(this.painelImagem);
        
    }
    
    /** 
     * Monta o painel que exibe o nome do jogador escolhido.
     */
    private void montarPainelLegenda()
    {    
        int wMensagem = 300;
        int hMensagem = 100;
        int xMensagem = (Client.getTela().getLargura() - wMensagem)/2;
        int yMensagem = 275;
        
        this.painelLegenda = Componentes.criarJLabel("NOME", SwingConstants.CENTER);
        this.painelLegenda.setBounds(xMensagem, yMensagem, wMensagem, hMensagem);
        
        Client.getTelaSelecao().add(this.painelLegenda);
    }
    
    /** 
     * Monta o painel que exibe os botões de ação após seleção do jogador.
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
        
        JButton anterior = Componentes.criarJButton("<");
        anterior.setPreferredSize(new Dimension(50, hBotoes-10));
        anterior.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(jogadorAtual == 0)
                    carregarImagem(Client.getJogadores().getQtdePersongens() - 1);
                else
                    carregarImagem(jogadorAtual - 1);
            }
        });
        
        JButton proximo = Componentes.criarJButton(">");
        proximo.setPreferredSize(new Dimension(50, hBotoes-10));
        proximo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(jogadorAtual == Client.getJogadores().getQtdePersongens() - 1)
                    carregarImagem(0);
                else
                    carregarImagem(jogadorAtual + 1);
            }
        });
        
        JButton selecionar = Componentes.criarJButton("SELECIONAR");
        selecionar.setPreferredSize(new Dimension(150, hBotoes-10));
        selecionar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Client.setJogador1(jogadorAtual);
                Client.getSelecao().ocultarTela();
                Client.getAdversario().exibirTela();
            }
        });
        
        JButton menu = Componentes.criarJButton("MENU");
        menu.setPreferredSize(new Dimension(150, hBotoes-10));
        menu.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Client.getSelecao().ocultarTela();
                Client.getInicio().exibirTela();                
            }
        });
        
        this.painelBotoes.add(menu);
        this.painelBotoes.add(anterior);
        this.painelBotoes.add(proximo);
        this.painelBotoes.add(selecionar);

        Client.getTelaSelecao().add(this.painelBotoes);
    }
    
    /** 
     * Monta o painel que exibe o logo do jogo.
     */
    private void montarPainelLogo()
    {        
        URL logoArquivo = getClass().getResource(Client.getDocumentRoot()+"logo.png");      
        ImageIcon logoIcon = new ImageIcon(logoArquivo);
        ImageIcon logoRed = new ImageIcon( logoIcon.getImage().getScaledInstance(
                                                                                    logoIcon.getIconWidth()/2,
                                                                                    logoIcon.getIconHeight()/2,
                                                                                    Image.SCALE_DEFAULT ) );
        
        int xLogo = (Client.getTela().getLargura() - logoRed.getIconWidth())/2;
        int yLogo = 450;
        
        this.painelLogo = new JLabel(logoRed);     
        this.painelLogo.setBounds(xLogo, yLogo, logoRed.getIconWidth(), logoRed.getIconHeight());
        
        Client.getTelaSelecao().add(this.painelLogo);
        
    }
    
    /** 
     * Exibe os componentes da tela, para iniciar a seleção do jogador
     */
    public void exibirTela()
    {
        carregarImagem(-1);
        
        Client.getTelaSelecao().setVisible(true);
    }
    
    /** 
     * Altera a mensagem exibida
     */
    public void alterarMensagem(String mensagem)
    {
        this.painelMensagem.setText(mensagem);
    }
    
    /** 
     * Carrega a imagem do jogador escolhido.
     * @param i int - Número do jogador, conforme array da classe Jogadores.
     * @see Jogadores
     */
    private void carregarImagem(int i)
    {
        int indice = i;
        
        if(i == -1)
        {
            Random gerador = new Random();
            indice = gerador.nextInt(Client.getJogadores().getQtdePersongens());
        }
        
        this.jogadorAtual = indice;
        
        this.painelImagem.setIcon(Client.getJogadores().getFotoSelecao(indice));
        this.painelLegenda.setText(Client.getJogadores().getNome(indice));
    }
    
    /** 
     * Oculta a tela em detrimento de outra que será exibida.
     */
    public void ocultarTela()
    {
        Client.getTelaSelecao().setVisible(false);
    }
    
}
