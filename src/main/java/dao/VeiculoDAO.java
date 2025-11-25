package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import model.Veiculo;

public class VeiculoDAO {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Configurando a sessão do Hibernate a partir do arquivo hibernate.cfg.xml
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void close() {
        sessionFactory.close();
    }

    public void salvarVeiculo(Veiculo veiculo) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(veiculo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Veiculo getVeiculoById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Veiculo.class, id);
        }
    }

    public void atualizarVeiculo(Veiculo veiculo) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(veiculo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deletarVeiculo(Veiculo veiculo) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(veiculo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Retorna todos os registros de veiculo como um ArrayList.
     * @return ArrayList de veiculos
     */
    public ArrayList<Veiculo> getTodosVeiculos() {
        try (Session session = sessionFactory.openSession()) {
            Query<Veiculo> query = session.createQuery("FROM Veiculo", Veiculo.class);
            List<Veiculo> veiculosList = query.list();
            
            // Converte a List para ArrayList explicitamente, se necessário
            return new ArrayList<>(veiculosList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Retorna uma lista vazia em caso de erro
        }
    }

}