package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaRepo {
    private List<PessoaFisica> pessoas = new ArrayList<>();

    public void inserir(PessoaFisica pessoa) {
        if (obter(pessoa.getId()) != null) {
            System.out.println("Pessoa Fisica com esse ID ja existe.");
            return;
        }
        pessoas.add(pessoa);
    }

    public void alterar(PessoaFisica pessoa) {
        for (int i = 0; i < pessoas.size(); i++) {
            if (pessoas.get(i).getId() == pessoa.getId()) {
                pessoas.set(i, pessoa);
                return;
            }
        }
        System.out.println("Pessoa Fisica não encontrada para alteracao.");
    }

    public void excluir(int id) {
        pessoas.removeIf(p -> p.getId() == id);
    }

    public PessoaFisica obter(int id) {
        for (PessoaFisica p : pessoas) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public List<PessoaFisica> obterTodos() {
        return pessoas;
    }

    public void persistir(String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(pessoas);
        } catch (IOException e) {
            System.out.println("Erro ao salvar Pessoa Fisica: " + e.getMessage());
        }
    }

    public void recuperar(String nomeArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            pessoas = (List<PessoaFisica>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar Pessoa Fisica: " + e.getMessage());
        }
    }
}
