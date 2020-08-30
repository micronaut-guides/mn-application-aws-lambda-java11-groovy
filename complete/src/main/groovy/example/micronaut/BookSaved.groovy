package example.micronaut;
import edu.umd.cs.findbugs.annotations.NonNull
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import groovy.transform.CompileStatic

@CompileStatic
@Introspected // <1>
class BookSaved {

    @NonNull // <3>
    @NotBlank // <2>
    String name


    @NonNull // <3>
    @NotBlank // <2>
    String isbn
}
