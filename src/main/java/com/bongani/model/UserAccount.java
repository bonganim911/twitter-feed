package com.bongani.model;

import java.util.Set;

public class UserAccount implements Comparable<UserAccount>{
    private String name;
    private Set<String> followers;

    public UserAccount() {
    }

    public UserAccount(String name, Set<String> followers) {
        this.name = name;
        this.followers = followers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<String> followers) {
        this.followers = followers;
    }


    @Override
    public int compareTo(UserAccount userAccount) {
        return name.compareToIgnoreCase(userAccount.name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserAccount{");
        sb.append("name='").append(name).append('\'');
        sb.append(", followers=").append(followers);
        sb.append('}');
        return sb.toString();
    }
}
