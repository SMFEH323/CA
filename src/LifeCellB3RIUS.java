
/**
 * @author Sayf Elhawary
 */
public class LifeCellB3RIUS implements Cell {

	// grid dimensions
	private int gridRows_, gridCols_;

	// the world state
	private LifeState[][] grid_;

	public LifeCellB3RIUS ( int gridRows, int gridCols ) {
		gridRows_ = gridRows;
		gridCols_ = gridCols;
	}

	public void initSim () {
		grid_ = new LifeState[gridRows_][gridCols_];
		for ( int row = 0 ; row < gridRows_ ; row++ ) {
			for ( int col = 0 ; col < gridCols_ ; col++ ) {
				if ( Math.random() < 0.30 ) {
					grid_[row][col] = LifeState.ALIVE;
				} else {
					grid_[row][col] = LifeState.DEAD;
				}
			}
		}
	}

	public boolean isSimOver ( int row, int col ) {
		return grid_[row][col] == LifeState.ALIVE;
	}

	public void doSimStep () {
		int row = (int) (Math.random() * gridRows_);
		int col = (int) (Math.random() * gridCols_);
		if ( grid_[row][col] == LifeState.ALIVE && countLivingCells(row,col) < 2 ) {
			grid_[row][col] = LifeState.DEAD;
		} else
		  if ( grid_[row][col] == LifeState.ALIVE && (countLivingCells(row,col) == 2
		      || countLivingCells(row,col) == 3) ) {
			      grid_[row][col] = LifeState.ALIVE;
		      } else
		    if ( grid_[row][col] == LifeState.ALIVE
		        && countLivingCells(row,col) > 3 ) {
			        grid_[row][col] = LifeState.DEAD;
		        } else
		      if ( grid_[row][col] == LifeState.DEAD
		          && countLivingCells(row,col) == 3 ) {
			          grid_[row][col] = LifeState.ALIVE;
		          } else {
			          grid_[row][col] = grid_[row][col];
		          }
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
