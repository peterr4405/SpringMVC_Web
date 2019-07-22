package stock.orm.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.SessionFactory;


public abstract class BaseDao implements IBaseDao{
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    @Transactional
    @Override
    public void create(Object o) {
        sessionFactory.getCurrentSession().save(o);
    }

    @Transactional
    @Override
    public void update(Object o) {
        sessionFactory.getCurrentSession().update(o);
    }

    @Transactional
    @Override
    public void delete(Object o) {
        sessionFactory.getCurrentSession().delete(o);
    }

    @Transactional
    @Override
    public <T> T get(Class<T> cla, Integer id) {
        return sessionFactory.getCurrentSession().get(cla, id);
    }

    @Transactional
    @Override
    public <T> List<T> queryAll(Class<T> cla) {
        List list = sessionFactory.getCurrentSession().createQuery("from "+cla.getSimpleName()).list();
        return list;
    }
    
}
