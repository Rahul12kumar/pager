package model;


import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
public class Developers {

    private int id;
    private int teamId;
    private String name;
    private String phoneNumber;

    public Developers(int teamId, String name, String phoneNumber) {
        this.teamId = teamId;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Developers(ResultSet rs) throws SQLException {
        this.phoneNumber = rs.getString("mobile_number");
        this.name = rs.getString("name");
    }
}
