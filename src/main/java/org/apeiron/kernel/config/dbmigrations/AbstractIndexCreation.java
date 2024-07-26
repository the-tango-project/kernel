package org.apeiron.kernel.config.dbmigrations;

import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apeiron.kernel.service.exception.GeneralException;
import org.bson.Document;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.CompoundIndexDefinition;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.index.PartialIndexFilter;
import org.springframework.data.mongodb.core.query.Criteria;

/**
 * Clase base para la creación de índices en una única colección.
 *
 * Incluye la método a invocar para el rollback de los indices creado.
 */
@Slf4j
abstract class AbstractIndexCreation {

    protected final MongoTemplate mongoTemplate;

    private final Class<?> xclass;
    private final IndexOperations indexOperations;

    /**
     * Al momento de construir la clase se debe indicar sobre que colección
     * se harán los índices.
     *
     * @param mongoTemplate
     * @param xclass Clase de la colección.
     */
    AbstractIndexCreation(MongoTemplate mongoTemplate, Class<?> xclass) {
        this.mongoTemplate = mongoTemplate;
        this.xclass = xclass;
        indexOperations = mongoTemplate.indexOps(xclass);
    }

    /*
     * For rollback operations
     */
    private static Set<String> indexNames = new HashSet<>();

    /**
     * Método a invocar en el rollback.
     */
    protected void deleteIndexes() {
        var indexOperationDelete = mongoTemplate.indexOps(xclass);
        indexNames.forEach(name -> deleteIndex(indexOperationDelete, name));
    }

    /**
     * Método para eliminar un índice específico por nombre.
     *
     * @param name El nombre del índice a eliminar.
     */
    protected void deleteIndex(String name) {
        deleteIndex(mongoTemplate.indexOps(xclass), name);
    }

    protected void deleteIndex(IndexOperations indexOperationDelete, String name) {
        if (indexOperationDelete.getIndexInfo().stream().anyMatch(indexInfo -> name.equals(indexInfo.getName()))) {
            indexOperationDelete.dropIndex(name);
        }
    }

    /**
     * Agrega un índice simple.
     *
     * @param sparse Indica si el indice solo aplica si la llave existe.
     * @param key Llave en donde se hará el índice.
     */
    protected void addSimpleIdx(boolean sparse, String key) {
        var name = buildIndexNames(key);
        indexOperations.ensureIndex(buildBaseIndex(name, sparse, key).background());
        indexNames.add(name);
    }

    /**
     * Agrega un índice simple único.
     *
     * @param sparse Indica si el indice solo aplica si la llave existe.
     * @param key Llave en donde se hará el índice.
     */
    protected void addUniqueIdx(boolean sparse, String key) {
        var name = buildIndexNames(key);
        indexOperations.ensureIndex(buildBaseIndex(name, sparse, key).unique());
        indexNames.add(name);
    }

    /**
     * Agrega un índice compuesto.
     *
     * @param sparse Indica si el indice solo aplica si las llaves existen.
     * @param keys Llaves en donde se hará el índice.
     */
    protected void addComposeIndex(boolean sparse, String... keys) {
        var name = buildIndexNames(keys);
        indexOperations.ensureIndex(buildComposeBaseIndex(name, sparse, keys).background());
        indexNames.add(name);
    }

    /**
     * Agrega un índice compuesto único.
     *
     * @param sparse Indica si el indice solo aplica si las llaves existen.
     * @param keys Llaves en donde se hará el índice.
     */
    protected void addComposeUniqueIndex(boolean sparse, String... keys) {
        var name = buildIndexNames(keys);
        indexOperations.ensureIndex(buildComposeBaseIndex(name, sparse, keys).unique());
        indexNames.add(name);
    }

    private Index buildBaseIndex(String name, boolean sparse, String key) {
        return completeIndex(name, sparse, new Index().on(key, Direction.ASC), key);
    }

    private Index buildComposeBaseIndex(String name, boolean sparse, String... keys) {
        var document = new Document();
        var criteria = new Criteria();

        for (String key : keys) {
            document.append(key, 1);
            criteria.and(key).exists(true);
        }

        return completeIndex(name, sparse, new CompoundIndexDefinition(document), keys);
    }

    private Index completeIndex(String name, boolean sparse, Index idx, Object keys) {
        String[] keysNames = new String[0];

        if (keys instanceof String) {
            keysNames = new String[1];
            keysNames[0] = (String) keys;
        } else if (keys instanceof String[]) {
            keysNames = (String[]) keys;
        } else {
            throw new GeneralException(String.format("El tipo de dato no estaba soportado indice %s, tipo %s", name, keys.getClass()));
        }

        if (sparse) {
            if (keysNames.length == 1) {
                idx.sparse();
            } else if (keysNames.length > 1) {
                var criteria = Criteria.where(keysNames[0]).exists(true);

                for (int i = 1; i < keysNames.length; i++) {
                    criteria.and(keysNames[i]).exists(true);
                }

                idx.partial(PartialIndexFilter.of(criteria));
            } else {
                log.warn("Las llaves era un array vacio: {}", name);
            }
        }

        return idx.named(name);
    }

    private String buildIndexNames(String... keys) {
        StringBuilder sb = new StringBuilder("idx");

        for (String key : keys) {
            sb.append("_").append(key.toLowerCase().replace(".", "_"));
        }

        return sb.toString();
    }
}
