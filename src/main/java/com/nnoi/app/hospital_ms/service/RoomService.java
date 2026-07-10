package com.nnoi.app.hospital_ms.service;

import com.nnoi.app.hospital_ms.entity.Room;
import com.nnoi.app.hospital_ms.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {

    private RoomRepository roomRepository;

    public List<Room> getRoomList() {
        return roomRepository.getAllRooms();
    }
}
