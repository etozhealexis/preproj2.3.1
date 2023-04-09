package ru.etozhealexis.crudapplication.models;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@NamedQueries({
        @NamedQuery(
                name = "getUsers",
                query = "SELECT u FROM User u ORDER BY u.id"
        ),
        @NamedQuery(
                name = "getUser",
                query = "SELECT u FROM User u WHERE u.id = :id"
        ),
        @NamedQuery(
                name = "getUserByEmail",
                query = "SELECT u FROM User u WHERE u.email = :email"
        )
})
@Entity
@Table(name = "users_crud")
public class User implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(unique = true)
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 40, message = "Name should be between 2 and 40 characters")
    private String name;

    @Column()
    @Min(value = 0, message = "Age should be at least 0")
    private short age;

    @Column(unique = true)
    @Email(message = "Email should be a valid email-address")
    private String email;

    @Column()
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 4, message = "Password must contain at least 4 characters")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User() {

    }

    public User(String name, short age, String email, String password, Set<Role> roles) {
        setName(name);
        setAge(age);
        setEmail(email);
        setPassword(password);
        setRoles(roles);
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
