package org.glehenaff.gestform.dao;

import java.sql.SQLException;

public class AlreadyExistsException extends SQLException {

	private static final long serialVersionUID = 1L;

	public AlreadyExistsException(Throwable cause) {
		super(cause);
	}

}
