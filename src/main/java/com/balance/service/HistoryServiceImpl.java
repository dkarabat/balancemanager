package com.balance.service;

import com.balance.dao.HistoryDAO;
import com.balance.domain.History;
import com.balance.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 03.01.14.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    HistoryDAO historyDao;

    @Override
    public void saveHistory(History history) {
        historyDao.saveHistory(history);
    }

    @Override
    public List<History> getHistory() {
        return historyDao.getHistory();
    }

    @Override
    public List<User> getHistoryByDate(Date from, Date to) throws ParseException {
        return historyDao.getHistoryByDate(from, to);
    }
}
