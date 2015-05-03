package entity;

import tn.esprit.Blues.entities.Operation;

public class OperationAction {

	private Operation operation;
	private String action;
	private Float value;
	private String color; 
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public OperationAction() {
	}
	public OperationAction(Operation operation, String action) {
		this.operation = operation;
		this.action = action;
	}
	
}
