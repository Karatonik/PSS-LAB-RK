package pss.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pss.demo.enums.ERole;
import pss.demo.models.Delegation;
import pss.demo.models.Role;
import pss.demo.models.User;
import pss.demo.services.interfaces.DelegationService;
import pss.demo.services.interfaces.RoleService;
import pss.demo.services.interfaces.UserService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DemoApplication.class})
@AutoConfigureMockMvc
@SpringBootTest
public class UserRestControllerTest {
    private final String apiPath = "/users";
    Set<Role> roleSet = new HashSet<>();
    User user1 = new User("NOWE", "NOWE NOWE 14", "1111111111", "NOWE", "NOWY",
            "NOWY@NOWY.com", "!Nowe2021Nowe");
    Role role = new Role(ERole.ROLE_USER);
    Set<Delegation> delegationSet = new HashSet<>();
    @Autowired
    private MockMvc mvc;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    @Test//ok
    public void register() throws Exception {
        user1.setDelegationSet(delegationSet);
        user1 = userService.set(user1);
        user1.setName("testowy");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user1);
        mvc.perform(post (apiPath+"/register").content(json).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print()).andExpect(status().isOk());

        assertThat(userService.get(user1.getUserId()).getName())
                .isEqualTo("testowy");

    }

    @Test//ok
    public void getAll() throws Exception {
        mvc.perform(get(apiPath).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test//ok
    public void changePassword() throws Exception {
        user1 = userService.set(user1);
        String newPassword="nowehasło1234124";
        mvc.perform(put(apiPath+"/changePassword?newPassword="+newPassword+"&userId=1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(userService.get(1).getPassword())
                .isEqualTo("nowehasło1234124");
    }

    @Test//ok
    public void deleteById() throws Exception {
        User user2 = new User("NOWE", "NOWE NOWE 14", "1111111111", "NOWE", "NOWY",
                "NOWY@NOWY.com", "!Nowe2021Nowe");
        user2 = userService.set(user2);
        mvc.perform(delete(apiPath+"/?userId="+user2.getUserId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test//ok
    public void setDelegation() throws Exception {
        userService.set(user1);
    Delegation delegation1 = new Delegation();
    delegation1.setDelegationId(1);
        String sDate1="31/12/1998";
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        delegation1.setDateTimeStart(date1);
        delegation1.setDateTimeStop(date1);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(delegation1);
    mvc.perform(post(apiPath+"/setDelegation?userId=1").content(json).contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());

        assertThat(userService.get(1).getDelegationSet().size())
                .isEqualTo(1);
    }

    @Test//ok
    public void byRoleName() throws Exception {
        roleService.set(role.getRoleName());
     mvc.perform(get(apiPath+"/byRoleName?roleName="+role.getRoleName()).contentType(MediaType.APPLICATION_JSON))
             .andDo(print())
             .andExpect(status().isOk())
             .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test//ok
    public void addRole() throws Exception {
       User user2= userService.set(user1);
      Role role1=  roleService.set(ERole.ROLE_USER);
        mvc.perform(put(apiPath+"/AddRole?roleName="+role1.getRoleName()+"&userId="+user2.getUserId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(userService.get(1).getRoleSet().size())
        .isEqualTo(1);
    }


}
