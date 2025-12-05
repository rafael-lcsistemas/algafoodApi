package com.algaworks.algafoodapi.api.exceptionhandler;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;

public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private LocalDateTime timestamp;
    private List<ProblemField> fields;

    public Problem(Integer status, String type, String title, String detail, String userMessage, LocalDateTime timestamp, List<ProblemField> fields) {
        this.status = status;
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.userMessage = userMessage;
        this.timestamp = timestamp;
        this.fields = fields;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<ProblemField> getFields() {
        return fields;
    }

    public void setFields(List<ProblemField> fields) {
        this.fields = fields;
    }

    public static class ProblemBuilder {
        private Integer status;
        private String type;
        private String title;
        private String detail;
        private String userMessage;
        private LocalDateTime timestamp;
        private List<ProblemField> fields;

        public ProblemBuilder status(Integer status) {
            this.status = status;
            return this;
        }

        public ProblemBuilder type(String type) {
            this.type = type;
            return this;
        }

        public ProblemBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ProblemBuilder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public ProblemBuilder userMessage(String userMessage) {
            this.userMessage = userMessage;
            return this;
        }

        public ProblemBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ProblemBuilder fields(List<ProblemField> fields) {
            this.fields = fields;
            return this;
        }

        public Problem build() {
            return new Problem(status, type, title, detail, userMessage, timestamp, fields);
        }
    }

    public static ProblemBuilder builder() {
        return new ProblemBuilder();
    }
}

