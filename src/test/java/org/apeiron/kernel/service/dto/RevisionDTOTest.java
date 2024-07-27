package org.apeiron.kernel.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.apeiron.kernel.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RevisionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RevisionDTO.class);
        RevisionDTO revisionDTO1 = new RevisionDTO();
        revisionDTO1.setId("id1");
        RevisionDTO revisionDTO2 = new RevisionDTO();
        assertThat(revisionDTO1).isNotEqualTo(revisionDTO2);
        revisionDTO2.setId(revisionDTO1.getId());
        assertThat(revisionDTO1).isEqualTo(revisionDTO2);
        revisionDTO2.setId("id2");
        assertThat(revisionDTO1).isNotEqualTo(revisionDTO2);
        revisionDTO1.setId(null);
        assertThat(revisionDTO1).isNotEqualTo(revisionDTO2);
    }
}
