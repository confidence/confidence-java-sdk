package org.rebaze.confidence.jrunner;

public class ExecutionException
        extends RuntimeException
{
    private static final long serialVersionUID = 4601658364559556917L;

    public ExecutionException()
    {
        // empty
    }

    /**
     * @param message The exception message.
     *
     * @see Exception#Exception(String)
     */
    public ExecutionException( String message )
    {
        super( message );
    }

    /**
     * @param message The exception message.
     * @param cause   The original cause of this exception.
     *
     * @see Exception#Exception(String,Throwable)
     */
    public ExecutionException( String message, Throwable cause )
    {
        super( message, cause );
    }
    /**
     * @param cause   The original cause of this exception.
     *
     * @see Exception#Exception(String,Throwable)
     */
    public ExecutionException( Throwable cause )
    {
        super( cause );
    }
}
