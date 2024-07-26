package org.apeiron.kernel.config.dbmigrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.apeiron.kernel.domain.Solicitud;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id = "add-solicitud-index", order = "005")
public class SolicitudIndexCreation extends AbstractIndexCreation {

    public SolicitudIndexCreation(MongoTemplate mongoTemplate) {
        super(mongoTemplate, Solicitud.class);
    }

    @Execution
    public void changeSet() {
        addSimpleIdx(false, "solucionId");
        addSimpleIdx(false, "usuario");
        addSimpleIdx(false, "estado");

        // Para paginados
        addSimpleIdx(false, "created_date");
        addComposeIndex(false, "_id", "created_date");
    }

    @RollbackExecution
    public void rollback() {
        deleteIndexes();
    }
}
