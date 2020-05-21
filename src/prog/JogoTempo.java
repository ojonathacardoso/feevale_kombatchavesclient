
package prog;

/** 
 * Controla o tempo do jogo
 * @author Jonatha Cardoso
 */
public class JogoTempo implements Runnable
{
    
    private int atualSegundo;
    private int atualMinuto;
    private int tempo;
    
    /** 
     * Construtor da classe, que define o tempo do cronômetro
     * @param tempo int - Tempo em segundos
     */
    public JogoTempo(int tempo)
    {
        this.tempo = tempo;
        this.atualSegundo = 60;
        this.atualMinuto = (tempo/60);
    }
    
    /** 
     * Cronômetro, que vai percorrer decrescentemente, atualizando o placar do jogo.
     * A cada minuto ele diminui o minuto e zera os segundos.
     */
    @Override
    public void run()
    {
        try{            
            for(int x = this.tempo; x >= 0; x--)
            {
                if(atualSegundo == 60) {
                    atualSegundo = 0;
                } else if (atualSegundo == -1) {
                    atualSegundo = 59;
                    atualMinuto--;
                }
                
                String min = atualMinuto <= 9? "0"+atualMinuto:atualMinuto+"";
                String seg = atualSegundo <= 9? "0"+atualSegundo:atualSegundo+"";
                
                Client.getJogo().alterarTempo(min+":"+seg);  
                
                Thread.sleep(1000);
                
                atualSegundo--;
            }
            
            Client.getJogo().setJogoAtivo(false);
            Client.getJogo().finalizar(false);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}