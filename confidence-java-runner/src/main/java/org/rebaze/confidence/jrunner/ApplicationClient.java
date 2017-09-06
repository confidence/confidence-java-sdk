package org.rebaze.confidence.jrunner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.ops4j.io.Pipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationClient
{

    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger( ApplicationClient.class );

    /**
     * If the execution should wait for platform shutdown.
     */
    private final boolean m_wait;
    /**
     * Framework process.
     */
    private Process m_frameworkProcess;
    /**
     * Shutdown hook.
     */
    private Thread m_shutdownHook;

    private PrintStream out = System.out;

    private PrintStream err = System.err;

    private InputStream inp = System.in;



    /**
     * Constructor.
     */
    public ApplicationClient()
    {
        this( true );
    }

    /**
     * Constructor.
     *
     * @param wait should wait for framework exis
     */
    public ApplicationClient(boolean wait )
    {
        m_wait = wait;
    }

    public synchronized void exec( final String[] vmOptions,
                                   final String[] classpath,
                                   final String mainClass,
                                   final String[] programOptions,
                                   final String javaHome,
                                   final File workingDirectory )
            throws ExecutionException
    {
        exec( vmOptions,classpath,mainClass,programOptions,javaHome,workingDirectory,new String [0] );
    }
    /**
     * {@inheritDoc}
     */
    public synchronized void exec( final String[] vmOptions,
                                   final String[] classpath,
                                   final String mainClass,
                                   final String[] programOptions,
                                   final String javaHome,
                                   final File workingDirectory,
                                   final String[] envOptions )
            throws ExecutionException
    {
        if( m_frameworkProcess != null )
        {
            throw new ExecutionException( "Platform already started" );
        }

        final StringBuilder cp = new StringBuilder();

        for( String path : classpath )
        {
            if( cp.length() != 0 )
            {
                cp.append( File.pathSeparator );
            }
            cp.append( path );
        }

        final CommandLineBuilder commandLine = new CommandLineBuilder()
                .append( getJavaExecutable( javaHome ) )
                .append( vmOptions )
                .append( "-cp" )
                .append( cp.toString() )
                .append( mainClass )
                .append( programOptions );

        LOG.debug( "Start command line [" + Arrays.toString( commandLine.toArray() ) + "]" );

        try
        {
            LOG.debug( "Starting platform process." );
            m_frameworkProcess = Runtime.getRuntime().exec( commandLine.toArray(), createEnvironmentVars( envOptions ), workingDirectory );
        }
        catch( IOException e )
        {
            throw new ExecutionException( "Could not start up the process", e );
        }

        m_shutdownHook = createShutdownHook( m_frameworkProcess );
        Runtime.getRuntime().addShutdownHook( m_shutdownHook );

        LOG.debug( "Added shutdown hook." );
        LOG.info( "DefaultJavaRunner completed successfully" );

        if( m_wait )
        {
            waitForExit();
        }
        else
        {
            System.out.println();
        }
    }

    public void setStandardOut(PrintStream ps ) {
        this.out = ps;
    }

    public void setErrorOut(PrintStream ps ) {
        this.err = ps;
    }

    public void setInputStream(InputStream inp ) {
        this.inp = inp;
    }

    private String[] createEnvironmentVars( String[] envOptions )
    {
        List<String> env = new ArrayList<String>(  );
        Map<String, String> getenv = System.getenv();
        for (String key : getenv.keySet() ) {
            env.add( key + "=" + getenv.get( key ) );
        }
        if (envOptions != null) Collections.addAll( env, envOptions );
        return env.toArray( new String[env.size()] );
    }

    /**
     * {@inheritDoc}
     */
    public void shutdown()
    {
        try {
            if (m_shutdownHook != null) {
                synchronized (m_shutdownHook) {
                    if (m_shutdownHook != null) {
                        LOG.debug("Shutdown in progress...");
                        Runtime.getRuntime().removeShutdownHook( m_shutdownHook );
                        m_frameworkProcess = null;
                        m_shutdownHook.run();
                        m_shutdownHook = null;
                        LOG.info( "Platform has been shutdown." );
                    }
                }
            }
        }
        catch( IllegalStateException ignore )
        {
            // just ignore
        }
    }

    /**
     * Wait till the framework process exits.
     */
    public void waitForExit()
    {
        synchronized (m_frameworkProcess) {
            try
            {
                LOG.debug( "Waiting for framework exit." );
                System.out.println();
                m_frameworkProcess.waitFor();
                shutdown();
            }
            catch( Throwable e )
            {
                LOG.debug( "Early shutdown.", e );
                shutdown();
            }
        }
    }

    /**
     * Create helper thread to safely shutdown the external framework process
     *
     * @param process framework process
     *
     * @return stream handler
     */
    private Thread createShutdownHook( final Process process )
    {
        LOG.debug( "Wrapping stream I/O." );

        final Pipe errPipe = new Pipe( process.getErrorStream(), err).start( "Error pipe" );
        final Pipe outPipe = new Pipe( process.getInputStream(), out ).start( "Out pipe" );
        final Pipe inPipe = new Pipe( process.getOutputStream(), inp ).start( "In pipe" );

        return new Thread(
                new Runnable()
                {
                    public void run()
                    {
                        System.out.println();
                        LOG.debug( "Unwrapping stream I/O." );

                        inPipe.stop();
                        outPipe.stop();
                        errPipe.stop();

                        try
                        {
                            process.destroy();
                        }
                        catch( Exception e )
                        {
                            // ignore if already shutting down
                        }
                    }
                },
                "DefaultJavaRunner shutdown hook"
        );
    }

    public static String getJavaHome() {
        String javaHome = System.getenv("JAVA_HOME");
        if (javaHome == null) {
            javaHome = System.getProperty("java.home");
        }
        return javaHome;
    }

    /**
     * Return path to java executable.
     *
     * @param javaHome java home directory
     *
     * @return path to java executable
     *
     * @throws ExecutionException if java home could not be located
     */
    static String getJavaExecutable( final String javaHome )
            throws ExecutionException
    {
        if( javaHome == null )
        {
            throw new ExecutionException( "JAVA_HOME is not set." );
        }
        return javaHome + "/bin/java";
    }



}
