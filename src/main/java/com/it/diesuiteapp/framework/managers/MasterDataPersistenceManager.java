package com.it.diesuiteapp.framework.managers;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.it.diesuiteapp.framework.model.UserDetailsDO;
import com.it.diesuiteapp.systemservices.exceptions.BusinessException;
import com.it.diesuiteapp.utils.HibernateUtil;

public class MasterDataPersistenceManager {

	Logger logger = Logger.getLogger(MasterDataPersistenceManager.class.getName());

	public List<UserDetailsDO> getAdminUserData(long userId) throws BusinessException {
		List<UserDetailsDO> doList = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			Query<UserDetailsDO> query = session.createQuery("from UserDetailsDO where userId = ?1 and deleted = ?2");
			query.setParameter(1, userId);
			query.setParameter(2, 0);
			List<UserDetailsDO> result = query.getResultList(); 
			if(result.size()>0) {
				for (UserDetailsDO userDataDO : result) {
					doList.add(userDataDO);
				}
			} 
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
		return doList;
	}

	public void saveAdminUserData(List<UserDetailsDO> doList) throws BusinessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (UserDetailsDO statutoryDataDO : doList) {
				session.save(statutoryDataDO);
			}
			tx.commit();
		}catch(Exception e) {
			try {
				if (tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> saveAgencyStatutoryData  is not succesful");
			}
			e.printStackTrace();
			logger.error(e);
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
	}

	public void deleteAdminUserData(long itemId) throws BusinessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			UserDetailsDO sddo = session.get(UserDetailsDO.class, new Long(itemId));
			sddo.setDeleted(1);
			session.update(sddo);
			tx.commit();
		}catch(Exception e) {
			try {
				if (tx != null)
					tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> deleteAgencyStatutoryData  is not succesful");
			}
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
	}

	/*//Staff Data
	public List<StaffDataDO> getAgencyStaffData(long agencyId) throws BusinessException {
		List<StaffDataDO> doList = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			Query<StaffDataDO> query = session.createQuery("from StaffDataDO where created_by = ?1 and deleted = ?2 ");
			query.setParameter(1, agencyId);
			query.setParameter(2, 0);
			List<StaffDataDO> result = query.getResultList(); 
			if(result.size()>0) {
				for (StaffDataDO staffDataDO : result) {
					doList.add(staffDataDO);
				}
			} 
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
		return doList;
	}
	
	//Staff Data
		public List<StaffDataDO> getAgencyAllStaffData(long agencyId) throws BusinessException {
			List<StaffDataDO> doList = new ArrayList<>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			try {
				@SuppressWarnings("unchecked")
				Query<StaffDataDO> query = session.createQuery("from StaffDataDO where created_by = ?1 and (deleted = ?2 or deleted = ?3) ");
				query.setParameter(1, agencyId);
				query.setParameter(2, 0);
				query.setParameter(3, 1);
				List<StaffDataDO> result = query.getResultList(); 
				if(result.size()>0) {
					for (StaffDataDO staffDataDO : result) {
						doList.add(staffDataDO);
					}
				} 
			}catch(Exception e) {
				logger.error(e);
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
			}finally {
				session.clear();
				session.close();
			}
			return doList;
		}

		@SuppressWarnings("unchecked")
		public void saveAgencyStaffData(List<StaffDataDO> doList) throws BusinessException {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				for (StaffDataDO staffDataDO : doList) {
					String ec = staffDataDO.getEmp_code();
					Query<StaffDataDO> query = session.createQuery("from StaffDataDO where created_by = ?1 and deleted = ?2 and emp_code="+"'"+ec+"'");
					query.setParameter(1, staffDataDO.getCreated_by());
					query.setParameter(2, 1);
					List<StaffDataDO> result = query.getResultList(); 
					if(result.size()>0) {
						for (StaffDataDO staffDataDO2 : result) {
							if((staffDataDO.getEmp_code()).equalsIgnoreCase((staffDataDO2.getEmp_code()))){
								session.clear();
								staffDataDO.setId(staffDataDO2.getId());
								staffDataDO.setDeleted(0);
								staffDataDO.setCreated_date(System.currentTimeMillis());
								session.update(staffDataDO);
							}else{
								session.save(staffDataDO);
							}
						}
					}else{
						session.save(staffDataDO);
					}
				}
				tx.commit();
			}catch(Exception e) {
				try {
					if (tx != null) tx.rollback();
				}catch (HibernateException e1) {
					logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> saveAgencyStaffData  is not succesful");
				}
				e.printStackTrace();
				logger.error(e);
				throw new BusinessException(e.getMessage());
			}finally {
				session.clear();
				session.close();
			}
		}


	public void deleteAgencyStaffData(long itemId) throws BusinessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			StaffDataDO sddo = session.get(StaffDataDO.class, new Long(itemId));
			sddo.setDeleted(1);
			session.update(sddo);
			tx.commit();
		}catch(Exception e) {
			try {
				if (tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> deleteAgencyStaffData  is not succesful");
			}
			e.printStackTrace();
			logger.error(e);
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
	}

	//Fleet Data
	public List<FleetDataDO> getAgencyFleetData(long agencyId) throws BusinessException {
		List<FleetDataDO> doList = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			Query<FleetDataDO> query = session.createQuery("from FleetDataDO where created_by = ?1 and deleted = ?2 ");
			query.setParameter(1, agencyId);
			query.setParameter(2, 0);
			List<FleetDataDO> result = query.getResultList(); 
			if(result.size()>0) {
				for (FleetDataDO fleetDataDO : result) {
					doList.add(fleetDataDO);
				}
			} 
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
		return doList;
	}
	//Fleet Data
		public List<FleetDataDO> getAgencyAllFleetData(long agencyId) throws BusinessException {
			List<FleetDataDO> doList = new ArrayList<>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			try {
				@SuppressWarnings("unchecked")
				Query<FleetDataDO> query = session.createQuery("from FleetDataDO where created_by = ?1 and (deleted = ?2  or deleted = ?3)");
				query.setParameter(1, agencyId);
				query.setParameter(2, 0);
				query.setParameter(3, 1);
				List<FleetDataDO> result = query.getResultList(); 
				if(result.size()>0) {
					for (FleetDataDO fleetDataDO : result) {
						doList.add(fleetDataDO);
					}
				} 
			}catch(Exception e) {
				e.printStackTrace();
				logger.error(e);
				throw new BusinessException(e.getMessage());
			}finally {
				session.clear();
				session.close();
			}
			return doList;
		}

		@SuppressWarnings("unchecked")
		public void saveAgencyFleetData(List<FleetDataDO> doList) throws BusinessException {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				for (FleetDataDO fleetDataDO : doList) {
					String vn = fleetDataDO.getVehicle_no();
					Query<FleetDataDO> query = session.createQuery("from FleetDataDO where created_by = ?1 and deleted = ?2 and vehicle_no="+"'"+vn+"'");
					query.setParameter(1, fleetDataDO.getCreated_by());
					query.setParameter(2, 1);
					List<FleetDataDO> result = query.getResultList(); 
					if(result.size()>0) {
						for (FleetDataDO fleetDataDO2 : result) {
							if((fleetDataDO.getVehicle_no()).equalsIgnoreCase((fleetDataDO2.getVehicle_no()))){
								session.clear();
								fleetDataDO.setId(fleetDataDO2.getId());
								fleetDataDO.setDeleted(0);
								fleetDataDO.setCreated_date(System.currentTimeMillis());
								session.update(fleetDataDO);
							}else{
								session.save(fleetDataDO);
							}
						}
					}else{
						session.save(fleetDataDO);
					}
				}
				tx.commit();
			}catch(Exception e) {
				try {
					if (tx != null) tx.rollback();
				}catch (HibernateException e1) {
					logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> saveAgencyFleetData  is not succesful");
				}
				e.printStackTrace();
				logger.error(e);
				throw new BusinessException(e.getMessage());
			}finally {
				session.clear();
				session.close();
			}
		}


	public void deleteAgencyFleetData(long itemId) throws BusinessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			FleetDataDO fddo = session.get(FleetDataDO.class, new Long(itemId));
			fddo.setDeleted(1);
			session.update(fddo);
			tx.commit();
		}catch(Exception e) {
			try {
				if (tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> deleteAgencyFleetData is not succesful");
			}
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
	}

	//Bank Data
	public List<BankDataDO> getAgencyBankData(long agencyId) throws BusinessException {
		List<BankDataDO> doList = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			Query<BankDataDO> query = session.createQuery("from BankDataDO where created_by = ?1 and deleted = ?2 ");
			query.setParameter(1, agencyId);
			query.setParameter(2, 0);
			List<BankDataDO> result = query.getResultList(); 
			if(result.size()>0) {
				for (BankDataDO bankDataDO : result) {
					doList.add(bankDataDO);
				}
			} 
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
		return doList;
	}
	
	//Bank Data
		public List<BankDataDO> getAgencyAllBankData(long agencyId) throws BusinessException {
			List<BankDataDO> doList = new ArrayList<>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			try {
				@SuppressWarnings("unchecked")
				Query<BankDataDO> query = session.createQuery("from BankDataDO where created_by = ?1 and (deleted = ?2 or deleted = ?3) ");
				query.setParameter(1, agencyId);
				query.setParameter(2, 0);
				query.setParameter(3, 1);
				List<BankDataDO> result = query.getResultList(); 
				if(result.size()>0) {
					for (BankDataDO bankDataDO : result) {
						doList.add(bankDataDO);
					}
				} 
			}catch(Exception e) {
				e.printStackTrace();
				logger.error(e);
				throw new BusinessException(e.getMessage());
			}finally {
				session.clear();
				session.close();
			}
			return doList;
		}

		@SuppressWarnings("unchecked")
		public void saveAgencyBankData(List<BankDataDO> doList) throws BusinessException {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				for (BankDataDO bankDataDO : doList) {
					String acn = bankDataDO.getBank_acc_no();
					Query<BankDataDO> query = session.createQuery("from BankDataDO where created_by = ?1 and deleted = ?2 and bank_acc_no="+"'"+acn+"'");
					query.setParameter(1, bankDataDO.getCreated_by());
					query.setParameter(2, 1);
					List<BankDataDO> result = query.getResultList(); 
					if(result.size()>0) {
						for (BankDataDO bankDataDO2 : result) {
							if((bankDataDO.getBank_acc_no()).equalsIgnoreCase((bankDataDO2.getBank_acc_no()))){
								session.clear();
								bankDataDO.setId(bankDataDO2.getId());
								bankDataDO.setDeleted(0);
								bankDataDO.setCreated_date(System.currentTimeMillis());
								session.update(bankDataDO);
							}else{
								session.save(bankDataDO);
							}
						}
					}else{
						session.save(bankDataDO);
					}
				}
				tx.commit();
			}catch(Exception e) {
				try {
					if (tx != null) tx.rollback();
				}catch (HibernateException e1) {
					logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> saveAgencyBankData is not succesful");
				}
				logger.error(e);
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
			}finally {
				session.clear();
				session.close();
			}
		}

	public void deleteAgencyBankData(long itemId) throws BusinessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			BankDataDO bddo = session.get(BankDataDO.class, new Long(itemId));
			bddo.setDeleted(1);
			session.update(bddo);
			tx.commit();
		}catch(Exception e) {
			try {
				if (tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> deleteAgencyBankData is not succesful");
			}
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
	}

	//CVO Data
	public List<CVODataDO> getAgencyCVOData(long agencyId) throws BusinessException {
		List<CVODataDO> doList = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			Query<CVODataDO> query = session.createQuery("from CVODataDO where created_by = ?1 and deleted = ?2 ");
			query.setParameter(1, agencyId);
			query.setParameter(2, 0);
			List<CVODataDO> result = query.getResultList(); 
			if(result.size()>0) {
				for (CVODataDO cvodo : result) {
					doList.add(cvodo);
				}
			} 
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
		return doList;
	}
	
	//CVO Data
		public List<CVODataDO> getAgencyAllCVOData(long agencyId) throws BusinessException {
			List<CVODataDO> doList = new ArrayList<>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			try {
				@SuppressWarnings("unchecked")
				Query<CVODataDO> query = session.createQuery("from CVODataDO where created_by = ?1");
				query.setParameter(1, agencyId);
				List<CVODataDO> result = query.getResultList(); 
				if(result.size()>0) {
					for (CVODataDO cvodo : result) {
						doList.add(cvodo);
					}
				} 
			}catch(Exception e) {
				logger.error(e);
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
			}finally {
				session.clear();
				session.close();
			}
			return doList;
		}

		@SuppressWarnings("unchecked")
		public void saveAgencyCVOData(List<CVODataDO> doList,
				List<AgencyCVOBalanceDataDO> acvoDOList, String effdate) throws BusinessException {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
	            List<Long> list = new ArrayList<>();
	            Map<Long,Integer> map =new HashMap<>();
	            
				for (CVODataDO cvodo : doList) {
					if(cvodo.getIs_gst_reg()==1){
						String gstin = cvodo.getCvo_tin();
						Query<CVODataDO> query = session.createQuery("from CVODataDO where created_by = ?1 and deleted = ?2 and cvo_tin="+"'"+gstin+"'");
						query.setParameter(1, cvodo.getCreated_by());
						query.setParameter(2, 1);
						List<CVODataDO> result = query.getResultList(); 
						if(result.size()>0) {
							for (CVODataDO cvodo2 : result) {
								if((cvodo.getCvo_tin()).equalsIgnoreCase((cvodo2.getCvo_tin()))){
									session.clear();
									cvodo.setId(cvodo2.getId());
									cvodo.setDeleted(0);
									cvodo.setCreated_date(System.currentTimeMillis());
									session.update(cvodo);

									map.put(cvodo.getId(), 1);
		                            list.add(cvodo.getId());
									
								}else{
									list.add((Long) session.save(cvodo));
		                            map.put(cvodo.getId(), 0);
								}
							}
						}else{
							list.add((Long) session.save(cvodo));
		                    map.put(cvodo.getId(), 0);
						}
					}else{
						list.add((Long) session.save(cvodo));
	                    map.put(cvodo.getId(), 0);
					}
				}
				
				int count=0;
				for (AgencyCVOBalanceDataDO cvobaldo : acvoDOList) {
					cvobaldo.setCvo_refid(list.get(count));
					cvobaldo.setRef_id(0);
					cvobaldo.setCvoflag(0);
					cvobaldo.setInv_date(Long.parseLong(effdate));
					if(map.get(cvobaldo.getCvo_refid())==0){
						session.save(cvobaldo);	
					}
					++count;
		       	}
				
				tx.commit();
								
			}catch(Exception e) {
				try {
					if (tx != null) tx.rollback();
				}catch (HibernateException e1) {
					logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> saveAgencyCVOData is not succesful");
				}
				e.printStackTrace();
				logger.error(e);
				throw new BusinessException(e.getMessage());
			}finally {
				session.clear();
				session.close();
			}
		}

	public void deleteAgencyCVOData(long itemId) throws BusinessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			CVODataDO cvodo = session.get(CVODataDO.class, new Long(itemId));
			cvodo.setDeleted(1);
			session.update(cvodo);
			tx.commit();
		}catch(Exception e) {
			try {
				if (tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> deleteAgencyCVOData is not succesful");
			}
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
	}

	//Expenditure Data
	public List<ExpenditureDataDO> getAgencyExpenditureData(long agencyId) throws BusinessException {
		List<ExpenditureDataDO> doList = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			Query<ExpenditureDataDO> query = session.createQuery("from ExpenditureDataDO where created_by = ?1 and deleted = ?2 ");
			query.setParameter(1, agencyId);
			query.setParameter(2, 0);
			List<ExpenditureDataDO> result = query.getResultList(); 
			if(result.size()>0) {
				for (ExpenditureDataDO edo : result) {
					doList.add(edo);
				}
			} 
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
		return doList;
	}

	public void saveAgencyExpenditureData(List<ExpenditureDataDO> doList) throws BusinessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (ExpenditureDataDO edo : doList) {
				session.save(edo);
			}
			tx.commit();
		}catch(Exception e) {
			try {
				if (tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> saveAgencyExpenditureData is not succesful");
			}
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
	}

	public void deleteAgencyExpenditureData(long itemId) throws BusinessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			ExpenditureDataDO dataDO = session.get(ExpenditureDataDO.class, new Long(itemId));
			dataDO.setDeleted(1);
			session.update(dataDO);
			tx.commit();
		}catch(Exception e) {
			try {
				if(tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> deleteAgencyExpenditureData is not succesful");
			}
			e.printStackTrace();
			logger.error(e);
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
	}

	//Credit Limits Data
	public List<CreditLimitsDataDO> getAgencyCreditLimitsData(long agencyId) throws BusinessException {
		List<CreditLimitsDataDO> doList = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			Query<CreditLimitsDataDO> query = session.createQuery("from CreditLimitsDataDO where created_by = ?1 and deleted = ?2 ");
			query.setParameter(1, agencyId);
			query.setParameter(2, 0);
			List<CreditLimitsDataDO> result = query.getResultList(); 
			if(result.size()>0) {
				for (CreditLimitsDataDO dataDO : result) {
					doList.add(dataDO);
				}
			} 
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
		return doList;
	}

	public void saveAgencyCreditLimitsData(List<CreditLimitsDataDO> doList) throws BusinessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (CreditLimitsDataDO edo : doList) {
				session.save(edo);
			}
			tx.commit();
		}catch(Exception e) {
			try {
				if(tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> saveAgencyCreditLimitsData is not succesful");
			}
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
	}

	public void deleteAgencyCreditLimitsData(long itemId) throws BusinessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			CreditLimitsDataDO dataDO = session.get(CreditLimitsDataDO.class, new Long(itemId));
			dataDO.setDeleted(1);
			session.update(dataDO);
			tx.commit();
		}catch(Exception e) {
			try {
				if(tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> deleteAgencyCreditLimitsData is not succesful");
			}
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void updateCreditlimitsData(Map<String, String> requestparams,long agencyId) throws BusinessException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		 String cid=requestparams.get("cid");
		 String dis19kgndne=requestparams.get("dis19kgndne");
		 String dis19kgcgas=requestparams.get("dis19kgcgas");
		 String dis35kgndne=requestparams.get("dis35kgndne");
		 String dis47_5kgndne=requestparams.get("dis47_5kgndne");
		 String dis450kgsumo=requestparams.get("dis450kgsumo");

		try {
			tx = session.beginTransaction();
			Query query1 = session.createQuery(
					"UPDATE CreditLimitsDataDO SET disc_19kg_ndne='"+dis19kgndne+"', disc_19kg_cutting_gas='"+dis19kgcgas+"',disc_35kg_ndne='"+dis35kgndne+"',disc_47_5kg_ndne='"+dis47_5kgndne+"', disc_450kg_sumo='"+dis450kgsumo+"' WHERE created_by='"+agencyId+"' AND cust_id='"+Long.parseLong(cid)+"'");
			query1.executeUpdate();
			tx.commit();
		}catch(Exception e) {
			try {
				if(tx != null) tx.rollback();
			}catch (HibernateException e1) {
				logger.error("Transaction rollback in  com.it.erpapp.framework.managers --> MasterDataPersistenceManager --> updateCreditlimitsData is not succesful");
			}
			logger.error(e);
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}finally {
			session.clear();
			session.close();
		}
	}*/
}
