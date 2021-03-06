package hello.domain.repository;

import java.time.LocalDateTime;

import hello.domain.Device;

public interface IDeviceRepository {

	public void disconnectDevice(Device d);

	public Device connectDevice(String idDevice, String roomName);

	public void sendStatus(LocalDateTime timestamp);

}
