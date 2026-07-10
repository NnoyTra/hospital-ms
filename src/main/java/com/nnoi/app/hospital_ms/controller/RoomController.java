package com.nnoi.app.hospital_ms.controller;

import com.nnoi.app.hospital_ms.entity.Room;
import com.nnoi.app.hospital_ms.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/room")
@AllArgsConstructor
public class RoomController {

    private RoomService roomService;

    @GetMapping
    List<Room> getDoctors() {
        return roomService.getRoomList();
    }
}
