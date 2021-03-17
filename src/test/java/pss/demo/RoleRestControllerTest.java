package pss.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pss.demo.models.Role;
import pss.demo.services.interfaces.RoleService;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DemoApplication.class})
@AutoConfigureMockMvc
@SpringBootTest
public class RoleRestControllerTest {
    private final String apiPath="/role";

    @Autowired
    private MockMvc mvc;
    @Autowired
    private RoleService roleService;
    private Role role = new Role("test");

    @Test
    public void create() throws Exception {
        mvc.perform(post (apiPath+"?roleName="+role.getRoleName()).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print()).andExpect(status().isOk());
    }
    @Test
    public void del() throws Exception {
        roleService.set(role.getRoleName());
        mvc.perform(delete(apiPath+"?roleName="+role.getRoleName()).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print()).andExpect(status().isOk());
    }
    @Test
    public void getAll() throws Exception {
        roleService.set(role.getRoleName());
        roleService.set(role.getRoleName()+1);
        roleService.set(role.getRoleName()+2);
        mvc.perform(get(apiPath).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }


}
