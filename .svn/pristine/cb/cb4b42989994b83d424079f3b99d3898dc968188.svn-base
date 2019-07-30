package com.ljx.hfgsjt.main.mapper;

import com.ljx.hfgsjt.entity.HelloTest;
import com.ljx.hfgsjt.entity.dto.HelloTestDTO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-06-10T14:05:47+0800",
    comments = "version: 1.2.0.CR1, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
public class HelloTestMapperImpl implements HelloTestMapper {

    @Override
    public HelloTestDTO EntityToDto(HelloTest helloTest) {
        if ( helloTest == null ) {
            return null;
        }

        HelloTestDTO helloTestDTO = new HelloTestDTO();

        helloTestDTO.setTname( helloTest.getName() );
        helloTestDTO.setTid( helloTest.getTid() );
        helloTestDTO.setTcode( helloTest.getTcode() );

        return helloTestDTO;
    }
}
