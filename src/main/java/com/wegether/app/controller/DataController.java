package com.wegether.app.controller;

import com.wegether.app.domain.dto.DataDTO;
import com.wegether.app.domain.dto.DataPagination;
import com.wegether.app.domain.type.CategoryType;
import com.wegether.app.domain.vo.DataVO;
import com.wegether.app.service.account.AccountService;
import com.wegether.app.service.data.DataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/datas/*")
public class DataController {
    private final DataService dataService;
    private final AccountService accountService;


//    자료 목록
    @GetMapping("list")
    public void goToDataList(DataPagination dataPagination, CategoryType categoryType, @RequestParam(defaultValue = "all") String type, @RequestParam(defaultValue = "new") String order, Model model){
        dataPagination.setTotal(dataService.getTotal());
        dataPagination.progress();
        categoryType.setType(type);
        categoryType.setOrder(order);
        model.addAttribute("datas", dataService.getList(dataPagination, categoryType));
    }

//    자료 상세
    @GetMapping("detail")
    public void read(@RequestParam Long id, Model model){
        model.addAttribute("dataDTO", dataService.read(id).get());
    }


//    자료 등록 - HttpSession session
    @GetMapping("register")
        public void goToRegisterForm(DataVO dataVO, HttpSession session, Model model){

//        String nickName = accountService.getMemberById((Long) session.getAttribute("id")).get().getMemberNickname();
        String nickName = accountService.getMemberById(2L).get().getMemberNickname();
        model.addAttribute("nickName", nickName);
    };





//    자료 등록 > 리스트 이동

    @PostMapping("register")
    public RedirectView write(DataDTO dataDTO){
        dataService.write(dataDTO);
        return new RedirectView("/data/list");
    }




//    @PostMapping("modify")
//    public RedirectView modify(DataDTO dataDTO, RedirectAttributes redirectAttributes){
//        dataService.modify(dataDTO);
//        redirectAttributes.addAttribute("id", dataDTO.getId());
//        return new RedirectView("/data/detail");
//    }
//
//    @PostMapping("remove")
//    public RedirectView remove(Long id){
//        dataService.remove(id);
//        return new RedirectView("/data/list");
//    }

}





















