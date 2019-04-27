package lz.controller;

public interface RowMapper<T> {
    public T mapRow(String record);
}