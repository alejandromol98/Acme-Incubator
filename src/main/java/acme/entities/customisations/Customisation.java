
package acme.entities.customisations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customisation extends DomainEntity {

	//Serialisation indetifier ------------------------

	private static final long	serialVersionUID	= 1L;

	//Atributes ---------------------------------------

	@Column(length = 1024)
	@NotBlank
	@Size(min = 0, max = 1024)
	private String				spamwords;

	@Range(min = 0, max = 100)
	@NotNull
	private Double				threshold;

	@Column(length = 1024)
	@NotBlank
	@Size(min = 0, max = 1024)
	private String				activitySectors;

}
