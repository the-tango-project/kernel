package org.apeiron.kernel.domain;

import java.io.Serializable;
import java.util.Objects;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * An authority (a security role) used by Spring Security.
 */
@Document(collection = "jhi_authority")
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 50)
    @Id
    private String name;

    /**
     * Getter method for the `name` property
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    public Authority name(String name) {
        this.setName(name);
        return this;
    }

    /**
     * Setter methdo for `name` property
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Equals method for the class `Authority`
     * 
     * @param o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Authority)) {
            return false;
        }
        return getName() != null && getName().equals(((Authority) o).getName());
    }

    /**
     * Hashcode method for the class `Authority`
     * 
     * @return the hascode of the entity
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    /**
     * The toString method of the `Authority` class
     * 
     * @return the string representation of the method
     */
    // prettier-ignore
    @Override
    public String toString() {
        return "Authority{" +
                "name='" + name + '\'' +
                "}";
    }
}
