package com.osdb.octipod.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public interface Username {
	@NotNull
	@Size(min = 10)
	String getUsername();
}
