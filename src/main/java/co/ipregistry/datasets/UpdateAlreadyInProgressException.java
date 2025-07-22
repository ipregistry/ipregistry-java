package co.ipregistry.datasets;

/**
 * Exception thrown when attempting to start a dataset update while another update is already in progress.
 * <p>
 * This exception is used in the context of dataset management to prevent concurrent updates
 * that could lead to data corruption or inconsistent states. Only one update operation
 * should be allowed at a time for data integrity.
 * </p>
 */
public class UpdateAlreadyInProgressException extends Exception {

    /**
     * Creates a new UpdateAlreadyInProgressException instance with default values.
     * This constructor is primarily used for JSON deserialization and object initialization.
     */
    public UpdateAlreadyInProgressException() {
    }

}
