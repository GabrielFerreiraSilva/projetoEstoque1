package SIGEM_COM_INTERFACE;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class TelaEstoquista extends Frame {
    private JComboBox comboBox1;
    private JButton OKButton;
    private JPanel painelGerente;
    public TelaEstoquista(){
        JFrame janelaGerente = new JFrame("SIGEM V1.5");
        janelaGerente.add(painelGerente);
        janelaGerente.pack();
        janelaGerente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaGerente.setLocationRelativeTo(null);
        janelaGerente.setResizable(false);
        janelaGerente.setVisible(true);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginEConsulta svc2 = new LoginEConsulta(null, null);
                MetodosEstoquista est = new MetodosEstoquista();

                while(true){
                    if(comboBox1.getSelectedItem().toString().equals("Consultar Produto no Estoque")) {
                        svc2.consultarProduto();
                        break;
                    }
                    else if(comboBox1.getSelectedItem().toString().equals("Adicionar Produto no Estoque")){
                        try {
                            est.adicionarProdutosEstoque();
                            break;
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });
    }
}
