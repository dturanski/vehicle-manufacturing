package dturanski.placeorder.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;

import static org.assertj.core.api.Assertions.assertThat;

public class ManufacturingCommandHandlerTests {

    @Test
    public void test() {

        ObjectMapper objectMapper = new ObjectMapper();
        new ApplicationContextRunner().withUserConfiguration(
                TestChannelBinderConfiguration.getCompleteConfiguration(TestApp.class))
                .withPropertyValues("--spring.jmx.enabled=false")
                .run(context -> {
                    ManufacturingCommandHandler commandHandler = context.getBean(ManufacturingCommandHandler.class);

                    OutputDestination output = context.getBean(OutputDestination.class);
                    commandHandler.addEngine(new AddEngineRequest("123", "2.6","V8"));
                    Message<byte[]> message = output.receive(1000);
                    DataFlowTaskLaunchRequest taskLaunchRequest =
                            objectMapper.readValue(message.getPayload(), DataFlowTaskLaunchRequest.class);
                    assertThat(taskLaunchRequest.getTaskName()).isEqualTo("add-engine");
                });
    }

    @Configuration
    @EnableAutoConfiguration
    @Import(ManufacturingCommandHandler.class)
    static class TestApp {

        }
}
