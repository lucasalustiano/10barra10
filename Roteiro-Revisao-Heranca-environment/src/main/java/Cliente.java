
public class Cliente {
  private String cpf;
  private String nome;

  public Cliente(String cpf, String nome) {
    this.cpf = cpf;
    this.nome = nome;
  }

  public String getCpf() {
    return cpf;
  }

  public String getNome() {
    return nome;
  }

  public void setCpf(String newCpf) {
    cpf = newCpf;
  }

  public void setNome(String newNome) {
    nome = newNome;
  }

}
