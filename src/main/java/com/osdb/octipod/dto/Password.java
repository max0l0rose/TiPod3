package com.osdb.octipod.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public interface Password {
	@NotNull
	@Size(min = 3)
	String getPassword();
}
