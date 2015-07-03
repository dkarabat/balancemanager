package com.balance.dao;

import com.balance.domain.History;
import com.balance.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Repository("historyDao")
public class HistoryDAOImpl implements HistoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveHistory(History history) {
        sessionFactory.getCurrentSession().saveOrUpdate(history);
    }

    @Override
    public List<User> getHistory() {
        List<User> historyList = sessionFactory.getCurrentSession()
                .createCriteria(User.class).list();
        return  historyList;
    }

    @Override
    public List<User> getHistoryByDate(Date from, Date to) throws ParseException {
        List events = sessionFactory.getCurrentSession().createCriteria(History.class)
                .add( Restrictions.or(
                        Restrictions.isNull("startDate"),
                        Restrictions.ge("startDate", from)))
                        .add( Restrictions.or(
                                Restrictions.isNull("endDate"),
                                Restrictions.lt("endDate", to)))
                                .list();
        return events;
    }
}
