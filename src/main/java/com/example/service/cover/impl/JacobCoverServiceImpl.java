package com.example.service.cover.impl;

import org.springframework.stereotype.Service;

import com.example.service.cover.JacobCoverService;
import com.jacob.activeX.ActiveXComponent;
//import com.jacob.com.Dispatch;
import com.jacob.com.*;

@Service
public class JacobCoverServiceImpl implements JacobCoverService {

	@Override
	public String getPartNumber() {
		Variant varPartNumber;
		try {
			ActiveXComponent xCom = new ActiveXComponent("Catia.Application");
			ActiveXComponent xCom = 
			Dispatch dispCom = xCom.getObject();
			xCom.setProperty("Visible", true);
			Dispatch docs = xCom.getProperty("Documents").toDispatch();
			//Dispatch doc = Dispatch.call(docs,"Add","Part").toDispatch();
			
			Dispatch doc = Dispatch.get(dispCom, "ActiveDocument").toDispatch();
			//Dispatch docs = Dispatch.get(dispCom,"Documents").toDispatch();
			//Dispatch doc = Dispatch.call(docs,"Item",1).toDispatch();
			Dispatch part = Dispatch.get(doc,"Product").toDispatch();
			//Dispatch part = Dispatch.call(doc,"Part").toDispatch();
			varPartNumber = Dispatch.get(part,"PartNumber");
		} catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
		return varPartNumber.getString();
	}
	
	@Override
	public String getSelectionName() {
		ActiveXComponent xCom = new ActiveXComponent("Catia.Application");
		Dispatch dispCom = xCom.getObject();
		Dispatch doc = Dispatch.call(dispCom, "ActiveDocument").toDispatch();
		Dispatch select = Dispatch.get(doc, "Selection").toDispatch();
		
		Object[] obj = {"MonoDim","MonoDimInfinite"};
		Variant status = Dispatch.call(select,"SelectElement2",obj,"Select Element","false");
		if (status.toString()=="cancel") {
			return "select cancel";
		}
		Dispatch element = Dispatch.call(select,"Item2",1).toDispatch();
		Dispatch ref = Dispatch.get(element,"Reference").toDispatch();
		Variant name = Dispatch.get(ref,"DisplayName");
		
		return name.toString();
		
	}
}
