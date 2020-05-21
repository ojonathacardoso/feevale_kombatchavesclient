
package prog;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/** 
 * Carrega e controla a tela de escolha do adversário.
 * @author Jonatha Cardoso
 */
public class Adversario implements Runnable
{
    
    private JLabel painelMensagem;
    private JLabel painelImagem;
    private JLabel painelLegenda;
    private JLabel painelBotoes;
    private JLabel painelLogo;
    
    private JButton jogar1;
    private JButton jogar2;
    
    /** 
     * Construtor da classe, que monta a tela.
     */
    public Adversario()
    {        
        this.montarPainelMensagem();
        this.montarPainelImagem();
        this.montarPainelLegenda();
        this.montarPainelBotoes(); 
        this.montarPainelLogo();
    }
    
    /** 
     * Monta o painel que exibe as mensagens sobre a escolha do adversário.
     */
    private void montarPainelMensagem()
    {    
        int wMensagem = 500;
        int hMensagem = 100;
        int xMensagem = (Client.getTela().getLargura() - wMensagem)/2;
        int yMensagem = 25;
        
        this.painelMensagem = Componentes.criarJLabel("", SwingConstants.CENTER);
        this.painelMensagem.setBounds(xMensagem, yMensagem, wMensagem, hMensagem);
        
        Client.getTelaAdversario().add(this.painelMensagem);
    }
    
    /** 
     * Monta o painel aonde aparece a imagem do adversário.
     */
    private void montarPainelImagem()
    {        
        int xLogo = (Client.getTela().getLargura() - 200)/2;
        int yLogo = 100;
        
        this.painelImagem = new JLabel();   
        this.painelImagem.setBounds(xLogo, yLogo, 200, 200);
        
        Client.getTelaAdversario().add(this.painelImagem);
        
    }
    
    /** 
     * Monta o painel que exibe o nome do personagem escolhido.
     */
    private void montarPainelLegenda()
    {    
        int wMensagem = 300;
        int hMensagem = 100;
        int xMensagem = (Client.getTela().getLargura() - wMensagem)/2;
        int yMensagem = 275;
        
        this.painelLegenda = Componentes.criarJLabel("", SwingConstants.CENTER);
        this.painelLegenda.setBounds(xMensagem, yMensagem, wMensagem, hMensagem);
        
        Client.getTelaAdversario().add(this.painelLegenda);
    }
    
    /** 
     * Monta o painel que exibe os botões de ação após seleção do adversário.
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
        
        this.jogar1 = Componentes.criarJButton("JOGAR");
        this.jogar1.setPreferredSize(new Dimension(150, hBotoes-10));
        this.jogar1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Client.getAdversario().ocultarTela();
                
                try{
                    enviarComunicacaoInicial();
                } catch(Exception ex){
                    ex.printStackTrace();
                }
                
                Client.getJogo().exibirTela();
                
                Thread thread = new Thread(Client.getJogo());
                thread.start();
            }
        });
        
        this.jogar2 = Componentes.criarJButton("JOGAR");
        this.jogar2.setPreferredSize(new Dimension(150, hBotoes-10));
        this.jogar2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Thread thread = new Thread(Client.getAdversario());
                thread.start();
            }
        });
        
        this.painelBotoes.add(this.jogar1);
        this.painelBotoes.add(this.jogar2);

        this.jogar1.setVisible(false);
        this.jogar2.setVisible(false);

        Client.getTelaAdversario().add(this.painelBotoes);
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
     * Exibe os componentes da tela, para iniciar a seleção do adversário
     */
    public void exibirTela()
    {
        Client.getTelaAdversario().setVisible(true);
        
        this.painelBotoes.setVisible(false);
        this.painelImagem.setVisible(false);
        this.painelLegenda.setVisible(false);
        
        this.exibirBotaoJogar1(false);
        this.exibirBotaoJogar2(false);

        escolherOpcaoAdversario();
    }
    
    /** 
     * Direciona para os métodos respectivos, se o jogo será com 1 ou 2 jogadores.
     */
    public void escolherOpcaoAdversario()
    {
        if(Client.getQtdeJogadores() == 1)
        {
            sortearAdversario();
        }
        else if(Client.getQtdeJogadores() == 2)
        {
            aguardarAdversario();
        }
    }
    
    /** 
     * Sorteia o adversário, quando o jogo for com 1 jogador.
     */
    public void sortearAdversario()
    {
        this.painelBotoes.setVisible(false);
        this.painelImagem.setVisible(true);
        this.painelLegenda.setVisible(false);
        
        this.exibirBotaoJogar1(false);
        this.exibirBotaoJogar2(false);
        
        this.painelMensagem.setText("O SEU ADVERSÁRIO SERÁ...");
        
        AdversarioSorteio s = new AdversarioSorteio(Client.getJogador1());
        Thread t = new Thread(s);
        
        t.start();
    }
    
