package SO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luciano
 */
public class EscalonamentoPDinamica {
        //time sharing 30ms    
    public static final int quantum = 30;
        //níveis de prioridade
    public static final int pAlta = 6;
    public static final int pMedia = 5;
    public static final int pBaixa = 3;
    
    public List<Processos> escPRIOd(List<Processos> processos) throws InterruptedException{
        List<Processos> filaAlta = this.filaAlta(processos); 
        List<Processos> filaMedia = this.filaMedia(processos); 
        List<Processos> filaBaixa = this.filaBaixa(processos); 
        
        Processos p = new Processos(); 
        
        while (!filaAlta.isEmpty() || !filaMedia.isEmpty() || !filaBaixa.isEmpty()){
            
            int qntQuantum = 1; 
            
            while (qntQuantum <= 4 && !filaAlta.isEmpty()){
                
                for (Processos processo: processos){
                    if (processo != filaAlta.get(0) && filaAlta.contains(processo)|| filaMedia.contains(processo)|| filaBaixa.contains(processo)){
                            processo.tempoFila += quantum; // +30ms para cada projeto na lista e não executado.
                        }
                    }
               
                if (filaAlta.get(0).qntExecMax != 0){
                    filaAlta.get(0).qntExecMax--;
                    filaAlta.get(0).numExec++;
                    qntQuantum++;

                    p.imprime(processos);
                    Thread.sleep(1000);
                    System.out.println("\n\n");
                }
                else {filaAlta.remove(0);}
            }


            qntQuantum = 1;
            while (qntQuantum <= 2 && !filaMedia.isEmpty()){
                
                for (Processos processo: processos){
                        if (processo != filaMedia.get(0) && filaAlta.contains(processo)|| filaMedia.contains(processo)|| filaBaixa.contains(processo)){
                            processo.tempoFila += quantum;
                        }
                }
                if (filaMedia.get(0).qntExecMax != 0){
                    filaMedia.get(0).qntExecMax--;
                    filaMedia.get(0).numExec++;
                    qntQuantum++;
                    p.imprime(processos);
                    Thread.sleep(1000);                        
                    System.out.println("\n\n");                    
                }
                else {filaMedia.remove(0);}
            }
            

            qntQuantum = 1;
            while (qntQuantum <= 1 && !filaBaixa.isEmpty()){

                if (filaBaixa.get(0).qntExecMax != 0){
                    filaBaixa.get(0).qntExecMax--;
                    filaBaixa.get(0).numExec++;
                    qntQuantum++;
                    p.imprime(processos);
                    Thread.sleep(1000);
                    for (Processos processo: processos){
                        if (processo != filaBaixa.get(0) && filaAlta.contains(processo)|| filaMedia.contains(processo)|| filaBaixa.contains(processo)){
                            processo.tempoFila += quantum;
                        }
                    }
                    System.out.println("\n\n");
                    //System.out.println("Processo: " + filaBaixa.get(0).id  + " tem prioridade: " + filaBaixa.get(0).prioridade + " foi executado " + filaBaixa.get(0).contExec);
                }
                else {filaBaixa.remove(0);}
            }
            
        }
        
        return processos;
    }
    
    public List<Processos> ordenaPrioridade (List<Processos> fila){
        List<Processos> filaProntos = new ArrayList();
        while (!fila.isEmpty())  {  
            int pos = 0;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < fila.size(); i++){
                if (fila.get(i).prioridade > max && !filaProntos.contains(fila.get(i))){
                    max = fila.get(i).prioridade;
                    pos = i;
                }
            }
            filaProntos.add(fila.get(pos));
            fila.remove(pos);
        }
        
        return filaProntos;
    }
    
    public List<Processos> filaAlta(List<Processos> processos){
        
        List<Processos> filaProntosAux = new ArrayList();
        for (Processos processo: processos){
            if (processo.prioridade >= pAlta){
                filaProntosAux.add(processo);
            }
        }
        List<Processos> filaProntos = this.ordenaPrioridade(filaProntosAux);
        
        return filaProntos;
    }
    
    public List<Processos> filaMedia(List<Processos> processos){
        
        List<Processos> filaProntosAux = new ArrayList();
        for (Processos processo: processos){
            if (processo.prioridade > pBaixa && processo.prioridade < pAlta){
                filaProntosAux.add(processo);
            }
        }
        List<Processos> filaProntos = this.ordenaPrioridade(filaProntosAux);
        
        return filaProntos;
    }
    
    public List<Processos> filaBaixa(List<Processos> processos){
        
        List<Processos> filaProntosAux = new ArrayList();
        for (Processos processo: processos){
            if (processo.prioridade <= pBaixa){
                filaProntosAux.add(processo);
            }
        }
        
        List<Processos> filaProntos = this.ordenaPrioridade(filaProntosAux);
        
        return filaProntos;
    }
    
}