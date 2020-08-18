
package acme.entities.challenges;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Challenge extends DomainEntity {

	// Serialisation identifier ----------------------------------------------
	private static final long	serialVersionUID	= 1L;

	// Attributes ------------------------------------------------------------

	@NotBlank
	@Size(min = 0, max = 255)
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@NotBlank
	@Size(min = 0, max = 255)
	private String				description;

	@NotBlank
	@Size(min = 0, max = 255)
	private String				goalRookie;

	@NotBlank
	@Size(min = 0, max = 255)
	private String				goalAverage;

	@NotBlank
	@Size(min = 0, max = 255)
	private String				goalExpert;

	@NotNull
	private Money				rewardRookie;

	@NotNull
	private Money				rewardAverage;

	@NotNull
	private Money				rewardExpert;

}
