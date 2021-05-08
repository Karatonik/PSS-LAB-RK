package pss.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import pss.demo.models.Delegation;
import pss.demo.models.Role;
import pss.demo.models.User;
import pss.demo.services.interfaces.DelegationService;
import pss.demo.services.interfaces.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DemoApplication.class})
@AutoConfigureMockMvc
@SpringBootTest
public class DelegationRestControllerTest {
    private final String apiPath = "/delegations";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DelegationService delegationService;
    @Autowired
    private UserService userService;
    Set<Role> roleSet=new HashSet<Role>();
    Delegation delegation1= new Delegation();
    User user1 = new User("NOWE", "NOWE NOWE 14", "1111111111", "NOWE", "NOWY",
            "NOWY@NOWY.com", "!Nowe2021Nowe");
    @Test//ok
    public void remove() throws Exception {
        User user= userService.set(user1);
        delegation1.setDelegationId(1);
        String sDate1="31/12/1998";
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        delegation1.setDateTimeStart(date1);
        delegation1.setDateTimeStop(date1);
        userService.set(user.getUserId(),delegation1);
        System.out.println("test");
        mvc.perform(delete(apiPath+"?delegationId=1&userId="+user.getUserId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }
    @Test
    public void change()throws Exception{
        User user= userService.set(user1);
        delegation1.setDelegationId(1);
        String sDate1="31/12/1998";
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        delegation1.setDateTimeStart(date1);
        delegation1.setDateTimeStop(date1);
        userService.set(user.getUserId(),delegation1);
        delegation1.setDescription("test");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(delegation1);
        mvc.perform(put(apiPath+"?delegationId=1").content(json).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        assertThat(delegationService.get(1).getDescription())
                .isEqualTo("test");


    }



    @Test
    public void getAll() throws Exception {
        User user= userService.set(user1);
        delegation1.setDelegationId(1);
        String sDate1="31/12/1998";
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        delegation1.setDateTimeStart(date1);
        delegation1.setDateTimeStop(date1);
        userService.set(user.getUserId(),delegation1);
        mvc.perform(get(apiPath).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
    @Test
    public void OrderByDateStartDesc() throws Exception {
        User user= userService.set(user1);
        delegation1.setDelegationId(2);
        String sDate1="31/12/1998";
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        delegation1.setDateTimeStart(date1);
        delegation1.setDateTimeStop(date1);
        userService.set(user.getUserId(),delegation1);
        mvc.perform(get(apiPath+"/orderByDateStartDesc").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
    @Test
    public void UserOrderByDateStartDesc() throws Exception {
        User user= userService.set(user1);
        delegation1.setDelegationId(2);
        String sDate1="31/12/1998";
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        delegation1.setDateTimeStart(date1);
        delegation1.setDateTimeStop(date1);
        userService.set(user.getUserId(),delegation1);
        mvc.perform(get(apiPath+"/userOrderByDateStartDesc?userId="+user.getUserId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

    }



}
