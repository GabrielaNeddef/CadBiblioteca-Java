import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Biblioteca biblio = new Biblioteca();
    static Scanner input = new Scanner(System.in);

    public static void clear() {
        
        System.out.print("\033[H\033[2J");
        System.out.flush(); 
    }

    private static int inputNumerico(String mensagem){
        int valor = 0;
        boolean entradaValida = false;
        System.out.println(mensagem);
        do{
        String valorStr = input.nextLine();
        try{
            valor = Integer.parseInt(valorStr);
            entradaValida = true;
        }catch(Exception e){
            System.out.println("erro. por favor informe um  numero inteiro");
        }
    }while(!entradaValida);
        return valor;
    }


    private static void listar() {
        // List<Livro> livros = biblio.pesquisarTodos();
        var livros = biblio.pesquisarTodos();
        livros.sort(Comparator.comparing(Livro::getTitulo));
        System.out.println("======== LISTA DE LIVROS =========");
        for (Livro livro : livros) {
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Ano: " + livro.getAnoPublicacao());
            System.out.println("N. Páginas: " + livro.getnPaginas());
        }
        input.nextLine();
    }

    private static void adicionar() {
        Livro novoLivro = new Livro();
        System.out.println("======== ADICIONANDO NOVO LIVRO ========");
        System.out.print("Informe o título do livro: ");
        String titulo = input.nextLine();
        novoLivro.setTitulo(titulo);

        System.out.print("Informe o nome do autor: ");
        novoLivro.setAutor(input.nextLine());

        System.out.print("Informe o ano de publicação: ");
        novoLivro.setAnoPublicacao(input.nextInt());
        input.nextLine(); 

        System.out.print("Informe o número de páginas: ");
        novoLivro.setnPaginas(input.nextInt());
        input.nextLine(); 

        try {
            biblio.adicionar(novoLivro);
            System.out.println("Livro adicionado com Sucesso!");
        } catch (Exception e) {
            System.out.println("ERRO:" + e.getMessage());
        }
        input.nextLine(); 
    }

    private static void pesquisarPorTitulo() {
        clear(); 
        System.out.print("Informe o título do livro a ser pesquisado: ");
        String titulo = input.nextLine();
        List<Livro> livrosEncontrados = biblio.pesquisarPorTitulo(titulo);

        if (livrosEncontrados.isEmpty()) {
            System.out.println("Nenhum livro encontrado com o título: " + titulo);
        } else {
            System.out.println("======== LIVROS ENCONTRADOS =========");
            for (Livro livro : livrosEncontrados) {
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Ano: " + livro.getAnoPublicacao());
                System.out.println("N. Páginas: " + livro.getnPaginas());
                System.out.println("-----------------------------------");
            }
        }
        input.nextLine(); // Pausa para o usuário visualizar
    }

    // Método para remover livro por título
    private static void removerPorTitulo() {
        clear(); // Limpa a tela antes de remover
        System.out.print("Informe o título do livro a ser removido: ");
        String titulo = input.nextLine();

        try {
            biblio.removerPorTitulo(titulo);
            System.out.println("Livro removido com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao remover o livro: " + e.getMessage());
        }
        input.nextLine(); // Pausa para o usuário visualizar
    }


    public static void main(String[] args) {
        clear();
        String menu = """
                SISTEMA DE GERENCIAMENTO DE BIBLIOTECA
                Escolha uma das opções:
                1 - Adicionar novo livro;
                2 - Listar todos os livros;
                3 - Pesquisar livro;
                4 - Remover livro;
                0 - Sair;
                """;
        int opcao;
        do {
            //System.out.println(menu);
            //opcao = input.nextInt();
            //input.nextLine(); // limpar buffer
            clear();
            opcao = inputNumerico(menu);
            switch (opcao) {
                case 0:
                    clear();
                    System.out.println("VOLTE SEMPRE!!!");
                    break;
                case 1:
                    clear();
                    adicionar();
                    break;
                case 2:
                    clear();
                    listar();
                    break;
                case 3:
                    clear();
                    pesquisarPorTitulo();
                    // pesquisar por titulo
                    break;
                case 4:
                    clear();
                    removerPorTitulo();
                    // remover
                    break;
                default:
                    clear();
                    System.out.println("Opção Inválida!!!");
                    input.nextLine();
                    break;
            }
        } while (opcao != 0);
    }
}