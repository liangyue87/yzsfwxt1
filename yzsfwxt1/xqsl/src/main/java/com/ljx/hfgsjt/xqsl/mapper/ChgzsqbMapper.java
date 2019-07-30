package com.ljx.hfgsjt.xqsl.mapper;

import com.ljx.hfgsjt.entity.xqsl.YwslChgzsqbEntity;
import com.ljx.hfgsjt.xqsl.dto.ChgzsqbDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChgzsqbMapper {
    @Mappings({
        @Mapping(target = "sqlx",ignore = true),
        @Mapping(target = "azlx",source = "azlx"),
        @Mapping(target = "lxr",source = "lxr"),
        @Mapping(target = "zt",source = "fpzt"),
        @Mapping(target = "slsj",source = "slrq"),
        @Mapping(target = "gcmc",source = "xmmc"),
        @Mapping(target = "lxdh",source = "lxrsjh"),
        @Mapping(target = "gcdz",source = "xmdz"),
        @Mapping(target = "bjdw",source = "dwmc"),
        @Mapping(target = "bz",source = "bz")
    })
    ChgzsqbDto EOToDto(YwslChgzsqbEntity ywslChgzsqbEntity);

    List<ChgzsqbDto> EOToDtoList (List<YwslChgzsqbEntity> ywslChgzsqbEntities);
}
