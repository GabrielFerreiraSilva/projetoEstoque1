package SIGEM_COM_INTERFACE;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class MetodosEstoquista {
    // Arquivo do estoque
    File estoque = new File("C:/Users/Computador/IdeaProjects/projetoEstoque1/src/SIGEM_COM_INTERFACE/produtos.txt");

    // Arquivo feito para auxiliar a atualização que será feita no estoque
    File auxiliar = new File("C:/Users/Computador/IdeaProjects/projetoEstoque1/src/SIGEM_COM_INTERFACE/auxiliar.txt");
    public void adicionarProdutosEstoque() throws FileNotFoundException{

        try {
            // Inicialmente o sistema lê o código de barras do produto que o estoquista quer adicionar
            long codigoLido = Long.parseLong(JOptionPane.showInputDialog(null, "Digite o Código de Barras do Produto", "Adicionar Produtos no Estoque", JOptionPane.PLAIN_MESSAGE));

            // Depois, ele lê a quantidade de itens que vão ser adicionadas
            int quantidadeNova = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a Quantidade de Itens a ser Adicionados", "Adicionar Produtos no Estoque", JOptionPane.PLAIN_MESSAGE));

            // O Estoquista não tem o direito de adicionar novos produtos
            // Logo se, por meio do código de barras, for verificado que o produto não existe cadastrado no estoque, essa mensagem será exibida
            if (consultarProdutoEstoque(codigoLido) == false){
                JOptionPane.showMessageDialog(null, "O produto não está cadastrado. Entre em Contato com o Gerente.", "Adicionar Produto Ao Estoque", JOptionPane.PLAIN_MESSAGE);
            }
            // Caso o produto exista no estoque, o programa continuará
            else {
                // Por meio do Scanner é verificado as informações dos produtos no estoque
                Scanner leitura = new Scanner(estoque);
                while(leitura.hasNextLine()){
                    Long codigoDeBarra = Long.parseLong(leitura.nextLine());
                    String marca = leitura.nextLine();
                    String modelo = leitura.nextLine();
                    float preco = Float.parseFloat(leitura.nextLine());
                    int quantidade = Integer.parseInt(leitura.nextLine());

                    // Caso o código digitado, for igual ao lido de algum produto durante a verificação do estoque
                    // ele será adicionado ao arquivo auxiliar com as novas quantidades desejadas pelo estoquista
                    if(codigoDeBarra != codigoLido){
                        FileWriter escritor1 = new FileWriter(auxiliar,true);
                        escritor1.write(codigoDeBarra + "\n");
                        escritor1.write(marca + "\n");
                        escritor1.write(modelo + "\n");
                        escritor1.write(preco + "\n");
                        escritor1.write(quantidade + "\n");
                        escritor1.close();

                        // Caso contrário, o restante dos produtos não sofrerão alterações e serão copiados TAMBÉM para o arquivo auxiliar
                    } else if (codigoDeBarra == codigoLido){
                        FileWriter escritor2 = new FileWriter(auxiliar,true);
                        escritor2.write(codigoDeBarra + "\n");
                        escritor2.write(marca + "\n");
                        escritor2.write(modelo + "\n");
                        escritor2.write(preco + "\n");
                        escritor2.write(quantidade + quantidadeNova + "\n");
                        escritor2.close();
                    }
                }
                // Após todos os produtos do estoque já estarem no arquivo auxiliar (com suas quantidades corretas)
                // O arquivo do estoque será limpo e ficará vazio
                PrintWriter limpador = new PrintWriter(estoque);
                limpador.flush();
                limpador.close();

                // A partir de agora o Scanner vai ler os produtos que estão no arquivo auxiliar
                Scanner leitura2 = new Scanner(auxiliar);
                while(leitura2.hasNextLine()){
                    Long codigoDeBarra = Long.parseLong(leitura2.nextLine());
                    String marca = leitura2.nextLine();
                    String modelo = leitura2.nextLine();
                    float preco = Float.parseFloat(leitura2.nextLine());
                    int quantidade = Integer.parseInt(leitura2.nextLine());

                    // E escrever novamente no estoque, só que agora com todas as informações corretas
                    FileWriter escritor3 = new FileWriter(estoque,true);
                    escritor3.write(codigoDeBarra + "\n");
                    escritor3.write(marca + "\n");
                    escritor3.write(modelo + "\n");
                    escritor3.write(preco + "\n");
                    escritor3.write(quantidade + "\n");
                    escritor3.close();
                }
                // Logo após, a transferência para o estoque, o arquivo auxiliar é limpo e fica totalmente vazio
                PrintWriter limpador2 = new PrintWriter(auxiliar);
                limpador2.flush();
                limpador2.close();

                JOptionPane.showMessageDialog(null, "Produto adicionado ao estoque com sucesso.", "Adicionar Produto Ao Estoque", JOptionPane.PLAIN_MESSAGE);
            }

        } catch (Exception exception){
            // Em caso de algum erro durante o caminho, essa mensagem será exibida
            JOptionPane.showMessageDialog(null, "Erro ao adicionar produto no estoque.", "Adicionar Produto Ao Estoque", JOptionPane.PLAIN_MESSAGE);
        }
    }
    public boolean consultarProdutoEstoque(long codigoDeBarras) throws FileNotFoundException {
        String marca = null;
        String modelo = null;
        float preco = 0;
        int quantidade = 0;
        boolean flag = false;
        while(true){
            Scanner teclado = new Scanner(System.in);
            try{
                Scanner leitura = new Scanner(estoque);
                while(leitura.hasNextLine()){
                    try{
                        Long cbLido = Long.parseLong(leitura.nextLine());
                        marca = leitura.nextLine();
                        modelo = leitura.nextLine();
                        preco = Float.parseFloat(leitura.nextLine());
                        quantidade = Integer.parseInt(leitura.nextLine());

                        if(cbLido == codigoDeBarras){
                            return true;
                        }
                    }
                    catch(Exception exception){
                        continue;
                    }
                }
            }
            catch(Exception exception){
                System.out.println("Erro ao acessar a base de dados");
            }
            if(!flag){
                //System.out.println("O produto não está cadastrado");
                return false;
            }
            else{
                break;
            }
        }
        return flag;
    }
}