package org.apeiron.kernel.config.dbmigrations;

import static org.apeiron.kernel.security.AuthoritiesConstants.INNOVADOR;
import static org.apeiron.kernel.security.AuthoritiesConstants.MODELADOR;
import static org.apeiron.kernel.security.AuthoritiesConstants.OPERADOR;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.apeiron.kernel.domain.Authority;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Creates the initial database setup.
 */
@ChangeUnit(id = "add-roles-for-apeiron", order = "004")
public class RolesSetupMigration {

    private final MongoTemplate template;

    public RolesSetupMigration(MongoTemplate template) {
        this.template = template;
    }

    @Execution
    public void changeSet() {
        template.save(createAuthority(INNOVADOR));
        template.save(createAuthority(MODELADOR));
        template.save(createAuthority(OPERADOR));
    }

    @RollbackExecution
    public void rollback() {}

    private Authority createAuthority(String authority) {
        Authority adminAuthority = new Authority();
        adminAuthority.setName(authority);
        return adminAuthority;
    }
}
