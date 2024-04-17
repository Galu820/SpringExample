package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.ssl.SslProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;

import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Starter {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Starter.class, args);
        UserRepo repo = ctx.getBean(UserRepo.class);
        //записать новую запись в БД
        repo.save(new User( "Ann", "Anna Guseva"));
        repo.save(new User( "Alx", "Alex Petrov"));
        repo.save(new User( "Ivn", "Ivan Ivanov"));

        // получить 1 запись и обновить её
        Optional<User> u = repo.findById(3);
        u.ifPresent(System.out::println);
        u.ifPresent(usr -> {
            usr.setUsername(usr.getUsername() + "!!!");
            repo.save(usr);
        });
        //получить все записи для "Ann"
        System.out.println("\n\n");
        System.out.println("получить все записи для \"Ann\"");
        repo.findAllByUsername("Ann").forEach(System.out::println);

        //получить все записи содержащие "e" в fio
        System.out.println("\n\n");
        System.out.println("получить все записи содержащие \"e\" в fio");
        repo.findUsersByFioContains("e").forEach(System.out::println);

        List<User> ls = (List<User>) repo.findAll();
//        //изменим коллекцию
        ls.forEach(usr->{usr.setFio("User: "+usr.getFio());});
//        //записать коллекцию в БД
        repo.saveAll(ls);


//        //получить все записи
        System.out.println("\n\n");
        System.out.println("получить все записи");
        repo.findAll().forEach(System.out::println);
//
//        //добавим новую запись через нативный SQL
        int res = repo.insertUser("BOSS", "Pushkin Alex");
        System.out.println("\n\n");
        System.out.println("+ BOSS");
        repo.findAll().forEach(System.out::println);
//
//        //обновим запись через нативный SQL
        res = repo.updateUsersSetUsernameForIdNative("Always First", 3);
        System.out.println("\n\n");
        System.out.println("обновим запись через нативный SQL: Always First");
        repo.findAll().forEach(System.out::println);

//      теперь надо прочитать файлик с логами
        System.out.println("\n\n");
        System.out.println("создаем логины");
        HashSet<Login> group1 = new HashSet();
        group1.add(new Login("Web", "01-04-2024"));
        group1.add(new Login("Phone", "31-03-2024"));
        group1.add(new Login("VTB", "30-03-2024"));
        group1.add(new Login("Phone", "29-03-2024"));

        group1.forEach(System.out::println);
        repo.findById(1).get().lgns = new HashSet<>();
        repo.findById(1).get().lgns.addAll(group1);
        repo.findAll().forEach(System.out::println);
        repo.saveAll(ls);

    }
}