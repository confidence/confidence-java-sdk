package org.rebaze.confidence.jrunner;

public class CommandLineBuilder
{

    /**
     * The command line array.
     */
    private String[] m_commandLine;

    /**
     * Creates a new command line builder.
     */
    public CommandLineBuilder()
    {
        m_commandLine = new String[0];
    }

    /**
     * Appends an array of strings to command line.
     *
     * @param segments array to append
     *
     * @return CommandLineBuilder for fluent api
     */
    public CommandLineBuilder append( final String[] segments )
    {
        if( segments != null && segments.length > 0 )
        {
            final String[] command = new String[m_commandLine.length + segments.length];
            System.arraycopy( m_commandLine, 0, command, 0, m_commandLine.length );
            System.arraycopy( segments, 0, command, m_commandLine.length, segments.length );
            m_commandLine = command;
        }
        return this;
    }

    /**
     * Appends a string to command line.
     *
     * @param segment string to append
     *
     * @return CommandLineBuilder for fluent api
     */
    public CommandLineBuilder append( final String segment )
    {
        if( segment != null )
        {
            return append( new String[]{ segment } );
        }
        return this;
    }

    /**
     * Returns the command line.
     *
     * @return command line
     */
    public String[] toArray()
    {
        return m_commandLine;
    }

}
