package example.micronaut
import edu.umd.cs.findbugs.annotations.NonNull
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import groovy.transform.CompileStatic

@CompileStatic
@Introspected // <1>
class Book {

    @NonNull // <3>
    @NotBlank // <2>
    String name
}
