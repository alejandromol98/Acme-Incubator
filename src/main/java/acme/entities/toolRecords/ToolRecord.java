
package acme.entities.toolRecords;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ToolRecord extends DomainEntity {

	// Serialisation identifier ----------------------------------------------
	private static final long	serialVersionUID	= 1L;

	// Attributes ------------------------------------------------------------

	@NotBlank
	@Size(min = 0, max = 255)
	private String				title;

	@NotBlank
	@Size(min = 0, max = 255)
	private String				activitySector;

	@NotBlank
	@Size(min = 0, max = 255)
	private String				investor;

	@NotBlank
	@Size(min = 0, max = 255)
	private String				description;

	@NotBlank
	@Size(min = 0, max = 255)
	@URL
	private String				website;

	@NotBlank
	@Size(min = 0, max = 255)
	@Email
	private String				email;

	@NotNull
	private Source				source;

	@Range(min = -5, max = 5)
	private Integer				rate;

}
