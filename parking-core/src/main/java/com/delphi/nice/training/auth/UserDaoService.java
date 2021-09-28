package com.delphi.nice.training.auth;

import com.delphi.nice.training.reader.UserReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserDaoService extends JdbcDaoSupport implements UserDao {

    private final String userPath;
    private final DataSource dataSource;

    public UserDaoService(@Value("${path.users}") String userPath, @Autowired DataSource dataSource) {
        this.userPath = userPath;
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public Optional<User> selectUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        return Optional.of(Objects.requireNonNull(Objects.requireNonNull(getJdbcTemplate()).queryForObject(sql, new Object[]{username}, new UserMapper())));
    }

    private List<User> getUsers() {
        return new UserReader().getJsonArr(userPath);
    }
}
