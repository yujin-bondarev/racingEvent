package com.example.racingevent.model.serializer;

import com.example.racingevent.model.dto.RacerDTO;
import com.example.racingevent.model.entity.Racer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class RacerDTOSerializer extends StdSerializer<Set<Racer>> {

    public RacerDTOSerializer() {
        this(null);
    }

    public RacerDTOSerializer(Class<Set<Racer>> t) {
        super(t);
    }

    @Override
    public void serialize(Set<Racer> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        Set<RacerDTO> dtoSet = value.stream().map(racer -> {
            RacerDTO dto = new RacerDTO();
            dto.setId(racer.getId());
            dto.setName(racer.getName());
            dto.setCarModel(racer.getCarModel());
            return dto;
        }).collect(Collectors.toSet());
        gen.writeObject(dtoSet);
    }
}
