package edu.mobileservice.model;

import edu.mobileservice.model.annotations.Column;
import edu.mobileservice.model.annotations.PrimaryKey;
import edu.mobileservice.model.annotations.Table;

@Table(name = "user")
public class UserEntity {

    @PrimaryKey
    @Column(name = "id")
    private Integer id;
    @Column(name = "name", length = 45)
    private String name;
    @Column(name = "surname", length = 45)
    private String surname;

    public UserEntity() {
    }

    public UserEntity(Integer id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return String.format("\nID: %-3s Name: %-8s Surname: %-1s", id, name, surname);
    }
}
