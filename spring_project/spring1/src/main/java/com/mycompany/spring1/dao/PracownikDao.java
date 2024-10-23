/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spring1.dao;


import com.mycompany.spring1.beans.Pracownik;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


/**
 *
 * @author student
 */
public class PracownikDao {
    
    JdbcTemplate template;
    
    public void setTemplate(JdbcTemplate template) {
        this.template = template; //wstrzyknięcie przez metodę set
    }
    
    public int save(Pracownik p) {
        String sql = "insert into pracownik (nazwisko,pensja,firma) " 
        + "values('" + p.getNazwisko() + "'," + p.getPensja()
        + ",'" + p.getFirma() + "')";
        return template.update(sql);
    }
    public List<Pracownik> getAll() {
        return template.query("select * from pracownik", new RowMapper<Pracownik>() {
                @Override
                public Pracownik mapRow(ResultSet rs, int row) 
                throws SQLException{
                Pracownik e = new Pracownik();
                e.setId(rs.getInt(1));
                e.setNazwisko(rs.getString(2));
                e.setFirma(rs.getString(4));
                e.setPensja(rs.getFloat(3));
                return e;
            }
        });
    }
}
