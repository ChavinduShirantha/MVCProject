package lk.ijse.institute.to;

public class User {
    private String name;
    private String surName;
    private String city;
    private String contact;
    private String email;
    private String userName;
    private String password;
    private String role;

    public User(String name, String surName, String city, String contact, String email, String userName, String password, String role) {
        this.setName(name);
        this.setSurName(surName);
        this.setCity(city);
        this.setContact(contact);
        this.setEmail(email);
        this.setUserName(userName);
        this.setPassword(password);
        this.setRole(role);
    }

    public User(String user_name, String pwd) {
        this.userName=user_name;
        this.password=pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", city='" + city + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
