package org.apeiron.kernel.config.dbmigrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.apeiron.kernel.domain.Revision;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id = "add-revision-index", order = "011")
public class RevisionIndexCreation extends AbstractIndexCreation {

    public RevisionIndexCreation(MongoTemplate mongoTemplate) {
        super(mongoTemplate, Revision.class);
    }

    @Execution
    public void changeSet() {
        addSimpleIdx(false, "evaluacion_id");
        addSimpleIdx(false, "estado");
        addSimpleIdx(false, "revisor.revisor_id");
        addSimpleIdx(false, "created_date");
        addComposeIndex(false, "_id", "created_date");
    }

    @RollbackExecution
    public void rollback() {
        deleteIndexes();
    }
}
