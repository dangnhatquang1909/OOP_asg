package a2_2001040173;

import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;

/**
 * @overview PC is a computer that is used mainly by people at home rather than
 *           by large organizations
 * @attributes
 * 
 *             <pre>
 * 		model			String
 * 		year			Integer			int 
 * 		manufacturer	String
 * 		comps			Set<String>
 *             </pre>
 * 
 * @object A typical PC is p=<m,y,mf,c>, where m(model), y(year),
 *         mf(manufacturer), c(comps)
 * @abstract_properties
 * 
 *                      <pre>
 * mutable(model) = true /\ optional(model) = false /\ length(model) = 20 /\
 * mutable(year) = false /\ optional(year) = false /\ min(year) = 1984 /\
 * mutable(manufacturer) = false /\ optional(manufacturer) = false /\ length(manufacturer) = 15 /\ 
 * mutable(comps) = true /\ optional(comps) = false
 *                      </pre>
 */
public class PC {
	// constants
	private static final int LENGTH_MODEL = 20;
	private static final double MIN_YEAR = 1984;
	private static final int LENGTH_MANUFACTURER = 15;
	// start state spaces
	@DomainConstraint(type = "String", mutable = true, optional = false, length = LENGTH_MODEL)
	private String model;
	@DomainConstraint(type = "Integer", mutable = false, optional = false, min = MIN_YEAR)
	private int year;
	@DomainConstraint(type = "String", mutable = false, optional = false, length = LENGTH_MANUFACTURER)
	private String manufacturer;
	@DomainConstraint(type = "Set<String>", mutable = true, optional = false)
	private Set<String> comps;
	// end state spaces

	// Constructor
	/**
	 * @effects
	 * 
	 *          <pre>
	 * 		if model, year, manufacturer, comps are valid
	 * 			initialize this as <m,y,mf,c>
	 * 		else
	 * 			throws NotPossibleException
	 *          </pre>
	 */
	@DOpt(type = OptType.Constructor)
	public PC(@AttrRef("model") String model, @AttrRef("year") int year, @AttrRef("manufacturer") String manufacturer,
			@AttrRef("comps") Set<String> comps) {
		if (!validateModel(model)) {
			throw new NotPossibleException("Invalid model:" + model);
		}
		if (!validateYear(year)) {
			throw new NotPossibleException("Invalid year:" + year);
		}
		if (!validateManufacturer(manufacturer)) {
			throw new NotPossibleException("Invalid manufacturer:" + manufacturer);
		}
		if (!validateComps(comps)) {
			throw new NotPossibleException("Invalid comps:" + comps);
		}
		// initialize this as <this.model, this.year,this.manufacturer, this.comps>
		this.model = model;
		this.year = year;
		this.manufacturer = manufacturer;
		this.comps = comps;
	}

	// Observer
	/**
	 * @effects return this.model
	 */
	@DOpt(type = OptType.Observer)
	@AttrRef("model")
	public String getModel() {
		return this.model;
	}

	/**
	 * @effects return this.year
	 */
	@DOpt(type = OptType.Observer)
	@AttrRef("year")
	public int getYear() {
		return this.year;
	}

	/**
	 * @effects return this.manufacturer
	 */
	@DOpt(type = OptType.Observer)
	@AttrRef("manufacturer")
	public String getManufacturer() {
		return this.manufacturer;
	}

	/**
	 * @effects return this.comps
	 */
	@DOpt(type = OptType.Observer)
	@AttrRef("comps")
	public Set<String> getComps() {
		return this.comps;
	}

	// Mutator
	/**
	 * @effects
	 * 
	 *          <pre>
	 * 		if model is valid
	 * 			set this.model = model
	 * 			return true
	 * 		else
	 * 			return false
	 *          </pre>
	 */
	@DOpt(type = OptType.Mutator)
	@AttrRef("model")
	public boolean setModel(String model) {
		if (!validateModel(model)) {
			return false;
		}
		this.model = model;
		return true;
	}

	/**
	 * @effects
	 * 
	 *          <pre>
	 * 		if comps if valid
	 * 			set this.comps = comps
	 * 			return true
	 * 		else
	 * 			return false
	 *          </pre>
	 */
	@DOpt(type = OptType.Mutator)
	@AttrRef("comps")
	public boolean setComps(Set<String> comps) {
		if (!validateComps(comps)) {
			return false;
		}
		this.comps = comps;
		return true;
	}

	// Helper
	// Validators (validate value vs domain constraint):attrs -> validators
	// validate optional, length, min, max, others
	/**
	 * @effects
	 * 
	 *          <pre>
	 * 		if comps is valid
	 * 			return true
	 * 		else
	 * 			return false
	 *          </pre>
	 */
	@DOpt(type = OptType.Helper)
	@AttrRef("comps")
	private boolean validateComps(Set<String> comps) {
		return comps != null;
	}

	/**
	 * @effects
	 * 
	 *          <pre>
	 *		if manufacturer is valid
	 *			return true
	 *		else
	 *			return false
	 *          </pre>
	 */
	@DOpt(type = OptType.Helper)
	@AttrRef("manufacturer")
	private boolean validateManufacturer(String manufacturer) {
		return manufacturer != null && !manufacturer.isEmpty() && manufacturer.length() <= LENGTH_MANUFACTURER;
	}

	/**
	 * @effects
	 * 
	 *          <pre>
	 * 		if year is valid
	 * 			return true
	 * 		else 	
	 * 			return false
	 *          </pre>
	 */
	@DOpt(type = OptType.Helper)
	@AttrRef("year")
	private boolean validateYear(int year) {
		return year >= MIN_YEAR;
	}

	/**
	 * @effects
	 * 
	 *          <pre>
	 * 		if model is valid
	 * 			return true
	 * 		else 
	 * 			return false
	 *          </pre>
	 */
	@DOpt(type = OptType.Helper)
	@AttrRef("model")
	private boolean validateModel(String model) {
		return model != null && !model.isEmpty() && model.length() <= LENGTH_MODEL;
	}

	/**
	 * @effects
	 * 
	 *          <pre>
	 * 		if this satisfies abstract properties
	 * 			return true
	 * 		else
	 * 			return false
	 *          </pre>
	 */
	@DOpt(type = OptType.Helper)
	public boolean repOK() {
		return validateModel(this.model) && validateYear(this.year) && validateManufacturer(this.manufacturer) && validateComps(this.comps);
	}

	// Default
	/**
	 * @effects return the data contains all properties of PC
	 */
	@DOpt(type = OptType.Default)
	@Override
	public String toString() {
		return "PC<" + model + "," + year + "," + manufacturer + "," + comps.toString() + ">";
	}

	/**
	 * @effects
	 * 
	 *          <pre>
	 *		if the properties are equal 
	 *     		return true 
	 *  	else 
	 *  		return false
	 *          </pre>
	 */
	@DOpt(type = OptType.Default)
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof PC))
			return false;
		PC pc = (PC) o;
		return getYear() == pc.getYear() && getModel().equals(pc.getModel())
				&& getManufacturer().equals(pc.getManufacturer()) && getComps().equals(pc.getComps());
	}
}