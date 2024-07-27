package org.apeiron.kernel.service.dto;

import org.apeiron.kernel.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RevisorDtoTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RevisorDto.class);
    }
}
