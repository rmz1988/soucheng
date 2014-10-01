package com.soucheng.vo;

import java.io.Serializable;

/**
 * 用户
 *
 * @author lichen
 */
public class User implements Serializable {

    private static final long serialVersionUID = 3556592383866291445L;

    private int id;
    private String username;
    private String password;
    private int gold;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gold=" + gold +
                '}';
    }
}
