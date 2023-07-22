package a2_2001040173;

import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.TextIO;

import static utils.TextIO.*;

import java.util.Vector;

/**
 * @overview PCProg is a program that captures data about PC objects and
 *           displays a report about them on the console.
 * 
 * @attributes <tt>
 *  objs  Set<PC> </tt>
 * 
 * @object <tt>
 *  A typical PCProg is {c1,...,cn} where c1,...,cn are pcs </tt>
 * 
 * @abstract_properties <tt>
 *  mutable(objs)=true /\ optional(objs)=false </tt>
 *
 */
public class PCProg {
	private static final Object YES = "Y";
	@DomainConstraint(mutable = true, optional = false)
	private Set<PC> objs;

	/**
	 * @effects <tt>
	 *  initialise this to have an empty set of PCs </tt>
	 */
	public PCProg() {
		objs = new Set<>();
	}

	/**
	 * @effects <tt>
	 *  if objs is not empty
	 *    display to the standard console a text-based tabular report on objs
	 *    return this report
	 *  else
	 *    display nothing and return null </tt>
	 */
	public String displayReport() {
		if (objs.size() > 0) {
			Vector<PC> pcs = objs.getElements();

			PCReport reportObj = new PCReport();
			return reportObj.displayReport(pcs.toArray(new PC[pcs.size()]));
		} else {
			return null;
		}
	}

	/**
	 * @effects <tt>
	 *  save report to a file pcs.txt in the same directory 
	 *  as the program's </tt>
	 */
	public void saveReport(String report) {
		String fileName = "pcs.txt";
		writeFile(fileName);
		putln(report);
		writeStandardOutput();
	}

	/**
	 * The run method
	 * 
	 * @effects <tt>
	 *  initialise an instance of PCProg 
	 *  create objects from data entered by the user
	 *  display a report on the objects 
	 *  prompt user to save report to file
	 *  if user answers "Y"
	 *    save report 
	 *  else
	 *    end </tt>
	 */
	public static void main(String[] args) {
		//
		PCProg prog = new PCProg();

		// create objects
		try {
			prog.createObjects();

			// display report
			String report = prog.displayReport();

			if (report != null) {
				// prompt user to save report
				putln("Save report to file? [Y/N]");
				String toSave = getln();
				if (toSave.equals("Y")) {
					prog.saveReport(report);
					putln("report saved");
				}
			}
		} catch (NotPossibleException e) {
			System.err.printf("%s: %s%n", e, e.getMessage());
		}

		putln("~END~");
	}

	/**
	 * @effects <tt>uses PCFactory to create a new PC object 
	 * 				and record it in objs and this method 
	 * 				should not invoke PC constructor directly.
	 * </tt>
	 */
	private void createObjects() {
		TextIO.putln("Create object" + "\nInput pc model: ");
		String model = TextIO.getln();

		TextIO.putln("Input PC year: ");
		int year = TextIO.getInt();
		TextIO.getln();

		TextIO.putln("Input manufacturer: ");
		String manufacturers = TextIO.getln();

		Set<String> comps = new Set<>();
		boolean hasNextComps = true;
		while (hasNextComps) {
			TextIO.putln("Input comp: ");
			String comp = TextIO.getln();
			comps.insert(comp);
			TextIO.putln("Continue to add comp? [Y/N]");
			hasNextComps = TextIO.getln().equals(YES);
		}

		PC pc = PCFactory.getInstance().createPC(model, year, manufacturers, comps);
		objs.insert(pc);

		TextIO.putln("Continue to add PC?[Y/N]");
		boolean continueAdd = TextIO.getln().equals(YES);
		if (continueAdd) {
			createObjects();
		}
	}

	/**
	 * @effects return the recorded PC objects
	 */
	public PC[] getObjects() {
		return objs.getElements().toArray(new PC[objs.size()]);
	}
}
