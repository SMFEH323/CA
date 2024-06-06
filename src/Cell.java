
/**
 * @author Sayf Elhawary
 */
public interface Cell {

	public void initSim ();

	public boolean isSimOver ( int row, int col );

	public void doSimStep ();

	public String getStringColor ( int row, int col );

	public int getRows ();

	public int getCols ();

}
