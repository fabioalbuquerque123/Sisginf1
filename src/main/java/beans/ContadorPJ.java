package beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="CONT")
public class ContadorPJ extends PessoaFisica{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7762319978547185233L;

}
