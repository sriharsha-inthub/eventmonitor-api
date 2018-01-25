package com.inthub.eventmonitor.controller;

import com.inthub.eventmonitor.models.MonitoringEvent;
import com.inthub.eventmonitor.services.MonitoringEventService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthNone;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@EnableCaching
@CrossOrigin("*")
@Api(
		name = "Event monitor API",
		description = "Provides a list of methods that exposes application monitoring events",
		group = "EventsAPI",
		visibility = ApiVisibility.PUBLIC,
		stage = ApiStage.BETA)
@ApiAuthNone
public class MonitoringEventController {
	
	// @Autowired
	// MonitoringEventRepository eventRepository;
	@Autowired MonitoringEventService eventService;


//	public MonitoringEventController(MonitoringEventService eventService){
//		this.eventService = eventService;
//	}
	
	//@Autowired MonitoringEventService eventService;
	@GetMapping("/events/count")
	@ApiMethod(description = "Get total number of monitoring events available in the database")
	public long getEventsCount() {
		return eventService.count();
	}
	
	@GetMapping("/events/page/{pageNumber}/{pageSize}")
	@ApiMethod(description = "Get all monitoring events from the database by pagination i.e pagenumber by size")
	public Page<MonitoringEvent> getAllEventsBypage(
			@ApiPathParam(name = "pageNumber") @PathVariable("pageNumber") int pageNumber,
			@ApiPathParam(name = "pageSize") @PathVariable("pageSize") int pageSize) {
		System.out.println("----------------------------------------------");
		System.out.println(java.time.LocalDateTime.now());
		Page<MonitoringEvent> eventList = eventService.findAll(MonitoringEventService.PageSpecification.constructPageSpecification(pageNumber, pageSize));
		System.out.println(java.time.LocalDateTime.now());
//		System.out.println("Page getSize() = "+eventList.getSize());
//		System.out.println("Page getTotalPages() = "+eventList.getTotalPages());
//		System.out.println("Page hasPrevious() = "+eventList.hasPrevious());
//		System.out.println("Page hasNext() = "+eventList.hasNext());
//		System.out.println("Page getTotalElements = "+eventList.getTotalElements());
		return eventList;
	}

	@GetMapping("/events")
	@ApiMethod(description = "Get all monitoring events from the database")
	public List<MonitoringEvent> getAllEvents() {
		Sort sortByEventTimestampDesc = new Sort(Sort.Direction.DESC, "event.timestamp");
		List<MonitoringEvent> eventList = eventService.findAll(sortByEventTimestampDesc);
		//eventList.forEach(System.out::println);
		return eventList; 
	}

