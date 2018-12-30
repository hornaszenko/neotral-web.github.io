package pl.lodz.uni.math.Communicator.exceptions;

/**
 * Exception thrown when trying to register user with credentials that already exists in datasource.
 *
 * @author Piotr Krzyminski
 */
public class AmbiguousUserException extends Throwable {

    public AmbiguousUserException() {
    }

    public AmbiguousUserException(String message) {
        super(message);
    }
}
