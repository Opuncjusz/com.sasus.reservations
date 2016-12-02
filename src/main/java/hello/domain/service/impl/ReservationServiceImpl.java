package hello.domain.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.CommonData;
import hello.Connect;
import hello.domain.Device;
import hello.domain.Group;
import hello.domain.Room;
import hello.domain.User;
import hello.domain.repository.IDeviceRepository;
import hello.domain.repository.impl.ReservationRepository;
import hello.domain.repository.impl.UserRepository;
import hello.domain.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	IDeviceRepository deviceRepository;

	public CommonData handleRequest(CommonData commonData) {

		CommonData responseData = new CommonData();
		//
		// User user = new User("wydmuszka", "wydymka");
		// Room room = new Room("wydmuch", new Group("grupa wydymka"));
		//
		// LocalDateTime start = LocalDateTime.now();
		// LocalDateTime stop = start.plusMinutes(30);

		// reservationRepository.reserveRoom(user, room, start, stop);

		// commonData.setState(1);

		return responseData;
	}

	@Override
	public Device handleConnection(CommonData connect) {
		Device device = deviceRepository.connectDevice(connect.getRoomName(), connect.getRoomId() + "");
		CommonData responseData = new CommonData();
		return device;
	}

}
