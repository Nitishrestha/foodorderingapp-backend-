package com.foodorderingapp.model;

import com.foodorderingapp.dto.UserListMapperDto;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "tbl_users")
@SqlResultSetMapping(
        name = "UserMapping",
        classes =
                {@ConstructorResult(targetClass = UserListMapperDto.class,
                        columns = {
                                @ColumnResult(name = "order_id", type = Integer.class),
                                @ColumnResult(name = "user_id", type = Integer.class),
                                @ColumnResult(name = "first_name", type = String.class),
                                @ColumnResult(name = "middle_name", type = String.class),
                                @ColumnResult(name = "last_name", type = String.class),
                                @ColumnResult(name = "ordered_date", type = Date.class),
                        })})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, updatable = false)
    private int userId;

    @NotBlank(message = "First name is required!!!")
    @Size(min = 3, max = 25, message = "First name must be between 3 and 25")
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    @Size(min = 2, max = 25, message = "Middle name must be between 2 and 25")
    private String middleName;

    @Column(name = "last_name")
    @NotBlank(message = "Last name is required!!!")
    @Size(min = 3, max = 25, message = "Last name must be between 3 and 25")
    private String lastName;

    @Column(name = "user_password")
    @Size(min = 5, max = 30, message = "First name must be between 5 and 30")
    @NotBlank(message = "Password is required!!!")
    private String userPassword;

    @Column(name = "email")
    @Size(min = 5, max = 40, message = "Email must be between 5 and 40")
    @NotBlank(message = "Email is required!!!")
    private String email;

    @Column(name = "contact_no")
    @NotBlank(message = "Contact number is required!!!")
    @Size(min = 7, max = 10, message = "Contact number must be between 7 and 10")
    private String contactNo;

    @Column(name = "address")
    @NotBlank(message = "Address is required!!!")
    @Size(min = 3, max = 50, message = "First name must be between 3 and 50")
    private String address;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "balance")
    private double balance = 1200;

    public User(String firstName, String middleName, String lastName, String userPassword, String email, String contactNo, String address, String userRole, double balance) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.userPassword = userPassword;
        this.email = email;
        this.contactNo = contactNo;
        this.address = address;
        this.userRole = userRole;
        this.balance = balance;
    }

    public User() {

    }

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
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return email != null ? email.equals(user.email) : user.email == null;
    }
}
