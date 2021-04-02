package com.cloud_lab.tema3_cloud.core.infrastructure.entity;

import javax.persistence.*;

@Entity
@Table(name="user", schema="tema3_db")
public class UserEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int id;

    @Column(name="user_first_name", length=50, nullable=false, unique=false)
    public String firstName;


    @Column(name="user_last_name", length=50, nullable=false, unique=false)
    public String lastName;


    @Column(name="user_email", length=50, nullable=false, unique=true)
    public String email;


    @Column(name="user_password", length=50, nullable=false, unique=false)
    public String password;

}
