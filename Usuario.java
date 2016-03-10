import java.sql.SQLException;

public class Usuario {
	private String nome;
	private String senha;
	private long conta;
	private long agencia;
	private double saldo;

	public Usuario(String nome, String senha, long conta, long agencia, double saldo) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.conta = conta;
		this.agencia = agencia;
		this.saldo = saldo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public long getConta() {
		return conta;
	}

	public void setConta(long conta) {
		this.conta = conta;
	}

	public long getAgencia() {
		return agencia;
	}

	public void setAgencia(long agencia) {
		this.agencia = agencia;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void consultarExtrato() {
		UsuarioDAO dao = new UsuarioDAO();
		UsuarioTO to = new UsuarioTO();
		to.setAgencia(agencia);
		to.setConta(conta);
		to.setSaldo(saldo);

		try {
			dao.consultarExtrato(to);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void efetuarSaque(double vlrSacado) {
		UsuarioDAO dao = new UsuarioDAO();
		UsuarioTO to = new UsuarioTO();
		to.setSaldo(saldo);
		to.setConta(conta);
		try {
			dao.efetuarSaque(to, vlrSacado);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
