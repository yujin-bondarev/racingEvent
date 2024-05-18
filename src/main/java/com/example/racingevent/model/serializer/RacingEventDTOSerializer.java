package com.example.racingevent.model.serializer;

import com.example.racingevent.model.dto.RacingEventDTO;
import com.example.racingevent.model.entity.RacingEvent;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class RacingEventDTOSerializer extends StdSerializer<RacingEvent> {

    public RacingEventDTOSerializer() {
        this(null);
    }

    public RacingEventDTOSerializer(Class<RacingEvent> t) {
        super(t);
    }

    @Override
    public void serialize(RacingEvent value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        RacingEventDTO dto = new RacingEventDTO();
        dto.setId(value.getId());
        dto.setEventName(value.getEventName());
        dto.setDate(value.getDate());
        dto.setLocation(value.getLocation());
        gen.writeObject(dto);
    }
}