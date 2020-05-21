
package prog;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/** 
 * Define as ações de teclas pressionadas na tela do jogo.
 * @author Jonatha Cardoso
 */
public class JogoListener implements KeyListener
{    
    @Override
    public void keyTyped(KeyEvent e)
    {
        //
    }

    /**
     * Identifica uma tecla pressionada e envia o respectivo código para o servidor.
     * @param e KeyEvent - Evento
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Client.getOut().println("DIR");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            Client.getOut().println("ESQ");
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            Client.getOut().println("CIM");
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            Client.getOut().println("BAI");
        }
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            Client.getOut().println("ATA");
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        //
    }                         

}