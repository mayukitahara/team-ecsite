package jp.co.internous.eagle.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.eagle.model.domain.MstDestination;
import jp.co.internous.eagle.model.mapper.MstDestinationMapper;
import jp.co.internous.eagle.model.mapper.TblCartMapper;
import jp.co.internous.eagle.model.mapper.TblPurchaseHistoryMapper;
import jp.co.internous.eagle.model.session.LoginSession;

@Controller
@RequestMapping("/eagle/settlement")
public class SettlementController {
	
	@Autowired
	private MstDestinationMapper destinationMapper;

	@Autowired
	private TblPurchaseHistoryMapper purchaseHistoryMapper;
	
	@Autowired
	private TblCartMapper cartMapper;
	
	@Autowired
	private LoginSession loginSession;
	
	private Gson gson = new Gson();
	

	@RequestMapping("/")
	public String index(Model m) {
		int userId = loginSession.getUserId();
		
		List<MstDestination> destination = destinationMapper.findByUserId(userId);
		m.addAttribute("destination", destination);
		//loginを引き継ぐためのsession
		m.addAttribute("loginSession", loginSession);
		
		return "settlement";
	}

	//決済ボタン
	@SuppressWarnings("unchecked")
	@RequestMapping("/complete")
	@ResponseBody
	public boolean complete(@RequestBody String destinationId) {
		//画面から渡されたdestinationIdを取得	
		Map<String, String> map = gson.fromJson(destinationId, Map.class);
		String id = map.get("destinationId");
		
		//DBの購入履歴情報に宛先情報とuserIdを登録
		int userId = loginSession.getUserId();
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("destinationId", id);
		parameter.put("userId", userId);
		int insertCount = purchaseHistoryMapper.insert(parameter);
		
		//カート情報を削除
		int deleteCount = 0;
		if(insertCount > 0) {
			deleteCount = cartMapper.deleteByUserId(userId);
		}
		return deleteCount == insertCount;
		
	}
}



