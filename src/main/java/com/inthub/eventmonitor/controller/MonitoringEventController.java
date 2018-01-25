package com.inthub.eventmonitor.controller;

import com.inthub.eventmonitor.models.MonitoringEvent;
import com.inthub.eventmonitor.services.MonitoringEventService;
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
public class MonitoringEventController {
	
	//@Autowired MonitoringEventRepository eventRepository;
	@Autowired MonitoringEventService eventService;
	
	//@Autowired MonitoringEventService eventService;
	
	@GetMapping("/events/count")
	public long getEventsCount() {
		return eventService.count();
	}
	
	@GetMapping("/events/page/{pageNumber}/{pageSize}")
	public Page<MonitoringEvent> getAllEventsBypage(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize) {
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
	public List<MonitoringEvent> getAllEvents() {
		Sort sortByEventTimestampDesc = new Sort(Sort.Direction.DESC, "event.timestamp");
		List<MonitoringEvent> eventList = eventService.findAll(sortByEventTimestampDesc);
		//eventList.forEach(System.out::println);
		return eventList; 
	}

	@GetMapping(value = "/events/txnIdGlobal/{txnIdGlobalValue}")
	public ResponseEntity<List<MonitoringEvent>> getByGlobalTransactionId(@PathVariable("txnIdGlobalValue") String globalTxnId) {
		List<MonitoringEvent> eventList = eventService.findByGlobalTransactionId(globalTxnId);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/txnIdParent/{txnIdParentValue}")
	public ResponseEntity<List<MonitoringEvent>> getByParentTransactionId(@PathVariable("txnIdParentValue") String parentTxnId) {
		List<MonitoringEvent> eventList = eventService.findByParentTransactionId(parentTxnId);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/txnIdLocal/{txnIdLocalValue}")
	public ResponseEntity<List<MonitoringEvent>> getByLocalTransactionId(@PathVariable("txnIdLocalValue") String localTxnId) {
		List<MonitoringEvent> eventList = eventService.findByLocalTransactionId(localTxnId);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/businessProcess/{businessProcessValue}")
	public ResponseEntity<List<MonitoringEvent>> findByBusinessProcess(@PathVariable("businessProcessValue") String businessProcess) {
		List<MonitoringEvent> eventList = eventService.findByBusinessProcess(businessProcess);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/sourceApp/{sourceAppValue}")
	public ResponseEntity<List<MonitoringEvent>> findBySourceAppInfo(@PathVariable("sourceAppValue") String sourceAppValue) {
		List<MonitoringEvent> eventList = eventService.findBySourceAppInfo(sourceAppValue);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/destinationApp/{destinationAppValue}")
	public ResponseEntity<List<MonitoringEvent>> findByDestinationAppInfo(@PathVariable("destinationAppValue") String destinationAppValue) {
		List<MonitoringEvent> eventList = eventService.findByDestinationAppInfo(destinationAppValue);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/appName/{appNameValue}")
	public ResponseEntity<List<MonitoringEvent>> findByApplicationName(@PathVariable("appNameValue") String applicationNameValue) {
		List<MonitoringEvent> eventList = eventService.findByApplicationName(applicationNameValue);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/msgFlowName/{msgFlowNameValue}/")
	public ResponseEntity<List<MonitoringEvent>> findByMsgFlowName(@PathVariable("msgFlowNameValue") String msgFlowNameValue) {
		List<MonitoringEvent> eventList = eventService.findByMsgFlowName(msgFlowNameValue);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/errorCode/{errorCodeValue}")
	public ResponseEntity<List<MonitoringEvent>> findByErrorCode(@PathVariable("eventTimestampValue") String errorCodeValue) {
		List<MonitoringEvent> eventList = eventService.findByErrorCode(errorCodeValue);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/allErrors")
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
	public ResponseEntity<List<MonitoringEvent>> findByGlobalTransactionIdLike(@PathVariable("txnIdGlobalValue") String txnIdGloballike) {
		List<MonitoringEvent> eventList = eventService.findByGlobalTransactionIdLike(txnIdGloballike);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/txnIdParent/like/{txnIdParentValue}")
	public ResponseEntity<List<MonitoringEvent>> findByParentTransactionIdLike(@PathVariable("txnIdParentValue") String txnIdParentlike) {
		List<MonitoringEvent> eventList = eventService.findByParentTransactionIdLike(txnIdParentlike);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/txnIdLocal/like/{txnIdLocalValue}")
	public ResponseEntity<List<MonitoringEvent>> findByLocalTransactionIdLike(@PathVariable("txnIdLocalValue") String txnIdLocallike) {
		List<MonitoringEvent> eventList = eventService.findByLocalTransactionIdLike(txnIdLocallike);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/appName/like/{appNameValue}")
	public ResponseEntity<List<MonitoringEvent>> findByApplicationNameLike(@PathVariable("appNameValue") String appNamelike) {
		List<MonitoringEvent> eventList = eventService.findByApplicationNameLike(appNamelike);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	
	@GetMapping(value = "/events/msgFlowName/like/{msgFlowNameValue}")
	public ResponseEntity<List<MonitoringEvent>> findByMsgFlowNameLike(@PathVariable("msgFlowNameValue") String msgFlowNameLike) {
		List<MonitoringEvent> eventList = eventService.findByMsgFlowNameLike(msgFlowNameLike);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/events/eventTime/between/{startTime}/{endTime}/")
	public ResponseEntity<List<MonitoringEvent>> findByEventTimestampBetween(@PathVariable("startTime") String startTime, @PathVariable("endTime") String endTime) {
		List<MonitoringEvent> eventList = eventService.findByEventTimestampBetween(startTime, endTime);
		if (eventList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(eventList, HttpStatus.OK);
		}
	}
	
}
