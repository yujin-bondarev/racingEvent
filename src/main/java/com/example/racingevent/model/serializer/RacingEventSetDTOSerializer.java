package com.example.racingevent.model.serializer;

import com.example.racingevent.model.dto.RacingEventDTO;
import com.example.racingevent.model.entity.RacingEvent;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class RacingEventSetDTOSerializer extends StdSerializer<Set<RacingEvent>> {

    public RacingEventSetDTOSerializer() {
        this(null);
    }

    public RacingEventSetDTOSerializer(Class<Set<RacingEvent>> t) {
        super(t);
    }

    @Override
    public void serialize(Set<RacingEvent> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        Set<RacingEventDTO> dtoSet = value.stream().map(event -> {
            RacingEventDTO dto = new RacingEventDTO();
            dto.setId(event.getId());
            dto.setEventName(event.getEventName());
            dto.setDate(event.getDate());
            dto.setLocation(event.getLocation());
            return dto;
        }).collect(Collectors.toSet());
        gen.writeObject(dtoSet);
    }
}

