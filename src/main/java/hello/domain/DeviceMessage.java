package hello.domain;

import java.time.LocalDateTime;

public class DeviceMessage {

	public int idStatus;
	public String status;
	public String additionalInfo;
	public LocalDateTime updateDate;

	public DeviceMessage() {

	}

	public DeviceMessage(int idStatus, String status, String additionalInfo, LocalDateTime updateDate) {
		this.idStatus = idStatus;
		this.status = status;
		this.additionalInfo = additionalInfo;
		this.updateDate = updateDate;
	}

}
