package com.ljx.hfgsjt.main.mapper;

import com.ljx.hfgsjt.entity.GoodInfo;
import com.ljx.hfgsjt.entity.GoodType;
import com.ljx.hfgsjt.entity.dto.GoodInfoDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-06-10T14:05:47+0800",
    comments = "version: 1.2.0.CR1, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
@Component
public class GoodInfoMapperImpl implements GoodInfoMapper {

    @Override
    public GoodInfoDTO fromGoodInfoDTO(GoodInfo good, GoodType type) {
        if ( good == null && type == null ) {
            return null;
        }

        GoodInfoDTO goodInfoDTO = new GoodInfoDTO();

        if ( good != null ) {
            goodInfoDTO.setId( good.getTid() );
            goodInfoDTO.setGoodName( good.getTitle() );
            goodInfoDTO.setGoodPrice( good.getPrice() );
        }
        if ( type != null ) {
            goodInfoDTO.setTypeName( type.getName() );
            goodInfoDTO.setShow( type.getShow() );
        }

        return goodInfoDTO;
    }
}
