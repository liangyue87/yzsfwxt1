package com.ljx.hfgsjt.xqsl.mapper;

import com.ljx.hfgsjt.entity.xqsl.YwslXjxqsqbEntity;
import com.ljx.hfgsjt.entity.xqsl.YwslXjxqsqbFjEntity;
import com.ljx.hfgsjt.xqsl.dto.XjxqsqbDto;
import com.ljx.hfgsjt.xqsl.dto.XjxqsqbFjDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface XjxqsqbMapper {
    @Mappings({
        @Mapping(target = "id",source = "ywslXjxqsqbEntity.id"),
        @Mapping(target = "sqbh",source = "ywslXjxqsqbEntity.sqbh"),
        @Mapping(target = "xmmc",source = "ywslXjxqsqbEntity.xmmc"),
        @Mapping(target = "kfsmc",source = "ywslXjxqsqbEntity.kfsmc"),
        @Mapping(target = "dchs",source = "ywslXjxqsqbEntity.dchs"),
        @Mapping(target = "gchs",source = "ywslXjxqsqbEntity.gchs"),
        @Mapping(target = "lxr",source = "ywslXjxqsqbEntity.lxr"),
        @Mapping(target = "lxrsjh",source = "ywslXjxqsqbEntity.lxrsjh"),
        @Mapping(target = "slr",source = "ywslXjxqsqbEntity.slr"),
        @Mapping(target = "slrq",source = "ywslXjxqsqbEntity.slrq"),
        @Mapping(target = "bgr",source = "ywslXjxqsqbEntity.bgr"),
        @Mapping(target = "bgsj",source = "ywslXjxqsqbEntity.bgsj"),
        @Mapping(target = "bgnr",source = "ywslXjxqsqbEntity.bgnr"),
        @Mapping(target = "bz",source = "ywslXjxqsqbEntity.bz"),
        @Mapping(target = "xzqybh",source = "ywslXjxqsqbEntity.xzqybh"),
        @Mapping(target = "fpzt",source = "ywslXjxqsqbEntity.fpzt"),
        @Mapping(target = "fpr",source = "ywslXjxqsqbEntity.fpr"),
        @Mapping(target = "fpsj",source = "ywslXjxqsqbEntity.fpsj"),
        @Mapping(target = "xmjl",source = "ywslXjxqsqbEntity.xmjl"),
        @Mapping(target = "xmdzSheng",source = "ywslXjxqsqbEntity.xmdz_sheng"),
        @Mapping(target = "xmdzShi",source = "ywslXjxqsqbEntity.xmdz_shi"),
        @Mapping(target = "xmdzXzqybh",source = "ywslXjxqsqbEntity.xmdz_xzqybh"),
        @Mapping(target = "xmdz",source = "ywslXjxqsqbEntity.xmdz"),
        @Mapping(target = "azlx",source = "ywslXjxqsqbEntity.azlx"),
        @Mapping(target = "zjzmj",source = "ywslXjxqsqbEntity.zjzmj"),
        @Mapping(target = "zzjzmj",source = "ywslXjxqsqbEntity.zzjzmj"),
        @Mapping(target = "zgcs",source = "ywslXjxqsqbEntity.zgcs"),
        @Mapping(target = "sqly",source = "ywslXjxqsqbEntity.sqly"),
    })
    XjxqsqbDto EOToDto(YwslXjxqsqbEntity ywslXjxqsqbEntity);

    XjxqsqbFjDto EOToFjDto(YwslXjxqsqbFjEntity ywslXjxqsqbFjEntity);

    List<XjxqsqbDto> EOToDtoList(List<YwslXjxqsqbEntity> ywslXjxqsqbEntities);

    List<XjxqsqbFjDto> EOToFjDtoList(List<YwslXjxqsqbFjEntity> ywslXjxqsqbFjEntities);
}
