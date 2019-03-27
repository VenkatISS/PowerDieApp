package com.it.diesuiteapp.appservices;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.it.diesuiteapp.commons.ApplicationConstants;
import com.it.diesuiteapp.framework.MasterDataFactory;
import com.it.diesuiteapp.framework.model.AdminDO;
import com.it.diesuiteapp.response.MessageObject;
import com.it.diesuiteapp.systemservices.exceptions.BusinessException;


public class MasterDataServiceBean {
	
	Logger logger=LoggerFactory.getLogger(MasterDataServiceBean.class);
	long  adminId;
	long itemId;
	long fleetDataId;
	long staffDataId;
	long cvoId;
	long bankDataId;
	long expDataId;
	long clDataId;
	
	String cid;
	String customername;
	String dis19kgndne;
	String dis19kgcgas;
	String dis35kgndne;
	String dis47_5kgndne;
	String dis450kgsumo;

	
	public void fetchAdminUserData(HttpServletRequest request,
			HttpServletResponse response){
		MessageObject msgObj = new MessageObject(3001, "FETCH USER DATA", ApplicationConstants.ERROR_STATUS_STRING);
		//HttpSession session = null;
		try {
			adminId = ((AdminDO)request.getSession().getAttribute("adminDO")).getAdminId();

				logger.info(ApplicationConstants.LogMessageKeys.FETCHSTATUTORYDATA.getValue()
						+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
	    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue(),adminId);
		//	CacheFactory cf = new CacheFactory();
			//session.setAttribute("users_data", (new Gson().toJson(cf.getAdminUserData())));
			MasterDataFactory mdf = new MasterDataFactory();
			request.setAttribute("users_data", (new Gson().toJson(
					mdf.getAdminUserData(((AdminDO)request.getSession().getAttribute("adminDO")).getAdminId()))));
			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
			
			
			logger.info(ApplicationConstants.LogMessageKeys.FETCHSTATUTORYDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					adminId,ApplicationConstants.SUCCESS_STATUS_STRING);

		}catch(BusinessException be) {
			msgObj.setMessageText(be.getExceptionMessage());
			
			logger.info(ApplicationConstants.LogMessageKeys.FETCHSTATUTORYDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					adminId,be);

		}
		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}

	public void saveAgencyUserData(HttpServletRequest request, HttpServletResponse response){
		
		MessageObject msgObj = new MessageObject(3002, "SAVE STATUTORY DATA", ApplicationConstants.ERROR_STATUS_STRING);
		try {
			adminId = Long.parseLong(request.getParameter("adminId"));
			
			adminId = Long.parseLong(request.getParameter("agencyId"));
			logger.info(ApplicationConstants.LogMessageKeys.SAVEAGENCYSTATUTORYDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue(),adminId);

			Map<String, String[]> requestParams = request.getParameterMap();
			MasterDataFactory mdf = new MasterDataFactory();
			request.setAttribute("statutory_data", (new Gson().toJson(mdf.saveAdminUserData(requestParams, adminId))));
			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
			logger.info(ApplicationConstants.LogMessageKeys.SAVEAGENCYSTATUTORYDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					adminId,ApplicationConstants.SUCCESS_STATUS_STRING);

		}catch(BusinessException be){
			msgObj.setMessageText(be.getExceptionMessage());
			
			logger.info(ApplicationConstants.LogMessageKeys.SAVEAGENCYSTATUTORYDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					adminId,be);

		}		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}

