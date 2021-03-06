package de.fnortheim.pantry;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;


/**
 * Created by sebastianbasner on 04.06.17.
 */
@Singleton
@Startup
@TransactionManagement(value = TransactionManagementType.BEAN)
public class FlywayIntegrator {

    private final Logger log = LoggerFactory.getLogger(FlywayIntegrator.class);

    // inject actual datasource
    @Resource(name = "${datasource.name}")
    private javax.sql.DataSource dataSource;

    @PostConstruct
    private void onStartup() {

        if (dataSource == null) {
            log.error("no datasource found to execute the db migrations!");
            throw new EJBException(
                    "no datasource found to execute the db migrations!");
        }

        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        MigrationInfo migrationInfo = flyway.info().current();

        if (migrationInfo == null) {
            log.info("No existing database at the actual datasource");
        }
        else {
            log.info("Found a database with the version: {}", migrationInfo.getVersion() + " : "
                    + migrationInfo.getDescription());
        }

        flyway.migrate();
        log.info("Successfully migrated to database version: {}", flyway.info().current().getVersion());
    }
}

