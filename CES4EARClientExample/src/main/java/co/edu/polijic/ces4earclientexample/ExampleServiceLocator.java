/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.polijic.ces4earclientexample;

import java.net.URL;
import java.util.Properties;

import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;
import javax.mail.Session;

/**
 *
 * @author l.sanchez
 */
public class ExampleServiceLocator {

    private InitialContext ic;

    public ExampleServiceLocator(Properties props) {
        try {
            ic = new InitialContext(props);
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

    private Object lookup(String jndiName) throws NamingException {
        return ic.lookup(jndiName);
    }

    /**
     * Will get the ejb Local home factory. Clients need to cast to the type of
     * EJBHome they desire.
     *
     * @param jndiHomeName jndi home name matching the requested local home
     * @return the Local EJB Home corresponding to the homeName
     * @throws NamingException if the lookup fails
     */
    public EJBLocalHome getLocalHome(String jndiHomeName) throws NamingException {
        return (EJBLocalHome) lookup(jndiHomeName);
    }

    /**
     * Will get the ejb Remote home factory. Clients need to cast to the type of
     * EJBHome they desire.
     *
     * @param jndiHomeName jndi home name matching the requested remote home
     * @param className desired type of the object
     * @return the EJB Home corresponding to the homeName
     * @throws NamingException if the lookup fails
     */
    public EJBHome getRemoteHome(String jndiHomeName, Class className) throws NamingException {
        Object objref = lookup(jndiHomeName);
        return (EJBHome) PortableRemoteObject.narrow(objref, className);
    }

    public <T> T getEJBInstance(String jndiHomeName) throws NamingException {
        return (T) lookup(jndiHomeName);
    }

    /**
     * This method helps in obtaining the JMS connection factory.
     *
     * @param connFactoryName name of the connection factory
     * @return the factory for obtaining JMS connection
     * @throws NamingException if the lookup fails
     */
    public ConnectionFactory getConnectionFactory(String connFactoryName) throws NamingException {
        return (ConnectionFactory) lookup(connFactoryName);
    }

    /**
     * This method obtains the topic itself for a caller.
     *
     * @param destName destination name
     * @return the Topic Destination to send messages to
     * @throws NamingException if the lookup fails
     */
    public Destination getDestination(String destName) throws NamingException {
        return (Destination) lookup(destName);
    }

    /**
     * This method obtains the datasource itself for a caller.
     *
     * @param dataSourceName data source name
     * @return the DataSource corresponding to the name parameter
     * @throws NamingException if the lookup fails
     */
    public DataSource getDataSource(String dataSourceName) throws NamingException {
        return (DataSource) lookup(dataSourceName);
    }

    /**
     * This method obtains the E-mail session itself for a caller.
     *
     * @param sessionName session name
     * @return the Session corresponding to the name parameter
     * @throws NamingException if the lookup fails
     */
    public Session getSession(String sessionName) throws NamingException {
        return (Session) lookup(sessionName);
    }

    /**
     * Gets the URL corresponding to the environment entry name.
     *
     * @param envName the environment name
     * @return the URL value corresponding to the environment entry name
     * @throws NamingException if the lookup fails
     */
    public URL getUrl(String envName) throws NamingException {
        return (URL) lookup(envName);
    }

    /**
     * Gets boolean value corresponding to the environment entry name.
     *
     * @param envName the environment name
     * @return the boolean value corresponding to the environment entry
     * @throws NamingException if the lookup fails
     */
    public boolean getBoolean(String envName) throws NamingException {
        Boolean bool = (Boolean) lookup(envName);
        return bool.booleanValue();
    }

    /**
     * Gets string value corresponding to the environment entry name.
     *
     * @param envName the environment name
     * @return the String value corresponding to the environment entry name
     * @throws NamingException if the lookup fails
     */
    public String getString(String envName) throws NamingException {
        return (String) lookup(envName);
    }
}
