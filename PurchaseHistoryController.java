package jp.co.internous.eagle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.eagle.model.domain.dto.PurchaseHistoryDto;
import jp.co.internous.eagle.model.mapper.TblPurchaseHistoryMapper;
import jp.co.internous.eagle.model.session.LoginSession;

@Controller
@RequestMapping("/eagle/history")
public class PurchaseHistoryController {
	
	@Autowired
	private TblPurchaseHistoryMapper purchaseHistoryMapper;
	
	@Autowired
	private LoginSession loginSession;
	
	@RequestMapping("/")
	public String index(Model m) {
		int userId = loginSession.getUserId();
		
		List<PurchaseHistoryDto> purchaseHistory = purchaseHistoryMapper.findByUserId(userId);
			m.addAttribute("purchaseHistory", purchaseHistory);
			//loginを引き継ぐためのsession
			m.addAttribute("loginSession", loginSession);
		
		return "purchase_history";
	}
	
	
	// 削除ボタン
	@RequestMapping("/delete")
	@ResponseBody
	public boolean delete() {
		int userId = loginSession.getUserId();
		int result = purchaseHistoryMapper.logicalDeleteByUserId(userId);
		
		return result > 0;
	}
	
}
