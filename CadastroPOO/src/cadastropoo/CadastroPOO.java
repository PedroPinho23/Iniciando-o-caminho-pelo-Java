package cadastropoo;

import model.*;
import java.util.*;
import java.io.*;

public class CadastroPOO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();
        int opcao;

        do {
            System.out.println("\n==== MENU CADASTRO ====");
            System.out.println("1 - Incluir");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Exibir pelo ID");
            System.out.println("5 - Exibir todos");
            System.out.println("6 - Salvar dados");
            System.out.println("7 - Recuperar dados");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Tipo (F = Fisica, J = Juridica): ");
                    String tipo = scanner.nextLine();
                    if (tipo.equalsIgnoreCase("F")) {
                        System.out.print("ID: "); int id = scanner.nextInt(); scanner.nextLine();
                        System.out.print("Nome: "); String nome = scanner.nextLine();
                        System.out.print("CPF: "); String cpf = scanner.nextLine();
                        System.out.print("Idade: "); int idade = scanner.nextInt(); scanner.nextLine();
                        repoFisica.inserir(new PessoaFisica(id, nome, cpf, idade));
                    } else if (tipo.equalsIgnoreCase("J")) {
                        System.out.print("ID: "); int id = scanner.nextInt(); scanner.nextLine();
                        System.out.print("Nome: "); String nome = scanner.nextLine();
                        System.out.print("CNPJ: "); String cnpj = scanner.nextLine();
                        repoJuridica.inserir(new PessoaJuridica(id, nome, cnpj));
                    }
                }
                case 2 -> {
                    System.out.print("Tipo (F = Fisica, J = Juridica): ");
                    String tipo = scanner.nextLine();
                    System.out.print("ID: "); int id = scanner.nextInt(); scanner.nextLine();
                    if (tipo.equalsIgnoreCase("F")) {
                        PessoaFisica pf = repoFisica.obter(id);
                        if (pf != null) {
                            pf.exibir();
                            System.out.print("Novo nome: "); String nome = scanner.nextLine();
                            System.out.print("Novo CPF: "); String cpf = scanner.nextLine();
                            System.out.print("Nova idade: "); int idade = scanner.nextInt(); scanner.nextLine();
                            repoFisica.alterar(new PessoaFisica(id, nome, cpf, idade));
                        } else System.out.println("Pessoa Fisica nao encontrada.");
                    } else if (tipo.equalsIgnoreCase("J")) {
                        PessoaJuridica pj = repoJuridica.obter(id);
                        if (pj != null) {
                            pj.exibir();
                            System.out.print("Novo nome: "); String nome = scanner.nextLine();
                            System.out.print("Novo CNPJ: "); String cnpj = scanner.nextLine();
                            repoJuridica.alterar(new PessoaJuridica(id, nome, cnpj));
                        } else System.out.println("Pessoa Juridica nao encontrada.");
                    }
                }
                case 3 -> {
                    System.out.print("Tipo (F = Fisica, J = Juridica): ");
                    String tipo = scanner.nextLine();
                    System.out.print("ID a excluir: "); int id = scanner.nextInt(); scanner.nextLine();
                    if (tipo.equalsIgnoreCase("F")) repoFisica.excluir(id);
                    else if (tipo.equalsIgnoreCase("J")) repoJuridica.excluir(id);
                }
                case 4 -> {
                    System.out.print("Tipo (F = Fisica, J = Juridica): ");
                    String tipo = scanner.nextLine();
                    System.out.print("ID: "); int id = scanner.nextInt(); scanner.nextLine();
                    if (tipo.equalsIgnoreCase("F")) {
                        PessoaFisica pf = repoFisica.obter(id);
                        if (pf != null) pf.exibir();
                        else System.out.println("Pessoa Fisica nao encontrada.");
                    } else if (tipo.equalsIgnoreCase("J")) {
                        PessoaJuridica pj = repoJuridica.obter(id);
                        if (pj != null) pj.exibir();
                        else System.out.println("Pessoa Juridica nao encontrada.");
                    }
                }
                case 5 -> {
                    System.out.print("Tipo (F = Fisica, J = Juridica): ");
                    String tipo = scanner.nextLine();
                    if (tipo.equalsIgnoreCase("F")) {
                        for (PessoaFisica pf : repoFisica.obterTodos()) pf.exibir();
                    } else if (tipo.equalsIgnoreCase("J")) {
                        for (PessoaJuridica pj : repoJuridica.obterTodos()) pj.exibir();
                    }
                }
                case 6 -> {
                    System.out.print("Prefixo do arquivo: ");
                    String prefixo = scanner.nextLine();
                    try {
                        repoFisica.persistir(prefixo + ".fisica.bin");
                        repoJuridica.persistir(prefixo + ".juridica.bin");
                        System.out.println("Dados salvos com sucesso.");
                    } catch (Exception e) {
                        System.out.println("Erro ao salvar os dados: " + e.getMessage());
                    }
                }
                case 7 -> {
                    System.out.print("Prefixo do arquivo: ");
                    String prefixo = scanner.nextLine();
                    try {
                        repoFisica.recuperar(prefixo + ".fisica.bin");
                        repoJuridica.recuperar(prefixo + ".juridica.bin");
                        System.out.println("Dados recuperados com sucesso.");
                    } catch (Exception e) {
                        System.out.println("Erro ao recuperar os dados: " + e.getMessage());
                    }
                }
                case 0 -> System.out.println("Finalizando...");
                default -> System.out.println("Opção invalida!");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
