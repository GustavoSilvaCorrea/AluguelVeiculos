package model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import exceptions.AnoInvalidoException;
import exceptions.CorInvalidaException;
import exceptions.DiariaInvalidaException;
import exceptions.DocumentoInvalidoException;
import exceptions.PlacaInvalidaException;
import exceptions.StatusInvalidoException;
import interfaces.VeiculoEmprestavel;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_veiculo")
public abstract class Veiculo implements VeiculoEmprestavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;
    private Integer ano;
    private String placa;
    private String cor;
    private Float diaria;
    private String status;

    public abstract void exibirVeiculo();

    protected Veiculo() {
    }

    public Veiculo(String modelo, Integer ano, String placa, String cor, Float diaria, String status) throws Exception {
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.cor = cor;
        this.status = status;
        this.diaria = diaria;

        if (modelo == null || modelo.length() < 1) {
            throw new DocumentoInvalidoException();
        }

        if (ano == null || ano < 1900) {
            throw new AnoInvalidoException();
        }

        if (placa == null || placa.length() < 1) {
            throw new PlacaInvalidaException();
        }

        if (cor == null || cor.length() < 1) {
            throw new CorInvalidaException();
        }

        if (diaria == null || ano < 0) {
            throw new DiariaInvalidaException();
        }

        if (status == null || status.length() < 1) {
            throw new StatusInvalidoException();
        }
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return this.ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return this.cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Float getDiaria() {
        return this.diaria;
    }

    public void setDiaria(Float diaria) {
        this.diaria = diaria;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
