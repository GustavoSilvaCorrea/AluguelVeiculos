package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import model.Aluguel;

public class AluguelDAO {

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

    public void salvarAluguel(Aluguel aluguel) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(aluguel);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Aluguel getAluguelById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Aluguel.class, id);
        }
    }

    public void atualizarAluguel(Aluguel aluguel) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(aluguel);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deletarAluguel(Aluguel aluguel) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(aluguel);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Retorna todos os registros de aluguel como um ArrayList.
     * @return ArrayList de alugueis
     */
    public ArrayList<Aluguel> getTodosAlugeis() {
        try (Session session = sessionFactory.openSession()) {
            Query<Aluguel> query = session.createQuery("FROM Aluguel", Aluguel.class);
            List<Aluguel> alugueisList = query.list();
            
            // Converte a List para ArrayList explicitamente, se necessário
            return new ArrayList<>(alugueisList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Retorna uma lista vazia em caso de erro
        }
    }

}