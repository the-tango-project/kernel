package org.apeiron.kernel.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.apeiron.kernel.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RevisionDtoTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RevisionDto.class);
        RevisionDto revisionDto1 = new RevisionDto();
        revisionDto1.setId("id1");
        RevisionDto revisionDto2 = new RevisionDto();
        assertThat(revisionDto1).isNotEqualTo(revisionDto2);
        revisionDto2.setId(revisionDto1.getId());
        assertThat(revisionDto1).isEqualTo(revisionDto2);
        revisionDto2.setId("id2");
        assertThat(revisionDto1).isNotEqualTo(revisionDto2);
        revisionDto1.setId(null);
        assertThat(revisionDto1).isNotEqualTo(revisionDto2);
    }
}
