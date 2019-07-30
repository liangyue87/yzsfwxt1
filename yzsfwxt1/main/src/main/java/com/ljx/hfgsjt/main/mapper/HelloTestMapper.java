package com.ljx.hfgsjt.main.mapper;

import com.ljx.hfgsjt.entity.HelloTest;
import com.ljx.hfgsjt.entity.dto.HelloTestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface HelloTestMapper {
    @Mappings({
        @Mapping(source="name",target = "tname")
    })
    public HelloTestDTO EntityToDto(HelloTest helloTest);

}
