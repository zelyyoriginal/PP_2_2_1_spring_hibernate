package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public User getUserByCarSeriesAndModel(int carSeries, String model) {
        List<User> a = sessionFactory.getCurrentSession()
                .createQuery("from User us where us.car.series = :carSeries and us.car.model = :model")
                .setParameter("carSeries", carSeries)
                .setParameter("model", model).getResultList();

        return a.get(0);
    }

}
