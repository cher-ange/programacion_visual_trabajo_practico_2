package ar.edu.unju.fi.exercise2.model;

import ar.edu.unju.fi.exercise2.constant.Month;

import java.util.Objects;

/**
 * Una efeméride.
 */
public class Event {

    private Integer code;
    private Month month;
    private Integer day;
    private String detail;

    public Event() {
    }

    public Event(
            Integer code,
            Month month,
            Integer day,
            String detail) {
        this.code = code;
        this.month = month;
        this.day = day;
        this.detail = detail;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Event event = (Event) object;
        return Objects.equals(code, event.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }

    @Override
    public String toString() {
        return "Event{" +
                "code=" + code +
                ", month=" + month +
                ", day=" + day +
                ", detail='" + detail + '\'' +
                '}';
    }
}
