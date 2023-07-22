package a2_2001040173;

import utils.AttrRef;
import utils.OptType;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.DOpt;

/**
 * @overview PCFactory is a singleton class that has a factory method for
 *           creating PCs
 */
public class PCFactory {
	@DomainConstraint(type = "PCFactory", mutable = false, optional = false)
	private static PCFactory instance;

	@DOpt(type = OptType.Constructor)
	private PCFactory() {
		// singleton
	}

	@DOpt(type = OptType.Helper)
	@AttrRef("instance")
	public static PCFactory getInstance() {
		if (instance == null) {
			instance = new PCFactory();
		}
		return instance;
	}

	public PC createPC(String model, int year, String manufacturer, Set<String> comps) throws NotPossibleException {
		return new PC(model, year, manufacturer, comps);
	}
}