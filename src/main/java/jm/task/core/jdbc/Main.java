package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        //try (Statement statement =new Util().getConnection().createStatement()){
        UserServiceImpl userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();

        userService.saveUser("Tom", "Ford", (byte) 25);
        System.out.println("User с именем – Tom добавлен в базу данных ");

        userService.saveUser("Mike", "Foo", (byte) 48);
        System.out.println("User с именем – Mike добавлен в базу данных ");

        userService.saveUser("Yan", "Cardiant", (byte) 59);
        System.out.println("User с именем – Yan добавлен в базу данных ");

        userService.saveUser("Akter", "Artisi", (byte) 18);
        System.out.println("User с именем – Akter добавлен в базу данных ");

        userService.removeUserById(1);
//
        userService.getAllUsers().stream().forEach(m -> System.out.println(m.toString()));
////
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }

}
