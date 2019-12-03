package com.flamelab.eventnotification.configuration;

import com.flamelab.eventnotification.configuration.testdata.ProvidingTestData;
import com.flamelab.eventnotification.configuration.testdata.TestDataGenerator;
import com.flamelab.eventnotification.repository.EventRepository;
import com.flamelab.eventnotification.repository.UserRepository;
import com.flamelab.eventnotification.servise.EventService;
import com.flamelab.eventnotification.servise.UserService;
import com.flamelab.eventnotification.servise.implementation.EventServiceImpl;
import com.flamelab.eventnotification.servise.implementation.UserServiseImpl;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.InetSocketAddress;

@EnableMongoRepositories(basePackageClasses = {UserRepository.class, EventRepository.class})
@Configuration
public class TestConfig extends AbstractMongoConfiguration {

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private EventRepository eventRepository;

    @Bean
    public ProvidingTestData testDataProvider() {
        return new TestDataGenerator();
    }

    @Override
    protected String getDatabaseName() {
        return "test";
    }

    @Override
    public MongoClient mongoClient() {
        InetSocketAddress serverAddress = new MongoServer(new MemoryBackend()).bind();
        return new MongoClient(new ServerAddress(serverAddress));
    }

    @Bean
    public UserService userService() {
        return new UserServiseImpl(userRepository);
    }

    @Bean
    public EventService eventService() {
        return new EventServiceImpl(eventRepository);
    }
}
