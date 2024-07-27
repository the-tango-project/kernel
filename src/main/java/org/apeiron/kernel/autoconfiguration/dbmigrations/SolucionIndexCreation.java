package org.apeiron.kernel.autoconfiguration.dbmigrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.apeiron.kernel.domain.Solucion;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id = "add-solucion-index", order = "006")
public class SolucionIndexCreation extends AbstractIndexCreation {

    public SolucionIndexCreation(MongoTemplate mongoTemplate) {
        super(mongoTemplate, Solucion.class);
    }

    @Execution
    public void changeSet() {
        addSimpleIdx(false, "estado");
        addSimpleIdx(false, "autoridades.usuarioId");
        addSimpleIdx(false, "autoridades.cvu");
        addSimpleIdx(false, "tags");
        addSimpleIdx(false, "calendario.fechaInicio");
        addSimpleIdx(false, "calendario.fechaFinSolicitud");
        addSimpleIdx(false, "tipo");

        // Para paginados
        addSimpleIdx(false, "created_date");
        addComposeIndex(false, "_id", "created_date");
    }

    @RollbackExecution
    public void rollback() {
        deleteIndexes();
    }
}
