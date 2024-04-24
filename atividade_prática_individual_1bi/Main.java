import Agencia.Banco;
import Lojas.Cliente;
import Lojas.Funcionario;
import Lojas.Loja;


public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        Loja[] lojas = {new Loja(banco, "Americanas"), new Loja(banco, "Novo Mundo")};
        Funcionario[] funcionarios = {new Funcionario(banco, "Maria", lojas[0]), new Funcionario(banco, "João", lojas[0]),
                new Funcionario(banco, "Claudia", lojas[1]), new Funcionario(banco, "Pedro", lojas[1])};
        Cliente[] clientes = {new Cliente(banco, "Cliente 1", lojas, funcionarios), new Cliente(banco, "Cliente 2", lojas, funcionarios),
                new Cliente(banco, "Cliente 3", lojas, funcionarios), new Cliente(banco, "Cliente 4", lojas, funcionarios), new Cliente(banco, "Cliente 5", lojas, funcionarios)};

        for (Funcionario funcionario : funcionarios) {
            funcionario.start();
        }

        for (Cliente cliente : clientes) {
            cliente.start();
        }

        for (Funcionario funcionario : funcionarios) {
            try {
                funcionario.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Cliente cliente : clientes) {
            try {
                cliente.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Loja loja : lojas) {
            loja.pagarSalarios(funcionarios);
        }

    }
}
