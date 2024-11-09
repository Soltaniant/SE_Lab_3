package ir.selab.tdd.repository;

import ir.selab.tdd.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserRepository {
    private final Map<String, User> usersByUserName;
    private final Map<String, User> usersByEmail;

    public UserRepository(List<User> users) {
        this.usersByUserName = users.stream().collect(Collectors.toMap(User::getUsername, u -> u, (u1, u2) -> {
            throw new IllegalArgumentException("Two users can not have the same username");
        }));

        this.usersByEmail = users.stream()
                .filter(u -> u.getEmail() != null)
                .collect(Collectors.toMap(User::getEmail, u -> u, (u1, u2) -> {
            throw new IllegalArgumentException("Two users can not have the same email");
        }));
    }

    public User getUserByUsername(String username) {
        return usersByUserName.get(username);
    }

    public User getUserByEmail(String email) {
        return usersByEmail.get(email);
    }

    public boolean addUser(User user) {
        if (usersByUserName.containsKey(user.getUsername()))
            return false;

        if (user.getEmail() != null) {
            if (usersByEmail.containsKey(user.getEmail()))
                return false;
            usersByEmail.put(user.getEmail(), user);
        }

        usersByUserName.put(user.getUsername(), user);
        return true;
    }

    public boolean removeUser(String username) {
        User user = usersByUserName.get(username);
        if(user == null)
            return false;
        usersByUserName.remove(username, user);
        usersByEmail.remove(user.getEmail(), user);
        return true;
    }

    public int getUserCount() {
        return usersByUserName.size();
    }

    public boolean changeUserEmail(String username, String newEmail) {
    User user = getUserByUsername(username);
    if (user == null)
        return false;

    if (newEmail != null && usersByEmail.containsKey(newEmail))
        return false;

    usersByEmail.remove(user.getEmail());
    user.setEmail(newEmail);
    usersByEmail.put(newEmail, user);
    return true;
}


}
