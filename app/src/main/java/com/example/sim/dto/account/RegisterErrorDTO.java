package com.example.sim.dto.account;


public class RegisterErrorDTO {
    private String[] FirstName;
    private String[] LastName;
    private String[] Email;
    private String[] ImageBase64;
    private String[] Password;
    private String[] ConfirmPassword;

    public String[] getFirstName() {
        return FirstName;
    }

    public void setFirstName(String[] firstName) {
        FirstName = firstName;
    }

    public String[] getLastName() {
        return LastName;
    }

    public void setLastName(String[] lastName) {
        LastName = lastName;
    }

    public String[] getEmail() {
        return Email;
    }

    public void setEmail(String[] email) {
        Email = email;
    }

    public String[] getImageBase64() {
        return ImageBase64;
    }

    public void setImageBase64(String[] imageBase64) {
        ImageBase64 = imageBase64;
    }

    public String[] getPassword() {
        return Password;
    }

    public void setPassword(String[] password) {
        Password = password;
    }

    public String[] getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String[] confirmPassword) {
        ConfirmPassword = confirmPassword;
    }
}
