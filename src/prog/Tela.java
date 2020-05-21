
package prog;

import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/** 
 * Monta a tela principal utilizada no jogo
 * @author Jonatha Cardoso
 */
public class Tela extends JFrame
{
    
    private ArrayList<JLabel> backgrounds = new ArrayList<JLabel>();
    private final int qtdeImagens = 4;
    
    private int largura = 800;
    private int altura = 600;
    
    private int lPersonagem = 274;
    private int aPersonagem = 436;
    
    private ImageIcon efeitoIcon;
    private JLabel efeito;
    
    /** 
     * Construtor da classe, que monta a tela principal.
     */
    public Tela()
    {      
        setLayout(null);
        setBounds(20, 20, largura, altura);
        setTitle("Chaves");
        setResizable(false);
        setFocusable(true);
        
        criarBackgrounds();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                try{
                    if(Client.getOut() != null){
                        Client.getOut().println("FIM");
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
    }

    /** 
     * Cria os backgrounds usados na tela
     */
    private void criarBackgrounds()
    {
        URL imagemArquivo;
        
        for(int x = 0; x < qtdeImagens; x++)
        {
            imagemArquivo = getClass().getResource(Client.getDocumentRoot()+"back"+x+".png");
            
            ImageIcon imagemIcn = new ImageIcon( imagemArquivo );
            ImageIcon imagemRed = new ImageIcon( imagemIcn.getImage().getScaledInstance(getLargura(), getAltura(), Image.SCALE_DEFAULT ) );
            
            if(x == 0) {
                this.efeitoIcon = imagemIcn;
            }
            
            JLabel imagemLbl = new JLabel( imagemRed );
            
            imagemLbl.setBounds(0, 0, getLargura(), getAltura());
            this.backgrounds.add(x, imagemLbl);
            
            efeito = backgrounds.get(0);
        }
    }
    
    /** 
     * Construtor da classe, que monta a tela principal.
     */
    public void exibirTela()
    {
        this.efeito.setVisible(true);
        this.exibirBackgrounds();
        setVisible(true);
    }
    
    /** 
     * Exibe o background do efeito e um sorteado.
     */
    public void exibirBackgrounds()
    {
        Random gerador = new Random();
        int indice = gerador.nextInt(3)+1; // Gera de 0 a x-1, e com o +1 fica de 1 a x
        
        add(this.backgrounds.get(0));
        add(this.backgrounds.get(indice));
    }
       
    /** 
     * Retorna a largura da tela.
     * @return int
     */
    public int getLargura()
    {
        return this.largura;
    }

    /** 
     * Retorna a altura da tela.
     * @return int
     */
    public int getAltura()
    {
        return this.altura;
    }
    
    /** 
     * Retorna a largura do personagem.
     * @return int
     */
    public int getlPersonagem()
    {
        return this.lPersonagem;
    }

    /** 
     * Retorna a altura do personagem.
     * @return int
     */
    public int getaPersonagem()
    {
        return this.aPersonagem;
    }

    /** 
     * Retorna o background do efeito.
     * @return JLabel
     */
    public JLabel getEfeito()
    {
        return this.efeito;
    }
    
    /** 
     * Retorna a imagem do efeito.
     * @return ImageIcon
     */
    public ImageIcon getEfeitoIcon()
    {
        return this.efeitoIcon;
    }
    
}
