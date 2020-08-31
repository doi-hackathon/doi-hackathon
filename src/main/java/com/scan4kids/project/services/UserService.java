package com.scan4kids.project.services;

import com.scan4kids.project.daos.UsersRepository;
import com.scan4kids.project.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UsersRepository usersRepository;

    public void importUsersCSV(){
        List<String> lines = null;
        User importUser = null;
        try {
            lines = Files.readAllLines(Paths.get("data", "sample-users-to-import.csv"));

            for (String line : lines) {
                System.out.println("line = " + line);
                //TODO: Now that you can read a file, separate the values by commas and build a new User object with those pieces and save it into the DB.
//                importUser = new User("fieldX","fieldY",...);
                //TODO: Consider hashing the passwords, I believe the best approach is to generate random passwords for them and let me change it in their profile page
//                usersRepository.save(importUser);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
