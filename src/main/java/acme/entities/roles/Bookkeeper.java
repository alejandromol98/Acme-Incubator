
package acme.entities.roles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bookkeeper extends UserRole {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Size(min = 0, max = 255)
	private String				firmName;

	@NotBlank
	@Column(length = 1024)
	@Size(min = 0, max = 1024)
	private String				responsibilityStatement;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
