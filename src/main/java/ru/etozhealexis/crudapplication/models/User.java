package ru.etozhealexis.crudapplication.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@NamedQueries({
        @NamedQuery(
                name = "getUsers",
                query = "SELECT u FROM User u ORDER BY u.id"
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
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 40, message = "Name should be between 2 and 40 characters")
    private String name;

    @Column()
    @Min(value = 0, message = "Age should be at least 0")
    private short age;

    @Column()
    @Email(message = "Email should be a valid email-address")
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
