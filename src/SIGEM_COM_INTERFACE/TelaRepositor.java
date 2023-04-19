package SIGEM_COM_INTERFACE;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TelaRepositor extends Frame {
    private JComboBox comboBox1;
    private JButton OKButton;
    private JPanel painelGerente;
    private JButton voltarButton;

    public TelaRepositor(){
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
                MetodosRepositor rep =  new MetodosRepositor();
                while(true){
                    if(comboBox1.getSelectedItem().toString().equals("Consultar Produto No Estoque")) {
                        svc2.consultarProduto();
                        break;
                    }
                    else if(comboBox1.getSelectedItem().toString().equals("Adicionar Produto no Mostruário")){
                        rep.adicionarProdutosMostruario();
                        break;
                    }
                    else if(comboBox1.getSelectedItem().toString().equals("Consultar Produto no Mostruário")){
                        rep.consultarProdutoMostruario();
                        break;
                    }
                    else if(comboBox1.getSelectedItem().toString().equals("Alterar Senha")){

                        rep.alterarSenha();
                        break;

                    }
                }
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                janelaGerente.dispose();

                TelaLogin telaLogin = new TelaLogin();

            }
        });
    }
}
