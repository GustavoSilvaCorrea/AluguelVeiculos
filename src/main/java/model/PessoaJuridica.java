package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import exceptions.DocumentoInvalidoException;
import exceptions.EnderecoInvalidoException;
import exceptions.RazaoInvalidaException;
import exceptions.ResponsavelInvalidoException;

@Entity
@DiscriminatorValue("Pessoa_Juridica")
public class PessoaJuridica extends Cliente {
    private String CNPJ;
    private String razaoSocial;
    private String endereco;
    private String responsavel;
    private Double saldo;

    protected PessoaJuridica() {
    }

    public PessoaJuridica(String nome, String contato, String CNPJ, String razaoSocial, String endereco,
            String responsavel, Double saldo) throws Exception {
        super(nome, contato);
        this.CNPJ = CNPJ;
        this.razaoSocial = razaoSocial;
        this.endereco = endereco;
        this.responsavel = responsavel;
        this.saldo = saldo;

        if (CNPJ == null || CNPJ.length() < 1) {
            throw new DocumentoInvalidoException();
        }

        if (razaoSocial == null || razaoSocial.length() < 1) {
            throw new RazaoInvalidaException();
        }

        if (endereco == null || endereco.length() < 1) {
            throw new EnderecoInvalidoException();
        }

        if (responsavel == null || responsavel.length() < 1) {
            throw new ResponsavelInvalidoException();
        }

    }

    public String getCNPJ() {
        return this.CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getRazaoSocial() {
        return this.razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getResponsavel() {
        return this.responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
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
        System.out.println("e-mail: " + this.getContato());
        System.out.println("CNPJ: " + this.CNPJ);
        System.out.println("Razão Social: " + this.razaoSocial);
        System.out.println("Endereco: " + this.endereco);
        System.out.println("Responsável: " + this.responsavel);
        System.out.println("Total de Alugeis: " + this.getTotalAlugeis());
        System.out.println("Saldo: " + this.saldo);
    }
}
