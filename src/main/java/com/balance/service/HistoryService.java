package com.balance.service;


import com.balance.domain.History;
import com.balance.domain.User;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface HistoryService {

    public void saveHistory(History history);

    public List<History> getHistory();

    public List<History> getHistoryByDate(String from, String to);
}
