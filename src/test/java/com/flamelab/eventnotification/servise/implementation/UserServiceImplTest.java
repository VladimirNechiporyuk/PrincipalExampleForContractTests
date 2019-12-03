package com.flamelab.eventnotification.servise.implementation;

import com.flamelab.eventnotification.configuration.TestConfig;
import com.flamelab.eventnotification.configuration.mocks.MocksForUserService;
import com.flamelab.eventnotification.configuration.testdata.ProvidingTestData;
import com.flamelab.eventnotification.servise.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class, MocksForUserService.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private ProvidingTestData testDataProvider;
    @Autowired
    private MocksForUserService mocksForService;

    @Test
    public void createUser() {

    }

    @Test
    public void fetchAllUsers() {
    }

    @Test
    public void fetchUserById() {
    }

    @Test
    public void fetchUserByName() {
    }

    @Test
    public void fetchUserByAge() {
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void deleteUserById() {
    }
}