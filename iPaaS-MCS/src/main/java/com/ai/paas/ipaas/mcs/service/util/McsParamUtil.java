package com.ai.paas.ipaas.mcs.service.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.paas.ipaas.mcs.service.constant.McsConstants;
import com.google.gson.Gson;

public class McsParamUtil {
	
	@SuppressWarnings("unchecked")
	public static Map<String ,String> getParamMap(String applyParam) {
		Gson gson = new Gson();
		return gson.fromJson(applyParam, Map.class);
	}
	private static String successfulParam(String applyParam,String msg) {
		Map<String ,String> paramMap = getParamMap(applyParam);
		paramMap.remove(McsConstants.CAPACITY);
		paramMap.remove(McsConstants.HA_MODE);
		paramMap.put(McsConstants.RESULT_CODE, McsConstants.SUCCESS_CODE);
		if(msg!=null&&msg.length()>0)
			paramMap.put(McsConstants.RESULT_MSG, msg);
		else
			paramMap.put(McsConstants.RESULT_MSG, McsConstants.SUCCESS_INFO);
		Gson gson = new Gson();
		return gson.toJson(paramMap);
	}
	
	private static String failedParam(String applyParam,String msg) {
		Map<String ,String> paramMap = getParamMap(applyParam);
		paramMap.remove(McsConstants.CAPACITY);
		paramMap.remove(McsConstants.HA_MODE);
		paramMap.put(McsConstants.RESULT_CODE, McsConstants.FAIL_CODE);
		if(msg!=null&&msg.length()>0)
			paramMap.put(McsConstants.RESULT_MSG, msg);
		else
			paramMap.put(McsConstants.RESULT_MSG, McsConstants.FAIL_INFO);

		
		Gson gson = new Gson();
		return gson.toJson(paramMap);
	}
	
	public static String getReturn(String applyParam,String code,String msg){
		if(McsConstants.SUCCESS_FLAG.equals(code))
			return successfulParam(applyParam,msg);
		return failedParam(applyParam,msg);
	}
	
	public static String getDataReturn(String code,String msg){
		Map<String ,String> paramMap = new HashMap<String,String>();
		if(McsConstants.SUCCESS_FLAG.equals(code)){
			paramMap.put(McsConstants.RESULT_CODE, McsConstants.SUCCESS_CODE);
		}else{
			paramMap.put(McsConstants.RESULT_CODE, McsConstants.FAIL_CODE);
		}
		paramMap.put(McsConstants.RESULT_MSG, msg);
		Gson gson = new Gson();
		return gson.toJson(paramMap);
		
	}
	
	public static String getFuncList(){
		List<Map<String ,String>> funs = new ArrayList<Map<String ,String> >();
		Map<String ,String> mapc = new HashMap<String ,String>();
		mapc.put("funcName", "开通");
		mapc.put("funcUrl", McsConstants.FUNCTION_PRE+McsConstants.APPLY_TYPE_C);
		funs.add(mapc);
		Map<String ,String> mapr = new HashMap<String ,String>();
		mapr.put("funcName", "注销");
		mapr.put("funcUrl", McsConstants.FUNCTION_PRE+McsConstants.APPLY_TYPE_R);
		funs.add(mapr);
		Map<String ,String> maps = new HashMap<String ,String>();
		maps.put("funcName", "启动");
		maps.put("funcUrl", McsConstants.FUNCTION_PRE+McsConstants.APPLY_TYPE_START);
		funs.add(maps);
		Map<String ,String> mapst = new HashMap<String ,String>();
		mapst.put("funcName", "停止");
		mapst.put("funcUrl", McsConstants.FUNCTION_PRE+McsConstants.APPLY_TYPE_STOP);
		funs.add(mapst);
		Map<String ,String> mapres = new HashMap<String ,String>();
		mapres.put("funcName", "重启");
		mapres.put("funcUrl", McsConstants.FUNCTION_PRE+McsConstants.APPLY_TYPE_RESTART);
		funs.add(mapres);
		Map<String ,String> mapm = new HashMap<String ,String>();
		mapm.put("funcName", "修改");
		mapm.put("funcUrl", McsConstants.FUNCTION_PRE+McsConstants.APPLY_TYPE_M);
		funs.add(mapm);
		Gson gson = new Gson();
		return gson.toJson(funs);
		
	}

}
