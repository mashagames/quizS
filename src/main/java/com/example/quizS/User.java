package com.example.quizS;

import javax.persistence.Column;
        import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;
        import javax.persistence.Table;
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "fio")
    private String fio;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "q1")
    private String q1;

    @Column(name = "q2")
    private String q2;

    @Column(name = "q3")
    private String q3;

    public User() {
    }

    public User(String fio, String email, String  phone, String q1, String q2, String q3) {
        this.fio = fio;
        this.email = email;
        this.phone = phone;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
    }
    public String getFio() {
        return fio;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public String getQ1() {
        return q1;
    }
    public String getQ2() {
        return q2;
    }
    public String getQ3() {
        return q3;
    }

    @Override
    public String toString() {
        return "User [fio=" + fio + ", email=" + email + ", phone=" +
                phone + ", q1=" + q1 + ", q2=" + q2 + ", q3=" + q3 + "]";
    }

}
