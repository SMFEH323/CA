
/**
 * @author Sayf Elhawary
 */
public class Controller {

	// grid dimensions
	private int gridRows_, gridCols_;

	private Cell cell_;

	public Controller ( Cell cell ) {
		cell_ = cell;
		gridRows_ = cell.getRows();
		gridCols_ = cell.getCols();
	}

	/**
	 * Initialize the starting state of the cells in the grid.
	 */
	public void initSim () {
		cell_.initSim();
	}

	/**
	 * Determine if the simulation is complete.
	 * 
	 * @return true if the simulation is complete, false if not
	 */
	public boolean isSimOver () {
		// complete if there are no burning cells
		for ( int row = 0 ; row < gridRows_ ; row++ ) {
			for ( int col = 0 ; col < gridCols_ ; col++ ) {
				if ( cell_.isSimOver(row,col) ) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Do one step of the simulation.
	 */
	public void doSimStep () {
		cell_.doSimStep();
	}

	public String getStringColor ( int row, int col ) {
		return cell_.getStringColor(row,col);
	}
}
