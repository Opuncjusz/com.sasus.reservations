package hello.domain.service;

import org.springframework.stereotype.Service;

import hello.CommonData;
import hello.Connect;
import hello.domain.Device;

@Service
public interface ReservationService {

	public CommonData handleRequest(CommonData commonData);

	public Device handleConnection(CommonData connect);
}
