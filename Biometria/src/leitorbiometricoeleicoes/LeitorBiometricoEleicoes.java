/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leitorbiometricoeleicoes;

import com.clayton.controle.Sistema;
import com.clayton.metodos.CapturaDigital;
import com.clayton.metodos.DigitalPersistencia;
import com.clayton.model.CadDigital;
import com.clayton.telas.cadastrardigital.CadastrodeDigital;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author clayton
 */
public class LeitorBiometricoEleicoes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DigitalPersistencia di = new DigitalPersistencia();
       byte [] b;
        //System.out.println(Arrays.toString(di.BuscaPessoa(1)));
        CadDigital pe = new CadDigital();
        //pe=di.BuscaPessoa(2);
        System.out.println(pe);
        CapturaDigital me = new CapturaDigital();
        b = pe.getPolegardir();
        System.out.println(Arrays.toString(b));
        
         me.setTemplates(me.Injetar_Digitais_Pessoa(pe));
        
        // se retornar com digitais ja inseri para alterar
        
            CadastrodeDigital sa = new CadastrodeDigital();
            sa.setTemplates(me.getTemplates());
            sa.UpdateUIP();
            boolean cadastro=true;
            sa.setCadastro(cadastro);
            sa.setVisible(true);
          
        
        
        
    }
    
}
