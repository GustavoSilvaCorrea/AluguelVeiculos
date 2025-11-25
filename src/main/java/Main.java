import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import dao.AluguelDAO;
import dao.ClienteDAO;
import dao.VeiculoDAO;
import exceptions.AnoInvalidoException;
import exceptions.AutonomiaInvalidaException;
import exceptions.CapacidadeInvalidaException;
import exceptions.CategoriaInvalidaException;
import exceptions.CilindradaInvalidaException;
import exceptions.ContatoInvalidoException;
import exceptions.CorInvalidaException;
import exceptions.DadosInvalidaException;
import exceptions.DataInvalidaException;
import exceptions.DataNascimentoInvalidaException;
import exceptions.DiariaInvalidaException;
import exceptions.DocumentoInvalidoException;
import exceptions.EnderecoInvalidoException;
import exceptions.ModeloInvalidoException;
import exceptions.NomeInvalidoException;
import exceptions.PlacaInvalidaException;
import exceptions.PortasInvalidaException;
import exceptions.RazaoInvalidaException;
import exceptions.ResponsavelInvalidoException;
import exceptions.StatusInvalidoException;
import exceptions.TipoInvalidoException;
import model.Aluguel;
import model.Carro;
import model.Cliente;
import model.Moto;
import model.PessoaFisica;
import model.PessoaJuridica;
import model.Veiculo;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ClienteDAO clienteDAO = new ClienteDAO();
        ArrayList<Cliente> clientes = clienteDAO.getTodosClientes();

        VeiculoDAO veiculoDAO = new VeiculoDAO();
        ArrayList<Veiculo> veiculos = veiculoDAO.getTodosVeiculos();

        AluguelDAO aluguelDAO = new AluguelDAO();
        ArrayList<Aluguel> alugueis = aluguelDAO.getTodosAlugeis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        int opcaoPrincipal;

        do {
            System.out.println("===== SISTEMA DE LOCAÇÃO =====");
            System.out.println("1 - Clientes");
            System.out.println("2 - Veículos");
            System.out.println("3 - Aluguéis");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcaoPrincipal = sc.nextInt();
            sc.nextLine();

            switch (opcaoPrincipal) {

                // ==================================================================
                // CLIENTES
                // ==================================================================
                case 1:
                    int opCliente;
                    do {
                        System.out.println("\n===== MENU CLIENTES =====");
                        System.out.println("1 - Cadastrar Pessoa Física");
                        System.out.println("2 - Cadastrar Pessoa Jurídica");
                        System.out.println("3 - Listar Clientes");
                        System.out.println("0 - Voltar");
                        System.out.print("Escolha: ");
                        opCliente = sc.nextInt();
                        sc.nextLine();

                        switch (opCliente) {
                            case 1:
                                System.out.print("Nome: ");
                                String nomePF = sc.nextLine();
                                System.out.print("Contato: ");
                                String contPF = sc.nextLine();
                                System.out.print("CPF: ");
                                String cpf = sc.nextLine();
                                System.out.print("Data de nascimento: ");
                                String nasc = sc.nextLine();
                                System.out.print("Saldo: ");
                                double saldoPF = sc.nextDouble();
                                sc.nextLine();

                                try {
                                    PessoaFisica PS1 = new PessoaFisica(nomePF, contPF, cpf, nasc, saldoPF);
                                    clientes.add(PS1);
                                    clienteDAO.salvarCliente(PS1);

                                    System.out.println("Pessoa física cadastrada!\n");
                                } catch (NomeInvalidoException | ContatoInvalidoException | DocumentoInvalidoException
                                        | DataNascimentoInvalidaException e) {
                                    System.out.println(e.getMessage());
                                } catch (Exception e) {
                                    System.out.println("Erro Desconhecido" + e.getMessage());
                                }
                                break;

                            case 2:
                                System.out.print("Nome: ");
                                String nomePJ = sc.nextLine();
                                System.out.print("Contato: ");
                                String contPJ = sc.nextLine();
                                System.out.print("CNPJ: ");
                                String cnpj = sc.nextLine();
                                System.out.print("Razão Social: ");
                                String rz = sc.nextLine();
                                System.out.print("Endereço: ");
                                String endPJ = sc.nextLine();
                                System.out.print("Responsável: ");
                                String resp = sc.nextLine();
                                System.out.print("Saldo: ");
                                double saldoPJ = sc.nextDouble();
                                sc.nextLine();

                                try {
                                    PessoaJuridica PJ1 = new PessoaJuridica(nomePJ, contPJ, cnpj, rz, endPJ, resp,
                                            saldoPJ);
                                    clientes.add(PJ1);
                                    clienteDAO.salvarCliente(PJ1);

                                    System.out.println("Pessoa jurídica cadastrada!\n");
                                } catch (NomeInvalidoException | ContatoInvalidoException | DocumentoInvalidoException
                                        | RazaoInvalidaException | EnderecoInvalidoException
                                        | ResponsavelInvalidoException e) {
                                    System.out.println(e.getMessage());
                                } catch (Exception e) {
                                    System.out.println("Erro Desconhecido" + e.getMessage());
                                }
                                break;

                            case 3:
                                System.out.println("===== LISTA DE CLIENTES =====");
                                for (Cliente c : clientes) {
                                    c.exibirInfo();
                                    System.out.println("---------------------");
                                }
                                break;

                            case 0:
                                break;

                            default:
                                System.out.println("Opção inválida!");
                        }

                    } while (opCliente != 0);
                    break;

                // ==================================================================
                // VEÍCULOS
                // ==================================================================
                case 2:
                    int opVeiculo;
                    do {
                        System.out.println("\n===== MENU VEÍCULOS =====");
                        System.out.println("1 - Cadastrar Carro");
                        System.out.println("2 - Cadastrar Moto");
                        System.out.println("3 - Listar Veículos");
                        System.out.println("0 - Voltar");
                        System.out.print("Escolha: ");
                        opVeiculo = sc.nextInt();
                        sc.nextLine();

                        switch (opVeiculo) {

                            case 1:
                                System.out.print("Modelo: ");
                                String modC = sc.nextLine();
                                System.out.print("Ano: ");
                                int anoC = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Placa: ");
                                String placaC = sc.nextLine();
                                System.out.print("Cor: ");
                                String corC = sc.nextLine();
                                System.out.print("Diária: ");
                                float diariaC = sc.nextFloat();
                                sc.nextLine();
                                System.out.print("Status: ");
                                String statusC = sc.nextLine();
                                System.out.print("Portas: ");
                                int portas = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Combustível: ");
                                String comb = sc.nextLine();
                                System.out.print("Bagagem (L): ");
                                int bag = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Categoria: ");
                                String cat = sc.nextLine();

                                try {
                                    Carro CR1 = new Carro(modC, anoC, placaC, corC, diariaC, statusC, portas, comb, bag,
                                            cat);
                                    veiculos.add(CR1);
                                    veiculoDAO.salvarVeiculo(CR1);

                                    System.out.println("Carro cadastrado!\n");
                                } catch (ModeloInvalidoException | AnoInvalidoException | PlacaInvalidaException
                                        | CorInvalidaException | DiariaInvalidaException | StatusInvalidoException
                                        | PortasInvalidaException | CapacidadeInvalidaException
                                        | CategoriaInvalidaException e) {
                                    System.out.println(e.getMessage());
                                } catch (Exception e) {
                                    System.out.println("Erro desconhecido: " + e.getMessage());
                                }

                                break;

                            case 2:
                                System.out.print("Modelo: ");
                                String modM = sc.nextLine();
                                System.out.print("Ano: ");
                                int anoM = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Placa: ");
                                String placaM = sc.nextLine();
                                System.out.print("Cor: ");
                                String corM = sc.nextLine();
                                System.out.print("Diária: ");
                                float diariaM = sc.nextFloat();
                                sc.nextLine();
                                System.out.print("Status: ");
                                String statusM = sc.nextLine();
                                System.out.print("Cilindrada: ");
                                int cc = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Tipo: ");
                                String tipo = sc.nextLine();
                                System.out.print("Autonomia: ");
                                int auto = sc.nextInt();
                                sc.nextLine();

                                try {
                                    Moto MT1 = new Moto(modM, anoM, placaM, corM, diariaM, statusM, cc, tipo, auto);
                                    veiculos.add(MT1);
                                    veiculoDAO.salvarVeiculo(MT1);

                                    System.out.println("Moto cadastrada!\n");
                                } catch (ModeloInvalidoException | AnoInvalidoException | PlacaInvalidaException
                                        | CorInvalidaException | DiariaInvalidaException | StatusInvalidoException
                                        | CilindradaInvalidaException | TipoInvalidoException
                                        | AutonomiaInvalidaException e) {
                                    System.out.println(e.getMessage());
                                } catch (Exception e) {
                                    System.out.println("Erro Desconhecido: " + e.getMessage());
                                }

                                break;

                            case 3:
                                System.out.println("===== LISTA DE VEÍCULOS =====");
                                for (Veiculo v : veiculos) {
                                    v.exibirVeiculo();
                                    System.out.println("---------------------");
                                }
                                break;

                            case 0:
                                break;

                            default:
                                System.out.println("Opção inválida!");
                        }
                    } while (opVeiculo != 0);
                    break;

                // ==================================================================
                // ALUGUÉIS
                // ==================================================================
                case 3:
                    int opAluguel;

                    do {
                        System.out.println("\n===== MENU ALUGUÉIS =====");
                        System.out.println("1 - Criar Aluguel");
                        System.out.println("2 - Listar Aluguéis");
                        System.out.println("0 - Voltar");
                        System.out.print("Escolha: ");
                        opAluguel = sc.nextInt();
                        sc.nextLine();

                        switch (opAluguel) {

                            case 1:
                                if (clientes.isEmpty() || veiculos.isEmpty()) {
                                    System.out.println("Cadastre pelo menos 1 cliente e 1 veículo!");
                                    break;
                                }

                                System.out.println("Escolha o cliente:");
                                for (int i = 0; i < clientes.size(); i++) {
                                    System.out.println(i + " - " + clientes.get(i).getNome());
                                }
                                int idxC = sc.nextInt();
                                sc.nextLine();

                                System.out.println("Escolha o veículo:");
                                for (int i = 0; i < veiculos.size(); i++) {
                                    System.out.println(i + " - " + veiculos.get(i).getModelo());
                                }
                                int idxV = sc.nextInt();
                                sc.nextLine();

                                System.out.print("Data início (dd/MM/yyyy): ");
                                String di = sc.nextLine();

                                System.out.print("Data fim (dd/MM/yyyy): ");
                                String df = sc.nextLine();

                                System.out.print("Status: ");
                                String statusA = sc.nextLine();

                                try {
                                    Date dataInicio = sdf.parse(di);
                                    Date dataFim = sdf.parse(df);

                                    Aluguel AL1 = new Aluguel(clientes.get(idxC), veiculos.get(idxV), dataInicio, dataFim, statusA);
                                    alugueis.add(AL1);
                                    aluguelDAO.salvarAluguel(AL1);

                                    System.out.println("Aluguel cadastrado!\n");
                                } catch (DadosInvalidaException | DataInvalidaException | StatusInvalidoException e) {
                                    System.out.println(e.getMessage());
                                } catch (Exception e) {
                                    System.out.println("Erro Desonhecido: " + e.getMessage());
                                }

                                break;

                            case 2:
                                System.out.println("===== LISTA DE ALUGUÉIS =====");
                                for (Aluguel a : alugueis) {
                                    a.exibirAluguel();
                                    System.out.println("---------------------");
                                }
                                break;

                            case 0:
                                break;

                            default:
                                System.out.println("Opção inválida!");
                        }

                    } while (opAluguel != 0);
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcaoPrincipal != 0);

        sc.close();
    }
}
