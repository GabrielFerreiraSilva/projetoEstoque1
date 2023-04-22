package SIGEM_COM_INTERFACE;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class TelaGerente extends Frame {
    private JComboBox comboBox1;
    private JButton OKButton;
    private JPanel painelGerente;
    private JButton voltarButton;

    private TelaLogin novaTela;

    public TelaGerente(){
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
                MetodosGerente ger = new MetodosGerente();
                while(true){
                    if(comboBox1.getSelectedItem().toString().equals("Consultar Produto No Estoque")) {
                        svc2.consultarProduto();
                        break;
                    }
                    else if(comboBox1.getSelectedItem().toString().equals("Adicionar Novo Produto Ao Cadastro")){
                        ger.adicionarProduto();
                        break;
                    }
                    else if(comboBox1.getSelectedItem().toString().equals("Remover Produto Do Cadastro")){
                        ger.retirarProduto();
                        break;
                    }
                    else if(comboBox1.getSelectedItem().toString().equals("Adicionar Novo Funcionário Ao Cadastro")){
                        ger.adicionarFuncionario();
                        break;
                    }
                    else if(comboBox1.getSelectedItem().toString().equals("Remover Funcionário Do Cadastro")){
                        ger.removerFuncionario();
                        break;
                    }
                    else if(comboBox1.getSelectedItem().toString().equals("Alterar Senha")){
                        ger.alterarSenha();
                        break;
                    }
                    else if(comboBox1.getSelectedItem().toString().equals("Histórico de Movimentações")){
                        try {
                            ger.visualizarLOG();

                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
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
