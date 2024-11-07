package ir.selab.tdd;

import ir.selab.tdd.repository.UserRepository;
import ir.selab.tdd.service.UserService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserServiceTest {
    private UserService userService;

    @Before
    public void setUp() {
        UserRepository userRepository = new UserRepository(List.of());
        userService = new UserService(userRepository);
        userService.registerUser("admin", "1234", "admin@gmail.com");
        userService.registerUser("ali", "qwert");
    }

    @Test
    public void registerNewUser__ShouldSuccess() {
        String username = "reza";
        String password = "123abc";
        boolean b = userService.registerUser(username, password);
        assertTrue(b);
    }

    @Test
    public void registerDuplicateUser__ShouldFail() {
        String username = "ali";
        String password = "123abc";
        boolean b = userService.registerUser(username, password);
        assertFalse(b);
    }

    @Test
    public void registerWithEmail__ShouldSuccess() {
        String username = "reza";
        String password = "123abc";
        String email = "reza@gmail.com";
        boolean b = userService.registerUser(username, password, email);
        assertTrue(b);
    }

    @Test
    public void registerWithEmail_WhenEmailIsDuplicated_ShouldFail() {
        String password = "123abc";
        String email = "reza@gmail.com";
        userService.registerUser("user1", password, email);
        boolean registered = userService.registerUser("user2", password, email);
        assertFalse(registered);
    }

    @Test
    public void loginWithValidUsernameAndPassword__ShouldSuccess() {
        boolean login = userService.loginWithUsername("admin", "1234");
        assertTrue(login);
    }

    @Test
    public void loginWithValidUsernameAndInvalidPassword__ShouldFail() {
        boolean login = userService.loginWithUsername("admin", "abcd");
        assertFalse(login);
    }

    @Test
    public void loginWithInvalidUsernameAndInvalidPassword__ShouldFail() {
        boolean login = userService.loginWithUsername("ahmad", "abcd");
        assertFalse(login);
    }

    @Test
    public void loginWithEmail_WhenPasswordIsCorrect_ShouldSuccess() {
        boolean login = userService.loginWithEmail("admin@gmail.com", "1234");
        assertTrue(login);
    }

    @Test
    public void loginWithEmail_WhenPasswordIsWrong_ShouldFail() {
        boolean login = userService.loginWithEmail("admin@gmail.com", "wrong-password");
        assertFalse(login);
    }
}
