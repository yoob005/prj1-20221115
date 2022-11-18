package com.study.controller.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.study.domain.member.MemberDto;
import com.study.service.member.MemberService;

@Controller
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("login")
	public void login() {
		
	}
	
	@PostMapping("existEmail")
	@ResponseBody
	public Map<String, Object> existEmail(@RequestBody Map<String, String> req){
		Map<String,Object> map = new HashMap<>();
		
		MemberDto member = service.getEmail(req.get("email"));
		if(member==null) {
			map.put("status", "not exist");
			map.put("message", "사용가능한 이메일입니다.");
		} else {
			map.put("status", "exist");
			map.put("message", "이미 존재하는 이메일입니다");
		}
		
		return map;
	}
	
	@GetMapping("existNickName/{nickName}")
	@ResponseBody
	public Map<String, Object> existNickName(@PathVariable String nickName) {
		Map<String, Object> map = new HashMap<>();

		MemberDto member = service.getNickName(nickName);
		
		if(member==null) {
			map.put("status", "not exist");
			map.put("message", "사용가능한 닉네임입니다.");
		}else {
			map.put("status", "exist");
			map.put("message", "이미 존재하는 닉네임입니다");
		}
		
		return map;
	}
	
	@GetMapping("existId/{id}")
	@ResponseBody
	public Map<String, Object> existId(@PathVariable String id){
		
		Map<String,Object> map = new HashMap<>();
		
		MemberDto member = service.get(id);
		
		if(member==null) {
			map.put("status", "not exist");
			map.put("message", "사용가능한 아이디입니다.");
		}else {
			map.put("status", "exist");
			map.put("message", "이미 존재하는 아이디입니다");
		}
		
		return map;
	}
	
	@GetMapping("signup")
	public void signup() {
		
	}
	
	@PostMapping("signup")
	public String signup(MemberDto member, RedirectAttributes rttr) {
		
		int cnt = service.insert(member);
		
		if(cnt==1) {
			// 가입 잘됨
			rttr.addFlashAttribute("message", "회원가입완료");
			return "redirect:/board/list";
		} else {
			// 가입	ㄴㄴ
			
			rttr.addFlashAttribute("message", "회원가입실패");
			rttr.addFlashAttribute("member", member);
			return "redirect:/member/signup";
		}
	}
	
	@GetMapping("list")
	@PreAuthorize("hasAuthority('admin')")
	public void list(Model model) {
		model.addAttribute("memberList", service.list());
//		Model.addAttribute("memberList",service.list());
	}
	
	@GetMapping({"get","modify"})
	@PreAuthorize("(authentication.name == #id) or (hasAuthority('admin')) ")
	public void get(String id, Model model) {
		
		MemberDto member = service.get(id);
		
		model.addAttribute("member", member);
	}
	
	
	@PostMapping("modify")
	@PreAuthorize("authentication.name == #member.id")
	public String modify(MemberDto member, String oldPassword, RedirectAttributes rttr) {
		MemberDto oldmember = service.get(member.getId());
		
		rttr.addAttribute("id", member.getId());
		
		boolean passwordMatch = passwordEncoder.matches(oldPassword, oldmember.getPassword());
		
		if (passwordMatch) {
			// 기존 암호가 맞으면 회원 정보 수정
			int cnt = service.update(member);
			
			if (cnt == 1) {
				rttr.addFlashAttribute("message", "회원 정보가 수정되었습니다.");
				return "redirect:/member/get";
			} else {
				rttr.addFlashAttribute("message", "회원 정보가 수정되지 않았습니다.");
				return "redirect:/member/modify";
			}
		} else {
			rttr.addFlashAttribute("message", "암호가 일치하지 않습니다.");
			return "redirect:/member/modify";
		}
	}
	@PostMapping("remove")
	public String remove(String id, String oldPassword, RedirectAttributes rttr, HttpServletRequest request) throws Exception {
		MemberDto oldmember = service.get(id);
		
		boolean passwordMatch = passwordEncoder.matches(oldPassword, oldmember.getPassword());
		
		if (passwordMatch) {
			
			service.remove(id);
			
			rttr.addFlashAttribute("message", "회원 탈퇴하였습니다.");
			request.logout();
			
			return "redirect:/member/list";

		} else {
			rttr.addAttribute("id", id);
			rttr.addFlashAttribute("message", "암호가 일치하지 않습니다.");
			return "redirect:/member/modify";
		}
	}
	
}





