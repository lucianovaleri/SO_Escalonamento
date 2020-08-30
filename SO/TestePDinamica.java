/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luciano
 */
public class TestePDinamica {
    public static void main(String[] args) throws InterruptedException {
       List<Processos> processos = new ArrayList();
        processos.add(new Processos(0 ,200, 1));
        processos.add(new Processos(1, 300, 3));
        processos.add(new Processos(2, 250, 2));
        processos.add(new Processos(3, 100, 5));
        processos.add(new Processos(4, 200, 8));
        processos.add(new Processos(5, 250, 3));
        processos.add(new Processos(6, 150, 1));
        processos.add(new Processos(7, 100, 1));
        processos.add(new Processos(8, 200, 4));
        processos.add(new Processos(9, 300, 6));
        
        
                
        EscalonamentoPDinamica priod = new EscalonamentoPDinamica();
        List<Processos> escPriod = priod.escPRIOd(processos);
        Processos p2 = new Processos();
        p2.imprime(escPriod);
        
    } 
    }

