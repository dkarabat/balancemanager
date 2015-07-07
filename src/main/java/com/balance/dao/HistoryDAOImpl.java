package com.balance.dao;

import com.balance.domain.History;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository("historyDao")
public class HistoryDAOImpl implements HistoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private static Logger log = LoggerFactory.getLogger(HistoryDAOImpl.class);

    @Override
    public void saveHistory(History history) {
        sessionFactory.getCurrentSession().saveOrUpdate(history);
    }

    @Override
    public List<History> getHistory() {
        List<History> historyList = sessionFactory.getCurrentSession()
                .createCriteria(History.class).list();
        log.info("history size: {}", historyList.size());
        return historyList;
    }

    @Override
    public List<History> getHistoryByDate(String from, String to) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(History.class);
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse(from);
            endDate = format.parse(to);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        criteria.add(Restrictions.between("update_date", startDate, endDate));
        List<History> histories = criteria.list(); // It works fine now
        log.info("history size = {}", histories.size());
        return histories;
    }
}
