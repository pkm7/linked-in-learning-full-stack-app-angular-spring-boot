package convertor;


import org.springframework.core.convert.converter.Converter;

import com.linkedin.learning.common.ResourceConststants;
import com.linkedin.learning.entity.RoomEntity;
import com.linkedin.learning.model.Links;
import com.linkedin.learning.model.Self;
import com.linkedin.learning.model.response.ReservableRoomResponse;


public class RoomEntityToReservableRoomResponseConverter implements Converter<RoomEntity, ReservableRoomResponse>{

	@Override
	public ReservableRoomResponse convert(RoomEntity room) {
		
		ReservableRoomResponse response = new ReservableRoomResponse();
		response.setRoomNumber(room.getRoomNumber());
		response.setPrice(Integer.valueOf(room.getPrice()));
		
		Links links = new Links();
		Self self = new Self();
		self.setRef(ResourceConststants.ROOM_RESERVATION_V1+"/"+room.getId());
		
		links.setSelf(self);
		response.setLinks(links);
		
		return response;
	}

	
	
}
