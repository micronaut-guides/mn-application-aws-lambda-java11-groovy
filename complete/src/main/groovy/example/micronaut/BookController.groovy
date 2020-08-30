package example.micronaut
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import javax.validation.Valid
import groovy.transform.CompileStatic

@CompileStatic
@Controller // <1>
class BookController {

    @Post // <2>
    BookSaved save(@Valid @Body Book book) { // <3>
        BookSaved bookSaved = new BookSaved()
        bookSaved.name = book.name
        bookSaved.isbn = UUID.randomUUID().toString()
        bookSaved
    }
}
