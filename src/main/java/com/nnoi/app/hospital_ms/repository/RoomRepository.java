package com.nnoi.app.hospital_ms.repository;

import com.nnoi.app.hospital_ms.entity.Room;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class RoomRepository {

    private final String ROOM = "room";
    private NamedParameterJdbcTemplate namedTemplate;

    public List<Room> getAllRooms() {
        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(ROOM);
        Map<String, Object> params = new HashMap<>();
        List<Room> resultSet = namedTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<>(Room.class));
        return resultSet;
    }

    public Room getById(Integer roomId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(ROOM).append(" WHERE room_id = :roomId");
        Map<String, Object> params = new HashMap<>();
        params.put("roomId", String.valueOf(roomId));
        List<Room> resultSet = namedTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<>(Room.class));
        return resultSet.stream()
                .findFirst()
                .orElse(null);
    }

    public boolean roomAvailable(Integer roomId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(ROOM).append(" WHERE room_id = :roomId").append(" AND is_reserved = FALSE");
        Map<String, Object> params = new HashMap<>();
        params.put("roomId", roomId);
        List<Room> resultSet = namedTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<>(Room.class));
        return !resultSet.isEmpty();
    }

    public boolean roomInMaintenance(Integer roomId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(ROOM).append(" WHERE room_id = :roomId").append(" AND in_maintenance = TRUE");
        Map<String, Object> params = new HashMap<>();
        params.put("roomId", String.valueOf(roomId));
        List<Room> resultSet = namedTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<>(Room.class));
        return resultSet.isEmpty();
    }
}
