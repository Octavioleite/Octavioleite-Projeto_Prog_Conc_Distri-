package Lojas;
import Agencia.Banco;
import Agencia.Conta;

public class Funcionario extends Thread {
    private Banco banco;
    private String nome;
    private Loja loja;
    private Conta contaSalario;
    private Conta contaInvestimento;
    private static final int MAX_ITERACOES = 10;
    
    public Funcionario(Banco banco, String nome, Loja loja) {
        this.banco = banco;
        this.nome = nome;
        this.loja = loja;
        this.contaSalario = new Conta("Conta salário de " + nome + " da loja " + loja, 0);
        this.contaInvestimento = new Conta("Conta de investimento de " + nome, 0);
    }
    public String getNome() { 
        return nome;
    }

    @Override
    public void run() {
        int iteracoes = 0;
        while (iteracoes < MAX_ITERACOES && !isInterrupted()) {
            try {
                Thread.sleep((long) (Math.random() * 4000 + 1000)); 
                banco.transferirDinheiro(loja.getConta(), contaSalario, 1400);
                double investimento = 0.2 * 1400;
                if (contaSalario.getSaldo() >= 1400) { 
                    banco.transferirDinheiro(contaSalario, contaInvestimento, investimento);
                    System.out.println(nome + " investiu R$" + investimento + " na conta de investimento.");
                } else {
                    //SAÍDA OPCIONAL, POLUÍ MUITO O CONSOLE
                    //System.out.println(nome + " não pode investir, pois não recebeu o salário.");
                }
                iteracoes++;
            } catch (InterruptedException e) {
                System.err.println(nome + " interrompido durante o trabalho.");
                interrupt(); 
            }
        }
        System.out.println(nome + " concluiu o trabalho.");
    }
    

    public Conta getContaSalario() {
        return contaSalario;
    }
}