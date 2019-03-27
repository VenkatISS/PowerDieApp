package com.it.diesuiteapp.framework.bos;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.it.diesuiteapp.framework.model.UserDetailsDO;
import com.it.diesuiteapp.systemservices.exceptions.BusinessException;

public class UserDataBO {
	
	public UserDetailsDO createDO(int itemType, String refNumber,
			String validUPTO, int remindBefore, String remarks, long agencyId)
			throws BusinessException {
		UserDetailsDO uData = new UserDetailsDO();
		try {
			uData.setUserId(itemType);
			uData.setUserName(refNumber);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date d = sdf.parse(validUPTO);
			uData.setCreatedBy(agencyId);
			uData.setCreatedDate(System.currentTimeMillis());
			uData.setVersion(1);
			uData.setDeleted(0);
		} catch (Exception e){
			throw new BusinessException("UNABLE TO SAVE AGENCY STATUTORY DATA");
		}
		return uData;
	}

}
