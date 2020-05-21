
package prog;

import java.util.Random;

/** 
 * Sorteia um adversário para o jogador.
 * @author Jonatha Cardoso
 */
public class AdversarioSorteio implements Runnable
{

    private int jogadorAtual;

    /** 
     * Construtor da classe que recebe o código do jogador que aguarda o adversário sorteado
     * @param jogador int - Código do jogador
     */
    public AdversarioSorteio(int jogador)
    {
        this.jogadorAtual = jogador;
    }
    
    /** 
     * Sorteia o adversário do jogador, que obrigatoriamente será diferente dele. 
     * Serão sorteados 60 jogadores, e o último é o escolhido.
     */
    @Override
    public void run()
    {
        int indice;
        
        try{
            for(int x = 1; x <= 60; x++)
            {
                do
                {
                    Random gerador = new Random();
                    indice = gerador.nextInt(Client.getJogadores().getQtdePersongens());
                } while (indice == this.jogadorAtual);
                
                if(x != 60)
                {
                    Client.getAdversario().carregarImagem(indice, false);
                }
                else
                {
                    Client.getAdversario().carregarImagem(indice, true);
                    Client.setJogador2(indice);
                }
                
                Thread.sleep(50);

            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}