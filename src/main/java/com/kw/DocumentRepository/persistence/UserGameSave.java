package com.kw.DocumentRepository.persistence;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "user_game_save")
public class UserGameSave {

    public UserGameSave() {}

    @Id
    @Column(name = "game_save_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "creation_date", columnDefinition = "DATETIME")
    private Date creationDate;

    @Column(name = "last_update_date", columnDefinition = "DATETIME")
    private Date lastUpdateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setLastUpdateDate(Time lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
