package prog;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/** 
 * Carrega e fornece as infformações dos jogadores e personagens usados no jogo
 * @author Jonatha Cardoso
 */
public class Jogadores
{
     
    private String[] arquivos;
    private String[] nomes;
    
    private int qtdePersonagens;
    
    /** 
     * Construtor da classe, que carrega os jogadores.
     */
    public Jogadores()
    {      
        carregarJogadores();
    }

    /** 
     * Carrega o nome e a descrição dos jogadores.
     */
    private void carregarJogadores()
    {
        this.setQtdePersongens(7);
        
        this.arquivos = new String[this.getQtdePersongens()];
        this.nomes = new String[this.getQtdePersongens()];
        
        this.arquivos[0] = "chaves";
        this.arquivos[1] = "chiquinha";
        this.arquivos[2] = "clotilde";
        this.arquivos[3] = "florinda";
        this.arquivos[4] = "madruga";
        this.arquivos[5] = "nhonho";
        this.arquivos[6] = "quico";
        
        this.nomes[0] = "CHAVES";
        this.nomes[1] = "CHIQUINHA";
        this.nomes[2] = "DONA CLOTILDE";
        this.nomes[3] = "DONA FLORINDA";
        this.nomes[4] = "SEU MADRUGA";
        this.nomes[5] = "NHONHO";
        this.nomes[6] = "QUICO";

    }
    
    /** 
     * Obtém a imagem do personagem para seleção de jogador/adversário.
     * @param indice int - Código do jogador no array.
     * @return ImageIcon
     */
    public ImageIcon getFotoSelecao(int indice)
    {
        URL imagemArquivo = getClass().getResource( Client.getDocumentRoot() + this.arquivos[indice] + "0.png" );
            
        ImageIcon imagemIcn = new ImageIcon( imagemArquivo );
          
        return imagemIcn;
    }
    
    /** 
     * Obtém a imagem do personagem para a disputa entre os jogadores
     * @param indice int - Código do jogador no array.
     * @param posicao int - Posição do jogador, sendo 1 para direita, 2 para esquerda e 3 para ataque.
     * @return ImageIcon
     */
    public ImageIcon getFotoJogo(int indice, int posicao)
    {
        URL imagemArquivo = getClass().getResource( Client.getDocumentRoot() + this.arquivos[indice] + posicao + ".png" );
           
        ImageIcon imagemIcn = new ImageIcon( imagemArquivo );
        ImageIcon imagemRed = new ImageIcon( imagemIcn.getImage().getScaledInstance(
                                                                                        Client.getTela().getlPersonagem(),
                                                                                        Client.getTela().getaPersonagem(),
                                                                                        Image.SCALE_DEFAULT )
        );
          
        return imagemRed;
    }
    
    /** 
     * Obtém a imagem do personagem para exibir no placar 
     * @param indice int - Código do jogador no array.
     * @return ImageIcon
     */
    public ImageIcon getFotoPlacar(int indice)
    {
        URL imagemArquivo = getClass().getResource( Client.getDocumentRoot() + this.arquivos[indice] + "0.png" );
           
        ImageIcon imagemIcn = new ImageIcon( imagemArquivo );
        ImageIcon imagemRed = new ImageIcon( imagemIcn.getImage().getScaledInstance(
                                                                                        30,
                                                                                        30,
                                                                                        Image.SCALE_DEFAULT )
        );
          
        return imagemRed;
    }
    
    /**
     * Retorna o nome do jogador.
     * @param indice int - Código do jogador.
     * @return String
     */
    public String getNome(int indice)
    {
        return this.nomes[indice];
    }

    /**
     * Retorna a quantidade de personagens.
     * @return int
     */
    public int getQtdePersongens()
    {
        return this.qtdePersonagens;
    }

    /**
     * Define a quantidade de personagens.
     * @param qtdePersonagens int - Quantidade de personagens
     */
    public void setQtdePersongens(int qtdePersonagens)
    {
        this.qtdePersonagens = qtdePersonagens;
    }
    
}