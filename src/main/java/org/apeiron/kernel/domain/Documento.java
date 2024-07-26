package org.apeiron.kernel.domain;

import com.mongodb.BasicDBObject;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Documento.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "documentos")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Documento implements AbstractExtensible, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("properties")
    private BasicDBObject properties = new BasicDBObject();

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and
    // setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Documento)) {
            return false;
        }
        return id != null && id.equals(((Documento) o).id);
    }

    @Override
    public int hashCode() {
        // see
        // https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }
}
