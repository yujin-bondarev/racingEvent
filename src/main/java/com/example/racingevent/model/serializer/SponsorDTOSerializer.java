package com.example.racingevent.model.serializer;

import com.example.racingevent.model.dto.SponsorDTO;
import com.example.racingevent.model.entity.Sponsor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class SponsorDTOSerializer extends StdSerializer<Set<Sponsor>> {

    public SponsorDTOSerializer() {
        this(null);
    }

    public SponsorDTOSerializer(Class<Set<Sponsor>> t) {
        super(t);
    }

    @Override
    public void serialize(Set<Sponsor> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        Set<SponsorDTO> dtoSet = value.stream().map(racer -> {
            SponsorDTO dto = new SponsorDTO();
            dto.setId(racer.getId());
            dto.setSpName(racer.getSpName());
            dto.setBudget(racer.getBudget());
            dto.setDateOfContract(racer.getDateOfContract());
            return dto;
        }).collect(Collectors.toSet());
        gen.writeObject(dtoSet);
    }
}
