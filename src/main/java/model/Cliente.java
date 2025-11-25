package model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import exceptions.ContatoInvalidoException;
import exceptions.NomeInvalidoException;
import interfaces.TomadorAluguel;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_cliente")
public abstract class Cliente implements TomadorAluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String contato;
    private Integer totalAlugeis;

    public abstract void exibirInfo();

    protected Cliente() {
    }

    public Cliente(String nome, String contato) throws Exception {
        this.nome = nome;
        this.contato = contato;

        if (nome == null || nome.length() < 1) {
            throw new  NomeInvalidoException();
        }

        if (contato == null || contato.length() < 1) {
            throw new  ContatoInvalidoException();
        }
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return this.contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Integer getTotalAlugeis() {
        return this.totalAlugeis;
    }

    public void setTotalAlugeis(Integer totalAlugeis) {
        this.totalAlugeis = totalAlugeis;
    }

}
