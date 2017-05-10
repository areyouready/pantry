package de.fnordheim;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.hibernate.boot.Metadata;
import org.hibernate.engine.jdbc.spi.JdbcServices;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by sebastianbasner on 04.05.17.
 */
public class FlywayIntegrator implements Integrator {

    private final Logger log = LoggerFactory.getLogger(FlywayIntegrator.class);

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        // Nothing to do here
    }

    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory,
                          SessionFactoryServiceRegistry serviceRegistry) {

        log.info("Starting DB migration");

        Connection connection = null;
        final JdbcServices jdbcServices = serviceRegistry.getService(JdbcServices.class);
        try {
            connection = jdbcServices.getBootstrapJdbcConnectionAccess().obtainConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
            DataSource dataSource = null;
            try {
                final Method method = connection != null ? connection.getClass().getMethod("getDataSource", null) : null;
                dataSource = (DataSource) (method != null ? method.invoke(connection, null) : null);

            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }

            Flyway flyway = new Flyway();
            flyway.setDataSource(dataSource);

            MigrationInfo current = flyway.info().current();
            if (current == null) {
                log.info("No existing DB found");
            }
            else {
                log.info("Current DB version is {}", current.getVersion());
            }

            flyway.migrate();

            log.info("Completed DB migration to version {}", flyway.info().current().getVersion());
    }
}
