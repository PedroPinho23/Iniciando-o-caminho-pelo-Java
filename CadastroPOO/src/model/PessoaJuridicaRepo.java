package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaRepo {
    private List<PessoaJuridica> pessoas = new ArrayList<>();

    public void inserir(PessoaJuridica pessoa) {
        if (obter(pessoa.getId()) != null) {
            System.out.println("Pessoa Juridica com esse ID ja existe.");
            return;
        }
        pessoas.add(pessoa);
    }

    public void alterar(PessoaJuridica pessoa) {
        for (int i = 0; i < pessoas.size(); i++) {
            if (pessoas.get(i).getId() == pessoa.getId()) {
                pessoas.set(i, pessoa);
                return;
            }
        }
        System.out.println("Pessoa Juridica nao encontrada para alteracao.");
    }

    public void excluir(int id) {
        pessoas.removeIf(p -> p.getId() == id);
    }

    public PessoaJuridica obter(int id) {
        for (PessoaJuridica p : pessoas) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public List<PessoaJuridica> obterTodos() {
        return pessoas;
    }

    public void persistir(String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(pessoas);
        } catch (IOException e) {
            System.out.println("Erro ao salvar Pessoa Juridica: " + e.getMessage());
        }
    }

    public void recuperar(String nomeArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            pessoas = (List<PessoaJuridica>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar Pessoa Juridica: " + e.getMessage());
        }
    }
}
