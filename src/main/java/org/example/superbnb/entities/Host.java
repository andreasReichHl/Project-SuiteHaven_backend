package org.example.superbnb.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

@Entity
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String hostName;

    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany
    private List<HolidayFlat> holidayFlats;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotBlank String getHostName() {
        return hostName;
    }

    public void setHostName(@NotBlank String hostName) {
        this.hostName = hostName;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String password){
        return BCrypt.checkpw(password, this.password);
    }
}
