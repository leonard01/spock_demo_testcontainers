import spock.lang.Shared


class TestNginxContainer extends DeployNginxContainer {

    @Shared HttpRequest req = new HttpRequest()
    @Shared String baseURL
    @Shared URL obj

    def setupSpec() {
        baseURL = url
        obj = new URL(baseURL)

    }

    def "When the nginx container is deployed we can hit the example endpoint"() {

        when: "We open an http request the"
        req.HTTPRequest(obj)

        then: "We search the response"
        assert req.searchResponse("Welcome to nginx!")
    }

    def "Check container logs"() {

        when: "Container is running"
        assert nginxContainer.isRunning()

        then: "Wait for container log message"
        assert waitForLogMessage(nginxContainer, "Configuration complete")
    }


}  