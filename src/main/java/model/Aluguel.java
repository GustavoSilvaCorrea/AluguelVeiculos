package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import exceptions.DadosInvalidaException;
import exceptions.DataInvalidaException;
import exceptions.StatusInvalidoException;

@Entity
@DiscriminatorColumn(name = "tipo_aluguel")
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Veiculo veiculoEmprestavel;

    @ManyToOne
    private Cliente tomadorAluguel;

    private Date dataInicio;
    private Date dataFim;
    private String status;
    private float valorTotal;

    protected Aluguel() {
    }

    public Aluguel(Cliente tomadorAluguel, Veiculo veiculoEmprestavel, Date dataInicio,
            Date dataFim,
            String status) throws Exception {
        this.tomadorAluguel = tomadorAluguel;
        this.veiculoEmprestavel = veiculoEmprestavel;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;

        if (tomadorAluguel == null) {
            throw new DadosInvalidaException();
        }

        if (veiculoEmprestavel == null) {
            throw new DadosInvalidaException();
        }

        if (dataInicio == null) {
            throw new DataInvalidaException();
        }

        if (dataFim == null) {
            throw new DataInvalidaException();
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

    public Cliente getTomadorAluguel() {
        return this.tomadorAluguel;
    }

    public void setTomadorAluguel(Cliente tomadorAluguel) {
        this.tomadorAluguel = tomadorAluguel;
    }

    public Veiculo getVeiculoEmprestavel() {
        return this.veiculoEmprestavel;
    }

    public void setVeiculoEmprestavel(Veiculo veiculoEmprestavel) {
        this.veiculoEmprestavel = veiculoEmprestavel;
    }

    public Date getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return this.dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public float getValorTotal() {
        return this.valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void exibirAluguel() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Data de início: " + sdf.format(this.dataInicio));
        System.out.println("Data de fim: " + sdf.format(this.dataFim));
        System.out.println("Status: " + this.status);

        System.out.println("\nCliente vinculado:");
        this.tomadorAluguel.exibirInfo();

        System.out.println("\nVeículo vinculado:");
        this.veiculoEmprestavel.exibirVeiculo();
    }
}
