package test.eshop.inventory.api.dto;

public class ResponseDTO<T>{

	T data;
	public T getData() {
		return data;
	}

	public ErrorDTO getError() {
		return error;
	}

	ErrorDTO error;
	
	public ResponseDTO(T data) {
		this.data = data;
	}
	
	public ResponseDTO(T data,ErrorDTO error) {
		this.data = data;
		this.error=error;
	}
	
	
}
