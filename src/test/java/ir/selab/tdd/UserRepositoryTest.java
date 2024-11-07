package ir.selab.tdd;

import ir.selab.tdd.domain.User;
import ir.selab.tdd.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UserRepositoryTest {
    private UserRepository repository;

    @Before
    public void setUp() {
        List<User> userList = Arrays.asList(
                new User("admin", "1234"),
                new User("ali", "qwert"),
                new User("mohammad", "123asd"));
        repository = new UserRepository(userList);
    }

    @Test
    public void getContainingUser__ShouldReturn() {
        User ali = repository.getUserByUsername("ali");
        assertNotNull(ali);
        assertEquals("ali", ali.getUsername());
        assertEquals("qwert", ali.getPassword());
    }

    @Test
    public void getNotContainingUser__ShouldReturnNull() {
        User user = repository.getUserByUsername("reza");
        assertNull(user);
    }

    @Test
    public void createRepositoryWithDuplicateUsers__ShouldThrowException() {
        User user1 = new User("ali", "1234");
        User user2 = new User("ali", "4567");
        assertThrows(IllegalArgumentException.class, () -> {
            new UserRepository(List.of(user1, user2));
        });
    }

    @Test
    public void createRepository_WhenUsersHaveDuplicateEmails_ShouldThrowException() {
        String email = "email";
        User user1 = new User("user1", "1234", email);
        User user2 = new User("user2", "4567", email);
        assertThrows(IllegalArgumentException.class, () -> {
            new UserRepository(List.of(user1, user2));
        });
    }

    @Test
    public void addNewUser__ShouldIncreaseUserCount() {
        int oldUserCount = repository.getUserCount();

        // Given
        String username = "reza";
        String password = "123abc";
        String email = "reza@sharif.edu";
        User newUser = new User(username, password);

        // When
        repository.addUser(newUser);

        // Then
        assertEquals(oldUserCount + 1, repository.getUserCount());
    }

    @Test
    public void addNewUser__ShouldAddToUsernamesMap() {
        String username = "reza";
        String password = "123abc";
        User newUser = new User(username, password);

        repository.addUser(newUser);

        assertEquals(newUser, repository.getUserByUsername(username));
    }

    @Test
    public void addNewUser__ShouldAddToEmailsMap() {
        String username = "reza";
        String password = "123abc";
        String email = "email@gmail.com";
        User newUser = new User(username, password);
        newUser.setEmail(email);

        repository.addUser(newUser);

        assertEquals(newUser, repository.getUserByEmail(email));
    }

    @Test
    public void addUser_WhenUsernameIsDuplicate_ShouldFail() {
        String username = "reza";
        String password = "123abc";
        User user1 = new User(username, password);
        User user2 = new User(username, password);

        assertTrue(repository.addUser(user1));
        assertFalse(repository.addUser(user2));
    }

    @Test
    public void addUser_WhenEmailIsDuplicate_ShouldFail() {
        String password = "123abc";
        String email = "duplicated@gmail.com";
        User user1 = new User("user1", password);
        User user2 = new User("user2", password);
        user1.setEmail(email);
        user2.setEmail(email);

        assertTrue(repository.addUser(user1));
        assertFalse(repository.addUser(user2));
    }
}
