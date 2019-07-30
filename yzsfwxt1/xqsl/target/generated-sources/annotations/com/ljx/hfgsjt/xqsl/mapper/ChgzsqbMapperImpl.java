package com.ljx.hfgsjt.xqsl.mapper;

import com.ljx.hfgsjt.entity.xqsl.YwslChgzsqbEntity;
import com.ljx.hfgsjt.xqsl.dto.ChgzsqbDto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-06-10T14:04:58+0800",
    comments = "version: 1.2.0.CR1, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
@Component
public class ChgzsqbMapperImpl implements ChgzsqbMapper {

    @Override
    public ChgzsqbDto EOToDto(YwslChgzsqbEntity ywslChgzsqbEntity) {
        if ( ywslChgzsqbEntity == null ) {
            return null;
        }

        ChgzsqbDto chgzsqbDto = new ChgzsqbDto();

        chgzsqbDto.setGcmc( ywslChgzsqbEntity.getXmmc() );
        chgzsqbDto.setLxdh( ywslChgzsqbEntity.getLxrsjh() );
        chgzsqbDto.setGcdz( ywslChgzsqbEntity.getXmdz() );
        if ( ywslChgzsqbEntity.getSlrq() != null ) {
            chgzsqbDto.setSlsj( new SimpleDateFormat().format( ywslChgzsqbEntity.getSlrq() ) );
        }
        chgzsqbDto.setBjdw( ywslChgzsqbEntity.getDwmc() );
        chgzsqbDto.setLxr( ywslChgzsqbEntity.getLxr() );
        chgzsqbDto.setAzlx( ywslChgzsqbEntity.getAzlx() );
        chgzsqbDto.setBz( ywslChgzsqbEntity.getBz() );
        chgzsqbDto.setZt( ywslChgzsqbEntity.getFpzt() );

        return chgzsqbDto;
    }

    @Override
    public List<ChgzsqbDto> EOToDtoList(List<YwslChgzsqbEntity> ywslChgzsqbEntities) {
        if ( ywslChgzsqbEntities == null ) {
            return null;
        }

        List<ChgzsqbDto> list = new ArrayList<ChgzsqbDto>( ywslChgzsqbEntities.size() );
        for ( YwslChgzsqbEntity ywslChgzsqbEntity : ywslChgzsqbEntities ) {
            list.add( EOToDto( ywslChgzsqbEntity ) );
        }

        return list;
    }
}
