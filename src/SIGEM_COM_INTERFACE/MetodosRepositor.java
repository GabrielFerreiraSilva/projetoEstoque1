package SIGEM_COM_INTERFACE;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class MetodosRepositor {
    // Arquivo referente ao mostruário
    File mostruario = new File("C:/Users/Computador/IdeaProjects/projetoEstoque1/src/SIGEM_COM_INTERFACE/mostruario.txt");

    File arquivo = new File("C:/Users/Computador/IdeaProjects/projetoEstoque1/src/SIGEM_COM_INTERFACE/funcionarios.txt");

    // Arquivo referente ao estoque
    File estoque = new File("C:/Users/Computador/IdeaProjects/projetoEstoque1/src/SIGEM_COM_INTERFACE/produtos.txt");

    // Arquivos que vao auxiliar no funciomanete da classe repositor
    File auxiliar = new File("C:/Users/Computador/IdeaProjects/projetoEstoque1/src/SIGEM_COM_INTERFACE/auxiliar.txt");
    File auxiliar2 = new File("C:/Users/Computador/IdeaProjects/projetoEstoque1/src/SIGEM_COM_INTERFACE/auxiliar2.txt");

    public void adicionarProdutosMostruario() {

        try {
            // O usuário digita o código de barras do produto
            long codigoLido = Long.parseLong(JOptionPane.showInputDialog(null, "Digite o Código de Barras do Produto", "Adicionar Produtos no Mostruário", JOptionPane.PLAIN_MESSAGE));

            // Logo após esses dois métodos são acionados, mostrando a disponibilidade do mesmo tanto no mostruário quanto no estoque

            // O usuário digita quantas unidades ele quer adicionar no mostruário
            int quantidadeNova = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a Quantidade de Itens a ser Adicionados", "Adicionar Produtos no Mostruário", JOptionPane.PLAIN_MESSAGE));

            // Caso o produto já esteja no mostruário e no estoque
            if(consultarProdutoMostruarioBoolean(codigoLido) == true){

                //Primeiro ocorre uma leitura no mostuário
                Scanner leitura = new Scanner(mostruario);
                while (leitura.hasNextLine()) {
                    Long codigoDeBarra = Long.parseLong(leitura.nextLine());
                    String marca = leitura.nextLine();
                    String modelo = leitura.nextLine();
                    float preco = Float.parseFloat(leitura.nextLine());
                    int quantidade = Integer.parseInt(leitura.nextLine());

                    if (codigoLido == codigoDeBarra){
                        // Os PRODUTOS vão sendo tranferidos para o auxiliar
                        FileWriter escritor1 = new FileWriter(auxiliar,true);
                        escritor1.write(codigoDeBarra + "\n");
                        escritor1.write(marca + "\n");
                        escritor1.write(modelo + "\n");
                        escritor1.write(preco + "\n");
                        // Caso seja viável tranferir do estoque para o mostruário, essa ação é executada
                        if (verificarQuantEstoque(codigoLido,quantidadeNova) == true) {
                            escritor1.write(quantidade + quantidadeNova + "\n");
                            escritor1.close();
                            // Caso não seja viável ocorrer essa transferência a quantidade não vai ser alterada
                        } else if (verificarQuantEstoque(codigoLido,quantidadeNova) == false){
                            escritor1.write(quantidade + "\n");
                            escritor1.close();
                        }
                    } else if (codigoLido != codigoDeBarra) {
                        // Os PRODUTOS vão sendo tranferidos para o auxiliar
                        FileWriter escritor2 = new FileWriter(auxiliar,true);
                        escritor2.write(codigoDeBarra + "\n");
                        escritor2.write(marca + "\n");
                        escritor2.write(modelo + "\n");
                        escritor2.write(preco + "\n");
                        escritor2.write(quantidade + "\n");
                        escritor2.close();
                    }
                }

                // Em seguida ocorre uma leitura no estoque
                Scanner leitura2 = new Scanner(estoque);
                while(leitura2.hasNextLine()){
                    Long codigoDeBarra = Long.parseLong(leitura2.nextLine());
                    String marca = leitura2.nextLine();
                    String modelo = leitura2.nextLine();
                    float preco = Float.parseFloat(leitura2.nextLine());
                    int quantidade = Integer.parseInt(leitura2.nextLine());

                    if(codigoDeBarra != codigoLido){
                        // Os PRODUTOS vão sendo tranferidos para o auxiliar2
                        FileWriter escritor1 = new FileWriter(auxiliar2,true);
                        escritor1.write(codigoDeBarra + "\n");
                        escritor1.write(marca + "\n");
                        escritor1.write(modelo + "\n");
                        escritor1.write(preco + "\n");
                        escritor1.write(quantidade + "\n");
                        escritor1.close();

                    }
                    else if (codigoLido == codigoDeBarra) {
                        // Os PRODUTOS vão sendo tranferidos para o auxiliar2
                        FileWriter escritor2 = new FileWriter(auxiliar2,true);
                        escritor2.write(codigoDeBarra + "\n");
                        escritor2.write(marca + "\n");
                        escritor2.write(modelo + "\n");
                        escritor2.write(preco + "\n");
                        // Caso seja viável ocorrer a transferência a quantidade do produto no estoque é atualizada
                        if (verificarQuantEstoque(codigoLido,quantidadeNova) == true) {
                            escritor2.write(quantidade - quantidadeNova + "\n");
                            escritor2.close();
                            // Caso a verificação seja igual a FALSE, essa mensagem vai aparecer e a quantidade não será alterada
                        } else if (verificarQuantEstoque(codigoLido,quantidadeNova) == false){
                            JOptionPane.showMessageDialog(null, "Não há essa quantidade de produtos desse modelo no estoque. Tente Novamente.", "Adicionar Produtos no Mostruário", JOptionPane.PLAIN_MESSAGE);
                            escritor2.write(quantidade + "\n");
                            escritor2.close();
                        }
                    }
                }
                // Após todas as informações atuais estarem nos arquivos auxiliares
                // As informações do estoque são apagadas
                PrintWriter limpador = new PrintWriter(estoque);
                limpador.flush();
                limpador.close();

                // As informações do mostruário são apagadas
                PrintWriter limpador2 = new PrintWriter(mostruario);
                limpador.flush();
                limpador.close();

                // A partir daqui às informações no arquivo auxiliar, referente ao MOSTRUÁRIO, vão ser tranferidas para o arquivo mostruário
                Scanner leitura3 = new Scanner(auxiliar);
                while (leitura3.hasNextLine()){
                    Long codigoDeBarra = Long.parseLong(leitura3.nextLine());
                    String marca = leitura3.nextLine();
                    String modelo = leitura3.nextLine();
                    float preco = Float.parseFloat(leitura3.nextLine());
                    int quantidade = Integer.parseInt(leitura3.nextLine());

                    FileWriter escritor3 = new FileWriter(mostruario,true);
                    escritor3.write(codigoDeBarra + "\n");
                    escritor3.write(marca + "\n");
                    escritor3.write(modelo + "\n");
                    escritor3.write(preco + "\n");
                    escritor3.write(quantidade + "\n");
                    escritor3.close();
                }

                // A partir daqui as informações no arquivo auxiliar2, referente ao ESTOQUE, vão ser tranferidas para o arquivo estoque
                Scanner leitura4 = new Scanner(auxiliar2);
                while (leitura4.hasNextLine()){
                    Long codigoDeBarra = Long.parseLong(leitura4.nextLine());
                    String marca = leitura4.nextLine();
                    String modelo = leitura4.nextLine();
                    float preco = Float.parseFloat(leitura4.nextLine());
                    int quantidade = Integer.parseInt(leitura4.nextLine());

                    FileWriter escritor4 = new FileWriter(estoque,true);
                    escritor4.write(codigoDeBarra + "\n");
                    escritor4.write(marca + "\n");
                    escritor4.write(modelo + "\n");
                    escritor4.write(preco + "\n");
                    escritor4.write(quantidade + "\n");
                    escritor4.close();
                }

                // Após toda a tranferência ocorrer de forma correta, os as informações presentes nos arquivos auxiliares são apagadas
                PrintWriter limpador3 = new PrintWriter(auxiliar);
                limpador.flush();
                limpador.close();

                PrintWriter limpador4 = new PrintWriter(auxiliar2);
                limpador.flush();
                limpador.close();
                JOptionPane.showMessageDialog(null, "Produto Adicionado com Sucesso!", "Adicionar Produtos no Mostruário", JOptionPane.PLAIN_MESSAGE);

            }

            // Caso o produto não esteja no mostruário, somente no estoque
            else if (consultarProdutoMostruarioBoolean(codigoLido) == false) {
                // Ocorre uma leitura no estoque
                Scanner leitura1 = new Scanner(estoque);
                while (leitura1.hasNextLine()){
                    Long codigoDeBarra = Long.parseLong(leitura1.nextLine());
                    String marca = leitura1.nextLine();
                    String modelo = leitura1.nextLine();
                    float preco = Float.parseFloat(leitura1.nextLine());
                    int quantidade = Integer.parseInt(leitura1.nextLine());

                    if (codigoLido != codigoDeBarra){
                        // Os produtos começam a ser escrito no arquivo auxiliar
                        FileWriter escritor1 = new FileWriter(auxiliar,true);
                        escritor1.write(codigoDeBarra + "\n");
                        escritor1.write(marca + "\n");
                        escritor1.write(modelo + "\n");
                        escritor1.write(preco + "\n");
                        escritor1.write(quantidade + "\n");
                        escritor1.close();
                    }
                    else if (codigoLido == codigoDeBarra){
                        // E as informações do produto escolhido são atualizadas e transferidas para o mostruário,já que ele não existia antes
                        FileWriter escritor2 = new FileWriter(mostruario,true);
                        escritor2.write(codigoDeBarra + "\n");
                        escritor2.write(marca + "\n");
                        escritor2.write(modelo + "\n");
                        escritor2.write(preco + "\n");
                        // Caso seja viável a transferência, a quantidade é alterada
                        if (verificarQuantEstoque(codigoLido,quantidadeNova) == true) {
                            escritor2.write(quantidadeNova + "\n");
                            escritor2.close();

                            // Caso não seja viável a transferência, a quantidade permanece a mesma
                        } else if (verificarQuantEstoque(codigoLido,quantidadeNova) == false){
                            JOptionPane.showMessageDialog(null, "Não há essa quantidade de produtos desse modelo no estoque. Tente Novamente.", "Adicionar Produtos no Mostruário", JOptionPane.PLAIN_MESSAGE);
                            escritor2.write(quantidade + "\n");
                            escritor2.close();
                        }

                        // As informações atuais, referente ao produto no estoque, são transferidos para o arquivo auxiliar
                        FileWriter escritor3 = new FileWriter(auxiliar,true);
                        escritor3.write(codigoDeBarra + "\n");
                        escritor3.write(marca + "\n");
                        escritor3.write(modelo + "\n");
                        escritor3.write(preco + "\n");
                        escritor3.write(quantidade - quantidadeNova + "\n");
                        escritor3.close();
                    }
                }
                // O arquivo estoque é limpo
                PrintWriter limpador = new PrintWriter(estoque);
                limpador.flush();
                limpador.close();

                //A partir as informações atuais do estoque são transferidas do arquivo auxiliar para o arquivo estoque
                Scanner leitura2 = new Scanner(auxiliar);
                while (leitura2.hasNextLine()){
                    Long codigoDeBarra = Long.parseLong(leitura2.nextLine());
                    String marca = leitura2.nextLine();
                    String modelo = leitura2.nextLine();
                    float preco = Float.parseFloat(leitura2.nextLine());
                    int quantidade = Integer.parseInt(leitura2.nextLine());

                    FileWriter escritor4 = new FileWriter(estoque,true);
                    escritor4.write(codigoDeBarra + "\n");
                    escritor4.write(marca + "\n");
                    escritor4.write(modelo + "\n");
                    escritor4.write(preco + "\n");
                    escritor4.write(quantidade + "\n");
                    escritor4.close();
                }
                // Por fim o arquivo auxiliar é limpo
                PrintWriter limpador2 = new PrintWriter(auxiliar);
                limpador2.flush();
                limpador2.close();
                JOptionPane.showMessageDialog(null, "Produto Adicionado com Sucesso!", "Adicionar Produtos no Mostruário", JOptionPane.PLAIN_MESSAGE);

            }

        } catch (Exception exception) {
            // Em caso de algum erro durante todo o caminho, essa mensagem será exibida
            JOptionPane.showMessageDialog(null, "Erro ao adicionar produto no mostruário.", "Adicionar Produtos no Mostruário", JOptionPane.PLAIN_MESSAGE);

        }
    }

    public void consultarProdutoMostruario(){
        boolean flag = false;
        String cb = JOptionPane.showInputDialog(null, "Digite o Código de Barras do Produto", "Consultar Produto", JOptionPane.PLAIN_MESSAGE);
        try{
            Scanner leitura = new Scanner(mostruario);
            while(leitura.hasNextLine()){
                if(leitura.nextLine().equals(cb)){
                    JOptionPane.showMessageDialog(null, "Código de Barras: " + cb + "\nMarca: "
                            + leitura.nextLine() + "\nModelo: " + leitura.nextLine() + "\nPreço: " + leitura.nextLine()  + "\nQuantidade no Mostruário: " + leitura.nextLine(), "Consultar Produto", JOptionPane.PLAIN_MESSAGE);
                    flag = true;
                    break;
                }
            }
            if(!flag){
                JOptionPane.showMessageDialog(null, "Produto Não Encontrado", "Consultar Produto", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Ocorreu um erro!");
        }
    }

    private boolean consultarProdutoMostruarioBoolean(long codigoDeBarras) throws FileNotFoundException {
        String marca = null;
        String modelo = null;
        float preco = 0;
        int quantidade = 0;
        boolean flag = false;
        while (true) {
            Scanner teclado = new Scanner(System.in);
            try {
                // Ele começa a ler o que está no mostruário
                Scanner leitura = new Scanner(mostruario);
                while (leitura.hasNextLine()) {
                    try {
                        Long cbLido = Long.parseLong(leitura.nextLine());
                        marca = leitura.nextLine();
                        modelo = leitura.nextLine();
                        preco = Float.parseFloat(leitura.nextLine());
                        quantidade = Integer.parseInt(leitura.nextLine());

                        // Caso o código de barras encontre um com o mesmo valor, é retornado true
                        if (cbLido == codigoDeBarras) {
                            return true;
                        }
                        else {
                        }
                    } catch (Exception exception) {
                        continue;
                    }
                }
            } catch (Exception exception) {
                System.out.println("Erro ao acessar a base de dados");
            }
            // Caso não encontre nada, retorna false
            if (!flag) {
                return false;
            } else {
                break;
            }
        }
        return flag;
    }

    // Método criado com o intuito de verificar se a quantidade X de produtos que o usuário quer retirar do estoque é viável
    private boolean verificarQuantEstoque(long codigoDeBarrasLido, int quant) throws FileNotFoundException {
        Scanner verificador = new Scanner(estoque);
        while (verificador.hasNextLine()){
            Long codigoDeBarra = Long.parseLong(verificador.nextLine());
            String marca = verificador.nextLine();
            String modelo = verificador.nextLine();
            float preco = Float.parseFloat(verificador.nextLine());
            int quantidade = Integer.parseInt(verificador.nextLine());

            if (codigoDeBarrasLido == codigoDeBarra){

                // Caso a quantidade que o usuário quer retirar do estoque for menor ou igual a quantidade que existe no estoque retorna TRUE
                if (quantidade - quant >= 0){
                    return true;

                    // Caso contrário, retorna FALSE
                } else if (quantidade - quant < 0) {
                    return false;
                }
            }
        }

        return false;
    }

    public void alterarSenha(){

        String matricula = JOptionPane.showInputDialog(null, "Digite sua matrícula", "Alterar Senha", JOptionPane.PLAIN_MESSAGE);
        String novaSenha = JOptionPane.showInputDialog(null, "Digite a Nova Senha", "Alterar Senha", JOptionPane.PLAIN_MESSAGE);
        try{
            Scanner leitura = new Scanner(arquivo);
            while(leitura.hasNextLine()){
                String matriculaLida = leitura.nextLine();
                String senhaLida = leitura.nextLine();
                String cargoLido = leitura.nextLine();
                if(matriculaLida.equals(matricula)){
                    FileWriter escritor2 = new FileWriter(auxiliar, true);
                    escritor2.write(matriculaLida + "\n");
                    escritor2.write(novaSenha + "\n");
                    escritor2.write(cargoLido + "\n");
                    escritor2.close();
                }
                else{
                    FileWriter escritor1 = new FileWriter(auxiliar, true);
                    escritor1.write(matriculaLida + "\n");
                    escritor1.write(senhaLida + "\n");
                    escritor1.write(cargoLido + "\n");
                    escritor1.close();
                }
            }
            PrintWriter limpador = new PrintWriter(arquivo);
            limpador.flush();
            limpador.close();
            Scanner leitura2 = new Scanner(auxiliar);
            while(leitura2.hasNextLine()){
                String matriculaLida = leitura2.nextLine();
                String senhaLida = leitura2.nextLine();
                String cargoLido = leitura2.nextLine();
                FileWriter escritor3 = new FileWriter(arquivo, true);
                escritor3.write(matriculaLida + "\n");
                escritor3.write(senhaLida + "\n");
                escritor3.write(cargoLido + "\n");
                escritor3.close();
            }
            PrintWriter limpador2 = new PrintWriter(auxiliar);
            limpador2.flush();
            limpador2.close();
            JOptionPane.showMessageDialog(null, "Senha Alterada Com Sucesso", "Alterar Senha", JOptionPane.PLAIN_MESSAGE);
        }
        catch(Exception e){
            System.out.println("Ocorreu um erro");
        }

    }
}