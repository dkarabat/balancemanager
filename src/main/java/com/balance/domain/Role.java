package com.balance.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USER_ROLES")
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;


    @Column(name = "ROLE")
    private String role;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="ROLES",
            joinColumns = {@JoinColumn(name="ROLE_ID", referencedColumnName="ID")},
            inverseJoinColumns = {@JoinColumn(name="USER_ID", referencedColumnName="ID")}
    )
    private Set<User> userRoles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
