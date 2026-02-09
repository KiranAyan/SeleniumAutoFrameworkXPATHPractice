package com.practice.pojo;

/**
 * This class acts as a data model for Login information.
 * It is used to map JSON data into Java Objects.
 */
public class LoginData {

    // These variables must match the keys in your loginData.json file
    private String username;
    private String password;
    private String expectedError; // Optional: for negative testing
    private String company; // Add this!

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    // Default constructor (Required by Jackson/Gson libraries)
    public LoginData() {
    }

    // Getter and Setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for expectedError
    public String getExpectedError() {
        return expectedError;
    }

    public void setExpectedError(String expectedError) {
        this.expectedError = expectedError;
    }

    /**
     * Overriding toString helps in debugging. 
     * When a test fails, TestNG will print the actual data values.
     */
    @Override
    public String toString() {
        return "LoginData{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}