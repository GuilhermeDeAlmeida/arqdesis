import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

	public UsuarioTO consultarExtrato(UsuarioTO to) throws SQLException {

		String sqlSelect = "SELECT agencia, conta, saldo FROM conta c WHERE c.conta = ?";

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {

			stm.setLong(1, to.getConta());

			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					to.setAgencia(rs.getLong("agencia"));
					to.setConta(rs.getLong("conta"));
					to.setSaldo(rs.getLong("saldo"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return to;
	}

	public void efetuarSaque(UsuarioTO to, double vlrSacado) throws SQLException {

		to.setSaldo(to.getSaldo() - vlrSacado);

		String sqlUpdate = "UPDATE conta c SET c.saldo = ? WHERE c.conta = ?";

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setDouble(1, to.getSaldo());
			stm.setLong(2, to.getConta());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
