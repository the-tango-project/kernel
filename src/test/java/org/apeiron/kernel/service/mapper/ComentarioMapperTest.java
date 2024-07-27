package org.apeiron.kernel.service.mapper;

import org.junit.jupiter.api.BeforeEach;

class ComentarioMapperTest {

    private ComentarioMapper comentarioMapper;

    @BeforeEach
    public void setUp() {
        comentarioMapper = new ComentarioMapperImpl();
    }
}
