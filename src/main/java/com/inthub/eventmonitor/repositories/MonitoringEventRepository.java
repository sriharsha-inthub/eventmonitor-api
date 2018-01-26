package com.inthub.eventmonitor.repositories;

import com.inthub.eventmonitor.models.MonitoringEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MonitoringEventRepository
		extends MongoRepository<MonitoringEvent, String>, PagingAndSortingRepository<MonitoringEvent, String> {

	// #################################################################
	// ### START = Direct Query with a specific field & actual value ###
	// #################################################################

	// returns total number of records in collection
	long count();

	// @Cacheable("monitoringevents")
	Page<MonitoringEvent> findAll(Pageable pageable);

	List<MonitoringEvent> findAll(Sort sort);

	@Query("{'transactionId.global_Transaction_Id': ?0}")
	List<MonitoringEvent> findByGlobalTransactionId(String globalTxnId);

	@Query("{'transactionId.parent_Transaction_Id': ?0}")
	List<MonitoringEvent> findByParentTransactionId(String parentTxnId);

	@Query("{'transactionId.parent_Transaction_Id': ?0}")
	List<MonitoringEvent> findByLocalTransactionId(String localTxnId);

	@Query("{'business_Process': ?0}")
	List<MonitoringEvent> findByBusinessProcess(String businessProcess);

	@Query("{'appInfo.source_App': ?0}")
	List<MonitoringEvent> findBySourceAppInfo(String sourceAppInfo);

	@Query("{'appInfo.destination_App': ?0}")
	List<MonitoringEvent> findByDestinationAppInfo(String destinationAppInfo);

	@Query("{'transactionHierarchy.applicationName': ?0}")
	List<MonitoringEvent> findByApplicationName(String applicationName);

	@Query("{'transactionHierarchy.msgFlowName': ?0}")
	List<MonitoringEvent> findByMsgFlowName(String msgFlowName);

	@Query("{'errors.code': ?0}")
	List<MonitoringEvent> findByErrorCode(String errorCode);

	@Query("{'is_Error': ?0}")
	List<MonitoringEvent> findAllErrors(Sort sort, boolean allErrors);

	// ######################################################################################
	// ### START = Search based on regex expression (startWith | endsWith |
	// containing..) ###
	// ######################################################################################

	@Query("{ 'transactionId.global_Transaction_Id' : { $regex: ?0 } }")
	List<MonitoringEvent> findByGlobalTransactionIdLike(String globalTxnIdRegex);
	// public List<MonitoringEvent> findByGlobalTransactionIdStartingWith(String
	// globalTxnIdPreffix);
	// public List<MonitoringEvent> findByGlobalTransactionIdEndsWith(String
	// globalTxnIdSuffix);

	@Query("{ 'transactionId.parent_Transaction_Id' : { $regex: ?0 } }")
	List<MonitoringEvent> findByParentTransactionIdLike(String parentTxnIdRegex);

	@Query("{ 'transactionId.local_Transaction_Id' : { $regex: ?0 } }")
	List<MonitoringEvent> findByLocalTransactionIdLike(String localTxnIdRegex);

	@Query("{ 'transactionHierarchy.applicationName' : { $regex: ?0 } }")
	List<MonitoringEvent> findByApplicationNameLike(String applicationNameRegex);

	@Query("{ 'transactionHierarchy.msgFlowName' : { $regex: ?0 } }")
	List<MonitoringEvent> findByMsgFlowNameLike(String msgFlowNameRegex);

	@Query("{ 'event.timestamp' : { $gt: ?0, $lt: ?1 } }")
	List<MonitoringEvent> findByEventTimestampBetween(String startTimestamp, String endTimestamp);

}
