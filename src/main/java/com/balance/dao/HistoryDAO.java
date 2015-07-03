package com.balance.dao;

import com.balance.domain.History;
import com.balance.domain.User;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 03.01.14.
 */
public interface HistoryDAO {

    public void saveHistory(History history);

    public List<User> getHistory();

    public List<User> getHistoryByDate(Date from, Date to) throws ParseException;

}
