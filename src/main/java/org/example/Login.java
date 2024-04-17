package org.example;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "Logins")
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Getter
    @Setter
    @Column(name = "application")
    String application;

    @Getter
    @Setter
    @Column(name = "access_date")
    String access_date;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User user;

    public Login (String application)
    {
        this.application = application;
    }
    public Login (String application, String access_date)
    {
        this.application = application;
        this.access_date = access_date;
    }
    public Login (String application, String access_date, User user)
    {
        this.application = application;
        this.access_date = access_date;
        this.user = user;
    }
}