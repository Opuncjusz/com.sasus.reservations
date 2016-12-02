package hello.domain;

public class User {


	private String name;
	private String cardId;
	public User(String name, String cardId) {
		this.name=name;
		this.cardId=cardId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
}
