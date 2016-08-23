package beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="RESP")
public class ResponsavelLegalPJ extends PessoaFisica{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3903506387283008071L;

}
