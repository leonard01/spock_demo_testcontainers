import org.testcontainers.containers.GenericContainer
import spock.lang.Shared
import spock.lang.Specification

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

}
