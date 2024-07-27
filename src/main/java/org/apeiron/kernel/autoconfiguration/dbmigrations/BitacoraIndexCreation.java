package org.apeiron.kernel.autoconfiguration.dbmigrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.apeiron.kernel.domain.bitacora.Bitacora;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * BitacoraIndexCreation
 */
@ChangeUnit(id = "add-bitacora-index", order = "007")
public class BitacoraIndexCreation extends AbstractIndexCreation {

    public BitacoraIndexCreation(MongoTemplate mongoTemplate) {
        super(mongoTemplate, Bitacora.class);
    }

    @Execution
    public void changeSet() {
        addSimpleIdx(false, "tipo");
        addSimpleIdx(false, "usuario_id");
        addSimpleIdx(false, "solicitudId");

        // Para paginados
        addSimpleIdx(false, "fecha_creacion");
        addComposeIndex(false, "_id", "fecha_creacion");
    }

    @RollbackExecution
    public void rollback() {
        deleteIndexes();
    }
}
