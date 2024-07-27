package org.apeiron.kernel.autoconfiguration.dbmigrations;

import com.google.common.collect.Lists;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.apeiron.kernel.domain.Form;
import org.apeiron.kernel.domain.enumeration.CustomComponente;
import org.apeiron.kernel.domain.enumeration.EstadoForm;
import org.apeiron.kernel.domain.enumeration.MenuElement;
import org.apeiron.kernel.domain.enumeration.TipoComponente;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Creates the initial database setup.
 */
@ChangeUnit(id = "componente-documentos-solicitud", order = "003")
public class RegistrarDocumentosComponent {

    private final MongoTemplate template;

    public RegistrarDocumentosComponent(MongoTemplate template) {
        this.template = template;
    }

    @Execution
    public void changeSet() {
        Form form = new Form();
        form.setId(CustomComponente.REGISTRAR_DOCUMENTOS.toString());
        form.setTitle("Documentos");
        form.setMenuName(MenuElement.DOCUMENTOS);
        form.setDescription("Documentos de la solicitud");
        form.setTipo(TipoComponente.CUSTOM);
        form.setTags(Lists.newArrayList("general"));
        form.getProperties().put("name", "documentos");
        form.getProperties().put("path", "personas/documentos");
        form.setEstado(EstadoForm.PUBLICADA);
        template.save(form);
    }

    @RollbackExecution
    public void rollback() {}
}
