package org.example.letersi.services;
import org.example.letersi.domain.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.letersi.services.AbstractService.getConnection;


public class GenreService {
    public List<Genre> getGenres() throws SQLException {
        List<Genre> genres = new ArrayList<>();
        Connection con = getConnection();
        Statement statement = null;
        ResultSet resultset = null;

        try{
            statement = con.createStatement();
            String sql = "SELECT * FROM genre";
            resultset = statement.executeQuery(sql);
            while(resultset.next()){
                Genre genre = new Genre();
                genre.setId(resultset.getInt("id"));
                genre.setName(resultset.getString("name"));
                genres.add(genre);
            }
            return genres;
        } finally {
            con.close();
            if(resultset != null){
                resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
    }
}