	public void deleteAdminUserData(HttpServletRequest request, HttpServletResponse response){
		
		MessageObject msgObj = new MessageObject(3003, "DELETE STATUTORY DATA", ApplicationConstants.ERROR_STATUS_STRING);
		try {
			
			
			adminId = Long.parseLong(request.getParameter("agencyId"));
			itemId = Long.parseLong(request.getParameter("itemId"));

			logger.info(ApplicationConstants.LogMessageKeys.DELETEAGENCYSTATUTORYDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.ITEMID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),
					adminId,itemId);

			MasterDataFactory mdf = new MasterDataFactory();
			request.setAttribute("statutory_data", (new Gson().toJson(mdf.deleteAgencyStatutoryData(itemId,adminId))));
			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
			logger.info(ApplicationConstants.LogMessageKeys.DELETEAGENCYSTATUTORYDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue()  +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.ITEMID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.actionStatusKeys.STATUS.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),adminId,itemId,
					ApplicationConstants.SUCCESS_STATUS_STRING);

		}catch(BusinessException be){
			msgObj.setMessageText(be.getExceptionMessage());
			
			logger.info(ApplicationConstants.LogMessageKeys.DELETEAGENCYSTATUTORYDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.ADMINID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue()  +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.ITEMID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),adminId,itemId,be);

		}
		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}

	/*//Staff Data Methods
	public void fetchStaffData(HttpServletRequest request,
			HttpServletResponse response){
		MessageObject msgObj = new MessageObject(3541, "FETCH STAFF DATA", ApplicationConstants.ERROR_STATUS_STRING);
		try {
			
			agencyId = ((AgencyVO)request.getSession().getAttribute("agencyVO")).getAgency_code();

			logger.info(ApplicationConstants.LogMessageKeys.FETCHSTAFFDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId);

			MasterDataFactory mdf = new MasterDataFactory();
			request.setAttribute("staff_data", (new Gson().toJson(
					mdf.getAgencyStaffData(((AgencyVO)request.getSession().getAttribute("agencyVO")).getAgency_code()))));
			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
			logger.info(ApplicationConstants.LogMessageKeys.FETCHSTAFFDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					agencyId,ApplicationConstants.SUCCESS_STATUS_STRING);

		}catch(BusinessException be) {
			msgObj.setMessageText(be.getExceptionMessage());
			
			logger.info(ApplicationConstants.LogMessageKeys.FETCHSTAFFDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					agencyId,be);

		}		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}

	public void saveStaffData(HttpServletRequest request, HttpServletResponse response){
		
		MessageObject msgObj = new MessageObject(3542, "SAVE STAFF DATA", ApplicationConstants.ERROR_STATUS_STRING);
		try {
			agencyId = Long.parseLong(request.getParameter("agencyId"));
			
			logger.info(ApplicationConstants.LogMessageKeys.SAVESTAFFDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId);

			Map<String, String[]> requestParams = request.getParameterMap();
			MasterDataFactory mdf = new MasterDataFactory();
			request.setAttribute("staff_data", (new Gson().toJson(mdf.saveAgencyStaffData(requestParams, agencyId))));
			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
			logger.info(ApplicationConstants.LogMessageKeys.SAVESTAFFDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					agencyId,ApplicationConstants.SUCCESS_STATUS_STRING);

		}catch(BusinessException be){
			msgObj.setMessageText(be.getExceptionMessage());
			
			logger.info(ApplicationConstants.LogMessageKeys.SAVESTAFFDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					agencyId,be);

		}		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}

	public void deleteStaffData(HttpServletRequest request, HttpServletResponse response){
		
		MessageObject msgObj = new MessageObject(3543, "DELETE STAFF DATA", ApplicationConstants.ERROR_STATUS_STRING);
		try {
			agencyId = Long.parseLong(request.getParameter("agencyId"));
			staffDataId = Long.parseLong(request.getParameter("staffDataId"));
			logger.info(ApplicationConstants.LogMessageKeys.DELETESTAFFDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.STAFFDATAID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),
					agencyId,staffDataId);

			MasterDataFactory mdf = new MasterDataFactory();
			request.setAttribute("staff_data", (new Gson().toJson(mdf.deleteAgencyStaffData(staffDataId,agencyId))));
			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
			logger.info(ApplicationConstants.LogMessageKeys.DELETESTAFFDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue()  +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.STAFFDATAID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.actionStatusKeys.STATUS.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId,staffDataId,
					ApplicationConstants.SUCCESS_STATUS_STRING);

		}catch(BusinessException be){
			msgObj.setMessageText(be.getExceptionMessage());
			
			logger.info(ApplicationConstants.LogMessageKeys.DELETESTAFFDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue()  +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.STAFFDATAID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId,staffDataId,be);

		}		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}

	//Fleet Data Methods
	public void fetchFleetData(HttpServletRequest request,
			HttpServletResponse response){
		MessageObject msgObj = new MessageObject(3531, "FETCH FLEET DATA", ApplicationConstants.ERROR_STATUS_STRING);
		try {
			
			agencyId = ((AgencyVO)request.getSession().getAttribute("agencyVO")).getAgency_code();

			logger.info(ApplicationConstants.LogMessageKeys.FETCHFLEETDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId);

			MasterDataFactory mdf = new MasterDataFactory();
			request.setAttribute("fleet_data", (new Gson().toJson(
					mdf.getAgencyFleetData(((AgencyVO)request.getSession().getAttribute("agencyVO")).getAgency_code()))));
			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
			logger.info(ApplicationConstants.LogMessageKeys.FETCHFLEETDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					agencyId,ApplicationConstants.SUCCESS_STATUS_STRING);

		}catch(BusinessException be) {
			msgObj.setMessageText(be.getExceptionMessage());
			
			logger.info(ApplicationConstants.LogMessageKeys.FETCHFLEETDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					agencyId,be);
	
		}		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}

	public void saveFleetData(HttpServletRequest request, HttpServletResponse response){
		
		MessageObject msgObj = new MessageObject(3532, "SAVE FLEET DATA", ApplicationConstants.ERROR_STATUS_STRING);
		try {
			agencyId = Long.parseLong(request.getParameter("agencyId"));
			
			logger.info(ApplicationConstants.LogMessageKeys.SAVEFLEETDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId);

			Map<String, String[]> requestParams = request.getParameterMap();
			MasterDataFactory mdf = new MasterDataFactory();
			request.setAttribute("fleet_data", (new Gson().toJson(mdf.saveAgencyFleetData(requestParams, agencyId))));
			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
			logger.info(ApplicationConstants.LogMessageKeys.SAVEFLEETDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					agencyId,ApplicationConstants.SUCCESS_STATUS_STRING);

		}catch(BusinessException be){
			msgObj.setMessageText(be.getExceptionMessage());
			logger.info(ApplicationConstants.LogMessageKeys.SAVEFLEETDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					agencyId,be);

		}		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}

	public void deleteFleetData(HttpServletRequest request, HttpServletResponse response){
		
		MessageObject msgObj = new MessageObject(3533, "DELETE FLEET DATA", ApplicationConstants.ERROR_STATUS_STRING);
		try {
			
			agencyId = Long.parseLong(request.getParameter("agencyId"));
			fleetDataId = Long.parseLong(request.getParameter("fleetDataId"));
			
			logger.info(ApplicationConstants.LogMessageKeys.DELETEFLEETDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.FLEETDATAID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),
					agencyId,fleetDataId);

			
			MasterDataFactory mdf = new MasterDataFactory();
			request.setAttribute("fleet_data", (new Gson().toJson(mdf.deleteAgencyFleetData(fleetDataId,agencyId))));
			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
			
			logger.info(ApplicationConstants.LogMessageKeys.DELETEFLEETDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue()  +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.FLEETDATAID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.actionStatusKeys.STATUS.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId,fleetDataId,
					ApplicationConstants.SUCCESS_STATUS_STRING);

		}catch(BusinessException be){
			msgObj.setMessageText(be.getExceptionMessage());
			
			logger.info(ApplicationConstants.LogMessageKeys.DELETEFLEETDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue()  +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.FLEETDATAID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId,fleetDataId,be);

		}
		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}

	//CVO Data Methods
	public void fetchCVOData(HttpServletRequest request,
			HttpServletResponse response){
		MessageObject msgObj = new MessageObject(3501, "FETCH CVO DATA", ApplicationConstants.ERROR_STATUS_STRING);
		try {
			
			 agencyId = ((AgencyVO)request.getSession().getAttribute("agencyVO")).getAgency_code();

				logger.info(ApplicationConstants.LogMessageKeys.FETCHCVODATA.getValue()
						+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
	    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId);

			HttpSession session = request.getSession(true);
			MasterDataFactory mdf = new MasterDataFactory();
//			if( (null == session.getAttribute("cvo_data")) ) {
			session.setAttribute("cvo_data", (new Gson().toJson(
					mdf.getAgencyCVOData(((AgencyVO)request.getSession().getAttribute("agencyVO")).getAgency_code()))));
//			}
			request.setAttribute("dcvo_data", (new Gson().toJson(
					mdf.getAgencyAllCVOData(((AgencyVO)request.getSession().getAttribute("agencyVO")).getAgency_code()))));
			
			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
			logger.info(ApplicationConstants.LogMessageKeys.FETCHCVODATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					agencyId,ApplicationConstants.SUCCESS_STATUS_STRING);

		}catch(BusinessException be) {
			msgObj.setMessageText(be.getExceptionMessage());
			
			logger.info(ApplicationConstants.LogMessageKeys.FETCHCVODATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					agencyId,be);

		}
		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}

	public void saveCVOData(HttpServletRequest request, HttpServletResponse response){
		
		MessageObject msgObj = new MessageObject(3502, "SAVE CVO DATA", ApplicationConstants.ERROR_STATUS_STRING);
		try {
			agencyId = Long.parseLong(request.getParameter("agencyId"));
			String effdate = request.getParameter("effDateInFTL");
			
			logger.info(ApplicationConstants.LogMessageKeys.SAVECVODATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId);

			Map<String, String[]> requestParams = request.getParameterMap();

			MasterDataFactory mdf = new MasterDataFactory();
			request.getSession().setAttribute("cvo_data", (new Gson().toJson(mdf.saveAgencyCVOData(requestParams, agencyId, effdate))));
			request.setAttribute("dcvo_data", (new Gson().toJson(
					mdf.getAgencyAllCVOData(((AgencyVO)request.getSession().getAttribute("agencyVO")).getAgency_code()))));
			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
			logger.info(ApplicationConstants.LogMessageKeys.SAVECVODATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),
					agencyId,ApplicationConstants.SUCCESS_STATUS_STRING);

		}catch(BusinessException be){
			msgObj.setMessageText(be.getExceptionMessage());
			
			logger.info(ApplicationConstants.LogMessageKeys.SAVECVODATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					agencyId,be);

		}
		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}

	public void deleteCVOData(HttpServletRequest request, HttpServletResponse response){
		
		MessageObject msgObj = new MessageObject(3503, "DELETE CVO DATA", ApplicationConstants.ERROR_STATUS_STRING);
		try {
			
			agencyId = Long.parseLong(request.getParameter("agencyId"));
			cvoId = Long.parseLong(request.getParameter("dataId"));
			
			logger.info(ApplicationConstants.LogMessageKeys.DELETECVODATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.CVOID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),
					agencyId,cvoId);

			
			MasterDataFactory mdf = new MasterDataFactory();
			request.getSession().setAttribute("cvo_data", (new Gson().toJson(mdf.deleteAgencyCVOData(cvoId,agencyId))));
			request.setAttribute("dcvo_data", (new Gson().toJson(
					mdf.getAgencyAllCVOData(((AgencyVO)request.getSession().getAttribute("agencyVO")).getAgency_code()))));			
			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
			logger.info(ApplicationConstants.LogMessageKeys.DELETECVODATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue()  +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.CVOID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.actionStatusKeys.STATUS.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId,cvoId,
					ApplicationConstants.SUCCESS_STATUS_STRING);

		}catch(BusinessException be){
			msgObj.setMessageText(be.getExceptionMessage());
			
			logger.info(ApplicationConstants.LogMessageKeys.DELETECVODATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue()  +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.CVOID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId,cvoId,be);

		}
		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}

	//Bank Data Methods
	public void fetchBankData(HttpServletRequest request,
			HttpServletResponse response){
		MessageObject msgObj = new MessageObject(3511, "FETCH BANK DATA", ApplicationConstants.ERROR_STATUS_STRING);
		try {
			
			 agencyId = ((AgencyVO)request.getSession().getAttribute("agencyVO")).getAgency_code();

				logger.info(ApplicationConstants.LogMessageKeys.FETCHBANKDATA.getValue()
						+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
	    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId);

			MasterDataFactory mdf = new MasterDataFactory();
			request.setAttribute("bank_data", (new Gson().toJson(
					mdf.getAgencyAllBankData(((AgencyVO)request.getSession().getAttribute("agencyVO")).getAgency_code()))));
			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
			logger.info(ApplicationConstants.LogMessageKeys.FETCHBANKDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					agencyId,ApplicationConstants.SUCCESS_STATUS_STRING);

		}catch(BusinessException be) {
			msgObj.setMessageText(be.getExceptionMessage());
			
			logger.info(ApplicationConstants.LogMessageKeys.FETCHBANKDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					agencyId,be);

		}	request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}

	public void saveBankData(HttpServletRequest request, HttpServletResponse response){
		
		MessageObject msgObj = new MessageObject(3512, "SAVE BANK DATA", ApplicationConstants.ERROR_STATUS_STRING);
		try {
			agencyId = Long.parseLong(request.getParameter("agencyId"));
			
			logger.info(ApplicationConstants.LogMessageKeys.SAVEBANKDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId);

			Map<String, String[]> requestParams = request.getParameterMap();
			MasterDataFactory mdf = new MasterDataFactory();
			request.setAttribute("bank_data", (new Gson().toJson(mdf.saveAgencyBankData(requestParams, agencyId))));
			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);

			logger.info(ApplicationConstants.LogMessageKeys.SAVEBANKDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					agencyId,ApplicationConstants.SUCCESS_STATUS_STRING);

		}catch(BusinessException be){
			msgObj.setMessageText(be.getExceptionMessage());
			
			logger.info(ApplicationConstants.LogMessageKeys.SAVEBANKDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					agencyId,be);

		}request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}

	public void deleteBankData(HttpServletRequest request, HttpServletResponse response){
		
		MessageObject msgObj = new MessageObject(3513, "DELETE BANK DATA", ApplicationConstants.ERROR_STATUS_STRING);
		try {
			agencyId = Long.parseLong(request.getParameter("agencyId"));
			bankDataId = Long.parseLong(request.getParameter("dataId"));
			
			logger.info(ApplicationConstants.LogMessageKeys.DELETEBANKDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.BANKDATAID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),
					agencyId,bankDataId);

			MasterDataFactory mdf = new MasterDataFactory();
			request.setAttribute("bank_data", (new Gson().toJson(mdf.deleteAgencyBankData(bankDataId,agencyId))));
			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
			
			logger.info(ApplicationConstants.LogMessageKeys.DELETEBANKDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue()  +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.BANKDATAID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.actionStatusKeys.STATUS.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId,bankDataId,
					ApplicationConstants.SUCCESS_STATUS_STRING);
		}catch(BusinessException be){
			msgObj.setMessageText(be.getExceptionMessage());
			logger.info(ApplicationConstants.LogMessageKeys.DELETEBANKDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue()  +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.BANKDATAID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId,bankDataId,be);

		}
		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}

	//Expenditure Data Methods
	public void fetchExpenditureData(HttpServletRequest request,
			HttpServletResponse response) {
		MessageObject msgObj = new MessageObject(3521, "FETCH EXPENDITURE DATA", ApplicationConstants.ERROR_STATUS_STRING);
		try {
			
			 agencyId = ((AgencyVO)request.getSession().getAttribute("agencyVO")).getAgency_code();

				logger.info(ApplicationConstants.LogMessageKeys.FETCHEXPENDITUREDATA.getValue()
						+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
	    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId);

			MasterDataFactory mdf = new MasterDataFactory();
			request.setAttribute("exp_data", (new Gson().toJson(
					mdf.getAgencyExpenditureData(((AgencyVO)request.getSession().getAttribute("agencyVO")).getAgency_code()))));
			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
			logger.info(ApplicationConstants.LogMessageKeys.FETCHEXPENDITUREDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					agencyId,ApplicationConstants.SUCCESS_STATUS_STRING);

		}catch(BusinessException be) {
			msgObj.setMessageText(be.getExceptionMessage());
			
			logger.info(ApplicationConstants.LogMessageKeys.FETCHEXPENDITUREDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					agencyId,be);

		}
		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}

	public void saveExpenditureData(HttpServletRequest request, HttpServletResponse response){
		
		MessageObject msgObj = new MessageObject(3522, "SAVE EXPENDITURE DATA", ApplicationConstants.ERROR_STATUS_STRING);
		try {
			agencyId = Long.parseLong(request.getParameter("agencyId"));
			
			logger.info(ApplicationConstants.LogMessageKeys.SAVEEXPENDITUREDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId);

			Map<String, String[]> requestParams = request.getParameterMap();
			MasterDataFactory mdf = new MasterDataFactory();
			request.setAttribute("exp_data", (new Gson().toJson(mdf.saveAgencyExpenditureData(requestParams, agencyId))));
			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
			logger.info(ApplicationConstants.LogMessageKeys.SAVEEXPENDITUREDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					agencyId,ApplicationConstants.SUCCESS_STATUS_STRING);

		}catch(BusinessException be){
			msgObj.setMessageText(be.getExceptionMessage());
			
			logger.info(ApplicationConstants.LogMessageKeys.SAVEEXPENDITUREDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					agencyId,be);

		}
		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}

	public void deleteExpenditureData(HttpServletRequest request, HttpServletResponse response){
		
		MessageObject msgObj = new MessageObject(3523, "DELETE EXPENDITURE DATA", ApplicationConstants.ERROR_STATUS_STRING);
		try {
			
			agencyId = Long.parseLong(request.getParameter("agencyId"));
			expDataId = Long.parseLong(request.getParameter("itemId"));
			
			logger.info(ApplicationConstants.LogMessageKeys.DELETEEXPENDITUREDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.EXPDATAID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),
					agencyId,expDataId);

			
			MasterDataFactory mdf = new MasterDataFactory();
			request.setAttribute("exp_data", (new Gson().toJson(mdf.deleteAgencyExpenditureData(expDataId,agencyId))));
			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
			logger.info(ApplicationConstants.LogMessageKeys.DELETEEXPENDITUREDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue()  +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.EXPDATAID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.actionStatusKeys.STATUS.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId,expDataId,
					ApplicationConstants.SUCCESS_STATUS_STRING);

		}catch(BusinessException be){
			msgObj.setMessageText(be.getExceptionMessage());
			
			logger.info(ApplicationConstants.LogMessageKeys.DELETEEXPENDITUREDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue()  +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.EXPDATAID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId,expDataId,be);

		}
		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}

	//Credit Limit Data Methods
	public void fetchCreditLimitsData(HttpServletRequest request,
			HttpServletResponse response){
		MessageObject msgObj = new MessageObject(3551, "FETCH CREDIT LIMITS DATA", ApplicationConstants.ERROR_STATUS_STRING);
		try {
			
			 agencyId = ((AgencyVO)request.getSession().getAttribute("agencyVO")).getAgency_code();

				logger.info(ApplicationConstants.LogMessageKeys.FETCHCREDITLIMITSDATA.getValue()
						+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
	    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId);
			
			MasterDataFactory mdf = new MasterDataFactory();
			request.setAttribute("cvo_data", (new Gson().toJson(
				mdf.getAgencyAllCVOData(((AgencyVO)request.getSession().getAttribute("agencyVO")).getAgency_code()))));
			HttpSession session = request.getSession(true);
			session.setAttribute("cl_data",(new Gson().toJson(
					mdf.getAgencyCreditLimitsData(((AgencyVO)request.getSession().getAttribute("agencyVO")).getAgency_code()))));

			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
			logger.info(ApplicationConstants.LogMessageKeys.FETCHCREDITLIMITSDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					agencyId,ApplicationConstants.SUCCESS_STATUS_STRING);

		}catch(BusinessException be) {
			msgObj.setMessageText(be.getExceptionMessage());
			
			logger.info(ApplicationConstants.LogMessageKeys.FETCHCREDITLIMITSDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					agencyId,be);

		}
		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}

	public void saveCreditLimitsData(HttpServletRequest request, HttpServletResponse response){
		
		MessageObject msgObj = new MessageObject(3552, "SAVE CREDIT LIMITS DATA", ApplicationConstants.ERROR_STATUS_STRING);
		try {
			request.setAttribute("checkDisplay", "1");
			agencyId = Long.parseLong(request.getParameter("agencyId"));
			
			logger.info(ApplicationConstants.LogMessageKeys.SAVECREDITLIMITSDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId);

			Map<String, String[]> requestParams = request.getParameterMap();
			MasterDataFactory mdf = new MasterDataFactory();
			mdf.saveAgencyCreditLimitsData(requestParams, agencyId);
			fetchCreditLimitsData(request,response);
			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
	
			logger.info(ApplicationConstants.LogMessageKeys.SAVECREDITLIMITSDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					agencyId,ApplicationConstants.SUCCESS_STATUS_STRING);

		}catch(BusinessException be){
			msgObj.setMessageText(be.getExceptionMessage());
			
			logger.info(ApplicationConstants.LogMessageKeys.SAVECREDITLIMITSDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),  
					agencyId,be);

		}
		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}

	public void deleteCreditLimitsData(HttpServletRequest request, HttpServletResponse response){
		
		MessageObject msgObj = new MessageObject(3553, "DELETE CREDIT LIMITS DATA", ApplicationConstants.ERROR_STATUS_STRING);
		try {
			request.setAttribute("checkDisplay", "1");
			agencyId = Long.parseLong(request.getParameter("agencyId"));
			clDataId = Long.parseLong(request.getParameter("itemId"));
			
			logger.info(ApplicationConstants.LogMessageKeys.DELETECREDITLIMITSDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.CREDITLIMITDATAID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),
					agencyId,clDataId);

			MasterDataFactory mdf = new MasterDataFactory();
			mdf.deleteAgencyCreditLimitsData(clDataId, agencyId);
			fetchCreditLimitsData(request,response);
			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);
			logger.info(ApplicationConstants.LogMessageKeys.DELETECREDITLIMITSDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue()  +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.CREDITLIMITDATAID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.actionStatusKeys.STATUS.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId,clDataId,
					ApplicationConstants.SUCCESS_STATUS_STRING);

		}catch(BusinessException be){
			msgObj.setMessageText(be.getExceptionMessage());
			
			logger.info(ApplicationConstants.LogMessageKeys.DELETECREDITLIMITSDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue()  +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.CREDITLIMITDATAID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),agencyId,clDataId,be);

		}
		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}
	
	
	public void updateCreditLimitsData(HttpServletRequest request, HttpServletResponse response){
		MessageObject msgObj = new MessageObject(3554, "UPDATE CREDIT LIMITS DATA", ApplicationConstants.ERROR_STATUS_STRING);
		try {
			request.setAttribute("checkDisplay", "1");
			 agencyId = Long.parseLong(request.getParameter("agencyId"));
			 cid=request.getParameter("cid");
			 customername=request.getParameter("cpname");
			 dis19kgndne=request.getParameter("dis19kgndne");
			 dis19kgcgas=request.getParameter("dis19kgcgas");
			 dis35kgndne=request.getParameter("dis35kgndne");
			 dis47_5kgndne=request.getParameter("dis47_5kgndne");
			 dis450kgsumo=request.getParameter("dis450kgsumo");

			logger.info(ApplicationConstants.LogMessageKeys.UPDATECREDITLIMITSDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.CUSTOMERID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue() +ApplicationConstants.paramKeys.DISCOUNTON19KGNDNECYL.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue()+ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.DISCOUNTON19KGCUTTINGGASCYL.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.paramKeys.DISCOUNTON35KGNDNECYL.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue()+ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.DISCOUNTON47_5KGNDNECYL.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.paramKeys.DISCOUNTON450KGSUMOCYL.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),
					agencyId,cid,dis19kgndne,dis19kgcgas,dis35kgndne,dis47_5kgndne,dis450kgsumo);

			Map<String, String> requestParams = new HashMap<>();
			MasterDataFactory mdf = new MasterDataFactory();
			requestParams.put("cid",request.getParameter("cid"));
			requestParams.put("dis19kgndne",request.getParameter("dis19kgndne"));
			requestParams.put("dis19kgcgas",request.getParameter("dis19kgcgas"));
			requestParams.put("dis35kgndne",request.getParameter("dis35kgndne"));
			requestParams.put("dis47_5kgndne",request.getParameter("dis47_5kgndne"));
			requestParams.put("dis450kgsumo",request.getParameter("dis450kgsumo"));


			mdf.updateCreditlimitsData(requestParams, agencyId);
			fetchCreditLimitsData(request,response);
			msgObj.setMessageStatus(ApplicationConstants.SUCCESS_STATUS_STRING);

			logger.info(ApplicationConstants.LogMessageKeys.UPDATECREDITLIMITSDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.CUSTOMERID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue() +ApplicationConstants.paramKeys.DISCOUNTON19KGNDNECYL.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue()+ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.DISCOUNTON19KGCUTTINGGASCYL.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.paramKeys.DISCOUNTON35KGNDNECYL.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue()+ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.DISCOUNTON47_5KGNDNECYL.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.paramKeys.DISCOUNTON450KGSUMOCYL.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue()+ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.STATUS.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),
					agencyId,cid,dis19kgndne,dis19kgcgas,dis35kgndne,dis47_5kgndne,dis450kgsumo,ApplicationConstants.SUCCESS_STATUS_STRING);

		}catch(BusinessException be){
			msgObj.setMessageText(be.getExceptionMessage());

			logger.info(ApplicationConstants.LogMessageKeys.UPDATECREDITLIMITSDATA.getValue()
					+ ApplicationConstants.paramKeys.PARAM.getValue()+ApplicationConstants.paramKeys.AGENCYID.getValue()
    				+ ApplicationConstants.LogKeys.LOG_PARAM.getValue() +ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.CUSTOMERID.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue() +ApplicationConstants.paramKeys.DISCOUNTON19KGNDNECYL.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue()+ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.DISCOUNTON19KGCUTTINGGASCYL.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.paramKeys.DISCOUNTON35KGNDNECYL.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue()+ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.paramKeys.DISCOUNTON47_5KGNDNECYL.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue()
					+ApplicationConstants.paramKeys.SEPERATOR.getValue()+ApplicationConstants.paramKeys.DISCOUNTON450KGSUMOCYL.toString()
					+ApplicationConstants.LogKeys.LOG_PARAM.getValue()+ApplicationConstants.paramKeys.SEPERATOR.getValue()
					+ApplicationConstants.actionStatusKeys.BUSINESSEXCEPTION.toString()+ApplicationConstants.LogKeys.LOG_PARAM.getValue(),
					agencyId,cid,dis19kgndne,dis19kgcgas,dis35kgndne,dis47_5kgndne,dis450kgsumo,be);

		}
		request.setAttribute(ApplicationConstants.MESSAGE_OBJECT_ATTRIBUTE_STRING, msgObj);
	}


*/
}
