package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import exceptions.CilindradaInvalidaException;
import exceptions.TipoInvalidoException;

@Entity
@DiscriminatorValue("Moto")
public class Moto extends Veiculo {
    private Integer cilindrada;
    private String tipo;              // Street, Esportiva, Custom...
    private Integer autonomia;        // km

    protected Moto(){}

    public Moto(String modelo, Integer ano, String placa, String cor, Float diaria, String status,
                Integer cilindrada, String tipo, Integer autonomia) throws Exception {

        super(modelo, ano, placa, cor, diaria, status);
        this.cilindrada = cilindrada;
        this.tipo = tipo;
        this.autonomia = autonomia;
        
        if (cilindrada <= 0 || cilindrada > 2500) {
            throw new CilindradaInvalidaException();
        }

        if (tipo == null || tipo.length() < 1) {
            throw new TipoInvalidoException();
        }
    }

    public Integer getCilindrada() {
        return this.cilindrada;
    }

    public void setCilindrada(Integer cilindrada) {
        this.cilindrada = cilindrada;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getAutonomia() {
        return this.autonomia;
    }

    public void setAutonomia(Integer autonomia) {
        this.autonomia = autonomia;
    }

    @Override
    public void exibirVeiculo() {
        System.out.println("Modelo: " + this.getModelo());
        System.out.println("Ano: " + this.getAno());
        System.out.println("Placa: " + this.getPlaca());
        System.out.println("Cor: " + this.getCor());
        System.out.println("Cilindrada: " + this.cilindrada + "cc");
        System.out.println("Tipo: " + this.tipo);
        System.out.println("Autonomia: " + this.autonomia + " km");
        System.out.println("Valor base diária: R$ " + this.getDiaria());
    }
}
