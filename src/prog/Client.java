
package prog;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JLabel;

/** 
 * Centraliza todos os componentes utilizados pelo jogo.
 * @author Jonatha Cardoso
 */
public class Client
{
    
    private static String documentRoot = "../img/";
    
    private static Tela tela = new Tela();
    
    private static Jogadores jogadores = new Jogadores();
    
    private static JLabel telaInicio = new JLabel();
    private static Inicio inicio = new Inicio();
    
    private static JLabel telaSelecao = new JLabel();
    private static Selecao selecao = new Selecao();
    
    private static JLabel telaAdversario = new JLabel();
    private static Adversario adversario = new Adversario();
    
    private static JLabel telaJogo = new JLabel();
    private static Jogo jogo = new Jogo();
    
    private static Socket socket;
    private static BufferedReader in;
    private static PrintWriter out;

    private static int jogador1;
    private static int jogador2;
    private static int qtdeJogadores;
    
    private static Thread aguardandoAdversario;

    /** 
     * Construtor da classe, que monta a tela.
     */
    public static void main(String[] args)
    {
        new Client();
    }
    
    /** 
     * Construtor da classe, que adiciona as telas ao frame, exibe a tela principal e carrega a tela de início.
     */
    public Client()
    {
        adicionarTelas();
        
        this.tela.exibirTela();
        
        this.inicio.exibirTela();
    }
    
    /** 
     * Adiciona as telas secundárias à tela principal.
     */
    public void adicionarTelas()
    {
        getTela().add(getTelaInicio());
        getTelaInicio().setBounds(0, 0, getTela().getLargura(), getTela().getAltura());
        getInicio().ocultarTela();        
        
        getTela().add(getTelaSelecao());
        getTelaSelecao().setBounds(0, 0, getTela().getLargura(), getTela().getAltura());        
        getSelecao().ocultarTela();
        
        getTela().add(getTelaAdversario());
        getTelaAdversario().setBounds(0, 0, getTela().getLargura(), getTela().getAltura());        
        getAdversario().ocultarTela();
        
        getTela().add(getTelaJogo());
        getTelaJogo().setBounds(0, 0, getTela().getLargura(), getTela().getAltura());        
        getJogo().ocultarTela();
    }
    
    /** 
     * Singleton que obtém o socket de conexão ao servidor
     * @return Socket
     */
    public static Socket getSocket()
    {
        if(socket == null) {
            try {
                socket = new Socket("localhost", 8800);
            } catch(Exception e) {
                e.printStackTrace();
            }            
        }
            
        return socket;
    }

    /** 
     * Singleton que obtém a entrada de mensagens do servidor.
     * @return BufferedReader
     */
    public static BufferedReader getIn()
    {
        if(in == null) {
            try {
                in = new BufferedReader(new InputStreamReader(Client.getSocket().getInputStream()));
            } catch(Exception e) {
                e.printStackTrace();
            }            
        }
        
        return in;
    }

    /** 
     * Singleton que obtém a saída de mensagens ao servidor
     * @return PrintWriter
     */
    public static PrintWriter getOut()
    {
        if(out == null) {
            try {
                out = new PrintWriter(Client.getSocket().getOutputStream(), true);
            } catch(Exception e) {
                e.printStackTrace();
            }            
        }

        return out;
    }
    
    /** 
     * Retorna a instância da classe de jogadores
     * @return Jogadores
     */
    public static Jogadores getJogadores()
    {
        return jogadores;
    }

    /** 
     * Retorna a instância da tela principal
     * @return Jogadores
     */
    public static Tela getTela()
    {
        return tela;
    }

    /** 
     * Retorna a pasta raiz onde estão os arquivos
     * @return String
     */
    public static String getDocumentRoot()
    {
        return documentRoot;
    }
    
    /** 
     * Retorna a instância da classe de início
     * @return Inicio
     */
    public static Inicio getInicio()
    {
        return inicio;
    }
    
    /** 
     * Retorna a instância da tela de início
     * @return JLabel
     */
    public static JLabel getTelaInicio()
    {
        return telaInicio;
    }
    
    /** 
     * Retorna a instância da classe de seleção
     * @return Selecao
     */
    public static Selecao getSelecao()
    {
        return selecao;
    }
    
    /** 
     * Retorna a instância da tela de seleção
     * @return JLabel
     */
    public static JLabel getTelaSelecao()
    {
        return telaSelecao;
    }
    
    /** 
     * Retorna a instância da classe de adversário
     * @return Adversario
     */
    public static Adversario getAdversario()
    {
        return adversario;
    }
    
    /** 
     * Retorna a instância da tela de adversário
     * @return JLabel
     */
    public static JLabel getTelaAdversario()
    {
        return telaAdversario;
    }
    
    /** 
     * Retorna a instância da classe de jogo
     * @return Jogo
     */
    public static Jogo getJogo()
    {
        return jogo;
    }

    /** 
     * Retorna a instância da tela do jogo
     * @return JLabel
     */
    public static JLabel getTelaJogo()
    {
        return telaJogo;
    }

    public static Thread getAguardandoAdversario()
    {
        return aguardandoAdversario;
    }

    public static void setAguardandoAdversario(Thread aAguardandoAdversario)
    {
        aguardandoAdversario = aAguardandoAdversario;
    }
    
    /** 
     * Retorna o código do jogador 1
     * @return int
     */
    public static int getJogador1()
    {
        return jogador1;
    }

    /** 
     * Define o código do jogador 1
     * @param aJogador1 int - Código
     */
    public static void setJogador1(int aJogador1)
    {
        jogador1 = aJogador1;
    }

    /** 
     * Retorna o código do jogador 2
     * @return int
     */
    public static int getJogador2()
    {
        return jogador2;
    }

    /** 
     * Define o código do jogador 1
     * @param aJogador2 int - Código
     */
    public static void setJogador2(int aJogador2)
    {
        jogador2 = aJogador2;
    }
    
    /** 
     * Retorna se o jogo será com 1 ou 2 jogadores
     * @return int
     */
    public static int getQtdeJogadores()
    {
        return qtdeJogadores;
    }

    /** 
     * Define a quantidade de jogadores
     * @param aQtdeJogadores int - Quantidade, sendo 1 ou 2
     */
    public static void setQtdeJogadores(int aQtdeJogadores)
    {
        qtdeJogadores = aQtdeJogadores;
    }
    
}