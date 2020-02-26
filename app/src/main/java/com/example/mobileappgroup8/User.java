package com.example.mobileappgroup8;

/**
 * Class includes user's details
 * @author Kerttuli
 */

public class User {

    private String name;
    private String email;

    /**
     *
     * @param name
     * @param email
     */

    /**
     *
     * @param name
     * @param email
     */

    public User (String name, String email){
        this.name = name;
        this.email = email;
    }

    /**
     *
     * @return
     */

    public String getName(){
        return this.name;
    }

    /**
     *
     * @return
     */

    public String getEmail(){
        return this.email;
    }

    public String toString(){
        return this.name + this.email;
    }



}
