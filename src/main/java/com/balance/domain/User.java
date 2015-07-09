package com.balance.domain;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int user_id;

	@Column(name = "USERNAME")
    @NotNull
	private String username;

    @Column(name = "PASSWORD")
    @NotNull
	private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "REG_DATE")
    private Date reg_date;

    @Column(name = "BALANCE")
    @NotNull
    private Double balance =0.0;

    @Column(name = "ENABLED")
    @NotNull
    private Boolean enabled;


    @OneToOne(cascade=CascadeType.ALL)
    @JoinTable(name="roles",
            joinColumns = {@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns = {@JoinColumn(name="ROLE_ID", referencedColumnName="ID")}
    )
    private Role role;



    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public Double getBalance() {
        return balance;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
