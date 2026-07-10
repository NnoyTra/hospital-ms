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
        StringBuilder sql = new StringBuilder("SELECT * FROM " + ROOM);
        Map<String, String> params = new HashMap<>();
        List<Room> resultSet = namedTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<>(Room.class));
        return resultSet;
    }
}
