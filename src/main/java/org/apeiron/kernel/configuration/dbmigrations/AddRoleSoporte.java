package org.apeiron.kernel.configuration.dbmigrations;

import static org.apeiron.kernel.security.AuthoritiesConstants.SOPORTE;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.apeiron.kernel.domain.Authority;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Creates the initial database setup.
 */
@ChangeUnit(id = "add-role-soporte", order = "009")
public class AddRoleSoporte {

    private final MongoTemplate template;

    /**
     * The constructor `public AddRoleSoporte(MongoTemplate template)` is
     * initializing the `template` field
     * of the `AddRoleSoporte` class with the value passed as an argument to the
     * constructor. This allows
     * the `template` field to be used throughout the class for interacting with
     * the MongoDB database using
     * the `MongoTemplate` provided during object creation.
     * 
     * @param template
     */
    public AddRoleSoporte(MongoTemplate template) {
        this.template = template;
    }

    /**
     * The `changeSet` function saves an authority with the role `SOPORTE`.
     */
    @Execution
    public void changeSet() {
        template.save(createAuthority(SOPORTE));
    }

    /**
     * The above function is annotated with @RollbackExecution and named "rollback"
     * in Java.
     */
    @RollbackExecution
    public void rollback() {
    }

    /**
     * The function creates an Authority object with a specified name and returns
     * it.
     * 
     * @param authority The `createAuthority` method takes a String parameter named
     *                  `authority`, which
     *                  represents the name of the authority being created. The
     *                  method creates a new `Authority` object,
     *                  sets its name using the provided `authority` parameter, and
     *                  then returns the created `Authority`
     *                  object.
     * @return An instance of the `Authority` class with the `name` set to the
     *         provided `authority`
     *         parameter is being returned.
     */
    private Authority createAuthority(String authority) {
        Authority adminAuthority = new Authority();
        adminAuthority.setName(authority);
        return adminAuthority;
    }
}
