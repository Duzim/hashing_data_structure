package hashing.Main;

import hashing.model.HashTable;
import hashing.model.NodeStatus;
import hashing.service.HashList;
import java.util.Scanner;

public class GerenciamentoFuncionario {
    private static final int tamanhoTabela = 20;
    HashTable<Funcionario> hashTable = new HashTable<Funcionario>(tamanhoTabela);
    HashList<Funcionario> hashList = new HashList<Funcionario>();
    Scanner scanner = new Scanner(System.in);

    private static void cadastrarFuncionario(HashTable<Funcionario> hashTable, HashList<Funcionario> hashList, Scanner scanner){
        String nomeFuncionario;
        Double salarioFuncionario;

        System.out.print("Digite o nome do Funcionario: ");
        do {
            nomeFuncionario = scanner.nextLine().trim();
        } while (nomeFuncionario.isEmpty());

        System.out.print("Digite o salario do Funcionario: ");
        salarioFuncionario = scanner.nextDouble();

        Funcionario funcionario = new Funcionario(nomeFuncionario,salarioFuncionario);
        hashList.insert(hashTable,funcionario);

        System.out.println("funcionario inserido");


        System.out.println( "================================================================================\n"+
                            hashList.toString(hashTable) +
                            "\n================================================================================\n"
        );
    }

    private static void aumentoSalarioPercentual(HashTable<Funcionario> hashTable, Scanner aumentoDeSalario){
        System.out.println("Qual o Aumento de salario será feito(digite somente o valor da porcentagem): ");
        double porcentagem = aumentoDeSalario.nextDouble();
        double salario;

        int tamanhoHash = hashTable.getItems().size();
        for (int i = 0; i < tamanhoHash; i++) {
            if (hashTable.getItems().get(i).getStatus() == NodeStatus.BUSY) {

                salario = hashTable.getItems().get(i).getValue().getSalario();
                double salarioAumentado = salario + ((salario * porcentagem)/100.0);

                hashTable.getItems().get(i).getValue().setSalario(salarioAumentado);

                System.out.println( "\n==============================\n"+
                                    "Salario de    : "+hashTable.getItems().get(i).getValue().getNome()+"\n"+
                                    "Aumentado para: " + hashTable.getItems().get(i).getValue().getSalario()+"\n"+
                                    "\n=============================="
                );
            }
        }
        System.out.println("aumento de salario feito com sucesso");
    }

    private static void consultarSalarioMais500(HashTable<Funcionario> hashTable){
        Double somaSalarial = 0.00;

        int tamanhoHash = hashTable.getItems().size();
        for (int i = 0; i < tamanhoHash; i++) {
            if (hashTable.getItems().get(i).getStatus() == NodeStatus.BUSY) {
                if (hashTable.getItems().get(i).getValue().getSalario() >= 500) {
                    somaSalarial += hashTable.getItems().get(i).getValue().getSalario();
                }
            }
        }
        System.out.println("Soma dos salario acima de R$ 500,00: " + somaSalarial);
    }

    private static void consultarTdsFuncionarios(HashTable<Funcionario> hashTable){
        int tamanhoHash = hashTable.getItems().size();

        for (int i = 0; i < tamanhoHash; i++) {
            if (hashTable.getItems().get(i).getStatus() == NodeStatus.BUSY) {
                System.out.println( "\n==============================\n"+
                                    "Funcionario("+i+")\n"+
                                    "Nome   : "+ hashTable.getItems().get(i).getValue().getNome()+"\n"+
                                    "Salario: " + hashTable.getItems().get(i).getValue().getSalario()+
                                    "\n=============================="
                );
            }
        }
    }

    private static void excluirPorNome(HashTable<Funcionario> hashTable,HashList hashList, Scanner scanner){
        System.out.println("Nome do funcionario que deseja excluir: ");
        String nomeRemover;
        String nomeExistente;

        do{
            nomeRemover = scanner.nextLine();
        } while (nomeRemover.isEmpty());

        System.out.println( "Hash estado inicial=============================================================\n"+
                            hashList.toString(hashTable)+
                            "\n================================================================================"
        );

        int tamanhoHash = hashTable.getItems().size();
        for (int i = 0; i < tamanhoHash; i++) {
            if (hashTable.getItems().get(i).getStatus() == NodeStatus.BUSY) {
                nomeExistente = hashTable.getItems().get(i).getValue().getNome();
                if (nomeExistente.equals(nomeRemover)) {
                    hashList.remove(hashTable, hashTable.getItems().get(i).getValue());
                    System.out.println("Funcionario "+ nomeExistente + " removido com sucesso!");
                }
            }
        }
        System.out.println( "Hash estado pós excluir ========================================================\n"+
                            hashList.toString(hashTable)+
                            "\n================================================================================\n"
        );
    }

    public void exibirMenu(){
        int opcao = 0;
        do {
            System.out.print(
                    "Menu:\n"+
                    "1 - Cadastrar Funcionario;\n" +
                    "2 - Conceder aumento percentual para todos os funcionarios;\n"+
                    "3 - Consultar a soma salarioal dos funcionários com salário superior a 500\n"+
                    "4 - Consultar todos os funcionários\n"+
                    "5 - Excluir por nome\n"+
                    "6 - Sair\n"
            );
            opcao = scanner.nextInt();

            switch (opcao){
                case 1:
                    cadastrarFuncionario(hashTable,hashList,scanner);
                    break;
                case 2:
                    aumentoSalarioPercentual(hashTable,scanner);
                    break;
                case 3:
                    consultarSalarioMais500(hashTable);
                    break;
                case 4:
                    consultarTdsFuncionarios(hashTable);
                    break;
                case 5:
                    excluirPorNome(hashTable,hashList,scanner);
                    break;
                case 6:
                    System.out.println("sair");
                    break;
                default:
                    System.out.println("Tente novamente");
                    break;
            }
        }while (opcao != 6);
    }
}
