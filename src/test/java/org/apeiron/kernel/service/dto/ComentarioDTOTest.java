package org.apeiron.kernel.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ComentarioDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        ComentarioDTO comentarioDTO1 = new ComentarioDTO();
        comentarioDTO1.setId("id1");
        ComentarioDTO comentarioDTO2 = new ComentarioDTO();
        assertThat(comentarioDTO1).isNotEqualTo(comentarioDTO2);
        comentarioDTO2.setId(comentarioDTO1.getId());
        assertThat(comentarioDTO1).isEqualTo(comentarioDTO2);
        comentarioDTO2.setId("id2");
        assertThat(comentarioDTO1).isNotEqualTo(comentarioDTO2);
        comentarioDTO1.setId(null);
        assertThat(comentarioDTO1).isNotEqualTo(comentarioDTO2);
    }
}
