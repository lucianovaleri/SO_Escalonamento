package SO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luciano
 */
public class EscalonamentoRoundRobin {
    //time sharing 30ms    
    static final int quantum = 30;

    public List<Processos> escRR(List<Processos> processos) throws InterruptedException{
        
    List<Processos> listaProntos = this.listaProntos(processos);
        
        // While roda sempre que lista prontos não esteja vazio
        while (!listaProntos.isEmpty()){
            Processos proc = listaProntos.remove(0);
            Thread.sleep(1000);            
            System.out.println("\n\n");
            for (Processos lista: listaProntos){
                lista.tempoFila = quantum + lista.tempoFila;
                //System.out.println("!!!!!!!");
            }
            proc.numExec++;

            proc.imprime(processos);
            
            if (proc.numExec <= 3){
                listaProntos.add(proc);
                //System.out.println("Chegou a 3");
            }

        }
        return processos; 
    }
    

    public List<Processos> listaProntos(List<Processos> processos){

        List<Processos> listaProntos = new ArrayList(); 

        while (listaProntos.size() != processos.size())  {  
            int max = Integer.MIN_VALUE;
            int pos = 0;
            for (int i = 0; i < processos.size(); i++){
                if (!listaProntos.contains(processos.get(i))){
                    if (processos.get(i).tempoEntrada > max ){
                        max = processos.get(i).tempoEntrada;
                        pos = i;
                    }
                }
            }
            listaProntos.add(processos.get(pos));
        }
        return listaProntos;
}
}