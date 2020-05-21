
package prog;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

/** 
 * Componentes swing personalizados para uso no jogo.
 */
public class Componentes
{

    /** 
     * Cria um JButton personalizado
     * @param texto String - Texto exibido no botão.
     */
    public static JButton criarJButton(String texto)
    {
        JButton botao = new JButton();
       
        botao.setText(texto);
        botao.setBackground(Color.LIGHT_GRAY);
        botao.setForeground(Color.LIGHT_GRAY);        
        botao.setFocusPainted(false);
        botao.setOpaque(false);
        botao.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        
        return botao;
    }
    
    /** 
     * Cria um JLabel personalizado
     * @param texto String - Texto exibido no botão.
     * @param alinhamento int - Define o alinhamento do texto, baseado em SwingConstants
     */
    public static JLabel criarJLabel(String texto, int alinhamento)
    {
        JLabel label = new JLabel();
       
        label.setText(texto);
        label.setBackground(Color.LIGHT_GRAY);
        label.setForeground(Color.LIGHT_GRAY);
        label.setOpaque(false);
        label.setBorder(null);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setHorizontalAlignment(alinhamento);
        
        return label;
    }
    
}