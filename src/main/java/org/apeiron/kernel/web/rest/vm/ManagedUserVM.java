package org.apeiron.kernel.web.rest.vm;

import org.apeiron.kernel.service.dto.AdminUserDto;

/**
 * View Model extending the AdminUserDto, which is meant to be used in the user management UI.
 */
public class ManagedUserVM extends AdminUserDto {

    public ManagedUserVM() {
        // Empty constructor needed for Jackson.
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ManagedUserVM{" + super.toString() + "} ";
    }
}
