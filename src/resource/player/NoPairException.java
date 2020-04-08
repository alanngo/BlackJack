package resource.player;

class NoPairException extends RuntimeException// used for the split function
{
	private static final long serialVersionUID = 1694935917440101962L;

	 NoPairException(String message){ super(message); }
}
