    package com.medicalsoftcontable.medicalsoftcontable.dto.responseDTO;

    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class ApiResponse<T> {
        private boolean success;  // Cambiar de String a boolean
        private String message;
        private int status;
        private T data;

        public ApiResponse(Boolean success, String message, int status, T data) {
            this.success = success;
            this.message = message;
            this.status = status;
            this.data = data;
        }
    }


