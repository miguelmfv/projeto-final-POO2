package org.example.controller;

import org.example.model.Carro;
import org.example.model.Usuario;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UsuarioController {
    private SessionFactory sessionFactory;

    public UsuarioController() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void addUsuario(Usuario usuario) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(usuario);
            transaction.commit();
        }
    }

    public List<Usuario> getAllUsuarios() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Usuario", Usuario.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Usuario getUsuarioByNome(String nome) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Usuario WHERE nome = :nome", Usuario.class)
                    .setParameter("nome", nome)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Usuario getUsuarioById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Usuario.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}