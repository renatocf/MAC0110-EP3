import java.util.*;

class NovoVisualizador
{
  public static void main(String[] args)
  {
    boolean fim = false;
    String nome = "a";
    
    while(!fim)
    {
      System.out.println("Menu");
      System.out.println("====");
      System.out.println("1) Ler imagem");
      System.out.println("2) Visualizar imagem");
      System.out.println("3) Suavizar com filtro médio");
      System.out.println("4) Suavizar com filtro mediano");
      System.out.println("5) Suavizar com filtro gaussiano");
      System.out.println("6) Gravar imagem");
      System.out.println("7) Fim");
      // Cria instância do visualizador
      
      Scanner sc = new Scanner(System.in);
      // Instanciando um objeto da classe Scanner, para interação
      // com o usuário via teclado.
      
      
      System.out.println("Qual a sua opção?");
      int opcao = sc.nextInt();
      // Permite ao usuário escolher uma opção no menu. A entrada
      // deve ser um int.
      
      VisualizadorImagem vis = new VisualizadorImagem();
      
      if(opcao == 1)
      {        
        System.out.println("Digite o nome do arquivo:");
        nome = sc.next();
      }
      
      Imagem imagemDoUsuario = LeituraEscritaImagem.leImagem(nome);
      
      imagemDoUsuario.filtroGaussiano(0.9, 3);
      
      // Mostra imagem 
      vis.mostraImagem(imagemDoUsuario, "Barbara.pgm");
      
      // Escreve imagem
      LeituraEscritaImagem.escreveImagem("Barbara_copia.pgm", imagemDoUsuario);
    
    } // while(!fim)
    
  } // main
} // Class NovoVisualizador