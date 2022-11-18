package state;

import model.entities.User;

public class AuthState {

    private static Boolean isAuthenticated = false;
    private static User userLogged = null;

    private AuthState() {
    }

    public static Boolean isAuthenticated(){
        return isAuthenticated;
    }

    public  static void setIsAuthenticated(){
        isAuthenticated = true;
    }

    public static User getUserLogged(){
        return userLogged;
    }

    public static void setUser(User user){
        userLogged = user;
    }
}
