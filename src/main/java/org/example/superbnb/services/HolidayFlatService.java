package org.example.superbnb.services;

import org.example.superbnb.dtos.holidayFlat.HolidayFlatRequestDto;
import org.example.superbnb.entities.*;
import org.example.superbnb.repositories.HolidayFlatRepository;
import org.example.superbnb.repositories.UserProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class HolidayFlatService {

    HolidayFlatRepository holidayFlatRepository;
    UserProfileRepository userProfileRepository;

    public HolidayFlatService(HolidayFlatRepository holidayFlatRepository, UserProfileRepository userProfileRepository) {
        this.holidayFlatRepository = holidayFlatRepository;
        this.userProfileRepository = userProfileRepository;
    }

    public HolidayFlat createNewHolidayFlat(HolidayFlatRequestDto dto){
        UserProfile userProfile = userProfileRepository.findById(dto.hostId()).orElseThrow(()->new NoSuchElementException("Host not found"));
        HolidayFlat holidayFlat = new HolidayFlat(dto.title(), dto.city());
        holidayFlat.setCountry(dto.country());
        holidayFlat.setPrice(dto.price());
        holidayFlat.setFinalCleaningPrice(dto.cleaning());
        holidayFlat.setDescription(dto.description());
        holidayFlat.setPersonNumber(dto.person());
        holidayFlat.setUser(userProfile.getUser());
        List<SleepingRoom> sleepingRooms = Arrays.stream(dto.sleepingRooms())
                .map(element -> {
                    SleepingRoom sleepingRoom = new SleepingRoom(
                            element.getBedOne(),
                            element.getNumberBedOne(),
                            element.getBedTwo(),
                            element.getNumberBedTwo()
                    );
                    sleepingRoom.setHolidayFlat(holidayFlat);
                    return sleepingRoom;
                }).toList();
        holidayFlat.setSleepingRooms(sleepingRooms);
        List<Room> roomList = Arrays.stream(dto.rooms())
                .map(element -> {
                   Room room = new Room(element.getName(), element.getNumber(), element.getHolidayFlat());
                   room.setHolidayFlat(holidayFlat);
                   return room;
                }).toList();
        holidayFlat.setRooms(roomList);
        return holidayFlatRepository.save(holidayFlat);
    }
}
