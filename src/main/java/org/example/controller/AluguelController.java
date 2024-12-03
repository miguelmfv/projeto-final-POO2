package org.example.controller;

import org.example.model.Aluguel;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AluguelController {

    public void inserirAluguel(Aluguel aluguel) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(aluguel);
            transaction.commit();
        }
    }

    public void atualizarAluguel(Aluguel aluguel) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(aluguel);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluirAluguel(Aluguel aluguel) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(aluguel);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Aluguel> getAllAlugueis() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Aluguel> query = session.createQuery("FROM Aluguel", Aluguel.class);
            return query.getResultList();
        }
    }

    public Aluguel getAluguelById(Long aluguelId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Aluguel.class, aluguelId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}