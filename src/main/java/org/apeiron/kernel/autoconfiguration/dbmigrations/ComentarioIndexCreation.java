package org.apeiron.kernel.autoconfiguration.dbmigrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.apeiron.kernel.domain.Comentario;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * ComentarioIndexCreation
 */
@ChangeUnit(id = "add-comentario-index", order = "008")
public class ComentarioIndexCreation extends AbstractIndexCreation {

    public ComentarioIndexCreation(MongoTemplate mongoTemplate) {
        super(mongoTemplate, Comentario.class);
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
