import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.output.OutputFrame
import org.testcontainers.containers.output.WaitingConsumer
import spock.lang.Shared
import spock.lang.Specification

import java.util.concurrent.TimeUnit

class DeployNginxContainer extends Specification{

    static final String NGINX_IMAGE = "nginx"
    @Shared GenericContainer nginxContainer
    @Shared url

    def setupSpec(){

        nginxContainer = new GenericContainer(NGINX_IMAGE)
                .withExposedPorts( 80)
        nginxContainer.start()
        def host = nginxContainer.getContainerIpAddress()
        def mappedPort = nginxContainer.getMappedPort(80)
        url = "http://${host}:${mappedPort}"
    }


    def waitForLogMessage(GenericContainer container, String logMessage){

        WaitingConsumer consumer = new WaitingConsumer()
        container.followOutput(consumer, OutputFrame.OutputType.STDOUT)
        consumer.waitUntil({ frame -> frame.getUtf8String().contains(logMessage)}, 30, TimeUnit.SECONDS)
        return true
    }


    def waitForLogMessageWithRegex(GenericContainer container, String regex, int times = 1){
        regex = "(?is)(.*)" + regex + "(.*)"
        WaitingConsumer consumer = new WaitingConsumer()
        container.followOutput(consumer, OutputFrame.OutputType.STDOUT)
        consumer.waitUntil({ frame -> frame.getUtf8String().matches(regex)}, 30, TimeUnit.SECONDS, times)
        return true
    }


}
