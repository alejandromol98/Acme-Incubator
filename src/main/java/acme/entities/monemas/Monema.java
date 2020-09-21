
package acme.entities.monemas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import acme.entities.investmentRounds.InvestmentRound;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Monema extends DomainEntity {

	// Serialisation identifier ----------------------------------------------
	private static final long	serialVersionUID	= 1L;

	// Attributes ------------------------------------------------------------

	@NotBlank
	@Column(length = 1024)
	@Size(min = 0, max = 1024)
	private String				text;

	@NotNull
	@Valid
	@OneToOne(optional = false)
	private InvestmentRound		investmentRound;

}
