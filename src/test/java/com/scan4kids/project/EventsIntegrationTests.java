package com.scan4kids.project;

import com.scan4kids.project.daos.EventsRepository;
import com.scan4kids.project.daos.UsersRepository;
import com.scan4kids.project.models.Event;
import com.scan4kids.project.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectApplication.class)
@AutoConfigureMockMvc
public class EventsIntegrationTests {

    private User testUser;
    private HttpSession httpSession;

    @Autowired
    private MockMvc mvc;

    @Autowired
    UsersRepository usersDao;

    @Autowired
    EventsRepository eventsDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup() throws Exception {

        testUser = usersDao.findByUsername("testUser");

        //Creates the test user if it does not exist
        if(testUser == null) {
            User newUser = new User();
            newUser.setUsername("testUser");
            newUser.setPassword(passwordEncoder.encode("pass"));
            newUser.setPasswordToConfirm(passwordEncoder.encode("pass"));
            newUser.setEmail("testUser@codeup.com");
            testUser = usersDao.save(newUser);
        }

        //Throws a POST request to /login and expect a redirection to the dashboard page after being logged in
        httpSession = this.mvc.perform(post ("/login").with(csrf())
                .param("username", "testUser")
                .param("password", "pass"))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/dashboard"))
                .andReturn()
                .getRequest()
                .getSession();

    }

    @Test
    public void contextLoads() {
        //sanity test, to make sure the MVC bean is working
        assertNotNull(mvc);
    }

    @Test
    public void testIfUserSessionIsActive() throws Exception {
        //make sure the returned the user session is active and not null
        assertNotNull(httpSession);
    }

    @Test
    public void testCreateEvent() throws Exception {

        //makes a post request to /events/create and expect a redirection to the Event
        this.mvc.perform(
                post("/events/create").with(csrf())
                        .session((MockHttpSession) httpSession)
                        //add all the required parameters to the request
                        .param("title", "testTitle")
                        .param("location", "testLocation")
                        .param("dateAndTime", "testDateAndTime"))
                .andExpect(status().is3xxRedirection());

    }

    @Test
    public void testEventsIndex() throws Exception {

        Event existingEvent = eventsDao.findAll().get(0);

        //makes a get request to /events and verifies that we get some of the text of the events/index.html template and at least the title from the first event
        this.mvc.perform(get("/events"))
                .andExpect(status().isOk())
                //test the static content of the page
                .andExpect(content().string(containsString("Upcoming")))
                //test the dynamic content of the page
                .andExpect(content().string(containsString(existingEvent.getTitle())));
    }

    @Test
    public void testEditEvent() throws Exception {
        //gets the first Event
        Event existingEvent = eventsDao.findAll().get(0);
        //makes a post request to /events/{id}/edit and expect a redirection to the events index page
        this.mvc.perform(
                post("/events/" + existingEvent.getId() + "/edit").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("title", "editedTitle")
                        .param("location", "editedLocation")
                        .param("dateAndTime", "editedDateAndTime"))
                .andExpect(status().is3xxRedirection());

        //Makes a GET request to /events and expect a redirection to the Events index page
        this.mvc.perform(get("/events"))
                .andExpect(status().isOk())
                //test the dynamic content of the page
                .andExpect(content().string(containsString("editedTitle")))
                .andExpect(content().string(containsString("editedDateAndTime")));
    }



}
