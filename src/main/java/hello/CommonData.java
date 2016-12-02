package hello;

public class CommonData {

	private long roomId;
	private String nfcSerial;
	private String roomName;
	private long state;
	private String time;
	private String theNearestEmptyRoom;

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public String getNfcSerial() {
		return nfcSerial;
	}

	public void setNfcSerial(String nfcSerial) {
		this.nfcSerial = nfcSerial;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public long getState() {
		return state;
	}

	public void setState(long state) {
		this.state = state;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTime() {
		return time;
	}

	public String getTheNearestEmptyRoom() {
		return theNearestEmptyRoom;
	}

	public void setTheNearestEmptyRoom(String theNearestEmptyRoom) {
		this.theNearestEmptyRoom = theNearestEmptyRoom;
	}
}
