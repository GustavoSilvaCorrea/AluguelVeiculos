package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import exceptions.DataNascimentoInvalidaException;
import exceptions.DocumentoInvalidoException;

@Entity
@DiscriminatorValue("Pessoa_Fisica")
public class PessoaFisica extends Cliente {
    private String CPF; // CPF
    private String DataNascimento;
    private Double saldo;

    protected PessoaFisica() {
    }

    public PessoaFisica(String nome, String contato, String CPF, String DataNascimento, Double saldo) throws Exception {
        super(nome, contato);
        this.CPF = CPF;
        this.DataNascimento = DataNascimento;
        this.saldo = saldo;

        if (CPF == null || CPF.length() < 1) {
            throw new  DocumentoInvalidoException();
        }

        if (DataNascimento == null || DataNascimento.length() < 1) {
            throw new  DataNascimentoInvalidaException();
        }
    }

    public String getCPF() {
        return this.CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getDataNascimento() {
        return this.DataNascimento;
    }

    public void setDataNascimento(String DataNascimento) {
        this.DataNascimento = DataNascimento;
    }

    public Double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Nome: " + this.getNome());
        System.out.println("Contato: " + this.getContato());
        System.out.println("CPF: " + this.CPF);
        System.out.println("Data de Nascimento: " + this.DataNascimento);
        System.out.println("Saldo: " + this.saldo);
        System.out.println("Total de Alugeis: " + this.getTotalAlugeis());
    }
}
