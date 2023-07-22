package a2_2001040173;

import utils.TextIO;

/**
 * @overview PCReport is the program that represent a tabular report for PCs
 */
public class PCReport {
	/**
	 * @effects
	 * 
	 *          <pre>
	 *  Generates report and then presents the result on a table.
	 *  
	 *  The report title is displayed in the middle of the top banner.
	 *  
	 *   All but the first column correspond to the PC attributes, the rows are data about the PC objects.
	 *   Thus, the second column corresponds to attribute model, the third corresponds to year, the fourth 
	 *   corresponds to manufacturer. 
	 *   The last column lists the string representations of the components of the PC objects. The first 
	 *   column sequentially displays the row numbers. Note that the widths of second and fourth columns 
	 *   are lengths of the corresponding attributes.  
	 *   
	 *   Widths of the first and third columns are 3 and 6 respectively. 
	 *   The fifth column is unrestricted in width.
	 *  
	 *  The cell values are properly aligned with the columns and are displayed right-justified, except for
	 *  the fifth column (to display components) which is left-aligned. The horizontal lines (as displayed 
	 *  by lines of hyphens in the report) are exactly 99 characters in width. The cell values need not be 
	 *  wrapped. Further, the boundary between two adjacent cells on same row are exactly one space (␣ ) apart.
	 *          </pre>
	 */
	public String displayReport(PC[] objs) {
		StringBuffer report = new StringBuffer();
		report.append(
				"---------------------------------------------------------------------------------------------------");
		report.append("\n");
		report.append(
				"                                           PCPROG REPORT                                          ");
		report.append("\n");

		report.append(
				"---------------------------------------------------------------------------------------------------");
		report.append("\n");
		String indexFormat = "%3d";
		String modelFormat = "%20s";
		String yearFormat = "%6d";
		String manufacturerFormat = "%15s";
		String compsFormat = "%s";
		for (int i = 0; i < objs.length; i++) {
			PC pc = objs[i];
			report.append(String.format(indexFormat, i + 1)).append(" ")
					.append(String.format(modelFormat, pc.getModel())).append(" ")
					.append(String.format(yearFormat, pc.getYear())).append(" ")
					.append(String.format(manufacturerFormat, pc.getManufacturer())).append(" ")
					.append(String.format(compsFormat, pc.getComps().getElements().toString())).append("\n");
		}

		report.append(
				"---------------------------------------------------------------------------------------------------");
		TextIO.putln(report);
		return report.toString();
	}
}
