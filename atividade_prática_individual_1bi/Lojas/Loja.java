package Lojas;
import Agencia.Banco;
import Agencia.Conta;

public class Loja {
    private Banco banco;
    private String nome;
    private Conta conta;

    public Loja(Banco banco, String nome) {
        this.banco = banco;
        this.nome = nome;
        this.conta = new Conta("Conta da loja " + nome, 0);
    }

    public void pagarSalarios(Funcionario[] funcionarios) {
        double totalSalarios = funcionarios.length * 1400;
        if (conta.getSaldo() >= totalSalarios) {
            for (Funcionario funcionario : funcionarios) {
                banco.transferirDinheiro(conta, funcionario.getContaSalario(), 1400);
            }
            System.out.println("Salários dos funcionários da loja " + nome + " foram pagos.");
        } else {
            //SAÍDA OPCIONAL, POLUÍ MUITO O CONSOLE
            //System.out.println("Saldo insuficiente na conta da loja " + nome + " para pagar os salários.");
        }
    }
    

    public Conta getConta() {
        return conta;
    }

    public String toString() {
        return nome;
    }

    public String getNome() {
        return nome;
    }
}
