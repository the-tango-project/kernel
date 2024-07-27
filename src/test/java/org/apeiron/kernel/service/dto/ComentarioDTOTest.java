package org.apeiron.kernel.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ComentarioDtoTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        ComentarioDto comentarioDto1 = new ComentarioDto();
        comentarioDto1.setId("id1");
        ComentarioDto comentarioDto2 = new ComentarioDto();
        assertThat(comentarioDto1).isNotEqualTo(comentarioDto2);
        comentarioDto2.setId(comentarioDto1.getId());
        assertThat(comentarioDto1).isEqualTo(comentarioDto2);
        comentarioDto2.setId("id2");
        assertThat(comentarioDto1).isNotEqualTo(comentarioDto2);
        comentarioDto1.setId(null);
        assertThat(comentarioDto1).isNotEqualTo(comentarioDto2);
    }
}
