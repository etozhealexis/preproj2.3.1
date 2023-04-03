package ru.etozhealexis.crudapplication.models;

import jakarta.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "getUsers",
                query = "SELECT u FROM User u"
        ),
        @NamedQuery(
                name = "getUser",
                query = "SELECT u FROM User u WHERE u.id = :id"
        )
})
@Entity
@Table(name = "users_crud")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column()
    private String name;

    @Column()
    private short age;

    @Column()
    private String email;

    public User() {

    }

    public User(String name, short age, String email) {
        setName(name);
        setAge(age);
        setEmail(email);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
