package example.micronaut
import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext
import com.amazonaws.serverless.proxy.model.AwsProxyRequest
import com.amazonaws.serverless.proxy.model.AwsProxyResponse
import com.amazonaws.services.lambda.runtime.Context
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpMethod
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import spock.lang.Specification
import spock.lang.Shared
import spock.lang.AutoCleanup
import io.micronaut.function.aws.proxy.MicronautLambdaHandler

class BookControllerSpec extends Specification {

    @Shared
    @AutoCleanup // <2>
    MicronautLambdaHandler handler = new MicronautLambdaHandler() // <1>

    @Shared
    Context lambdaContext = new MockLambdaContext()

    @Shared
    ObjectMapper objectMapper = handler.applicationContext.getBean(ObjectMapper)

    void "test save Book"() {
        given:
        Book book = new Book()
        book.name = "Building Microservices"
        String json = objectMapper.writeValueAsString(book)

        when:
        AwsProxyRequest request = new AwsProxyRequestBuilder("/", HttpMethod.POST.toString())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .body(json).build()
        AwsProxyResponse response = handler.handleRequest(request, lambdaContext) // <3>

        then:
        HttpStatus.OK.code == response.statusCode
        response.body

        when:
        BookSaved bookSaved = objectMapper.readValue(response.body, BookSaved)

        then:
        bookSaved.name == book.name
        bookSaved.isbn
    }
}
