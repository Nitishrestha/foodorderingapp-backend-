package com.foodorderingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foodorderingapp.dto.UserListDto;
import com.foodorderingapp.dto.UserListMapperDto;
import com.sun.istack.internal.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="tbl_users")
@SqlResultSetMapping(
        name="UserMapping",
        classes =
                {@ConstructorResult(targetClass = UserListMapperDto.class,
                        columns = {
                                @ColumnResult(name="order_id", type=Integer.class),
                                @ColumnResult(name="user_id", type=Integer.class),
                                @ColumnResult(name="first_name",type=String.class),
                                @ColumnResult(name="middle_name",type=String.class),
                                @ColumnResult(name="last_name" ,type=String.class),
                                @ColumnResult(name="ordered_date",type=Date.class),
                        })})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;
    @Column(name="first_name")
    private String firstName;
    @Column(name="middle_name")
    private String middleName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="user_password")
    private String userPassword;
    @Column(name="email")
    private String email;
    @Column(name="contact_no")
    private String contactNo;
    @Column(name="address")
    private String address;
    @Column(name="user_role")
    private String userRole;
    @Column(name="balance")
    private double balance;



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return email != null ? email.equals(user.email) : user.email == null;
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }
}
