package com.delphi.nice.training.auth;

import com.delphi.nice.training.connection.Database;
import com.delphi.nice.training.reader.UserReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoService implements UserDao {

    private final String userPath;

    public UserDaoService(@Value("${path.users}") String userPath) {
        this.userPath = userPath;
    }

    @Override
    public Optional<User> selectUserByUsername(String username) {
        return new Database().getUserByUserName(username);
    }

    private List<User> getUsers() {
        return new UserReader().getJsonArr(userPath);
    }
}
