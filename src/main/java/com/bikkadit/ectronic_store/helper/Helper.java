package com.bikkadit.ectronic_store.helper;

import com.bikkadit.ectronic_store.dto.PageableResponse;
import com.bikkadit.ectronic_store.dto.UserDto;
import com.bikkadit.ectronic_store.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class Helper {

    public static<U,V> PageableResponse<V> getPageableResponse(Page<U>page,Class<V> type){

        List<U> entity = page.getContent();
        List<V> dtoList = entity.stream().map(Object -> new ModelMapper().map(Object,type)).collect(Collectors.toList());
        PageableResponse<V> pageableResponse = new PageableResponse<>();
        pageableResponse.setContents(dtoList);
        pageableResponse.setPageNumber(page.getNumber()+1);
        pageableResponse.setPageSize(page.getSize());
        pageableResponse.setTotalPages(page.getTotalPages());
        pageableResponse.setTotalElement(page.getTotalElements());
        pageableResponse.setLastPage(page.isLast());

        return pageableResponse;
    }
}
