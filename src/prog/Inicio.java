
package prog;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Inicio
{

    private JLabel painelLogo;
    private JLabel painelBotoes; 
    
    /** 
     * Construtor da classe, que monta a tela.
     */
    public Inicio()
    {
        this.montarPainelLogo();
        this.montarPainelBotoes();
    }
    
    /** 
     * Monta o painel que exibe o logo do jogo.
     */
    private void montarPainelLogo()
    {        
        URL logoArquivo = getClass().getResource(Client.getDocumentRoot()+"logo.png");        
        ImageIcon logoIcon = new ImageIcon(logoArquivo);
        
        this.painelLogo = new JLabel(logoIcon);
        
        int xLogo = (Client.getTela().getLargura() - logoIcon.getIconWidth())/2;
        int yLogo = 100;        
        this.painelLogo.setBounds(xLogo, yLogo, logoIcon.getIconWidth(), logoIcon.getIconHeight());
        
        Client.getTelaInicio().add(this.painelLogo);
        
    }
    
    /** 
     * Monta o painel que exibe os botões de opção entre 1 e 2 jogadores.
     */
    private void montarPainelBotoes()
    {    
        int wBotoes = 400;
        int hBotoes = 60;
        int xBotoes = (Client.getTela().getLargura() - wBotoes)/2;
        int yBotoes = 325;
        
        this.painelBotoes = new JLabel();
        this.painelBotoes.setBounds(xBotoes, yBotoes, wBotoes, hBotoes);
        this.painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

        JButton jogador1 = Componentes.criarJButton("1 JOGADOR");
        jogador1.setPreferredSize(new Dimension(160, hBotoes-10));
        jogador1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Client.setQtdeJogadores(1);
                Client.getInicio().ocultarTela();
                Client.getSelecao().exibirTela();                
            }
        });
          
        JButton jogador2 = Componentes.criarJButton("2 JOGADORES");
        jogador2.setPreferredSize(new Dimension(160, hBotoes-10));
        jogador2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Client.setQtdeJogadores(2);
                Client.getInicio().ocultarTela();
                Client.getSelecao().exibirTela();
            }
        });
        
        this.painelBotoes.add(jogador1);
        this.painelBotoes.add(jogador2);
        
        Client.getTelaInicio().add(this.painelBotoes);
    }
    
    /** 
     * Exibe os componentes da tela, para iniciar a seleção do jogo
     */
    public void exibirTela()
    {
        Client.getTelaInicio().setVisible(true);
    }
    
    /** 
     * Oculta a tela em detrimento de outra que será exibida.
     */
    public void ocultarTela()
    {
        Client.getTelaInicio().setVisible(false);
    }
    
}