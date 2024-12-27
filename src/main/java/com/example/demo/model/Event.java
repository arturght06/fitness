package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Date eventDate;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }
}
