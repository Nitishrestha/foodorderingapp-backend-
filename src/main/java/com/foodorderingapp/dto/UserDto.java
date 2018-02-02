package com.foodorderingapp.dto;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

    private int userId;

    @NotBlank(message = "Password is required!!!")
    @Size(min = 5, max = 70, message = "Password must be between 5 and 30")
    private String userPassword;
    @Size(min = 5, max = 40, message = "Email must be between 5 and 70")
    @NotBlank(message = "Email is required!!!")
    @Email
    private String email;
    @NotBlank(message = "This field is required.")
    @Size(min=3,max=50,message = "Address must be between 3 and 50.")
    private String address;
    @NotBlank(message = "First name is required!!!")
    @Size(min = 3, max = 25, message = "First name must be between 3 and 25")
    private String firstName;
    @Size(min = 2, max = 25, message = "Middle name must be between 2 and 25")
    private String middleName;
    @NotBlank(message = "Last name is required!!!")
    @Size(min = 3, max = 25, message = "Last name must be between 3 and 25")
    private String lastName;
    @NotBlank(message = "Contact number is required!!!")
    @Size(min = 7, max = 10, message = "Contact number must be between 7 and 10")
    private String contactNo;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
