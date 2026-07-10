package com.nnoi.app.hospital_ms.service;

import com.nnoi.app.hospital_ms.entity.Room;
import com.nnoi.app.hospital_ms.exceptions.RoomNotFoundException;
import com.nnoi.app.hospital_ms.repository.RoomRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RoomService {

    private static final String ROOM_CACHE_KEY = "rooms";
    private RoomRepository roomRepository;
    private RedissonClient redissonClient;

    @PostConstruct
    public void loadRoomsIntoCache() {
        RMap<Integer, Room> roomCache = redissonClient.getMap(ROOM_CACHE_KEY);
        roomCache.clear();

        List<Room> rooms = fetchFromDbAndCache(roomCache);

        log.info("Loaded {} rooms into Redis cache [{}]", rooms.size(), ROOM_CACHE_KEY);
    }

    public List<Room> getRoomList() {
        RMap<Integer, Room> roomCache = redissonClient.getMap(ROOM_CACHE_KEY);

        if (!roomCache.isEmpty()) {
            log.debug("Returning {} rooms from Redis cache", roomCache.size());
            return new ArrayList<>(roomCache.values());
        }

        log.info("Redis cache empty for [{}] - falling back to DB", ROOM_CACHE_KEY);
        return fetchFromDbAndCache(roomCache);
    }

    private List<Room> fetchFromDbAndCache(RMap<Integer, Room> roomCache) {
        List<Room> rooms = roomRepository.getAllRooms();

        for (Room room : rooms) {
            roomCache.put(room.getRoomId(), room);
        }

        return rooms;
    }

    public Room getRoomById(Integer roomId) {
        RMap<Integer, Room> roomCache = redissonClient.getMap(ROOM_CACHE_KEY);
        Room room = roomCache.get(roomId);
        if (room != null) {
            return room;
        }

        room = roomRepository.getById(roomId);
        if (room == null) {
            throw new RoomNotFoundException("Room with id " + roomId + " not found", HttpStatus.NOT_FOUND);
        }
        roomCache.put(roomId, room);
        return room;
    }

}
