package pl.lodz.uni.math.Communicator.exceptions;

/**
 * Exception thrown when user cannot be registered.
 *
 * @author Piotr Krzyminski.
 */
public class UserRegisterException extends Throwable {

    public UserRegisterException() { }

    public UserRegisterException(String message) {
        super(message);
    }
}
