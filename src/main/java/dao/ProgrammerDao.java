package dao;

import entities.Programmer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Log4j
@Component
@RequiredArgsConstructor
public class ProgrammerDao implements Dao<Programmer> {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Programmer> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Programmer> cr = cb.createQuery(Programmer.class);
            Root<Programmer> root = cr.from(Programmer.class);
            cr.select(root);

            Query<Programmer> query = session.createQuery(cr);
            return query.getResultList();
        }
    }

    @Override
    public List<Programmer> get(String surname) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Programmer> cr = cb.createQuery(Programmer.class);
            Root<Programmer> root = cr.from(Programmer.class);
            cr.select(root).where(cb.like(root.get("surName"), surname));
            Query<Programmer> query = session.createQuery(cr);
            return query.getResultList();
        }
    }

    @Override
    public void delete(String surname) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<Programmer> criteriaDelete = cb.createCriteriaDelete(Programmer.class);
            Root<Programmer> root = criteriaDelete.from(Programmer.class);
            criteriaDelete.where(cb.like(root.get("surName"), surname));

            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaDelete).executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void update(Programmer programmer) {
        List<Programmer> oldProgramers = get(programmer.getSurName());
        oldProgramers
                .forEach((a) -> {
                    a.setSurName(programmer.getSurName());
                    a.setName(programmer.getName());
                    a.setTask(programmer.getTask());
                    Session session = sessionFactory.openSession();
                    Transaction transaction = session.beginTransaction();
                    session.merge(a);
                    transaction.commit();
                    sessionFactory.openSession().close();
                });
    }

    public void save(Programmer programmer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(programmer);
            session.getTransaction().commit();
        }
    }
}
