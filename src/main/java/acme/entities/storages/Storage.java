
package acme.entities.storages;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Storage extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String				firmName;

	@NotBlank
	private String				responsibilityStatement;

	@NotNull
	private StorageStatus		status;

	@NotNull
	@Valid
	@OneToOne(optional = false)
	private Authenticated		authenticated;

}
