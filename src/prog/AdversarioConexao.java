
package prog;

/** 
 * Conecta-se ao servidor para verificar se os jogadores são diferentes,
 * sendo que, em caso positivo, faz a alteração.
 * @author Jonatha Cardoso
 */
public class AdversarioConexao implements Runnable
{
    /** 
     * Aguarda a confirmação do servidor de que os jogadores são diferentes. 
     * Caso não sejam, o jogador deverá selecionar outro.
     */
    @Override
    public void run()
    {
        try{
            String mensagem = Client.getIn().readLine();
            if(mensagem != null)
            {
                int adversario = Integer.parseInt(mensagem); 
                if(adversario == -1)
                {
                    Client.getAdversario().ocultarTela();
                    Client.getSelecao().alterarMensagem("ESCOLHA OUTRO JOGADOR!");
                    Client.getSelecao().exibirTela();
                }
                else
                {
                    Client.getAdversario().carregarImagem(adversario, true);
                    Client.setJogador2(adversario);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}