package com.revature.repositories.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.revature.enums.AccountState;
import com.revature.enums.TransferState;
import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.TransferNotFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transfer;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.TransferDAO;
import com.revature.util.ConnectionFactory;

public class TransferPostgressDAO implements TransferDAO {
	private static ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
	private static CustomerDAO custDAO = new CustomerPostgresDAO();

	@Override
	public Transfer addTransfer(Transfer t) throws SQLException {
		Connection conn = cf.getConnection();

		String sql = "insert into transfers values (current_timestamp, ?, ?, ?, ?, ?, ?) returning transfers_id;";
		// this sets the customer data
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, t.getAmmount());
		statement.setString(2, t.getSendingCustomer().getUsername());
		statement.setInt(3, t.getSendingAccountId());
		statement.setString(4, t.getReceivingCustomer().getUsername());
		statement.setInt(5, t.getReceivingAccountId());
		statement.setString(6, t.getTransferState().toString());
		ResultSet rs = statement.executeQuery();
		rs.next();
		t.setTransferId(rs.getInt("transfers_id"));

		return t;
	}

	@Override
	public Transfer updateTransferByID(Transfer t, int id) throws SQLException, AccountNotFoundException {
		Connection conn = cf.getConnection();

		String sql = "update transfers set transfer_state=? where transfers_id = ?";
		// this sets the customer data
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, t.getTransferState().toString());
		statement.setInt(2, t.getTransferId());
		if (statement.executeUpdate() == 0) {
			throw new AccountNotFoundException();
		}
		
		return t;
	}


	@Override
	public Collection<Transfer> findAllPendingTransfersForCustomer(String username) throws AccountNotFoundException, UserNotFoundException, SQLException, TransferNotFoundException {
		Connection conn = cf.getConnection();
		ArrayList<Transfer> transfers = new ArrayList<>();
		
		String sql = "select * from transfers  where transfer_state like 'PENDING' and (receiving_customer like ? or sending_customer like ?)";
		
		
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, username);
		statement.setString(2, username);
		ResultSet res = statement.executeQuery();

		while (res.next()) {
			TransferState state = TransferState.valueOf(res.getString("transfer_state"));
			Customer send = custDAO.findCustomerByName(res.getString("sending_customer"));
			Customer receive = custDAO.findCustomerByName(res.getString("receiving_customer"));
			transfers.add(
					new Transfer(res.getInt("ammount"),send, res.getInt("sending_account_id"), 
					receive, res.getInt("receiving_account_id"), res.getInt("transfers_id"), state));
		}
		
		if(transfers.size() == 0) {
			throw new TransferNotFoundException();
		}
		return transfers;
	}

	@Override
	public Transfer findTransferByID(int id) throws TransferNotFoundException, UserNotFoundException, AccountNotFoundException, SQLException {
		Connection conn = cf.getConnection();
		String sql = "select * from transfers where transfers_id = ?;";

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet res = statement.executeQuery();
		if(!res.next()) {
			throw new TransferNotFoundException();
		}

		TransferState state = TransferState.valueOf(res.getString("transfer_state"));
		Customer send = custDAO.findCustomerByName(res.getString("sending_customer"));
		Customer receive = custDAO.findCustomerByName(res.getString("receiving_customer"));
		
		return new Transfer(res.getInt("ammount"),send, res.getInt("sending_account_id"), 
				receive, res.getInt("receiving_account_id"), res.getInt("transfers_id"), state);
		
	}

}
