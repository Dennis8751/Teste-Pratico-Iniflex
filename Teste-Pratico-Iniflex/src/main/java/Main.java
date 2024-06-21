import javax.security.auth.login.AccountLockedException;
import javax.sound.midi.Soundbank;
import java.io.FilterOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.time.Period;


public class Main {
    public static void main(String[] args) {

        System.out.println("ABAIXO OS FUNCIONÁRIOS CADASTRADOS:\n");

        //Armazena os funcionários
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria",
                LocalDate.of(2000, Month.OCTOBER, 18),
                new BigDecimal("2009.44"),
                "Operador"));
        funcionarios.add(new Funcionario("João",
                LocalDate.of(1990, Month.MAY, 12),
                new BigDecimal("2284.38"),
                "Operador"));
        funcionarios.add(new Funcionario("Caio",
                LocalDate.of(1961, Month.MAY, 2),
                new BigDecimal("9836.14"),
                "Coordenador"));
        funcionarios.add(new Funcionario("Miguel",
                LocalDate.of(1988, Month.OCTOBER, 14),
                new BigDecimal("19119.88"),
                "Diretor"));
        funcionarios.add(new Funcionario("Alice",
                LocalDate.of(1995, Month.JANUARY, 5),
                new BigDecimal("2234.68"),
                "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor",
                LocalDate.of(1999, Month.NOVEMBER, 19),
                new BigDecimal("1582.72"),
                "Operador"));
        funcionarios.add(new Funcionario("Arthur",
                LocalDate.of(1993, Month.MARCH, 31),
                new BigDecimal("4071.84"),
                "Contador"));
        funcionarios.add(new Funcionario("Laura",
                LocalDate.of(1994, Month.JUNE, 8),
                new BigDecimal("3017.45"),
                "Gerente"));
        funcionarios.add(new Funcionario("Heloisa",
                LocalDate.of(2003, Month.MAY, 24),
                new BigDecimal("1606.85"),
                "Eletricista"));
        funcionarios.add(new Funcionario("Helena",
                LocalDate.of(1996, Month.SEPTEMBER, 2),
                new BigDecimal("2799.93"),
                "Gerente"));

        for (Funcionario listaFuncionarios : funcionarios){
            System.out.println(listaFuncionarios);
        }

        System.out.println("\n3.2 – Remover o funcionário “João” da lista.\n");
        funcionarios.remove(1);//Exclui a segunda pessoa da lista "João"
        for (Funcionario listaFuncionarios : funcionarios){
                        System.out.println(listaFuncionarios);
        }

        //atraves da operação lambda foi reajustado o salario com acrescimo de 10%
        funcionarios.forEach(novoSalario -> novoSalario.setSalario(novoSalario.getSalario().multiply(new BigDecimal(1.1))));

        System.out.println("\n3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.\n");
        for (Funcionario listaFuncionarios : funcionarios){
            System.out.println(listaFuncionarios);
        }

        System.out.println("\n3.6 – Imprimir os funcionários, agrupados por função.");

        Map<String, List<Funcionario>> funcionarioAgrupadoFuncao = funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

        for(Map.Entry<String, List<Funcionario>> entry : funcionarioAgrupadoFuncao.entrySet()){
            String funcao = entry.getKey();
            List<Funcionario> listaFuncionarios = entry.getValue();

            System.out.println("Função: " + funcao);
            for (Funcionario funcionario : listaFuncionarios){
                System.out.println(funcionario);
            }
        }

        System.out.println("\n3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.");

        //Filtro para aniversariante de outubro e dezembro
        List<Pessoa> aniversarioMes10e12 = funcionarios.stream()
                                            .filter(pessoa -> pessoa.getDataNascimento().getMonth() == Month.OCTOBER ||
                                                              pessoa.getDataNascimento().getMonth() == Month.DECEMBER)
                                            .collect(Collectors.toList());
        aniversarioMes10e12.forEach(System.out::println);

        System.out.println("\n3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.");

        Optional<Funcionario> maiorIdade = funcionarios.stream().max(Comparator.comparing(Pessoa::getIdade));

        //Uma condicional que se caso contiver um valor presente ".isPresent" em "Optional" devolve um bool
        if (maiorIdade.isPresent()){
            Pessoa pessoaMaisVelha = maiorIdade.get();
            int idadeMaisVelha = pessoaMaisVelha.getIdade();
            System.out.println("Funcionário: " + pessoaMaisVelha.getNome());
            System.out.println("Idade: " + idadeMaisVelha + " anos");
        }

        System.out.println("\n3.10 – Imprimir a lista de funcionários por ordem alfabética.");
        funcionarios.sort(Comparator.comparing(Pessoa::getNome));

        funcionarios.forEach(System.out::println);

        System.out.println("\n3.11 – Imprimir o total dos salários dos funcionários.");
        BigDecimal totalSalario = BigDecimal.ZERO;
        for (Funcionario funcionario : funcionarios) {
            totalSalario = totalSalario.add(funcionario.getSalario());
        }

        System.out.println(totalSalario.setScale(2, BigDecimal.ROUND_HALF_UP));

        System.out.println("\n3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.");

        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        for (Funcionario funcionario : funcionarios){
            BigDecimal salarioMinimoPorFuncionario = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(funcionario.getNome() + " ganha " + salarioMinimoPorFuncionario + " salário(s) minimo(s)");
        }

    }
}