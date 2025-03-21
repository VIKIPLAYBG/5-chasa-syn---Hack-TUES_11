package com.site.GuardianAngel.service;

import com.site.GuardianAngel.api.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> userList;

    public UserService() {
        userList = new ArrayList<>();

        // Hard coded database, subject to change
        User user = new User(1L, "Ida", "ldaij@fijoes");
        User user2 = new User(2L, "Mie", "ftbd@sjnv");
        User user3 = new User(3L, "Kay", "fbt@fdntrfn");
        User user4 = new User(4L, "Jey", "db@ngygd");
        User user5 = new User(5L, "Ivo", "erged@ghn");

        userList.addAll(Arrays.asList(user, user2, user3, user4, user5));
    }

//    public UserService(List<User> userList) {
//        this.userList = userList;
//    }

    public Optional<User> getUser(Long id) {
        Optional<User> optional = Optional.empty();
        for(User user : userList) {
            if(user.getId().equals(id)) {
                optional = Optional.of(user);
                return optional;
            }
        }
        return optional;
    }
}
