package com.example.racingevent.model.serializer;

import com.example.racingevent.model.dto.ViewerDTO;
import com.example.racingevent.model.entity.Viewer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class ViewerDTOSerializer extends StdSerializer<Set<Viewer>> {

    public ViewerDTOSerializer() {
        this(null);
    }

    public ViewerDTOSerializer(Class<Set<Viewer>> t) {
        super(t);
    }

    @Override
    public void serialize(Set<Viewer> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        Set<ViewerDTO> dtoSet = value.stream().map(racer -> {
            ViewerDTO dto = new ViewerDTO();
            dto.setId(racer.getId());
            dto.setVwName(racer.getVwName());
            dto.setTicketType(racer.getTicketType());
            return dto;
        }).collect(Collectors.toSet());
        gen.writeObject(dtoSet);
    }
}
