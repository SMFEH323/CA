
/**
 * @author Sayf Elhawary
 */
public class FireCellRandom implements Cell {

	// grid dimensions
	private int gridRows_, gridCols_;
	// probability of a tree cell catching fire if it has at least one neighbor
	// on fire
	private double probcatch_;

	private double probTree_;
	// probability that a tree (burning or not burning) initially occupies a site.

	private double probBurning_;
	// If a site has a tree, the probability that the tree is initially burning,
	// or that the grid site is BURNING.

	// the world state
	private FireState[][] grid_;

	public FireCellRandom ( int gridRows, int gridCols, double probcatch,
	                        double probTree, double probBurning ) {
		gridRows_ = gridRows;
		gridCols_ = gridCols;
		probcatch_ = probcatch;
		probTree_ = probTree;
		probBurning_ = probBurning;
		initSim();
	}

	public void initSim () {
		grid_ = new FireState[gridRows_][gridCols_];
		for ( int row = 0 ; row < gridRows_ ; row++ ) {
			for ( int col = 0 ; col < gridCols_ ; col++ ) {
				if ( Math.random() < probTree_ ) {
					if ( Math.random() < probBurning_ ) {
						grid_[row][col] = FireState.BURNING;
					} else {
						grid_[row][col] = FireState.TREE;
					}
				} else {
					grid_[row][col] = FireState.EMPTY;
				}
			}
		}
	}

	public boolean isSimOver ( int row, int col ) {
		return grid_[row][col] == FireState.BURNING;
	}

	@Override
	public void doSimStep () {
		FireState[][] newgrid = new FireState[gridRows_][gridCols_];
		for ( int row = 0 ; row < gridRows_ ; row++ ) {
			for ( int col = 0 ; col < gridCols_ ; col++ ) {
				if ( grid_[row][col] == FireState.EMPTY ) {
					newgrid[row][col] = FireState.EMPTY;
				} else if ( grid_[row][col] == FireState.BURNING ) {
					newgrid[row][col] = FireState.EMPTY;
				} else if ( grid_[row][col] == FireState.TREE ) {
					if ( ((row > 0 && grid_[row - 1][col] == FireState.BURNING)
					    || (col > 0 && grid_[row][col - 1] == FireState.BURNING)
					    || (row < gridRows_ - 1
					        && grid_[row + 1][col] == FireState.BURNING)
					    || (col < gridCols_ - 1
					        && grid_[row][col + 1] == FireState.BURNING))
					    && Math.random() < probcatch_ ) {
						newgrid[row][col] = FireState.BURNING;
					} else {
						newgrid[row][col] = FireState.TREE;
					}
				}
			}
		}
		grid_ = newgrid;
	}

	public String getStringColor ( int row, int col ) {
		if ( grid_[row][col] == FireState.TREE ) {
			return "#008000";
		} else if ( grid_[row][col] == FireState.BURNING ) {
			return "#FF0000";
		} else if ( grid_[row][col] == FireState.EMPTY ) {
			return "#FFFF00";
		} else {
			return "#000000";
		}
	}

	public int getRows () {
		return gridRows_;
	}

	public int getCols () {
		return gridCols_;
	}

}
