package io.app.skillbit;

public class AppModel {
    private String name;
    private String email;
    private String phone;
    private String password;
    private String address;
    private String designation;
    private String city;
    private String empId;

    public AppModel() {
    }

    public AppModel(String name, String email, String address, String designation, String city) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.designation = designation;
        this.city = city;
    }

    public AppModel(String name, String email, String phone, String password,String empId,String city,String designation,String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password=password;
        this.designation=designation;
        this.empId=empId;
        this.city=city;
        this.address=address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }
}
