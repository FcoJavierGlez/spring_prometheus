package com.nttdata.bootcamp.prometheus.controller;

import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
@RestControllerEndpoint(id = "custom")
public class ActuatorCustom {
	
	private Counter counter;
	
	private Counter cpuHeat;
	private Counter cpuUse;
	
	private Counter ramHeat;
	private Counter ramUse;
	
	private Counter hddHeat;
	private Counter hddSpaceUse;
	private Counter hddSpaceFree;

	public ActuatorCustom( MeterRegistry registry ) {
		this.counter = Counter.builder("invocaciones.custom").description("Invocaciones totales").register(registry);
		this.setCpu(registry);
		this.setRam(registry);
		this.setHdd(registry);
		
	}
	
	private void setCpu( MeterRegistry registry ) {
		this.cpuHeat = Counter.builder("invocaciones.cpuHeat").description("Invocaciones cpu_heat").register(registry);
		this.cpuUse = Counter.builder("invocaciones.cpuUse").description("Invocaciones cpu_use").register(registry);
	}
	
	private void setRam( MeterRegistry registry ) {
		this.ramHeat = Counter.builder("invocaciones.ramHeat").description("Invocaciones ram_heat").register(registry);
		this.ramUse = Counter.builder("invocaciones.ramUse").description("Invocaciones ram_use").register(registry);
	}
	
	private void setHdd( MeterRegistry registry ) {
		this.hddHeat = Counter.builder("invocaciones.hddHeat").description("Invocaciones hdd_heat").register(registry);
		this.hddSpaceUse = Counter.builder("invocaciones.hddSpaceUse").description("Invocaciones hdd_space_use").register(registry);
		this.hddSpaceFree = Counter.builder("invocaciones.hddSpaceFree").description("Invocaciones hdd_space_use").register(registry);
	}
	
	@GetMapping("/cpu/heat")
	public double getCpuHeat() {
		counter.increment();
		cpuHeat.increment();
		return 62.1;
	}
	
	@GetMapping("/cpu/use")
	public double getCpuUse() {
		counter.increment();
		cpuUse.increment();
		return 44.6;
	}
	
	@GetMapping("/ram/heat")
	public double getRamHeat() {
		counter.increment();
		ramHeat.increment();
		return 32.8;
	}
	
	@GetMapping("/ram/use")
	public Long getRamUse() {
		counter.increment();
		ramUse.increment();
		return 4274254621L;
	}
	
	@GetMapping("/hdd/space/use")
	public Long getHddUse() {
		counter.increment();
		hddSpaceUse.increment();
		return 36277964521L;
	}
	
	@GetMapping("/hdd/space/free")
	public Long getHddFree() {
		counter.increment();
		hddSpaceFree.increment();
		return 124254621L;
	}
	
	@GetMapping("/hdd/heat")
	public double getHddHeat() {
		counter.increment();
		hddHeat.increment();
		return 36.7;
	}

}
