package com.inthub.eventmonitor.services;

import com.inthub.eventmonitor.models.MonitoringEvent;
import com.inthub.eventmonitor.repositories.MonitoringEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
	public long count() {
		return this.eventRepository.count();
	}

	@Cacheable("monitoringevents")
	public Page<MonitoringEvent> findAll(Pageable pageable) {
		return this.eventRepository.findAll(pageable);
	}

	public List<MonitoringEvent> findAll(Sort sort) {
		// return this.eventRepository.findAll(sort);
		// using java8 lambda expression.
		List<MonitoringEvent> eventsList = new ArrayList<>();
		this.eventRepository.findAll(sort).forEach(eventsList::add);
		return eventsList;

	}

	public List<MonitoringEvent> findByGlobalTransactionId(String globalTxnId) {
		return this.eventRepository.findByGlobalTransactionId(globalTxnId);
	}

	public List<MonitoringEvent> findByParentTransactionId(String parentTxnId) {
		return this.eventRepository.findByParentTransactionId(parentTxnId);
	}

	public List<MonitoringEvent> findByLocalTransactionId(String localTxnId) {
		return this.eventRepository.findByLocalTransactionId(localTxnId);
	}

	public List<MonitoringEvent> findByBusinessProcess(String businessProcess) {
		return this.eventRepository.findByBusinessProcess(businessProcess);
	}

	public List<MonitoringEvent> findBySourceAppInfo(String sourceAppInfo) {
		return this.eventRepository.findBySourceAppInfo(sourceAppInfo);
	}

	public List<MonitoringEvent> findByDestinationAppInfo(String destinationAppInfo) {
		return this.eventRepository.findByDestinationAppInfo(destinationAppInfo);
	}

	public List<MonitoringEvent> findByApplicationName(String applicationName) {
		return this.eventRepository.findByApplicationName(applicationName);
	}

	public List<MonitoringEvent> findByMsgFlowName(String msgFlowName) {
		return this.eventRepository.findByMsgFlowName(msgFlowName);
	}

	public List<MonitoringEvent> findByErrorCode(String errorCode) {
		return this.eventRepository.findByErrorCode(errorCode);
	}

	public List<MonitoringEvent> findAllErrors(Sort sort, boolean allErrors) {
		return this.eventRepository.findAllErrors(sort, allErrors);
	}

	// ######################################################################################
	// ### START = Search based on regex expression (startWith | endsWith |
	// containing..) ###
	// ######################################################################################

	public List<MonitoringEvent> findByGlobalTransactionIdLike(String globalTxnIdRegex) {
		return this.eventRepository.findByGlobalTransactionIdLike(globalTxnIdRegex);
	}
	// List<MonitoringEvent> findByGlobalTransactionIdStartingWith(String
	// globalTxnIdPreffix);
	// List<MonitoringEvent> findByGlobalTransactionIdEndsWith(String
	// globalTxnIdSuffix);

	public List<MonitoringEvent> findByParentTransactionIdLike(String parentTxnIdRegex) {
		return this.eventRepository.findByParentTransactionIdLike(parentTxnIdRegex);
	}

	public List<MonitoringEvent> findByLocalTransactionIdLike(String localTxnIdRegex) {
		return this.eventRepository.findByLocalTransactionIdLike(localTxnIdRegex);
	}

	public List<MonitoringEvent> findByApplicationNameLike(String applicationNameRegex) {
		return this.eventRepository.findByApplicationNameLike(applicationNameRegex);
	}

	public List<MonitoringEvent> findByMsgFlowNameLike(String msgFlowNameRegex) {
		return this.eventRepository.findByMsgFlowNameLike(msgFlowNameRegex);
	}

	public List<MonitoringEvent> findByEventTimestampBetween(String startTimestamp, String endTimestamp) {
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
