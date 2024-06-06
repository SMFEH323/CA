
/**
 * @author Sayf Elhawary
 */
public class LifeCellB3 implements Cell {

	// grid dimensions
	private int gridRows_, gridCols_;

	// the world state
	private LifeState[][] grid_;

	public LifeCellB3 ( int gridRows, int gridCols ) {
		gridRows_ = gridRows;
		gridCols_ = gridCols;
	}

	public void initSim () {
		grid_ = new LifeState[gridRows_][gridCols_];
		for ( int row = 0 ; row < gridRows_ ; row++ ) {
			for ( int col = 0 ; col < gridCols_ ; col++ ) {
				grid_[row][col] = LifeState.DEAD;
			}
		}
		int midRow = gridRows_ / 2;
		int midCol = gridCols_ / 2;
		grid_[midRow][midCol] = LifeState.ALIVE;
		grid_[midRow + 1][midCol] = LifeState.ALIVE;
		grid_[midRow][midCol - 1] = LifeState.ALIVE;
		grid_[midRow - 1][midCol] = LifeState.ALIVE;
		grid_[midRow - 1][midCol + 1] = LifeState.ALIVE;
	}

	public boolean isSimOver ( int row, int col ) {
		return grid_[row][col] == LifeState.ALIVE;
	}

	public void doSimStep () {
		LifeState[][] newgrid = new LifeState[gridRows_][gridCols_];
		for ( int row = 0 ; row < gridRows_ ; row++ ) {
			for ( int col = 0 ; col < gridCols_ ; col++ ) {
				if ( grid_[row][col] == LifeState.ALIVE
				    && countLivingCells(row,col) < 2 ) {
					newgrid[row][col] = LifeState.DEAD;
				} else if ( grid_[row][col] == LifeState.ALIVE
				    && (countLivingCells(row,col) == 2
				        || countLivingCells(row,col) == 3) ) {
					        newgrid[row][col] = LifeState.ALIVE;
				        } else
				  if ( grid_[row][col] == LifeState.ALIVE
				      && countLivingCells(row,col) > 3 ) {
					      newgrid[row][col] = LifeState.DEAD;
				      } else
				    if ( grid_[row][col] == LifeState.DEAD
				        && countLivingCells(row,col) == 3 ) {
					        newgrid[row][col] = LifeState.ALIVE;
				        } else {
					        newgrid[row][col] = grid_[row][col];
				        }

			}
		}
		grid_ = newgrid;
	}

	private int countLivingCells ( int row, int col ) {
		int count = 0;
		for ( int i = -1 ; i <= 1 ; i++ ) {
			for ( int j = -1 ; j <= 1 ; j++ ) {
				if ( ((row + i >= 0) && (row + i < gridRows_) && (col + j >= 0)
				    && (col + j < gridCols_))
				    && grid_[row + i][col + j] == LifeState.ALIVE ) {
					count++;
				}
			}
		}
		if ( grid_[row][col] == LifeState.ALIVE ) {
			count--;
		}
		return count;
	}

	public String getStringColor ( int row, int col ) {
		if ( grid_[row][col] == LifeState.ALIVE ) {
			return "#000000";
		} else if ( grid_[row][col] == LifeState.DEAD ) {
			return "#FFFFFF";
		} else {
			return "#FFFFFF";
		}
	}

	public int getRows () {
		return gridRows_;
	}

	public int getCols () {
		return gridCols_;
	}
}
