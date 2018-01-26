package com.inthub.eventmonitor.services;

import com.inthub.eventmonitor.models.MonitoringEvent;
import com.inthub.eventmonitor.models.Total;
import com.inthub.eventmonitor.repositories.MonitoringEventRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MonitoringEventService {

	private int pageNumber = 0;
	private int pageSize = 10;

    private static final Logger logger = LogManager.getLogger(MonitoringEventService.class);

	public static class PageSpecification {
		public static Pageable constructPageSpecification(final int pageIndex, final int pageSize) {
			Pageable pageSpecification = new PageRequest(pageIndex, pageSize,
					new Sort(Sort.Direction.DESC, "event.timestamp"));
			return pageSpecification;
		}
	}

	@Autowired
	private MonitoringEventRepository eventRepository;

	/*
	 * public Page<MonitoringEvent> findAllByPages(int pageNumber , int pageSize){
	 * Sort sortByEventTimestampDesc = new Sort(Sort.Direction.DESC,
	 * "event.timestamp"); PageRequest request = new PageRequest(pageNumber,
	 * pageSize, sortByEventTimestampDesc); //return
	 * this.eventRepository.findAll(request).getContent(); return
	 * this.eventRepository.findAll(request);
	 * 
	 * }
	 */

	// #################################################################
	// ### START = Direct Query with a specific field & actual value ###
	// #################################################################

	// returns total number of records in collection
	public Total count() {
	    logger.debug("Performing count");
		Total total = new Total(this.eventRepository.count());
        logger.debug("count = "+total.getCount());
	    return total;
	}

	// @Cacheable("monitoringevents")
	public Page<MonitoringEvent> findAll(Pageable pageable) {
        logger.debug("Performing findAll@Pageable");
		return this.eventRepository.findAll(pageable);
	}

	public List<MonitoringEvent> findAll(Sort sort) {
		// return this.eventRepository.findAll(sort);
		// using java8 lambda expression.
        logger.debug("Performing findAll@Sort");
		List<MonitoringEvent> eventsList = new ArrayList<>();
		this.eventRepository.findAll(sort).forEach(eventsList::add);
		return eventsList;

	}

	public List<MonitoringEvent> findByGlobalTransactionId(String globalTxnId) {
        logger.debug("Performing findByGlobalTransactionId");
		return this.eventRepository.findByGlobalTransactionId(globalTxnId);
	}

	public List<MonitoringEvent> findByParentTransactionId(String parentTxnId) {
        logger.debug("Performing findByParentTransactionId");
		return this.eventRepository.findByParentTransactionId(parentTxnId);
	}

	public List<MonitoringEvent> findByLocalTransactionId(String localTxnId) {
        logger.debug("Performing findByLocalTransactionId");
		return this.eventRepository.findByLocalTransactionId(localTxnId);
	}

	public List<MonitoringEvent> findByBusinessProcess(String businessProcess) {
        logger.debug("Performing findByBusinessProcess");
		return this.eventRepository.findByBusinessProcess(businessProcess);
	}

	public List<MonitoringEvent> findBySourceAppInfo(String sourceAppInfo) {
        logger.debug("Performing findBySourceAppInfo");
		return this.eventRepository.findBySourceAppInfo(sourceAppInfo);
	}

	public List<MonitoringEvent> findByDestinationAppInfo(String destinationAppInfo) {
        logger.debug("Performing findByDestinationAppInfo");
		return this.eventRepository.findByDestinationAppInfo(destinationAppInfo);
	}

	public List<MonitoringEvent> findByApplicationName(String applicationName) {
        logger.debug("Performing findByApplicationName");
		return this.eventRepository.findByApplicationName(applicationName);
	}

	public List<MonitoringEvent> findByMsgFlowName(String msgFlowName) {
        logger.debug("Performing findByMsgFlowName");
		return this.eventRepository.findByMsgFlowName(msgFlowName);
	}

	public List<MonitoringEvent> findByErrorCode(String errorCode) {
        logger.debug("Performing findByErrorCode");
		return this.eventRepository.findByErrorCode(errorCode);
	}

	public List<MonitoringEvent> findAllErrors(Sort sort, boolean allErrors) {
        logger.debug("Performing findAllErrors");
		return this.eventRepository.findAllErrors(sort, allErrors);
	}

	// ######################################################################################
	// ### START = Search based on regex expression (startWith | endsWith |
	// containing..) ###
	// ######################################################################################

    public List<MonitoringEvent> findByGlobalTransactionIdStartingWith(String globalTxnIdPreffix) {
        logger.debug("Performing findByGlobalTransactionIdStartingWith");
        return null;
    }

    public List<MonitoringEvent> findByGlobalTransactionIdEndsWith(String globalTxnIdSuffix) {
        logger.debug("Performing findByGlobalTransactionIdEndsWith");
        return null;
    }

	public List<MonitoringEvent> findByGlobalTransactionIdLike(String globalTxnIdRegex) {
        logger.debug("Performing findByGlobalTransactionIdLike");
		return this.eventRepository.findByGlobalTransactionIdLike(globalTxnIdRegex);
	}

	public List<MonitoringEvent> findByParentTransactionIdLike(String parentTxnIdRegex) {
        logger.debug("Performing findByParentTransactionIdLike");
		return this.eventRepository.findByParentTransactionIdLike(parentTxnIdRegex);
	}

	public List<MonitoringEvent> findByLocalTransactionIdLike(String localTxnIdRegex) {
        logger.debug("Performing findByLocalTransactionIdLike");
		return this.eventRepository.findByLocalTransactionIdLike(localTxnIdRegex);
	}

	public List<MonitoringEvent> findByApplicationNameLike(String applicationNameRegex) {
        logger.debug("Performing findByApplicationNameLike");
		return this.eventRepository.findByApplicationNameLike(applicationNameRegex);
	}

	public List<MonitoringEvent> findByMsgFlowNameLike(String msgFlowNameRegex) {
        logger.debug("Performing findByMsgFlowNameLike");
		return this.eventRepository.findByMsgFlowNameLike(msgFlowNameRegex);
	}

	public List<MonitoringEvent> findByEventTimestampBetween(String startTimestamp, String endTimestamp) {
        logger.debug("Performing findAllErrors");
		return this.eventRepository.findByEventTimestampBetween(startTimestamp, endTimestamp);
	}

	public void simulateSlowService() {
		try {
			long time = 3000L;
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
