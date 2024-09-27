import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    //BD em memória
    private List<Livro> acervo = new ArrayList<>();

    

    public void adicionar(Livro livro)throws Exception{
        if(livro.getTitulo()== null || livro.getTitulo().isEmpty())
            throw new Exception("Não é permitido cadastrar um livro sem titulo");
        for (Livro livrofor : acervo) {
            if (livrofor.getTitulo().equalsIgnoreCase(livro.getTitulo()))
                throw new Exception("Ja existe um livro cadastrado com este titulo");
        }
        if (livro.getTitulo() == null || livro.getTitulo().trim().isEmpty()) {
            throw new Exception("O título não pode ser vazio.");
        }      

        if (livro.getAutor() == null || livro.getAutor().trim().isEmpty()) {
            throw new Exception("O autor não pode ser vazio.");
        }

        int anoAtual = LocalDate.now().getYear();
        if (livro.getAnoPublicacao() < 1400 ||livro.getAnoPublicacao()  > anoAtual) {
            throw new Exception("O ano de publicação deve estar entre 1400 e " + anoAtual + ".");
        }

        if (livro.getnPaginas() <= 0) {
            throw new Exception("O número de páginas deve ser maior que zero.");
        }

        acervo.add(livro);
    }

    public List<Livro> pesquisarPorTitulo(String titulo){
        List<Livro> livrosEncontrados =  new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getTitulo().toLowerCase().contains((titulo.toLowerCase()))) {
                livrosEncontrados.add(livro);
            }
        }
        return livrosEncontrados;
    }

    public void removerPorTitulo(String titulo) throws Exception{
        boolean existe = false;
        for (Livro livro : acervo){
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                acervo.remove(livro);
                existe = true;
                break;
            }
        }
        if (!existe)
            throw new Exception("Não existe livro com este título");
    }

    public List<Livro> pesquisarTodos(){
        return this.acervo;
    }
    
}