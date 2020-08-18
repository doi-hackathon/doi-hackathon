package com.scan4kids.project;

import com.scan4kids.project.daos.UsersRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectApplication.class)
@AutoConfigureMockMvc
public class AlbumsIntegrationTests {

    private User testUser;
    private HttpSession httpSession;

    @Autowired
    private MockMvc mvc;

    @Autowired
    UsersRepository usersDao;

    @Autowired
    AlbumsRepository albumsDao;

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
        httpSession = this.mvc.perform(post("/login").with(csrf())
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
    public void testCreateAlbum() throws Exception {

        //makes a post request to /albums/create and expect a redirection
        this.mvc.perform(
                post("/albums/create").with(csrf())
                        .session((MockHttpSession) httpSession)
                        //add all the required parameters to the request
                        .param("title", "testAlbumTitle"))
                .andExpect(status().is3xxRedirection());

    }

    @Test
    public void testShowAlbum() throws Exception {

        Album existingAlbum = albumsDao.findAll().get(0);

        // Makes a GET request to /albums/{id} and expect a redirection to the Album show page
        this.mvc.perform(get("/albums/" + existingAlbum.getId()))
                .andExpect(status().isOk())
                // Test the dynamic content of the page
                .andExpect(content().string(containsString(existingAlbum.getTitle())));


    }

    @Test
    public void testAlbumsIndex() throws Exception {

        Album existingAlbum = albumsDao.findAll().get(0);

        //makes a get request to /albums and verifies that we get some of the text from the albums/index.html template and at least the title from the first album
        this.mvc.perform(get("/albums"))
                .andExpect(status().isOk())
                //test the static content of the page
                .andExpect(content().string(containsString("Albums")))
                //test the dynamic content of the page
                .andExpect(content().string(containsString(existingAlbum.getTitle())));
    }

    @Test
    public void testEditAlbum() throws Exception {
        //gets the first Album
        Album existingAlbum = albumsDao.findAll().get(0);
        //makes a post request to /albums/{id}/edit and expects a redirection to the individual album page
        this.mvc.perform(
                post("/albums/" + existingAlbum.getId() + "/edit").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("title", "editedTitle"))
                .andExpect(status().is3xxRedirection());

        //Makes a GET request to /albums and expect a redirection to the individual album page
        this.mvc.perform(get("/albums"))
                .andExpect(status().isOk())
                //test the static content of the page
                .andExpect(content().string(containsString("editedTitle")));
    }

    @Test
    public void testDeleteAlbum() throws Exception {
        // Creates a test Album to be deleted
        this.mvc.perform(
                post("/albums/create").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("title", "Fake album")
                        .param("description", "Fake album description"))
                .andExpect(status().is3xxRedirection());

        // Get the recent Album that matches the title
        Album existingAlbum = albumsDao.findByTitle("Fake album");

        // Makes a Post request to /albums/{id}/delete and expects a redirection to the Album index page
        this.mvc.perform(
                post("/albums/" + existingAlbum.getId() + "/delete").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("id", String.valueOf(existingAlbum.getId())))
                .andExpect(status().is3xxRedirection());
    }


}
