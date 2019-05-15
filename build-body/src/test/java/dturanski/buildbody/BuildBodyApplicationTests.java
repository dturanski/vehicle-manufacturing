package dturanski.buildbody;

import com.fasterxml.jackson.databind.ObjectMapper;
import dturanski.manufacturing.event.Event;
import org.junit.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;

import static org.assertj.core.api.Assertions.assertThat;

public class BuildBodyApplicationTests {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void test() {
        BuildBodyRequest buildBodyRequest = new BuildBodyRequest("123", "Tahoe");

        new ApplicationContextRunner().withUserConfiguration(
                TestChannelBinderConfiguration.getCompleteConfiguration(TestApp.class))
                .withPropertyValues("--spring.jmx.enabled=false",
                        "--spring.cloud.task.closecontext-enabled=false",
                        "--spring.cloud.task.events.enabled=false")
                .run(context -> {

                    OutputDestination output = context.getBean(OutputDestination.class);
                    CommandLineRunner commandLineRunner = context.getBean(CommandLineRunner.class);
                    //Json serialization flattens the json
                    commandLineRunner.run("request=" + objectMapper.writeValueAsString(buildBodyRequest),
                            "maxSleepMs=0");
                    Message<byte[]> eventMessage = output.receive(1000);
                    assertThat(eventMessage).isNotNull();

                    ObjectMapper objectMapper = new ObjectMapper();
                    Event event = objectMapper.readValue(eventMessage.getPayload(), Event.class);

                    assertThat(objectMapper.convertValue(event.getData(),BuildBodyRequest.class))
                            .isEqualTo(buildBodyRequest);
                });
    }

    @Configuration
    @EnableAutoConfiguration
    @Import(TaskConfiguration.class)
    static class TestApp {

    }
}
