

public class UserScore {

	// fields of the class
	private String username;
	private int score;

	// constructor to create an object
	public UserScore(String username, int score) {
		super();
		this.username = username;
		this.score = score;
	}

	public String getUsername() {
		return username;
	}

	// setters and getters of the fields

	public void setUsername(String username) {
		// if username is null set to "N/A"
		if (username == null) {
			this.username = "N/A";
		} else
			this.username = username;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {

		// if score is negative set to 0
		if (score < 0) {
			System.out.println("Invalid Score. Set to 0.");
			this.score = 0;
		} else
			this.score = score;
	}

	// to string to make it readable in console
	@Override
	public String toString() {
		return "userScore [username=" + username + ", score=" + score + "]";
	}

}
