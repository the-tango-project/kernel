package org.apeiron.kernel.service.mapper;

import org.junit.jupiter.api.BeforeEach;

class RevisionMapperTest {

    private RevisionMapper revisionMapper;

    @BeforeEach
    public void setUp() {
        revisionMapper = new RevisionMapperImpl();
    }
}
