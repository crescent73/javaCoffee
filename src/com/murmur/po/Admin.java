package com.murmur.po;

import java.lang.reflect.Field;


public class Admin {

    private Long id; // table id
    private String adminName; // admin name
    private String adminEmail; // admin email
    private String adminPassword; // admin login password

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    /**
     * set all filed to null
     */
    public void reset() {
        Field[] fields = getClass().getDeclaredFields();
        for(Field field : fields) {
            try {
                field.set(this, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", adminName='" + adminName + '\'' +
                ", adminEmail='" + adminEmail + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                '}';
    }
}
