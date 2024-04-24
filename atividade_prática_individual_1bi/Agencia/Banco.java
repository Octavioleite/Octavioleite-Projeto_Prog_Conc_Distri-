package Agencia;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Banco {
    private Lock lock = new ReentrantLock();

    public void transferirDinheiro(Conta origem, Conta destino, double valor) {
        lock.lock();
        try {
            if (origem.getSaldo() >= valor) {
                origem.debitar(valor);
                destino.creditar(valor);
                System.out.println("TRANSFERÊNCIA: \n");
                System.out.println("Transferência de R$" + valor + " de " + origem.getTitular() + " para " + destino.getTitular() + " realizada com sucesso.");
                System.out.println("SALDO ATUALIZADO: \n");
                System.out.println("Saldo de " + origem.getTitular() + ": R$" + origem.getSaldo());
                System.out.println("Saldo de " + destino.getTitular() + ": R$" + destino.getSaldo());
            } else if (origem.getSaldo() < valor) {
                //SAÍDA OPCIONAL, POLUÍ MUITO O CONSOLE
                //System.out.println("Saldo insuficiente em " + origem.getTitular() + " para transferência de R$" + valor + " para " + destino.getTitular() + ".");
            }
        } finally {
            lock.unlock();
        }
    }
}
