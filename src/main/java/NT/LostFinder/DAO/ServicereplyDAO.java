package NT.LostFinder.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import NT.LostFinder.DTO.Servicereply;

public class ServicereplyDAO {
	public static ServicereplyDAO sr=null;
	private ServicereplyDAO() {
	}
	synchronized public static ServicereplyDAO getBoardDAO() {
		if(sr==null)
			sr=new ServicereplyDAO();
		return sr;
	}
	synchronized public static boolean example(Servicereply data) {
		String sql="sql commend";
		try(Connection con=Connect.getInstance();PreparedStatement ps=con.prepareStatement(sql);){
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	synchronized public static boolean createServicereply(Servicereply data) {
		String sql="insert into servicereply values(servicereply_seq.nextval,?,?,?,sysdate)";
		try(Connection con=Connect.getInstance();PreparedStatement ps=con.prepareStatement(sql);){
			ps.setString(1, Integer.toString(data.getService_no()));
			ps.setString(2, data.getMember_id());
			ps.setString(3, data.getReply_content());
			if(ps.executeUpdate()==1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	synchronized public static ArrayList<Servicereply> listServicereply() { // 담은 객체를 return 값으로 보냄 
		String sql="select * from servicereply";
		try(Connection con=Connect.getInstance();PreparedStatement ps=con.prepareStatement(sql);ResultSet rs=ps.executeQuery()){
			ArrayList<Servicereply>list = new ArrayList<Servicereply>();
			if(rs.next()) {
				do {
					list.add(new Servicereply(rs.getString(1),Integer.parseInt(rs.getString(2)),rs.getString(3),rs.getString(4),rs.getTimestamp(5)));
				}while(rs.next());
				return list;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	synchronized public static ArrayList<Servicereply> viewservicereply() {
		String sql="select * from servicereply";
		try(Connection con=Connect.getInstance();PreparedStatement ps=con.prepareStatement(sql);ResultSet rs=ps.executeQuery()){
			ArrayList<Servicereply>list = new ArrayList<Servicereply>();
			if(rs.next()) {
				do {
					list.add(new Servicereply(rs.getString(1),Integer.parseInt(rs.getString(2)),rs.getString(3),rs.getString(4),rs.getTimestamp(5)));
				}while(rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}