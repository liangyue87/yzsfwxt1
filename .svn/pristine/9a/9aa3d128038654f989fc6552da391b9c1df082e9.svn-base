package com.ljx.hfgsjt.main.mapper;

import com.ljx.hfgsjt.entity.GoodInfo;
import com.ljx.hfgsjt.entity.GoodType;
import com.ljx.hfgsjt.entity.dto.GoodInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface GoodInfoMapper {
   @Mappings({
            @Mapping(source = "type.name",target = "typeName"),
            @Mapping(source = "good.tid",target = "id"),
            @Mapping(source = "good.title",target = "goodName"),
            @Mapping(source = "good.price",target = "goodPrice")
    })
    GoodInfoDTO fromGoodInfoDTO(GoodInfo good, GoodType type);
}

