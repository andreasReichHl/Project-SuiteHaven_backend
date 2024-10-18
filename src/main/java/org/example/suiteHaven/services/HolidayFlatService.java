package org.example.suiteHaven.services;

import org.example.suiteHaven.dtos.holidayFlat.HolidayFlatRequestDto;
import org.example.suiteHaven.entities.amenity.Amenity;
import org.example.suiteHaven.entities.booking.FlatManagement;
import org.example.suiteHaven.entities.flat.Bed;
import org.example.suiteHaven.entities.flat.Bedroom;
import org.example.suiteHaven.entities.flat.HolidayFlat;
import org.example.suiteHaven.entities.flat.Room;
import org.example.suiteHaven.entities.users.User;
import org.example.suiteHaven.repositories.HolidayFlatRepository;
import org.example.suiteHaven.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class HolidayFlatService {

    HolidayFlatRepository holidayFlatRepository;
    UserRepository userRepository;

    public HolidayFlatService(HolidayFlatRepository holidayFlatRepository, UserRepository userRepository) {
        this.holidayFlatRepository = holidayFlatRepository;
        this.userRepository = userRepository;
    }

    public HolidayFlat createNewHolidayFlat(HolidayFlatRequestDto dto) {
        User user = getUserProfile(dto);
        HolidayFlat holidayFlat = buildHolidayFlat(dto, user);

        List<Bedroom> bedrooms = createSleepingRooms(dto, holidayFlat);
        holidayFlat.setBedrooms(bedrooms);
        List<Room> roomList = createRooms(dto, holidayFlat);
        holidayFlat.setRooms(roomList);
        List<Amenity> amenityList = createAmenity(dto, holidayFlat);
        holidayFlat.setAmenities(amenityList);

        FlatManagement flatManagement = new FlatManagement();
        holidayFlat.setManagement(flatManagement);
        flatManagement.setHolidayFlat(holidayFlat);
        return holidayFlatRepository.save(holidayFlat);
    }

    private static List<Room> createRooms(HolidayFlatRequestDto dto, HolidayFlat holidayFlat) {
        return Arrays.stream(dto.rooms())
                .map(element -> {
                    Room room = new Room(element.getName(), element.getNumber(), element.getHolidayFlat());
                    room.setHolidayFlat(holidayFlat);
                    return room;
                }).toList();
    }

    private static List<Bedroom> createSleepingRooms(HolidayFlatRequestDto dto, HolidayFlat holidayFlat) {
        return Arrays.stream(dto.bedrooms())
                .map(element -> {
                    Bedroom bedroom = new Bedroom();
                    bedroom.setHolidayFlat(holidayFlat);
                    bedroom.setRoomNumber(element.getRoomNumber());
                    List<Bed> bedList = element.getBeds().stream()
                            .map(bedRequest -> {
                                Bed bed = new Bed();
                                bed.setBedType(bedRequest.getBedType());
                                bed.setQuantity(bedRequest.getQuantity());
                                bed.setBedroom(bedroom);
                                return bed;
                            }).toList();
                    bedroom.setBeds(bedList);
                    return bedroom;
                }).toList();
    }

    private static HolidayFlat buildHolidayFlat(HolidayFlatRequestDto dto, User user) {
        HolidayFlat holidayFlat = new HolidayFlat(dto.title(), dto.city());
        holidayFlat.setCountry(dto.country());
        holidayFlat.setPrice(dto.price());
        holidayFlat.setFinalCleaningPrice(dto.cleaning());
        holidayFlat.setDescription(dto.description());
        holidayFlat.setPersonNumber(dto.person());
        holidayFlat.setUser(user);
        return holidayFlat;
    }

    private User getUserProfile(HolidayFlatRequestDto dto) {
        return userRepository.findById(dto.hostId()).orElseThrow(() -> new NoSuchElementException("Host not found"));
    }

    private List<Amenity> createAmenity(HolidayFlatRequestDto dto, HolidayFlat holidayFlat) {
        return Arrays.stream(dto.amenities())
                .map(amenityType -> {
                    Amenity amenity = new Amenity();
                    amenity.setAmenityType(amenityType.getAmenityType()); // Verwende direkt amenityType, da es bereits ein Enum ist
                    amenity.setHolidayFlat(holidayFlat);
                    return amenity;
                })
                .toList();
    }
    public void deleteFlat(long id){
        HolidayFlat flat = holidayFlatRepository.findById(id).orElseThrow(()-> new NoSuchElementException("HolidayFlat not founded"));
        holidayFlatRepository.delete(flat);
    }
}
