package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import exceptions.CapacidadeInvalidaException;
import exceptions.CategoriaInvalidaException;
import exceptions.PortasInvalidaException;

@Entity
@DiscriminatorValue("Carro")
public class Carro extends Veiculo {
    private Integer portas;
    private String tipoCombustivel;      // Gasolina, Diesel, Álcool, Elétrico...
    private Integer capacidadeBagagem;   // litros
    private String categoria;            // SUV, Sedan, Hatch...

    protected Carro(){}

    public Carro(String modelo, Integer ano, String placa, String cor, Float diaria, String status,
                 Integer portas, String tipoCombustivel, Integer capacidadeBagagem, String categoria) throws Exception {

        super(modelo, ano, placa, cor, diaria, status);
        this.portas = portas;
        this.tipoCombustivel = tipoCombustivel;
        this.capacidadeBagagem = capacidadeBagagem;
        this.categoria = categoria;
        
        if (portas < 2 || portas > 4) {
            throw new PortasInvalidaException();
        }

        if (capacidadeBagagem <= 0 || capacidadeBagagem > 500) {
            throw new CapacidadeInvalidaException();
        }

        if (categoria == null || categoria.length() < 1) {
            throw new CategoriaInvalidaException();
        }
    }

    public Integer getPortas() {
        return this.portas;
    }

    public void setPortas(Integer portas) {
        this.portas = portas;
    }

    public String getTipoCombustivel() {
        return this.tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public Integer getCapacidadeBagagem() {
        return this.capacidadeBagagem;
    }

    public void setCapacidadeBagagem(Integer capacidadeBagagem) {
        this.capacidadeBagagem = capacidadeBagagem;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public void exibirVeiculo() {
        System.out.println("Modelo: " + this.getModelo());
        System.out.println("Ano: " + this.getAno());
        System.out.println("Placa: " + this.getPlaca());
        System.out.println("Cor: " + this.getCor());
        System.out.println("Portas: " + this.portas);
        System.out.println("Combustível: " + this.tipoCombustivel);
        System.out.println("Capacidade de bagagem: " + this.capacidadeBagagem + " litros");
        System.out.println("Categoria: " + this.categoria);
        System.out.println("Valor base da diária: R$ " + this.getDiaria());
    }
}
