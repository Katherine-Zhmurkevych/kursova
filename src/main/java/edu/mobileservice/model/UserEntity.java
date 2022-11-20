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
    @Column(name = "surname", length = 12)
    private String phone;

    public UserEntity() {
    }

    public UserEntity(final Integer id, final String name, final String surname, final String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("\nID: %-3s Name: %-8s Surname: %-1s", id, name, surname);
    }
}
