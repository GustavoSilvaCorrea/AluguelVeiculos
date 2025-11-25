package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import model.Cliente;

public class ClienteDAO {

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

    public void salvarCliente(Cliente cliente) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Cliente getClienteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Cliente.class, id);
        }
    }

    public void atualizarCliente(Cliente cliente) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deletarCliente(Cliente cliente) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Retorna todos os registros de cliente como um ArrayList.
     * 
     * @return ArrayList de clientes
     */
    public ArrayList<Cliente> getTodosClientes() {
        try (Session session = sessionFactory.openSession()) {
            Query<Cliente> query = session.createQuery("FROM Cliente", Cliente.class);
            List<Cliente> clientesList = query.list();
            return new ArrayList<>(clientesList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}