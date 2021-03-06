
package acme.entities.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Entrepreneur extends UserRole {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Size(min = 0, max = 255)
	private String				startupName;

	@NotBlank
	@Size(min = 0, max = 255)
	private String				activitySector;

	@NotBlank
	@Size(min = 0, max = 255)
	private String				qualificationRecord;

	@NotBlank
	@Size(min = 0, max = 255)
	private String				skillsRecord;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
