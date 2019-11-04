public class Conta {
  private String numero;
  private double saldo;
  private Cliente cliente;

  public Conta(String numero, double saldo, Cliente cliente) {
    this.numero = numero;
    this.saldo = saldo;
    this.cliente = cliente;
  }

  public void creditar(double valor){
    saldo = saldo + valor;
  }
  public void debitar(double valor){
    saldo = saldo - valor;
  }
  public Cliente getCliente(){
    return cliente;
  }
  public String getNumero() {
    return numero;
  }
  public double getSaldo() {
    return saldo;
  }
  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }
  public void setNumero(String num) {
    this.numero = num;
  }
  public void setSaldo(double valor) {
    saldo = valor;
  }
  public void transferir(Conta c, double v) {
    this.debitar(v);
    c.creditar(v);
  }
}