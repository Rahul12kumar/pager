package dao;

import exceptions.InvalidTeamDetailsException;
import model.Developers;
import utils.DbConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class DeveloperDBHelper {

    public static final String INSERT_QUERY = "insert into developers(team_id,name,mobile_number) values(?,?,?) returning *;";

    public static final String SELECT_DEVELOPERS = "select * from developers where team_id = ? limit 1";


    public static void addDevelopers(List<Developers> developersList) throws InvalidTeamDetailsException {

        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            c = DbConnect.getConnection();
            ps = c.prepareStatement(INSERT_QUERY);
            c.setAutoCommit(false);
            for(Developers developer:developersList) {
                ps.setInt(1,developer.getTeamId());
                ps.setString(2,developer.getName());
                ps.setString(3,developer.getPhoneNumber());
                ps.addBatch();
            }
            ps.executeBatch();
            c.commit();
        }
        catch (Exception e) {
            throw new InvalidTeamDetailsException("Invalid Developers Details");
        }
        finally {
            DbConnect.closeConnection(ps);
            DbConnect.closeConnection(c);
            DbConnect.closeConnection(rs);
        }
    }

    public static Developers selectDevelopers(int teamId) throws InvalidTeamDetailsException {

        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            c = DbConnect.getConnection();
            ps = c.prepareStatement(SELECT_DEVELOPERS);
            ps.setInt(1,teamId);
            rs = ps.executeQuery();
            if(rs.next()){
                return new Developers(rs);
            }
        }
        catch (Exception e) {
            throw new InvalidTeamDetailsException("Invalid Developers Details");
        }
        finally {
            DbConnect.closeConnection(ps);
            DbConnect.closeConnection(c);
            DbConnect.closeConnection(rs);
        }
        return null;
    }
}
