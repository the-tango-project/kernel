package org.apeiron.kernel.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.apeiron.kernel.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FormDtoTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormDto.class);
        FormDto formDto1 = new FormDto();
        formDto1.setId("id1");
        FormDto formDto2 = new FormDto();
        assertThat(formDto1).isNotEqualTo(formDto2);
        formDto2.setId(formDto1.getId());
        assertThat(formDto1).isEqualTo(formDto2);
        formDto2.setId("id2");
        assertThat(formDto1).isNotEqualTo(formDto2);
        formDto1.setId(null);
        assertThat(formDto1).isNotEqualTo(formDto2);
    }
}
