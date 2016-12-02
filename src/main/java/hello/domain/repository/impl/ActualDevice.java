package hello.domain.repository.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hello.domain.Device;
import hello.domain.DeviceMessage;
import hello.domain.Room;
import hello.domain.repository.IDeviceRepository;
import hello.domain.repository.IGroupRepository;
import hello.domain.repository.IReservationRepository;

@Repository
public class ActualDevice implements IDeviceRepository{

	public Map<Room,Device> connectedDevices;
	
	@Autowired
	public IGroupRepository allRooms;
	
	@Autowired
	public IReservationRepository reservations;
	
	
	public ActualDevice() {
		connectedDevices = new HashMap<Room,Device>();
		//connectDevice("Tablet 1","1.01");
		//connectDevice("Tablet 2","1.02");		

	}

	@Override
	public void connectDevice(String idDevice,String roomName) {
		Device d=new Device(idDevice,allRooms.getRoom(roomName));
		System.out.println("Urządzenie "+idDevice+" przy pokoju" + roomName + "zostało uruchomione");
		connectedDevices.put(d.room,d);		
	}

	@Override
	public void disconnectDevice(Device d) {
		connectedDevices.remove(d);	
	}

	@Override
	public void sendStatus(LocalDateTime timestamp) {
		//aktualizacja tabletów
		DeviceMessage msg=null;
		Device dev=null;
		for(Room room:allRooms.getAllRooms()){
			msg=null;
			dev=null;
			msg=reservations.getRoomStatus(room, LocalDateTime.now());
			dev=connectedDevices.get(room);
			if(dev!=null){
				System.out.println("tu następuje wysyłka danych do urządzenia" + dev.idDevice + "\n przy sali "+ room.name +"\n o statusie: "+msg.status + " \n "+ msg.additionalInfo);
			}
		}
		
	}

}
