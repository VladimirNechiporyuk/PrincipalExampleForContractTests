package com.flamelab.eventnotification.configuration.mocks;

import com.flamelab.eventnotification.configuration.testdata.ProvidingTestData;
import com.flamelab.eventnotification.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MocksForUserService implements MocksForService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProvidingTestData testDataProvider;

    @Override
    public void mockResponses() {

    }
}