    /** 
     * Aguarda a informação do adversário pelo servidor, quando o jogo for com 2 jogadores.
     */
    public void aguardarAdversario()
    {
        this.painelBotoes.setVisible(true);
        this.painelImagem.setVisible(false);
        this.painelLegenda.setVisible(false);
        
        this.exibirBotaoJogar1(false);
        this.exibirBotaoJogar2(false);
        
        this.painelMensagem.setText("AGUARDANDO O SEU ADVERSÁRIO!");
        
        this.enviarComunicacaoInicial();
        
        AdversarioConexao c = new AdversarioConexao();
        Client.setAguardandoAdversario(new Thread(c));
        Client.getAguardandoAdversario().start();
    }
    
    /** 
     * Carrega a imagem do adversário escolhido.
     * @param indice int - Número do jogador, conforme array da classe Jogadores.
     * @param ultimo boolean - Ativa os painéis e botões para início do jogo, após sorteio do adversário ou 
     * informação do servidor.
     * @see Jogadores
     */
    public void carregarImagem(int indice, boolean ultimo)
    {
        this.painelImagem.setIcon(Client.getJogadores().getFotoSelecao(indice));
        
        if(ultimo)
        {
            this.painelMensagem.setText("O SEU ADVERSÁRIO SERÁ...");
            this.painelLegenda.setText(Client.getJogadores().getNome(indice));
            
            this.painelLegenda.setVisible(true);
            this.painelImagem.setVisible(true);
            this.painelBotoes.setVisible(true);
            
            if(Client.getQtdeJogadores() == 1)
            {
                this.exibirBotaoJogar1(true);
                this.exibirBotaoJogar2(false);
            }
            else if(Client.getQtdeJogadores() == 2)
            {
                this.exibirBotaoJogar1(false);
                this.exibirBotaoJogar2(true);
            }
        }
    }
    
    /** 
     * Exibe o botão de jogar quando o jogo será com apenas 1 jogador
     * @param exibir boolean - Exibe ou não o botão
     */
    public void exibirBotaoJogar1(Boolean exibir)
    {
        this.jogar1.setVisible(exibir);
    }
    
    /** 
     * Exibe o botão de jogar quando o jogo será com 2 jogadores
     * @param exibir boolean - Exibe ou não o botão
     */
    public void exibirBotaoJogar2(Boolean exibir)
    {
        this.jogar2.setVisible(exibir);
    }
    
    /** 
     * Aguarda o servidor informar que o adversário está pronto para iniciar o jogo
     */ 
    @Override
    public void run()
    {
        try {
            this.aguardarJogo();
            Client.getOut().println("OK");
            
            String pronto;
            do {
                pronto = Client.getIn().readLine();
                if(pronto == null) pronto = "FIM";
            } while( ! ( pronto.equals("OK") || pronto.equals("FIM") ) );
            
            if(pronto.equals("FIM"))
            {
                System.exit(1);
            }
            else
            {
                Thread thread = new Thread(Client.getJogo());
                thread.start();

                Client.getAdversario().ocultarTela();
                Client.getJogo().exibirTela();
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /** 
     * Oculta os painéis até que o servidor autorize o início do jogo.
     */  
    public void aguardarJogo()
    {
        this.painelBotoes.setVisible(false);
        this.painelImagem.setVisible(false);
        this.painelLegenda.setVisible(false);

        this.exibirBotaoJogar1(false);
        this.exibirBotaoJogar2(false);

        this.painelMensagem.setText("O JOGO JÁ VAI COMEÇAR... AGUARDE!");
    }
    
    /** 
     * Envia ao servidor um protocolo de abertura, contendo informações de jogadores e tela.
     */
    public void enviarComunicacaoInicial()
    {        
        StringBuilder sb = new StringBuilder();

        sb.append(Client.getQtdeJogadores());
        sb.append(";");
        sb.append(Client.getJogador1());
        sb.append(";");
        sb.append(Client.getJogador2());
        sb.append(";");
        sb.append(Client.getTela().getAltura());
        sb.append(";");
        sb.append(Client.getTela().getLargura());
        sb.append(";");
        sb.append(Client.getTela().getaPersonagem());
        sb.append(";");
        sb.append(Client.getTela().getlPersonagem());

        Client.getOut().println(sb);
    }
    
    /** 
     * Oculta a tela em detrimento de outra que será exibida.
     */
    public void ocultarTela()
    {
        Client.getTelaAdversario().setVisible(false);
    }
    
}