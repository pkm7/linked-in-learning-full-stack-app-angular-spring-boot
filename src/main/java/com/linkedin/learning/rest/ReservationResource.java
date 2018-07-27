package com.linkedin.learning.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.linkedin.learning.common.ResourceConststants;
import com.linkedin.learning.entity.ReservationEntity;
import com.linkedin.learning.entity.RoomEntity;
import com.linkedin.learning.model.response.ReservableRoomResponse;
import com.linkedin.learning.model.response.ReservationResponse;
import com.linkedin.learning.repository.PagedRoomRepository;
import com.linkedin.learning.repository.ReservationRepository;
import com.linkedin.learning.repository.RoomRepository;
import com.linkedin.learning.request.ReservationRequest;

import convertor.RoomEntityToReservableRoomResponseConverter;

@RestController
@RequestMapping(ResourceConststants.ROOM_RESERVATION_V1)
@CrossOrigin
public class ReservationResource {

	@Autowired
	PagedRoomRepository pagedRoomRepository;
	@Autowired
	RoomRepository roomRepository;
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	ConversionService conversionService;
	
	@RequestMapping(path="", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<ReservableRoomResponse> getAvailableRooms(
			@RequestParam(value = "checkin")
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
			LocalDate checkin, 
			@RequestParam(value = "checkout")
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
			LocalDate checkout,
			Pageable pageable){
		
		Page<RoomEntity> roomList = pagedRoomRepository.findAll(pageable);
		
		return mapper(roomList);
	}
	
	
	@RequestMapping(path="/{roomId}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<RoomEntity> getRoomById(
				@PathVariable Long roomId){
		
		Optional<RoomEntity> room = roomRepository.findById(roomId);
		
		return new ResponseEntity<RoomEntity>(room.get(), HttpStatus.OK);
	}
	
	
	
	@RequestMapping(path="", method=RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReservationResponse> createReservations(
			@RequestBody
			ReservationRequest reservationRequest){
		
		ReservationEntity reservationEntity = conversionService.convert(reservationRequest, ReservationEntity.class);
		reservationRepository.save(reservationEntity);
		
		Optional<RoomEntity> roomEntity = roomRepository.findById(reservationRequest.getRoomId());
		roomEntity.get().addReservationEntity(reservationEntity);
		roomRepository.save(roomEntity.get());
		reservationEntity.setRoomEntity(roomEntity.get());
		
		ReservationResponse response = conversionService.convert(reservationEntity, ReservationResponse.class);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	
	@RequestMapping(path="", method=RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReservableRoomResponse> updateReservations(
			@RequestBody
			ReservationRequest reservationRequest){
		return new ResponseEntity<>(new ReservableRoomResponse(), HttpStatus.OK);
	}
	
	
	@RequestMapping(path="/{reservationId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteReservation(
			@PathVariable
			long reservationId){
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	
	private Page<ReservableRoomResponse> mapper(Page<RoomEntity> rooms){
		RoomEntityToReservableRoomResponseConverter conv = new RoomEntityToReservableRoomResponseConverter();
		List<ReservableRoomResponse> resList = new ArrayList<>();
		for (RoomEntity room : rooms) {
			resList.add(conv.convert(room));
		}
		return new PageImpl<>(resList);
	}
}

