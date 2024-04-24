package Lojas;
import Agencia.Banco;
import Agencia.Conta;

public class Cliente extends Thread {
    private Banco banco;
    private String nome;
    private Loja[] lojas;
    private Funcionario[] funcionarios; 

    public Cliente(Banco banco, String nome, Loja[] lojas, Funcionario[] funcionarios) { 
        this.banco = banco;
        this.nome = nome;
        this.lojas = lojas;
        this.funcionarios = funcionarios; 
    }

    @Override
    public void run() {
        Conta conta = new Conta("Conta de " + nome, 1000);
        while (conta.getSaldo() > 0) {
            try {
                Thread.sleep((long) (Math.random() * 2000 + 1000)); 
                Loja loja = lojas[(int) (Math.random() * lojas.length)];
                double valor = (Math.random() < 0.5) ? 100 : 200;
                banco.transferirDinheiro(conta, loja.getConta(), valor);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
       
        for (Funcionario funcionario : funcionarios) {
            try {
                funcionario.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}