	@GetMapping(value = "/events/txnIdGlobal/{txnIdGlobalValue}")
	@ApiMethod(description = "Get all monitoring events for specified global transaction id from the database")
	public ResponseEntity<List<MonitoringEvent>> getByGlobalTransactionId(
			@ApiPathParam(name = "txnIdGlobalValue") @PathVariable("txnIdGlobalValue") String globalTxnId) {
		List<MonitoringEvent> eventList = eventService.findByGlobalTransactionId(globalTxnId);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/txnIdParent/{txnIdParentValue}")
	@ApiMethod(description = "Get all monitoring events for specified parent transaction id from the database")
	public ResponseEntity<List<MonitoringEvent>> getByParentTransactionId(
			@ApiPathParam(name = "txnIdParentValue") @PathVariable("txnIdParentValue") String parentTxnId) {
		List<MonitoringEvent> eventList = eventService.findByParentTransactionId(parentTxnId);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/txnIdLocal/{txnIdLocalValue}")
	@ApiMethod(description = "Get monitoring event for specified local transaction id from the database")
	public ResponseEntity<List<MonitoringEvent>> getByLocalTransactionId(
			@ApiPathParam(name = "txnIdLocalValue") @PathVariable("txnIdLocalValue") String localTxnId) {
		List<MonitoringEvent> eventList = eventService.findByLocalTransactionId(localTxnId);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/businessProcess/{businessProcessValue}")
	@ApiMethod(description = "Get all monitoring event for a specific line of business from the database")
	public ResponseEntity<List<MonitoringEvent>> findByBusinessProcess(
			@ApiPathParam(name = "businessProcessValue") @PathVariable("businessProcessValue") String businessProcess) {
		List<MonitoringEvent> eventList = eventService.findByBusinessProcess(businessProcess);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/sourceApp/{sourceAppValue}")
	@ApiMethod(description = "Get all monitoring event for specific source app from the database")
	public ResponseEntity<List<MonitoringEvent>> findBySourceAppInfo(
			@ApiPathParam(name = "sourceAppValue") @PathVariable("sourceAppValue") String sourceAppValue) {
		List<MonitoringEvent> eventList = eventService.findBySourceAppInfo(sourceAppValue);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/destinationApp/{destinationAppValue}")
	@ApiMethod(description = "Get all monitoring event for specific destination app from the database")
	public ResponseEntity<List<MonitoringEvent>> findByDestinationAppInfo(
			@ApiPathParam(name = "destinationAppValue") @PathVariable("destinationAppValue") String destinationAppValue) {
		List<MonitoringEvent> eventList = eventService.findByDestinationAppInfo(destinationAppValue);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/appName/{appNameValue}")
	@ApiMethod(description = "Get all monitoring event for specific application name from the database")
	public ResponseEntity<List<MonitoringEvent>> findByApplicationName(
			@ApiPathParam(name = "appNameValue") @PathVariable("appNameValue") String applicationNameValue) {
		List<MonitoringEvent> eventList = eventService.findByApplicationName(applicationNameValue);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/msgFlowName/{msgFlowNameValue}/")
	@ApiMethod(description = "Get all monitoring event for specific messageflow from the database")
	public ResponseEntity<List<MonitoringEvent>> findByMsgFlowName(
			@ApiPathParam(name = "msgFlowNameValue") @PathVariable("msgFlowNameValue") String msgFlowNameValue) {
		List<MonitoringEvent> eventList = eventService.findByMsgFlowName(msgFlowNameValue);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/errorCode/{errorCodeValue}")
	@ApiMethod(description = "Get all monitoring event for specified error code from the database")
	public ResponseEntity<List<MonitoringEvent>> findByErrorCode(
			@ApiPathParam(name = "eventTimestampValue") @PathVariable("eventTimestampValue") String errorCodeValue) {
		List<MonitoringEvent> eventList = eventService.findByErrorCode(errorCodeValue);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/allErrors")
	@ApiMethod(description = "Get all error monitoring event from the database")
	public ResponseEntity<List<MonitoringEvent>> findAllErrors() {
		Sort sortByEventTimestampDesc = new Sort(Sort.Direction.DESC, "event.timestamp");
		List<MonitoringEvent> eventList = eventService.findAllErrors(sortByEventTimestampDesc, true);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/txnIdGlobal/like/{txnIdGlobalValue}")
	@ApiMethod(description = "Get all monitoring event that are of global transaction id like value")
	public ResponseEntity<List<MonitoringEvent>> findByGlobalTransactionIdLike(
			@ApiPathParam(name = "txnIdGlobalValue") @PathVariable("txnIdGlobalValue") String txnIdGloballike) {
		List<MonitoringEvent> eventList = eventService.findByGlobalTransactionIdLike(txnIdGloballike);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/txnIdParent/like/{txnIdParentValue}")
	@ApiMethod(description = "Get all monitoring event that are of parent transaction id like value")
	public ResponseEntity<List<MonitoringEvent>> findByParentTransactionIdLike(
			@ApiPathParam(name = "txnIdParentValue") @PathVariable("txnIdParentValue") String txnIdParentlike) {
		List<MonitoringEvent> eventList = eventService.findByParentTransactionIdLike(txnIdParentlike);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/txnIdLocal/like/{txnIdLocalValue}")
	@ApiMethod(description = "Get all monitoring event that are of local transaction id like value")
	public ResponseEntity<List<MonitoringEvent>> findByLocalTransactionIdLike(
			@ApiPathParam(name = "txnIdLocalValue") @PathVariable("txnIdLocalValue") String txnIdLocallike) {
		List<MonitoringEvent> eventList = eventService.findByLocalTransactionIdLike(txnIdLocallike);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/appName/like/{appNameValue}")
	@ApiMethod(description = "Get all monitoring event that are of application names like")
	public ResponseEntity<List<MonitoringEvent>> findByApplicationNameLike(
			@ApiPathParam(name = "appNameValue") @PathVariable("appNameValue") String appNamelike) {
		List<MonitoringEvent> eventList = eventService.findByApplicationNameLike(appNamelike);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	
	@GetMapping(value = "/events/msgFlowName/like/{msgFlowNameValue}")
	@ApiMethod(description = "Get all monitoring event that are of message flow name like")
	public ResponseEntity<List<MonitoringEvent>> findByMsgFlowNameLike(
			@ApiPathParam(name = "msgFlowNameValue") @PathVariable("msgFlowNameValue") String msgFlowNameLike) {
		List<MonitoringEvent> eventList = eventService.findByMsgFlowNameLike(msgFlowNameLike);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/eventTime/between/{startTime}/{endTime}/")
	@ApiMethod(description = "Get all monitoring events between timestamps from the database")
	public ResponseEntity<List<MonitoringEvent>> findByEventTimestampBetween(
			@ApiPathParam(name = "startTime") @PathVariable("startTime") String startTime,
			@ApiPathParam(name = "endTime") @PathVariable("endTime") String endTime) {
		List<MonitoringEvent> eventList = eventService.findByEventTimestampBetween(startTime, endTime);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
}
