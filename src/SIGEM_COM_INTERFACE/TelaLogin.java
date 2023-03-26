package SIGEM_COM_INTERFACE;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TelaLogin extends JFrame{
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton OKButton;
    private JPanel painelLogin;
    public TelaLogin(){
        JFrame janelaLogin = new JFrame("SIGEM V1.5");
        janelaLogin.add(painelLogin);
        janelaLogin.pack();
        janelaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaLogin.setLocationRelativeTo(null);
        janelaLogin.setResizable(false);
        janelaLogin.setVisible(true);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String senha = new String(passwordField1.getPassword());
                LoginEConsulta svc = new LoginEConsulta(textField1.getText(), senha);
                svc.realizarLogin();
                while(true){
                    if(svc.getAcabou()){
                        if(svc.getFlag()){
                            janelaLogin.dispose();
                            if(svc.getCargo().equals("gerente")){
                                TelaRepositor tela2 = new TelaRepositor();
                            } else if(svc.getCargo().equals("estoquista")){
                                TelaEstoquista telaEst = new TelaEstoquista();
                            } else if(svc.getCargo().equals("repositor")){
                                TelaRepositor telarep = new TelaRepositor();
                            }
                            break;
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Matricula ou Senha Incorreta. Tente Novamente.", "Erro!", JOptionPane.ERROR_MESSAGE);
                            textField1.setText("");
                            passwordField1.setText("");
                            break;
                        }
                    }
                }
            }
        });
    }
}