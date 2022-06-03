/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannh.student;

import java.io.Serializable;

/**
 *
 * @author HuuToan
 */
public class StudentDTO implements Serializable{
    private String id;
    private String sClass;
    private String fullName;
    private String address;
    private String sex;
    private String status;
    private String firstName;
    private String middleName;
    private String lastName;
    private String password;

    public StudentDTO() {
    }

    public StudentDTO(String id, String sClass, String fullName, String address, String sex, String status) {
        this.id = id;
        this.sClass = sClass;
        this.fullName = fullName;
        this.address = address;
        this.sex = sex;
        this.status = status;
    }

    public StudentDTO(String id, String sClass, String fullName, String address, String sex, String status, 
            String firstName, String middleName, String lastName, String password) {
        this.id = id;
        this.sClass = sClass;
        this.fullName = fullName;
        this.address = address;
        this.sex = sex;
        this.status = status;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StudentDTO{" + "id=" + id + ", sClass=" + sClass + ", fullName=" + fullName + ", address=" + address + ", sex=" + sex + ", status=" + status + '}';
    }
    
    
}
