package com.example.users.data.sql;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class SQLQueryCountAndExists {
    private JdbcTemplate jdbc;
    public SQLQueryCountAndExists (JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }
    private String whereQuery = "";

    private String tableName;

    private String orderByQuery = "";

    private List<Object> values = new ArrayList<>();

    public SQLQueryCountAndExists orderBy(String orders){
        if(!orderByQuery.isEmpty())
            orderByQuery += ", ";
        orderByQuery += orders;
        return this;
    }

    public SQLQueryCountAndExists where(String condition, Object... parameters){
        if(!whereQuery.isEmpty())
            whereQuery += " AND ";
        whereQuery += condition;
        values.addAll(Arrays.asList(parameters));
        return this;
    }
    public SQLQueryCountAndExists tableName(String tableName){
        this.tableName = tableName;
        return this;
    }




    public long count(){
        String query = "SELECT COUNT(*) FROM " + tableName;
        if(!whereQuery.isEmpty())
            query += " WHERE " + whereQuery;
        if(!orderByQuery.isEmpty())
            query += " ORDER BY " + orderByQuery;
        class tempMapperObject{
            Integer count = 0;

            tempMapperObject(){}
            tempMapperObject(Integer exists){
                this.count = exists;
            }
            tempMapperObject mapperObject(ResultSet resultSet, int rowNumber) throws SQLException{
                return new tempMapperObject(resultSet.getInt(1));
            }
        }
        return jdbc.queryForObject(query, new tempMapperObject()::mapperObject, values.toArray()).count;
    }

    public boolean exists(){
        String query = "";
        query += "SELECT EXISTS (SELECT * FROM " + tableName;
        if(!whereQuery.isEmpty())
            query += " WHERE " + whereQuery;
        query += ")";
        class tempMapperObject{
            Boolean exists = false;

            tempMapperObject(){}
            tempMapperObject(boolean exists){
                this.exists = exists;
            }
            tempMapperObject mapperObject(ResultSet resultSet, int rowNumber) throws SQLException{
                return new tempMapperObject(resultSet.getBoolean(1));
            }
        }
        return jdbc.queryForObject(query, new tempMapperObject()::mapperObject, values.toArray()).exists;

    }


}
