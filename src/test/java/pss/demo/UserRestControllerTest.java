package pss.demo;

import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pss.demo.models.Role;
import pss.demo.models.User;
import pss.demo.repositorys.UserRepository;

import java.util.HashMap;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DemoApplication.class})
@SpringBootTest
public class UserRestControllerTest {



    @Autowired
    private UserRepository userRepository;

    Set<Role> roleSet;
    @Test
    public void add() {
        User user1 = new User("d","sdf","sdf","sdf","sdf","sdf","sdf",roleSet);
        userRepository.save(user1);
    }
}
