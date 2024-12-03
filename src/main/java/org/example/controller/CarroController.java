package org.example.controller;

import org.example.model.Carro;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CarroController {
    public void addCarro(Carro carro) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(carro);
            transaction.commit();
        }
    }

    public void updateCarro(Carro carro) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(carro);
            transaction.commit();
        }
    }

    public void deleteCarro(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Carro carro = session.get(Carro.class, id);
            if (carro != null) {
                session.delete(carro);
            }
            transaction.commit();
        }
    }

    public Carro getCarroById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Carro.class, id);
        }
    }

    public List<Carro> getAllCarros() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Carro> query = session.createQuery("from Carro", Carro.class);
            return query.getResultList();
        }
    }
}