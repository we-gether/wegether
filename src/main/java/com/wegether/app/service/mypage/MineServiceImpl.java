package com.wegether.app.service.mypage;

import com.wegether.app.dao.*;
import com.wegether.app.domain.dto.*;
import com.wegether.app.domain.vo.ConsultingVO;
import com.wegether.app.domain.vo.DataFileVO;
import com.wegether.app.domain.vo.MemberVO;
import com.wegether.app.domain.vo.NoticeFileVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MineServiceImpl implements MypageService {

    private final MemberDAO memberDAO;
    private final DataDAO dataDAO;
    private final ConsultingDAO consultingDAO;
    private final ProjectDAO projectDAO;
    private final FileDAO fileDAO;
    private final DataFileDAO dataFileDAO;

    @Override
    public void mypage() {
        log.info("내 참여 다 조회");
    }


//    마이페이지 메인
    public Optional<MemberDTO> loadMine(Long id){
//
        final Optional<MemberDTO> data = memberDAO.showmypage(id);
//        data.setFileId(memberDAO.showMyPageProfile(id).getFileId());
//        data.setFilePath(memberDAO.showMyPageProfile(id).getFilePath());
//        data.setFileUuid(memberDAO.showMyPageProfile(id).getFileUuid());
//        data.setFileName(memberDAO.showMyPageProfile(id).getFileName());
//
        return data;
    }

//    마이페이지 회원 탈퇴
    public void changeAccount(Long id){
        memberDAO.updateMemberS(id);
    }


    //    내가 등록한 자료 조회
    public List<DataDTO> readMine(Long memberId) {
        final List<DataDTO> datas = dataDAO.showmydata(memberId);
        //        게시글 하나씩 첨부파일 목록 담기
        datas.forEach(data -> data.setFiles(fileDAO.dataFindAll(data.getId())));
        return datas;
    }




    //    기본 설정 변경
    public void modifyBasicSetting(Long id, String memberNickname, String memberPhoneNumber){
        memberDAO.setBasicSetting(id, memberNickname, memberPhoneNumber);
    }



//상담
    public List<ConsultingVO> readMyConsulting(Long memberId){
        return consultingDAO.getmyconsult(memberId);
    }

    // 내 프로젝트 조회

    //    내 프로젝트 조회
    public List<ProjectDTO> readMyProject(Long memberId) {
        final List<ProjectDTO> projects = projectDAO.showmyProject(memberId);
        //        게시글 하나씩 첨부파일 목록 담기
        projects.forEach(project -> project.setFiles(fileDAO.projectFindAll(project.getId())));
        return projects;
    }
//    public List<ProjectDTO> readMyProject(Long memberId){return projectDAO.showmyProject(memberId);}


//    마이페이지 프로필 설정
    public void modifyProfile(MemberVO memberVO){
        memberDAO.setProfile(memberVO);

    }

}
