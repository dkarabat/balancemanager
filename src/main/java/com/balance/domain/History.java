package com.balance.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "HISTORY")
public class History {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "ADMIN_NAME")
	private String admin_name;

    @Column(name = "USER_NAME")
	private String user_name;

    @Column(name = "UPDATE_DATE")
    private Date update_date;

    @Column(name = "SUMM")
    private Double summ;

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public Double getSumm() {
        return summ;
    }

    public void setSumm(Double summ) {
        this.summ = summ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
