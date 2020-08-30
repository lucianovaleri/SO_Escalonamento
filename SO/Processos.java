package SO;

import java.util.List;

/**
 *
 * @author luciano
 */
public class Processos{
    
    int id, tempoEntrada, prioridade; 
    int tempoFila = 0; 
    
    int numExec = 0; 
    
    int qntExecMax = 3; 

    
    public Processos(int id, int tempoEntrada, int prioridade) {
        this.id = id;
        this.tempoEntrada = tempoEntrada;
        this.prioridade = prioridade;
    }

    Processos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
           
    public void imprime(List<Processos> processos) {
        processos.forEach((processo) -> {
            System.out.println("Processos{" +   "Número do Processo = " + processo.id + 
                    "\nPrioridade do Processo = " + processo.prioridade + 
                    "\nTempo na fila = " + processo.tempoFila +
                    "\nVezes executado = " + processo.numExec );

        });
    }
}