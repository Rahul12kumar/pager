package dao;

import exceptions.InvalidTeamDetailsException;
import model.Team;
import utils.DbConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TeamDBHelper {

    public static final String INSERT_QUERY = "insert into team(team_name) values (?) returning *;";

    public static Team insertTeam(String teamName) throws InvalidTeamDetailsException {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            c = DbConnect.getConnection();
            ps = c.prepareStatement(INSERT_QUERY);
            ps.setString(1,teamName);
            rs = ps.executeQuery();
            if( rs.next() ) {
                return new Team(rs);
            }
        }
        catch (Exception e) {
            throw new InvalidTeamDetailsException("Invalid Team Details");
        }
        finally {
            DbConnect.closeConnection(ps);
            DbConnect.closeConnection(c);
            DbConnect.closeConnection(rs);
        }
        return null;
    }
}
