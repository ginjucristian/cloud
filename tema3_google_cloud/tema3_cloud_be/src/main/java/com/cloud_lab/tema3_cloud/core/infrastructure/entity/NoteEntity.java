package com.cloud_lab.tema3_cloud.core.infrastructure.entity;

import javax.persistence.*;

@Entity
@Table(name="note", schema="tema3_db")
public class NoteEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int id;

    @Column(name="note_title", length=50, nullable=false, unique=false)
    public String noteTitle;


    @Column(name="note_description", length=512, nullable=false, unique=false)
    public String noteDescription;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public UserEntity user;
}
