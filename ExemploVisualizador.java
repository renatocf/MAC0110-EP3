class ExemploVisualizador
{
  public static void main(String[] args)
  {
    // Cria instância do visualizador
    VisualizadorImagem vis = new VisualizadorImagem();
    
    // Lê imagem
    Imagem lena = LeituraEscritaImagem.leImagem("Lena.pgm");
    
    //for(int i=0; i < 10; i++)
      lena.filtroMediano(3);
    
    // Mostra imagem 
    vis.mostraImagem(lena, "Lena.pgm");
      
    
    // Escreve imagem
    //LeituraEscritaImagem.escreveImagem("Barbara_copia.pgm", barbara);
  }
}