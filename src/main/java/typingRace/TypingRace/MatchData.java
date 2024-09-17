package typingRace.TypingRace;

public class MatchData {
	
	private String position;
	private String wpm;
	
	public MatchData(String posizione, String wpm) {
		this.position=posizione;
		this.wpm=wpm;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String posizione) {
		this.position = posizione;
	}

	public String getWpm() {
		return wpm;
	}

	public void setWpm(String wpm) {
		this.wpm = wpm;
	}
}