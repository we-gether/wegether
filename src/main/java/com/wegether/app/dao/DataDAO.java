package com.wegether.app.dao;

import com.wegether.app.domain.dto.ConsultingDTO;
import com.wegether.app.domain.dto.DataDTO;
import com.wegether.app.domain.dto.DataPagination;
import com.wegether.app.domain.dto.Pagination;
import com.wegether.app.domain.vo.DataVO;
import com.wegether.app.mapper.DataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DataDAO {
    private final DataMapper dataMapper;

    //    자료 목록
    public List<DataDTO> findAll(DataPagination dataPagination){
        return dataMapper.selectAll(dataPagination);
    }

    //    자료 추가
    public void save(DataDTO dataDTO){
        dataMapper.insert(dataDTO);
    }

    //    자료 조회
    public Optional<DataDTO> findById(Long id){
        return dataMapper.select(id);
    }

    //    게시글 총 개수
    public int findCountOfData(){
        return dataMapper.selectCountOfData();
    }























    










//    소영 마이페이지 내가 등록한 자료 조회
    public List<DataDTO> showmydata(Long memberId){return dataMapper.selectmydata(memberId);}

}
