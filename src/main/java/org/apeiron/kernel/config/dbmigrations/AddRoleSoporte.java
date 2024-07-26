package org.apeiron.kernel.config.dbmigrations;

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

    public AddRoleSoporte(MongoTemplate template) {
        this.template = template;
    }

    @Execution
    public void changeSet() {
        template.save(createAuthority(SOPORTE));
    }

    @RollbackExecution
    public void rollback() {}

    private Authority createAuthority(String authority) {
        Authority adminAuthority = new Authority();
        adminAuthority.setName(authority);
        return adminAuthority;
    }
}
