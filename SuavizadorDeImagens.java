import java.util.*;

class SuavizadorDeImagens
{
  public static void main(String[] args)
  {
    boolean fim = false;
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
      
      Scanner sc = new Scanner(System.in);
      int opcao = sc.nextInt();
      String nome;
      
      VisualizadorImagem vis
      if(opcao == 1)
      {
        System.out.println("Digite o nome do arquivo:");
        sc.nextLine();
        
        Imagem minhaImagem = LeituraEscritaImagem.leImagem(nome);
        if (minhaImagem == null)
          System.out.println("Arquivo não existente ou problemas na leitura");
        else
          LeituraEscritaImagem.escreveImagem(nome); // CÓDIGO NÃO COMPILA!
        continue;
      } // if(opcao == 1)
      
      
      if(opcao == 2)
      {
        VisualizadorImagem vis = new VisualizadorImagem();
        Imagem minhaImagem = LeituraEscritaImagem.leImagem(nome);
        if (minhaImagem == null)
          System.out.println("Arquivo não existente ou problemas na leitura");
        else
          vis.mostraImagem(minhaImagem, nome);
        continue;
      } // if(opcao == 2)
    
      
      if(opcao == 3)
      {
      }
    }
    
    
    
    // Cria instância do visualizador
    VisualizadorImagem vis = new VisualizadorImagem();
    
    // Lê imagem
    Imagem barbara = LeituraEscritaImagem.leImagem("Barbara.pgm");
    
    barbara.filtroGaussiano(0.9, 3);
    
    // Mostra imagem 
    vis.mostraImagem(barbara, "Barbara.pgm");
    
    // Escreve imagem
    LeituraEscritaImagem.escreveImagem("Barbara_copia.pgm", barbara);
  } // main
} // class