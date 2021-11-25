package model;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
public class Team {

    private int teamId;
    private String teamName;

    public Team(int teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
    }

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public Team(ResultSet rs) throws SQLException {
        this.teamId = rs.getInt("team_id");
        this.teamName = rs.getString("team_name");
    }
}
