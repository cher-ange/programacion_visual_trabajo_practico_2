package ar.edu.unju.fi.exercise6.interfaces.functionals;

/**
 * @author Gutierrez Angel Gonzalo
 * @version 1.0
 */
@FunctionalInterface
public interface Converter<T, R> {
    R convert(T t);

    static <T> boolean isNotNull(T t) {
        return t != null;
    }

    default
    void showObject(R r) {
        System.out.println("Objeto: " + r.toString());
    }
}
